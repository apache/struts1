<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImgPageOnclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onclick="onclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onclick="onclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageOndblclick">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" ondblclick="ondblclick"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             ondblclick="ondblclick">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageOnkeydown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onkeydown="onkeydown"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onkeydown="onkeydown">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageOnkeypress">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onkeypress="onkeypress"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onkeypress="onkeypress">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageOnkeyup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onkeyup="onkeyup"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onkeyup="onkeyup">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" titleKey="alternate.bundle.message"
                  bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" titleKey="alternate.bundle.message"
                  bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             title="Message D'Essai">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageUsemap">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" usemap="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             usemap="XXX">
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImgPageVspace">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" vspace="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             vspace="15">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImgPageWidth">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:img page="/some/image.gif" width="15"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <img src="<%=response.encodeURL( request.getContextPath() + "/some/image.gif")%>"
             width="15">
    </bean:define>
</logic:equal>


<%
    String expected = "";
    String compareTo = "";

    if (pageContext.getAttribute("EXPECTED_RESULTS") == null) {
        throw new javax.servlet.jsp.JspException
                ("No tests on this page were called.  Please verify that you've setup the tests correctly.");
    } else {
        expected = pageContext.getAttribute("TEST_RESULTS").toString();
    }
    if (pageContext.getAttribute("TEST_RESULTS") != null) {
        compareTo = pageContext.getAttribute("EXPECTED_RESULTS").toString();
    }

    Assert.assertEquals(expected, compareTo);
%>
