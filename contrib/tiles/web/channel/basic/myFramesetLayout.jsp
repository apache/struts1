<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>

<%-- Frameset Layout component 
  parameters : title, header, menu, body, footer 
--%>

<html>
<head>
    <title><template:get name="title"/></title>
</head>

<frameset rows="73, *, 73">
  <frame src="<%=request.getContextPath()%><template:get name="header" />" name="header" >
  <frame src="<%=request.getContextPath()%><template:get name="body" />" name="body" >
  <frame src="<%=request.getContextPath()%><template:get name="footer" />" name="footer" >
</frameset>


</html>