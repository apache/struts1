<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" uri="/WEB-INF/app.tld"     %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<%-- FIXME
<c:if test="${registrationForm.action == 'Edit'}">
  <a:checkLogon/>
</c:if>
--%>

<f:use_faces>
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

<s:errors/>

<s:form action="/saveRegistration" focus="username"
         onsubmit="return validateRegistrationForm(this);">

  <h:input_hidden id="action" valueRef="registrationForm.action"/>

  <h:panel_grid
           columns="2"
        panelClass="form-background"
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
            valueRef="registrationForm.username"/>
        </c:when>
        <c:when test="${registrationForm.action == 'Edit'}">
          <h:panel_group id="usernameGroup">
            <s:write filter="true"
             valueRef="registrationForm.username"/>
            <h:input_hidden id="username"
                valueRef="registrationForm.username"/>
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
                size="16" valueRef="registrationForm.password"/>

    <h:output_label for="password2">
      <s:message key="prompt.password2"/>
    </h:output_label>

    <h:input_text id="password2"
                size="16" valueRef="registrationForm.password2"/>

    <h:output_label for="fullName">
      <s:message key="prompt.fullName"/>
    </h:output_label>

    <h:input_text id="fullName"
                size="50" valueRef="registrationForm.fullName"/>

    <h:output_label for="fromAddress">
      <s:message key="prompt.fromAddress"/>
    </h:output_label>

    <h:input_text id="fromAddress"
                size="50" valueRef="registrationForm.fromAddress"/>

    <h:output_label for="replyToAddress">
      <s:message key="prompt.replyToAddress"/>
    </h:output_label>

    <h:input_text id="replyToAddress"
                size="50" valueRef="registrationForm.replyToAddress"/>

    <h:command_button id="submit" type="SUBMIT" commandName="submit"
            commandClass="command-single"
                   label="Save"/>                        <%-- FIXME - i18n --%>

    <h:panel_group>
      <h:command_button id="reset" type="RESET" commandName="reset"
              commandClass="command-multiple"
                     label="Reset"/>                     <%-- FIXME - i18n --%>
      <h:command_button id="cancel" type="SUBMIT" commandName="cancel"
              commandClass="command-multiple"
                     label="Cancel"/>                    <%-- FIXME - i18n --%>
    </h:panel_group>

  </h:panel_grid>

</s:form>

<c:if test="${registrationForm.action == 'Edit'}">

  <h:panel_list id="panel"
     columnClasses="list-column-host,list-column-user,list-column-type,list-column-auto,list-column-action"
       headerClass="list-header"
        panelClass="list-background"
        rowClasses="list-row-even,list-row-odd">

    <%-- Column Headings --%>
    <f:facet name="header">
      <h:panel_group id="headers">
        <s:message key="heading.host"/>
        <s:message key="heading.user"/>
        <s:message key="heading.type"/>
        <s:message key="heading.autoConnect"/>
        <s:message key="heading.action"/>
      </h:panel_group>
    </f:facet>

    <%-- List Data --%>
    <h:panel_data id="data" var="subscription"
       valueRef="user.subscriptions">
      <h:output_text id="subhost"     valueRef="subscription.host"/>
      <h:output_text id="subusername" valueRef="subscription.username"/>
      <h:output_text id="subtype"     valueRef="subscription.type"/>
      <h:output_text id="subconn"     valueRef="subscription.autoConnect"/>
      <h:panel_group id="subactions">
        <h:command_hyperlink id="delete" label="Delete"
                                  commandClass="command-multiple"
                                          href="editSubscription.do">
          <f:parameter name="action"      value="Delete"/>
          <f:parameter name="username" valueRef="subscription.user.username"/>
          <f:parameter name="host"     valueRef="subscription.host"/>
        </h:command_hyperlink>
        <h:command_hyperlink id="edit"    label="Edit"
                                   commandClass="command-multiple"
                                           href="editSubscription.do">
          <f:parameter name="action"      value="Edit"/>
          <f:parameter name="username" valueRef="subscription.user.username"/>
          <f:parameter name="host"     valueRef="subscription.host"/>
        </h:command_hyperlink>
      </h:panel_group>
    </h:panel_data>

  </h:panel_list>

  <h:command_hyperlink id="add"           label="Create New Mail Subscription"
                                           href="editSubscription.do">
    <f:parameter name="action"            value="Create"/>
    <f:parameter name="username"       valueRef="registrationForm.username"/>
  </h:command_hyperlink>

</c:if>


<s:javascript formName="registrationForm"
     dynamicJavascript="true"
      staticJavascript="false"/>
<script language="Javascript1.1" src="staticJavascript.jsp"></script>

</body>
</s:html>
</f:use_faces>
