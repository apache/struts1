<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using src ------ -->
<logic:equal name="runTest" value="testImgSrcAlign1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="left"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="left">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="right"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="right">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="top"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="top">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="middle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="middle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="bottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="bottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="texttop"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="texttop">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="absmiddle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="absmiddle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="absbottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="absbottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="any value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="any value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlign10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" align="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" border="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcHeight1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" height="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" height="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcHeight2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" height="155px"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" height="155px">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcHspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" hspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" hspace="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcImageName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" imageName="nameOfImage"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" name="nameOfImage">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcImageIsmap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" ismap="nameOfMap"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" ismap="nameOfMap">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcLocale">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" alt="Message D'Essai">
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
