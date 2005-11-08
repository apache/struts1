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
      <title>simple</title>
      <s:base/>
      <s:stylesheet path="/stylesheet.css"/>
    </head>
    <body>

      <s:message id="message-direct"
             bundle="messages"
                key="resource.simple"/>
      <s:message id="message-indirect"
             bundle="messages"
              value="#{simple.simpleResource}"/>
      <s:message id="message-filtered"
             bundle="messages"
                key="resource.filtered"/>
      <s:message id="message-unfiltered"
             bundle="messages"
             filter="false"
                key="resource.unfiltered"/>
      <s:message id="message-substitute"
             bundle="messages"
                key="resource.substitute">
        <f:param
              value="Here"/>
        <f:param
              value="#{simple.eternity}"/>
      </s:message>

      <s:loadMessages
           messages="messages"
                var="lookup"/>
      <s:write   id="lookup-simple"
              value="#{lookup['resource.simple']}"/>
      <s:write   id="lookup-filtered"
              value="#{lookup['resource.filtered']}"/>
      <s:write   id="lookup-unfiltered"
             filter="false"
              value="#{lookup['resource.unfiltered']}"/>

      <s:write   id="write-literal"
              value="Literal Write Content"/>
      <s:write   id="write-filtered"
              value="Literal <b>Filtered</b> Content"/>
      <s:write   id="write-unfiltered"
             filter="false"
              value="Literal <b>Unfiltered</b> Content"/>

      <s:write   id="retrieved-literal"
              value="#{simple.simpleContent}"/>
      <s:write   id="retrieved-filtered"
              value="#{simple.filteredContent}"/>
      <s:write   id="retrieved-unfiltered"
             filter="false"
              value="#{simple.unfilteredContent}"/>

      <f:subview id="subview">
        <s:write id="write1"
            binding="#{binding.write1}"
              value="#{binding.write1ClientId}"/>
      </f:subview>

    </body>
  </s:html>
</f:view>
