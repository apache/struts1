<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>


In the right column you can find some basic examples, 
while in the left column you find the corresponding output result.
<table cellspacing=4 cellpadding="2" border="4" >
<tr>
<td><strong>Output Result</strong></td>
<td><strong>Sources</strong></td>
</tr>
<tr>
    <td valign="top"><comp:insert page="testBasic.jsp" /></td>
    <td valign="top">
	  <comp:insert page="/common/viewSrcBody.jsp">
	    <comp:put name="srcPath" value="/test/testBasic.jsp" />
	  </comp:insert>
	</td>
</tr>
<tr>
    <td valign="top"><comp:insert page="testList.jsp" /></td>
    <td valign="top">
	  <comp:insert page="/common/viewSrcBody.jsp">
	    <comp:put name="srcPath" value="/test/testList.jsp" />
	  </comp:insert>
	</td>
</tr>
<tr>
    <td valign="top"><comp:insert page="testDefinitions.jsp" /></td>
    <td valign="top">
	  <comp:insert page="/common/viewSrcBody.jsp">
	    <comp:put name="srcPath" value="/test/testDefinitions.jsp" />
	  </comp:insert>
	</td>
</tr>
</table>
