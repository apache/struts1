<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<form:html locale="true">
<head>
<title><bean:message key="logon.title"/></title>
<form:base/>
</head>
<body bgcolor="white">

<struts:errors/>

<form:form action="logon.do" focus="username">
<table border="0" width="100%">

  <tr>
    <th align="right">
      <bean:message key="prompt.username"/>
    </th>
    <td align="left">
      <form:text property="username" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.password"/>
    </th>
    <td align="left">
      <form:password property="password" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <form:submit property="submit" value="Submit"/>
    </td>
    <td align="left">
      <form:reset/>
    </td>
  </tr>

</table>

</form:form>

</body>
</form:html>
