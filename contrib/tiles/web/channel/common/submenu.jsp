<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ page import="java.util.Iterator" %>


<%-- Push component attributes in page context --%>
<comp:importAttribute />

<table>
<logic:present name="title">
<tr>
  <th colspan=2>
    <div align="left"><strong><bean:write name="title"/></strong></div>
  </th>
</tr>
</logic:present>

<%-- Check if selected exist. --%>
<logic:notPresent name="selected" >
  <% pageContext.setAttribute( "selected", "" ); %>
</logic:notPresent>

<%-- Prepare the links list to be iterated --%>
<bean:define id="links" name="links" type="java.util.List" scope="page" />
<% Iterator i = links.iterator(); %>

<%-- iterate on items list --%>
<%-- Normally, we should write something like this :
   <logic:iterate id="item" name="items" type="java.lang.String" >
   But, Struts doesn't declare the TEI class for iterate, and 
   some web container deal badly with the declared variable. 
   So, we use what follow instead.
    --%>
<logic:iterate id="iterateItem" name="items" >
<bean:define id="item" name="iterateItem" type="java.lang.String" scope="page" />


<tr>
  <td width="10" valign="top" ></td>
  <td valign="top"  >
    <%-- check if selected --%>
	<logic:notEqual name="selected" value="<%=item%>">
	  <% // Compute link value
	    String link = (String)i.next();
	    if(link.startsWith("/") )
		  link = request.getContextPath() + link;
	  %>
	  <font size="-1"><a href="<%=link%>"><%=item%></a></font>
	</logic:notEqual>
	<logic:equal name="selected" value="<%=item%>">
	  <font size="-1" color="fuchsia"><%=item%></font>
  </logic:equal>
  </td>
</tr>
</logic:iterate>

</table>

<%-- Following are some code example using this submenu
<comp:insert page="/common/submenu.jsp" flush="true">
  <comp:put name="title" value="Main Menu" />
  <comp:putList name="items" >
    <comp:add value="Home" />
    <comp:add value="Edit Customer" />
    <comp:add value="Invoice" />
  </comp:putList>
  <comp:putList name="links" >
    <comp:add value="index.jsp" />
    <comp:add value="invoice/index.jsp" />
    <comp:add value="invoice/index.jsp" />
  </comp:putList>
</comp:insert>

<comp:insert definition="mainSubMenu" flush="true">
  <comp:put name="selected" value="Home" />
</comp:insert>
--%>
