<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testLinkForwardOnblur">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onblur="onblur">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onblur="onblur">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onclick="onclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onclick="onclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOndblclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" ondblclick="ondblclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" ondblclick="ondblclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnfocus">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onfocus="onfocus">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onfocus="onfocus">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnkeydown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onkeydown="onkeydown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onkeydown="onkeydown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnkeypress">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onkeypress="onkeypress">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onkeypress="onkeypress">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnkeyup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onkeyup="onkeyup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onkeyup="onkeyup">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnmousedown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onmousedown="onmousedown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onmousedown="onmousedown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnmousemove">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onmousemove="onmousemove">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onmousemove="onmousemove">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnmouseout">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onmouseout="onmouseout">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onmouseout="onmouseout">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnmouseover">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onmouseover="onmouseover">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onmouseover="onmouseover">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardOnmouseup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" onmouseup="onmouseup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" onmouseup="onmouseup">Test Link</a>
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testLinkForwardParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link forward="simpleForward" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testLinkForwardStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" style="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" style="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" styleClass="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" class="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" styleId="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" id="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardTabIndex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" tabindex="4">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" tabindex="4">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardTarget">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" target="_new">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" target="_new">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" title="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" title="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" titleKey="default.bundle.message">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" title="Testing Message">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link forward="simpleForward" transaction="true">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">Test Link</a>
    </bean:define>
</logic:equal>





<% 
String expected  = (String) pageContext.getAttribute("EXPECTED_RESULTS");
String compareTo = (String) pageContext.getAttribute("TEST_RESULTS");

if ((expected == null) || (compareTo == null)){
    Assert.fail("An invalid (or mispelled) test on this page was called.  Please verify that you've setup the tests (and spellings) correctly.");
}
	
Assert.assertEquals(compareTo, expected);
%>