<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testDefineNameApplicationScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineNamePropertyApplicationScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" property="value" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineValueApplicationScope">
	<bean:define id="DEFINE_TAG_KEY" value="Test Value" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineBodyApplicationScope">
	<bean:define id="DEFINE_TAG_KEY" scope="application">
		Test Value
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testDefineNameSessionScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineNamePropertySessionScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" property="value" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineValueSessionScope">
	<bean:define id="DEFINE_TAG_KEY" value="Test Value" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineBodySessionScope">
	<bean:define id="DEFINE_TAG_KEY" scope="session">
		Test Value
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testDefineNameRequestScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" scope="request"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineNamePropertyRequestScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" property="value" scope="request"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineValueRequestScope">
	<bean:define id="DEFINE_TAG_KEY" value="Test Value" scope="request"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineBodyRequestScope">
	<bean:define id="DEFINE_TAG_KEY" scope="request">
		Test Value
	</bean:define>
</logic:equal>



<logic:equal name="runTest" value="testDefineNameNoScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineNamePropertyNoScope">
	<bean:define id="DEFINE_TAG_KEY" name="TestDefineTag" property="value"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineValueNoScope">
	<bean:define id="DEFINE_TAG_KEY" value="Test Value"/>
</logic:equal>

<logic:equal name="runTest" value="testDefineBodyNoScope">
	<bean:define id="DEFINE_TAG_KEY">
		Test Value
	</bean:define>
</logic:equal>


<bean:write name="DEFINE_TAG_KEY"/>



