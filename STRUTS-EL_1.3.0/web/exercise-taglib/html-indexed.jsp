<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html-el:html>
    <head>
        <title>Test indexed HTML tags</title>
    </head>

    <body bgcolor="white">
    <div align="center">
        <h1>Test indexed HTML tags</h1>
    </div>

    <html-el:form action="html-indexed.do">
        <table>
            <logic-el:iterate collection="${testbean.coords}"
                              id="coord" indexId="ctr">
                <tr>
                    <td>
                        X:<html-el:text name="coord" property="x"
                                        indexed="true"/>
                    </td>
                    <td>
                        Y:<html-el:text name="coord" property="y"
                                        indexed="true"/>
                    </td>
                </tr>
            </logic-el:iterate>
            <tr>
                <td>
                    <html-el:submit property="submitValue">
                        Submit Changes
                    </html-el:submit>
                </td>
            </tr>
        </table>
        (
        <logic-el:iterate collection="${testbean.coords}"
                          id="coord" indexId="ctr">
            [<c:out value="${coord.x}"/>,<c:out value="${coord.y}"/>]
        </logic-el:iterate>
        )
        <table>
            <tr>
                <logic-el:iterate collection="${testbean.images}" id="image"
                                  indexId="ctr">
                    <td>
                        <html-el:image src="${image}" property="imageCoords"
                                       indexed="true"/>
                    </td>
                </logic-el:iterate>
            </tr>
            <tr>
                <logic-el:iterate collection="${testbean.imageCoords}"
                                  id="coord"
                                  indexId="ctr">
                    <td>
                        (<c:out value="${coord.x}"/>,<c:out
                            value="${coord.y}"/>)
                    </td>
                </logic-el:iterate>
            </tr>
        </table>
    </html-el:form>
    </body>
</html-el:html>
