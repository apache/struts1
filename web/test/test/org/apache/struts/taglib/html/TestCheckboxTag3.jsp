<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalse">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" accesskey="a" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" disabled="disabled">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" onmouseup="Put script here">
	</bean:define>
</logic:equal>



<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>