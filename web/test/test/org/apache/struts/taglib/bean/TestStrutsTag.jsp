<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testStrutsTagFormBean">
	<bean:struts id="PAGE_KEY" formBean="testFormBean"/>
	<bean:write name="PAGE_KEY" property="name"/>
</logic:equal>

<logic:equal name="runTest" value="testStrutsTagDynaFormBean">
	<bean:struts id="PAGE_KEY" formBean="testDynaFormBean"/>
	<bean:write name="PAGE_KEY" property="name"/>
</logic:equal>

<logic:equal name="runTest" value="testStrutsTagForward">
	<bean:struts id="PAGE_KEY" forward="testIncludeTagForward"/>
	<bean:write name="PAGE_KEY" property="name"/>
</logic:equal>

<logic:equal name="runTest" value="testStrutsTagMapping">
	<bean:struts id="PAGE_KEY" mapping="/testIncludeTagTransaction"/>
	<bean:write name="PAGE_KEY" property="path"/>
</logic:equal>




