/*
 * Copyright 2002,2004 The Apache Software Foundation.
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

package org.apache.struts.faces.sysclient;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBase;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHead;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlResetInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * <p>Test case for verifying <code>FacesContext</code> information against
 * what is visible in the Struts <code>Action</code> method that is invoked.</p>
 *
 * @version $Rev$ $Date$
 */

public class ContextTestCase extends AbstractTestCase {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Construct a new instance of this test case.</p>
     *
     * @param name Name of the new test case
     */
    public ContextTestCase(String name) {

        super(name);

    }


    // ------------------------------------------------------ Instance Variables


    // ------------------------------------------------------ Test Setup Methods


    /**
     * <p>Set up the instance variables required for this test case.</p>
     */
    public void setUp() throws Exception {

        super.setUp();
        page("/context.faces");

    }


    /**
     * <p>Return the set of tests included in this test suite.</p>
     */
    public static Test suite() {

        return (new TestSuite(ContextTestCase.class));

    }


    /**
     * <p>Tear down instance variables required by this test case.</p>
     */
    public void tearDown() {

        super.tearDown();

    }



    // ------------------------------------------------- Individual Test Methods


    /**
     * <p>Verify the content of a pristine page returned when executing this
     * view for the first time.</p>
     */
    public void testPristine() throws Exception {

        HtmlSpan span = null;
        assertEquals("context", title());

        // Validate FacesContext Values

        span = (HtmlSpan) element("form:renderKitIdFC");
        assertEquals("HTML_BASIC", span.asText());

        span = (HtmlSpan) element("form:viewIdFC");
        assertEquals("/context.jsp", span.asText());

        // Validate ExternalContext Values

        span = (HtmlSpan) element("form:authTypeEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:remoteUserEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:requestContextPathEC");
        assertEquals("/struts-faces-systest1", span.asText()); // FIXME - Ant property?


        span = (HtmlSpan) element("form:requestLocaleEC");
        String formRequestLocaleEC = span.asText(); // FIXME - validate this

        span = (HtmlSpan) element("form:requestPathInfoEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:requestServletPathEC");
        assertEquals("/context.jsp", span.asText());

        // Validate HttpServletRequest Values

        span = (HtmlSpan) element("form:authTypeRQ");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:contextPathRQ");
        assertEquals("/struts-faces-systest1", span.asText()); // FIXME - Ant property?

        span = (HtmlSpan) element("form:localeRQ");
        assertEquals(formRequestLocaleEC, span.asText());

        span = (HtmlSpan) element("form:pathInfoRQ");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:remoteUserRQ");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:servletPathRQ");
        assertEquals("/context.jsp", span.asText());

        // Validate ServletContext Values

        span = (HtmlSpan) element("form:majorVersionSC");
        assertEquals("2", span.asText());

        span = (HtmlSpan) element("form:minorVersionSC");
        assertTrue("3".equals(span.asText()) || "4".equals(span.asText()));

    }


    /**
     * <p>Submit the initial form and validate the resulting values.</p>
     */
    public void testSubmit() throws Exception {

        HtmlSpan span = null;
        HtmlSpan spanCA = null;
        HtmlSubmitInput submit = (HtmlSubmitInput) element("form:submit");
        submit(submit);
        assertEquals("context1", title());

        // Validate FacesContext Values

        span = (HtmlSpan) element("form:renderKitIdFC");
        assertEquals("HTML_BASIC", span.asText());

        span = (HtmlSpan) element("form:viewIdFC");
        assertEquals("/context1.jsp", span.asText());

        // Validate ExternalContext Values

        span = (HtmlSpan) element("form:authTypeEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:remoteUserEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:requestContextPathEC");
        assertEquals("/struts-faces-systest1", span.asText()); // FIXME - Ant property?

        span = (HtmlSpan) element("form:requestLocaleEC");
        String formRequestLocaleEC = span.asText(); // FIXME - validate this

        span = (HtmlSpan) element("form:requestPathInfoEC");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:requestServletPathEC");
        assertEquals("/context1.jsp", span.asText());

        // Validate HttpServletRequest Values

        span = (HtmlSpan) element("form:authTypeRQ");
        assertEquals("", span.asText());

        span = (HtmlSpan) element("form:contextPathRQ");
        assertEquals("/struts-faces-systest1", span.asText()); // FIXME - Ant property?
        spanCA = (HtmlSpan) element("form:contextPathCA");
        assertEquals(span.asText(), spanCA.asText());

        span = (HtmlSpan) element("form:localeRQ");
        assertEquals(formRequestLocaleEC, span.asText());
        spanCA = (HtmlSpan) element("form:localeCA");
        assertEquals(span.asText(), spanCA.asText());

        span = (HtmlSpan) element("form:pathInfoRQ");
        assertEquals("", span.asText());
        spanCA = (HtmlSpan) element("form:pathInfoCA");
        assertEquals(span.asText(), spanCA.asText());

        span = (HtmlSpan) element("form:remoteUserRQ");
        assertEquals("", span.asText());
        spanCA = (HtmlSpan) element("form:remoteUserCA");
        assertEquals(span.asText(), spanCA.asText());

        span = (HtmlSpan) element("form:servletPathRQ");
        assertEquals("/context1.jsp", span.asText());
        spanCA = (HtmlSpan) element("form:servletPathCA");
        assertEquals("/context.faces", spanCA.asText());

        // Validate ServletContext Values

        span = (HtmlSpan) element("form:majorVersionSC");
        assertEquals("2", span.asText());

        span = (HtmlSpan) element("form:minorVersionSC");
        assertTrue("3".equals(span.asText()) || "4".equals(span.asText()));

    }


}
