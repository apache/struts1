<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld"  prefix="html-el"  %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
<head>
<title>Test Replacements for struts-bean:include Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test Replacements for struts-bean:include Tag</h1>
</div>

<c:import url="/index.jsp" var="index"/>

<p>Display the contents returned by invoking <code>/index.jsp</code>
directly, with no filtering.</p>
<hr>
<pre>
<c:out value="${index}" escapeXml="false"/>
</pre>
<hr>

<p>Display the contents returned by invoking <code>/index.jsp</code>
directly, with filtering.</p>
<hr>
<pre>
<c:out value="${index}" escapeXml="true"/>
</pre>
<hr>

</body>
</html-el:html>
