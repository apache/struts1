<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/app.tld"    prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<app:checkLogon/>

<%-- In real life, these would be loaded from a database --%>
<%
  java.util.ArrayList list = new java.util.ArrayList();
  list.add(new org.apache.struts.util.LabelValueBean("IMAP Protocol", "imap"));
  list.add(new org.apache.struts.util.LabelValueBean("POP3 Protocol", "pop3"));
  pageContext.setAttribute("serverTypes", list);
%>

<html:html>
<head>
<logic:equal name="subscriptionForm" property="action"
            scope="request" value="Create">
  <title><bean:message key="subscription.title.create"/></title>
</logic:equal>
<logic:equal name="subscriptionForm" property="action"
            scope="request" value="Delete">
  <title><bean:message key="subscription.title.delete"/></title>
</logic:equal>
<logic:equal name="subscriptionForm" property="action"
            scope="request" value="Edit">
  <title><bean:message key="subscription.title.edit"/></title>
</logic:equal>
<html:base/>
</head>
<body bgcolor="white">

<html:errors/>

<html:form action="/saveSubscription" focus="host">
<html:hidden property="action"/>
<table border="0" width="100%">

  <tr>
    <th align="right">
      <bean:message key="prompt.username"/>
    </th>
    <td align="left">
        <bean:write name="user" property="username" filter="true"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.mailHostname"/>
    </th>
    <td align="left">
      <logic:equal name="subscriptionForm" property="action"
                  scope="request" value="Create">
        <html:text property="host" size="50"/>
      </logic:equal>
      <logic:notEqual name="subscriptionForm" property="action"
                     scope="request" value="Create">
        <bean:write   name="subscriptionForm" property="host"/>
        <html:hidden property="host"/>
      </logic:notEqual>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.mailUsername"/>
    </th>
    <td align="left">
      <html:text property="username" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.mailPassword"/>
    </th>
    <td align="left">
      <html:password property="password" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.mailServerType"/>
    </th>
    <td align="left">
      <html:select property="type">
        <html:options collection="serverTypes" property="value"
                   labelProperty="label"/>
      </html:select>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.autoConnect"/>
    </th>
    <td align="left">
      <html:checkbox property="autoConnect"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <logic:equal name="subscriptionForm" property="action"
                  scope="request" value="Create">
        <html:submit>
          <bean:message key="button.save"/>
        </html:submit>
      </logic:equal>
      <logic:equal name="subscriptionForm" property="action"
                  scope="request" value="Delete">
        <html:submit>
          <bean:message key="button.confirm"/>
        </html:submit>
      </logic:equal>
      <logic:equal name="subscriptionForm" property="action"
                  scope="request" value="Edit">
        <html:submit>
          <bean:message key="button.save"/>
        </html:submit>
      </logic:equal>
    </td>
    <td align="left">
      <logic:notEqual name="subscriptionForm" property="action"
                     scope="request" value="Delete">
        <html:reset>
          <bean:message key="button.reset"/>
        </html:reset>
      </logic:notEqual>
      &nbsp;
      <html:cancel>
        <bean:message key="button.cancel"/>
      </html:cancel>
    </td>
  </tr>

</table>

</html:form>

</body>
</html:html>
