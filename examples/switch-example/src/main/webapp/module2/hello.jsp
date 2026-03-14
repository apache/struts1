<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Module 2 - Hello</title>
  <style>
    body { font-family: monospace; max-width: 800px; margin: 2em auto; }
    .safe { background: #e8f5e9; border: 1px solid #4caf50; padding: 1em; }
  </style>
</head>
<body>

<h1>Module 2 - Hello</h1>

<div class="safe">
  <p>This is the legitimate destination page in <code>/module2</code>.</p>
  <p>You arrived here via <code>SwitchAction</code> with:</p>
  <ul>
    <li><code>prefix=/module2</code></li>
    <li><code>page=/hello.do</code></li>
  </ul>
  <p>This is the intended usage of SwitchAction.</p>
</div>


<p><a href="../index.jsp">Back to index</a></p>

</body>
</html>
