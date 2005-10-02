<%@ page language="java" %>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<logic:present parameter="src" >
<bean:parameter id="srcPath" name="src" />
<strong>file '<%=srcPath%>' not found !</strong>
</logic:present>

<logic:notPresent parameter="src" >
No source specified !
</logic:notPresent>
