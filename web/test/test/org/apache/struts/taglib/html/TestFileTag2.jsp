<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testFilePropertyStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" tabindex="Put something here" value="Test Value">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Put something here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string">File Value Here</html:file>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string">
			<bean:message key="default.bundle.message"/>
		</html:file>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:file property="string">
			<bean:message key="default.bundle.message"/>
		</html:file>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="file" name="string" value="Test Value">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testFilePropertyIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:file property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:file property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:file property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:file property="string" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:file property="string" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[1].string" value="Test Value">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testFilePropertyIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:file property="string" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[0].string" value="Test Value">
	
		<input type="file" name="org.apache.struts.taglib.html.BEAN[1].string" value="Test Value">
	
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
