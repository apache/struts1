<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testParameterTag">
	<bean:parameter id="PAGE_KEY" name="testParam"/>
</logic:equal>

<logic:equal name="runTest" value="testParameterTagValue">
	<bean:parameter id="PAGE_KEY" name="does not exists" value="Test Value"/>
</logic:equal>


<bean:write name="PAGE_KEY"/>
