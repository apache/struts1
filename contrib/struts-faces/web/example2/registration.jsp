<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<%--
<f:view>
<s:html locale="true">
<head>
  <title><c:choose>
    <c:when test="${registrationForm.action == 'Create'}">
      <s:message key="registration.title.create"/>
    </c:when>
    <c:when test="${registrationForm.action == 'Edit'}">
      <s:message key="registration.title.edit"/>
    </c:when>
    <c:otherwise>
      UNKNOWN ACTION
    </c:otherwise>
  </c:choose></title>
  <s:base/>
  <s:stylesheet path="/stylesheet.css"/>
</head>
<body bgcolor="white">
--%>

<s:errors/>

<s:form action="/saveRegistration" focus="username"
         onsubmit="return validateRegistrationForm(this);">

  <h:input_hidden id="action" value="#{registrationForm.action}"/>

  <h:panel_grid
           columns="2"
        styleClass="form-background"
       headerClass="form-header"
     columnClasses="form-prompt,form-field">

    <%-- Grid header element --%>

    <f:facet name="header">
      <h:panel_group>
        <c:choose>
          <c:when test="${registrationForm.action == 'Create'}">
            <s:message key="registration.header.create"/>
          </c:when>
          <c:when test="${registrationForm.action == 'Edit'}">
            <s:message key="registration.header.edit"/>
          </c:when>
          <c:otherwise>
            <h:output_text id="unknownActionTitle" value="UNKNOWN ACTION"/>
          </c:otherwise>
        </c:choose>
      </h:panel_group>
    </f:facet>

    <%-- Grid data elements --%>

    <h:output_label for="username">
      <s:message key="prompt.username"/>
    </h:output_label>

    <h:panel_group>
      <c:choose>
        <c:when test="${registrationForm.action == 'Create'}">
          <h:input_text id="username" size="16"
                     value="#{registrationForm.username}"/>
        </c:when>
        <c:when test="${registrationForm.action == 'Edit'}">
          <h:panel_group id="usernameGroup">
            <s:write filter="true"
                      value="#{registrationForm.username}"/>
            <h:input_hidden id="username"
                         value="#{registrationForm.username}"/>
          </h:panel_group>
        </c:when>
        <c:otherwise>
          <h:output_text id="unknownActionMessage" value="UNKNOWN ACTION"/>
        </c:otherwise>
      </c:choose>
    </h:panel_group>

    <h:output_label for="password">
      <s:message key="prompt.password"/>
    </h:output_label>

    <h:input_text id="password"
                size="16"
               value="#{registrationForm.password}"/>

    <h:output_label for="password2">
      <s:message key="prompt.password2"/>
    </h:output_label>

    <h:input_text id="password2"
                size="16"
               value="#{registrationForm.password2}"/>

    <h:output_label for="fullName">
      <s:message key="prompt.fullName"/>
    </h:output_label>

    <h:input_text id="fullName"
                size="50"
               value="#{registrationForm.fullName}"/>

    <h:output_label for="fromAddress">
      <s:message key="prompt.fromAddress"/>
    </h:output_label>

    <h:input_text id="fromAddress"
                size="50"
               value="#{registrationForm.fromAddress}"/>

    <h:output_label for="replyToAddress">
      <s:message key="prompt.replyToAddress"/>
    </h:output_label>

    <h:input_text id="replyToAddress"
                size="50"
               value="#{registrationForm.replyToAddress}"/>

    <h:command_button id="submit" type="SUBMIT"
              styleClass="command-single"
                   value="Save"/>                        <%-- FIXME - i18n --%>

    <h:panel_group>
      <h:command_button id="reset" type="RESET"
                styleClass="command-multiple"
                     value="Reset"/>                     <%-- FIXME - i18n --%>
      <h:command_button id="cancel" type="SUBMIT"
                styleClass="command-multiple"
                     value="Cancel"/>                    <%-- FIXME - i18n --%>
    </h:panel_group>

  </h:panel_grid>

  <s:javascript formName="registrationForm"
       dynamicJavascript="true"
        staticJavascript="false"/>
  <script language="Javascript1.1" src="staticJavascript.jsp"></script>

</s:form>

<c:if test="${registrationForm.action == 'Edit'}">

<h:form id="subscriptions">

  <h:data_table         id="table"
             columnClasses="list-column-host,list-column-user,list-column-type,
                            list-column-auto,list-column-action"
               headerClass="list-header"
                styleClass="list-background"
                rowClasses="list-row-even,list-row-odd"
                     value="#{user.subscriptions}"
                       var="subscription">

    <h:column           id="hostColumn">
      <f:facet        name="header">
        <s:message      id="hostHeader"
                       key="heading.host"/>
      </f:facet>
      <h:output_text    id="subhost"
                     value="#{subscription.host}"/>
    </h:column>

    <h:column           id="usernameColumn">
      <f:facet        name="header">
        <s:message      id="usernameHeader"
                       key="heading.user"/>
      </f:facet>
      <h:output_text    id="subusername"
                     value="#{subscription.username}"/>
    </h:column>

    <h:column           id="typeColumn">
      <f:facet        name="header">
        <s:message      id="typeHeader"
                       key="heading.type"/>
      </f:facet>
      <h:output_text    id="subtype"
                     value="#{subscription.type}"/>
    </h:column>

    <h:column           id="autoConnectColumn">
      <f:facet        name="header">
        <s:message      id="autoConnectHeader"
                       key="heading.autoConnect"/>
      </f:facet>
      <h:output_text    id="subauto"
                     value="#{subscription.autoConnect}"/>
    </h:column>

    <h:column           id="actionColumn">
      <f:facet        name="header">
        <s:message      id="actionHeader"
                       key="heading.action"/>
      </f:facet>
      <h:command_button id="delete"
                styleClass="command-multiple"
                 immediate="true"
                    action="#{registrationBacking.delete}"
                     value="#{registrationBacking.deleteLabel}"/>
      <h:command_button id="edit"
                styleClass="command-multiple"
                 immediate="true"
                    action="#{registrationBacking.edit}"
                     value="#{registrationBacking.editLabel}"/>
    </h:column>

  </h:data_table>

  <h:command_button     id="create"
                 immediate="true"
                    action="#{registrationBacking.create}"
                     value="Add New"/>

</h:form>

</c:if>
<%--
</body>
</s:html>
</f:view>
--%>
