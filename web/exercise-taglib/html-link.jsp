<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html:html>
<head>
<title>Test html:link Tag</title>
<%
  String newValue = "New string value";
  pageContext.setAttribute("newValue", newValue);
  java.util.HashMap newValues = new java.util.HashMap();
  newValues.put("floatProperty", "444.0");
  newValues.put("intProperty", "555");
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

<html:form action="html-link.do">
<table border="0" width="100%">

  <tr>
    <th colspan="4" align="center">Current Values</th>
  </tr>

  <tr>
    <th align="right">booleanProperty</th>
    <td align="left">
      <html:checkbox property="booleanProperty"/>
    </td>
    <th align="right">intProperty</th>
    <td align="left">
      <html:text property="intProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">doubleProperty</th>
    <td align="left">
      <html:text property="doubleProperty" size="16"/>
    </td>
    <th align="right">longProperty</th>
    <td align="left">
      <html:text property="longProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th align="right">floatProperty</th>
    <td align="left">
      <html:text property="floatProperty" size="16"/>
    </td>
    <th align="right">stringProperty</th>
    <td align="left">
      <html:text property="stringProperty" size="16"/>
    </td>
  </tr>

  <tr>
    <th colspan="4" align="center">
      Hyperlinks To Be Tested
    </th>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:link page="/html-link.do">
        No modifications at all
      </html:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:link page="/html-link.do?doubleProperty=321.321&amp;longProperty=321321">
        Double and long via hard coded changes
      </html:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:link page="/html-link.do"
              paramId="stringProperty" paramName="newValue">
        String via paramId and paramName
      </html:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:link page="/html-link.do"
              paramId="booleanProperty"
            paramName="testbean" paramProperty="nested.booleanProperty">
        Boolean via paramId, paramName, and paramValue
      </html:link>
    </td>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:link page="/html-link.do"
                 name="newValues">
        Float and int via name (Map)
      </html:link>
    </td>
  </tr>

  <tr>
    <th colspan="4" align="center">Reset and Cancel Buttons</th>
  </tr>

  <tr>
    <td colspan="4" align="center">
      <html:reset>Reset</html:reset>
      <html:cancel>Cancel</html:cancel>
    </td>
  </tr>

</table>

</html:form>


</html:html>
