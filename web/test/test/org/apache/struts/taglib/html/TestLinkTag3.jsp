<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!-- --------Testing attributes using forward------ -->
<logic:equal name="runTest" value="testLinkAction">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionAccesskey">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" accesskey="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", null, null, false)%>" accesskey="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionAnchor">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" anchor="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=response.encodeURL("/test/simpleAction.do#XXX")%>">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkActionIndexedArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link action="simpleAction" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?index=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>







<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionIndexedAlternateIdEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link action="simpleAction" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=0")%>">Test Link</a>
	
		<a href="<%=response.encodeURL("/test/simpleAction.do?alternateId=1")%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionLinkName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link linkName="linkName">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a name="linkName">Test Link</a>
    </bean:define>
</logic:equal>





<logic:equal name="runTest" value="testLinkActionNameNoScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramMap">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNamePropertyNoScope">
   <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramPropertyMap" property="map">Test Link</html:link>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNameApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramMap" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNamePropertyApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramPropertyMap" property="map" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNameSessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramMap" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNamePropertySessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramPropertyMap" property="map" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNameRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramMap" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkActionNamePropertyRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link action="simpleAction" name="paramPropertyMap" property="map" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, null, "simpleAction", thisMap, null, false)%>">Test Link</a>
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