<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-validator.tld" prefix="validator" %>

<html:html>
<head>
<title><template:get name='title'/></title>
<html:base/>
</head>
<body bgcolor="white">
  <table border=0 cellspacing=0 cellpadding=0 width="100%">
    <tr>
       <td colspan="2"><template:get name='header'/></td>
    </tr>
    <tr valign="top">
       <td width="15%" valign="top"><template:get name='sidebar'/></td>
       <td width="80%" align="left" valign="top"><template:get name='main'/></td>
    </tr>
    <tr>
       <td colspan="2"><template:get name='footer'/></td>
    </tr>
  </table>
</body>
</html:html>

