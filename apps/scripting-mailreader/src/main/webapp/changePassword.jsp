<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html:html locale="true">
<head>
<title><bean:message key="change.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<bean:message key="change.message"/>
<html:link action="/Logon">
  <bean:message key="change.try"/>
</html:link>

</body>
</html:html>
