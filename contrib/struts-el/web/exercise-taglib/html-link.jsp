<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
<head>
<title>Test html-el:link Tag</title>
<%
  String newValue = "New string value";
  pageContext.setAttribute("newValue", newValue);
  java.util.HashMap newValues = new java.util.HashMap();
  newValues.put("floatProperty", new Float(444.0));
  newValues.put("intProperty", new Integer(555));
  newValues.put("stringArray", new String[]
   { "Value 1", "Value 2", "Value 3" });
  pageContext.setAttribute("newValues", newValues);
%>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-html Link Tag</h1>
</div>

The following links should hyperlink back to this page, with various
combinations of request parameters used to modify the previous values of
the associated form bean.  Press the "Cancel" button to return to the
main menu.

<html-el:form action="html-link.do">
<table border="0" width="100%">

  <tr>
    <th colspan="4" align="center">Current Values</th>
  </tr>

  <tr>
    <th align="right">booleanProperty</th>
    <td align="left">
      <html-el:checkbox property="booleanProperty"/>
    </td>
    <th align="right">intProperty</th>
    <td align="left">
      <html-el:text property="intProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">doubleProperty</th>
    <td align="left">
      <html-el:text property="doubleProperty" size="16"/>
    </td>
    <th align="right">longProperty</th>
    <td align="left">
      <html-el:text property="longProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">floatProperty</th>
    <td align="left">
      <html-el:text property="floatProperty" size="16"/>
    </td>
    <th align="right">stringProperty</th>
    <td align="left">
      <html-el:text property="stringProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">stringArray</th>
    <td align="left" colspan="3">
      <html-el:text property="stringArray[0]" size="16"/>
      <html-el:text property="stringArray[1]" size="16"/>
    </td>
  </tr>

  <tr>
    <th colspan="4" align="center">
      Hyperlinks To Be Tested
    </th>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:link page="/html-link.do">
        No modifications at all
      </html-el:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:link page="/html-link.do?doubleProperty=321.321&amp;longProperty=321321">
        Double and long via hard coded changes
      </html-el:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:link page="/html-link.do"
              paramId="stringProperty" paramName="newValue">
        String via paramId and paramName
      </html-el:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:link page="/html-link.do"
              paramId="booleanProperty"
            paramName="testbean" paramProperty="nested.booleanProperty">
        Boolean via paramId, paramName, and paramValue
      </html-el:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:link page="/html-link.do"
                 name="newValues">
        Float, int, and stringArray via name (Map)
      </html-el:link>
    </td>
  </tr>

  <tr>
    <th colspan="4" align="center">Reset and Cancel Buttons</th>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html-el:reset>Reset</html-el:reset>
      <html-el:cancel>Cancel</html-el:cancel>
    </td>
  </tr>

</table>

</html-el:form>


</html-el:html>
