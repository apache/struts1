<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%-- Menu of sources component 
  This component is a menu of sources. When a source is clicked, its 
  code is shown using a viewer.
  A list of absolute source path must be provided.
  Viewer path can be provided.
  @param list List of source paths to display
  @param viewerPath Path of viewer page. Optional.
--%>

<comp:importAttribute />

  <%-- Set default viewer if not specified --%>
<logic:notPresent name="viewerPath" >
  <% pageContext.setAttribute( "viewerPath", request.getContextPath()+"/common/viewSrc.jsp" ); %>
</logic:notPresent>
<bean:define id="viewerPath" name="viewerPath" type="java.lang.String" />

<table>
<tr><th colspan=2><div align="left"><strong>JSP Sources</strong></div></th></tr>

<%-- Iterate on sources list --%>
<%-- Normally, we should write something like this :
   <logic:iterate id="item" name="items" type="java.lang.String" >
   But, Struts doesn't declare the TEI class for iterate, and 
   some web container deal badly with the declared variable. 
   So, we use what follow instead.
    --%>
<logic:iterate id="iterateItem" name="list" >
<bean:define id="srcPath" name="iterateItem" type="java.lang.String" scope="page" />

<tr>
  <td width="10" align="center"></td>
  <td  width="120">
    <font size="-2">
      <a href="<%=viewerPath%>?src=<%=srcPath%>"><%=srcPath%></a>
    </font>
  </td>
</tr>

</logic:iterate>
</table>
