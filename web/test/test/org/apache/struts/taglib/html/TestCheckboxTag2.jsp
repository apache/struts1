<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" tabindex="Put something here" value="on" checked="checked">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="Put something here" checked="checked">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">Checkbox Value Here</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">Checkbox Value Here
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">Testing Message
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checked">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checked" value="on" checked="checked">Message D'Essai
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:checkbox property="checked" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checked" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checked" value="on" checked="checked">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertybooleanTrueIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:checkbox property="checked" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checked" value="on" checked="checked">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checked" value="on" checked="checked">
	
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