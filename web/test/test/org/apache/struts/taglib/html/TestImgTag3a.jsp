<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImgPageKeyNameNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramMapNoScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapNoScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNamePropertyNoScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramPropertyMapNoScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapNoScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNameApplicationScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramMapApplicationScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapApplicationScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNamePropertyApplicationScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramPropertyMapApplicationScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapApplicationScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNameSessionScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramMapSessionScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapSessionScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNamePropertySessionScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramPropertyMapSessionScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapSessionScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap2, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNameRequestScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramMapRequestScope"/>
   </bean:define>
   <bean:define id="thisMap1" name="paramMapRequestScope" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap1, null, false)%>">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyNamePropertyRequestScope">
   <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" name="paramPropertyMapRequestScope" property="map"/>
   </bean:define>
   <bean:define id="thisMap2" name="paramPropertyMapRequestScope" property="map" type="java.util.Map"/>
   <bean:define id="EXPECTED_RESULTS" toScope="page">
		<img src="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, null, null, "/images/default/default/myimage.gif", null, thisMap2, null, false)%>">
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
