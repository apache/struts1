<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>




<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImagePageAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" accesskey="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" accesskey="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageAlign">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" align="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" border="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="TRUE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageDisabled6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" disabled="FALSE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageLocale">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onblur="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onblur="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onchange="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onchange="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" ondblclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" ondblclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onfocus="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onfocus="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onkeydown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeydown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onkeypress="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeypress="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onkeyup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeyup="XXX">
    </bean:define>
</logic:equal>



<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new javax.servlet.jsp.JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
