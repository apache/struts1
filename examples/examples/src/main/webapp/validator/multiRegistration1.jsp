<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
<head>
<title><bean:message key="registrationForm.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:messagesPresent>
   <bean:message key="errors.header"/>
   <ul>
   <html:messages id="error">
      <li><bean:write name="error"/></li>
   </html:messages>
   </ul><hr />
</logic:messagesPresent>


<html:form action="multiRegistration-submit" onsubmit="return validateMultiRegistrationForm(this);">
  <html:hidden property="action"/>
  <html:hidden property="page" value="1"/>

<table border="0" width="100%">
  <tr>
    <th align="left">
      <bean:message key="registrationForm.firstname.displayname"/>
    </th>
    <td align="left">
      <html:text property="firstName" size="30" maxlength="30"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.lastname.displayname"/>
    </th>
    <td align="left">
      <html:text property="lastName" size="60" maxlength="60"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.addr.displayname"/>
    </th>
    <td align="left">
      <html:textarea property="addr" cols="40" rows="5"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.city.displayname"/>
    </th>
    <td align="left">
      <html:text property="cityStateZip.city" size="60" maxlength="60"/>
    </td>
  </tr>
  <tr>
    <td>
      <html:submit property="submit" onclick="bCancel=false;">
         <bean:message key="button.save"/>
      </html:submit>
      &nbsp;
      <html:reset>
         <bean:message key="button.reset"/>
      </html:reset>
      &nbsp;
      <html:cancel onclick="bCancel=true;">
         <bean:message key="button.cancel"/>
      </html:cancel>    
    </td>
  </tr>
</table>

</html:form>

<html:javascript formName="multiRegistrationForm" page="1"/>

</body>
</html:html>
