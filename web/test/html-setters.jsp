<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title>Test struts-html Property Setters</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-html Property Setters</h1>
</div>

Whatever changes you make to properties should be reflected when the page
is redisplayed.  Press "Save" to update, or "Cancel" to return to the
main menu.

<html:form action="html-setters.do">
<table border="0" width="100%">

  <tr>
    <th align="right">booleanProperty</th>
    <td align="left">
      <html:checkbox property="booleanProperty"/>
    </td>
  </tr>

  <tr>
    <th align="right">doubleProperty</th>
    <td align="left">
      <html:text property="doubleProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">floatProperty</th>
    <td align="left">
      <html:text property="floatProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">intProperty</th>
    <td align="left">
      <html:text property="intProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">longProperty</th>
    <td align="left">
      <html:text property="longProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">stringProperty</th>
    <td align="left">
      <html:text property="stringProperty" size="32"/>
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
