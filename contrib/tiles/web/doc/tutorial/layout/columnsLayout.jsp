<%@ page import="org.apache.struts.tiles.ComponentContext"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Layout component 
  Render a list in severals columns
  parameters : numCols, list0, list1, list2, list3, ... 
--%>

<comp:useAttribute id="numColsStr" name="numCols" classname="java.lang.String" />


<table>
<tr>
<%
int numCols = Integer.parseInt(numColsStr);
ComponentContext context = ComponentContext.getContext( request );
for( int i=0; i<numCols; i++ )
  {
  java.util.List list=(java.util.List)context.getAttribute( "list" + i );
  pageContext.setAttribute("list", list );
  if(list==null)
    System.out.println( "list is null for " + i  );
%>
<td valign="top">
  <comp:insert page="/layout/vboxLayout.jsp" flush="true" >
    <comp:put name="componentsList" beanName="list" beanScope="page" />
  </comp:insert>
</td>
<%
  } // end loop
%>
</tr>
</table>






