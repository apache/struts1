/*
 * Copyright 2026 The Struts1-Forever Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.taglib.html;

import java.io.ByteArrayInputStream;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.commons.validator.ValidatorResources;
import org.apache.struts.Globals;
import org.apache.struts.config.impl.ModuleConfigImpl;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;
import org.apache.struts.mock.MockJspWriter;
import org.apache.struts.mock.MockPageContext;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorPlugIn;

/**
 * XSS tests for {@link JavascriptValidatorTag} — field key injection.
 *
 * <h3>Fix: field.getKey() is now escaped before insertion into JS string literal</h3>
 *
 * <p>{@code JavascriptValidatorTag.createDynamicJavascript()} generates JavaScript
 * like {@code this.a0 = new Array("<fieldKey>", ...)}. The field key was previously
 * interpolated directly without escaping, while the validation message was correctly
 * escaped with {@code escapeQuotes()}. The fix wraps {@code field.getKey()} with
 * the existing {@code escapeJavascript()} method.</p>
 *
 * <p>A field key containing {@code "} (double-quote) would break out of the JavaScript
 * string literal and allow injection of arbitrary JavaScript — XSS.</p>
 */
public class TestJavascriptValidatorTag extends TestCase {

    /**
     * Minimal validator-rules XML that declares a "required" rule with a trivial
     * JavaScript body. The rule name "required" is referenced in the form definition.
     */
    private static final String VALIDATOR_RULES_XML =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<!DOCTYPE form-validation PUBLIC\n"
        + "    \"-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.1.3//EN\"\n"
        + "    \"http://jakarta.apache.org/commons/dtds/validator_1_1_3.dtd\">\n"
        + "<form-validation>\n"
        + "  <global>\n"
        + "    <validator name=\"required\"\n"
        + "               classname=\"org.apache.commons.validator.GenericValidator\"\n"
        + "               method=\"isBlankOrNull\"\n"
        + "               methodParams=\"java.lang.Object\"\n"
        + "               msg=\"errors.required\">\n"
        + "      <javascript><![CDATA[function required() { return true; }]]></javascript>\n"
        + "    </validator>\n"
        + "  </global>\n"
        + "</form-validation>\n";

    /**
     * Form-validation XML that defines a form named "testForm" with one field
     * whose property contains a double-quote — the XSS payload.
     */
    private static final String FORM_VALIDATION_XML_TEMPLATE =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<!DOCTYPE form-validation PUBLIC\n"
        + "    \"-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.1.3//EN\"\n"
        + "    \"http://jakarta.apache.org/commons/dtds/validator_1_1_3.dtd\">\n"
        + "<form-validation>\n"
        + "  <formset>\n"
        + "    <form name=\"testForm\">\n"
        + "      <field property=\"FIELD_PROPERTY\" depends=\"required\">\n"
        + "        <arg0 key=\"label.test\" />\n"
        + "      </field>\n"
        + "    </form>\n"
        + "  </formset>\n"
        + "</form-validation>\n";

    private MockServletContext context;
    private MockServletConfig config;
    private MockHttpSession session;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockPageContext pageContext;
    private MockJspWriter out;

    protected void setUp() throws Exception {
        context = new MockServletContext();
        context.setAttribute(Globals.MODULE_KEY + "", new ModuleConfigImpl(""));
        MessageResources emptyResources = MessageResources.getMessageResources(
                "org.apache.struts.taglib.html.EmptyBundle");
        context.setAttribute(Globals.MESSAGES_KEY, emptyResources);

        config = new MockServletConfig(context);
        session = new MockHttpSession(context);
        request = new MockHttpServletRequest(session);
        request.setLocale(Locale.getDefault());
        response = new MockHttpServletResponse();
        pageContext = new MockPageContext(config, request, response);
        out = new MockJspWriter();
        pageContext.setJspWriter(out);
    }

    /**
     * Build a {@link ValidatorResources} containing one form named "testForm"
     * with one field whose property is {@code fieldProperty}, then register it
     * in the servlet context so {@link JavascriptValidatorTag} can find it.
     */
    private void registerValidatorResources(String fieldProperty) throws Exception {
        String formXml = FORM_VALIDATION_XML_TEMPLATE.replace("FIELD_PROPERTY", fieldProperty);

        byte[] rulesBytes = VALIDATOR_RULES_XML.getBytes("UTF-8");
        byte[] formBytes  = formXml.getBytes("UTF-8");

        ValidatorResources resources = new ValidatorResources(
                new java.io.InputStream[] {
                    new ByteArrayInputStream(rulesBytes),
                    new ByteArrayInputStream(formBytes)
                });

        context.setAttribute(ValidatorPlugIn.VALIDATOR_KEY + "", resources);
    }

    /**
     * Run the tag for form "testForm" and return the captured output.
     */
    private String renderTag() throws Exception {
        out = new MockJspWriter();
        pageContext.setJspWriter(out);

        JavascriptValidatorTag tag = new JavascriptValidatorTag();
        tag.setPageContext(pageContext);
        tag.setFormName("testForm");
        tag.setStaticJavascript("false");
        tag.setDynamicJavascript("true");
        tag.setHtmlComment("false");

        tag.doStartTag();
        tag.doEndTag();

        return out.getContent();
    }

    // ------------------------------------------------------------------
    // Safe field key — baseline
    // ------------------------------------------------------------------

    /**
     * A field key with only safe characters must appear as-is in the output.
     */
    public void testFieldKey_SafeName_AppearsInOutput() throws Exception {
        registerValidatorResources("safeField");
        String output = renderTag();
        assertTrue("Safe field key must appear in generated JavaScript",
                output.contains("safeField"));
    }

    // ------------------------------------------------------------------
    // XSS via double-quote in field key (the vulnerability)
    // ------------------------------------------------------------------

    /**
     * A double-quote in a field key must be backslash-escaped in the JavaScript
     * string literal to prevent breaking out of the string context (XSS).
     *
     * <p>Note: {@code "&quot;"} is the XML entity for {@code "}; the XML parser
     * delivers a literal {@code "} to {@code field.getKey()}, so the JS output
     * must contain the backslash-escaped form {@code bad\"field}.</p>
     */
    public void testFieldKey_ContainsDoubleQuote_IsEscapedInOutput() throws Exception {
        // "&quot;" is the XML entity for '"'; field.getKey() returns the literal char '"'
        registerValidatorResources("bad&quot;field");
        String output = renderTag();
        // The raw double-quote must NOT appear inside the Array(...) call
        assertFalse("Raw double-quote in field key must not appear unescaped in JS output",
                output.contains("\"bad\"field\""));
        // The escaped form (backslash + double-quote) must be present
        assertTrue("Double-quote in field key must be backslash-escaped as \\\"",
                output.contains("bad\\\"field"));
    }
}
