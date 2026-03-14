/*
 * $Id$ 
 *
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.struts.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.ModuleUtils;

/**
 * <p>A standard <strong>Action</strong> that switches to a new module
 * and then forwards control to a URI (specified in a number of possible ways)
 * within the new module.</p>
 *
 * <p>Valid request parameters for this Action are:</p>
 * <ul>
 * <li><strong>page</strong> - Module-relative URI (beginning with "/")
 *     to which control should be forwarded after switching.</li>
 * <li><strong>prefix</strong> - The module prefix (beginning with "/")
 *     of the module to which control should be switched.  Use a
 *     zero-length string for the default module.  The
 *     appropriate <code>ModuleConfig</code> object will be stored as a
 *     request attribute, so any subsequent logic will assume the new
 *     module.</li>
 * </ul>
 *
 * @version $Rev$ $Date$
 * @since Struts 1.1
 */
public class SwitchAction extends Action {


    // ----------------------------------------------------- Instance Variables


    /**
     * Commons Logging instance.
     */
    protected static Log log = LogFactory.getLog(SwitchAction.class);


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources
        ("org.apache.struts.actions.LocalStrings");


    // --------------------------------------------------------- Public Methods


    // See superclass for JavaDoc
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        // Identify the request parameters controlling our actions
        String page = request.getParameter("page");
        String prefix = request.getParameter("prefix");
        if ((page == null) || (prefix == null)) {
            String message = messages.getMessage("switch.required");
            log.error(message);
            throw new ServletException(message);
        }

        // Validate the page parameter against path traversal and
        // Servlet-spec protected directories (C-1)
        validatePage(page);

        // Switch to the requested module
        ModuleUtils.getInstance().selectModule(prefix, request, getServlet().getServletContext());

        if (request.getAttribute(Globals.MODULE_KEY) == null) {
            String message = messages.getMessage("switch.prefix", prefix);
            log.error(message);
            throw new ServletException(message);
        }

        // Forward control to the specified module-relative URI
        return (new ActionForward(page));

    }


    /**
     * <p>Validate that the {@code page} parameter is a safe module-relative
     * URI suitable for forwarding.</p>
     *
     * <p>A valid page must:</p>
     * <ol>
     *   <li>Start with {@code "/"} (module-relative)</li>
     *   <li>Contain no path-traversal sequences ({@code ".."})</li>
     *   <li>After normalization, not resolve into the Servlet-spec protected
     *       directories {@code /WEB-INF/} or {@code /META-INF/}</li>
     * </ol>
     *
     * @param page the page parameter value to validate
     * @throws ServletException if the page value is unsafe
     */
    private void validatePage(String page) throws ServletException {
        // Extract the path portion (before any query string)
        String path = page;
        int question = path.indexOf('?');
        if (question >= 0) {
            path = path.substring(0, question);
        }

        // Decode percent-encoded characters for defense-in-depth;
        // servlet containers normally do this before getParameter(),
        // but double-encoding attacks may leave encoded sequences.
        path = decodePath(path);

        // Requirement 1: must be module-relative (start with "/")
        if (path.length() == 0 || path.charAt(0) != '/') {
            rejectPage(page);
            return;
        }

        // Normalize: collapse consecutive slashes, then resolve "." and ".."
        path = normalizePath(path);
        if (path == null) {
            // normalizePath returns null when ".." escapes above the root
            rejectPage(page);
            return;
        }

        // Requirement 3: the Servlet specification (SRV.9.5 / SRV.10.5)
        // reserves /WEB-INF/ and /META-INF/ — clients must never reach them.
        // Compare case-insensitively for portability across file systems.
        String upper = path.toUpperCase();
        if (upper.startsWith("/WEB-INF/") || upper.equals("/WEB-INF")
                || upper.startsWith("/META-INF/") || upper.equals("/META-INF")) {
            rejectPage(page);
        }
    }

    /**
     * Throw a {@link ServletException} for an invalid page parameter.
     */
    private void rejectPage(String page) throws ServletException {
        String message = messages.getMessage("switch.page", page);
        log.error(message);
        throw new ServletException(message);
    }

    /**
     * <p>Normalize a path by resolving {@code "."} and {@code ".."} segments
     * and collapsing consecutive slashes.</p>
     *
     * @param path an absolute path starting with {@code "/"}
     * @return the normalized path, or {@code null} if {@code ".."} would
     *         navigate above the root
     */
    static String normalizePath(String path) {
        // Fast-path: nothing to normalize
        if (path.indexOf('.') < 0 && path.indexOf("//") < 0) {
            return path;
        }

        // Split on "/" and resolve segment by segment
        // Use a simple array-based stack (Java 1.4 compatible)
        String[] segments = splitPath(path);
        String[] stack = new String[segments.length];
        int top = 0;

        for (int i = 0; i < segments.length; i++) {
            String seg = segments[i];
            if (seg.length() == 0 || seg.equals(".")) {
                // skip empty segments (from "//") and "."
                continue;
            } else if (seg.equals("..")) {
                if (top == 0) {
                    // cannot go above root
                    return null;
                }
                top--;
            } else {
                stack[top++] = seg;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < top; i++) {
            sb.append('/');
            sb.append(stack[i]);
        }
        if (sb.length() == 0) {
            return "/";
        }
        return sb.toString();
    }

    /**
     * Split a path on {@code "/"} (Java 1.4 compatible).
     */
    private static String[] splitPath(String path) {
        java.util.List parts = new java.util.ArrayList();
        int start = 0;
        int idx;
        while ((idx = path.indexOf('/', start)) >= 0) {
            parts.add(path.substring(start, idx));
            start = idx + 1;
        }
        parts.add(path.substring(start));
        return (String[]) parts.toArray(new String[parts.size()]);
    }

    /**
     * Decode percent-encoded characters ({@code %XX}) in a path string.
     * This provides defense-in-depth against double-encoding attacks.
     *
     * @param path the path to decode
     * @return the decoded path
     */
    private static String decodePath(String path) {
        if (path.indexOf('%') < 0) {
            return path;
        }
        StringBuffer sb = new StringBuffer(path.length());
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == '%' && i + 2 < path.length()) {
                int hi = Character.digit(path.charAt(i + 1), 16);
                int lo = Character.digit(path.charAt(i + 2), 16);
                if (hi >= 0 && lo >= 0) {
                    sb.append((char) (hi * 16 + lo));
                    i += 2;
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


}
