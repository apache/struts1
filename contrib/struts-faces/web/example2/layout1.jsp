<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>
<%@ taglib prefix="t" uri="/WEB-INF/struts-tiles.tld" %>

<f:view>
  <s:html locale="true">
    <head>
      <title>
        <s:message key="layout.title"/>
      </title>
    </head>
    <body>
      <table border="1" width="100%" cellspacing="5">
        <tr>
          <th colspan="2" align="center">
              <t:insert attribute="header" flush="false"/>
          </th>
        </tr>
        <tr>
          <td width="140" valign="top">
              <t:insert attribute="menu" flush="false"/>
          </td>
          <td align="left" valign="top">
              <t:insert attribute="body" flush="false"/>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
              <t:insert attribute="footer" flush="false"/>
          </td>
        </tr>
      </table>
    </body>
  </s:html>
</f:view>
