<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:definition id="definitionName" template="/tutorial/basic/myLayout.jsp" >
  <comp:put name="title"  value="My first page" />
  <comp:put name="header" value="/tutorial/common/header.jsp" />
  <comp:put name="footer" value="/tutorial/common/footer.jsp" />
  <comp:put name="menu"   value="/tutorial/basic/menu.jsp" />
  <comp:put name="body"   value="/tutorial/basic/helloBody.jsp" />
</comp:definition>

<comp:insert beanName="definitionName" flush="true" />
