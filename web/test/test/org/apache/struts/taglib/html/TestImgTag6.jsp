<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using src ------ -->
<logic:equal name="runTest" value="testImgSrcOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onclick="onclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onclick="onclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" ondblclick="ondblclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" ondblclick="ondblclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onkeydown="onkeydown"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onkeydown="onkeydown">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onkeypress="onkeypress"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onkeypress="onkeypress">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onkeyup="onkeyup"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onkeyup="onkeyup">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcUsemap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" usemap="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" usemap="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgSrcVspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" vspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" vspace="15">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgSrcWidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img src="/some/image.gif" width="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="/some/image.gif" width="15">
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
