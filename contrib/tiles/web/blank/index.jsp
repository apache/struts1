<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

  <%-- Insert a definition described in tiles configuration file 
    Change the definition name to insert another definition.
	It is possible to overload some definition attribute by adding
	some put tags with appropriate name.
  --%>
<tiles:insert definition="site.index.page" flush="true" />
