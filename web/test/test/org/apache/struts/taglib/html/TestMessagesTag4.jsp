<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="junit.framework.Assert"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.apache.struts.util.MessageResources"%>
<%@page import="org.apache.struts.action.Action"%>
<%@page import="org.apache.struts.action.ActionError"%>
<%@page import="org.apache.struts.action.ActionMessage"%>
<%@page import="org.apache.struts.action.ActionMessages"%>
<%@page import="org.apache.struts.Globals"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%
/** 
  * From the Docs:
  * 
  * <html:message>
  * Displays a set of messages prepared by a business logic component and 
  * stored as an ActionMessages  object, ActionErrors object, a String, 
  * or a String array in request scope. If such a bean is not found, nothing 
  * will be rendered.
  * 
  * The tests are setup to test all possible scenarios with each ojbect listed.
  * Using Action.ERROR_KEY in the request
  * TestMessagesTag1.jsp - uses ActionMessages to store any keys for testing
  * TestMessagesTag2.jsp - uses a String to store any keys for testing
  * TestMessagesTag3.jsp - uses a String Array to store any keys for testing
  * TestMessagesTag4.jsp - uses ActionErrors to store any keys for testing
  * 
  * Using Action.MESSAGE_KEY in the request
  * TestMessagesTag5.jsp - uses ActionMessages to store any keys for testing
  * TestMessagesTag6.jsp - uses a String to store any keys for testing
  * TestMessagesTag7.jsp - uses a String Array to store any keys for testing
  * TestMessagesTag8.jsp - uses ActionErrors to store any keys for testing
  * 
  **/
%>

<!-- --------Testing attributes using forward------ -->
<logic:equal name="runTest" value="testMessages">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message" >
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesDefaultBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>
   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message" >
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageDefaultBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message" >
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageDefaultBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  header="custom.errors.header">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageDefaultBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
      
        Message:Testing Message
      custom_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  header="custom.errors.header" footer="custom.errors.footer">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testMessagesNameDefaultBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMessagesNamePropertyDefaultBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message" name="my-key" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testMessagesNameActionMessageDefaultBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageDefaultBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>



<logic:equal name="runTest" value="testMessagesNameActionMessageDefaultBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.errors.header">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageDefaultBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.errors.header" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMessagesNameActionMessageDefaultBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
      
        Message:Testing Message
      custom_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.errors.header" footer="custom.errors.footer">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageDefaultBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_error_header
        Message:Testing Message
      custom_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("default.bundle.message"));
     messages.add("myproperty2", new ActionMessage("default.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.errors.header" footer="custom.errors.footer" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>







<logic:equal name="runTest" value="testMessagesAlternateBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageAlternateBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageAlternateBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  header="custom.alternate.errors.header" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesActionMessageAlternateBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
      
        Message:Testing Message
      custom_alternate_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute(Action.ERROR_KEY, messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  header="custom.alternate.errors.header" 
             footer="custom.alternate.errors.footer" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>




<logic:equal name="runTest" value="testMessagesNameAlternateBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNamePropertyAlternateBundleEmpty">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" bundle="alternate" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNameActionMessageAlternateBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageAlternateBundle">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      
        Message:Testing Message
    </bean:define>

   <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" bundle="alternate" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNameActionMessageAlternateBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
      
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.alternate.errors.header" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageAlternateBundleHeader">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.alternate.errors.header" bundle="alternate" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>

<logic:equal name="runTest" value="testMessagesNameActionMessageAlternateBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
      
        Message:Testing Message
      custom_alternate_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.alternate.errors.header" 
             footer="custom.alternate.errors.footer" bundle="alternate">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>


<logic:equal name="runTest" value="testMessagesNamePropertyActionMessageAlternateBundleHeaderFooter">
    <bean:define id="EXPECTED_RESULTS" toScope="page">
      Messages:
      custom_alternate_error_header
        Message:Testing Message
      custom_alternate_error_footer
    </bean:define>

  <% 
     ActionMessages messages = new ActionMessages();
     messages.add("myproperty1", new ActionMessage("alternate.bundle.message"));
     messages.add("myproperty2", new ActionMessage("alternate.bundle.message"));
     request.setAttribute("my-key", messages);
   %>
    <bean:define id="TEST_RESULTS" toScope="page">
      Messages:
      <html:messages id="message"  name="my-key" header="custom.alternate.errors.header" 
             footer="custom.alternate.errors.footer" bundle="alternate" property="myproperty2">
        Message:<bean:write name="message"/>
      </html:messages>
    </bean:define>
</logic:equal>




<%
   MessageResources messageResources  = 
      MessageResources.getMessageResources("org.apache.struts.taglib.LocalStrings");

String expected  = (String) pageContext.getAttribute("EXPECTED_RESULTS");
String compareTo = (String) pageContext.getAttribute("TEST_RESULTS");

if ((expected == null) || (compareTo == null)){
    Assert.fail(messageResources.getMessage("tests.failure"));
}

Assert.assertEquals(expected, compareTo);
%>
