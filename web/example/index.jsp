<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>

<html>
<head>
<title><struts:message key="index.title"/></title>
</head>
<body bgcolor="white">
<h3><struts:message key="index.heading"/></h3>
<ul>
<li><struts:link href="registration.jsp?action=Create"><struts:message key="index.registration"/></struts:link>
<li><struts:link href="logon.jsp"><struts:message key="index.logon"/></struts:link>
</ul>

</body>
</html>
