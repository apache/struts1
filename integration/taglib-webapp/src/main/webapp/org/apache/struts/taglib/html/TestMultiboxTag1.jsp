<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testMultiboxPropertyTrue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" accesskey="a" value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" accesskey="a" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" alt="Testing alt attribute"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray"
                       altKey="alternate.bundle.message" value="value1"
                       bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueDisabled_True">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="true" value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueDisabled_False1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="false"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyTrueDisabled_False2">
    <!-- This was changed recently by overwhelming consensus. The prior
    functionality
    was very counter-intuitive. It used to be that putting disabled="false"
    was the same as putting disabled="true". Craig sited the confusion is due
    to the HTML spec.
    -->
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="anything but true"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onblur="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onblur="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onchange="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onclick="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" ondblclick="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onfocus="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeydown="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeypress="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeyup="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousedown="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousemove="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseout="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseover="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyTrueOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseup="Put script here"
                       value="value1"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value1"
               checked="checked" onmouseup="Put script here">
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
