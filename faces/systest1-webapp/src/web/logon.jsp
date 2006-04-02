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

 $Id$

-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://struts.apache.org/tags-faces" %>

<f:view>
  <s:html locale="true" xhtml="true">
    <head>
      <title>logon</title>
    </head>
    <body>

      <s:loadMessages
                    messages="messages"
                         var="messages"/>

      <s:errors           id="globalErrors"
                      bundle="messages"/>

      <s:form             id="form"
                      action="/logon">

      <h:panelGrid   columns="3">

        <h:outputText     id="usernamePrompt"
                       value="#{messages['prompt.username']}"/>
        <h:inputText      id="username"
                       value="#{logonForm.username}"/>
        <s:errors         id="usernameErrors"
                      bundle="messages"
                    property="username"/>

        <h:outputText     id="passwordPrompt"
                       value="#{messages['prompt.password']}"/>
        <h:inputSecret    id="password"
                       value="#{logonForm.password}"/>
        <s:errors         id="passwordErrors"
                      bundle="messages"
                    property="password"/>

        <h:commandButton  id="submit"
                        type="SUBMIT"
                       value="#{messages['button.logon']}"/>
        <h:commandButton  id="reset"
                        type="RESET"
                       value="#{messages['button.reset']}"/>
        <h:commandButton  id="cancel"
                        type="SUBMIT"
                       value="#{messages['button.cancel']}"/>

        <s:commandLink    id="submit2"
                       value="#{messages['button.logon']}"/>

      </h:panelGrid>

      </s:form>

    </body>
  </s:html>
</f:view>
