<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/tutorial/basic/myLayout.jsp" flush="true">
  <comp:put name="title"  value="My first page" />
  <comp:put name="header" value="/tutorial/common/header.jsp" />
  <comp:put name="footer" value="/common/footer.jsp" />
  <comp:put name="menu"   value="/tutorial/basic/menu.jsp" />
  <comp:put name="body"   value="/tutorial/basic/helloBody.jsp" />
</comp:insert>
