<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="/tags/struts-bean" prefix="bean" %><%@ taglib uri="/tags/struts-html" prefix="html" %><%@ taglib uri="/tags/struts-logic" prefix="logic" %><%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<html:html>
  <head>
    <title>
      <bean:message key="typeForm.title" />
    </title>
    <html:base />
  </head>
  <body bgcolor="white">
    <logic:messagesPresent>
      <bean:message key="errors.header" />
      <ul>
        <html:messages id="error" property="byte">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="short">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="integer">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="long">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="float">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="floatRange">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="double">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="date">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="creditCard">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="mask">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="email">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
        <html:messages id="error" property="url">
          <li>
            <bean:write name="error" />
          </li>
        </html:messages>
      </ul>
      <hr />
    </logic:messagesPresent>
    <html:form action="type-submit">
      <html:hidden property="action" />
      <table border="0">
        <tr>
          <th align="left">
            <bean:message key="typeForm.byte.displayname" />
          </th>
          <td align="left">
            <html:text property="byte" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.short.displayname" />
          </th>
          <td align="left">
            <html:text property="short" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.integer.displayname" />
          </th>
          <td align="left">
            <html:text property="integer" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.long.displayname" />
          </th>
          <td align="left">
            <html:text property="long" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.float.displayname" />
          </th>
          <td align="left">
            <html:text property="float" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.floatRange.displayname" />
          </th>
          <td align="left">
            <html:text property="floatRange" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.double.displayname" />
          </th>
          <td align="left">
            <html:text property="double" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.date.displayname" />
          </th>
          <td align="left">
            <html:text property="date" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.creditCard.displayname" />
          </th>
          <td align="left">
          <html:text property="creditCard" size="16" maxlength="16" />(e.g. 4111111111111111, 5500000000000004)</td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.mask.displayname" />
          </th>
          <td align="left">
            <html:text property="mask" size="15" maxlength="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.email.displayname" />
          </th>
          <td align="left">
            <html:text property="email" size="15"/>
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.url.displayname" />
          </th>
          <td align="left">
            <html:text property="url" size="15" />
          </td>
        </tr>
        <tr>
          <th align="left">
            <bean:message key="typeForm.nested" />
          </th>
          <td align="left">&#160;</td>
        </tr>
        <nested:iterate property="nameList">
          <tr>
            <th align="left">&#160;</th>
            <td align="left">
              <nested:messagesPresent property="value">
                <br />
                <ul>
                  <nested:messages id="error" property="value">
                    <li>
                      <bean:write name="error" />
                    </li>
                  </nested:messages>
                </ul>
              </nested:messagesPresent>
              <nested:text property="value" size="15" maxlength="15" />
            </td>
          </tr>
        </nested:iterate>
        <tr>
          <td>
          <html:submit property="submit">
            <bean:message key="button.save" />
          </html:submit>&#160; 
          <html:reset>
            <bean:message key="button.reset" />
          </html:reset>&#160; 
          <html:cancel>
            <bean:message key="button.cancel" />
          </html:cancel></td>
        </tr>
      </table>
    </html:form>
  </body>
</html:html>
