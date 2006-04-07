<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<logic:equal name="runTest" value="testRequestScopePropertyIterateArray">
    <logic:iterate id="iteration" name="testRequestScopePropertyIterateArray"
                   scope="request">
        <bean:write name="iteration"/>
    </logic:iterate>
</logic:equal>