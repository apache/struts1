<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testPasswordProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" accesskey="a"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" accesskey="a" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" alt="Testing alt attribute"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" altKey="alternate.bundle.message"
                       bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyDisabled">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyMaxlength">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" maxlength="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" maxlength="10"
               value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onblur="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onblur="Put script here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onchange="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" ondblclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onfocus="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onkeydown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onkeypress="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onkeyup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onmousedown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onmousemove="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onmouseout="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onmouseover="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testPasswordPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" onmouseup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               onmouseup="Put script here">
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
