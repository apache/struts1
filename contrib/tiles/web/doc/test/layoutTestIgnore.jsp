<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>



<%--
  Layout to test 'ignore' attribute.
--%>
<table  border="2"  width="300"  bordercolor="Gray">
<tr>
<td  bgcolor="Blue"><strong><template:getAsString name="title" ignore="false" /></strong></td>
</tr>
<tr>
<td><template:insert attribute="header" ignore="true"/></td>
</tr>
<tr>
<td><template:insert attribute="body" ignore="true"/></td>
</tr>
</table>
