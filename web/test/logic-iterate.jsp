<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<title>Test struts-logic Iterate Tag</title>
</head>
<body bgcolor="white">

<%
  {
    java.util.ArrayList list = new java.util.ArrayList();
    list.add("First");
    list.add("Second");
    list.add("Third");
    list.add("Fourth");
    list.add("Fifth");
    pageContext.setAttribute("list", list, PageContext.PAGE_SCOPE);
  }
%>

<div align="center">
<h1>Test struts-logic Iterate Tag</h1>
</div>

<jsp:useBean id="bean" scope="page" class="org.apache.struts.test.TestBean"/>
<jsp:useBean id="list" scope="page" class="java.util.ArrayList"/>

<h3>Test 1 - Iterate Over A String Array [0..4]</h3>

<ol>
<logic:iterate id="element" name="bean" property="stringArray">
  <li><em><bean:write name="element"/></em></li>
</logic:iterate>
</ol>

<h3>Test 2 - Iterate Over A String Array [0..2]</h3>

<ol>
<logic:iterate id="element" name="bean" property="stringArray" length="3">
  <li><em><bean:write name="element"/></em></li>
</logic:iterate>
</ol>

<h3>Test 3 - Iterate Over A String Array [3..4]</h3>

<ol>
<logic:iterate id="element" name="bean" property="stringArray" offset="3">
  <li><em><bean:write name="element"/></em></li>
</logic:iterate>
</ol>

<h3>Test 4 - Iterate Over A String Array [1..3]</h3>

<ol>
<logic:iterate id="element" name="bean" property="stringArray"
               offset="1" length="3">
  <li><em><bean:write name="element"/></em></li>
</logic:iterate>
</ol>

<h3>Test 5 - Iterate Over an Array List</h3>

<ol>
<logic:iterate id="item" name="list">
  <li><em><bean:write name="item"/></em></li>
</logic:iterate>
</ol>

<h3>Test 6 - Iterate Over an Array List [0..2]</h3>

<ol>
<logic:iterate id="item" name="list"
       offset="0" length="3">
  <li><em><bean:write name="item"/></em></li>
</logic:iterate>
</ol>

<h3>Test 7 - Iterate Over an Array List [2..4]</h3>

<ol>
<logic:iterate id="item" name="list"
       offset="2" length="3">
  <li><em><bean:write name="item"/></em></li>
</logic:iterate>
</ol>

</body>
</html>
