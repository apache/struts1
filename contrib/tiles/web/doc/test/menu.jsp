<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ page import="java.util.Iterator" %>

<%-- 
--%>

<%-- Push component attributes in page context --%>
<comp:importAttribute />

<%-- Prepare the links list to be iterated --%>
<bean:define id="links" name="links" type="java.util.List" scope="page" />
<% Iterator i = links.iterator(); %>

<%-- Iterate on list of items
    --%>
<table>
<logic:iterate id="item" name="items" >

<tr>
  <td width="10" valign="top" ></td>
  <td valign="top"  >
	  <% // Compute link value
	    String link = i.next().toString();
	    if(link.startsWith("/") )
		  link = request.getContextPath() + link;
	  %>
	  <font size="-1"><a href="<%=link%>"><bean:write name="item" filter="false"/></a></font>
  </td>
</tr>
</logic:iterate>
</table>
