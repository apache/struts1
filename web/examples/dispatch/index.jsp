<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html>
<head>
<title><bean:message key="index.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

    <p>
       <html:link forward="module-root"><bean:message key="index.home"/></html:link>
    </p>

    <hr />
    <p>
       <strong>Change Language | Changez Le Langage:</strong>
       &nbsp;
       <html:link action="/locale?language=en">English | Anglais</html:link>
       &nbsp;
       <html:link action="/locale?language=fr">French | Francais</html:link>
    </p>

    <hr />

    <h3><bean:message key="index.title"/></h3>

    <ul>
       <li><html:link action="/dispatch"><bean:message key="dispatch.title"/></html:link></li>
       <li><html:link action="/mapping"><bean:message key="mapping.title"/></html:link></li>
       <li><html:link action="/lookup"><bean:message key="lookup.title"/></html:link></li>
       <li><html:link action="/actionDispatcher"><bean:message key="actionDispatcher.title"/></html:link></li>
    </ul>

</body>
</html:html>
