<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
/*
 * ===========================================================
 * Testing MessageTag (these comments serve as a divider of 
 *                     functionality being tested)
 * 
 * Section: 3 Arg
 * Locale:  (default)
 * ===========================================================
 */
%>


<logic:equal name="runTest" value="testMessageTag3ArgKeyNoScopeDefaultBundle">
	<bean:message key="default.bundle.message.3" arg0="1" arg1="2" arg2="3"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeyApplicationScopeDefaultBundle">
	<bean:message key="default.bundle.message.3" arg0="1" arg1="2" arg2="3" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeySessionScopeDefaultBundle">
	<bean:message key="default.bundle.message.3" arg0="1" arg1="2" arg2="3" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeyRequestScopeDefaultBundle">
	<bean:message key="default.bundle.message.3" arg0="1" arg1="2" arg2="3" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag3ArgKeyNoScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.3" arg0="1" arg1="2" arg2="3" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeyApplicationScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.3" arg0="1" arg1="2" arg2="3" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeySessionScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.3" arg0="1" arg1="2" arg2="3" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgKeyRequestScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.3" arg0="1" arg1="2" arg2="3" bundle="alternate" scope="request"/>
</logic:equal>





<logic:equal name="runTest" value="testMessageTag3ArgNameNoScopeDefaultBundle">
	<bean:define id="key">
		default.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNameApplicationScopeDefaultBundle">
	<bean:define id="key" toScope="application">
		default.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNameSessionScopeDefaultBundle">
	<bean:define id="key" toScope="session">
		default.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="session"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag3ArgNameRequestScopeDefaultBundle">
	<bean:define id="key" toScope="request">
		default.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="request"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag3ArgNameNoScopeAlternateBundle">
	<bean:define id="key">
		alternate.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNameApplicationScopeAlternateBundle">
	<bean:define id="key" toScope="application">
		alternate.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNameSessionScopeAlternateBundle">
	<bean:define id="key" toScope="session">
		alternate.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNameRequestScopeAlternateBundle">
	<bean:define id="key" toScope="request">
		alternate.bundle.message.3
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="request"/>
</logic:equal>









<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyNoScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyApplicationScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNamePropertySessionScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="session" property="string"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyRequestScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"scope="request" property="string"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyNoScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyApplicationScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNamePropertySessionScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="session" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag3ArgNamePropertyRequestScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3"bundle="alternate" scope="request" property="string"/>
</logic:equal>


