<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/common/submenu.jsp" flush="true">
  <comp:put name="title" value="Main Menu" />
  <comp:putList name="items" >
    <comp:add value="Home" />
    <comp:add value="Basic Page" />
    <comp:add value="First Portal" />
  </comp:putList>
  <comp:putList name="links" >
    <comp:add value="index.jsp" />
    <comp:add value="basicPage.jsp" />
    <comp:add value="portalPage.jsp" />
  </comp:putList>
</comp:insert>
