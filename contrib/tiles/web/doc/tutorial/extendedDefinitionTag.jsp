<%@ taglib uri="/WEB-INF/tiles.tld" prefix="comp" %>

<comp:definition id="definitionName" extends="myFirstDefinition" >
  <comp:put name="title"  value="My first extended definition tag page" />
</comp:definition>

<comp:insert beanName="definitionName" flush="true" />
