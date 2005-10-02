<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/tutorial/basic/myLayout.jsp" flush="true">
  <tiles:put name="title"  value="My first page" />
  <tiles:put name="header" value="/tutorial/common/header.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
  <tiles:put name="menu"   value="/tutorial/basic/menu.jsp" />
  <tiles:put name="body"   value="/tutorial/basic/helloBody.jsp" />
</tiles:insert>
