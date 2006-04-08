<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testSubmitProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" accesskey="a"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" accesskey="a" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" alt="Testing alt attribute"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAltKey3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" altKey="alternate.bundle.message"
                     bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" altKey="no.such.key"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyDisabled_True">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyDisabled_False1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyDisabled_False2">
    <!-- This was changed recently by overwhelming consensus. The prior
    functionality
    was very counter-intuitive. It used to be that putting disabled="false"
    was the same as putting disabled="true". Craig sited the confusion is due
    to the HTML spec.
    -->
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" disabled="anything but true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onblur="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onblur="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onchange="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" ondblclick="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onfocus="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onkeydown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onkeypress="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onkeyup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onmousedown="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onmousemove="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onmouseout="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onmouseover="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" onmouseup="Put script here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
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
