<%
/**
 * Redirect default requests to Welcome action.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:12 $
*/
%>
<%@ page language="java" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<logic:forward name="welcome"/>
