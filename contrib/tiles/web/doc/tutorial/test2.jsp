<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert page="/test/myLayout.jsp" flush="true">
  <comp:put name="title"  value="My first page" />
  <comp:put name="header" value="/test/header.jsp" />
  <comp:put name="footer" value="/common/footer.jsp" />
  <comp:put name="menu"   value="/basic/menu.jsp" />
  <comp:put name="body"   value="/forwardExampleAction.do" />
</comp:insert>
