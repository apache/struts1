<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<BR>
<table cellpadding=0 cellspacing=0 border=0 width="10%">
   <tr bgcolor=eeeeee><td>
         <table cellpadding=3 cellspacing=1 border=0 width="100%">
            <%-- <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="index.jsp"><bean:message key="app.home"/></a></b></font></td></tr>
	    --%>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="registration.do?action=create"><bean:message key="registrationForm.title"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="registration.do?action=search"><bean:message key="registrationForm.title.search"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="jsRegistration.do?action=create"><bean:message key="jsRegistrationForm.title"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="jsRegistration.do?action=search"><bean:message key="jsRegistrationForm.title.search"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="multiRegistration.do?action=create"><bean:message key="multiRegistrationForm.title"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="multiRegistration.do?action=search"><bean:message key="multiRegistrationForm.title.search"/></a></b></font></td></tr>

            <tr><td align="center" bgcolor=#FFFFFF>&nbsp;<BR></td></tr>

            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><bean:message key="changeLanguage.change"/></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="changeLocale.jsp?locale=en"><bean:message key="changeLanguage.english"/></a></b></font></td></tr>
            <tr><td align="center" bgcolor=#FFFFFF><font face="Arial,Helvetica" size=-1>
	       <b><a href="changeLocale.jsp?locale=fr"><bean:message key="changeLanguage.french"/></a></b></font></td></tr>

         </table>
   </td></tr>
</table>