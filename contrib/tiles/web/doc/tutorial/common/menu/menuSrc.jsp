<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/common/menuViewSrc.jsp" flush="true" >
  <comp:putList name="list" >
    <comp:add value="/tutorial/basicPage.jsp" />
    <comp:add value="/tutorial/portalPage.jsp" />
    <comp:add value="/tutorial/portal/portalBody.jsp" />
    <comp:add value="/tutorial/common/header.jsp" />
    <comp:add value="/tutorial/common/menu.jsp" />
    <comp:add value="/tutorial/common/footer.jsp" />
    <comp:add value="/layout/classicLayout.jsp" />
    <comp:add value="/layout/vboxLayout.jsp" />
  </comp:putList>
</comp:insert>
