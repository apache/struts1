<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' 
       prefix='template' %>

<% String msg = "In your application, you could insert some clever functionality into the file next.jsp, and then link to it from footer.html."; %>

<template:insert template='/chapterTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='<%= msg %>' 
                        direct='true'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>
