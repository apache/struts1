<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"     %>


<P><img src="<%=request.getContextPath()%>/images/id_nav_outside.gif" align="left" border="0">
<img src="<%=request.getContextPath()%>/images/id_nav_bkgnd.gif" align="right" border="0"> </P>

value="<comp:getAsString name="body"/>"