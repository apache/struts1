<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html locale="true">
<head>
<title><bean:message key="change.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<bean:message key="change.message"/>
<html:link page="/logon.jsp">
  <bean:message key="change.try"/>
</html:link>

</body>
</html:html>
