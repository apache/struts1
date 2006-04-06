<html>
<body>
<p>
<b>The Text:</b>&nbsp;<%= request.getAttribute("text") %>
</p>
<p>
<b>The Query Parameter:</b>&nbsp;<%= request.getAttribute("queryValue") %>
</p>
<p>
<b>The File name:</b>&nbsp;<%= request.getAttribute("fileName") %>
</p>
<p>
<b>The File content type:</b>&nbsp;<%= request.getAttribute("contentType") %>
</p>
<p>
<b>The File size:</b>&nbsp;<%= request.getAttribute("size") %>
</p>
<p>
<b>The File data:</b>
</p>
<hr />
<pre>
<%= request.getAttribute("data") %>
</pre>
<hr />

<hr />
<h3>Request Parameters</h3>

    <p>Display the request parameter values to show that the multipart request
       retains them after a forward.</p>

    <b>The Text:</b>&nbsp;<%= request.getParameter("theText") %></br>
    <b>Write File:</b>&nbsp;<%= request.getParameter("writeFile") %></br>
    <b>File Path:</b>&nbsp;<%= request.getParameter("filePath") %></br>

    <hr />
</body>
</html>
