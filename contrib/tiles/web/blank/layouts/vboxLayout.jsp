<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%-- Layout component 
  Render a list of tiles in a vertical column
  @param : list List of names to insert 
  
--%>

<tiles:useAttribute id="list" name="list" classname="java.util.List" />

<%-- Iterate over names.
  We don't use <iterate> tag because it doesn't allow insert (in JSP1.1)
 --%>
<%
Iterator i=list.iterator();
while( i.hasNext() )
  {
  String name= (String)i.next();
%>
<tiles:insert name="<%=name%>" flush="true" />
<br>

<%
  } // end loop
%>

