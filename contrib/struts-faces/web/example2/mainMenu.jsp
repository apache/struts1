<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<h:panelGrid      columns="1">

  <f:facet           name="header">
    <h:outputText   value="#{messages['mainMenu.heading']} #{user.username}"/>
  </f:facet>

  <h:outputLink     value="editRegistration.do">
    <f:param         name="action"
                    value="Edit"/>
    <h:outputText   value="#{messages['mainMenu.registration']}"/>
  </h:outputLink>

  <h:outputLink     value="logoff.do">
    <h:outputText   value="#{messages['mainMenu.logoff']}"/>
  </h:outputLink>

</h:panelGrid>


