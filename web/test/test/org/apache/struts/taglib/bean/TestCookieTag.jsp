<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<logic:equal name="runTest" value="testCookieTagName">
	<bean:cookie id="cookie" name="COOKIE_KEY"/>
	<bean:define id="COOKIE_TAG_KEY" name="cookie" property="value"/>
</logic:equal>

<logic:equal name="runTest" value="testCookieTagNameMultiple">
	<bean:cookie id="cookie" name="COOKIE_KEY" multiple="any value here"/>
	<bean:define id="COOKIE_TAG_KEY"
		><logic:iterate id="cookieValue" name="cookie" type="javax.servlet.http.Cookie"
			><bean:write name="cookieValue" property="value"
		/></logic:iterate>
	</bean:define>
</logic:equal>


<logic:equal name="runTest" value="testCookieTagNameValue">
	<bean:cookie id="cookie" name="COOKIE_KEY" value="COOKIE_VAL"/>
	<bean:define id="COOKIE_TAG_KEY" name="cookie" property="value"/>

</logic:equal>

<bean:write name="COOKIE_TAG_KEY"/>



