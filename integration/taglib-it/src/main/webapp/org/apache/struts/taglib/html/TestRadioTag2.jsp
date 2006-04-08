<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="org.apache.struts.action.ActionMessage" %>
<%@ page import="org.apache.struts.action.ActionMessages" %>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest" value="testRadioPropertyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" style="Put something here"
                    errorStyle="some error style"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               style="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyErrorStyle">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute("ALT_ERROR_KEY", errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" style="Put something here"
                    errorStyle="some error style" errorKey="ALT_ERROR_KEY"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               style="some error style">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" styleClass="Put something here"
                    errorStyleClass="some error style class"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               class="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyErrorStyleClass">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" styleClass="Put something here"
                    errorStyleClass="some error style class"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               class="some error style class">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" styleId="Put something here"
                    errorStyleId="some error style id"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               id="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyErrorStyleId">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" styleId="Put something here"
                    errorStyleId="some error style id"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               id="some error style id">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" title="Put something here"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               title="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" titleKey="default.bundle.message"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               title="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyTitleKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" titleKey="default.bundle.message"
                    value="Put Some Value Here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Put Some Value Here"
               title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyValueMatch">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" value="Test Value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Test Value"
               checked="checked">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyValueNotMatch">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:radio property="string" value="Not Test Value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="radio" name="string" value="Not Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyIndexedArray">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyIndexedArrayProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="list">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyIndexedMap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyIndexedMapProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="map">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testRadioPropertyIndexedEnumeration">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[1].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testRadioPropertyIndexedEnumerationProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="enumeration">
            <html:radio property="string" indexed="true" value="Test Value"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value" checked="checked">

        <input type="radio"
               name="org.apache.struts.taglib.html.BEAN[1].string"
               value="Test Value" checked="checked">

    </bean:define>
</logic:equal>


<%
    String expected = "";
    String compareTo = "";

    if (pageContext.getAttribute("EXPECTED_RESULTS") == null) {
        throw new javax.servlet.jsp.JspException(
                "No tests on this page were called.  Please verify that you've setup the tests correctly.");
    } else {
        expected = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("TEST_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
