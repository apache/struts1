<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/tags/app" prefix="app" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<app:checkLogon/>
<jsp:useBean id="user" scope="session" type="org.apache.struts.webapp.example.User"/>

<html:html>
<head>
<title><bean:message key="mainMenu.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<h3><bean:message key="mainMenu.heading"/>
<jsp:getProperty name="user" property="username"/></h3>
<ul>
<li><html:link action="/editRegistration?action=Edit"><bean:message key="mainMenu.registration"/></html:link></li>
<li><html:link forward="logoff"><bean:message key="mainMenu.logoff"/></html:link></li>
</ul>

</body>
</html:html>
