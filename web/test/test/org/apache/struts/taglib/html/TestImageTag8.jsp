<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->
<logic:equal name="runTest" value="testImageSrcKeyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" property=""/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" tabindex="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" tabindex="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/alternate/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/default/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyTitleKeyAlternateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/fr/alternate/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image srcKey="default.testing.image" value="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>" value="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImageSrcKeyIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image srcKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:image srcKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image srcKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:image srcKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:image srcKey="default.testing.image" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImageSrcKeyIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:image srcKey="default.testing.image" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/images/default/default/myimage.gif")%>">
	
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
