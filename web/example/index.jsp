<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts.tld" prefix="struts" %>

<html>
<head>
<title><struts:message key="index.title"/></title>
</head>
<body bgcolor="white">

<struts:ifAttributeMissing name="database" scope="application">
  <font color="red">
    ERROR:  User database not loaded -- check servlet container logs
    for error messages.
  </font>
  <hr>
</struts:ifAttributeMissing>

<struts:ifAttributeMissing name="org.apache.struts.action.MESSAGE"
 scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</struts:ifAttributeMissing>

<h3><struts:message key="index.heading"/></h3>
<ul>
<li><struts:link href="editRegistration.do?action=Create"><struts:message key="index.registration"/></struts:link>
<li><struts:link href="logon.jsp"><struts:message key="index.logon"/></struts:link>
</ul>

</body>
</html>
