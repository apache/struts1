<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' prefix='template' %>

<template:insert template='/chapterTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='/introduction.html'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>

<% 
/*
Specify template for this page (chapterTemplate.jsp). 
The chapterTemplate.jsp defines the layout positoins for five 
elements: title, header, sidebar, content, and footer. 
Specify the source file (html or jsp) for each element.
*/
%>
