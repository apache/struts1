<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
<title>Test struts-bean:include Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-bean:include Tag</h1>
</div>

<bean:include id="index" page="/index.jsp"/>

<p>Display the contents returned by invoking <code>/index.jsp</code>
directly, with no filtering.</p>
<hr>
<pre>
<%= index %>
</pre>
<hr>

<p>Display the contents returned by invoking <code>/index.jsp</code>
directly, with filtering.</p>
<hr>
<pre>
<bean:write name="index" filter="true"/>
</pre>
</hr>

</body>
</html>
