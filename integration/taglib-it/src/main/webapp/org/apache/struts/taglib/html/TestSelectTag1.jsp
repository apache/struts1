<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testSelectPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" alt="Testing alt attribute">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" alt="Testing alt attribute">Some Body
            Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" altKey="default.bundle.message">Some
            Body Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" alt="Testing Message">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" altKey="no.such.key">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" altKey="alternate.bundle.message"
                     bundle="alternate">Some Body Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" alt="Testing Message">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" altKey="default.bundle.message">Some
            Body Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" alt="Message D'Essai">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" altKey="no.such.key">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyDisabled">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="true">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" disabled="disabled">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyMultiple">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" multiple="true">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" multiple="multiple">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onblur="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onblur="Put script here">Some Body Here</select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSelectPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onchange="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onchange="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onclick="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onclick="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" ondblclick="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" ondblclick="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onfocus="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onfocus="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onkeydown="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onkeydown="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onkeypress="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onkeypress="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onkeyup="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onkeyup="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onmousedown="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onmousedown="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onmousemove="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onmousemove="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onmouseout="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onmouseout="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onmouseover="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onmouseover="Put script here">Some Body
            Here</select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSelectPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" onmouseup="Put script here">Some Body
            Here</html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" onmouseup="Put script here">Some Body
            Here</select>
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
