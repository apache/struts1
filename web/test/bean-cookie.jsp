<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
<title>Test struts-bean:cookie Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-bean:cookie Tag</h1>
</div>

Display the properties of our current session ID cookie

<bean:cookie id="sess" name="JSESSIONID"/>

<table border="1">
  <tr>
    <th>Property Name</th>
    <th>Correct Value</th>
    <th>Test Result</th>
  </tr>
  <tr>
    <td>comment</td>
    <td><jsp:getProperty name="sess" property="comment"/></td>
    <td><bean:write name="sess" property="comment"/></td>
  </tr>
  <tr>
    <td>domain</td>
    <td><jsp:getProperty name="sess" property="domain"/></td>
    <td><bean:write name="sess" property="domain"/></td>
  </tr>
  <tr>
    <td>maxAge</td>
    <td><jsp:getProperty name="sess" property="maxAge"/></td>
    <td><bean:write name="sess" property="maxAge"/></td>
  </tr>
  <tr>
    <td>name</td>
    <td><jsp:getProperty name="sess" property="name"/></td>
    <td><bean:write name="sess" property="name"/></td>
  </tr>
  <tr>
    <td>path</td>
    <td><jsp:getProperty name="sess" property="path"/></td>
    <td><bean:write name="sess" property="path"/></td>
  </tr>
  <tr>
    <td>secure</td>
    <td><jsp:getProperty name="sess" property="secure"/></td>
    <td><bean:write name="sess" property="secure"/></td>
  </tr>
  <tr>
    <td>value</td>
    <td><jsp:getProperty name="sess" property="value"/></td>
    <td><bean:write name="sess" property="value"/></td>
  </tr>
  <tr>
    <td>version</td>
    <td><jsp:getProperty name="sess" property="version"/></td>
    <td><bean:write name="sess" property="version"/></td>
  </tr>
</table>

</body>
</html>
