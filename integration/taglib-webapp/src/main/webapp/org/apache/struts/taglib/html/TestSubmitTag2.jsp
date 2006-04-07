<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest" value="testSubmitPropertyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" style="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               style="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" styleClass="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               class="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" styleId="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               id="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" tabindex="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" tabindex="Put something here"
               value="Submit">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" title="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               title="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName"
                     titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               title="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyTitleKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName"
                     titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit"
               title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName" value="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Put something here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyBodyContent">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName">Submit Value Here</html:submit>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Submit Value Here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyBodyContentMessageKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName">
            <bean:message key="default.bundle.message"/>
        </html:submit>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testSubmitPropertyBodyContentMessageKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:submit property="propertyName">
            <bean:message key="default.bundle.message"/>
        </html:submit>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="propertyName" value="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testSubmitPropertyIndexedArray">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyIndexedArrayProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="list">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyIndexedMap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyIndexedMapProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="map">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testSubmitPropertyIndexedEnumeration">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

        <input type="submit" name="propertyName[1]" value="Submit">

    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testSubmitPropertyIndexedEnumerationProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <logic:iterate id="indivItem" name="lst" property="enumeration">
            <html:submit property="propertyName" indexed="true"/>
        </logic:iterate>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">

        <input type="submit" name="propertyName[0]" value="Submit">

        <input type="submit" name="propertyName[1]" value="Submit">

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
