<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld"  prefix="html-el"  %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el"  %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
 <head>
  <title>Source for page: <c:out value="${param.path}"/></title>
 </head>
 <body>
  <div align="center">
   <h1>Source for page: <c:out value="${param.path}"/></h1>
  </div>
  <bean-el:resource id="pathSource" name="${param.path}"/>
  <hr/>
  <!-- Specifically avoiding newlines and spaces inside the "pre" tag. -->
  <pre><c:out value="${pathSource}" escapeXml="true"/></pre>
  <hr/>
 </body>
</html-el:html>
