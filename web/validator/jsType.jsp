<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html>
<head>
<title><bean:message key="typeForm.title"/></title>
<html:base/>
</head>
<body bgcolor="white">

<logic:messagesPresent>
   <bean:message key="errors.header"/>
   <ul>
   <html:messages id="error">
      <li><bean:write name="error"/></li>
   </html:messages>
   </ul><hr>
</logic:messagesPresent>


<html:form action="jsType" onsubmit="return validateJsTypeForm(this);">
  <html:hidden property="action"/>

<table border="0">
  <tr>
    <th align="left">
      <bean:message key="typeForm.byte.displayname"/>
    </th>
    <td align="left">
      <html:text property="byte" size="15" maxlength="15"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.short.displayname"/>
    </th>
    <td align="left">
      <html:text property="short" size="15" maxlength="15"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.integer.displayname"/>
    </th>
    <td align="left">
      <html:text property="integer" size="15" maxlength="15"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left">
      <bean:message key="typeForm.long.displayname"/>
    </th>
    <td align="left">
      <html:text property="long" size="15" maxlength="15"/>
    </td>
  </tr>
--%>
  <tr>
    <th align="left">
      <bean:message key="typeForm.float.displayname"/>
    </th>
    <td align="left">
      <html:text property="float" size="15" maxlength="15"/>
    </td>
  </tr>

  <tr>
    <th align="left">
      <bean:message key="typeForm.floatRange.displayname"/>
    </th>
    <td align="left">
      <html:text property="floatRange" size="15" maxlength="15"/>
    </td>
  </tr>
<%--
  <tr>
    <th align="left">
      <bean:message key="typeForm.double.displayname"/>
    </th>
    <td align="left">
      <html:text property="double" size="15" maxlength="15"/>
    </td>
  </tr>
--%>
  <tr>
    <th align="left">
      <bean:message key="typeForm.date.displayname"/>
    </th>
    <td align="left">
      <html:text property="date" size="15" maxlength="15"/>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.creditCard.displayname"/>
    </th>
    <td align="left">
      <html:text property="creditCard" size="16" maxlength="16"/>
       (e.g. 4111111111111111, 5500000000000004)
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.option.satisfaction"/>:
    </th>
    <td align="left">
      <html:select property="satisfaction">
		<html:option value="" key="typeForm.option.select.one"/>
        <html:options collection="satisfactionList" property="value"
                   labelProperty="label"/>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.option.os.list"/>:
    </th>
    <td align="left">
      <html:select property="osList" multiple="true">
		<html:option value="" key="typeForm.option.select.many"/>
        <html:options collection="osTypes" property="value"
                   labelProperty="label"/>
      </html:select>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.radio.overall.satisfaction"/>:
    </th>
    <td align="left">
      <logic:iterate id="satBean" name="satisfactionList" type="org.apache.struts.util.LabelValueBean">
        <html:radio property="overallSatisfaction" value="<%=satBean.getValue()%>">
          <%=satBean.getLabel()%>
        </html:radio>
      </logic:iterate>
    </td>
  </tr>
  <tr>
    <th align="left">
      <bean:message key="typeForm.checkbox.used.languages"/>:
    </th>
    <td align="left">
      <logic:iterate id="langBean" indexId="usedIndex" name="languageTypes" type="org.apache.struts.util.LabelValueBean">
        <html:multibox property="usedLanguages" value="<%=langBean.getValue()%>"/><%=langBean.getLabel()%>

      </logic:iterate>
    </td>
  </tr>
  <tr>
    <td>
      <html:submit property="submit" onclick="bCancel=false;">
         <bean:message key="button.save"/>
      </html:submit>
      &nbsp;
      <html:reset>
         <bean:message key="button.reset"/>
      </html:reset>
      &nbsp;
      <html:cancel onclick="bCancel=true;">
         <bean:message key="button.cancel"/>
      </html:cancel>
    </td>
  </tr>
</table>

</html:form>

<html:javascript formName="jsTypeForm" dynamicJavascript="true" staticJavascript="true"/>


</body>
</html:html>
