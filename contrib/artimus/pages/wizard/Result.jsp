<%--
/**
 * List of articles that match query.
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:05:16 $
 */
--%>

<%@ page language="java" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
  <head>
    <html:base/>
    <link rel="stylesheet" type="text/css" href="../../styles/global.css" />
    <title>Wizard - Wizard List</title>
  </head>
  <body>
   <table class="parent">
     <tr>
       <td align="center">
         <table class="child">
           <tr> 
             <td align="center" colspan="3"> 
               <bean:write name="RESULT" property="size"/> 
               matches for 
               <bean:write name="RESULT" property="description"/>
             </td>
           </tr>
           <logic:notEqual name="RESULT" property="size" value="0"> 
             <tr bgcolor="FFFFEE">
               <th>
                 <span style="text-transform: uppercase">id</span>
               </th>
               <th>
                 <span style="text-transform: uppercase">date</span>
               </th>
               <th>
                 <span style="text-transform: uppercase">amount</span>
               </th>
             </tr>
               <% int i = 0; %>
               <logic:iterate name="RESULT" property="iterator" id="row">
                 <% i++; 
                    if ( i % 2 == 0) { %>
                 <tr bgcolor="#EEEEEE">
                 <% } else { %>
                 <tr bgcolor="#FFFFFF">
                 <% } %>
                   <td align="left">
                     <bean:write name="row" property="id"/>
                   </td>
                   <td align="left" nowrap>
                     <bean:write name="row" property="date"/>
                   </td>
                   <td align="left" width="100%">
<html:link forward="wizard" paramName="row" paramProperty="key" paramId="key"><bean:write name="row" property="check"/></html:link> 
                   </td>
                 </tr>
               </logic:iterate>
           </logic:notEqual>
         </table>
        </td>
       </tr>
       <tr>
         <td class="options">
            <html:link forward="done">DONE</html:link>
         </td>
      </tr>
    </table>
  </body>
</html:html>
