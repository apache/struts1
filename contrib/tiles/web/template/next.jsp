<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' 
	     prefix='template' %>

<% String msg = "Insert next page functionality here"; %>

<template:insert definition='next.page' >
  <template:put name='content' content='<%= msg %>' 
  										  direct='true'/>
</template:insert>
