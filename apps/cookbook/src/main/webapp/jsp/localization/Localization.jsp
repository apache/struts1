<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html xhtml="true" lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Localization Example</title>
<html:base/>
<link rel="stylesheet" type="text/css" href="../../css/example.css" />
</head>
<body>
<html:link page="/jsp/localization/source.jsp">
  <img src="../../images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link>
<a href="../../index.jsp"><img src="../../images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" /></a>
<h1>Localization Example</h1>
<hr noshade="noshade"/>

<h2><bean:message key="message.welcome"/></h2>

<h4>Change Language</h4>
<ul>
   <li><html:link page="/processLocalization.do">Default</html:link></li>
   <li><html:link page="/processLocalization.do?language=en">English</html:link></li>
   <li><html:link page="/processLocalization.do?language=en&amp;country=CA">English (Canadian)</html:link></li>
   <li><html:link page="/processLocalization.do?language=en&amp;country=US">English (US)</html:link></li>
   <li><html:link page="/processLocalization.do?language=en&amp;country=GB">English (British)</html:link></li>
   <li><html:link page="/processLocalization.do?language=fr">French</html:link></li>
   <li><html:link page="/processLocalization.do?language=de">German</html:link></li>
   <li><html:link page="/processLocalization.do?language=es">Spanish</html:link></li>
   <li><html:link page="/processLocalization.do?language=it" lang="it" dir="ltr">Italian</html:link></li>
   <li><html:link page="/processLocalization.do?language=pt" lang="pt" dir="ltr">Portuguese</html:link></li>
</ul>

<hr />
<p><strong>Notes:</strong> If I've got the translations wrong please feel free to tell me the correct version. I'm a programmer not a linguist, Jim.</p>

</body>
</html:html>
