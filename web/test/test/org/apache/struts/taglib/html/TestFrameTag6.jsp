<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testFrameHrefParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" paramName="paramName"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:frame href="/path/to/non/existing/jsp.jsp" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testFrameHrefScrolling1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="Yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="Yes">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="YES"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="YES">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="no">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="No"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="No">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="NO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="NO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="Auto"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="Auto">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="AUTO"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="AUTO">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefScrolling10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" scrolling="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" scrolling="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" transaction="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">
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