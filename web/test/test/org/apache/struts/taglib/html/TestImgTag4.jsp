<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImgPageKeyOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onclick="onclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onclick="onclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" ondblclick="ondblclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" ondblclick="ondblclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onkeydown="onkeydown"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeydown="onkeydown">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onkeypress="onkeypress"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeypress="onkeypress">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onkeyup="onkeyup"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onkeyup="onkeyup">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/alternate/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/fr/default/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/fr/alternate/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyUsemap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" usemap="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" usemap="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageKeyVspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" vspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" vspace="15">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageKeyWidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img pageKey="default.testing.image" width="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" width="15">
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
