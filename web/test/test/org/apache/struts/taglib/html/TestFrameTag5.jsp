<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using href------ -->
<logic:equal name="runTest" value="testFrameHref">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameHrefAnchor">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" anchor="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp#XXX")%>">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameHrefFrameborder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" frameborder="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" frameborder="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameHrefFrameName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" frameName="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" name="XXX">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameHrefLongdesc">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" longdesc="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" longdesc="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefMarginheight">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" marginheight="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" marginheight="15">
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testFrameHrefMarginwidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" marginwidth="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" marginwidth="10">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefNameNoScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramMap"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefNamePropertyNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map"/>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNameApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramMap" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNamePropertyApplicationScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="application"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNameSessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramMap" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNamePropertySessionScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="session"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNameRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramMap" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNamePropertyRequestScope">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" name="paramPropertyMap" property="map" scope="request"/>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
		<frame src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/path/to/non/existing/jsp.jsp", null, null, thisMap, null, false)%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFrameHrefNoresize1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNoresize2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>" noresize="noresize">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNoresize3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNoresize4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNoresize5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="yes"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFrameHrefNoresize6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:frame href="/path/to/non/existing/jsp.jsp" noresize="no"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <frame src="<%=response.encodeURL("/path/to/non/existing/jsp.jsp")%>">
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