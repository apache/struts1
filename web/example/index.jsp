<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="index.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:notPresent name="database" scope="application">
  <font color="red">
    ERROR:  User database not loaded -- check servlet container logs
    for error messages.
  </font>
  <hr>
</logic:notPresent>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<h3><bean:message key="index.heading"/></h3>
<ul>
<li><html:link page="/editRegistration.do?action=Create"><bean:message key="index.registration"/></html:link></li>
<li><html:link page="/logon.jsp"><bean:message key="index.logon"/></html:link></li>
</ul>

<p>&nbsp;</p>
<p><a target="_blank" href="tour.htm"><font size="1">A Walking Tour of the Example Application</font></a></p>

<html:img page="/struts-power.gif" alt="Powered by Struts"/>

</body>
</html:html>
