<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testLinkActionOnblur">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onblur="onblur">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onblur="onblur">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onclick="onclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onclick="onclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOndblclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" ondblclick="ondblclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" ondblclick="ondblclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnfocus">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onfocus="onfocus">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onfocus="onfocus">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnkeydown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onkeydown="onkeydown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onkeydown="onkeydown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnkeypress">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onkeypress="onkeypress">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onkeypress="onkeypress">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnkeyup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onkeyup="onkeyup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onkeyup="onkeyup">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnmousedown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onmousedown="onmousedown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onmousedown="onmousedown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnmousemove">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onmousemove="onmousemove">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onmousemove="onmousemove">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnmouseout">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onmouseout="onmouseout">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onmouseout="onmouseout">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnmouseover">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onmouseover="onmouseover">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onmouseover="onmouseover">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionOnmouseup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" onmouseup="onmouseup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" onmouseup="onmouseup">Test Link</a>
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testLinkActionParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link action="simpleAction" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testLinkActionStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" style="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" style="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" styleClass="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" class="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" styleId="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" id="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionTabIndex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" tabindex="4">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" tabindex="4">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionTarget">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" target="_new">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" target="_new">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" title="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" title="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" titleKey="default.bundle.message">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" title="Testing Message">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link action="simpleAction" transaction="true">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">Test Link</a>
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