<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/tutorial/layout/classicLayout.jsp" flush="true">
  <comp:put name="title"  value="My First Portal Page" />
  <comp:put name="header" value="/tutorial/common/header.jsp" />
  <comp:put name="footer" value="/tutorial/common/footer.jsp" />
  <comp:put name="menu"   value="/tutorial/basic/menu.jsp" />
  <%-- <comp:put name="menu"   value="/tutorial/common/menu.jsp" /> --%>
  <comp:put name="body"   value="/tutorial/portal/portalBody.jsp" />
</comp:insert>
