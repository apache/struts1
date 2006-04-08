<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest" value="testCancelStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel style="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;"
               style="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel styleClass="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;"
               class="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel styleId="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;" id="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel tabindex="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               tabindex="Put something here" value="Cancel"
               onclick="bCancel=true;">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel title="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;"
               title="Put something here">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitleKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;" title="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelTitleKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel" onclick="bCancel=true;" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testCancelValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel value="Put something here"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Put something here" onclick="bCancel=true;">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContent">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel>Cancel Value Here</html:cancel>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Cancel Value Here" onclick="bCancel=true;">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContentMessageKey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel>
            <bean:message key="default.bundle.message"/>
        </html:cancel>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Testing Message" onclick="bCancel=true;">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCancelBodyContentMessageKey_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:cancel>
            <bean:message key="default.bundle.message"/>
        </html:cancel>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="submit" name="org.apache.struts.taglib.html.CANCEL"
               value="Message D'Essai" onclick="bCancel=true;">
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
