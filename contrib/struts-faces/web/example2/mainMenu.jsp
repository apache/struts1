<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>

<f:view>
<s:html locale="true">
<head>
  <title>
    <s:message key="mainMenu.title"/>
  </title>
  <s:base/>
  <s:stylesheet path="/stylesheet.css"/>
</head>
<body bgcolor="white">

<h3>
  <s:message key="mainMenu.heading"/>
  <em><c:out value="${user.username}"/></em>
</h3>
<ul>
  <li><h:output_link value="editRegistration.do">
        <f:parameter  name="action"
                     value="Edit"/>
        <s:message key="mainMenu.registration"/>
      </h:output_link></li>
  <li><h:output_link value="logoff.do">
        <s:message key="mainMenu.logoff"/>
      </h:output_link></li>
</ul>

</body>
</s:html>
</f:view>
