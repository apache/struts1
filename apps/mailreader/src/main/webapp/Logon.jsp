<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:xhtml/>
<html>
<head>
    <title><bean:message key="logon.title"/></title>
</head>

<body>
<html:errors/>

<html:form action="/SubmitLogon" focus="username"
           onsubmit="return validateLogonForm(this);">
    <table border="0" width="100%">

        <tr>
            <th align="right">
                <bean:message key="prompt.username"/>:
            </th>
            <td align="left">
                <html:text property="username" size="16" maxlength="18"/>
            </td>
        </tr>

        <tr>
            <th align="right">
                <bean:message key="prompt.password" bundle="alternate"/>:
            </th>
            <td align="left">
                <html:password property="password" size="16" maxlength="18"
                               redisplay="false"/>
            </td>
        </tr>

        <tr>
            <td align="right">
                <html:submit property="Submit" value="Submit"/>
            </td>
            <td align="left">
                <html:reset/>
            </td>
        </tr>

    </table>

</html:form>

<html:javascript formName="LogonForm"
                 dynamicJavascript="true"
                 staticJavascript="false"/>
<script language="Javascript1.1" src="StaticJavascript.jsp"></script>

<jsp:include page="Footer.jsp"/>
</body>
</html>
