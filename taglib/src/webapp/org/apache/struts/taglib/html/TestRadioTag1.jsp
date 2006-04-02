<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testRadioProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" value="Test Value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Test Value"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" accesskey="a"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" accesskey="a"
               value="Put Some Value Here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" alt="Testing alt attribute"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" altKey="default.bundle.message"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" altKey="no.such.key"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" altKey="alternate.bundle.message"
                    value="Put Some Value Here" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" altKey="default.bundle.message"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" altKey="no.such.key"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyDisabled">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" disabled="true"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onblur="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onblur="Put script here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onchange="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onclick="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" ondblclick="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onfocus="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onkeydown="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onkeypress="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onkeyup="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onmousedown="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onmousemove="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onmouseout="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onmouseover="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testRadioPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" onmouseup="Put script here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
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
