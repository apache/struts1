<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImgPageAlign1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="left"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="left">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="right"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="right">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="top"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="top">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="middle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="middle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="bottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="bottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="texttop"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="texttop">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="absmiddle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="absmiddle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="absbottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="absbottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="any value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="any value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlign10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" align="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" border="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageHeight1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" height="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" height="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageHeight2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" height="155px"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" height="155px">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageHspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" hspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" hspace="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageImageName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" imageName="nameOfImage"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" name="nameOfImage">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageImageIsmap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" ismap="nameOfMap"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" ismap="nameOfMap">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageLocale">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/some/image.gif")%>" alt="Message D'Essai">
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
