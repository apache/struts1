<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalse">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" accesskey="a" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" disabled="disabled">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperFalseOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" onmouseup="Put script here">
	</bean:define>
</logic:equal>



<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>