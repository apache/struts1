package org.apache.strutsel.taglib.html;

import java.net.MalformedURLException;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ApplicationConfig;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;

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


public class TestELFrameTag
    extends JspTagTestCase {

    private static final String FORWARD_VALUE   = "stuff";
    private static final String PATH_VALUE      = "/stuff";

    protected ELFrameTag   elFrameTag  = null;

    public TestELFrameTag(String theName) {
        super(theName);
    }

    public static void main(String[] args) {
        junit.awtui.TestRunner.
            main(new String[] { TestELFrameTag.class.getName() });
    }

    public static Test suite() {
        return new TestSuite(TestELFrameTag.class);
    }

    public void setUp() {
        elFrameTag = new ELFrameTag();
        elFrameTag.setPageContext(pageContext);
    }

    public void tearDown() {
        elFrameTag = null;
    }

    /**
     * Tests all attributes unset, which is illegal.  At least one of
     * "forward", "href", or "page" needs to be set.
     */
    public void testPlain()
                   throws ServletException, JspException {
        HttpServletResponse   response =
            (HttpServletResponse)pageContext.getResponse();

        boolean   gotCorrectException  = false;

        try {
            int startTagReturn  = elFrameTag.doStartTag();
        } catch (JspException ex) {
            // This is supposed to happen.
            gotCorrectException  = true;
        } catch (Exception ex) {
            fail();
        }

        if (!gotCorrectException)
            fail();
    }

    /**
     * Tests setting "forward" attribute to a Forward with a null Path.
     */
    public void testForward()
        throws ServletException, JspException {

        ActionServlet   actionServlet  = new ActionServlet();
        actionServlet.init(pageContext.getServletConfig());
        actionServlet.init();

        ApplicationConfig  appConfig   = new ApplicationConfig("");

        pageContext.setAttribute(Action.APPLICATION_KEY, appConfig,
                                 PageContext.APPLICATION_SCOPE);

        ActionForward   actionForward  = new ActionForward();
        actionForward.setName(FORWARD_VALUE);
        actionForward.setPath(PATH_VALUE);
        appConfig.addForwardConfig(actionForward);

        elFrameTag.setForward(FORWARD_VALUE);

        HttpServletResponse   response =
            (HttpServletResponse)pageContext.getResponse();

        int startTagReturn  = elFrameTag.doStartTag();
        int afterBodyReturn = elFrameTag.doAfterBody();
        int endTagReturn    = elFrameTag.doEndTag();
    }

    public void endForward(com.meterware.httpunit.WebResponse testResponse) {
        try {
            TestHelper.printResponse(testResponse);

            org.w3c.dom.Document document = testResponse.getDOM();
            DOMHelper.printNode(document.getDocumentElement());
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }
}
