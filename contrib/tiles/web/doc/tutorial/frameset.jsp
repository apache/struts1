<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<%-- Layout component 
  parameters : title, header, menu, body, footer 
--%>

<html>
<head>
    <title><comp:getAsString name="title"/></title>
</head>

<body>
<frameset rows="3">
  <frame src="<comp:get name="header" />" name="header" id="header" scrolling="Auto">
  <frame src="<comp:get name="body" />" name="body" id="header" scrolling="Auto">
  <frame src="<comp:get name="footer" />" name="footer" id="header" scrolling="Auto">
</frameset>
</body>


</html>