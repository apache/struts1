# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Language Policy

**All contributions must be in English**: code, comments, commit messages, JSP/HTML content, test names, and any documentation. This is a worldwide open-source project and English is the shared language of the community.

## Project Overview

**struts1-forever** is a maintained fork of Apache Struts 1.2.9, providing security fixes for legacy Struts 1 applications. It is a Java web MVC framework built with Maven.

## Backward Compatibility Policy

**Backward compatibility is the top priority.** This library is used in production legacy systems where any behavioral change can cause breakage. All changes — including security fixes — must preserve the existing public API and observable behavior.

- Do not change method signatures, return values, or exception types of public/protected classes.
- Do not alter default configuration values or processing order.
- Do not remove or rename any public class, method, or field.
- If a security fix unavoidably requires a breaking change, **stop and consult the maintainer before proceeding.** Never land a breaking change without explicit approval.

## Build Commands

```bash
# Full build and test
mvn package

# Compile only
mvn compile

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=TestActionServlet

# Run tests matching a pattern
mvn test -Dtest=TestDynaActionForm#testXxx

# Compile test classes without running
mvn test-compile
```

Note: Surefire is configured with explicit `<includes>` in pom.xml. If you add a new test class, add it to that list or it won't run.

## Running Example Applications

Example webapps live under `examples/`. Each is a Maven WAR project using the `waitt-maven-plugin` (Tomcat 9 embedded).

```bash
# Start a specific example (runs on http://localhost:8080/)
cd examples/switch-example
mvn waitt:run

# Build all examples without starting
cd examples
mvn package
```

Available examples:

| Directory | Purpose |
| --- | --- |
| `examples/switch-example/` | Demonstrates the SwitchAction path traversal vulnerability (C-1) |
| `examples/example/` | Standard Struts mailreader-style example app |
| `examples/examples/` | Framework features demo (validator, upload, dispatch, exercise modules) |
| `examples/mailreader/` | Mailreader reference application |
| `examples/tiles-documentation/` | Tiles framework documentation and demos |

### Servlet 2.2 Constraint in Example Apps

The example `web.xml` files use the **Servlet 2.2 DTD** (`web-app_2_2.dtd`). This is intentional — it mirrors the deployment environments that Struts 1 legacy applications run in and must not be upgraded.

**Consequence for TLD files**: Servlet 2.3+ introduced automatic TLD discovery from JARs (`META-INF/tlds/`). Servlet 2.2 containers have no such mechanism, so every tag library must be declared explicitly in `web.xml` with a `<taglib-location>` pointing to a physical file inside the webapp.

When running under Tomcat 9 (Servlet 5.0), Tomcat honours the `web.xml` `<taglib>` declarations and requires the files to exist on disk — even though Tomcat itself could auto-discover them from the JAR. Therefore:

- **Do not remove `<taglib>` entries from `web.xml`** — they are part of the Servlet 2.2 contract.
- **Keep `struts-bean.tld`, `struts-html.tld`, `struts-logic.tld`, `struts-nested.tld` in `WEB-INF/`** of each example that declares them. These files are extracted from the struts JAR (`META-INF/tlds/`) and committed alongside the webapp sources.
- **`validator-rules.xml` must also be in `WEB-INF/`** of any example that uses `ValidatorPlugIn`, because `ValidatorPlugIn` loads it via `ServletContext.getResourceAsStream()` (webapp filesystem only, not classpath).
- **`.properties` resource files must be on the classpath** (i.e. under `src/main/resources/` or included via a `<resources>` entry in `pom.xml`). The example apps keep them under `src/main/java/` (old Ant layout), so their `pom.xml` files include an explicit `<resource>` block to copy `**/*.properties` from `src/main/java/` into `target/classes/`.

## Source Layout

- **`src/share/`** — Main production sources (Maven `sourceDirectory`)
- **`src/test/`** — Test sources (Maven `testSourceDirectory`)
- **`conf/share/`** — DTDs and `validator-rules.xml`; copied to `target/classes/org/apache/struts/resources/` at compile time
- **`doc/userGuide/`** — XML source for tag library documentation; XSLT-transformed into TLD files at `generate-resources` phase

## Architecture

### MVC Request Flow

```
HTTP Request
  → ActionServlet (front controller, maps modules)
  → RequestProcessor (pipeline: locale, forward, include, path, roles, form, validate, action)
  → ActionMapping (route lookup from struts-config.xml)
  → ActionForm.validate() (input validation)
  → Action.execute() (business logic)
  → ActionForward (view resolution)
  → JSP + custom tags (rendering)
```

### Main Packages (`src/share/org/apache/struts/`)

| Package | Responsibility |
|---|---|
| `action/` | Core MVC: `ActionServlet`, `RequestProcessor`, `Action`, `ActionForm`, `ActionMapping`, `ActionForward` |
| `config/` | Parse and hold `struts-config.xml` via Commons Digester; `ModuleConfig` is the root config object |
| `taglib/html/` | JSP tags for HTML forms (`<html:form>`, `<html:text>`, etc.) |
| `taglib/logic/` | Conditional/iteration tags (`<logic:iterate>`, `<logic:present>`, etc.) |
| `taglib/bean/` | Bean access tags (`<bean:write>`, `<bean:message>`, etc.) |
| `tiles/` | Template/layout framework; `TilesPlugin` bootstraps it |
| `validator/` | Form validation via Commons-Validator integration |
| `actions/` | Built-in reusable Actions (`DispatchAction`, `ForwardAction`, etc.) |
| `upload/` | Multipart file upload via Commons-FileUpload |
| `util/` | `RequestUtils` (param binding), `ResponseUtils` (HTML escape), `TokenProcessor` (CSRF) |

### Multi-Module Support

Struts supports multiple modules per webapp, each with its own `struts-config.xml`. `ModuleConfig` holds per-module bean/action/forward/exception configs. The default module prefix is `""`.

## Test Infrastructure

Tests use JUnit 3.8.1 with custom mock objects (no servlet container required):

- `src/test/org/apache/struts/mock/` — Mock implementations:
  - `MockServletContext`, `MockServletConfig`, `MockHttpSession`
  - `MockHttpServletRequest` — supports `addHeader()`, `addCookie()`, `addParameter()`
  - `MockHttpServletResponse`
  - `MockPageContext` — supports `setJspWriter()` for capturing tag output
  - `MockJspWriter` — captures JSP output; use `getContent()` to assert output

Typical test setup:
```java
context = new MockServletContext();
config = new MockServletConfig(context);
session = new MockHttpSession(context);
request = new MockHttpServletRequest(session);
response = new MockHttpServletResponse();
pageContext = new MockPageContext(config, request, response);
```

For tag tests that produce output, wire a `MockJspWriter`:
```java
MockJspWriter out = new MockJspWriter();
pageContext.setJspWriter(out);
// ... run tag lifecycle ...
String output = out.getContent();
```

## Test Policy

**Never modify production code to make a failing test pass.**

Tests are specifications. If a test fails, the test is documenting real behavior — either a bug or a vulnerability. Fix the test only if it is genuinely wrong. Do not change `src/share/` code just to silence a test failure; doing so destroys the diagnostic value of the test suite.

### Test-First for Vulnerabilities

When a bug or security vulnerability is found during test coverage work:

1. Write a test that **fails** to document the vulnerability.
2. Leave it failing — do NOT fix the production code at that moment.
3. The failing test is the evidence that the vulnerability exists and needs a dedicated fix.

This preserves the Fix Process described in the Known Security Issues section: every fix must begin with a failing test that proves the problem is real.

### Coverage Mandate for Breaking-Change Safety

Before making any security fix or refactoring, ensure the affected area has adequate regression tests. The goal is that every externally-observable behaviour of security-sensitive classes is covered by at least one test, so that an unintended breaking change causes a test failure rather than a silent regression.

Security-sensitive areas requiring thorough coverage:

| Area | Class(es) | Risk |
| --- | --- | --- |
| Path traversal in actions | `SwitchAction`, `IncludeAction`, `ForwardAction` | Arbitrary resource access |
| Reflection-based dispatch | `DispatchAction`, `LookupDispatchAction`, `MappingDispatchAction` | Method injection |
| XSS output escaping | `ResponseUtils`, `TagUtils` | Cross-site scripting |
| CSRF token lifecycle | `TokenProcessor` | Cross-site request forgery |
| Config immutability | `ForwardConfig`, `ExceptionConfig` | Configuration tampering |

### Java Version Compatibility

Production code (`src/share/`) is compiled with `source=1.4` / `target=1.4` (overridden to `release=8` on JDK 11+). Test code (`src/test/`) uses the same compiler configuration.

**Do not use Java 5+ language features in test code**, even if the local JDK is newer and compilation succeeds. Specifically:

- No generics (`List<String>`, `Map<K,V>`, etc.) — use raw types (`List`, `Map`)
- No enhanced for-loops (`for (String s : list)`) — use `Iterator` explicitly
- No autoboxing, varargs, enums, or static imports

Violations compile silently on JDK 11+ (where `release=8` applies) but would fail on a strict JDK 1.4 build environment.

Test naming conventions:

- Test method names describe the **required behaviour** of the system, not the test's pass/fail status.
  - Good: `testValueAttribute_EscapesDoubleQuote()`, `testPage_WebInf_IsRejected()`
  - Avoid: `testVulnerability_…`, `testBehavior_…` — these describe the test, not the spec.
- Format: `test<Subject>_<Condition>_<ExpectedOutcome>()` or `test<Subject>_<ExpectedOutcome>()`
- When a test documents the **current broken state** (and is expected to pass only until the fix lands), add a Javadoc comment stating this and noting it should be removed after the fix.
- Failing-by-design tests must include a comment explaining why they currently fail and what fix is needed.

## Known Security Issues

Security vulnerabilities are tracked as GitHub Security Advisories in this repository.

### Fix Process (follow this for every vulnerability)

1. **Pick one advisory** from GitHub Security Advisories. Work on exactly one at a time.
2. **Write a failing test first** that reproduces the vulnerability. The test must fail before the fix is applied — this proves the vulnerability is real and the test is meaningful. Add the new test class to Surefire `<includes>` in `pom.xml` if it is a new file.
3. **Fix the vulnerability** in production code. Run `mvn test` to confirm the previously-failing test now passes and no existing tests regress.
4. **Commit in one unit**: stage only the test file(s) + the fixed source file(s) together. Write the commit message in the form:

   ```text
   Fix <CVE-ID or advisory title>: <one-line description>
   ```

5. **Update the Security Advisory** on GitHub to reflect the fix: add the patched version, resolution details, and link to the commit.

## Release Process

Releases follow the naming convention `1.2.9-forever-N` (e.g., `1.2.9-forever-1`, `1.2.9-forever-2`).

### Steps

1. **Update `pom.xml` version** from `1.2.10-SNAPSHOT` to `1.2.9-forever-N` and commit.
2. **Build the JAR with JDK 8** (`-Dmaven.test.skip=true` to skip test compilation):

   ```bash
   JAVA_HOME=/Library/Java/JavaVirtualMachines/zulu-8.jdk/Contents/Home \
     mvn clean package -Dmaven.test.skip=true
   ```

3. **Tag and push**: `git tag 1.2.9-forever-N && git push origin STRUTS_1_2_BRANCH --tags`
4. **Create GitHub Release** with `gh release create`, attaching `target/struts-1.2.9-forever-N.jar` as an asset.
5. **Revert `pom.xml`** to `1.2.10-SNAPSHOT` and commit ("Prepare next development iteration").

### Build JDK Requirement

The release JAR **must be built with JDK 8**. The pom.xml has compiler profiles that select `--release` based on the JDK version:

| Build JDK | Effective setting | Class file major version | Minimum runtime |
| --- | --- | --- | --- |
| JDK 8 | `source=1.4 target=1.4` | **48** (Java 1.4) | Java 1.4+ |
| JDK 9–10 | `--release 6` | 50 (Java 6) | Java 6+ |
| JDK 11+ | `--release 8` | 52 (Java 8) | Java 8+ |

Building with JDK 11+ produces class files that **cannot run on Java 5, 6, or 7**. Since this library targets legacy environments, always use JDK 8 for release builds to ensure maximum compatibility.

**Note**: JDK 8 cannot compile test code that uses annotations (`@Override` etc.), so release builds must use `-Dmaven.test.skip=true`. Tests should be run separately with JDK 11+ before the release.
