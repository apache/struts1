<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testResetProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" accesskey="a"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" accesskey="a" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" alt="Testing alt attribute"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" altKey="alternate.bundle.message"
                    bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyDisabled_True">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyDisabled_False1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyDisabled_False2">
    <!-- This was changed recently by overwhelming consensus. The prior
    functionality
    was very counter-intuitive. It used to be that putting disabled="false"
    was the same as putting disabled="true". Craig sited the confusion is due
    to the HTML spec.
    -->
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" disabled="anything but true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onblur="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onblur="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onchange="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" ondblclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onfocus="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onkeydown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onkeypress="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onkeyup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onmousedown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onmousemove="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onmouseout="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onmouseover="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" onmouseup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
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
