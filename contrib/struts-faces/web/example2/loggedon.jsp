<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>
<%@ taglib prefix="t" uri="/WEB-INF/struts-tiles.tld" %>
<h:form id="loggedon">
  <h:panel_grid columns="1">
    <h:command_link  id="logoff"
                 action="#{loggedOn.logoff}"
              immediate="true">
      <s:message    key="loggedon.logoff"/>
    </h:command_link>
  </h:panel_grid>
</h:form>
