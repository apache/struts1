<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<logic:equal name="runTest" value="testResourceTag">
	<bean:resource id="PAGE_KEY" 
		name="/test/org/apache/struts/taglib/bean/resources/TextFileForTesting.txt"/>
</logic:equal>

<logic:equal name="runTest" value="testResourceTagInput">
	<bean:resource id="stream" 
		name="/test/org/apache/struts/taglib/bean/resources/TextFileForTesting.txt"
		input="any value here gives you an InputStream"/>

<%	/* 
	 * I borrowed this snippet from the ResourceTag.
	 * This test may seem trivial and redundent, but later, when others
	 * come along and change/add functionality, you'll come to appreciate
	 * tests like this.
	 */
    try {
        StringBuffer sb = new StringBuffer();
        java.io.InputStreamReader reader =
          new java.io.InputStreamReader(stream);
        char buffer[] = new char[256];
        int n = 0;
        while (true) {
            n = reader.read(buffer);
        if (n < 1)
            break;
        sb.append(buffer, 0, n);
        }
        reader.close();
        pageContext.setAttribute("PAGE_KEY", sb.toString());
    } catch (java.io.IOException e) {
    	e.printStackTrace();
    }
%>
</logic:equal>

<bean:write name="PAGE_KEY"/>
