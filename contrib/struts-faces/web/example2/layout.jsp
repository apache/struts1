<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="s" uri="http://jakarta.apache.org/struts/tags-faces" %>
<%@ taglib prefix="t" uri="/WEB-INF/struts-tiles.tld" %>

<f:view>
  <s:html locale="true">
    <head>
      <title><s:message key="layout.title"/></title>
      <s:stylesheet path="/stylesheet.css"/>
    </head>
    <body>
      <table border="1" width="100%" cellspacing="5">
        <tr>
          <th colspan="2" align="center">
            <f:subview id="header">
              <t:insert attribute="header" flush="false"/>
            </f:subview>
          </th>
        </tr>
        <tr>
          <td width="140" valign="top">
            <f:subview id="menu">
              <t:insert attribute="menu" flush="false"/>
            </f:subview>
          </td>
          <td align="left" valign="top">
            <f:subview id="body">
              <t:insert attribute="body" flush="false"/>
            </f:subview>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <f:subview id="footer">
              <t:insert attribute="footer" flush="false"/>
            </f:subview>
          </td>
        </tr>
      </table>
    </body>
  </s:html>
</f:view>
