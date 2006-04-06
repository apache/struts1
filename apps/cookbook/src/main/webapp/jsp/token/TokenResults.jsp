<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html xhtml="true" lang="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Token Example Results</title>
<html:base/>
<link rel="stylesheet" type="text/css" href="../../css/example.css" />
</head>
<body>
<html:link page="/jsp/token/source.jsp">
	   <img src="../../images/code.gif" width="24" height="24" alt="View Source" class="icon" />
</html:link>
<a href="../../index.jsp"><img src="../../images/return.gif" height="24" width="24" alt="Return to examples page" class="icon" /></a>
<h1>Token Example Results</h1>
<hr noshade="noshade"/>

<p>The form has been submitted.</p>
<p>You can cause a duplicate submission by either: 
<ol>
<li>Using your browser back button to return to the previous page and resubmitting the form.</li>
<li>Refeshing this page and selecting OK when your browser asks if you want to resubmit the data.</li>
</ol>
In either case, the input form page will be displayed, along with an error message explaining that the form has been submitted out of sequence.
</p>

<p><strong>Important:</strong> This example demonstrates how you can prevent <em>unintentional</em> form resubmission. 
The token is reset before the input form is redisplayed. This means that once the user has been notified of the attempted
duplicate submission they can then <em>choose</em> whether to resubmit the form.</p>

</body>
</html:html>