<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testWriteTagName">
	<bean:write name="REQUEST_KEY"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameProperty">
	<bean:write name="REQUEST_KEY" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFormat">
	<bean:write name="REQUEST_KEY" format="###,###"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFormatKeyDefaultBundle">
	<bean:write name="REQUEST_KEY" formatKey="default.format"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFormatKeyDefaultBundleDouble">
	<bean:write name="REQUEST_KEY" formatKey="default.format"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFormatKeyAlternateBundle">
	<bean:write name="REQUEST_KEY" formatKey="alternate.format" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFormatKeyAlternateBundleDouble">
	<bean:write name="REQUEST_KEY" formatKey="alternate.format" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNamePropertyFormat">
	<bean:write name="REQUEST_KEY" property="integerValue" format="###,###"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameIgnore">
	<bean:write name="REQUEST_KEY" ignore="true" scope="request"/>
</logic:equal>

<logic:equal name="runTest" value="testWriteTagNameFilter">
	<bean:write name="REQUEST_KEY" filter="true"/>
</logic:equal>

