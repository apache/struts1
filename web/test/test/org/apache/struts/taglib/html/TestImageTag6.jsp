<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImageSrcOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" property=""/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" tabindex="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" tabindex="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image src="/test/some/image.gif" value="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" value="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image src="/test/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:image src="/test/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image src="/test/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:image src="/test/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:image src="/test/some/image.gif" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:image src="/test/some/image.gif" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
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
