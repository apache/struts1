<%@ taglib uri='/WEB-INF/tlds/struts-template.tld' 
	     prefix='template' %>

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
