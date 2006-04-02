<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<c:if test='${not empty param["redirectType"]}'>
    <c:choose>
        <c:when test='${param["redirectType"] eq "forward"}'>
            <logic-el:redirect forward='${param["param1"]}'
                               transaction="${!empty pageScope}"/>
        </c:when>
        <c:when test='${param["redirectType"] eq "href"}'>
            <logic-el:redirect href='${param["param1"]}'/>
        </c:when>
        <c:when test='${param["redirectType"] eq "page"}'>
            <logic-el:redirect page='${param["param1"]}'/>
        </c:when>
    </c:choose>
</c:if>
<html-el:html>
    <head>
        <title>Test Struts &lt;logic:redirect&gt; tag</title>
    </head>

    <body>
    <div align="center">
        <h1>Test Struts &lt;logic:redirect&gt; tag</h1>
    </div>
    <table>
        <tr>
            <td>
                Redirect with <html-el:link forward="redirectForward">
                Forward</html-el:link>
            </td>
        </tr>
        <tr>
            <td>
                Redirect with <html-el:link forward="redirectHref">
                Href</html-el:link>
            </td>
        </tr>
        <tr>
            <td>
                Redirect with <html-el:link forward="redirectPage">
                Page</html-el:link>
            </td>
        </tr>
    </table>
    </body>
</html-el:html>
