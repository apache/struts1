<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Layout component 
  Render a list on severals columns
  parameters : componentsList 
--%>

<comp:useAttribute id="list" name="componentsList" classname="java.util.List" />

<%-- Next will be a tag, as soon as tags allow include in iteration
  Wait until jsp1.3 --%>
<%
Iterator i=list.iterator();
while( i.hasNext() )
  {
  String comp=(String)i.next();
%>
<comp:insert name="<%=comp%>" flush="true" />
<br>

<%
  } // end loop
%>

