<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <title>
    <h:outputText   value="#{messages['registration.title.create']}"
                 rendered="#{state.mode == 'CREATE'}"/>
    <h:outputText   value="#{messages['registration.title.edit']}"
                 rendered="#{state.mode == 'EDIT'}"/>
  </title>
</head>
<body>

<h:messages    globalOnly="true"/>

<h:form                id="registration">

  <h:panelGrid    columns="3">

    <%-- Grid header element --%>

    <f:facet name="header">
      <h:panelGroup>
        <h:outputText
                    value="#{messages['registration.header.create']}"
                 rendered="#{state.mode == 'CREATE'}"/>
        <h:outputText
                    value="#{messages['registration.header.edit']}"
                 rendered="#{state.mode == 'EDIT'}"/>
      </h:panelGroup>
    </f:facet>

    <%-- First row --%>

    <h:outputLabel    for="username"
               styleClass="label">
      <h:outputText value="#{messages['prompt.username']}"/>
    </h:outputLabel>

    <h:panelGroup>
      <h:inputText     id="username"
                 rendered="#{state.mode == 'CREATE'}"
                 required="true"
                     size="16"
                    value="#{registration.username}"/>
      <h:outputText value="#{registration.username}"
                 rendered="#{state.mode == 'EDIT'}"/>
    </h:panelGroup>

    <h:panelGroup>
      <h:message      for="username"
                 rendered="#{state.mode == 'CREATE'}"/>
      <h:outputText value=""
                 rendered="#{state.mode == 'EDIT'}"/>
    </h:panelGroup>

    <%-- Second row --%>

    <h:outputLabel    for="password">
      <h:outputText value="#{messages['prompt.password']}"/>
    </h:outputLabel>

    <h:inputText       id="password"
                     size="16"
                 required="#{state.mode == 'CREATE'}"
                    value="#{registration.password}"/>

    <h:message        for="password"/>

    <%-- Third row --%>

    <h:outputLabel    for="password2">
      <h:outputText value="#{messages['prompt.password2']}"/>
    </h:outputLabel>

    <h:inputText       id="password2"
                     size="16"
                 required="#{state.mode == 'CREATE'}"
                    value="#{registration.password2}"/>

    <h:message        for="password2"/>

    <%-- Fourth row --%>

    <h:outputLabel    for="fullName">
      <h:outputText value="#{messages['prompt.fullName']}"/>
    </h:outputLabel>

    <h:inputText       id="fullName"
                     size="50"
                 required="true"
                    value="#{registration.fullName}"/>

    <h:message        for="fullName"/>

    <%-- Fifth row --%>

    <h:outputLabel    for="fromAddress">
      <h:outputText value="#{messages['prompt.fromAddress']}"/>
    </h:outputLabel>

    <h:inputText       id="fromAddress"
                     size="50"
                 required="true"
                    value="#{registration.fromAddress}"/>

    <h:message        for="fromAddress"/>

    <%-- Sixth row --%>

    <h:outputLabel    for="replyToAddress">
      <h:outputText value="#{messages['prompt.replyToAddress']}"/>
    </h:outputLabel>

    <h:inputText       id="replyToAddress"
                     size="50"
                    value="#{registration.replyToAddress}"/>

    <h:message        for="replyToAddress"/>

    <%-- Seventh row --%>

    <h:commandButton   id="submit"
                   action="#{registration.save}"
                     type="SUBMIT"
                    value="#{messages['button.save']}"/>

    <h:panelGroup>
      <h:commandButton id="reset"
                     type="RESET"
                    value="#{messages['button.reset']}"/>
      <h:commandButton id="cancel"
                     type="SUBMIT"
                   action="#{registration.cancel}"
                    value="#{messages['button.cancel']}"/>
    </h:panelGroup>

  </h:panelGrid>

  <h:dataTable         id="table"
                 rendered="#{state.mode == 'EDIT'}"
                    value="#{registration.subscriptions}"
                      var="current">

    <h:column>
      <f:facet       name="header">
        <h:outputText
                    value="#{messages['heading.host']}"/>
      </f:facet>
      <h:outputText    id="subHost"
                    value="#{current.host}"/>
    </h:column>

    <h:column>
      <f:facet       name="header">
        <h:outputText
                    value="#{messages['heading.user']}"/>
      </f:facet>
      <h:outputText    id="subUsername"
                    value="#{current.username}"/>
    </h:column>

    <h:column>
      <f:facet       name="header">
        <h:outputText
                    value="#{messages['heading.type']}"/>
      </f:facet>
      <h:outputText    id="subType"
                    value="#{current.type}"/>
    </h:column>

    <h:column>
      <f:facet       name="header">
        <h:outputText
                    value="#{messages['heading.autoConnect']}"/>
      </f:facet>
      <h:outputText    id="subAutoConnect"
                    value="#{current.autoConnect}"/>
    </h:column>

    <h:column>
      <f:facet       name="header">
        <h:outputText
                    value="#{messages['heading.action']}"/>
      </f:facet>
      <h:commandButton id="delete"
                   action="#{registration.delete}"
                immediate="true"
                    value="#{messages['button.delete']}"/>
      <h:commandButton id="edit"
                   action="#{registration.edit}"
                immediate="true"
                    value="#{messages['button.edit']}"/>
    </h:column>

  </h:dataTable>

  <h:commandButton     id="create"
                   action="#{registration.create}"
                immediate="true"
                 rendered="#{state.mode == 'EDIT'}"
                    value="#{messages['button.add']}"/>

</h:form>

</body>
</html>
</f:view>
