<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:insert definition="mainLayout" flush="true">
  <comp:put name="body" value="/invoice/editInvoice.jsp" />
</comp:insert>

