<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Source Code for Wildcard Mappings Example</title>
<html:xhtml/>
<link rel="stylesheet" type="text/css" href="css/example.css" />
</head>
<body>
<html:link action="/PrepareWildcard">
   <img src="images/execute.gif" width="24" height="24" alt="Execute example" class="icon" />
</html:link>
<html:link action="/Home">
   <img src="images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" />
</html:link>
<h1>Source Code for Wildcard Example</h1>
<hr noshade="noshade"/>

<h2>JavaServer Pages</h2>
<p>
    <html:link page="/source.jsp?src=/jsp/Wildcard/MatchAny.jsp">
        MatchAny.jsp
    </html:link>
</p>
<p>
    <html:link page="/source.jsp?src=/jsp/Wildcard/MatchOne.jsp">
        MatchOne.jsp
    </html:link>
</p>

<h2>Configuration file</h2>
<p>
    <html:link page="/source.jsp?src=/WEB-INF/struts-config-Wildcard.xml">
        struts-config-Wildcard.xml
    </html:link>
</p>

</body>
</html>