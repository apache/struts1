<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld"  prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html>
<head>
<title>Test struts bean-el Size Tag</title>
</head>
<body bgcolor="white">

<%
    java.util.ArrayList data = new java.util.ArrayList();
    data.add("First");
    data.add("Second");
    data.add("Third");
    data.add("Fourth");
    data.add("Fifth");
    pageContext.setAttribute("list", data, PageContext.PAGE_SCOPE);
    java.util.HashMap temp = new java.util.HashMap();
    temp.put("First", "0");
    temp.put("Second", "1");
    temp.put("Third", "2");
    temp.put("Fourth", "3");
    temp.put("Fifth", "4");
    pageContext.setAttribute("map", temp, PageContext.PAGE_SCOPE);
%>
<%-- Change "${list}" to "${data}" to demonstrate NPE, and need for better
			 exception in "Sizetag.doStartTag()". --%>
<bean-el:size id="dataSize" collection="${list}"/>
<bean-el:size id="tempSize" collection="${map}"/>

<div align="center">
<h1>Test struts bean-el Size Tag</h1>
</div>

<jsp:useBean id="bean" scope="page" class="org.apache.struts.webapp.exercise.TestBean"/>
<bean-el:size id="stringSize" name="bean" property="stringArray"/>
<bean-el:size id="intSize" name="bean" property="intArray"/>
<jsp:useBean id="list" scope="page" class="java.util.ArrayList"/>
<bean-el:size id="listSize" name="list"/>
<jsp:useBean id="map" scope="page" class="java.util.HashMap"/>
<bean-el:size id="mapSize" name="map"/>

<table border="1">
  <tr>
    <th>Collection Type</th>
    <th>Correct Value</th>
    <th>Test Result</th>
  </tr>
  <tr>
    <td>Bean (List)</td>
    <td align="center"><%= list.size() %></td>
    <td align="center"><c:out value="${listSize}"/></td>
  </tr>
  <tr>
    <td>Bean (Map)</td>
    <td align="center"><%= map.size() %></td>
    <td align="center"><c:out value="${mapSize}"/></td>
  </tr>
  <tr>
    <td>Collection (List)</td>
    <td align="center"><%= data.size() %></td>
    <td align="center"><c:out value="${dataSize}"/></td>
  </tr>
  <tr>
    <td>Collection (Map)</td>
    <td align="center"><%= temp.size() %></td>
    <td align="center"><c:out value="${tempSize}"/></td>
  </tr>
  <tr>
    <td>Property (int[])</td>
    <td align="center"><% int intValues[] = bean.getIntArray(); out.print(intValues.length); %></td>
    <td align="center"><c:out value="${intSize}"/></td>
  </tr>
  <tr>
    <td>Property (String[])</td>
    <td align="center"><% String stringValues[] = bean.getStringArray(); out.print(stringValues.length); %></td>
    <td align="center"><c:out value="${stringSize}"/></td>
  </tr>
</table>

</html>
</body>
