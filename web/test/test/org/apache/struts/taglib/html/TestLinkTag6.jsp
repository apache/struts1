<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testLinkHrefOnblur">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onblur="onblur">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onblur="onblur">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onclick="onclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onclick="onclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOndblclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" ondblclick="ondblclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" ondblclick="ondblclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnfocus">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onfocus="onfocus">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onfocus="onfocus">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnkeydown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onkeydown="onkeydown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onkeydown="onkeydown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnkeypress">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onkeypress="onkeypress">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onkeypress="onkeypress">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnkeyup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onkeyup="onkeyup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onkeyup="onkeyup">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnmousedown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onmousedown="onmousedown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onmousedown="onmousedown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnmousemove">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onmousemove="onmousemove">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onmousemove="onmousemove">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnmouseout">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onmouseout="onmouseout">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onmouseout="onmouseout">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnmouseover">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onmouseover="onmouseover">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onmouseover="onmouseover">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefOnmouseup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" onmouseup="onmouseup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" onmouseup="onmouseup">Test Link</a>
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testLinkHrefParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link href="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testLinkHrefStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" style="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" style="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" styleClass="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" class="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" styleId="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" id="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefTabIndex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" tabindex="4">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" tabindex="4">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefTarget">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" target="_new">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" target="_new">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" title="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" title="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" titleKey="default.bundle.message">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" title="Testing Message">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link href="/some/page.do" transaction="true">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">Test Link</a>
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