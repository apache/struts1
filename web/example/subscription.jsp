<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld"    prefix="app" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>
<app:checkLogon/>

<%-- In real life, these would be loaded from a database --%>
<%
  pageContext.setAttribute("serverTypeValues",
    new String[] { "imap", "pop3" });
  pageContext.setAttribute("serverTypeLabels",
    new String[] { "IMAP Protocol", "POP3 Protocol" });
%>

<html>
<head>
<struts:ifPropertyEquals name="subscriptionForm"
                         property="action" value="Create">
  <title><struts:message key="subscription.title.create"/></title>
</struts:ifPropertyEquals>
<struts:ifPropertyEquals name="subscriptionForm"
                         property="action" value="Delete">
  <title><struts:message key="subscription.title.delete"/></title>
</struts:ifPropertyEquals>
<struts:ifPropertyEquals name="subscriptionForm"
                         property="action" value="Edit">
  <title><struts:message key="subscription.title.edit"/></title>
</struts:ifPropertyEquals>
</head>
<body bgcolor="white">

<struts:errors/>

<struts:form action="saveSubscription.do" name="subscriptionForm"
              focus="host"
               type="org.apache.struts.example.SubscriptionForm">
<struts:hidden property="action"/>
<table border="0" width="100%">

  <tr>
    <th align="right">
      <struts:message key="prompt.username"/>
    </th>
    <td align="left">
        <jsp:getProperty name="user" property="username"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.mailHostname"/>
    </th>
    <td align="left">
      <struts:textarea property="host" cols="50" rows="1"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.mailUsername"/>
    </th>
    <td align="left">
      <struts:text property="username" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.mailPassword"/>
    </th>
    <td align="left">
      <struts:password property="password" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <struts:message key="prompt.mailServerType"/>
    </th>
    <td align="left">
      <struts:select property="type">
        <struts:options name="serverTypeValues"
                   labelName="serverTypeLabels"/>
      </struts:select>
    </td>
  </tr>

  <tr>
    <td align="right">
      <struts:ifParameterNotEquals name="action" value="Delete">
        <struts:submit>
          <struts:message key="button.save"/>
        </struts:submit>
      </struts:ifParameterNotEquals>
      <struts:ifParameterEquals name="action" value="Delete">
        <struts:submit>
          <struts:message key="button.confirm"/>
        </struts:submit>
      </struts:ifParameterEquals>
    </td>
    <td align="left">
      <struts:ifParameterNotEquals name="action" value="Delete">
        <struts:reset>
          <struts:message key="button.reset"/>
        </struts:reset>
      </struts:ifParameterNotEquals>
      &nbsp;
      <struts:cancel>
        <struts:message key="button.cancel"/>
      </struts:cancel>
    </td>
  </tr>

</table>

</struts:form>
