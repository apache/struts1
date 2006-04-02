<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ page import="org.apache.struts.action.ActionMessage" %>
<%@ page import="org.apache.struts.action.ActionMessages" %>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


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
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:<default_errors_header><default_errors_prefix>My
        Errors Text<default_errors_suffix><default_errors_prefix>My Errors
        Text 2<default_errors_suffix><default_errors_footer>
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
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors bundle="alternate"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:<alternate_errors_header><alternate_errors_prefix>My
        Alternate Errors
        Text<alternate_errors_suffix><alternate_errors_prefix>My Alternate
        Errors Text 2<alternate_errors_suffix><alternate_errors_footer>
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
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<default_errors_header_fr><default_errors_prefix_fr>My Errors
        Text (fr)<default_errors_suffix_fr><default_errors_prefix_fr>My Errors
        Text 2 (fr)<default_errors_suffix_fr><default_errors_footer_fr>
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
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors bundle="alternate"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<alternate_errors_header_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text
        (fr)<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text 2
        (fr)<alternate_errors_suffix_fr><alternate_errors_footer_fr>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testErrorsDefaultBundle0ErrorsLocaleName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testErrorsDefaultBundle2ErrorsLocaleName">
    <%
        //This should be done in your action.  I do it here to keep the tests simple.
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<default_errors_header_fr><default_errors_prefix_fr>My Errors
        Text (fr)<default_errors_suffix_fr><default_errors_prefix_fr>My Errors
        Text 2 (fr)<default_errors_suffix_fr><default_errors_footer_fr>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testErrorsAlternateBundle0ErrorsLocaleName">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testErrorsAlternateBundle2ErrorsLocaleName">
    <%
        //This should be done in your action.  I do it here to keep the tests simple.
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       bundle="alternate"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<alternate_errors_header_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text
        (fr)<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text 2
        (fr)<alternate_errors_suffix_fr><alternate_errors_footer_fr>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testErrorsDefaultBundle0ErrorsLocaleName_fr">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testErrorsDefaultBundle2ErrorsLocaleName_fr">
    <%
        //This should be done in your action.  I do it here to keep the tests simple.
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("default.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<default_errors_header_fr><default_errors_prefix_fr>My Errors
        Text (fr)<default_errors_suffix_fr><default_errors_prefix_fr>My Errors
        Text 2 (fr)<default_errors_suffix_fr><default_errors_footer_fr>
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testErrorsAlternateBundle0ErrorsLocaleName_fr">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go here:
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testErrorsAlternateBundle2ErrorsLocaleName_fr">
    <%
        //This should be done in your action.  I do it here to keep the tests simple.
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag"));
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("alternate.testing.errors.tag2"));
        request.setAttribute("MY_ERRORS_KEY", errors);
    %>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        My Errors go here:<html:errors locale="MY_LOCALE_KEY"
                                       bundle="alternate"
                                       name="MY_ERRORS_KEY"/>
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
        My Errors go
        here:<alternate_errors_header_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text
        (fr)<alternate_errors_suffix_fr><alternate_errors_prefix_fr>My
        Alternate Errors Text 2
        (fr)<alternate_errors_suffix_fr><alternate_errors_footer_fr>
    </bean:define>
</logic:equal>


<%
    String expected = "";
    String compareTo = "";

    if (pageContext.getAttribute("EXPECTED_RESULTS") == null) {
        throw new javax.servlet.jsp.JspException(
                "No tests on this page were called.  Please verify that you've setup the tests correctly.");
    } else {
        expected = pageContext.getAttribute("TEST_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
