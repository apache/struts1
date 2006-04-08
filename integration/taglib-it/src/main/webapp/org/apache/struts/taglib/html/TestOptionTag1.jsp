<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testOptionBodySelected">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="SelectMe">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">My
            value</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionBodyNotSelected">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="Some other value">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">My
            value</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionBodySelectedDisabled_true">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="true">
            <html:option value="SelectMe">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" disabled="disabled"><option value="SelectMe"
                                                          selected="selected">
            My value</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionBodyNotSelectedDisabled_true">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="true">
            <html:option value="Some other value">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string" disabled="disabled"><option
                value="Some other value">My value</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionBodySelectedDisabled_false">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="false">
            <html:option value="SelectMe">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">My
            value</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionBodyNotSelectedDisabled_false">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="false">
            <html:option value="Some other value">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">My
            value</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionBodySelectedDisabled_other">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="some value">
            <html:option value="SelectMe">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">My
            value</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionBodyNotSelectedDisabled_other">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string" disabled="some value">
            <html:option value="Some other value">
                My value
            </html:option>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">My
            value</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionKeySelected">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="SelectMe" key="default.bundle.message"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">
            Testing Message</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionKeyNotSelected">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="Some other value"
                         key="default.bundle.message"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">Testing
            Message</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionKeySelectedAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="SelectMe" key="alternate.bundle.message"
                         bundle="alternate"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">
            Testing Message</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionKeyNotSelectedAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="Some other value"
                         key="alternate.bundle.message" bundle="alternate"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">Testing
            Message</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionKeySelectedLocale_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="SelectMe" key="default.bundle.message"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">
            Message D'Essai</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testOptionKeyNotSelectedLocale_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="Some other value"
                         key="default.bundle.message"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">Message
            D'Essai</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testOptionKeySelectedAlternateBundleLocale_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="SelectMe" key="alternate.bundle.message"
                         bundle="alternate"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="SelectMe" selected="selected">
            Message D'Essai</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionKeyNotSelectedAlternateBundleLocale_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:option value="Some other value"
                         key="alternate.bundle.message" bundle="alternate"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="Some other value">Message
            D'Essai</option></select>
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
