<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<html>
<head>
<title><bean:message key="index.title"/></title>
<link rel="stylesheet" type="text/css" href="base.css" />
</head>

<h3><bean:message key="index.heading"/></h3>
<ul>
<li><html:link action="/editRegistration?action=Create"><bean:message key="index.registration"/></html:link></li>
<li><html:link action="/logon"><bean:message key="index.logon"/></html:link></li>
</ul>

<p><html:link action="/tour"><bean:message key="index.tour"/></html:link></p>

<p><html:img bundle="alternate" pageKey="struts.logo.path" altKey="struts.logo.alt"/></p>

</body>
</html>
