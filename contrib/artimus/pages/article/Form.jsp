<%--
/**
 * Data entry page.
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:05:16 $
 */
--%>

<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<html:html>
  <head>
   <html:base/>
   <link rel="stylesheet" type="text/css" href="../../styles/global.css" />
   <title>Articles - Article Entry Form</title>
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
            <html:form action="/admin/Store">
              <tr> 
                <td align="right" nowrap>Title:</td>
                <td align="left" colspan="2">
                  <html:text property="title" size="50" 
			maxlength="255" accesskey="T"/>
                </td>
              </tr>
              <tr> 
                <td align="right" nowrap>Author:</td>
                <td align="left">
                  <html:text property="creator" size="30" 
			maxlength="75" accesskey="A"/>
                </td>
                <td align="left">
                  Full name of person who orginated the article.
                </td>
              </tr>
              <tr> 
                <td align="right" nowrap>Article:</td>
                <td align="left" colspan="2">
		<!-- The Struts html:textarea tag does not support wrapping -->
		<!-- so we use this trick instead -->
                  <textarea name="content" rows="12" cols="50" 
			accesskey="a" tabindex="2" wrap="soft">
                    <bean:write name="articleForm" property="content"/>
                  </textarea>
                </td>
              </tr>
              <tr> 
                <td align="right" nowrap>Contributed:</td>
                <td align="left">
                  <html:text property="contributedText" size="30" 
			maxlength="75" accesskey="A"/>
                </td>
                <td align="left">
                </td>
              </tr>              
              <tr> 
                <td align="right" nowrap>Contributor:</td>
                <td align="left">
                  <html:text property="contributor" size="30" 
			maxlength="75" accesskey="A"/>
                </td>
                <td align="left">
                </td>
              </tr>                <td align="right" nowrap>Article ID:</td>
                <logic:notPresent name="articleForm" property="article">
                  <td align="left"><i>
                    <html:hidden property="article"/>
                    not assigned
                  </i></td>
                </logic:notPresent>
                <logic:present name="articleForm" property="article">
                  <td align="left">
                    <html:hidden property="article"/>
                    <bean:write name="articleForm" property="article"/>
                  </td>
                </logic:present>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td colspan="3" align="right">
                  <html:submit accesskey="S">
                    SAVE
                  </html:submit>
                  <html:cancel  accesskey="C">
                    CANCEL
                  </html:cancel>
                </td>
              </tr>
              <html:hidden property="key"/>
            </html:form>
          </table>
        </td>
      </tr>
      <tr>
        <td class="options">
          <html:link forward="done" accesskey="D">
            DONE
          </html:link>
        </td>
      </tr>
    </table>
  </body>
</html:html>
