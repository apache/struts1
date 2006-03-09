<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld"  prefix="html-el"  %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
 <head>
  <title>
   Testing access of DynaActionForms through JSTL/Struts-EL
  </title>
 </head>
 <body bgcolor="white">
  <div align="center">
   <h1>Test access of DynaActionForms through JSTL/Struts-EL</h1>
  </div>
  <table border="1">
   <tr>
    <th>Property</th>
    <th>bean:write reference</th>
    <th>c:out reference</th>
   </tr>
   <tr>
    <td>foo</td>
    <td><bean:write name="dynabean" property="foo"/></td>
    <td><c:out value="${dynabean.map.foo}"/></td>
   </tr>
   <tr>
    <td>bar</td>
    <td><bean:write name="dynabean" property="bar"/></td>
    <td><c:out value="${dynabean.map.bar}"/></td>
   </tr>
   <tr>
    <td>thing</td>
    <td>
     <c:catch var="ex">
      <%-- This will throw an exception, as the "thing" property is not
			  defined.
      --%>
      <bean:write name="dynabean" property="thing"/>
     </c:catch>
     <c:if test="${!empty ex}">
      &lt;exception&gt;
     </c:if>
    </td>
    <%-- This will just return an empty string --%>
    <td><c:out value="${dynabean.map.thing}" default="<empty>"/></td>
   </tr>
  </table>
 </body>
</html-el:html>
