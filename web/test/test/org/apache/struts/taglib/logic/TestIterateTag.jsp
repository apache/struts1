<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="junit.framework.Assert"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testRequestScopePropertyIterateArray">
  <logic:iterate id="iteration" name="testRequestScopePropertyIterateArray" scope="request">
	<bean:write name="iteration"/>
  </logic:iterate>
</logic:equal>