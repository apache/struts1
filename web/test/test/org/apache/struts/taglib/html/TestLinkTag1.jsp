<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using forward------ -->
<logic:equal name="runTest" value="testLinkForward">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardAccesskey">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" accesskey="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp")%>" accesskey="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardAnchor">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" anchor="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp#XXX")%>">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkForwardIndexedArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link forward="simpleForward" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?index=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>







<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardIndexedAlternateIdEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link forward="simpleForward" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/path/to/non/existing/jsp.jsp?alternateId=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardLinkName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link linkName="linkName">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a name="linkName">Test Link</a>
    </bean:define>
</logic:equal>





<logic:equal name="runTest" value="testLinkForwardNameNoScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramMap">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNamePropertyNoScope">
   <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramPropertyMap" property="map">Test Link</html:link>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNameApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramMap" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNamePropertyApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramPropertyMap" property="map" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNameSessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramMap" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNamePropertySessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramPropertyMap" property="map" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNameRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramMap" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkForwardNamePropertyRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link forward="simpleForward" name="paramPropertyMap" property="map" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=org.apache.struts.util.RequestUtils.computeURL(pageContext, "simpleForward", null, null, null, thisMap, null, false)%>">Test Link</a>
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