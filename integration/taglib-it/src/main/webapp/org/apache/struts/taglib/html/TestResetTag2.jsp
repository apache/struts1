<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest" value="testResetPropertyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" style="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               style="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" styleClass="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               class="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" styleId="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               id="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" tabindex="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" tabindex="Put something here"
               value="Reset">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" title="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               title="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName"
                    titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               title="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyTitleKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName"
                    titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset"
               title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testResetPropertyValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName" value="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Put something here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyBodyContent">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName">Reset Value Here</html:reset>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Reset Value Here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyBodyContentMessageKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName">
            <bean:message key="default.bundle.message"/>
        </html:reset>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testResetPropertyBodyContentMessageKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:reset property="propertyName">
            <bean:message key="default.bundle.message"/>
        </html:reset>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="reset" name="propertyName" value="Message D'Essai">
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
