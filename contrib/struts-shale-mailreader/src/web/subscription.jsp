<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!--

 Copyright 2002,2004 The Apache Software Foundation.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

-->


<f:view>
<f:loadBundle    basename="org.apache.shale.examples.mailreader.ApplicationResources"
                      var="messages"/>
<html>
<head>
  <title><c:choose>
    <c:when          test="${state.mode == 'CREATE'}">
      <h:outputText    id="titleCreate"
                    value="#{messages['subscription.title.create']}"/>
    </c:when>
    <c:when          test="${state.mode == 'DELETE'}">
      <h:outputText    id="titleDelete"
                    value="#{messages['subscription.title.delete']}"/>
    </c:when>
    <c:when          test="${state.mode == 'Edit'}">
      <h:outputText    id="titleEdit"
                    value="#{messages['subscription.title.edit']}"/>
    </c:when>
  </c:choose></title>
</head>
<body>

<h:messages    globalOnly="true"/>

<h:form                id="subscription">

  <h:panelGrid    columns="3">

    <%-- Grid header element --%>

    <f:facet name="header">
      <h:panelGroup>
        <c:choose>
          <c:when    test="${state.mode == 'CREATE'}">
            <h:outputText
                       id="headerCreate"
                    value="#{messages['subscription.title.create']}"/>
          </c:when>
          <c:when    test="${state.mode == 'DELETE'}">
            <h:outputText
                       id="headerDelete"
                    value="#{messages['subscription.title.delete']}"/>
          </c:when>
          <c:when    test="${state.mode == 'EDIT'}">
            <h:outputText
                       id="headerEdit"
                    value="#{messages['subscription.title.edit']}"/>
          </c:when>
        </c:choose>
      </h:panelGroup>
    </f:facet>

    <%-- First row --%>

    <h:outputLabel    for="user">
      <h:outputText value="#{messages['prompt.username']}"/>
    </h:outputLabel>

    <h:outputText      id="user"
                    value="#{state.user.username}"/>

    <h:outputText   value=""/>

    <%-- Second row --%>

    <h:outputLabel    for="host">
      <h:outputText value="#{messages['prompt.mailHostname']}"/>
    </h:outputLabel>

    <c:choose>
      <c:when        test="${state.mode == 'CREATE'}">
        <h:inputText   id="host"
                     size="50"
                    value="#{subscription.host}"/>
        <h:message     id="hostMessages"
                      for="host"/>
      </c:when>
      <c:otherwise>
        <h:outputText  id="hostDisplay"
                    value="#{subscription.host}"/>
        <h:outputText  id="hostEmpty"
                    value=""/>
      </c:otherwise>
    </c:choose>

    <%-- Third row --%>

    <h:outputLabel    for="username">
      <h:outputText value="#{messages['prompt.mailUsername']}"/>
    </h:outputLabel>

    <h:inputText       id="username"
                     size="50"
                    value="#{subscription.username}"/>

    <h:message        for="username"/>

    <%-- Fourth row --%>

    <h:outputLabel    for="password">
      <h:outputText value="#{messages['prompt.mailPassword']}"/>
    </h:outputLabel>

    <h:inputText       id="password"
                     size="50"
                    value="#{subscription.password}"/>

    <h:message        for="username"/>

    <%-- Fifth row --%>

    <h:outputLabel    for="type">
      <h:outputText value="#{messages['prompt.mailServerType']}"/>
    </h:outputLabel>

    <h:selectOneMenu   id="type"
                    value="#{subscription.type}">
      <f:selectItems
                    value="#{protocols}"/>
    </h:selectOneMenu>

    <h:message        for="type"/>

    <%-- Sixth row --%>

    <h:outputLabel    for="autoConnect">
      <h:outputText value="#{messages['prompt.autoConnect']}"/>
    </h:outputLabel>

    <h:selectBooleanCheckbox
                       id="autoConnect"
                    value="#{subscription.autoConnect}"/>

    <h:message        for="autoConnect"/>

    <%-- Seventh row --%>

    <c:choose>
      <c:when        test="${state.mode == 'DELETE'}">
        <h:commandButton
                       id="confirm"
                   action="#{subscription.save}"
                     type="SUBMIT"
                    value="#{messages['button.confirm']}"/>
      </c:when>
      <c:otherwise>
        <h:commandButton
                       id="save"
                   action="#{subscription.save}"
                     type="SUBMIT"
                    value="#{messages['button.save']}"/>
      </c:otherwise>
    </c:choose>

    <h:panelGroup      id="reset_and_cancel">
      <h:commandButton id="reset"
                     type="RESET"
                    value="#{messages['button.reset']}"/>
      <h:commandButton id="cancel"
                   action="#{subscription.cancel}"
                     type="SUBMIT"
                    value="#{messages['button.cancel']}"/>
    </h:panelGroup>

  </h:panelGrid>

</h:form>

</body>
</html>
</f:view>
