<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using forward------ -->
<logic:equal name="runTest" value="testFrameForward">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameForwardAnchor">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" anchor="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp#XXX")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameForwardFrameborder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" frameborder="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" frameborder="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameForwardFrameName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" frameName="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" name="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameForwardLongdesc">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" longdesc="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" longdesc="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardMarginheight">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" marginheight="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" marginheight="15">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameForwardMarginwidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" marginwidth="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" marginwidth="10">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramMap"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardNamePropertyNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramPropertyMap" property="map"/>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramMap" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNamePropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramPropertyMap" property="map" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramMap" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNamePropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramPropertyMap" property="map" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramMap" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNamePropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" name="paramPropertyMap" property="map" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameForwardNoresize1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNoresize2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNoresize3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNoresize4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNoresize5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameForwardNoresize6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame forward="simpleForward" noresize="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
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