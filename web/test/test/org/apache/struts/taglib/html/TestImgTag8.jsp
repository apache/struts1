<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using srcKey ------ -->
<logic:equal name="runTest" value="testImgSrcKeyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onclick="onclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onclick="onclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" ondblclick="ondblclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" ondblclick="ondblclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onkeydown="onkeydown"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onkeydown="onkeydown">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onkeypress="onkeypress"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onkeypress="onkeypress">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onkeyup="onkeyup"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onkeyup="onkeyup">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/alternate/myimage.gif" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/fr/default/myimage.gif" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/fr/alternate/myimage.gif" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyUsemap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" usemap="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" usemap="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcKeyVspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" vspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" vspace="15">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcKeyWidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img srcKey="default.testing.image" width="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/images/default/default/myimage.gif" width="15">
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
