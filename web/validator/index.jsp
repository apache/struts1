<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.struts.validator.ValidatorPlugIn" session="true" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html locale="true">
<head>
<title><bean:message key="index.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<logic:notPresent name="<%= ValidatorPlugIn.VALIDATOR_KEY %>" scope="application">
  <font color="red">
    ERROR:  Validator resources not loaded -- check Commons Logging
    logs for error messages.
  </font>
</logic:notPresent>

<h3><bean:message key="registrationForm.title"/></h3>
<ul>
   <li><html:link page="/registration.jsp"><bean:message key="registrationForm.title"/></html:link></li>
   <li>
      <html:link page="/jsRegistration.jsp"><bean:message key="jsRegistrationForm.title"/></html:link> - 
      <bean:message key="jsRegistrationForm.description"/>
   </li>
   <li>
      <html:link page="/multiRegistration1.jsp"><bean:message key="multiRegistrationForm.title"/></html:link> - 
      <bean:message key="multiRegistrationForm.description"/>
   </li>
</ul>

<p>&nbsp;</p>

<h3><bean:message key="typeForm.title"/></h3>
<ul>
   <li>
      <html:link page="/type.jsp"><bean:message key="typeForm.title"/></html:link> -
      <bean:message key="typeForm.description"/>
   </li>
   <li>
      <html:link page="/editJsType.do"><bean:message key="jsTypeForm.title"/></html:link> -
      <bean:message key="jsTypeForm.description"/>
   </li>
</ul>

<p>&nbsp;</p>

<h3>Change Language | Changez Le Langage</h3>
<ul>
   <li><html:link page="/locale.do?language=en">English | Anglais</html:link></li>
   <li>
      <html:link page="/locale.do?language=fr">French | Francais</html:link> - 
      <bean:message key="localeForm.fr"/>
   </li>
   <li>
      <html:link page="/locale.do?language=fr&country=CA">French Canadian | Francais Canadien</html:link> - 
      <bean:message key="localeForm.frCA"/>
   </li>
   <li>
      <html:link page="/locale.do?language=ja">Japanese | Japonais</html:link> - 
      <bean:message key="localeForm.ja"/>
   </li>
</ul>

<p>&nbsp;</p>

<html:img page="/struts-power.gif" altKey="index.powered"/>

</body>
</html:html>
