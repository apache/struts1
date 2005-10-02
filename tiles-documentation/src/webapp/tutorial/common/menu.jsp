<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert page="/layout/vboxLayout.jsp" flush="true" >
  <tiles:putList name="componentsList" >
    <tiles:add value="/tutorial/common/menu/menuLogo.jsp" />
    <tiles:add value="/tutorial/common/menu/menuLinks.jsp" />
    <tiles:add value="/tutorial/common/menu/menuSrc.jsp" />
  </tiles:putList>
</tiles:insert>
