<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"     %>


<P><img src="<%=request.getContextPath()%>/images/id_nav_outside.gif" align="left" border="0">
<img src="<%=request.getContextPath()%>/images/id_nav_bkgnd.gif" align="right" border="0"> </P>

value="<tiles:getAsString name="body"/>"
