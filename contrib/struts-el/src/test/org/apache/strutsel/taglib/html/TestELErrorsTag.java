package org.apache.strutsel.taglib.html;

import com.meterware.httpunit.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.xml.transform.*;
import junit.framework.*;
import org.apache.cactus.*;
import org.apache.commons.logging.*;
import org.apache.commons.logging.impl.*;
import org.apache.struts.action.*;
import org.apache.struts.taglib.html.*;
import org.apache.strutsel.taglib.utils.*;
import org.apache.taglibs.standard.tag.common.core.*;
import org.apache.taglibs.standard.tag.el.core.*;
import org.w3c.dom.*;
import org.xml.sax.*;


public class TestELErrorsTag
    extends JspTagTestCase {
    protected static final String FOOTER_VALUE            = "zzz";
    protected static final String HEADER_VALUE            = "aaa";
    protected static final String PREFIX_VALUE            = "[[[";
    protected static final String PROPERTY_KEY            = "property";
    protected static final String REQUIRED_TEXT_VALUE_KEY = 
            "RequiredTextValue";
    protected static final String SUFFIX_VALUE            = "]]]";
    protected static final String XXX_VALUE               = "xxx";
    protected static final String YYY_VALUE               = "yyy";
    protected ELErrorsTag         elErrorsTag             = null;
    private static Log            log                     = 
            LogFactory.getLog(TestELErrorsTag.class);

    public TestELErrorsTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELErrorsTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELErrorsTag.class);
    }

    public void setUp() {
        elErrorsTag = new ELErrorsTag();
        elErrorsTag.setPageContext(pageContext);
    }

    /**
     * Tests simple global error with all tag attributes set to default, and no
     * header, footer, prefix or suffix.
     */
    public void testPlain()
                   throws ServletException, JspException {
        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(ActionErrors.GLOBAL_ERROR, error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = XXX_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests multiple errors put in errors list, with two different property
     * names, but referring to the same error, but still using the default
     * value of "property", which will cause both errors to be retrieved.
     */
    public void testMultiProperties()
                             throws ServletException, JspException {
        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(PROPERTY_KEY + "1", error);
        errors.add(PROPERTY_KEY + "2", error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = XXX_VALUE + " " + XXX_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endMultiProperties(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests putting in two errors in errors list, with two different property
     * names, and referring to two different error keys, and the "property"
     * attribute set to only one of them.
     */
    public void testOnlyWantOneProperty()
                                 throws ServletException, JspException {
        elErrorsTag.setProperty(PROPERTY_KEY + "2");

        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error1.misc", XXX_VALUE);
        messageResources.addMessage("error2.misc", YYY_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        errors.add(PROPERTY_KEY + "1", new ActionError("error1.misc"));
        errors.add(PROPERTY_KEY + "2", new ActionError("error2.misc"));
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = YYY_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endOnlyWantOneProperty(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests one error on a specific property, with "property" attribute set to
     * default.
     */
    public void testProperty()
                      throws ServletException, JspException {
        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(PROPERTY_KEY, error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = XXX_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endProperty(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Just like "testPropertySpecified", but the "property" value is an EL
     * expression, evaluating to the same value as in the other test.
     */
    public void testPropertySpecifiedEL()
                                 throws ServletException, JspException {
        String varName = "targetVar";
        pageContext.setAttribute(varName, PROPERTY_KEY);
        elErrorsTag.setProperty("${" + varName + "}");
        testPropertySpecified();
    }

    public void endPropertySpecifiedEL(com.meterware.httpunit.WebResponse testResponse) {
        endPropertySpecified(testResponse);
    }

    /**
     * Tests one error in errors list, set to a property, and the "property"
     * attribute set to that property key.
     */
    public void testPropertySpecified()
                               throws ServletException, JspException {
        elErrorsTag.setProperty(PROPERTY_KEY);

        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(PROPERTY_KEY, error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = XXX_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endPropertySpecified(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests one error in the errors list, set to a property, and the
     * "property" attribute set to a different property key.
     */
    public void testPropertySpecifiedWrong()
                                    throws ServletException, JspException {
        elErrorsTag.setProperty(PROPERTY_KEY + "x");

        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(PROPERTY_KEY, error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = "";
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endPropertySpecifiedWrong(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    public void tearDown() {
        elErrorsTag = null;
    }

    /**
     * Tests a single error in errors list, with the "header" and "footer"
     * resource set to non-empty strings.
     */
    public void testHeaderFooter()
                          throws ServletException, JspException {
        HashMapMessageResources messageResources = new HashMapMessageResources(
                                                           null, null, true);
        messageResources.addMessage("error.misc", XXX_VALUE);
        messageResources.addMessage("errors.header", HEADER_VALUE);
        messageResources.addMessage("errors.footer", FOOTER_VALUE);
        String bundle   = elErrorsTag.getBundle();
        if (bundle == null)
            bundle   = Action.MESSAGES_KEY;
        pageContext.setAttribute(bundle, messageResources, 
                                 PageContext.APPLICATION_SCOPE);

        ActionErrors errors = new ActionErrors();
        ActionError  error = new ActionError("error.misc");
        errors.add(ActionErrors.GLOBAL_ERROR, error);
        pageContext.setAttribute(elErrorsTag.getName(), errors, 
                                 PageContext.REQUEST_SCOPE);

        HttpServletResponse response     = (HttpServletResponse)pageContext.getResponse();
        String              requiredText = HEADER_VALUE + " " + XXX_VALUE + 
                                           " " + FOOTER_VALUE;
        response.addHeader(REQUIRED_TEXT_VALUE_KEY, requiredText);

        int startTagReturn  = elErrorsTag.doStartTag();
        int afterBodyReturn = elErrorsTag.doAfterBody();
        int endTagReturn    = elErrorsTag.doEndTag();
    }

    public void endHeaderFooter(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            String nodeText = DOMHelper.getNodeText(document, "/html/body");
            log.debug("nodeText[" + nodeText + "]");

            String requiredTextValue = (String)testResponse.getHeaderField(
                                               REQUIRED_TEXT_VALUE_KEY);

            if (!nodeText.equals(requiredTextValue)) {
                fail("The <errors> tag instance should have resulted " + 
                     "in the text " + "\"" + requiredTextValue + 
                     "\", but instead had the value \"" + nodeText + "\".");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
