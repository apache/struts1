<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>

<%-- Test template definitions (from factory) 
--%>
<hr>
<strong>Insert definition defined directly in jsp page</strong>
<template:definition id="definition" template="/test/layout.jsp" >
  <template:put name="title"  value="Test definition defined in jsp page" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="body.jsp" />
</template:definition>

<br>
<template:insert beanName="definition"/>

<hr>
<strong>Insert definition defined in factory</strong>
<br>
<template:insert definition="test.layout.test1"/>

<hr>
<strong>Insert definition defined in factory</strong>
<br>
<template:insert definition="test.layout.test2"/>

<hr>
<strong>Insert definition defined in factory</strong>
<br>
<template:insert definition="test.layout.test3"/>

<hr>
<strong>Insert definition defined in factory : Overload title attribute</strong>
<br>
<template:insert definition="test.layout.test3">
  <template:put name="title" value="Test definition : overload attribute 'title'" />
</template:insert>
