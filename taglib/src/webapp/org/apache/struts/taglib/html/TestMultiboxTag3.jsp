<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testMultiboxPropertyFalse">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" accesskey="a" value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" accesskey="a"
               value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" alt="Testing alt attribute"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseDisabled_True">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="true"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseDisabled_False1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="false"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxPropertyFalseDisabled_False2">
    <!-- This was changed recently by overwhelming consensus. The prior
    functionality
    was very counter-intuitive. It used to be that putting disabled="false"
    was the same as putting disabled="true". Craig sited the confusion is due
    to the HTML spec.
    -->
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="anything but true"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onblur="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onblur="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onchange="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onclick="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" ondblclick="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onfocus="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeydown="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeypress="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeyup="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousedown="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousemove="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseout="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseover="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxPropertyFalseOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseup="Put script here"
                       value="value100"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
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
        expected = pageContext.getAttribute("TEST_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
