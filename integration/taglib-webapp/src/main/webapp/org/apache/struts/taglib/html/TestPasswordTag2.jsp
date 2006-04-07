<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="org.apache.struts.action.ActionMessage" %>
<%@ page import="org.apache.struts.action.ActionMessages" %>

<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest" value="testPasswordPropertyReadonly">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" readonly="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               readonly="readonly">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertySize">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" size="10"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" size="10" value="Test Value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" style="Put something here"
                       errorStyle="some error style"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               style="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyErrorStyle">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute("ALT_ERROR_KEY", errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" style="Put something here"
                       errorStyle="some error style"
                       errorKey="ALT_ERROR_KEY"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               style="some error style">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" styleClass="Put something here"
                       errorStyleClass="some error style class"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               class="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyErrorStyleClass">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" styleClass="Put something here"
                       errorStyleClass="some error style class"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               class="some error style class">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" styleId="Put something here"
                       errorStyleId="some error style id"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               id="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyErrorStyleId">
    <%
        ActionMessages errors = new ActionMessages();
        errors.add("string",
                new ActionMessage("default.testing.errors.tag"));
        request.setAttribute(Globals.ERROR_KEY, errors);
    %>
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" styleId="Put something here"
                       errorStyleId="some error style id"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               id="some error style id">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" title="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               title="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               title="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyTitleKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Test Value"
               title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:password property="string" value="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="password" name="string" value="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyIndexedArray">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyIndexedArrayProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="list">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyIndexedMap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyIndexedMapProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="map">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testPasswordPropertyIndexedEnumeration">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[1].string"
               value="Test Value">

    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testPasswordPropertyIndexedEnumerationProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="enumeration">
            <html:password property="string" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[0].string"
               value="Test Value">

        <input type="password"
               name="org.apache.struts.taglib.html.BEAN[1].string"
               value="Test Value">

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
