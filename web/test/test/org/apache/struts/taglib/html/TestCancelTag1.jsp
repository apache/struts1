<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testCancel">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" value="Cancel">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAccesskey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel accesskey="a"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" accesskey="a" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAlt">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel alt="Testing alt attribute"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" alt="Testing alt attribute" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAltKey1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" alt="Testing Message" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAltKey2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAltKey1_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel altKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" alt="Message D'Essai" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelAltKey2_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel altKey="no.such.key"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelDisabled_True">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel disabled="true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" disabled="disabled" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelDisabled_False1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel disabled="false"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelDisabled_False2">
	<!--  This was changed recently by overwhelming consensus.  The prior functionality
	      was very counter-intuitive.  It used to be that putting disabled="false"
	      was the same as putting disabled="true".  Craig sited the confusion is due
	      to the HTML spec.
	-->
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel disabled="anything but true"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelOnblur">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onblur="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onblur="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testCancelOnchange">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onchange="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onchange="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onclick="Put script here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOndblclick">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel ondblclick="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" ondblclick="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnfocus">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onfocus="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onfocus="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnkeydown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onkeydown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onkeydown="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnkeypress">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onkeypress="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onkeypress="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnkeyup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onkeyup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onkeyup="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnmousedown">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onmousedown="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onmousedown="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnmousemove">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onmousemove="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onmousemove="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnmouseout">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onmouseout="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onmouseout="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnmouseover">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onmouseover="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onmouseover="Put script here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelOnmouseup">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel onmouseup="Put script here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" onmouseup="Put script here" onclick="bCancel=true;">
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