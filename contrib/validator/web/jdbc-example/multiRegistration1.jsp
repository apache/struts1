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
           <html:text property="city" size="60" maxlength="60"/>
         </td>
       </tr>
       <tr>
         <td nowrap>
            <logic:notEqual name="multiRegistrationForm" property="action" value="delete">
               <html:submit onclick="bCancel=false;">
                 <bean:message key="button.save"/>
               </html:submit>
               &nbsp;&nbsp;
               <html:reset>
                 <bean:message key="button.reset"/>
               </html:reset>
            </logic:notEqual>
            <logic:equal name="multiRegistrationForm" property="action" value="delete">
              <html:submit onclick="bCancel=true;">
                <bean:message key="button.confirm"/>
              </html:submit>
            </logic:equal>
            &nbsp;&nbsp;
            <html:cancel onclick="bCancel=true;">
               <bean:message key="button.cancel"/>
            </html:cancel>    
          </html:form>
            </td>
       </tr>
     </table>
     
     
     
     <validator:javascript formName="multiRegistrationForm" page="1"/>
  </template:put>  
  <template:put name='footer' content='/WEB-INF/templates/footer.jsp' />
</template:insert>
