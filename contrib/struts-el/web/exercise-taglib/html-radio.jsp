<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<html-el:html>
 <head>
  <title>Test html-el:radio Tag</title>
 </head>
 <body bgcolor="white">

  <div align="center">
   <h1>Test struts html-el:radio Tag</h1>
  </div>

  <html-el:form action="html-radio.do">
   <table>
    <tr>
     <td>
      <html-el:radio property="stringProperty" value="flagOne"/>
     </td>
     <td>flag one</td>
    </tr>
    <tr>
     <td>
      <html-el:radio property="stringProperty" value="flagTwo"/>
     </td>
     <td>flag two</td>
    </tr>
    <tr>
     <td>
      <html-el:radio property="stringProperty" value="flagThree"
                     onblur="showit('onblur')"
                     onchange="showit('onchange')"
                     onclick="showit('onclick')"
                     ondblclick="showit('ondblclick')"
                     onfocus="showit('onfocus')"
                     onkeydown="showit('onkeydown')"
                     onkeypress="showit('onkeypress')"
                     onkeyup="showit('onkeyup')"
                     onmousedown="showit('onmousedown')"
                     onmousemove="showit('onmousemove')"
                     onmouseout="showit('onmouseout')"
                     onmouseover="showit('onmouseover')"
                     onmouseup="showit('onmouseup')"
      />
     </td>
     <td>flag three</td>
    </tr>
    <tr>
     <td>
      <html-el:submit>Save</html-el:submit>
     </td>
    </tr>
   </table>
  </html-el:form>

  <script>
   <!--
    function showit(thing) { window.status = thing; }
    // -->
  </script>

 </body>
</html-el:html>
