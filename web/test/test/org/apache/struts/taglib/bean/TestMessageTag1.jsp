<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
/*
 * ===========================================================
 * Testing MessageTag (these comments serve as a divider of 
 *                     functionality being tested)
 * 
 * Section: 1 Arg
 * Locale:  (default)
 * ===========================================================
 */
%>


<logic:equal name="runTest" value="testMessageTag1ArgKeyNoScopeDefaultBundle">
	<bean:message key="default.bundle.message.1" arg0="1"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeyApplicationScopeDefaultBundle">
	<bean:message key="default.bundle.message.1" arg0="1" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeySessionScopeDefaultBundle">
	<bean:message key="default.bundle.message.1" arg0="1" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeyRequestScopeDefaultBundle">
	<bean:message key="default.bundle.message.1" arg0="1" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag1ArgKeyNoScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.1" arg0="1" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeyApplicationScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.1" arg0="1" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeySessionScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.1" arg0="1" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgKeyRequestScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.1" arg0="1" bundle="alternate" scope="request"/>
</logic:equal>





<logic:equal name="runTest" value="testMessageTag1ArgNameNoScopeDefaultBundle">
	<bean:define id="key">
		default.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNameApplicationScopeDefaultBundle">
	<bean:define id="key" toScope="application">
		default.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNameSessionScopeDefaultBundle">
	<bean:define id="key" toScope="session">
		default.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"scope="session"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag1ArgNameRequestScopeDefaultBundle">
	<bean:define id="key" toScope="request">
		default.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"scope="request"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag1ArgNameNoScopeAlternateBundle">
	<bean:define id="key">
		alternate.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNameApplicationScopeAlternateBundle">
	<bean:define id="key" toScope="application">
		alternate.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNameSessionScopeAlternateBundle">
	<bean:define id="key" toScope="session">
		alternate.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNameRequestScopeAlternateBundle">
	<bean:define id="key" toScope="request">
		alternate.bundle.message.1
	</bean:define>
	<bean:message name="key" arg0="1"bundle="alternate" scope="request"/>
</logic:equal>









<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyNoScopeDefaultBundle">
	<bean:message name="key" arg0="1"property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyApplicationScopeDefaultBundle">
	<bean:message name="key" arg0="1"scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNamePropertySessionScopeDefaultBundle">
	<bean:message name="key" arg0="1"scope="session" property="string"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyRequestScopeDefaultBundle">
	<bean:message name="key" arg0="1"scope="request" property="string"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyNoScopeAlternateBundle">
	<bean:message name="key" arg0="1"bundle="alternate" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyApplicationScopeAlternateBundle">
	<bean:message name="key" arg0="1"bundle="alternate" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNamePropertySessionScopeAlternateBundle">
	<bean:message name="key" arg0="1"bundle="alternate" scope="session" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag1ArgNamePropertyRequestScopeAlternateBundle">
	<bean:message name="key" arg0="1"bundle="alternate" scope="request" property="string"/>
</logic:equal>


