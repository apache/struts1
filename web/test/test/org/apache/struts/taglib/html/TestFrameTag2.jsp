<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testFrameForwardParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testFrameForwardScrolling1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="Yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="Yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="YES"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="YES">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="no">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="No"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="No">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="NO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="NO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="Auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="Auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="AUTO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="AUTO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardScrolling10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" scrolling="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" scrolling="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" transaction="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">
    </bean:define>
</logic:equal>





<% 
String expected  = (String) pageContext.getAttribute("EXPECTED_RESULTS");
String compareTo = (String) pageContext.getAttribute("TEST_RESULTS");

if ((expected == null) || (expected == null)){
    Assert.fail("An invalid (or mispelled) test on this page was called.  Please verify that you've setup the tests (and spellings) correctly.");
}
	
Assert.assertEquals(compareTo, expected);
%>