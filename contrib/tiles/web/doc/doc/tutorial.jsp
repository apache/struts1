<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert definition="doc.mainLayout" flush="true">
  <comp:put name="body" value="/doc/tutorialBody.html" />
</comp:insert>  
