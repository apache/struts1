<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<logic:equal parameter="locale" value="en">
   <% session.setAttribute(org.apache.struts.action.Action.LOCALE_KEY, new java.util.Locale("en", "")); %>
   <logic:forward name="registration"/>
</logic:equal>   

<logic:equal parameter="locale" value="fr">
   <% session.setAttribute(org.apache.struts.action.Action.LOCALE_KEY, new java.util.Locale("fr", "")); %>
   <logic:forward name="registration"/>
</logic:equal>   