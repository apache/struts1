<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImageSrcAccesskey">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" accesskey="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" accesskey="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcAlign">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" align="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" border="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="true"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="True"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="TRUE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" disabled="disabled">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="false"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="False"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcDisabled6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" disabled="FALSE"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcLocale">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnblur">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onblur="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onblur="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnchange">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onchange="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onchange="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" ondblclick="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" ondblclick="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnfocus">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onfocus="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onfocus="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onkeydown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeydown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onkeypress="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeypress="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onkeyup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onkeyup="XXX">
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
