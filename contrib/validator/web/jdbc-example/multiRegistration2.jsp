<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/datetime.tld" prefix="dt" %>
<%@ taglib uri="/WEB-INF/struts-validator.tld" prefix="validator" %>

<template:insert template='/WEB-INF/templates/mainTemplate.jsp'>
  <template:put name='title'>
     <bean:message key="multiRegistrationForm.title"/>
  </template:put>
  <template:put name='header' content='/WEB-INF/templates/header.jsp' />
  <template:put name='sidebar' content='/WEB-INF/templates/sidebar.jsp' />
  <template:put name='main'>
     <font size=+2><bean:message key="multiRegistrationForm.title"/></font><BR><BR>
     
     <logic:messagesPresent>
        <bean:message key="errors.header"/>
        <ul>
        <html:messages id="error">
           <li><bean:write name="error"/></li>
        </html:messages>
        </ul><hr>
     </logic:messagesPresent>
     
     <html:form action="multiRegistration" onsubmit="return validateMultiRegistrationForm(this);">
       <html:hidden property="action"/>
       <html:hidden property="page" value="2"/>
       
       <html:hidden property="firstName"/>
       <html:hidden property="lastName"/>
       <html:hidden property="addr"/>
       <html:hidden property="city"/>
       
     
     <table border="0" width="100%">
       <tr>
         <th align="left">
           <bean:message key="registrationForm.stateprov.displayname"/>
         </th>
         <td align="left">
           <html:text property="stateProv" size="60" maxlength="60"/>
         </td>
       </tr>
       <tr>
         <th align="left">
           <bean:message key="registrationForm.zippostal.displayname"/>
         </th>
         <td align="left">
           <html:text property="zipPostal" size="25" maxlength="25"/>
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
         <td nowrap>
           <html:submit property="submit" onclick="bCancel=false;">
              <bean:message key="button.save"/>
           </html:submit>
           &nbsp;&nbsp;
           <html:reset>
              <bean:message key="button.reset"/>
           </html:reset>
           &nbsp;&nbsp;
           <html:cancel onclick="bCancel=true;">
              <bean:message key="button.cancel"/>
           </html:cancel>   
    </html:form>
         </td>
       </tr>
     </table>
     
     </html:form>
     
     <validator:javascript formName="multiRegistrationForm" page="2"/>
  </template:put>  
  <template:put name='footer' content='/WEB-INF/templates/footer.jsp' />
</template:insert>
