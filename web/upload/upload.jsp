<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-form.tld" prefix="form" %>

<!--
	The most important part is to declare your form's enctype to be "multipart/form-data",
	and to have a form:file element that maps to your ActionForm's FormFile property
-->
  
  
<form:form action="upload.do" enctype="multipart/form-data">

	Please enter some text, just to demonstrate the handling of text elements as opposed to file elements:<br />
	<form:text property="theText" /><br /><br />
	
	Please select the file that you would like to upload:<br />
	<form:file property="theFile" /><br /><br />
	
	<form:submit />


</form:form>