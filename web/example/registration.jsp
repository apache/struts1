<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/app.tld"    prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:equal name="registrationForm" property="action"
            scope="request" value="Edit">
  <app:checkLogon/>
</logic:equal>

<html:html>
<head>
<logic:equal name="registrationForm" property="action"
            scope="request" value="Create">
  <title><bean:message key="registration.title.create"/></title>
</logic:equal>
<logic:equal name="registrationForm" property="action"
            scope="request" value="Edit">
  <title><bean:message key="registration.title.edit"/></title>
</logic:equal>
<html:base/>
</head>
<body bgcolor="white">

<html:errors/>

<html:form action="/saveRegistration">
<html:hidden property="action"/>
<table border="0" width="100%">

  <tr>
    <th align="right">
      <bean:message key="prompt.username"/>
    </th>
    <td align="left">
      <logic:equal name="registrationForm" property="action"
                  scope="request" value="Create">
        <html:text property="username" size="16" maxlength="16"/>
      </logic:equal>
      <logic:equal name="registrationForm" property="action"
                  scope="request" value="Edit">
        <bean:write name="registrationForm" property="username"
                   scope="request" filter="true"/>
	<html:hidden property="username"/>
      </logic:equal>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.password"/>
    </th>
    <td align="left">
      <html:password property="password" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.password2"/>
    </th>
    <td align="left">
      <html:password property="password2" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.fullName"/>
    </th>
    <td align="left">
      <html:text property="fullName" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.fromAddress"/>
    </th>
    <td align="left">
      <html:text property="fromAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.replyToAddress"/>
    </th>
    <td align="left">
      <html:text property="replyToAddress" size="50"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <html:submit>
        <bean:message key="button.save"/>
      </html:submit>
    </td>
    <td align="left">
      <html:reset>
        <bean:message key="button.reset"/>
      </html:reset>
      &nbsp;
      <html:cancel>
        <bean:message key="button.cancel"/>
      </html:cancel>
    </td>
  </tr>

</table>
</html:form>

<logic:equal name="registrationForm" property="action"
            scope="request" value="Edit">

<div align="center">
<h3><bean:message key="heading.subscriptions"/></h3>
</div>

<table border="1" width="100%">

  <tr>
    <th align="center" width="30%">
      <bean:message key="heading.host"/>
    </th>
    <th align="center" width="25%">
      <bean:message key="heading.user"/>
    </th>
    <th align="center" width="10%">
      <bean:message key="heading.type"/>
    </th>
    <th align="center" width="10%">
      <bean:message key="heading.autoConnect"/>
    </th>
    <th align="center" width="15%">
      <bean:message key="heading.action"/>
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
      <app:linkSubscription page="/editSubscription.do?action=Delete">
        <bean:message key="registration.deleteSubscription"/>
      </app:linkSubscription>
      <app:linkSubscription page="/editSubscription.do?action=Edit">
        <bean:message key="registration.editSubscription"/>
      </app:linkSubscription>
    </td>
  </tr>
</logic:iterate>

</table>

<html:link page="/editSubscription.do?action=Create" paramId="username"
 paramName="registrationForm" paramProperty="username">
  <bean:message key="registration.addSubscription"/>
</html:link>
                     

</logic:equal>

</body>
</html:html>
