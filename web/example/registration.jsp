<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld"    prefix="app" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>
<struts:ifPropertyEquals name="registrationForm" scope="request"
                         property="action" value="Edit">
  <app:checkLogon/>
</struts:ifPropertyEquals>

<html>
<head>
<struts:ifPropertyEquals name="registrationForm" scope="request"
                         property="action" value="Create">
  <title><struts:message key="registration.title.create"/></title>
</struts:ifPropertyEquals>
<struts:ifPropertyEquals name="registrationForm" scope="request"
                         property="action" value="Edit">
  <title><struts:message key="registration.title.edit"/></title>
</struts:ifPropertyEquals>
</head>
<body bgcolor="white">

<struts:errors/>

<struts:form action="saveRegistration.do" name="registrationForm"
             scope="request" type="org.apache.struts.example.RegistrationForm">
<struts:hidden property="action"/>
<table border="0" width="100%">

  <tr>
    <th align="right">
      <struts:message key="prompt.username"/>
    </th>
    <td align="left">
      <struts:ifPropertyEquals name="registrationForm" scope="request"
                               property="action" value="Create">
        <struts:text property="username" size="16" maxlength="16"/>
      </struts:ifPropertyEquals>
      <struts:ifPropertyEquals name="registrationForm" scope="request"
                               property="action" value="Edit">
        <struts:property property="username"/>
	<struts:hidden property="username"/>
      </struts:ifPropertyEquals>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.password"/>
    </th>
    <td align="left">
      <struts:password property="password" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.password2"/>
    </th>
    <td align="left">
      <struts:password property="password2" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.fullName"/>
    </th>
    <td align="left">
      <struts:text property="fullName" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.fromAddress"/>
    </th>
    <td align="left">
      <struts:text property="fromAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.replyToAddress"/>
    </th>
    <td align="left">
      <struts:text property="replyToAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <struts:submit>
        <struts:message key="button.save"/>
      </struts:submit>
    </td>
    <td align="left">
      <struts:reset>
        <struts:message key="button.reset"/>
      </struts:reset>
      &nbsp;
      <struts:cancel>
        <struts:message key="button.cancel"/>
      </struts:cancel>
    </td>
  </tr>

</table>
</struts:form>

<struts:ifPropertyEquals name="registrationForm" scope="request"
                         property="action" value="Edit">

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

<struts:enumerate id="subscription" name="user" property="subscriptions">
  <tr>
    <td align="left">
      <struts:htmlProperty name="subscription" property="host"/>
    </td>
    <td align="left">
      <struts:htmlProperty name="subscription" property="username"/>
    </td>
    <td align="center">
      <struts:htmlProperty name="subscription" property="type"/>
    </td>
    <td align="center">
      <struts:htmlProperty name="subscription" property="autoConnect"/>
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
</struts:enumerate>

</table>

<app:linkUser href="editSubscription.do?action=Create">
  <struts:message key="registration.addSubscription"/>
</app:linkUser>

</struts:ifPropertyEquals>

</body>
</html>
