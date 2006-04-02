<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
  <head>
    <title>Test struts-bean:resource Tag</title>
  </head>
  <body>
    <div align="center">
      <h1>Test struts-bean:resource Tag</h1>
    </div>
    <bean:resource id="webxml" name="/WEB-INF/web.xml" />
    <p>Display the contents of the 
    <code>WEB-INF/web.xml</code>resource for this web application, with no filtering.</p>
    <hr />
    <pre>
<%= webxml %>
</pre>
    <hr />
    <p>Display the contents of the 
    <code>WEB-INF/web.xml</code>resource for this web application, with filtering.</p>
    <hr />
    <pre>
<bean:write name="webxml" filter="true" />
</pre>
    <hr />
  </body>
</html>
