<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testPageTagApplication">
	<bean:page id="app" property="application"/>
	<%=app.getAttribute("PAGETAG_KEY")%>
</logic:equal>

<logic:equal name="runTest" value="testPageTagSession">
	<bean:page id="s" property="session"/>
	<%=s.getAttribute("PAGETAG_KEY")%>
</logic:equal>

<logic:equal name="runTest" value="testPageTagRequest">
	<bean:page id="req" property="request"/>
	<%=req.getAttribute("PAGETAG_KEY")%>
</logic:equal>

<logic:equal name="runTest" value="testPageTagResponse">
	<bean:page id="resp" property="response"/>
	<%resp.getWriter().println("PAGETAG_VAL");%>
</logic:equal>

<logic:equal name="runTest" value="testPageTagConfig">
	<bean:page id="cfg" property="config"/>
	<%=cfg.getServletContext().getAttribute("PAGETAG_KEY")%>
</logic:equal>

