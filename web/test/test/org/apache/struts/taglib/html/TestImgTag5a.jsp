<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using src ------ -->
<logic:equal name="runTest" value="testImgSrcNameNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramMapNoScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapNoScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null,"/some/image.gif",  null, null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNamePropertyNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramPropertyMapNoScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapNoScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNameApplicationScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramMapApplicationScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapApplicationScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNamePropertyApplicationScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramPropertyMapApplicationScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapApplicationScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNameSessionScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramMapSessionScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapSessionScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNamePropertySessionScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramPropertyMapSessionScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapSessionScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNameRequestScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramMapRequestScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapRequestScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcNamePropertyRequestScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" name="paramPropertyMapRequestScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapRequestScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, "/some/image.gif", null, null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>


<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new javax.servlet.jsp.JspException
    	("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
