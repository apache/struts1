<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' prefix='template' %>

<html><head><title><template:get name='title'/></title></head>
<body background='graphics/blueAndWhiteBackground.gif'>

<table>
   <tr valign='top'><td><template:get name='sidebar'/></td>
      <td><table>
            <tr><td><template:get name='header'/></td></tr>
            <tr><td><template:get name='content'/></td></tr>
            <tr><td><template:get name='footer'/></td></tr>
          </table>
      </td>
   </tr> 
</table>
</body></html>

<%
/* 
Display a "sidebar" in a column along the left side of the page.
Display a "header" over the right column.
Display the page "content" below the header.
Display a "footer" at below the content.
If we change the layout of the elements on this page, all pages 
inserting this page will also change to use the new layout.
*/
%>
