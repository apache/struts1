<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" tabindex="Put something here" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="Put something here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">Checkbox Value Here</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">Checkbox Value Here
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">Testing Message
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on">Message D'Essai
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checked" value="on">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanFalseIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:checkbox property="checked" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checked" value="on">
	
	</bean:define>
</logic:equal>



<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>