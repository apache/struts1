<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.Globals"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<logic:equal name="runTest" value="testHtml">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html>
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="True">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale3">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="TRUE">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale4">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHtmlLocale5">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="False">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale6">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="FALSE">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale_fr1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html lang="fr">
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale_fr2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="True">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html lang="fr">
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale_fr3">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="TRUE">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html lang="fr">
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale_fr4">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHtmlLocale_fr5">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="False">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocale_fr6">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="FALSE">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="true">
			<bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html xmlns="http://www.w3.org/1999/xhtml">
			true
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="True">
			<bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html xmlns="http://www.w3.org/1999/xhtml">
			true
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml3">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="TRUE">
			<bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html xmlns="http://www.w3.org/1999/xhtml">
			true
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml4">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml5">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="False">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlXhtml6">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html xhtml="FALSE">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocaleXhtml1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true" xhtml="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocaleXhtml2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true" xhtml="true">
			<bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html xmlns="http://www.w3.org/1999/xhtml">
			true
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocaleXhtml3">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="false" xhtml="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHtmlLocaleXhtml_fr1">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true" xhtml="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html lang="fr">
		
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocaleXhtml_fr2">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="true" xhtml="true">
			<bean:write name="<%=Globals.XHTML_KEY%>" scope="page"/>
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" xml:lang="fr">
			true
		</html>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testHtmlLocaleXhtml_fr3">
	<bean:define id="TEST_RESULTS" toScope="page">
		<html:html locale="false" xhtml="false">
		
		</html:html>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		<html>
		
		</html>
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
