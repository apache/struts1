<%@page import="org.apache.cactus.server.*,
                org.apache.cactus.util.log.LogService" session="true" %><%

    /**                                                
     *                                                 
     * Note:                                           
     * It is very important not to put any character between the end
     * of the page tag and the beginning of the java code expression, otherwise,
     * the generated servlet containss a 'out.println("\r\n");' and this breaks
     * our mechanism !
     */

    /**
     * This JSP is used as a proxy to call your server-side unit tests. We use
     * a JSP rather than a servlet because for testing custom JSP tags for
     * example we need access to JSP implicit objects (PageContext and
     * JspWriter).
     */

    /**
     * Initialise logging if not already initialised
     */
    if (!LogService.getInstance().isInitialized())
    {
        LogService.getInstance().init("/log_server.properties");
    }

    JspImplicitObjects objects = new JspImplicitObjects();
    objects.setHttpServletRequest(request);
    objects.setHttpServletResponse(response);
    objects.setServletConfig(config);
    objects.setServletContext(application);
    objects.setJspWriter(out);
    objects.setPageContext(pageContext);

    JspTestRedirector redirector = new JspTestRedirector();
    redirector.doGet(objects);
%>
