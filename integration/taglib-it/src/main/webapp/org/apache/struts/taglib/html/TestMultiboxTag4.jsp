<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalse">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" accesskey="a">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" accesskey="a"
               value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" alt="Testing alt attribute">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Testing alt attribute">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAltKey1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAltKey2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAltKey1_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="default.bundle.message">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseAltKey2_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" altKey="no.such.key">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testMultiboxBodyPropertyFalseDisabled_True">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="true">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               disabled="disabled">
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testMultiboxBodyPropertyFalseDisabled_False1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="false">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest"
             value="testMultiboxBodyPropertyFalseDisabled_False2">
    <!-- This was changed recently by overwhelming consensus. The prior
    functionality
    was very counter-intuitive. It used to be that putting disabled="false"
    was the same as putting disabled="true". Craig sited the confusion is due
    to the HTML spec.
    -->
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" disabled="anything but true">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onblur="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onblur="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onchange="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onchange="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onclick="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" ondblclick="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               ondblclick="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onfocus="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onfocus="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeydown="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeydown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeypress="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeypress="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onkeyup="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onkeyup="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousedown="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmousedown="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmousemove="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmousemove="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseout="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmouseout="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseover="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmouseover="Put script here">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMultiboxBodyPropertyFalseOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:multibox property="stringArray" onmouseup="Put script here">
            value100
        </html:multibox>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="checkbox" name="stringArray" value="value100"
               onmouseup="Put script here">
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
