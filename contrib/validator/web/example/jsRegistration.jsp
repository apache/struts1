<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-validator.tld" prefix="validator" %>

<html:html>
<head>
<title><bean:message key="registrationForm.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<validator:errorsExist>
   <bean:message key="errors.header"/>
   <ul>
   <validator:errors id="error">
      <li><bean:write name="error"/></li>
   </validator:errors>
   </ul><hr>
</validator:errorsExist>

<html:form action="registration" onsubmit="return validateRegistrationForm(this);">
  <html:hidden property="action"/>

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
  <tr colspan="1">
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

<%-- 
   Referencing the registrationForm since they are identical 
   so I don't need a separate validation.xml listing just for this form.
--%>
<validator:javascript formName="registrationForm"/>

</body>
</html:html>
