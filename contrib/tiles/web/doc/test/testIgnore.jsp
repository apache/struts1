<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>

<%-- Test 'ignore' attribute : 
  Insert components/templates with undefined attributes, or bad urls.
  Undefined must be spkipped, while false urls must output exception.
--%>
<hr>
<strong>Test ignore : body isn't defined</strong>
<br>
<template:insert template="layoutTestIgnore.jsp">
  <template:put name="title"  value="Test ignore : body isn't defined" />
  <template:put name="header" value="header.jsp" />
</template:insert>

<hr>
<strong>Test ignore : bad body definition name (exception must be shown)</strong>
<br>
<template:insert template="layoutTestIgnore.jsp">
  <template:put name="title"  value="Test ignore : bad body definition name (exception must be shown)" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="badDefinitionName" type="definition" />
</template:insert>

<hr>
<strong>Test ignore : Definition not found (no errors, no insertion)</strong>
<br>
<template:definition id="templateDefinition" template="layout.jsp">
  <template:put name="title"  value="Test ignore : Definition not found (no errors, no insertion)" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="body.jsp"   />
</template:definition>
<template:insert beanName="badTemplateDefinitionName" ignore="true"/>

<hr>
<strong>Test ignore : bad body urls (exception must be shown)</strong>
<br>
<template:insert template="layoutTestIgnore.jsp">
  <template:put name="title"  value="Test ignore : bad body urls (exception must be shown)" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="body2.jsp"/>
</template:insert>

