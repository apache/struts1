<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<logic:equal name="runTest" value="testHeaderTagName">
	<bean:header id="HEADER_TAG_KEY" name="HEADER_KEY"/>
</logic:equal>

<logic:equal name="runTest" value="testHeaderTagNameMultiple">
	<bean:header id="header" name="HEADER_KEY" multiple="any value here"/>
	<bean:define id="HEADER_TAG_KEY"
		><logic:iterate id="headerValue" name="header"
			><bean:write name="headerValue"
		/></logic:iterate>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testHeaderTagNameValue">
	<bean:header id="HEADER_TAG_KEY" name="HEADER_KEY" value="HEADER_VAL"/>
</logic:equal>

<bean:write name="HEADER_TAG_KEY"/>



