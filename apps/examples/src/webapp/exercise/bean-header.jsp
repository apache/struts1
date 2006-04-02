<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
  <head>
    <title>Test struts-bean:header Tag</title>
  </head>
  <body>
  <div align="center">
    <h1>Test struts-bean:header Tag</h1>
  </div>Display the values of the headers included in this request.
  <br />
  <br /><%
     java.util.Enumeration names =
       ((HttpServletRequest) request).getHeaderNames();
  %>
  <table border="1">
    <tr>
      <th>Header Name</th>
      <th>Header Value</th>
    </tr><%
      while (names.hasMoreElements()) {
        String name = (String) names.nextElement();
    %>
    <bean:header id="head" name="<%= name %>" />
    <tr>
      <td>
        <%= name %>
      </td>
      <td>
        <%= head %>
      </td>
    </tr><%
      }
    %>
    <bean:header id="dummy" name="UNKNOWN-HEADER" value="UNKNOWN VALUE" />
    <tr>
      <td>UNKNOWN HEADER</td>
      <td>
        <bean:write name="dummy" />
      </td>
    </tr>
  </table></body>
</html>
