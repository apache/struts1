<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>


<logic:equal name="runTest"
             value="testOptionsCollectionArrayItemValueInCollectionProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options collection="arrayOfLVB" property="label"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="key0">key0</option>
            <option value="key1" selected="selected">key1</option>
            <option value="key2">key2</option>
            <option value="key3">key3</option>
            <option value="key4">key4</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionsCollectionArrayItemValueNotInCollectionProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options collection="arrayOfLVB" property="label"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="key0">key0</option>
            <option value="key1">key1</option>
            <option value="key2">key2</option>
            <option value="key3">key3</option>
            <option value="key4">key4</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testOptionsCollectionArrayItemValueInCollectionPropertyLabelProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options collection="arrayOfLVB" property="label"
                          labelProperty="value"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="key0">Test Message 0</option>
            <option value="key1" selected="selected">Test Message 1</option>
            <option value="key2">Test Message 2</option>
            <option value="key3">Test Message 3</option>
            <option value="key4">Test Message 4</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionsCollectionArrayItemValueNotInCollectionPropertyLabelProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options collection="arrayOfLVB" property="label"
                          labelProperty="value"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="key0">Test Message 0</option>
            <option value="key1">Test Message 1</option>
            <option value="key2">Test Message 2</option>
            <option value="key3">Test Message 3</option>
            <option value="key4">Test Message 4</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testOptionsNameArrayItemValueInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options name="stringValues"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1" selected="selected">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionsNameArrayItemValueNotInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options name="stringValues"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testOptionsPropertyArrayItemValueInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options property="stringArray"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1" selected="selected">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionsPropertyArrayItemValueNotInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options property="stringArray"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>


<logic:equal name="runTest"
             value="testOptionsNamePropertyArrayItemValueInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options name="stringValues" property="stringArray"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1" selected="selected">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testOptionsNamePropertyArrayItemValueNotInCollection">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:select property="string">
            <html:options name="stringValues" property="stringArray"/>
        </html:select>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <select name="string"><option value="val0">val0</option>
            <option value="val1">val1</option>
            <option value="val2">val2</option>
            <option value="val3">val3</option>
            <option value="val4">val4</option></select>
    </bean:define>
</logic:equal>


* Neither labelName nor labelProperty is specified - The labels will be the
same as the option
values themselves.

* Only labelName is specified - The value of this attribute is the name of a
JSP bean in some
scope that is the collection.

* Only labelProperty is specified - The value of this attribute is the name of
a property of the
ActionForm bean associated with our form, which will return the collection.

* Both labelName and labelProperty are specified - The value of the labelName
attribute identifies
a JSP bean in some scope. The value of the labelProperty attribute is the name
of some property
of that bean which will return the collection.


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

// Swallow tabs, carriage returns, and newlines before comparing
    if (expected == null) {
        expected = "";
    }
    if (compareTo == null) {
        compareTo = "";
    }

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < expected.length(); i++) {
        char ch = expected.charAt(i);
        if ((ch == '\t') || (ch == '\r') || (ch == '\n')) {
            ;
        } else {
            sb.append(ch);
        }
    }
    expected = sb.toString();

    sb = new StringBuffer();
    for (int i = 0; i < compareTo.length(); i++) {
        char ch = compareTo.charAt(i);
        if ((ch == '\t') || (ch == '\r') || (ch == '\n')) {
            ;
        } else {
            sb.append(ch);
        }
    }
    compareTo = sb.toString();

    Assert.assertEquals(expected, compareTo);
%>
