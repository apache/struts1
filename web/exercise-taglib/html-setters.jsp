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
    <th align="center" colspan="4">Scalar Properties</th align="center">
  </tr>

  <tr>
    <th align="right">booleanProperty</th>
    <td align="left">
      <html:checkbox property="booleanProperty"/>
    </td>
    <th align="right">nested.booleanProperty</th>
    <td align="left">
      <html:checkbox property="nested.booleanProperty"/>
    </td>
  </tr>

  <tr>
    <th align="right">doubleProperty</th>
    <td align="left">
      <html:text property="doubleProperty" size="32"/>
    </td>
    <th align="right">nested.doubleProperty</th>
    <td align="left">
      <html:text property="nested.doubleProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">floatProperty</th>
    <td align="left">
      <html:text property="floatProperty" size="32"/>
    </td>
    <th align="right">nested.floatProperty</th>
    <td align="left">
      <html:text property="nested.floatProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">intProperty</th>
    <td align="left">
      <html:text property="intProperty" size="32"/>
    </td>
    <th align="right">nested.intProperty</th>
    <td align="left">
      <html:text property="nested.intProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">longProperty</th>
    <td align="left">
      <html:text property="longProperty" size="32"/>
    </td>
    <th align="right">nested.longProperty</th>
    <td align="left">
      <html:text property="nested.longProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">stringProperty</th>
    <td align="left">
      <html:text property="stringProperty" size="32"/>
    </td>
    <th align="right">nested.stringProperty</th>
    <td align="left">
      <html:text property="nested.stringProperty" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="center" colspan="4">Indexed Properties</th align="center">
  </tr>

  <tr>
    <th align="right">intIndexed[0]</th>
    <td align="left">
      <html:text property="intIndexed[0]" size="32"/>
    </td>
    <th align="right">nested.intIndexed[0]</th>
    <td align="left">
      <html:text property="nested.intIndexed[0]" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">intIndexed[1]</th>
    <td align="left">
      <html:text property="intIndexed[1]" size="32"/>
    </td>
    <th align="right">nested.intIndexed[1]</th>
    <td align="left">
      <html:text property="nested.intIndexed[1]" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">stringIndexed[0]</th>
    <td align="left">
      <html:text property="stringIndexed[0]" size="32"/>
    </td>
    <th align="right">nested.stringIndexed[0]</th>
    <td align="left">
      <html:text property="nested.stringIndexed[0]" size="32"/>
    </td>
  </tr>

  <tr>
    <th align="right">stringIndexed[1]</th>
    <td align="left">
      <html:text property="stringIndexed[1]" size="32"/>
    </td>
    <th align="right">nested.stringIndexed[1]</th>
    <td align="left">
      <html:text property="nested.stringIndexed[1]" size="32"/>
    </td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td align="right">
      <html:submit>Save</html:submit>
    </td>
    <td align="left">
      <html:reset>Reset</html:reset>
      <html:cancel>Cancel</html:cancel>
    </td>
    <td>&nbsp;</td>
  </tr>

</table>

</html:form>


</html:html>
