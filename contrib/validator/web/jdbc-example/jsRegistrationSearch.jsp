<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-validator.tld" prefix="validator" %>

<template:insert template='/WEB-INF/templates/mainTemplate.jsp'>
  <template:put name='title'>
     <bean:message key="jsRegistrationForm.title.search"/>
  </template:put>
  <template:put name='header' content='/WEB-INF/templates/header.jsp' />
  <template:put name='sidebar' content='/WEB-INF/templates/sidebar.jsp' />
  <template:put name='main'>
     <font size=+2><bean:message key="jsRegistrationForm.title.search"/></font><BR><BR>
      <logic:messagesPresent>
         <bean:message key="errors.header"/>
         <ul>
         <html:messages id="error">
            <li><bean:write name="error"/></li>
         </html:messages>
         </ul><hr>
      </logic:messagesPresent>
      
      
      <validator:messagesExist>
         <ul>
         <validator:messages id="message">
            <li><bean:write name="message"/></li>
         </validator:messages>
         </ul>
      </validator:messagesExist><BR>
     
     <html:form action="jsRegistration">
        <html:hidden property="action" value="search"/>
     
        <table><tr nowrap>
           <td><b><bean:message key="registrationForm.lastname.displayname"/></b></td>
           <td>&nbsp;</td>
           <td><html:text property="lastName" size="30" maxlength="30"/></td>
           <td>&nbsp;</td>
           <td><html:submit><bean:message key="button.search"/></html:submit></td>
        </tr></table>                                            
     </html:form>                                                
     
     <TABLE bgcolor=#dcdcdc border=0 cellPadding=4 cellSpacing=1 width="100%">
        <tr bgcolor="#eeeeee">
           <th width="15%" align="left"><bean:message key="registrationForm.lastname.displayname"/></th>
           <th width="15%" align="left"><bean:message key="registrationForm.firstname.displayname"/></th>
           <th width="30%" align="left"><bean:message key="registrationForm.phone.displayname"/></th>
           <th width="30%" align="left"><bean:message key="registrationForm.email.displayname"/></th>
           <th width="1%">&nbsp;</th>
           <th width="1%">&nbsp;</th> 
        </tr>
     
     <logic:present name="<%= com.wintecinc.struts.action.GenericAction.SEARCH_KEY %>">
        <logic:iterate id="searchResults" name="<%= com.wintecinc.struts.action.GenericAction.SEARCH_KEY %>">
           <tr bgcolor="#ffffff">
               <td nowrap><bean:write name="searchResults" property="lastName"/></td>
               <td nowrap><bean:write name="searchResults" property="firstName"/></td>
               <td nowrap><bean:write name="searchResults" property="phone"/></td>
               <td nowrap><bean:write name="searchResults" property="email"/></td>
               <td align=center nowrap><font size=-1>
                  <A href="jsRegistration.do?action=edit&id=<bean:write name="searchResults" property="id"/>">
                     <bean:message key="button.edit"/>
                  </A>
                  </font>
               </td>
               <td align=center nowrap><font size=-1>
                  <A href="jsRegistration.do?action=delete&id=<bean:write name="searchResults" property="id"/>">
                     <bean:message key="button.delete"/>
                  </A>
                  </font>
               </td> 
            </tr>
        </logic:iterate>
     </logic:present>
     </table>                     
  </template:put>  
  <template:put name='footer' content='/WEB-INF/templates/footer.jsp' />
</template:insert>
