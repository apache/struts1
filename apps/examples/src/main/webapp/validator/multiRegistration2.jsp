<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html>
<head>
<title><bean:message key="registrationForm.title"/></title>
<html:base/>
</head>
<body bgcolor="white">
<p><html:link action="showStrutsConfig" target="_blank">struts-config.xml</html:link> &nbsp;
   <html:link action="showValidation" target="_blank">validation.xml</html:link></p>

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
  <html:hidden property="page" value="2"/>
  
  <html:hidden property="firstName"/>
  <html:hidden property="lastName"/>
  <html:hidden property="addr"/>
  <html:hidden property="cityStateZip.city"/>
  

<table border="0" width="100%">
  <tr>
    <th align="left">
      <bean:message key="registrationForm.stateprov.displayname"/>
    </th>
    <td align="left">
      <html:text property="cityStateZip.stateProv" size="60" maxlength="60"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.zippostal.displayname"/>
    </th>
    <td align="left">
      <html:text property="cityStateZip.zipPostal[1]" size="25" maxlength="25"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.phone.displayname"/>
    </th>
    <td align="left">
      <html:text property="phone" size="20" maxlength="20"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="registrationForm.email.displayname"/>
    </th>
    <td align="left">
      <html:text property="email" size="60" maxlength="60"/>
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

<html:javascript formName="multiRegistrationForm" page="2"/>

</body>
</html:html>
