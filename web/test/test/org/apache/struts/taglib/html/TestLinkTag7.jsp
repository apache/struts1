<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.util.RequestUtils"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using href------ -->
<logic:equal name="runTest" value="testLinkPage">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageAccesskey">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" accesskey="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, null, null, false)%>" accesskey="XXX">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageAnchor">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" anchor="XXX">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do#XXX", null, null, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testLinkPageIndexedArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=1", null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link page="/some/page.do" indexed="true">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=0", null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?index=1", null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>







<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdArray">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdArrayProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdMap">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdMapProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	  </logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdEnumeration">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=1", null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageIndexedAlternateIdEnumerationProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:link page="/some/page.do" indexed="true" indexId="alternateId">Test Link</html:link>
	</logic:iterate>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=0", null, null, null, false)%>">Test Link</a>
	
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do?alternateId=1", null, null, null, false)%>">Test Link</a>
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageLinkName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link linkName="linkName">Test Link</html:link>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        <a name="linkName">Test Link</a>
    </bean:define>
</logic:equal>





<logic:equal name="runTest" value="testLinkPageNameNoScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramMap">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNamePropertyNoScope">
   <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramPropertyMap" property="map">Test Link</html:link>
   </bean:define>
   <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
   <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNameApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramMap" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNamePropertyApplicationScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramPropertyMap" property="map" scope="application">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNameSessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramMap" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNamePropertySessionScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramPropertyMap" property="map" scope="session">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNameRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramMap" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramMap" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testLinkPageNamePropertyRequestScope">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <html:link page="/some/page.do" name="paramPropertyMap" property="map" scope="request">Test Link</html:link>
    </bean:define>
    <bean:define id="thisMap" name="paramPropertyMap" property="map" type="java.util.Map"/>
    <bean:define id="TEST_RESULTS" toScope="page">
		<a href="<%=RequestUtils.computeURL(pageContext, null, null, "/some/page.do", null, thisMap, null, false)%>">Test Link</a>
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