<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImagePageKeyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" accesskey="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" accesskey="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyAlign">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" align="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" alt="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/alternate/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/default/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/alternate/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" border="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="true">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="True">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="TRUE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="TRUE">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="false">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="False">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyDisabled6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" disabled="FALSE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" disabled="FALSE">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyLocaleDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyLocaleAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" locale="secret locale" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/alternate/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyLocaleDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/default/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyLocaleAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" locale="secret locale" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/alternate/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onblur="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onblur="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onchange="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onchange="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" ondblclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" ondblclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onfocus="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onfocus="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onkeydown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeydown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onkeypress="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeypress="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onkeyup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeyup="XXX">
    </bean:define>
</logic:equal>


<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>