<%@ taglib uri="/tags/struts-html" prefix="html" %>
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
  </body>
</html>
