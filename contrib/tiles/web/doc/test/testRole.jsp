<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Test tags basic behaviors 
--%>
<hr>
<strong>Show Component if 'tomcat' role is set</strong>
<br>
<comp:insert template="layout.jsp" role="tomcat">
  <comp:put name="title"  value="Tomcat role" />
  <comp:put name="header" value="header.jsp" />
  <comp:put name="body"   value="body.jsp" />
</comp:insert>

<hr>
<strong>Show Component if 'role1' role is set</strong>
<br>
<comp:insert template="layout.jsp" role="role1">
  <comp:put name="title"  value="role1 role" />
  <comp:put name="header" value="header.jsp" />
  <comp:put name="body"   value="body.jsp" />
</comp:insert>

