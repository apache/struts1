<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%
    Integer border = new Integer(0);
    pageContext.setAttribute("border", border);
    String thing = "thisFrame";
    pageContext.setAttribute("thing", thing);
    String scrolling = "yes";
    pageContext.setAttribute("scrolling", scrolling);
%>
<html-el:html>
    <frameset rows="33%,33%,34%" cols="33%,33%,34%">
        <html-el:frame href="html-frame1.jsp"/>
        <html-el:frame href="html-frame1.jsp" noresize="${!empty pageScope}"/>
        <html-el:frame href="html-frame1.jsp"
                       transaction="${!empty pageScope}"/>
        <html-el:frame href="html-frame1.jsp" frameborder="${border}"/>
        <html-el:frame href="html-frame1.jsp" frameName="${thing}"/>
        <html-el:frame href="html-frame1.jsp" marginheight="${border + 10}"/>
        <html-el:frame href="html-frame1.jsp" marginwidth="${border + 15}"/>
        <html-el:frame href="html-frame1.jsp" scrolling="${scrolling}"/>
        <html-el:frame href="html-frame1.jsp" styleId="${thing}"/>
    </frameset>
</html-el:html>
