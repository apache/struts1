<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="org.apache.struts.action.ActionError"%>
<%@page import="org.apache.struts.action.ActionErrors"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!-- Default Bundle -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0Errors">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2Errors">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header>
<default_errors_prefix>My Errors Text
<default_errors_suffix><default_errors_prefix>My Errors Text 2
<default_errors_suffix><default_errors_footer>
	</bean:define>
</logic:equal>


<!-- Alternate Bundle -->

<logic:equal name="runTest" value="testErrorsAlternateBundle0Errors">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle2Errors">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header>
<alternate_errors_prefix>My Alternate Errors Text
<alternate_errors_suffix><alternate_errors_prefix>My Alternate Errors Text 2
<alternate_errors_suffix><alternate_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0Errors_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>


<!-- French Locale -->

<logic:equal name="runTest" value="testErrorsDefaultBundle2Errors_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0Errors_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2Errors_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>



<!-- Specified Locale -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocale">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocale">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocale">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocale">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocale_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocale_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocale_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocale_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>








<!-- Name -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsName">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsName">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header>
<default_errors_prefix>My Errors Text
<default_errors_suffix><default_errors_prefix>My Errors Text 2
<default_errors_suffix><default_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsName">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsName">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header>
<alternate_errors_prefix>My Alternate Errors Text
<alternate_errors_suffix><alternate_errors_prefix>My Alternate Errors Text 2
<alternate_errors_suffix><alternate_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsName_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsName_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsName_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsName_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>











<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleName">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleName">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleName">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleName">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleName_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleName_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleName_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleName_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag"));
    errors.add(ActionErrors.GLOBAL_ERROR,
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" name="MY_ERRORS_KEY"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>



<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") != null){
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>