<%@ taglib uri="/WEB-INF/tiles.tld"    prefix="comp" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%-- Edit an Address object
  @param address An address object to edit.
  @param compName UI Component name. Use as prefix for html fields and sub-components
--%>
<%-- Retrieve parameters from component context, and declare them as page variable --%>
<comp:useAttribute id="prefix" name="property" classname="java.lang.String"/>
<comp:importAttribute name="bean" />

<%-- Add a separator tothe component name, in order to have html fields prefix name : 'compName.'--%>
<% prefix = prefix + "."; %>

<table border="0" width="100%">

  <tr>
    <th align="right" width="30%">
      Street
    </th>
    <td align="left">
	  <%-- Declare an html input field. 										--%>
	  <%-- We use a tag that extends Struts 'text' tag. This extension add 		--%>
	  <%-- attribute 'prefix', allowing to give a prefix to the normal name  	--%>
	  
	  <html:text name="bean" property='<%=prefix+"street1"%>' size="50"/>
	  
    </td>
  </tr>

  <tr>
    <th align="right">
      Street (con't)
    </th>
    <td align="left">
        <ext:text property="street2" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      City
    </th>
    <td align="left">
        <ext:text property="city" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Country
    </th>
    <td align="left">
        <ext:text property="country" size="50"/>
    </td>
  </tr>

  <tr>
    <th align="right">
      Zip code
    </th>
    <td align="left">
	  <ext:text property="zipCode" size="50"/>
    </td>
  </tr>

</table>
