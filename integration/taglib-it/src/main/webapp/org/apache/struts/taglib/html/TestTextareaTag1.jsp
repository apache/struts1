<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testTextareaProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" accesskey="a"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" accesskey="a">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" alt="Testing alt attribute"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" alt="Testing alt attribute">Test
            Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" alt="Testing Message">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" altKey="alternate.bundle.message"
                       bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" alt="Testing Message">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" alt="Message D'Essai">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyCols">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" cols="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" cols="10">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyDisabled">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" disabled="disabled">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onblur="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onblur="Put script here">Test Value</textarea>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testTextareaPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onchange="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onchange="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onclick="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" ondblclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" ondblclick="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onfocus="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onfocus="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onkeydown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onkeydown="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onkeypress="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onkeypress="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onkeyup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onkeyup="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onmousedown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onmousedown="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onmousemove="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onmousemove="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onmouseout="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onmouseout="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onmouseover="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onmouseover="Put script here">Test
            Value</textarea>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testTextareaPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:textarea property="string" onmouseup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <textarea name="string" onmouseup="Put script here">Test
            Value</textarea>
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
