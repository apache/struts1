<%@ taglib uri="/WEB-INF/tiles.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<template:insert template='/test/templateLayout.jsp'>
  <template:put name="title"  content="My first page" direct="true"/>
  <template:put name="header" content="/common/header.jsp" direct="true"/>
  <template:put name="footer" content="/common/footer.jsp" />
  <template:put name="menu"   content="/basic/menu.jsp" direct="true"/>
   <template:put name="body" content='/testAction.do' type="page"/>
</template:insert>

<%--
   <template:put name="body" content='/testAction2.do'/>
   <template:put name="body" direct='true'>
      <bean:insert id='xout2' page='/forwardExampleAction.do'/>
      <bean:write name='xout2' filter='false'/>
    </template:put>
--%>