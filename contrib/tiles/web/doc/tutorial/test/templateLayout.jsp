<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%-- Layout component 
  parameters : title, header, menu, body, footer 
--%>

<html>
<head>
    <title><comp:get name="title"/></title>
</head>

<body>
<TABLE width="100%">
  
  <TR>
    <TD colspan="2"><comp:get name="header" /></TD></TR>
  <TR>
    <TD width="120"><comp:get name="menu" /></TD>
    <TD>
	  **<comp:insert name="body" flush='true'/>**
	</TD></TR>
  <TR>
    <TD colspan="2"><comp:get name="footer" /></TD>
  </TR>
</TABLE>

</body>
</html>