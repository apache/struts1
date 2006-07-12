package org.apache.struts.chain.commands.servlet;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.mock.MockActionServlet;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/* JUnitTest case for class: org.apache.struts.chain.commands.servlet.SetOriginalURI */
public class TestSetOriginalURI extends TestCase {
    SetOriginalURI command = null;

    public TestSetOriginalURI(String _name) {
        super(_name);
    }

    /* setUp method for test case */
    protected void setUp() throws Exception {
        this.command = new SetOriginalURI();
    }

    /* tearDown method for test case */
    protected void tearDown() {
    }

    public void testSetOriginalURI()
        throws Exception {
        MockHttpServletRequest request =
            new MockHttpServletRequest("foo/", "bar.do", null, null);
        MockServletConfig servletConfig = new MockServletConfig();
        MockServletContext servletContext = new MockServletContext();
        MockActionServlet servlet =
            new MockActionServlet(servletContext, servletConfig);

        servlet.initInternal();

        ServletActionContext saContext =
            new ServletActionContext(servletContext, request,
                new MockHttpServletResponse());

        saContext.setActionServlet(servlet);

        boolean result = command.execute(saContext);

        assertTrue(!result);

        String uri = (String) request.getAttribute(Globals.ORIGINAL_URI_KEY);

        assertTrue("Original uri not correct: " + uri, "bar.do".equals(uri));

        request.setPathElements("foo/", "bar2.do", null, null);
        uri = (String) request.getAttribute(Globals.ORIGINAL_URI_KEY);
        assertTrue("Original uri not correct: " + uri, "bar.do".equals(uri));
    }

    /* Executes the test case */
    public static void main(String[] argv) {
        String[] testCaseList = { TestSetOriginalURI.class.getName() };

        junit.textui.TestRunner.main(testCaseList);
    }
}
