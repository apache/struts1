<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>


  <%-- Insert a layout rendering requested tiles.
  --%>
<tiles:insert page="/layouts/classicLayout.jsp" flush="true">
  <tiles:put name="title"  value="Tiles Basic Page" />
  <tiles:put name="header" value="/tiles/common/header.jsp" />
  <tiles:put name="footer" value="/tiles/common/footer.jsp" />
  <tiles:put name="menu"   value="/tiles/simpleMenu.jsp" />
  <tiles:put name="body"   value="/tiles/body.jsp" />
</tiles:insert>
