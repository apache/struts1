<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="org.apache.struts.action.ActionError"%>
<%@page import="org.apache.struts.action.ActionErrors"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<!-- Property -->
<!-- Default Bundle -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header>
<default_errors_prefix>My Errors Text
<default_errors_suffix><default_errors_prefix>My Errors Text 2
<default_errors_suffix><default_errors_footer>
	</bean:define>
</logic:equal>


<!-- Alternate Bundle -->

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header>
<alternate_errors_prefix>My Alternate Errors Text
<alternate_errors_suffix><alternate_errors_prefix>My Alternate Errors Text 2
<alternate_errors_suffix><alternate_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>


<!-- French Locale -->

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>



<!-- Specified Locale -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute(Globals.ERROR_KEY, errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>








<!-- Name -->

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsNameProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsNameProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header>
<default_errors_prefix>My Errors Text
<default_errors_suffix><default_errors_prefix>My Errors Text 2
<default_errors_suffix><default_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsNameProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsNameProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header>
<alternate_errors_prefix>My Alternate Errors Text
<alternate_errors_suffix><alternate_errors_prefix>My Alternate Errors Text 2
<alternate_errors_suffix><alternate_errors_footer>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsNameProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsNameProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsNameProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsNameProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors bundle="alternate" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>











<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleNameProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleNameProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleNameProperty">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleNameProperty">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<alternate_errors_header_fr>
<alternate_errors_prefix_fr>My Alternate Errors Text (fr)
<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My Alternate Errors Text 2 (fr)
<alternate_errors_suffix_fr><alternate_errors_footer_fr>
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleNameProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleNameProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("default.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:<default_errors_header_fr>
<default_errors_prefix_fr>My Errors Text (fr)
<default_errors_suffix_fr><default_errors_prefix_fr>My Errors Text 2 (fr)
<default_errors_suffix_fr><default_errors_footer_fr>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsAlternateBundle0ErrorsLocaleNameProperty_fr">
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" name="MY_ERRORS_KEY" property="someProperty"/>
	</bean:define>
	<bean:define id="TEST_RESULTS" toScope="page">
		My Errors go here:
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsAlternateBundle2ErrorsLocaleNameProperty_fr">
<%
//This should be done in your action.  I do it here to keep the tests simple.
	ActionErrors errors = new ActionErrors();
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag"));
    errors.add("someProperty",
         new ActionError("alternate.testing.errors.tag2"));
    request.setAttribute("MY_ERRORS_KEY", errors);
%>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
		My Errors go here:<html:errors locale="MY_LOCALE_KEY" bundle="alternate" name="MY_ERRORS_KEY" property="someProperty"/>
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