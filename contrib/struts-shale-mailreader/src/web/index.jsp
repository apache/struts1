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
    <h:outputText   value="#{messages['index.title']}"/>
  </title>
</head>
<body>

<h:form                id="indexForm">

  <h:panelGrid    columns="1">

    <f:facet         name="header">
      <h:outputText value="#{messages['index.heading']}"/>
    </f:facet>

    <h:commandLink     id="create"
                   action="#{index.create}"
                immediate="true">
      <h:outputText value="#{messages['index.registration']}"/>
    </h:commandLink>

    <h:commandLink     id="logon"
                   action="logon"
                immediate="true">
      <h:outputText value="#{messages['index.logon']}"/>
    </h:commandLink>

  </h:panelGrid>

</h:form>

</body>
</html>
</f:view>
