<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testHiddenPropertyStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Test Value" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:hidden property="string" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="hidden" name="string" value="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:hidden property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:hidden property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:hidden property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:hidden property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:hidden property="string" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[1].string" value="Test Value">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHiddenPropertyIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:hidden property="string" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
	
		<input type="hidden" name="org.apache.struts.taglib.html.BEAN[1].string" value="Test Value">
	
	</bean:define>
</logic:equal>



<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new javax.servlet.jsp.JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
