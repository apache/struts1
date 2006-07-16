<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean-el" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html-el:html>
    <head>
        <title>Test bean-el:include Tag and Replacements</title>
    </head>

    <body bgcolor="white">

    <div align="center">
        <h1>Test bean-el:include Tag and Replacements</h1>
    </div>

    <c:import url="/index.jsp" var="index"/>
    <bean-el:include id="index2" page="/index.jsp"/>

    <p>Display the contents returned by invoking <code>/index.jsp</code>
        using <code>&lt;c:import&gt;</code>, with no filtering.</p>
    <hr>
    <pre>
        <c:out value="${index}" escapeXml="false"/>
    </pre>
    <hr>

    <p>Display the contents returned by invoking <code>/index.jsp</code>
        using <code>&lt;c:import&gt;</code>, with filtering.</p>
    <hr>
    <pre>
        <c:out value="${index}" escapeXml="true"/>
    </pre>
    <hr>

    <p>Display the contents returned by invoking <code>/index.jsp</code>
        using <code>&lt;bean-el:include&gt;</code>, with no filtering.</p>
    <hr>
    <pre>
        <c:out value="${index2}" escapeXml="false"/>
    </pre>
    <hr>

    <p>Display the contents returned by invoking <code>/index.jsp</code>
        using <code>&lt;bean-el:include&gt;</code>, with filtering.</p>
    <hr>
    <pre>
        <c:out value="${index2}" escapeXml="true"/>
    </pre>
    <hr>

    </body>
</html-el:html>
