<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>
<app:checkLogon/>
<jsp:useBean id="user" scope="session" type="org.apache.struts.example.User"/>

<html>
<head>
<title><bean:message key="mainMenu.title"/></title>
</head>
<body bgcolor="white">

<h3><bean:message key="mainMenu.heading"/>
<jsp:getProperty name="user" property="username"/></h3>
<ul>
<li><form:link href="editRegistration.do?action=Edit"><bean:message key="mainMenu.registration"/></form:link></li>
<li><form:link href="logoff.do"><bean:message key="mainMenu.logoff"/></form:link></li>
</ul>

</body>
</html>
