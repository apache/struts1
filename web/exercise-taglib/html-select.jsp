<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title>Test html:select Tag</title>
<%
  String multipleValues[] =
   { "Multiple 0", "Multiple 1", "Multiple 2", "Multiple 3", "Multiple 4",
     "Multiple 5", "Multiple 6", "Multiple 7", "Multiple 8", "Multiple 9" };
  pageContext.setAttribute("multipleValues", multipleValues);
%>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-html Select Tag</h1>
</div>

Whatever changes you make to properties should be reflected when the page
is redisplayed.  Press "Save" to update, or "Cancel" to return to the
main menu.

<html:form action="html-select.do">
<table border="0" width="100%">

  <tr>
    <th align="right">Single Select Allowed:</th>
    <td align="left">
      <html:select property="singleSelect" size="10">
        <html:option value="Single 0">Single 0</html:option>
        <html:option value="Single 1">Single 1</html:option>
        <html:option value="Single 2">Single 2</html:option>
        <html:option value="Single 3">Single 3</html:option>
        <html:option value="Single 4">Single 4</html:option>
        <html:option value="Single 5">Single 5</html:option>
        <html:option value="Single 6">Single 6</html:option>
        <html:option value="Single 7">Single 7</html:option>
        <html:option value="Single 8">Single 8</html:option>
        <html:option value="Single 9">Single 9</html:option>
      </html:select>
    </td>
  </tr>

  <tr>
    <th align="right">Multiple Select Allowed:</th>
    <td align="left">
      <html:select property="multipleSelect" size="10" multiple="true">
        <html:options name="multipleValues" labelName="multipleValues"/>
      </html:select>
    </td>
  </tr>

  <tr>
    <td align="right">
      <html:submit>Save</html:submit>
    </td>
    <td align="left">
      <html:reset>Reset</html:reset>
      <html:cancel>Cancel</html:cancel>
    </td>
  </tr>

</table>

</html:form>


</html:html>
