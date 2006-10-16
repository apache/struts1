<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html xhtml="true" lang="true">
<head>
    <link rel="stylesheet" type="text/css" href="css/example.css" />
    <title>Wildcard Mappings Example</title>
</head>
<body>
<p>
<html:link action="SourceWildcard">
  <img src="images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link>
<html:link action="Home">
    <img src="images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" />
</html:link>
</p>
<h1>Wildcard Example</h1>
<hr noshade="noshade"/>
<h2>Select a link. </h2>
<ul>
    <li>
        <html:link action="Wildcard-Any">
            This link demonstrates the Wildcard mapping feature.
        </html:link>
    </li>
    <li>
        <html:link action="Wildcard-Not">
            This link demonstrates that a specific mapping will override a
            less-specific Wildcard.
        </html:link>
    </li>
</ul>
</body>
</html:html>