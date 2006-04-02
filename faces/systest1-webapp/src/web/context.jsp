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
      <title>context</title>
    </head>
    <body>

      <s:form             id="form"
                      action="/context">

      <h:panelGrid   columns="2">

        <%-- ViewRoot Values --%>

        <h:outputText  value="FacesContext.getViewRoot()"/>
        <h:outputText  value="==============================================="/>

        <h:outputText  value="renderKitId"/>
        <h:outputText     id="renderKitIdFC"
                       value="#{facesContext.viewRoot.renderKitId}"/>

        <h:outputText  value="viewId"/>
        <h:outputText     id="viewIdFC"
                       value="#{facesContext.viewRoot.viewId}"/>

        <%-- ExternalContext Values --%>

        <h:outputText  value="ExternalContext"/>
        <h:outputText  value="==============================================="/>

        <h:outputText  value="authType"/>
        <h:outputText     id="authTypeEC"
                       value="#{facesContext.externalContext.authType}"/>

        <h:outputText  value="remoteUser"/>
        <h:outputText     id="remoteUserEC"
                       value="#{facesContext.externalContext.remoteUser}"/>

        <h:outputText  value="requestContextPath"/>
        <h:outputText     id="requestContextPathEC"
                       value="#{facesContext.externalContext.requestContextPath}"/>

        <h:outputText  value="requestLocale"/>
        <h:outputText     id="requestLocaleEC"
                       value="#{facesContext.externalContext.requestLocale}"/>

        <h:outputText  value="requestPathInfo"/>
        <h:outputText     id="requestPathInfoEC"
                       value="#{facesContext.externalContext.requestPathInfo}"/>

        <h:outputText  value="requestServletPath"/>
        <h:outputText     id="requestServletPathEC"
                       value="#{facesContext.externalContext.requestServletPath}"/>

        <%-- Request Values --%>

        <h:outputText  value="HttpServletRequest"/>
        <h:outputText  value="==============================================="/>

        <h:outputText  value="authType"/>
        <h:outputText     id="authTypeRQ"
                       value="#{facesContext.externalContext.request.authType}"/>

        <h:outputText  value="contextPath"/>
        <h:outputText     id="contextPathRQ"
                       value="#{facesContext.externalContext.request.contextPath}"/>

        <h:outputText  value="locale"/>
        <h:outputText     id="localeRQ"
                       value="#{facesContext.externalContext.request.locale}"/>

        <h:outputText  value="pathInfo"/>
        <h:outputText     id="pathInfoRQ"
                       value="#{facesContext.externalContext.request.pathInfo}"/>

        <h:outputText  value="remoteUser"/>
        <h:outputText     id="remoteUserRQ"
                       value="#{facesContext.externalContext.request.remoteUser}"/>

        <h:outputText  value="servletPath"/>
        <h:outputText     id="servletPathRQ"
                       value="#{facesContext.externalContext.request.servletPath}"/>

        <%-- ServletContext Values --%>

        <h:outputText  value="ServletContext"/>
        <h:outputText  value="==============================================="/>

        <h:outputText  value="majorVersion"/>
        <h:outputText     id="majorVersionSC"
                       value="#{facesContext.externalContext.context.majorVersion}"/>

        <h:outputText  value="minorVersion"/>
        <h:outputText     id="minorVersionSC"
                       value="#{facesContext.externalContext.context.minorVersion}"/>

        <h:outputText  value="serverInfo"/>
        <h:outputText     id="serverInfoSC"
                       value="#{facesContext.externalContext.context.serverInfo}"/>

        <h:outputText  value="servletContextName"/>
        <h:outputText     id="servletContextNameSC"
                       value="#{facesContext.externalContext.context.servletContextName}"/>

        <%-- Submit Buttons --%>

        <h:commandButton  id="submit"
                        type="SUBMIT"
                       value="Submit"/>
        <h:commandButton  id="reset"
                        type="RESET"
                       value="Reset"/>

      </h:panelGrid>

      </s:form>

    </body>
  </s:html>
</f:view>
