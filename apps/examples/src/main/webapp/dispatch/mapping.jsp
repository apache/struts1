<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
  <head>
    <title><bean:message key="mapping.title" /></title>
    <html:base />
  </head>
  <body bgcolor="white">

    <p>
       <html:link forward="module-root"><bean:message key="index.home"/></html:link>
       &nbsp;
       <html:link forward="module-dispatch"><bean:message key="index.title"/></html:link>
    </p>

    <hr />
    <p>
       <strong>Change Language | Changez Le Langage:</strong>
       &nbsp;
       <html:link action="/dispatchLocale?language=en">English | Anglais</html:link>
       &nbsp;
       <html:link action="/dispatchLocale?language=fr">French | Francais</html:link>
    </p>

    <hr />

  <h3><bean:message key="mapping.title" /></h3>

    <logic:messagesPresent>
      <html:messages id="msg">
          <p><strong><font color="red"><bean:write name="msg" /></font></strong></p>
      </html:messages>
    </logic:messagesPresent>

    <logic:messagesPresent message="true">
      <html:messages message="true" id="msg">
          <p><strong><bean:write name="msg" /></strong></p>
      </html:messages>
    </logic:messagesPresent>

    <logic:messagesNotPresent message="true">
      <logic:messagesNotPresent>
          <p>&nbsp;</p>
      </logic:messagesNotPresent>
    </logic:messagesNotPresent>

       <p>
          <html:form action="mapping-foo" style="display:inline">
              <html:submit><bean:message key="button.foo.label" /></html:submit>
          </html:form>
              &nbsp;
          <html:form action="mapping-bar" style="display:inline">
              <html:submit><bean:message key="button.bar.label" /></html:submit>
          </html:form>
              &nbsp;
          <html:form action="mapping-invalid" style="display:inline">
              <html:submit><bean:message key="button.invalid.label" /></html:submit>
          </html:form>
              &nbsp;
          <html:form action="mapping-execute" style="display:inline">
              <html:submit><bean:message key="method.execute.label" /></html:submit>
          </html:form>
              &nbsp;
          <html:form action="mapping-noparam" style="display:inline">
              <html:submit><bean:message key="parameter.missing.label" /></html:submit>
          </html:form>
       </p>

  </body>
</html:html>
