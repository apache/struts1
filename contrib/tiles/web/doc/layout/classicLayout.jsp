<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Layout component 
  parameters : title, header, menu, body, footer 
--%>
<HTML>
  <HEAD>
    <title><comp:getAsString name="title"/></title>
  </HEAD>

<body bgcolor="#ffffff" text="#000000" link="#023264" alink="#023264" vlink="#023264">
<table border="0" width="100%" cellspacing="5">
<tr>
  <td colspan="2"><comp:insert attribute="header" /></td>
</tr>
<tr>
  <td width="140" valign="top">
    <comp:insert attribute='menu'/>
  </td>
  <td valign="top"  align="left">
    <comp:insert attribute='body' />
  </td>
</tr>
<tr>
  <td colspan="2">
    <hr>
  </td>
</tr>
<tr>
  <td colspan="2">
    <comp:insert attribute="footer" />
  </td>
</tr>
</table>
</body>
</html>

