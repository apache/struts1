<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/app" prefix="app" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<app:checkLogon/>
<html>
<head>
<title><bean:message key="mainMenu.title"/></title>
<link rel="stylesheet" type="text/css" href="base.css" />
</head>
<h3><bean:message key="mainMenu.heading"/> <bean:write name="user" property="fullName" /></h3>
<ul>
<li><html:link action="/EditRegistration?action=Edit"><bean:message key="mainMenu.registration"/></html:link></li>
<li><html:link forward="logoff"><bean:message key="mainMenu.logoff"/></html:link></li>
</ul>
</body>
</html>
