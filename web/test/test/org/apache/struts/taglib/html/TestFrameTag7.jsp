<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testFramePage">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFramePageAnchor">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" anchor="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp#XXX")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFramePageFrameborder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" frameborder="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" frameborder="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFramePageFrameName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" frameName="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" name="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFramePageLongdesc">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" longdesc="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" longdesc="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFramePageMarginheight">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" marginheight="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" marginheight="15">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFramePageMarginwidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" marginwidth="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" marginwidth="10">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFramePageNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramMap"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFramePageNamePropertyNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map"/>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramMap" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNamePropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramMap" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNamePropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramMap" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNamePropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/path/to/non/existing/jsp.jsp", null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFramePageNoresize1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNoresize2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNoresize3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNoresize4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNoresize5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFramePageNoresize6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame page="/path/to/non/existing/jsp.jsp" noresize="no"/>
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