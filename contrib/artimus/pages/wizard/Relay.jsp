<%--
/**
 * Form to enter data.
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $dateString: 2001/09/25 $
*/
--%>
<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<html:html>
<head>
<title>Form - String</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<html:base/>
</head>
<body bgcolor=#FFFFFF link="#000066" vlink="#660066" alink="#33CCCC" 
	onload="document.forms[0].elements[0].focus();">
<table width="600" align="center" border="1"><tr><td align="center"><table cellspacing="4">

<tr><td colspan="3">
<html:errors/>
</td></tr>

<html:form action="/wizard/Relay" type="get">
<tr> 
<td align="right" nowrap>ID:</td>
<td align="left">&nbsp;<html:text property="id" size="4" maxlength="4" onblur="this.form.dateString.focus();"/></td>
<td>&nbsp;<font size="-1">1-9999</font></td>
</tr>

<tr> 
<td align="right" nowrap>Date:</td>
<td align="left">&nbsp;<html:text property="dateString" size="10" maxlength="10"/></td>
<td align="left">&nbsp;<font size="-1">12/12/12</font></td>
</tr>

<tr> 
<td align="right" nowrap>Amount:</td>
<td align="left">$<html:text property="amount" size="9" maxlength="9"/></td>
<td>&nbsp;<font size="-1">1-9999</font></td>
</tr>

<tr> 
<td align="right" nowrap>Check:</td>
<td align="left">&nbsp;<html:checkbox property="check"/></td>
<td>&nbsp;</td>
</tr>

<tr>
<td align="right" nowrap>Phone:</td>
<td align="left">&nbsp;<html:text property="phone" size="14" maxlength="14"/></td>
<td align="left">&nbsp;<font size="-1">123-456-7890</font></td>
</tr>

<tr>
<td align="right" nowrap>ZIP:</td>
<td align="left">&nbsp;<html:text property="zip" size="5" maxlength="5"/></td>
<td align="left">&nbsp;<font size="-1">12345</font></td>
</tr>

<tr>
<td align="right" nowrap>Email:</td>
<td align="left">&nbsp;<html:text property="email" size="20" maxlength="40"/></td>
<td align="left">&nbsp;<font size="-1">you@somewhere.com</font></td>
</tr>

<tr>
<td align="right" nowrap>Text:</td>
<td align="left" colspan="2">&nbsp;<html:text property="text" size="40" maxlength="40"/></td>
</tr>

<tr>
<td align="right" colspan="3">&nbsp;<html:submit onclick="this.forward=store;">ENTER</html:submit>&nbsp;<html:cancel onclick="bCancel=true;">CANCEL</html:cancel></td>
</tr>
<tr>
<input type="hidden" name="forward" value="error">
<td align="right" colspan="3">&nbsp;<html:submit onclick="this.forward=confirm;">CONFIRM</html:submit></td>
</tr>
</html:form>
<!-- validator:javascript formName="StringForm"/ -->
<script language="javascript">
<!--
function submitForm(form) {
  if (validateStringStringForm(form)) {
    form.submit.value=" ..."; 
    return true;
  } 
  else return false;
};
// -->
</script>
</table></td></tr>
<tr><td align="center" bgcolor="#EEEEEE"></td></tr>
</table></table></body></html:html>
