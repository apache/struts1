<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

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
    <TD colspan="2">
	  <comp:insert attribute="header" >
	    <comp:put name="body" beanName="body" beanScope="template" />
	  </comp:insert>
	</TD></TR>
  <TR>
    <TD width="120"><comp:insert attribute="menu" /></TD>
    <TD>
	  <comp:useAttribute name="body" classname="java.lang.String"/>
	  <bean:insert id="bodyStr" page="<%=body%>" />
	  <bean:write name="bodyStr" filter="false"/>
	</TD></TR>
  <TR>
    <TD colspan="2"><comp:insert attribute="footer" /></TD>
  </TR>
</TABLE>

</body>
</html>