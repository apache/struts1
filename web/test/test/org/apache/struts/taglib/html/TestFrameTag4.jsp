<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testFrameActionParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameActionParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/test/simpleAction.do")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testFrameActionScrolling1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="Yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="Yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="YES"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="YES">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="no">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="No"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="No">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="NO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="NO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="Auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="Auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="AUTO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="AUTO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionScrolling10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" scrolling="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" scrolling="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameActionTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame action="simpleAction" transaction="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/simpleAction.do")%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">
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