<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>
<app:checkLogon/>
<jsp:useBean id="user" scope="session" type="org.apache.struts.example.User"/>

<html>
<head>
<title><struts:message key="mainMenu.title"/></title>
</head>
<body bgcolor="white">

<h3><struts:message key="mainMenu.heading"/>
<jsp:getProperty name="user" property="username"/></h3>
<ul>
<li><struts:link href="editRegistration.do?action=Edit"><struts:message key="mainMenu.registration"/></struts:link>
<li><struts:link href="logoff.do"><struts:message key="mainMenu.logoff"/></struts:link>
</ul>

</body>
</html>
