<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' 
	     prefix='template' %>

<template:insert template='/chapterTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='/using.html'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>
