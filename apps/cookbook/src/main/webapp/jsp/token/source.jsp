<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Source Code for Token Example</title>
<html:xhtml/>
<html:base/>
<link rel="stylesheet" type="text/css" href="../../css/example.css" />
</head>
<body>
<html:link action="/processToken">
	   <img src="../../images/execute.gif" width="24" height="24" alt="Execute example" class="icon" />
</html:link>
<a href="../../index.jsp"><img src="../../images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" /></a>
<h1>Source Code for Token Example</h1>
<hr noshade="noshade"/>

<h2>JaveServer Pages</h2>
<p><html:link page="/source.jsp?src=/jsp/token/Token.jsp">Token.jsp</html:link></p>
<p><html:link page="/source.jsp?src=/jsp/token/TokenResults.jsp">TokenResults.jsp</html:link></p>

<h2>Actions</h2>
<p><html:link page="/source.jsp?src=/WEB-INF/src/java/examples/token/PrepareTokenAction.java">PrepareTokenAction.java</html:link></p>
<p><html:link page="/source.jsp?src=/WEB-INF/src/java/examples/token/ProcessTokenAction.java">ProcessTokenAction.java</html:link></p>

<h2>ActionForm</h2>
<p>Configured in struts-config.xml</p>

<h2>Configuration Files</h2>
<p><html:link page="/source.jsp?src=/WEB-INF/struts-config.xml">struts-config.xml</html:link></p>

<h2>Other source files</h2>
<p>None</p>

</body>
</html>