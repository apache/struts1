<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %><%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %><%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html:html>
  <head>
    <title><bean:write name="fileName" scope="request" /></title>
    <html:base />
  </head>
  <body bgcolor="white">

    <h2>File: <i><bean:write name="fileName" scope="request" /></i></h2>
    <hr />
    <pre>
        <bean:write name="fileContents" scope="request" filter="true"/>
    </pre>
    <hr />
  </body>
</html:html>
