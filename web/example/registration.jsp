<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld"    prefix="app" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<struts:ifPropertyEquals name="registrationForm" scope="request"
                         property="action" value="Edit">
  <app:checkLogon/>
</struts:ifPropertyEquals>

<html>
<head>
<logic:equal name="registrationForm" property="action"
            scope="request" value="Create">
  <title><struts:message key="registration.title.create"/></title>
</logic:equal>
<logic:equal name="registrationForm" property="action"
            scope="request" value="Edit">
  <title><struts:message key="registration.title.edit"/></title>
</logic:equal>
</head>
<body bgcolor="white">

<struts:errors/>

<form:form action="saveRegistration.do" name="registrationForm"
            scope="request" type="org.apache.struts.example.RegistrationForm">
<form:hidden property="action"/>
<table border="0" width="100%">

  <tr>
    <th align="right">
      <struts:message key="prompt.username"/>
    </th>
    <td align="left">
      <logic:equal name="registrationForm" property="action"
                  scope="request" value="Create">
        <form:text property="username" size="16" maxlength="16"/>
      </logic:equal>
      <logic:equal name="registrationForm" property="action"
                  scope="request" value="Edit">
        <bean:write name="registrationForm" property="username"
                   scope="request" filter="true"/>
	<form:hidden property="username"/>
      </logic:equal>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.password"/>
    </th>
    <td align="left">
      <form:password property="password" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.password2"/>
    </th>
    <td align="left">
      <form:password property="password2" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.fullName"/>
    </th>
    <td align="left">
      <form:text property="fullName" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.fromAddress"/>
    </th>
    <td align="left">
      <form:text property="fromAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.replyToAddress"/>
    </th>
    <td align="left">
      <form:text property="replyToAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <form:submit>
        <struts:message key="button.save"/>
      </form:submit>
    </td>
    <td align="left">
      <form:reset>
        <struts:message key="button.reset"/>
      </form:reset>
      &nbsp;
      <form:cancel>
        <struts:message key="button.cancel"/>
      </form:cancel>
    </td>
  </tr>

</table>
</form:form>

<logic:equal name="registrationForm" property="action"
            scope="request" value="Edit">

<div align="center">
<h3><struts:message key="heading.subscriptions"/></h3>
</div>

<table border="1" width="100%">

  <tr>
    <th align="center" width="30%">
      <struts:message key="heading.host"/>
    </th>
    <th align="center" width="25%">
      <struts:message key="heading.user"/>
    </th>
    <th align="center" width="10%">
      <struts:message key="heading.type"/>
    </th>
    <th align="center" width="10%">
      <struts:message key="heading.autoConnect"/>
    </th>
    <th align="center" width="15%">
      <struts:message key="heading.action"/>
    </th>
  </tr>

<logic:iterate id="subscription" name="user" property="subscriptions">
  <tr>
    <td align="left">
      <bean:write name="subscription" property="host" filter="true"/>
    </td>
    <td align="left">
      <bean:write name="subscription" property="username" filter="true"/>
    </td>
    <td align="center">
      <bean:write name="subscription" property="type" filter="true"/>
    </td>
    <td align="center">
      <bean:write name="subscription" property="autoConnect"/>
    </td>
    <td align="center">
      <app:linkSubscription href="editSubscription.do?action=Delete">
        <struts:message key="registration.deleteSubscription"/>
      </app:linkSubscription>
      <app:linkSubscription href="editSubscription.do?action=Edit">
        <struts:message key="registration.editSubscription"/>
      </app:linkSubscription>
    </td>
  </tr>
</logic:iterate>

</table>

<app:linkUser href="editSubscription.do?action=Create">
  <struts:message key="registration.addSubscription"/>
</app:linkUser>

</logic:equal>

</body>
</html>
