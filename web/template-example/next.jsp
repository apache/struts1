<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' 
	     prefix='template' %>

<% String msg = "Insert next page functionality here"; %>

<template:insert template='/chapterTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='<%= msg %>' 
  										  direct='true'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>
