<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html xhtml="true" lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Multibox Results</title>
<html:base/>
<link rel="stylesheet" type="text/css" href="../../css/example.css" />
</head>
<body>
<html:link page="/jsp/multibox/source.jsp">
	   <img src="../../images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link>
<a href="../../index.jsp"><img src="../../images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" /></a>
<h1>Multibox Results</h1>
<hr noshade="noshade"/>

<p><strong>Selected fruits: </strong>
<logic:iterate name="multiboxForm" property="fruits" id="fruit">
	<bean:write name="fruit" />
</logic:iterate>
</p>

<p><strong>Selected colors: </strong>
<logic:iterate name="multiboxForm" property="colors" id="color">
	<bean:write name="color" />
</logic:iterate>
</p>

</body>
</html:html>