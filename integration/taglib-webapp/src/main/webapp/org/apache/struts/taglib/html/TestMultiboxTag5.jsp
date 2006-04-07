<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="org.apache.struts.action.ActionMessage" %>
<%@ page import="org.apache.struts.action.ActionMessages" %>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testMultiboxPropertyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       style="Put some value here"
                       errorStyle="some error style"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" style="Put some value here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyErrorStyle">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("stringArray",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       style="Put some value here"
                       errorStyle="some error style"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" style="some error style">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       styleClass="Put some value here"
                       errorStyleClass="some error style class"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" class="Put some value here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyErrorStyleClass">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("stringArray",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       styleClass="Put some value here"
                       errorStyleClass="some error style class"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" class="some error style class">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       styleId="Put some value here"
                       errorStyleId="some error style id"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" id="Put some value here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyErrorStyleId">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("stringArray",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"
                       styleId="Put some value here"
                       errorStyleId="some error style id"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" id="some error style id">
    </bean:define>
</logic:equal>


<%
    String expected = "";
    String compareTo = "";

    if (pageContext.getAttribute("EXPECTED_RESULTS") == null) {
        throw new javax.servlet.jsp.JspException(
                "No tests on this page were called.  Please verify that you've setup the tests correctly.");
    } else {
        expected = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("TEST_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
