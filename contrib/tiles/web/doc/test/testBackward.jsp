<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Test backward compatibility
--%>

<hr>
<strong>Check synonyms for include and put.</strong>
<br>
<comp:insert component="layout.jsp">
  <comp:put name="title"  value="Test with a tag body" direct="true" />
  <comp:put name="header" value="header.jsp">
    <strong>This is a header</strong>
  </comp:put>
  <comp:put name="body"   value="body.jsp" direct="false" />
</comp:insert>
