<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<title>Test struts-logic Emptiness Tags</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-logic Emptiness Tags</h1>
</div>

<jsp:useBean id="bean" scope="page" class="org.apache.struts.webapp.exercise.TestBean"/>

<table border="1">
  <tr>
    <th>Test Type</th>
    <th>Correct Value</th>
    <th>Test Result</th>
  </tr>
  <tr>
    <td>null</td>
    <td>empty</td>
    <td>
      <logic:empty name="bean" property="nullProperty">
        empty
      </logic:empty>
      <logic:notEmpty name="bean" property="nullProperty">
        notEmpty
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <td>empty string</td>
    <td>empty</td>
    <td>
      <logic:empty name="bean" property="emptyStringProperty">
        empty
      </logic:empty>
      <logic:notEmpty name="bean" property="emptyStringProperty">
        notEmpty
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <td>non-empty string</td>
    <td>notEmpty</td>
    <td>
      <logic:empty name="bean" property="stringProperty">
        empty
      </logic:empty>
      <logic:notEmpty name="bean" property="stringProperty">
        notEmpty
      </logic:notEmpty>
    </td>
  </tr>
  <tr>
    <td>non-string object</td>
    <td>notEmpty</td>
    <td>
      <logic:empty name="bean" property="intProperty">
        empty
      </logic:empty>
      <logic:notEmpty name="bean" property="intProperty">
        notEmpty
      </logic:notEmpty>
    </td>
  </tr>
</table>

</body>
</html>
