<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/layout/vboxLayout.jsp" flush="true" >
  <comp:putList name="componentsList" >
    <comp:add value="/common/menu/menuLogo.jsp" />
    <comp:add value="/common/menu/menuLinks.jsp" />
    <comp:add value="/common/menu/menuSrc.jsp" />
  </comp:putList>
</comp:insert>
