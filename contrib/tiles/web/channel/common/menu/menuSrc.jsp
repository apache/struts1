<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/common/menuViewSrc.jsp" flush="true" >
  <comp:putList name="list" >
    <comp:add value="/basicPage.jsp" />
    <comp:add value="/portalPage.jsp" />
    <comp:add value="/portal/portalBody.jsp" />
    <comp:add value="/common/header.jsp" />
    <comp:add value="/common/menu.jsp" />
    <comp:add value="/common/footer.jsp" />
    <comp:add value="/layout/classicLayout.jsp" />
    <comp:add value="/layout/vboxLayout.jsp" />
  </comp:putList>
</comp:insert>
