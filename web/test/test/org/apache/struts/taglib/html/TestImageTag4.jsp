<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- --------Testing attributes using page------ -->

<logic:equal name="runTest" value="testImagePageKeyOnmousedown">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onmousedown="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmousedown="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnmousemove">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onmousemove="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmousemove="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnmouseout">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onmouseout="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseout="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnmouseover">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onmouseover="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseover="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyOnmouseup">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" onmouseup="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" onmouseup="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyProperty">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" property=""/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyStyle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" style="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" style="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyStyleClass">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" styleClass="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" class="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyStyleId">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" styleId="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" id="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTabindex">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" tabindex="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" tabindex="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTitle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" title="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" title="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTitleKeyDefaultBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTitleKeyAlternateBundle">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/alternate/myimage.gif")%>" title="Testing Message">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTitleKeyDefaultBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" titleKey="default.bundle.message"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/default/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyTitleKeyAlterateBundle_fr">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="alternate.testing.image" titleKey="alternate.bundle.message" bundle="alternate"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/fr/alternate/myimage.gif")%>" title="Message D'Essai">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyValue">
    <bean:define id="TEST_RESULTS" toScope="page">
        <html:image pageKey="default.testing.image" value="XXX"/>
    </bean:define>
    <bean:define id="EXPECTED_RESULTS" toScope="page">
        <input type="image" name="" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>" value="XXX">
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testImagePageKeyIndexedArray">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image pageKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyIndexedArrayProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="list">
		<html:image pageKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyIndexedMap">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst">
		<html:image pageKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyIndexedMapProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	  <logic:iterate id="indivItem" name="lst" property="map">
		<html:image pageKey="default.testing.image" indexed="true"/>
	  </logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
		
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyIndexedEnumeration">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst">
		<html:image pageKey="default.testing.image" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
	
	</bean:define>
</logic:equal>

<logic:equal name="runTest" value="testImagePageKeyIndexedEnumerationProperty">
	<bean:define id="TEST_RESULTS" toScope="page">
	<logic:iterate id="indivItem" name="lst" property="enumeration">
		<html:image pageKey="default.testing.image" indexed="true"/>
	</logic:iterate>
	</bean:define>
	<bean:define id="EXPECTED_RESULTS" toScope="page">
	
		<input type="image" name="[0]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
	
		<input type="image" name="[1]" src="<%=response.encodeURL("/test/images/default/default/myimage.gif")%>">
	
	</bean:define>
</logic:equal>







<% 
String expected = "";
String compareTo = "";

if (pageContext.getAttribute("EXPECTED_RESULTS") == null){
    throw new JspException("No tests on this page were called.  Please verify that you've setup the tests correctly.");
}else{
	expected=pageContext.getAttribute("TEST_RESULTS").toString();
}
if (pageContext.getAttribute("TEST_RESULTS") != null){
	compareTo=pageContext.getAttribute("EXPECTED_RESULTS").toString();
}

Assert.assertEquals(compareTo, expected);
%>