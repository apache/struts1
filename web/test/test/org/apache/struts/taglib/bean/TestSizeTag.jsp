<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
	/* 
	 * Testing the SizeTag using an Array.
	 *                NoScope   App     Session      Requ    
	 * Name            x         x         x          x
	 * 
	 * NameProperty    x         x         x          x
	 * 
	 * NameProperty    x         x         x          x
	 *  (nested)
	 * 
	 * 
	 */
%>
<logic:equal name="runTest" value="testSizeTagNameArrayNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameArrayApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameArraySessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameArrayRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyArrayNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="array"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArrayApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="array" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArraySessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="array" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArrayRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="array" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyArrayNoScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.array"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArrayApplicationScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.array" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArraySessionScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.array" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyArrayRequestScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.array" scope="request"/>
</logic:equal>


<%
	/* 
	 * Testing the SizeTag using a Collection.
	 *                App     Session      Requ    
	 * Name            x         x           x
	 * 
	 * NameProperty    x         x           x
	 * 
	 * NameProperty    x         x           x
	 *  (nested)
	 * 
	 * 
	 */
%>
<logic:equal name="runTest" value="testSizeTagNameCollectionNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameCollectionApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameCollectionSessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameCollectionRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="collection"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="collection" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionSessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="collection" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="collection" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionNoScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.collection"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionApplicationScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.collection" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionSessionScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.collection" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyCollectionRequestScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.collection" scope="request"/>
</logic:equal>






<%
	/* 
	 * Testing the SizeTag using a Map.
	 *                App     Session      Requ    
	 * Name            x         x           x
	 * 
	 * NameProperty    x         x           x
	 * 
	 * NameProperty    x         x           x
	 *  (nested)
	 * 
	 * 
	 */
%>
<logic:equal name="runTest" value="testSizeTagNameMapNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameMapApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameMapSessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNameMapRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyMapNoScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="map"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapApplicationScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="map" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapSessionScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="map" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapRequestScope">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="map" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testSizeTagNamePropertyMapNoScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.map"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapApplicationScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.map" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapSessionScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.map" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testSizeTagNamePropertyMapRequestScopeNested">
	<bean:size id="PAGE_KEY" name="REQUEST_KEY" property="nestedObject.map" scope="request"/>
</logic:equal>


<bean:write name="PAGE_KEY"/>
