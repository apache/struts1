<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html>
    <head>
        <title>
            <title>Test struts-html:form Tag</title>
        </title>
        <html:base/>
    </head>

    <body bgcolor="white">

    <p><strong><i>Post Back</i> Form example</strong></p>

    <p>
        Pressing the Submit button should re-display this page.
    </p>

    <logic:messagesPresent>
    <p>
        <font color="red"><strong>
            <html:messages id="msg">
                <bean:write name="msg"/>
            </html:messages>
         </strong></font>
    </p>
    </logic:messagesPresent>

    <p>
        <html:form>
            <html:submit property="submit"/>
        </html:form>
    </p>
    <p>
        Use the links below to change the <i>postback</i> action (both return
        to this page).
        <ul>
           <li>Switch to <html:link action="html-form">/html-form</html:link></li>
           <li>Switch to <html:link action="html-form-postback">/html-form-postback</html:link></li>
        </ul>
    </p>
    <hr/>

    <p><html:link action="welcome">Return to the Taglib Exercises main page</html:link></p>

    </body>
</html:html>
