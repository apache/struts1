<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" accesskey="a" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" disabled="disabled">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" onmouseup="Put script here">
	</bean:define>
</logic:equal>


<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new javax.servlet.jsp.JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
