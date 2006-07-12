package org.apache.struts.chain.commands.servlet;

import junit.framework.TestCase;

import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.commands.UnauthorizedActionException;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.mock.MockActionServlet;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockPrincipal;
import org.apache.struts.mock.MockServletConfig;
import org.apache.struts.mock.MockServletContext;

/* JUnitTest case for class: org.apache.struts.chain.commands.servlet.AuthorizeAction */
public class TestAuthorizeAction extends TestCase {
    MockHttpServletRequest request = null;
    MockPrincipal principal = null;
    ServletWebContext swContext = null;
    ServletActionContext saContext = null;
    AuthorizeAction command = null;

    public TestAuthorizeAction(String _name) {
        super(_name);
    }

    /* setUp method for test case */
    protected void setUp() throws Exception {
        this.request = new MockHttpServletRequest();
        this.principal =
            new MockPrincipal("Mr. Macri", new String[] { "administrator" });
        this.request.setUserPrincipal(principal);

        MockServletConfig servletConfig = new MockServletConfig();
        MockServletContext servletContext = new MockServletContext();
        MockActionServlet servlet =
            new MockActionServlet(servletContext, servletConfig);

        servlet.initInternal();

        this.saContext =
            new ServletActionContext(servletContext, request,
                new MockHttpServletResponse());

        this.saContext.setActionServlet(servlet);
        this.command = new AuthorizeAction();
    }

    /* tearDown method for test case */
    protected void tearDown() {
    }

    public void testAuthorizeOneRole()
        throws Exception {
        ActionConfig config = new ActionConfig();

        config.setPath("/testAuthorizeOneRole");
        config.setRoles("administrator");
        this.saContext.setActionConfig(config);

        boolean result = command.execute(saContext);

        assertFalse(result);
    }

    public void testAuthorizeOneOfManyRoles()
        throws Exception {
        ActionConfig config = new ActionConfig();

        config.setPath("/testAuthorizeOneOfManyRoles");
        config.setRoles("administrator,roustabout,memory");
        this.saContext.setActionConfig(config);

        boolean result = command.execute(saContext);

        assertFalse(result);
    }

    public void testAuthorizeNoRoles()
        throws Exception {
        ActionConfig config = new ActionConfig();

        config.setPath("/testAuthorizeNoRoles");
        this.saContext.setActionConfig(config);

        boolean result = command.execute(saContext);

        assertFalse(result);
    }

    public void testNotAuthorizedOneRole()
        throws Exception {
        ActionConfig config = new ActionConfig();

        config.setPath("/testNotAuthorizedOneRole");
        config.setRoles("roustabout");
        this.saContext.setActionConfig(config);

        try {
            boolean result = command.execute(saContext);
        } catch (UnauthorizedActionException ex) {
        }
    }

    public void testNotAuthorizedOneOfManyRoles()
        throws Exception {
        ActionConfig config = new ActionConfig();

        config.setPath("/testNotAuthorizedOneOfManyRoles");
        config.setRoles("roustabout,memory");
        this.saContext.setActionConfig(config);

        try {
            boolean result = command.execute(saContext);
        } catch (UnauthorizedActionException ex) {
        }
    }

    /* Executes the test case */
    public static void main(String[] argv) {
        String[] testCaseList = { TestAuthorizeAction.class.getName() };

        junit.textui.TestRunner.main(testCaseList);
    }
}
