<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
/*
 * ===========================================================
 * Testing MessageTag (these comments serve as a divider of 
 *                     functionality being tested)
 * 
 * Section: No Arg
 * Locale:  (default)
 * ===========================================================
 */
%>


<logic:equal name="runTest" value="testMessageTagNoArgKeyNoScopeDefaultBundle">
	<bean:message key="default.bundle.message"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeyApplicationScopeDefaultBundle">
	<bean:message key="default.bundle.message" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeySessionScopeDefaultBundle">
	<bean:message key="default.bundle.message" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeyRequestScopeDefaultBundle">
	<bean:message key="default.bundle.message" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTagNoArgKeyNoScopeAlternateBundle">
	<bean:message key="alternate.bundle.message" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeyApplicationScopeAlternateBundle">
	<bean:message key="alternate.bundle.message" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeySessionScopeAlternateBundle">
	<bean:message key="alternate.bundle.message" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgKeyRequestScopeAlternateBundle">
	<bean:message key="alternate.bundle.message" bundle="alternate" scope="request"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTagNoArgNameNoScopeDefaultBundle">
	<bean:define id="key">
		default.bundle.message
	</bean:define>
	<bean:message name="key"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNameApplicationScopeDefaultBundle">
	<bean:define id="key" toScope="application">
		default.bundle.message
	</bean:define>
	<bean:message name="key" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNameSessionScopeDefaultBundle">
	<bean:define id="key" toScope="session">
		default.bundle.message
	</bean:define>
	<bean:message name="key" scope="session"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTagNoArgNameRequestScopeDefaultBundle">
	<bean:define id="key" toScope="request">
		default.bundle.message
	</bean:define>
	<bean:message name="key" scope="request"/>
</logic:equal>









<logic:equal name="runTest" value="testMessageTagNoArgNameNoScopeAlternateBundle">
	<bean:define id="key">
		alternate.bundle.message
	</bean:define>
	<bean:message name="key" bundle="alternate"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNameApplicationScopeAlternateBundle">
	<bean:define id="key" toScope="application">
		alternate.bundle.message
	</bean:define>
	<bean:message name="key" bundle="alternate" scope="application"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNameSessionScopeAlternateBundle">
	<bean:define id="key" toScope="session">
		alternate.bundle.message
	</bean:define>
	<bean:message name="key" bundle="alternate" scope="session"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNameRequestScopeAlternateBundle">
	<bean:define id="key" toScope="request">
		alternate.bundle.message
	</bean:define>
	<bean:message name="key" bundle="alternate" scope="request"/>
</logic:equal>











<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyNoScopeDefaultBundle">
	<bean:message name="key" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyApplicationScopeDefaultBundle">
	<bean:message name="key" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNamePropertySessionScopeDefaultBundle">
	<bean:message name="key" scope="session" property="string"/>
</logic:equal>


<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyRequestScopeDefaultBundle">
	<bean:message name="key" scope="request" property="string"/>
</logic:equal>



<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyNoScopeAlternateBundle">
	<bean:message name="key" bundle="alternate" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyApplicationScopeAlternateBundle">
	<bean:message name="key" bundle="alternate" scope="application" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNamePropertySessionScopeAlternateBundle">
	<bean:message name="key" bundle="alternate" scope="session" property="string"/>
</logic:equal>

<logic:equal name="runTest" value="testMessageTagNoArgNamePropertyRequestScopeAlternateBundle">
	<bean:message name="key" bundle="alternate" scope="request" property="string"/>
</logic:equal>


