<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImageSrcKeyAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" accesskey="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" accesskey="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyAlign">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" align="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" alt="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/alternate/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/default/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/alternate/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" border="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="TRUE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyDisabled6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" disabled="FALSE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyLocaleDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyLocaleAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" locale="secret locale" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/alternate/myimage.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyLocaleDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/default/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyLocaleAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" locale="secret locale" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/alternate/myimage.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onblur="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onblur="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onchange="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onchange="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" ondblclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" ondblclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onfocus="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onfocus="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onkeydown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onkeydown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onkeypress="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onkeypress="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onkeyup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onkeyup="XXX">
    </bean:define>
</logic:equal>

<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new javax.servlet.jsp.JspException
    	("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
