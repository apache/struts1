<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title>Test html:multibox Tag</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-html Property Setters</h1>
</div>

Whatever changes you make to properties should be reflected when the page
is redisplayed.  When first started, all of the listed checkboxes should
be selected.  Press "Save" to update, or "Cancel" to return to the
main menu.

<html:form action="html-multibox.do">
<table border="0" width="100%">

  <tr>
    <th align="center" colspan="4">String Array Values</th>
  </tr>

  <tr>
    <th align="right">String 0</th>
    <td align="left">
      <html:multibox property="stringArray" value="String 0"/>
    </td>
    <th align="right">(nested) String 0</th>
    <td align="left">
      <html:multibox property="nested.stringArray" value="String 0"/>
    </td>
  </tr>

  <tr>
    <th align="right">String 1</th>
    <td align="left">
      <html:multibox property="stringArray" value="String 1"/>
    </td>
    <th align="right">(nested) String 1</th>
    <td align="left">
      <html:multibox property="nested.stringArray" value="String 1"/>
    </td>
  </tr>

  <tr>
    <th align="right">String 2</th>
    <td align="left">
      <html:multibox property="stringArray" value="String 2"/>
    </td>
    <th align="right">(nested) String 2</th>
    <td align="left">
      <html:multibox property="nested.stringArray" value="String 2"/>
    </td>
  </tr>

  <tr>
    <th align="right">String 3</th>
    <td align="left">
      <html:multibox property="stringArray" value="String 3"/>
    </td>
    <th align="right">(nested) String 3</th>
    <td align="left">
      <html:multibox property="nested.stringArray" value="String 3"/>
    </td>
  </tr>

  <tr>
    <th align="right">String 4</th>
    <td align="left">
      <html:multibox property="stringArray" value="String 4"/>
    </td>
    <th align="right">(nested) String 4</th>
    <td align="left">
      <html:multibox property="nested.stringArray" value="String 4"/>
    </td>
  </tr>

  <tr>
    <th align="center" colspan="4">Integer Array Values</th>
  </tr>

  <tr>
    <th align="right">0</th>
    <td align="left">
      <html:multibox property="intArray" value="0"/>
    </td>
    <th align="right">(nested) 0</th>
    <td align="left">
      <html:multibox property="nested.intArray" value="0"/>
    </td>
  </tr>

  <tr>
    <th align="right">10</th>
    <td align="left">
      <html:multibox property="intArray" value="10"/>
    </td>
    <th align="right">(nested) 10</th>
    <td align="left">
      <html:multibox property="nested.intArray" value="10"/>
    </td>
  </tr>

  <tr>
    <th align="right">20</th>
    <td align="left">
      <html:multibox property="intArray" value="20"/>
    </td>
    <th align="right">(nested) 20</th>
    <td align="left">
      <html:multibox property="nested.intArray" value="20"/>
    </td>
  </tr>

  <tr>
    <th align="right">30</th>
    <td align="left">
      <html:multibox property="intArray" value="30"/>
    </td>
    <th align="right">(nested) 30</th>
    <td align="left">
      <html:multibox property="nested.intArray" value="30"/>
    </td>
  </tr>

  <tr>
    <th align="right">40</th>
    <td align="left">
      <html:multibox property="intArray" value="40"/>
    </td>
    <th align="right">(nested) 40</th>
    <td align="left">
      <html:multibox property="nested.intArray" value="40"/>
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
