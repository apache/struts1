<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title><bean:message key="logon.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<html:errors/>

<html:form action="/logon" focus="username">
<table border="0" width="100%">

  <tr>
    <th align="right">
      <bean:message key="prompt.username"/>
    </th>
    <td align="left">
      <html:text property="username" size="16" maxlength="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      <bean:message key="prompt.password"/>
    </th>
    <td align="left">
      <html:password property="password" size="16" maxlength="16"
                    redisplay="false"/>
    </td>
  </tr>

  <tr>
    <td align="right">
      <html:submit property="submit" value="Submit"/>
    </td>
    <td align="left">
      <html:reset/>
    </td>
  </tr>

</table>

</html:form>

</body>
</html:html>
