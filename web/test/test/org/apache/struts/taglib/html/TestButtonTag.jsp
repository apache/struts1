<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testButtonProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" accesskey="a" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" disabled="disabled">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testButtonPropertyOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" onmouseup="Put script here">
	</bean:define>
</logic:equal>



<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>