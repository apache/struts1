<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testLinkPageOnblur">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onblur="onblur">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onblur="onblur">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onclick="onclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onclick="onclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOndblclick">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" ondblclick="ondblclick">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" ondblclick="ondblclick">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnfocus">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onfocus="onfocus">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onfocus="onfocus">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnkeydown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onkeydown="onkeydown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onkeydown="onkeydown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnkeypress">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onkeypress="onkeypress">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onkeypress="onkeypress">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnkeyup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onkeyup="onkeyup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onkeyup="onkeyup">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnmousedown">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onmousedown="onmousedown">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onmousedown="onmousedown">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnmousemove">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onmousemove="onmousemove">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onmousemove="onmousemove">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnmouseout">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onmouseout="onmouseout">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onmouseout="onmouseout">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnmouseover">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onmouseover="onmouseover">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onmouseover="onmouseover">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageOnmouseup">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" onmouseup="onmouseup">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" onmouseup="onmouseup">Test Link</a>
    </bean:define>
</logic:equal>








<logic:equal name="runTest" value="testLinkPageParamIdParamNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageParamIdParamNameParamPropertyNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageParamIdParamNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageParamIdParamNameParamPropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageParamIdParamNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageParamIdParamNameParamPropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageParamIdParamNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" paramName="paramName">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramValue">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageParamIdParamNameParamPropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
		<html:link page="/some/page.do" paramId="myParam" 
			paramName="testingParamProperty" paramProperty="string">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?myParam=paramPropertyValue">Test Link</a>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testLinkPageStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" style="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" style="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" styleClass="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" class="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" styleId="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" id="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageTabIndex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" tabindex="4">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" tabindex="4">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageTarget">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" target="_new">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" target="_new">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" title="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" title="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" titleKey="default.bundle.message">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" title="Testing Message">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageTransaction">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:link page="/some/page.do" transaction="true">Test Link</html:link>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>?org.apache.struts.taglib.html.TOKEN=Some_Token_Here">Test Link</a>
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