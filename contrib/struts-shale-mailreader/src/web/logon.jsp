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
    <h:outputText   value="#{messages['logon.title']}"/>
  </title>
</head>
<body>

<h:messages    globalOnly="true"/>

<h:form                id="logon">

  <h:panelGrid    columns="3">

    <%-- Form header --%>

    <f:facet name="header">
      <h:outputText value="#{messages['logon.header']}"/>
    </f:facet>

    <%-- First row --%>

    <h:outputLabel    for="username">
      <h:outputText value="#{messages['prompt.username']}"/>
    </h:outputLabel>

    <h:inputText       id="username"
                 required="true"
                     size="16"
                    value="#{logon.username}"/>

    <h:message        for="username"/>

    <%-- Second row --%>

    <h:outputLabel    for="password">
      <h:outputText value="#{messages['prompt.password']}"/>
    </h:outputLabel>

    <h:inputSecret     id="password"
                 required="true"
                     size="16"
                    value="#{logon.password}"/>

    <h:message        for="password"/>

    <%-- Third row --%>

    <h:commandButton   id="submit"
                     type="SUBMIT"
                   action="#{logon.logon}"
                    value="#{messages['button.logon']}"/>

    <h:commandButton   id="reset"
                     type="RESET"
                    value="#{messages['button.reset']}"/>

    <%-- Form footer --%>

    <f:facet name="footer">
      <h:outputText value="#{messages['logon.footer']}"/>
    </f:facet>

  </h:panelGrid>

</h:form>

</body>
</html>
</f:view>
