<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" tabindex="Put something here" value="on">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="Put something here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper">Checkbox Value Here</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">Checkbox Value Here
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">Testing Message
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:checkbox property="checkedWrapper">
			<bean:message key="default.bundle.message"/>
		</html:checkbox>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="checkbox" name="checkedWrapper" value="on">Message D'Essai
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checkedWrapper" value="on">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCheckboxPropertyBooleanWrapperTrueIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:checkbox property="checkedWrapper" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[0].checkedWrapper" value="on">
	
		<input type="checkbox" name="org.apache.struts.taglib.html.BEAN[1].checkedWrapper" value="on">
	
	</bean:define>
</logic:equal>




<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>