<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>

<%-- Test tags basic behaviors 
--%>
<hr>
<strong>Basic template usage</strong>
<br>
<template:insert template="layout.jsp">
  <template:put name="title"  value="Test with default no types" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="body.jsp" />
</template:insert>

<hr>
<strong>Specify attribute types</strong>
<br>
<template:insert template="layout.jsp">
  <template:put name="title"  value="Test with specified types"   type="string" />
  <template:put name="header" value="header.jsp" type="page"   />
  <template:put name="body"   value="body.jsp"   type="page"   />
</template:insert>

<hr>
<strong>Set attribute value with tag body</strong>
<br>
<template:insert template="layout.jsp">
  <template:put name="title"  value="Test with a tag body" />
  <template:put name="header" type="string">
    <strong>This header is inserted as body of tag</strong>
  </template:put>
  <template:put name="body"   value="body.jsp"/>
</template:insert>

<hr>
<strong>Use of definition</strong>
<br>
<template:definition id="templateDefinition" template="layout.jsp">
  <template:put name="title"  value="Use of definition" />
  <template:put name="header" value="header.jsp" />
  <template:put name="body"   value="body.jsp"   />
</template:definition>
<template:insert beanName="templateDefinition" />

<hr>
<strong>Use of definition, overload of parameters </strong>Title parameter
from previous definition is overloaded
<br>
<template:insert beanName="templateDefinition" >
  <template:put name="title"  value="Use of definition, overload of parameters"   type="string" />
</template:insert>

<hr>
<strong>Test ignore : body isn't defined </strong>(We use another layout)
<br>
<template:insert template="layoutTestIgnore.jsp">
  <template:put name="title"  value="Test ignore : body isn't defined" />
  <template:put name="header" value="header.jsp" />
</template:insert>

 