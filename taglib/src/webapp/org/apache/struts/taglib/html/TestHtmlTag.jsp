<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testHtml">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html>

        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html>

        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="true">
            <bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html xmlns="http://www.w3.org/1999/xhtml">
        true
        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="True">
            <bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html xmlns="http://www.w3.org/1999/xhtml">
        true
        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="TRUE">
            <bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html xmlns="http://www.w3.org/1999/xhtml">
        true
        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="false">

        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html>

        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="False">

        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html>

        </html>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:html xhtml="FALSE">

        </html:html>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html>

        </html>
    </bean:define>
</logic:equal>

<%
    String expected = "";
    String compareTo = "";

    if (pageContext.getAttribute("EXPECTED_RESULTS") == null) {
        throw new javax.servlet.jsp.JspException(
                "No tests on this page were called.  Please verify that you've setup the tests correctly.");
    } else {
        expected = pageContext.getAttribute("TEST_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
