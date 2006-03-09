<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld"  prefix="html-el"  %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
<head>
<title>Test struts bean-el Resource Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts bean-el Resource Tag</h1>
</div>

<bean-el:resource id="webxml" name="/WEB-INF/web.xml"/>

<p>Display the contents of the <code>/WEB-INF/web.xml</code> resource for this
web application, with no filtering.</p>
<hr>
<pre>
<c:out value="${webxml}" escapeXml="false"/>
</pre>
<hr>

<p>Display the contents of the <code>/WEB-INF/web.xml</code> resource for this
web application, with filtering.</p>
<hr>
<pre>
<c:out value="${webxml}" escapeXml="true"/>
</pre>
<hr>

</body>
</html-el:html>
