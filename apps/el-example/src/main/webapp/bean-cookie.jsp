<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html-el:html>
<head>
    <title>Test Replacements for struts-bean:cookie Tag</title>
</head>

<body bgcolor="white">

<div align="center">
    <h1>Test Replacements for struts-bean:cookie Tag</h1>
</div>

<p>Display the properties of our current session ID cookie (if there is
    one):</p>

<c:set var="jcookie" value='${cookie["JSESSIONID"]}'/>

<table border="1">
    <tr>
        <th>Property Name</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>comment</td>
        <td><c:out value="${jcookie.comment}"/></td>
    </tr>
    <tr>
        <td>domain</td>
        <td><c:out value="${jcookie.domain}"/></td>
    </tr>
    <tr>
        <td>maxAge</td>
        <td><c:out value="${jcookie.maxAge}"/></td>
    </tr>
    <tr>
        <td>name</td>
        <td><c:out value="${jcookie.name}"/></td>
    </tr>
    <tr>
        <td>path</td>
        <td><c:out value="${jcookie.path}"/></td>
    </tr>
    <tr>
        <td>secure</td>
        <td><c:out value="${jcookie.secure}"/></td>
    </tr>
    <tr>
        <td>value</td>
        <td><c:out value="${jcookie.value}"/></td>
    </tr>
    <tr>
        <td>version</td>
        <td><c:out value="${jcookie.version}"/></td>
    </tr>
</table>

<br><br>

<p>Display the properties of an undefined cookie that was given the default
    value <code>UNKNOWN_VALUE</code>:</p>

<bean:cookie id="dummy" name="UNKNOWN_COOKIE" value="UNKNOWN_VALUE"/>

<table border="1">
    <tr>
        <th>Property Name</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>comment</td>
        <td><c:out value="${dummy.comment}"/></td>
    </tr>
    <tr>
        <td>domain</td>
        <td><c:out value="${dummy.domain}"/></td>
    </tr>
    <tr>
        <td>maxAge</td>
        <td><c:out value="${dummy.maxAge}"/></td>
    </tr>
    <tr>
        <td>name</td>
        <td><c:out value="${dummy.name}"/></td>
    </tr>
    <tr>
        <td>path</td>
        <td><c:out value="${dummy.path}"/></td>
    </tr>
    <tr>
        <td>secure</td>
        <td><c:out value="${dummy.secure}"/></td>
    </tr>
    <tr>
        <td>value</td>
        <td><c:out value="${dummy.value}"/></td>
    </tr>
    <tr>
        <td>version</td>
        <td><c:out value="${dummy.version}"/></td>
    </tr>
</table>

</body>
</html-el:html>
