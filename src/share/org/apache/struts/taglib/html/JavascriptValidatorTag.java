/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.struts.taglib.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.commons.validator.Var;
import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.validator.Resources;
import org.apache.struts.validator.ValidatorPlugIn;

/**
 * Custom tag that generates JavaScript for client side validation based
 * on the validation rules loaded by the <code>ValidatorPlugIn</code>
 * defined in the struts-config.xml file.
 *
 * @author David Winterfeldt
 * @version $Revision: 1.20 $ $Date: 2003/01/16 03:55:09 $
 * @since Struts 1.1
 */
public class JavascriptValidatorTag extends BodyTagSupport {

    // ----------------------------------------------------------- Properties

    /**
     * The servlet context attribute key for our resources.
     */
    protected String bundle = Globals.MESSAGES_KEY;

    /**
     * The default locale on our server.
     */
    protected static Locale defaultLocale = Locale.getDefault();

    /**
     * The name of the form that corresponds with the action name
     * in struts-config.xml. Specifying a form name places a
     * &lt;script&gt; &lt;/script&gt; around the javascript.
     */
    protected String formName = null;

    /**
     * The current page number of a multi-part form.
     * Only valid when the formName attribute is set.
     */
    protected int page = 0;

    /**
     * This will be used as is for the JavaScript validation method name if it has a value.  This is
     * the method name of the main JavaScript method that the form calls to perform validations.
     */
    protected String methodName = null;

    /**
     * The static JavaScript methods will only be printed if this is set to "true".
     */
    protected String staticJavascript = "true";

    /**
     * The dynamic JavaScript objects will only be generated if this is set to "true".
     */
    protected String dynamicJavascript = "true";

    /**
     * The src attribute for html script element (used to include an external script
     * resource). The src attribute is only recognized
     * when the formName attribute is specified.
     */
    protected String src = null;

    /**
     * The JavaScript methods will enclosed with html comments if this is set to "true".
     */
    protected String htmlComment = "true";

    private String htmlBeginComment = "\n<!-- Begin \n";

    private String htmlEndComment = "//End --> \n";
    /**
     * Gets the key (form name) that will be used
     * to retrieve a set of validation rules to be
     * performed on the bean passed in for validation.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * Sets the key (form name) that will be used
     * to retrieve a set of validation rules to be
     * performed on the bean passed in for validation.
     * Specifying a form name places a
     * &lt;script&gt; &lt;/script&gt; tag around the javascript.
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * Gets the current page number of a multi-part form.
     * Only field validations with a matching page numer
     * will be generated that match the current page number.
     * Only valid when the formName attribute is set.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the current page number of a multi-part form.
     * Only field validations with a matching page numer
     * will be generated that match the current page number.
     * Only valid when the formName attribute is set.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets the method name that will be used for the Javascript
     * validation method name if it has a value.  This overrides
     * the auto-generated method name based on the key (form name)
     * passed in.
     */
    public String getMethod() {
        return methodName;
    }

    /**
     * Sets the method name that will be used for the Javascript
     * validation method name if it has a value.  This overrides
     * the auto-generated method name based on the key (form name)
     * passed in.
     */
    public void setMethod(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Gets whether or not to generate the static
     * JavaScript.  If this is set to 'true', which
     * is the default, the static JavaScript will be generated.
     */
    public String getStaticJavascript() {
        return staticJavascript;
    }

    /**
     * Sets whether or not to generate the static
     * JavaScript.  If this is set to 'true', which
     * is the default, the static JavaScript will be generated.
     */
    public void setStaticJavascript(String staticJavascript) {
        this.staticJavascript = staticJavascript;
    }

    /**
     * Gets whether or not to generate the dynamic
     * JavaScript.  If this is set to 'true', which
     * is the default, the dynamic JavaScript will be generated.
     */
    public String getDynamicJavascript() {
        return dynamicJavascript;
    }

    /**
     * Sets whether or not to generate the dynamic
     * JavaScript.  If this is set to 'true', which
     * is the default, the dynamic JavaScript will be generated.
     */
    public void setDynamicJavascript(String dynamicJavascript) {
        this.dynamicJavascript = dynamicJavascript;
    }

    /**
      * Gets whether or not to delimit the
      * JavaScript with html comments.  If this is set to 'true', which
      * is the default, the htmlComment will be surround the JavaScript.
      */
    public String getHtmlComment() {
        return htmlComment;
    }

    /**
     * Sets whether or not to delimit the
     * JavaScript with html comments.  If this is set to 'true', which
     * is the default, the htmlComment will be surround the JavaScript.
     */
    public void setHtmlComment(String htmlComment) {
        this.htmlComment = htmlComment;
    }

    /**
     * Gets the src attribute's value when defining
     * the html script element.
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the src attribute's value when defining
     * the html script element. The src attribute is only recognized
     * when the formName attribute is specified.
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Render the JavaScript for to perform validations based on the form name.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        StringBuffer results = new StringBuffer();

        ModuleConfig config = RequestUtils.getModuleConfig(pageContext);
        ValidatorResources resources =
            (ValidatorResources) pageContext.getAttribute(
                ValidatorPlugIn.VALIDATOR_KEY + config.getPrefix(),
                PageContext.APPLICATION_SCOPE);
        Locale locale = null;
        try {
            locale =
                (Locale) pageContext.getAttribute(Globals.LOCALE_KEY, PageContext.SESSION_SCOPE);
        } catch (IllegalStateException e) { // Invalidated session
            locale = null;
        }

        if (locale == null) {
            locale = defaultLocale;
        }

        Form form = null;
        form = resources.get(locale, formName);
        if (form != null) {
            if ("true".equals(dynamicJavascript)) {
                MessageResources messages =
                    (MessageResources) pageContext.getAttribute(
                        bundle + config.getPrefix(),
                        PageContext.APPLICATION_SCOPE);

                List lActions = new ArrayList();
                List lActionMethods = new ArrayList();

                // Get List of actions for this Form
                for (Iterator i = form.getFields().iterator(); i.hasNext();) {
                    Field field = (Field) i.next();

                    for (Iterator x = field.getDependencies().iterator(); x.hasNext();) {
                        Object o = x.next();

                        if (o != null && !lActionMethods.contains(o)) {
                            lActionMethods.add(o);
                        }
                    }

                }

                // Create list of ValidatorActions based on lActionMethods
                for (Iterator i = lActionMethods.iterator(); i.hasNext();) {
                    String depends = (String) i.next();
                    ValidatorAction va = resources.getValidatorAction(depends);

                    // throw nicer NPE for easier debugging
                    if (va == null) {
                        throw new NullPointerException(
                            "Depends string \""
                                + depends
                                + "\" was not found in validator-rules.xml.");
                    }               
                    
                    String javascript = va.getJavascript();
                    if (javascript != null && javascript.length() > 0) {
                        lActions.add(va);
                    } else {
                        i.remove();
                    }
                }

                Collections.sort(lActions, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        ValidatorAction va1 = (ValidatorAction) o1;
                        ValidatorAction va2 = (ValidatorAction) o2;

                        if ((va1.getDepends() == null || va1.getDepends().length() == 0)
                            && (va2.getDepends() == null || va2.getDepends().length() == 0)) {
                            return 0;
                        } else if (
                            (va1.getDepends() != null && va1.getDepends().length() > 0)
                                && (va2.getDepends() == null || va2.getDepends().length() == 0)) {
                            return 1;
                        } else if (
                            (va1.getDepends() == null || va1.getDepends().length() == 0)
                                && (va2.getDepends() != null && va2.getDepends().length() > 0)) {
                            return -1;
                        } else {
                            return va1.getDependencies().size() - va2.getDependencies().size();
                        }
                    }
                });

                String methods = null;
                for (Iterator i = lActions.iterator(); i.hasNext();) {
                    ValidatorAction va = (ValidatorAction) i.next();

                    if (methods == null) {
                        methods = va.getMethod() + "(form)";
                    } else {
                        methods += " && " + va.getMethod() + "(form)";
                    }
                }

                results.append(getJavascriptBegin(methods));

                for (Iterator i = lActions.iterator(); i.hasNext();) {
                    ValidatorAction va = (ValidatorAction) i.next();
                    String jscriptVar = null;
                    String functionName = null;

                    if (va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0) {
                        functionName = va.getJsFunctionName();
                    } else {
                        functionName = va.getName();
                    }

                    results.append("    function " + functionName + " () { \n");
                    for (Iterator x = form.getFields().iterator(); x.hasNext();) {
                        Field field = (Field) x.next();

                        // Skip indexed fields for now until there is
                        // a good way to handle error messages (and the length of the list (could retrieve from scope?))
                        if (!field.isIndexed()
                            && field.getPage() == page
                            && field.isDependency(va.getName())) {
                            String message = Resources.getMessage(messages, locale, va, field);
                            message = (message != null ? message : "");

                            jscriptVar = getNextVar(jscriptVar);

                            results.append(
                                "     this."
                                    + jscriptVar
                                    + " = new Array(\""
                                    + field.getKey()
                                    + "\", \""
                                    + message
                                    + "\", ");

                            results.append("new Function (\"varName\", \"");

                            Map hVars = field.getVars();
                            // Loop through the field's variables.
                            for (Iterator iVars = hVars.keySet().iterator(); iVars.hasNext();) {
                                String varKey = (String) iVars.next();
                                Var var = (Var) hVars.get(varKey);
                                String varValue = var.getValue();
                                String jsType = var.getJsType();

                                if (Var.JSTYPE_INT.equalsIgnoreCase(jsType)) {
                                    results.append(
                                        "this."
                                            + varKey
                                            + "="
                                            + ValidatorUtil.replace(varValue, "\\", "\\\\")
                                            + "; ");
                                } else if (Var.JSTYPE_REGEXP.equalsIgnoreCase(jsType)) {
                                    results.append(
                                        "this."
                                            + varKey
                                            + "=/"
                                            + ValidatorUtil.replace(varValue, "\\", "\\\\")
                                            + "/; ");
                                } else if (Var.JSTYPE_STRING.equalsIgnoreCase(jsType)) {
                                    results.append(
                                        "this."
                                            + varKey
                                            + "='"
                                            + ValidatorUtil.replace(varValue, "\\", "\\\\")
                                            + "'; ");
                                    // So everyone using the latest format doesn't need to change their xml files immediately.
                                } else if ("mask".equalsIgnoreCase(varKey)) {
                                    results.append(
                                        "this."
                                            + varKey
                                            + "=/"
                                            + ValidatorUtil.replace(varValue, "\\", "\\\\")
                                            + "/; ");
                                } else {
                                    results.append(
                                        "this."
                                            + varKey
                                            + "='"
                                            + ValidatorUtil.replace(varValue, "\\", "\\\\")
                                            + "'; ");
                                }
                            }

                            results.append(" return this[varName];\"));\n");
                        }
                    }
                    results.append("    } \n\n");
                }
            } else if ("true".equals(staticJavascript)) {
                results.append(this.getStartElement());
                if ("true".equals(htmlComment))
                    results.append(htmlBeginComment);
            }
        }
        if ("true".equals(staticJavascript)) {
            results.append(getJavascriptStaticMethods(resources));
        }

        if (form != null
            && ("true".equals(dynamicJavascript) || "true".equals(staticJavascript))) {
            results.append(getJavascriptEnd());
        }

        // Print this field to our output writer
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(results.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
            //throw new JspException(messages.getMessage("common.io", e.toString()));
        }

        // Continue processing this page
        return (EVAL_BODY_TAG);

    }

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        bundle = Globals.MESSAGES_KEY;
        formName = null;
        page = 0;
        methodName = null;
        staticJavascript = "true";
        dynamicJavascript = "true";
        htmlComment = "true";
        src = null;
    }

    /**
     * Returns the opening script element and some initial javascript.
     */
    protected String getJavascriptBegin(String methods) {
        StringBuffer sb = new StringBuffer();
        String name =
            formName.substring(0, 1).toUpperCase() + formName.substring(1, formName.length());

        sb.append(this.getStartElement());
        
        if ("true".equals(htmlComment)) {
            sb.append(htmlBeginComment);
        }
        sb.append("\n     var bCancel = false; \n\n");

        if (methodName == null || methodName.length() == 0)
            sb.append(
                "    function validate"
                    + name
                    + "(form) {                                                                   \n");
        else
            sb.append(
                "    function "
                    + methodName
                    + "(form) {                                                                   \n");

        sb.append("        if (bCancel) \n");
        sb.append("      return true; \n");
        sb.append("        else \n");

        // Always return true if there aren't any Javascript validation methods
        if (methods == null || methods.length() == 0) {
            sb.append("       return true; \n");
        } else {
            sb.append("       return " + methods + "; \n");
        }

        sb.append("   } \n\n");

        return sb.toString();
    }

    protected String getJavascriptStaticMethods(ValidatorResources resources) {
        StringBuffer sb = new StringBuffer();

        sb.append("\n\n");

        for (Iterator i = resources.getValidatorActions().values().iterator(); i.hasNext();) {
            ValidatorAction va = (ValidatorAction) i.next();
            if (va != null) {
                String javascript = va.getJavascript();
                if (javascript != null && javascript.length() > 0) {
                    sb.append(javascript + "\n");
                }
            }
        }

        return sb.toString();
    }

    /**
     * Returns the closing script element.
     */
    protected String getJavascriptEnd() {
        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        if ("true".equals(htmlComment)){
            sb.append(htmlEndComment);
        }
        
        sb.append("</script>\n\n");

        return sb.toString();
    }

    /**
     * The value <code>null</code> will be returned at the end of the sequence.
     * &nbsp;&nbsp;&nbsp; ex: "zz" will return <code>null</code>
     */
    private String getNextVar(String input) {
        if (input == null) {
            return "aa";
        }

        input = input.toLowerCase();

        for (int i = input.length(); i > 0; i--) {
            int pos = i - 1;

            char c = input.charAt(pos);
            c++;

            if (c <= 'z') {
                if (i == 0) {
                    return c + input.substring(pos, input.length());
                } else if (i == input.length()) {
                    return input.substring(0, pos) + c;
                } else {
                    return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
                }
            } else {
                input = replaceChar(input, pos, 'a');
            }

        }

        return null;

    }

    /**
     * Replaces a single character in a <code>String</code>
     */
    private String replaceChar(String input, int pos, char c) {
        if (pos == 0) {
            return c + input.substring(pos, input.length());
        } else if (pos == input.length()) {
            return input.substring(0, pos) + c;
        } else {
            return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
        }
    }

    /**
     * Constructs the beginning &lt;script&gt; element depending on xhtml status.
     */
    private String getStartElement() {
        StringBuffer start = new StringBuffer("<script type=\"text/javascript\"");

        // there is no language attribute in xhtml
        if (!this.isXhtml()) {
            start.append(" language=\"Javascript1.1\"");
        }

        if (this.src != null) {
            start.append(" src=\"" + src + "\"");
        }

        start.append("> \n");
        return start.toString();
    }
    
    /**
     * Returns true if this is an xhtml page.
     */
    private boolean isXhtml() {
        String xhtml =
            (String) this.pageContext.getAttribute(Globals.XHTML_KEY, this.pageContext.PAGE_SCOPE);

        return ("true".equalsIgnoreCase(xhtml));
    }

}
