<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"     %>
<%@ taglib uri="/WEB-INF/tiles.tld"  prefix="template" %>

<html:html>
  <head>
    <title>Content Form</title>
  </head>

  <body bgcolor="white">
      <bean:insert id='xout2' page='/testAction.do'/>
      <bean:write name='xout2' filter='true'/>

      <template:insert template="/forwardExampleAction.do">
        <template:put name="label"    content="Text:"       direct="true"/>
        <template:put name="property" content="contentForm" direct="true"/>
      </template:insert>

	
  </body>
</html:html>

