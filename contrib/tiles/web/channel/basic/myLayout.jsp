<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Layout component 
  parameters : title, header, menu, body, footer 
--%>

<html>
<head>
    <title><comp:getAsString name="title"/></title>
</head>

<body>
<TABLE width="100%">
  
  <TR>
    <TD colspan="2"><comp:insert attribute="header" /></TD></TR>
  <TR>
    <TD width="120"><comp:insert attribute="menu" /></TD>
    <TD><comp:insert attribute="body" /></TD></TR>
  <TR>
    <TD colspan="2"><comp:insert attribute="footer" /></TD>
  </TR>
</TABLE>

</body>
</html>