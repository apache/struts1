<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<app:checkLogon/>
<jsp:useBean id="user" scope="session" type="org.apache.struts.example.User"/>

<html:html>
<head>
<title><bean:message key="mainMenu.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<h3><bean:message key="mainMenu.heading"/>
<jsp:getProperty name="user" property="username"/></h3>
<ul>
<li><html:link page="/editRegistration.do?action=Edit"><bean:message key="mainMenu.registration"/></html:link></li>
<li><html:link page="/logoff.do"><bean:message key="mainMenu.logoff"/></html:link></li>
</ul>

</body>
</html:html>
