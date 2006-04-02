<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
  <head>
    <title>Test struts-html:img Tag</title>
  </head>
  <body>
    <div align="center">
      <h1>Test struts-html:img Tag</h1>
    </div>

    <table border="1" cellspacing="2" cellpadding="4" align="center">
    <tr>
    <td>
    Standard img tag
    </td>
    <td>
    <img src="struts-power.gif">
    </td>
    </tr>
    <td>
    Struts img tag via page attribute
    </td>
    <td>
    <html:img page="/struts-power.gif" />
    </td>
    </tr>
    <tr>
    <td>
    Struts img tag via action attribute
    </td>
    <td>
    <html:img action="/html-img-action" />
    </td>
    </tr>
    <tr>
    <td>
    Struts img tag via page attribute, default module
    </td>
    <td>
    <html:img page="/exercise/struts-power.gif" module="/"/>
    </td>
    </tr>
  </body>
</html>
