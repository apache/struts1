<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testButtonPropertyStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" style="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" class="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" id="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" tabindex="Put something here" value="Click">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" title="Put something here">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" title="Testing Message">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Click" title="Message D'Essai">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testButtonPropertyValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName" value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Put something here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName">Button Value Here</html:button>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Button Value Here">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName">
			<bean:message key="default.bundle.message"/>
		</html:button>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Testing Message">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testButtonPropertyBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:button property="propertyName">
			<bean:message key="default.bundle.message"/>
		</html:button>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="button" name="propertyName" value="Message D'Essai">
	</bean:define>
</logic:equal>




<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>