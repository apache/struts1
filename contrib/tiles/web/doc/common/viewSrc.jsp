<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert definition="mainLayout" flush="true" >
  <comp:put name="title" value="JSP Source Code" />
  <comp:put name="body" value="/common/viewSrcBody.jsp" />
</comp:insert>  