<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
<head>
    <title><bean:message key="mainMenu.title"/></title>
    <link rel="stylesheet" type="text/css" href="base.css"/>
</head>

<body>
<h3><bean:message key="mainMenu.heading"/> <bean:write name="user"
                                                       property="fullName"/></h3>
<ul>
    <li><html:link action="/EditRegistration?task=Edit"><bean:message
            key="mainMenu.registration"/></html:link></li>
    <li><html:link action="/Logoff"><bean:message key="mainMenu.logoff"/>
    </html:link></li>
</ul>
</body>
</html>
