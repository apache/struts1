<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<s:errors/>

<s:form action="/logon" focus="username"
      onsubmit="return validateLogonForm(this);">

  <h:panel_grid
           columns="2"
        styleClass="form-background"
       headerClass="form-header"
     columnClasses="form-prompt,form-field"
       footerClass="form-footer">

    <%-- Grid header element --%>

    <f:facet name="header">
        <s:message key="logon.header"/>
    </f:facet>

    <%-- Grid data elements --%>

    <h:output_label for="username">
      <s:message key="prompt.username"/>
    </h:output_label>

    <h:input_text id="username" size="16"
               value="#{logonForm.map.username}"/>

    <h:output_label for="password">
      <s:message key="prompt.password"/>
    </h:output_label>

    <h:input_secret id="password" size="16"
                 value="#{logonForm.map.password}"/>

    <h:command_button id="submit" type="SUBMIT"
              styleClass="command-single"
                   value="Log On"/>

    <h:command_button id="reset" type="RESET"
              styleClass="command-single"
                   value="Reset"/>

    <%-- Grid footer element --%>

    <f:facet name="footer">
        <s:message key="logon.footer"/>
    </f:facet>

  </h:panel_grid>

  <s:javascript formName="logonForm"
       dynamicJavascript="true"
        staticJavascript="false"/>
  <script language="Javascript1.1" src="staticJavascript.jsp"></script>

</s:form>

