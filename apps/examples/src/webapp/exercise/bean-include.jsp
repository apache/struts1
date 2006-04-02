<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
  <head>
    <title>Test struts-bean:include Tag</title>
  </head>
  <body>
    <div align="center">
      <h1>Test struts-bean:include Tag</h1>
    </div>
    <bean:include id="welcome" page="/welcome.jsp" />
    <p>Display the contents returned by invoking
    <code>/welcome.html</code>directly, with no filtering.</p>
    <hr />
    <pre>
<%= welcome %>
</pre>
    <hr />
    <p>Display the contents returned by invoking 
    <code>/welcome.html</code>directly, with filtering.</p>
    <hr />
    <pre>
<bean:write name="welcome" filter="true" />
</pre>
    <hr />
  </body>
</html>
