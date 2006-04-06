<%@ page contentType="text/html; charset=utf-8" %><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
  <head>
    <title>utf-8 upload page</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  </head>
  <body>
    <!--
            The most important part is to declare your form's enctype to be "multipart/form-data",
            and to have a form:file element that maps to your ActionForm's FormFile property
    -->
    <html:form action="upload-submit.do" enctype="multipart/form-data">Please enter some text, just to demonstrate the handling of text elements as opposed to file elements:
    <br />
    <html:text property="theText" />
    <br />
    <br />Please select the file that you would like to upload:
    <br />
    <html:file property="theFile" />
    <br />
    <br />If you would rather write this file to another file, please check here: 
    <html:checkbox property="writeFile" />
    <br />
    <br />If you checked the box to write to a file, please specify the file path here:
    <br />
    <html:text property="filePath" />
    <br />
    <br />
    <html:submit /></html:form>
  </body>
</html>
