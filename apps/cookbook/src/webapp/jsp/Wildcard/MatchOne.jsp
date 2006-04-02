<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html xhtml="true" lang="true">
<head>
    <link rel="stylesheet" type="text/css" href="css/example.css" />
    <title>Wildcard Example Demonstrating Match One</title>
</head>
<body>
<html:link action="SourceWildcard">
    <img src="images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link>
<html:link action="Home">
    <img src="images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" />
</html:link>
<h1>Wildcard Mapping Example Results - Match One</h1>
<p>
    This page displays when the WildCard-Not link is selected,
    even though the link also matches a wildcard mapping.
</p>
<h2>Press back</h2>
</body>
</html:html>