package org.apache.strutsel.taglib.html;

import com.meterware.httpunit.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.xml.transform.*;

import junit.framework.*;

import org.apache.cactus.*;
import org.apache.struts.taglib.html.*;
import org.apache.strutsel.taglib.utils.*;
import org.apache.taglibs.standard.tag.common.core.*;
import org.apache.taglibs.standard.tag.el.core.*;
import org.w3c.dom.*;
import org.xml.sax.*;


public class TestELCancelTag
    extends JspTagTestCase {

    protected static final String PROPERTY_ATTR_VALUE         = "stuff";
    protected static final String REQUIRED_DISABLED_VALUE_KEY = 
            "RequiredDisabledValue";
    protected static final String REQUIRED_TYPE_VALUE_KEY  = 
            "RequiredTypeValue";
    protected static final String REQUIRED_VALUE_VALUE_KEY = 
            "RequiredValueValue";
    protected static final String REQUIRED_NAME_VALUE_KEY = 
            "RequiredNameValue";
    protected ELCancelTag         elCancelTag              = null;

    public TestELCancelTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.main(
                new String[] { TestELCancelTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELCancelTag.class);
    }

    public void setUp() {
        elCancelTag = new ELCancelTag();
        elCancelTag.setPageContext(pageContext);
        elCancelTag.setProperty(PROPERTY_ATTR_VALUE);
    }

    public void tearDown() {
        elCancelTag = null;
    }

    /**
     * Tests a plain "cancel" tag, with all default attribute values.
     */
    public void testPlain()
                   throws ServletException, JspException {
        HttpServletResponse response          = (HttpServletResponse)pageContext.getResponse();

        String              requiredNameValue = PROPERTY_ATTR_VALUE;
        response.addHeader(REQUIRED_NAME_VALUE_KEY, requiredNameValue);
        String              requiredTypeValue = "submit";
        response.addHeader(REQUIRED_TYPE_VALUE_KEY, requiredTypeValue);

        String requiredValueValue = "Cancel";
        response.addHeader(REQUIRED_VALUE_VALUE_KEY, requiredValueValue);

        int startTagReturn  = elCancelTag.doStartTag();
        int afterBodyReturn = elCancelTag.doAfterBody();
        int endTagReturn    = elCancelTag.doEndTag();
    }

    public void endPlain(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, 
                                              new String[] {
                "name", "type", "value" }, 
                                              false);

            checkAttrValue(attrMap, testResponse, REQUIRED_NAME_VALUE_KEY, 
                           "cancel", "name");
            checkAttrValue(attrMap, testResponse, REQUIRED_TYPE_VALUE_KEY, 
                           "cancel", "type");
            checkAttrValue(attrMap, testResponse, REQUIRED_VALUE_VALUE_KEY, 
                           "cancel", "value");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * Tests the "disabled" attribute.
     */
    public void testDisabled()
                      throws ServletException, JspException {

        elCancelTag.setDisabled(true);

        String requiredDisabledValue = "disabled";
        response.addHeader(REQUIRED_DISABLED_VALUE_KEY, requiredDisabledValue);

        int startTagReturn  = elCancelTag.doStartTag();
        int afterBodyReturn = elCancelTag.doAfterBody();
        int endTagReturn    = elCancelTag.doEndTag();
    }

    public void endDisabled(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());

            HashMap attrMap = new HashMap();
            DOMHelper.recordFoundAttributes(testResponse.getDOM(), 
                                            "/html/body/input", attrMap);
            DOMHelper.verifyAttributesPresent(attrMap, 
                                              new String[] { "disabled" }, 
                                              true);
            checkAttrValue(attrMap, testResponse, REQUIRED_DISABLED_VALUE_KEY, 
                           "cancel", "disabled");
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
