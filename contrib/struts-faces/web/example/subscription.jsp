<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<%-- FIXME <a:checkLogon/> --%>

<f:view>
<s:html locale="true">
<head>
  <title><c:choose>
    <c:when test="${subscriptionForm.action == 'Create'}">
      <s:message id="titleCreate" key="subscription.title.create"/>
    </c:when>
    <c:when test="${subscriptionForm.action == 'Delete'}">
      <s:message id="titleDelete" key="subscription.title.delete"/>
    </c:when>
    <c:when test="${subscriptionForm.action == 'Edit'}">
      <s:message id="titleEdit" key="subscription.title.edit"/>
    </c:when>
    <c:otherwise>
      UNKNOWN ACTION
    </c:otherwise>
  </c:choose></title>
  <s:base/>
  <s:stylesheet path="/stylesheet.css"/>
</head>
<body bgcolor="white">

<s:errors/>

<s:form action="/saveSubscription" focus="host">

  <h:input_hidden id="action" value="#{subscriptionForm.action}"/>

  <h:panel_grid
           columns="2"
        styleClass="form-background"
       headerClass="form-header"
     columnClasses="form-prompt,form-field">

    <%-- Grid header element --%>

    <f:facet name="header">
      <h:panel_group>
        <c:choose>
          <c:when test="${subscriptionForm.action == 'Create'}">
            <s:message id="headerCreate" key="subscription.title.create"/>
          </c:when>
          <c:when test="${subscriptionForm.action == 'Delete'}">
            <s:message id="headerDelete" key="subscription.title.delete"/>
          </c:when>
          <c:when test="${subscriptionForm.action == 'Edit'}">
            <s:message id="headerEdit" key="subscription.title.edit"/>
          </c:when>
          <c:otherwise>
            <h:output_text id="headerUnknown" value="UNKNOWN ACTION"/>
          </c:otherwise>
        </c:choose>
      </h:panel_group>
    </f:facet>

    <%-- Grid data elements --%>

    <h:output_label for="user">
      <s:message key="prompt.username"/>
    </h:output_label>

    <s:write id="user" filter="true"
          value="#{user.username}"/>

    <h:output_label for="host">
      <s:message key="prompt.mailHostname"/>
    </h:output_label>

    <c:choose>
      <c:when test="${subscriptionForm.action == 'Create'}">
        <h:input_text id="host" size="50"
                   value="#{subscriptionForm.host}"/>
      </c:when>
      <c:otherwise>
        <h:panel_group id="hostGroup">
          <s:write id="hostDisplay" filter="true"
                                     value="#{subscriptionForm.host}"/>
          <h:input_hidden id="host"  value="#{subscriptionForm.host}"/>
        </h:panel_group>
      </c:otherwise>
    </c:choose>

    <h:output_label for="username">
      <s:message key="prompt.mailUsername"/>
    </h:output_label>

    <h:input_text id="username"
                size="50"
               value="#{subscriptionForm.username}"/>

    <h:output_label for="password">
      <s:message key="prompt.mailPassword"/>
    </h:output_label>

    <h:input_text id="password"
                size="50"
               value="#{subscriptionForm.password}"/>

    <h:output_label for="type">
      <s:message key="prompt.mailServerType"/>
    </h:output_label>

    <h:selectone_menu id="type"
                   value="#{subscriptionForm.type}">
      <f:selectitem itemValue="imap" itemLabel="IMAP Protocol"/>
      <f:selectitem itemValue="pop3" itemLabel="POP3 Protocol"/>
    </h:selectone_menu>

    <h:output_label for="autoConnect">
      <s:message key="prompt.autoConnect"/>
    </h:output_label>

    <h:selectboolean_checkbox id="autoConnect"
                           value="#{subscriptionForm.autoConnect}"/>

    <c:choose>
      <c:when test="${subscriptionForm.action == 'Delete'}">
        <h:command_button id="confirm"
                        type="SUBMIT"
                  styleClass="command-single"
                       value="Confirm"/>  <%-- FIXME - i18n --%>
      </c:when>
      <c:otherwise>
        <h:command_button id="save"
                        type="SUBMIT"
                  styleClass="command-single"
                       value="Save"/>     <%-- FIXME - i18n --%>
      </c:otherwise>
    </c:choose>

    <h:panel_group id="reset_and_cancel">
      <h:command_button id="reset"
                      type="RESET"
                styleClass="command-multiple"
                     value="Reset"/>       <%-- FIXME - i18n --%>
      <h:command_button id="cancel"
                      type="SUBMIT"
                styleClass="command-multiple"
                     value="Cancel"/>     <%-- FIXME - i18n --%>
    </h:panel_group>

  </h:panel_grid>

</s:form>

</body>
</s:html>
</f:view>
