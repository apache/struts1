<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
    String thing = "thing";
    pageContext.setAttribute("thing", thing);
%>
<html-el:html>
    <head>
        <title>Test html-el input tags</title>
    </head>

    <body bgcolor="white">

    <div align="center">
        <h1>Test struts html-el input tags</h1>
    </div>

    <html-el:form action="html-input.do">
        <table>
            <tr>
                <td>
                    <html-el:hidden property="stringProperty"
                                    write="${!empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:hidden property="stringProperty"
                                    write="${empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:hidden property="stringProperty"
                                    title="${thing}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <logic-el:iterate collection="${pageScope}" id="item">
                        <html-el:hidden property="stringProperty"
                                        indexed="${!empty pageScope}"/>
                    </logic-el:iterate>
                </td>
            </tr>
            <tr>
                <td>
                    <logic-el:iterate collection="${pageScope}" id="item">
                        <html-el:hidden name="testbean"
                                        property="stringProperty"
                                        indexed="${!empty pageScope}"/>
                    </logic-el:iterate>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:password property="stringProperty"
                                      title="${thing}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:password property="stringProperty"
                                      title="${thing}"
                                      disabled="${!empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:password property="stringProperty"
                                      title="${thing}"
                                      maxlength="${3-1}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:password property="stringProperty"
                                      title="${thing}"
                                      readonly="${!empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <html-el:password property="stringProperty"
                                      title="${thing}"
                                      redisplay="${!empty pageScope}"/>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="right">
                    <html-el:submit disabled="${!empty pageScope}">
                        Save</html-el:submit>
                    <html-el:submit>Save</html-el:submit>
                </td>
                <td align="left">
                    <html-el:reset>Reset</html-el:reset>
                    <html-el:cancel>Cancel</html-el:cancel>
                </td>
                <td>&nbsp;</td>
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
