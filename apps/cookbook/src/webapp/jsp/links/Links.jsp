<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html xhtml="true" lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Link Examples</title>
<html:base />
<link rel="stylesheet" type="text/css" href="../../css/example.css" />
</head>
<body>

<html:link page="/jsp/links/source.jsp">
  <img src="../../images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link> 
<a href="../../index.jsp"><img src="../../images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" /></a>
<h1>Link Examples</h1>
<hr noshade="noshade"/>

<h2>Simple Links</h2>
<p>If you view this page with cookies disabled, you will see that the struts tags append the session id to the link URL.<br />
 Observe that the HTML &lt;a&gt; tag does not have the URL appended.</p>
<a href="../../processLinks.do">Link using HTML &lt;a&gt; tag</a><br />
<%-- This causes an exception on Tomcat 4.06 and 4.1.24. It works on Tomcat 5 and WebSphere 5. 
     I think we can assume a bug inTomcat 4.x.
	<html:link href="../../processLinks.do">Link using href attribute</html:link><br />
--%>
<html:link page="/processLinks.do">Link using page attribute</html:link><br />
<html:link action="/processLinks">Link using action attribute</html:link><br />
<html:link action="/processLinks" anchor="test">Link to an anchor</html:link><br />

<h2>Links with Parameters</h2>
<html:link action="/processLinks?color=blue">Hardcoded parameter</html:link><br />
<bean:define id="myColor" value="red"/>
<html:link action="/processLinks" paramId="color" paramName="myColor">Dynamic parameter</html:link><br />
<html:link action="/processLinks" paramId="msg" paramName="testBean" paramProperty="stringValue">Dynamic parameter from a bean property</html:link><br />
<html:link action="/processLinks" name="parms">Multiple dynamic parameters from a Map</html:link><br />

</body>
</html:html>