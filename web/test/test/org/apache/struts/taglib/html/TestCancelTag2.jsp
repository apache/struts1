<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<logic:equal name="runTest" value="testCancelStyle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel style="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" style="Put something here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelStyleClass">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel styleClass="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" class="Put something here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelStyleId">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel styleId="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" id="Put something here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTabindex">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel tabindex="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" tabindex="Put something here" value="Cancel" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitle">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel title="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" title="Put something here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitleKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" title="Testing Message" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitleKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel titleKey="default.bundle.message"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel" title="Message D'Essai" onclick="bCancel=true;">
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelValue">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel value ="Put something here"/>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Put something here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContent">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel>Cancel Value Here</html:cancel>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Cancel Value Here" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContentMessageKey">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel>
			<bean:message key="default.bundle.message"/>
		</html:cancel>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Testing Message" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContentMessageKey_fr">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:cancel>
			<bean:message key="default.bundle.message"/>
		</html:cancel>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="Message D'Essai" onclick="bCancel=true;">
	</bean:define>
</logic:equal>


<% 
Assert.assertEquals(
	pageContext.getAttribute("TEST_RESULTS").toString(), 
	pageContext.getAttribute("EXPECTED_RESULTS").toString()
	);
%>