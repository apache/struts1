<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<strong>Example of list usage</strong>
<%-- Insert a menu component.
  Menu component require two lists : one of items, and one of links.
--%>
<comp:insert component="menu.jsp" >
  <comp:put name="title" value="Test menu" />
  <comp:putList name="items">
    <comp:add value="home" />
    <comp:add>
	  <img src="<%=request.getContextPath()%>/images/struts-power.gif"
	       align="right" border="0"></comp:add>
    <comp:add value="documentation"/>
  </comp:putList>
  <comp:putList name="links">
    <comp:add value="/index.jsp"/>
    <comp:add value="/../struts-documentation"/>
    <comp:add value="/../comps-doc" type="string" />
  </comp:putList>
</comp:insert>