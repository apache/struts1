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
      <html-el:radio property="stringProperty" value="flagOne"
                     title="Flag One" tabindex="3" accesskey="1"
                     disabled="${!empty pageScope}"/>
     </td>
     <td>flag one</td>
    </tr>
    <tr>
     <td>
      <html-el:radio property="stringProperty" value="flagTwo"
                     title="Flag Two" tabindex="2" accesskey="2"/>
     </td>
     <td>flag two</td>
    </tr>
    <tr>
     <td>
      <html-el:radio property="stringProperty" value="flagThree"
                     title="Flag Three" tabindex="1" accesskey="3"
                     onblur="showevent(event)"
                     onchange="showevent(event)"
                     onclick="showevent(event)"
                     ondblclick="showevent(event)"
                     onfocus="showevent(event)"
                     onkeydown="showevent(event)"
                     onkeypress="showevent(event)"
                     onkeyup="showevent(event)"
                     onmousedown="showevent(event)"
                     onmousemove="showevent(event)"
                     onmouseout="showevent(event)"
                     onmouseover="showevent(event)"
                     onmouseup="showevent(event)"
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
    function showevent(evt)
	 {
        window.status = evt.type;
    }
    // -->
  </script>

 </body>
</html-el:html>
