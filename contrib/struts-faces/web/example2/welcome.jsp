<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<h3><bean:message key="index.heading"/></h3>
<ul>
<li><html:link page="/editRegistration.do?action=Create"><bean:message key="index.registration"/></html:link></li>
<li><html:link page="/faces/logon.jsp"><bean:message key="index.logon"/></html:link></li>
</ul>
