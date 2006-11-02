<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
  <head>
    <title>Test struts-html:image Tag</title>
  </head>
  <body>
    <div align="center">
      <h1>Test struts-html:image Tag</h1>
    <p>
        Click on the <strong><i>powered by</i></strong> images below to submit the form:
    </p>
    <p>
        Last Submitted:
        <font color="blue">
            <%= new java.util.Date() %>
        </font>
    </p>
    </div>
    <html:form>
        <table border="1" cellspacing="2" cellpadding="4" align="center">
        <tr>
            <td>Standard image tag</td>
            <td>
                <input type="image" src="struts-power.gif">
            </td>
        </tr>
        <tr>
            <td>Struts image tag via page attribute</td>
            <td>
                <html:image page="/struts-power.gif" />
            </td>
        </tr>
        <tr>
            <td>Struts image tag via page attribute, current module</td>
            <td>
                <html:image page="/struts-power.gif"/>
            </td>
         </tr>
         <tr>
            <td>Struts image tag via page attribute, default module</td>
            <td>
                <html:image page="/struts-power.gif" module="/validator"/>
            </td>
         </tr>
    </html:form>
  </body>
</html>
