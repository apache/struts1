<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
/*
 * ===========================================================
 * Testing MessageTag (these comments serve as a divider of 
 *                     functionality being tested)
 * 
 * Section: 4 Arg
 * Locale:  (default)
 * ===========================================================
 */
%>


<logic:equal name="runTest" value="testMessageTag4ArgKeyNoScopeDefaultBundle">
	<bean:message key="default.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeyApplicationScopeDefaultBundle">
	<bean:message key="default.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeySessionScopeDefaultBundle">
	<bean:message key="default.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeyRequestScopeDefaultBundle">
	<bean:message key="default.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag4ArgKeyNoScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeyApplicationScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeySessionScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgKeyRequestScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.4" arg0="1" arg1="2" arg2="3" arg3="4" bundle="alternate" scope="request"/>
</logic:equal>





<logic:equal name="runTest" value="testMessageTag4ArgNameNoScopeDefaultBundle">
	<bean:define id="key">
		default.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNameApplicationScopeDefaultBundle">
	<bean:define id="key" toScope="application">
		default.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNameSessionScopeDefaultBundle">
	<bean:define id="key" toScope="session">
		default.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="session"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag4ArgNameRequestScopeDefaultBundle">
	<bean:define id="key" toScope="request">
		default.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="request"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag4ArgNameNoScopeAlternateBundle">
	<bean:define id="key">
		alternate.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNameApplicationScopeAlternateBundle">
	<bean:define id="key" toScope="application">
		alternate.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNameSessionScopeAlternateBundle">
	<bean:define id="key" toScope="session">
		alternate.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNameRequestScopeAlternateBundle">
	<bean:define id="key" toScope="request">
		alternate.bundle.message.4
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="request"/>
</logic:equal>









<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyNoScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyApplicationScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNamePropertySessionScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="session" property="string"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyRequestScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"scope="request" property="string"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyNoScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyApplicationScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNamePropertySessionScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="session" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag4ArgNamePropertyRequestScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2" arg2="3" arg3="4"bundle="alternate" scope="request" property="string"/>
</logic:equal>


