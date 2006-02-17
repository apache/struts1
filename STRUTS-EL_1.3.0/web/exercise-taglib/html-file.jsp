<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html-el:html>
    <head>
        <title>Test html-el:file Tag</title>
    </head>

    <body bgcolor="white">

    <div align="center">
        <h1>Test struts html-el:file Tag</h1>
    </div>

    <html-el:form action="html-file.do">
        <table>
            <tr>
                <td>
                    <html-el:file property="stringProperty"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:file property="stringProperty"
                                  disabled="${!empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <logic-el:iterate collection="${pageScope}" id="item">
                        <html-el:file property="stringProperty"
                                      indexed="${!empty pageScope}"/>
                    </logic-el:iterate>
                </td>
            </tr>
        </table>
    </html-el:form>
    <script>
        <!--
        function showevent(evt) {
            window.status = evt.type;
        }
        // -->
    </script>

    </body>
</html-el:html>
