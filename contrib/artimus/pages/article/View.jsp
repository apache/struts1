<%--
/**
 * Display complete article (read-only).
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:05:16 $
*/
--%>

<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/request" prefix="req" %>

<html:html>
  <head>
    <html:base/>
    <link rel="stylesheet" type="text/css" href="../../styles/global.css" />
    <title>Artimus - Article</title>
  </head>
  <body>
    <table class="parent">
      <tr>
        <td>
          <table class="child">
            <tr>
              <td colspan="3">
                <html:errors/>
              </td>
            </tr>
            <tr>
              <td colspan="3" align="center">
                <h2>
                  <bean:write name="articleForm" property="title"/>
                </h2>
              </td>
            </tr>
            <tr>
              <td colspan="3" class="author">
                by <bean:write name="articleForm" property="creator"/>
              </td>
            </tr>
            <tr>
              <td colspan="3">
                <bean:write name="articleForm" property="content" 
			filter="false"/>
              </td>
            </tr>
            <req:isUserInRole role="contributor">
              <tr>
                <td colspan="3">
                  <hr />
                </td>
              </tr>
              <tr>
                <html:form action="/admin/Delete">
                  <td align="left">
                    <html:submit>DELETE</html:submit>
                  </td>
                  <html:hidden property="key"/>
                </html:form>
                <html:form action="/admin/Edit">
                  <td colspan="2" align="right">
                    <html:submit>EDIT</html:submit>
                    <html:cancel>CANCEL</html:cancel>
                  </td>
                  <html:hidden property="key"/>
                </html:form>
              </tr>
            </req:isUserInRole>
          </table>
        </td>
      </tr>
      <tr>
        <td class="options">
          <html:link forward="done">DONE</html:link>
        </td>
      </tr>
    </table>
  </body>
</html:html>
