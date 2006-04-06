<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
  <head>
    <title>Test struts-bean:parameter Tag</title>
  </head>
  <body>
    <div align="center">
      <h1>Test struts-bean:parameter Tag</h1>
    </div>
    <p>If called from the 
    <code>index.html</code>page, two request parameters will be included and their values displayed below. If you call this page without including the appropriate request parameters, you will receive a JSP runtime error instead.</p>
    <bean:parameter id="param1" name="param1" />
    <bean:parameter id="param2" name="param2" />
    <bean:parameter id="param3" name="param3" value="UNKNOWN VALUE" />
    <table border="1">
      <tr>
        <th>Parameter Name</th>
        <th>Correct Value</th>
        <th>Test Result</th>
      </tr>
      <tr>
        <td>param1</td>
        <td>value1</td>
        <td>
          <%= param1 %>
        </td>
      </tr>
      <tr>
        <td>param2</td>
        <td>value2</td>
        <td>
          <%= param2 %>
        </td>
      </tr>
      <tr>
        <td>param3</td>
        <td>UNKNOWN VALUE</td>
        <td>
          <%= param3 %>
        </td>
      </tr>
    </table>
  </body>
</html>
