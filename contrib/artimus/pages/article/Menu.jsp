<%--
/**
 * Menu page.
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
    <title>Admin - Menu</title>
  </head>
  <body>
    <table class="parent">
      <tr>
        <td align="center">
          <table class="child">
            <tr>
              <td colspan="3">
                <html:errors/>
              </td>
            </tr>
            <html:form action="/search/Hours">
              <tr> 
                <td align="right">Posted in the last:</td>
                <td align="left">
                  <input type="radio" name="key" value="24">Day<br>
                  <input type="radio" name="key" value="168">Week<br>
                  <input type="radio" name="key" value="720">Month
                </td>
                <td align="left">
                  <html:submit property="submit" value=" FIND "/>
                </td>
              </tr>
            </html:form>
            <html:form action="/search/Title">
              <tr> 
                <td align="right">Article Title:</td>
                <td align="left">
                  <input type="text" name="key" size="16" 
			maxlength="45">
                </td>
                <td align="left">
                  <html:submit property="submit" value=" FIND "/>
                </td>
              </tr>
            </html:form>
            <html:form action="/search/Author">
              <tr> 
                <td align="right">Article Author:</td>
                <td align="left">
                  <input type="text" name="key" size="16" 
			maxlength="45">
                </td>
                <td align="left">
                  <html:submit property="submit" value=" FIND "/>
                </td>
             </tr>
           </html:form>
           <html:form action="/search/Content">
             <tr> 
               <td align="right">Article Text:</td>
               <td align="left">
                 <input type="content" name="key" size="16" 
			maxlength="45">
               </td>
               <td align="left">
                 <html:submit property="submit" value=" FIND "/>
               </td>
             </tr>
           </html:form>
           <html:form action="/View">
             <tr> 
               <td align="right">Article ID:</td>
               <td align="left">
                 <input type="text" name="key" size="5" maxlength="10">
               </td>
               <td align="left">
                 <html:submit property="submit" value=" VIEW "/>
               </td>
             </tr>
           </html:form>
           <html:form action="/search/Last" method="get">
             <tr> 
               <td align="right">&nbsp;</td>
               <td align="left">
                 <input type="hidden" name="key" value="20">
                  <html:submit property="submit" value=" LATEST NEWS "/>
               </td>
               <td align="left"></td>
             </tr>
           </html:form>
           <req:isUserInRole role="contributor">
             <tr>
               <td colspan="3">
                 <hr>
               </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>
                  <html:form action="/admin/Input">
                    <html:submit>ADD ARTICLE</html:submit>
                  </html:form>
                </td>
                <td>&nbsp;</td>
              </tr>
            </req:isUserInRole>
            <req:isUserInRole role="manager">
              <tr>
                <td>&nbsp;</td>
                <td>
                  <table border="1" cellpadding="8" width="100%">
                    <tr>
                      <td>
                        &nbsp;
                        <html:link forward="createTables">
                          CREATE TABLES
                        </html:link></br>
                        &nbsp;
                        <html:link forward="createIndex">
                          CREATE INDEX
                        </html:link></br>
                        &nbsp;
                        <html:link forward="reload">
                          RELOAD CONFIG
                        </html:link>
                      </td>
                    </tr>
                  </table>
                </td>
                <td>&nbsp;</td>
              </tr>
            </req:isUserInRole>
         </table>
        </td>
        </tr>
        <tr>
          <td class="options">
             <html:link forward="logon">LOGON</html:link> | 
            <html:link forward="exit">EXIT</html:link>
          </td>
        </tr>
      </table>
    </table>
  </body>
</html:html>

