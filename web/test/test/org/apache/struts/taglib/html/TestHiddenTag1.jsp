<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testHiddenProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" accesskey="a" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testHiddenPropertyOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHiddenPropertyOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" onmouseup="Put script here">
	</bean:define>
</logic:equal>


<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>