<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
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
  <html-el:frame href="html-frame1.jsp" transaction="${!empty pageScope}"/>
  <html-el:frame href="html-frame1.jsp" frameborder="${border}"/>
  <html-el:frame href="html-frame1.jsp" frameName="${thing}"/>
  <html-el:frame href="html-frame1.jsp" marginheight="${border + 10}"/>
  <html-el:frame href="html-frame1.jsp" marginwidth="${border + 15}"/>
  <html-el:frame href="html-frame1.jsp" scrolling="${scrolling}"/>
  <html-el:frame href="html-frame1.jsp" styleId="${thing}"/>
 </frameset>
</html-el:html>
