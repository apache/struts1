<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testIncludeTagForward">
	<bean:include id="INCLUDE_TAG_KEY" forward="testIncludeTagForward"/>
</logic:equal>


<logic:equal name="runTest" value="testIncludeTagHref">
	<bean:define id="serverAddress">
	http://<%=request.getServerName()%>:<%=request.getServerPort()%>/test/test/org/apache/struts/taglib/bean/resources/IncludeTagTest.jsp
	</bean:define>
	<bean:include id="INCLUDE_TAG_KEY" href="<%=serverAddress%>"/>
</logic:equal>


<logic:equal name="runTest" value="testIncludeTagPage">
	<bean:include id="INCLUDE_TAG_KEY" page="/test/org/apache/struts/taglib/bean/resources/IncludeTagTest.jsp"/>
</logic:equal>



<bean:write name="INCLUDE_TAG_KEY"/>
