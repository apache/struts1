<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/layout/classicLayout.jsp" flush="true">
  <comp:put name="title"  value="My First Portal Page" />
  <comp:put name="header" value="/common/header.jsp" />
  <comp:put name="footer" value="/common/footer.jsp" />
  <comp:put name="menu"   value="/basic/menu.jsp" />
  <%-- <comp:put name="menu"   value="/common/menu.jsp" /> --%>
  <comp:put name="body"   value="/portal/portalBody.jsp" />
</comp:insert>
