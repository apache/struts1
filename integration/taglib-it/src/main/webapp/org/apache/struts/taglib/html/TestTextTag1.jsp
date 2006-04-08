<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testTextProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" accesskey="a"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" accesskey="a" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" alt="Testing alt attribute"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" altKey="alternate.bundle.message"
                   bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyDisabled">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyMaxlength">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" maxlength="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" maxlength="10" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onblur="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onblur="Put script here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onchange="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" ondblclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onfocus="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onkeydown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onkeypress="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onkeyup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onmousedown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onmousemove="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onmouseout="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onmouseover="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:text property="string" onmouseup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="text" name="string" value="Test Value"
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
