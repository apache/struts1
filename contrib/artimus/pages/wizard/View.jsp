<%--
/**
 * Display data entered form (read-only).
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $dateString: 2001/08/23 $
*/
--%>
<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<html:html/>
<head>
<title>View - String</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<html:base/>
</head>
<body bgcolor=#FFFFFF link="#000066" vlink="#660066" alink="#33CCCC">
<table width="600" align="center" border="1"><tr><td align="center"><table cellspacing="4">
<tr><td colspan="3"><html:errors/></td></tr>

<tr> 
<td align="right" nowrap>ID:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="id"/></td>
<td>&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Amount:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="amount"/></td>
<td>&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Check:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="check"/></td>
<td>&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Date:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="dateString"/></td>
<td>&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Phone:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="phone"/></td>
<td>&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Zip:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="zip"/></td>
<td align="left">&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Email:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="email"/></td>
<td align="left">&nbsp;</td>
</tr>

<tr> 
<td align="right" nowrap>Text:</td>
<td align="left">&nbsp;<bean:write name="wizardForm" property="text"/></td>
<td align="left">&nbsp;</td>
</tr>

</table></td></tr>
<tr><td align="center" bgcolor="#EEEEEE"></td></tr>
</table></table></body></html>
