<%@page import="org.apache.commons.cactus.server.*" session="false" %><%

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

    JspTestCaller caller = new JspTestCaller();

    JspImplicitObjects objects = new JspImplicitObjects();
    objects.m_Config = config;
    objects.m_Request = request;
    objects.m_Response = response;
    objects.m_PageContext = pageContext;
    objects.m_JspWriter = out;

    caller.doTest(objects);

%>