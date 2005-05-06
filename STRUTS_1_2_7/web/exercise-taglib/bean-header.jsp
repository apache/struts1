<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld"  prefix="html-el"  %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
<head>
<title>Test Replacements for struts-bean:header Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test Replacements for struts-bean:header Tag</h1>
</div>

Display the values of the headers included in this request.<br><br>

<table border="1">
  <tr>
    <th>Header Name</th>
    <th>Header Value</th>
  </tr>
  <c:forEach items="${pageContext.request.headerNames}" var="name">
	<tr>
	 <td><c:out value="${name}"/></td>
	 <td><c:out value="${header[name]}"/></td>
	</tr>
  </c:forEach>
  <bean:header id="dummy" name="UNKNOWN-HEADER" value="UNKNOWN VALUE"/>
  <tr>
    <td>UNKNOWN HEADER</td>
    <td><c:out value="${dummy}"/></td>
  </tr>
</table>

</body>
</html-el:html>
