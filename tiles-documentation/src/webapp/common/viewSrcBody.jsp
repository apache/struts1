<%@ page language="java" %>
<%@ page errorPage="/common/viewSrcBodyError.jsp" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%-- Include requested resource file.
  If file is not found, Exception is thrown, and catched by the errorPage 
  directive (see above). Error page show a message.   
--%>

  <%-- Import component attributes, if any.
   --%>
   

<tiles:importAttribute/>

<logic:notPresent name="srcPath" >
  <logic:present parameter="src" >
  <bean:parameter id="srcPath" name="src" />
  </logic:present>  
</logic:notPresent>
     

<logic:present name="srcPath" >
<bean:define id="srcPathVar" name="srcPath" type="java.lang.String"/>
<bean:resource id="src" name="<%=srcPathVar%>" />
<strong>file '<%=srcPathVar%>'</strong>
<br>
<pre>
<bean:write filter="true" name="src" scope="page"/>
</pre>
<br>
</logic:present>

<logic:notPresent name="srcPath" >
No source specified !
</logic:notPresent>
