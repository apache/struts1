<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/basic/myLayout.jsp" flush="true">
  <comp:put name="title"  value="My first page" />
  <comp:put name="header" value="/common/header.jsp" />
  <comp:put name="footer" value="/common/footer.jsp" />
  <comp:put name="menu"   value="/basic/menu.jsp" />
  <comp:put name="body"   value="/basic/helloBody.jsp" />
</comp:insert>
