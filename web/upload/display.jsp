<%@ page language="java" %>

<b>The Text</b>: <%= request.getAttribute("text") %> <br />

<b>The Query Parameter</b>: <%= request.getAttribute("queryValue") %> <br />

<b>The File name</b>: <%= request.getAttribute("fileName") %> <br />

<b>The File content type</b>: <%= request.getAttribute("contentType") %> <br />

<b>The File size</b>: <%= request.getAttribute("size") %> <br />

<b>The File data</b>: <br />

<hr />
<pre><%= request.getAttribute("data") %></pre>
<hr />