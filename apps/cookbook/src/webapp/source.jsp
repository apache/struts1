<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View Source</title>
<html:xhtml/>
<html:base/>
<link rel="stylesheet" type="text/css" href="css/example.css" />
</head>
<body>
<bean:parameter name="src" id="srcfile" />
<bean:resource name="<%= srcfile %>" id="src" />
<p><strong>Viewing: </strong><bean:write name="srcfile"/></p>
<hr noshade="noshade" />
<pre>
<bean:write name="src" filter="true"/>
</pre>
</body>
</html>
