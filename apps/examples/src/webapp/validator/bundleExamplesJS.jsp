<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
  <head>
    <title><bean:message key="javascript.bundlesExamples.title" /></title>
    <html:base />
  </head>
  <body bgcolor="white">

    <p><strong><bean:message key="javascript.bundlesExamples.description"/></strong></p>
    <p><html:link action="showStrutsConfigBundles" target="_blank">struts-config-bundles.xml</html:link> &nbsp;
       <html:link action="showValidationBundles" target="_blank">validation-bundles.xml</html:link></p>

    <html:errors prefix="html.li.open" suffix="html.li.close"/>

    <html:form action="validateJsBundleExamples" onsubmit="return validateBundlesForm(this);">
      <table border="0">
        <!-- Name Field -->
        <tr>
          <td align="right"><b><bean:message key="bundlesForm.label.name" /></b></td>
          <td align="left"><html:text property="name" size="15" maxlength="15" /></td>
        </tr>

        <!-- Address Field -->
        <tr>
          <td align="right"><b><bean:message key="bundlesForm.label.address" /></b></td>
          <td align="left"><b><html:text property="address" size="25" maxlength="15" /></b></td>
        </tr>

        <!-- Phone Field -->
        <tr>
          <td align="right"><b><bean:message key="bundlesForm.label.phone" /></b></td>
          <td align="left"><b><html:text property="phone" size="25" maxlength="15" /></b></td>
        </tr>

        <!-- Date of Birth Field -->
        <tr>
          <td align="right"><b><bean:message key="bundlesForm.label.dob" /></b></td>
          <td align="left"><b><html:text property="dob" size="25" maxlength="15" /></b></td>
        </tr>

        <!-- Age Field -->
        <tr>
          <td align="right"><b><bean:message key="bundlesForm.label.age" /></b></td>
          <td align="left"><b><html:text property="age" size="25" maxlength="15" /></b></td>
        </tr>

        <!-- Buttons -->
        <tr align="center">
          <td colspan="2"><html:submit><bean:message key="button.save" /></html:submit>&nbsp;
                          <html:reset><bean:message key="button.reset" /></html:reset>&nbsp;
                          <html:cancel><bean:message key="button.cancel" /></html:cancel></td>
        </tr>

      </table>
    </html:form>
    <hr />
    <br />
    <p><strong><bean:message key="bundlesExamples.notes.heading"/></strong></p>
    <ul>
        <li><bean:message key="bundlesExamples.notes1"/></li>
        <li><bean:message key="bundlesExamples.notes2"/></li>
        <li><bean:message key="bundlesExamples.notes3"/></li>
        <li><bean:message key="bundlesExamples.notes4"/></li>
        <li><bean:message key="bundlesExamples.notes5"/></li>
        <li><bean:message key="bundlesExamples.notes6"/></li>
    </ul>
    <html:javascript formName="bundlesForm" dynamicJavascript="true" staticJavascript="true" />
  </body>
</html:html>
