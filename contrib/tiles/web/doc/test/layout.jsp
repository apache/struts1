<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>



<%--
  A simple layout.
  First, we get component/template attribute (title) as a String.
  Next, we insert component/template attribute (header and body).
--%>
<table  border="2"  width="300"  bordercolor="Gray">
<tr>
<td  bgcolor="Blue"><strong><template:getAsString name="title"/></strong></td>
</tr>
<tr>
<td><template:insert attribute="header"/></td>
</tr>
<tr>
<td><template:insert attribute="body"/></td>
</tr>
</table>
