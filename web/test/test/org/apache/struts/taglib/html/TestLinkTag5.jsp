<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using href------ -->
<logic:equal name="runTest" value="testLinkHref">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefAccesskey">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" accesskey="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, null, null, false)%>" accesskey="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefAnchor">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" anchor="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do#XXX", null, null, null, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkHrefIndexedArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=1", null, null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link href="/some/page.do" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=0", null, null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?index=1", null, null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>







<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=1", null, null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefIndexedAlternateIdEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link href="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=0", null, null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do?alternateId=1", null, null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefLinkName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link linkName="linkName">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a name="linkName">Test Link</a>
    </bean:define>
</logic:equal>





<logic:equal name="runTest" value="testLinkHrefNameNoScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramMap">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNamePropertyNoScope">
   <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramPropertyMap" property="map">Test Link</html:link>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNameApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramMap" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNamePropertyApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramPropertyMap" property="map" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNameSessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramMap" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNamePropertySessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramPropertyMap" property="map" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNameRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramMap" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkHrefNamePropertyRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link href="/some/page.do" name="paramPropertyMap" property="map" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, "/some/page.do", null, null, thisMap, null, false)%>">Test Link</a>
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