<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ page import="java.util.Iterator" %>


<%-- Menu Layout
  This layout render a menu with links.
  This implementation doesn't use any Struts tags. If you also use Struts, please check
  menu.jsp implementation.
  It takes as parameter the title, and a list of items. Each item is a bean with following properties :
  value, href, icon, tooltip.
  @param title Menu title
  @param items list of items. Items are beans whith following properties : 
 --%>



<%-- Push tiles attributes in page context --%>
<tiles:importAttribute />
<% // Make Tile context available for conditional test
  //ComponentContext tileContext = RequestU 
%>
<table>
<logic:present name="title">
<% if( pageContext.getAttribute("title")!=null) { %>
<tr>
  <th colspan=2>
    <div align="left"><strong><tiles:getAsString name="title"/></strong></div>
  </th>
</tr>
</logic:present>
<% } // end if %>

<%-- iterate on items list --%>
<%
  java.util.List list = (java.util.List)pageContext.getAttribute("items");
  java.util.Iterator iterator = list.iterator();
  while( iterator.hasNext() )
    {
	org.apache.struts.tiles.beans.MenuItem item = (org.apache.struts.tiles.beans.MenuItem)iterator.next();
%>
<logic:iterate id="item" name="items" type="org.apache.struts.tiles.beans.MenuItem" >

<%  // Add site url if link start with "/"
  String link = item.getLink();
	if(link.startsWith("/") ) link = request.getContextPath() + link;
%>
<tr>
  <td width="10" valign="top" ></td>
  <td valign="top"  >
	  <font size="-1"><a href="<%=link%>">
<% if( item.getIcon()==null) { %>
  <%=item.getValue()%>
<% } else { %>  
	<%  // Add site url if link start with "/"
	  String icon = item.getIcon();
		if(icon.startsWith("/") ) icon = request.getContextPath() + icon;
	%>
<img src='<%=request.getContextPath()+item.getIcon()%>'
       alt='<%=request.getContextPath()+item.getTooltip()%>' />
<% } // end if %>
	   </a>
	  </font>
  </td>
</tr>

<% // end iterate
  } // end loop
%>
</table>
