<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>




<!-- --------Testing attributes using page------ -->


<logic:equal name="runTest" value="testImagePageOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" property=""/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" tabindex="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" tabindex="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageTitleKeyAlterateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image page="/some/image.gif" value="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/some/image.gif")%>" value="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image page="/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:image page="/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image page="/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:image page="/some/image.gif" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:image page="/some/image.gif" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/test/some/image.gif")%>">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:image page="/some/image.gif" indexed="true"/>
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
    throw new javax.servlet.jsp.JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>
