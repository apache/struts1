<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
/*
 * ===========================================================
 * Testing MessageTag (these comments serve as a divider of 
 *                     functionality being tested)
 * 
 * Section: 2 Arg
 * Locale:  (default)
 * ===========================================================
 */
%>


<logic:equal name="runTest" value="testMessageTag2ArgKeyNoScopeDefaultBundle">
	<bean:message key="default.bundle.message.2" arg0="1" arg1="2"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeyApplicationScopeDefaultBundle">
	<bean:message key="default.bundle.message.2" arg0="1" arg1="2" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeySessionScopeDefaultBundle">
	<bean:message key="default.bundle.message.2" arg0="1" arg1="2" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeyRequestScopeDefaultBundle">
	<bean:message key="default.bundle.message.2" arg0="1" arg1="2" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag2ArgKeyNoScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.2" arg0="1" arg1="2" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeyApplicationScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.2" arg0="1" arg1="2" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeySessionScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.2" arg0="1" arg1="2" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgKeyRequestScopeAlternateBundle">
	<bean:message key="alternate.bundle.message.2" arg0="1" arg1="2" bundle="alternate" scope="request"/>
</logic:equal>





<logic:equal name="runTest" value="testMessageTag2ArgNameNoScopeDefaultBundle">
	<bean:define id="key">
		default.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNameApplicationScopeDefaultBundle">
	<bean:define id="key" toScope="application">
		default.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNameSessionScopeDefaultBundle">
	<bean:define id="key" toScope="session">
		default.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"scope="session"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag2ArgNameRequestScopeDefaultBundle">
	<bean:define id="key" toScope="request">
		default.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"scope="request"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag2ArgNameNoScopeAlternateBundle">
	<bean:define id="key">
		alternate.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNameApplicationScopeAlternateBundle">
	<bean:define id="key" toScope="application">
		alternate.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNameSessionScopeAlternateBundle">
	<bean:define id="key" toScope="session">
		alternate.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNameRequestScopeAlternateBundle">
	<bean:define id="key" toScope="request">
		alternate.bundle.message.2
	</bean:define>
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="request"/>
</logic:equal>









<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyNoScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2"property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyApplicationScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2"scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNamePropertySessionScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2"scope="session" property="string"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyRequestScopeDefaultBundle">
	<bean:message name="key" arg0="1" arg1="2"scope="request" property="string"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyNoScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyApplicationScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNamePropertySessionScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="session" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTag2ArgNamePropertyRequestScopeAlternateBundle">
	<bean:message name="key" arg0="1" arg1="2"bundle="alternate" scope="request" property="string"/>
</logic:equal>


