<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
  /* There's not really a clean way to test parameter with Cactus tests.
   * I went with what worked for me.
   * The URL used to call these tests have the following parameters:
   *
   * Cactus_TestMethod=testParameterTag
   * Cactus_TestClass=org.apache.struts.taglib.bean.TestParameterTag
   * Cactus_AutomaticSession=true
   * Cactus_Service=CALL_TEST
   **/
%>


<logic:equal name="runTest" value="testParameterTag">
	<bean:parameter id="PAGE_KEY" name="Cactus_TestClass"/>
</logic:equal>

<logic:equal name="runTest" value="testParameterTagValue">
	<bean:parameter id="PAGE_KEY" name="does not exists" value="Test Value"/>
</logic:equal>


<bean:write name="PAGE_KEY"/>
