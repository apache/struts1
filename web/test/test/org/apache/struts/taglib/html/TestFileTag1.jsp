<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testFileProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>
 
<logic:equal name="runTest" value="testFilePropertyAccept">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" accept="image/jpeg, image/gif"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" accept="image/jpeg, image/gif" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" accesskey="a" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" alt="Testing alt attribute">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" alt="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" alt="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" disabled="disabled">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onblur="Put script here">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFilePropertyOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onchange="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" ondblclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onfocus="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onkeydown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onkeypress="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onkeyup="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onmousedown="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onmousemove="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onmouseout="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onmouseover="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" onmouseup="Put script here">
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