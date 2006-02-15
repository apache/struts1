<%@ page import="org.apache.struts.action.*,
                 java.util.Iterator,
                 org.apache.struts.webapp.upload.UploadForm, 
                 org.apache.struts.Globals" %><%@ taglib uri="/tags/struts-bean" prefix="bean" %><%@ taglib uri="/tags/struts-html" prefix="html" %><%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<html>
  <head>
    <title>File Upload Example</title>
  </head>
  <body>
<logic:messagesPresent>
   <ul>
   <html:messages id="error">
      <li><bean:write name="error"/></li>
   </html:messages>
   </ul><hr />
</logic:messagesPresent>

    <!--
            The most important part is to declare your form's enctype to be "multipart/form-data",
            and to have a form:file element that maps to your ActionForm's FormFile property
    -->
    <html:form action="upload-submit.do?queryParam=Successful" enctype="multipart/form-data">
    <p>Please enter some text, just to demonstrate the handling of text elements as opposed to file elements: <br />
    <html:text property="theText" /></p>
    <p>Please select the file that you would like to upload: <br />
    <html:file property="theFile" /></p>
    <p>If you would rather write this file to another file, please check here: <br />
    <html:checkbox property="writeFile" /></p>
    <p>If you checked the box to write to a file, please specify the file path here: <br />
    <html:text property="filePath" /></p>
    <p>
    <html:submit />
    </p>
    </html:form>
    <hr/>
    <html:form action="upload-submit.do?queryParam=Successful" enctype="multipart/form-data">
    <p>This form is to test <a href="http://issues.apache.org/bugzilla/show_bug.cgi?id=38534">Bug 38534</a>.
       If this bug is fixed then perversly a <code>NestedNullException</code> will be thrown by BeanUtils's
       populate method, but the application should continue to function after that. If its not fixed then
       the result page will be shown BUT after that the application will no longer function.
    </p>
    <p>
        <input type="file"   name="theFile" />
        <input type="hidden" name="multipartRequestHandler.servlet.servletContext.attribute(org.apache.struts.action.MODULE)" size="60" value="Field named to trash the default module!"/></br>
        <input type="hidden" name="multipartRequestHandler.servlet.servletContext.attribute(org.apache.struts.globals.MODULE_PREFIXES)" size="60" value="Trash the prefixes to other modules!"/></br>
        <input type="hidden" name="theText" value="ABCDEF"/>
    </p>
    <p>
    <html:submit>Submit For Bug 38534</html:submit>
    </p>
    </html:form>
  </body>
</html>
