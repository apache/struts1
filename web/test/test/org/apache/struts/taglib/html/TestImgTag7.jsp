<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using srcKey ------ -->
<logic:equal name="runTest" value="testImgSrcKeyAlign1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="left"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="left">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="right"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="right">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign3">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="top"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="top">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign4">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="middle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="middle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign5">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="bottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="bottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign6">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="texttop"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="texttop">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign7">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="absmiddle"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="absmiddle">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign8">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="absbottom"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="absbottom">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign9">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="any value"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="any value">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlign10">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" align="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" align="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAlt">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" alt="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" alt="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAltKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAltKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/alternate/myimage.gif" alt="Testing Message">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAltKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/fr/default/myimage.gif" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyAltKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="alternate.testing.image" altKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/fr/alternate/myimage.gif" alt="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyBorder">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" border="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" border="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyHeight1">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" height="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" height="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyHeight2">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" height="155px"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" height="155px">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyHspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" hspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" hspace="15">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyImageName">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" imageName="nameOfImage"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" name="nameOfImage">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyImageIsmap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" ismap="nameOfMap"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" ismap="nameOfMap">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyLocale">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" locale="secret locale" altKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/fr/default/myimage.gif" alt="Message D'Essai">
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
