/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/FormTag.java,v 1.48 2003/05/17 01:56:51 dgraham Exp $
 * $Revision: 1.48 $
 * $Date: 2003/05/17 01:56:51 $
 *
 * ====================================================================
 *
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
 *
 */

package org.apache.struts.taglib.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

/**
 * Custom tag that represents an input form, associated with a bean whose
 * properties correspond to the various fields of the form.
 *
 * @author Craig R. McClanahan
 * @author Martin Cooper
 * @author James Turner
 * @author David Graham
 * @version $Revision: 1.48 $ $Date: 2003/05/17 01:56:51 $
 */
public class FormTag extends TagSupport {

    // ----------------------------------------------------- Instance Variables

    /**
     * The action URL to which this form should be submitted, if any.
     */
    protected String action = null;

    /**
     * The module configuration for our module.
     */
    protected ModuleConfig moduleConfig = null;

    /**
     * The content encoding to be used on a POST submit.
     */
    protected String enctype = null;

    /**
     * The name of the field to receive focus, if any.
     */
    protected String focus = null;

    /**
     * The index in the focus field array to receive focus.  This only applies if the field
     * given in the focus attribute is actually an array of fields.  This allows a specific
     * field in a radio button array to receive focus while still allowing indexed field
     * names like "myRadioButtonField[1]" to be passed in the focus attribute.
     * @since Struts 1.1
     */
    protected String focusIndex = null;

    /**
     * The line ending string.
     */
    protected static String lineEnd = System.getProperty("line.separator");

    /**
     * The ActionMapping defining where we will be submitting this form
     */
    protected ActionMapping mapping = null;

    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

    /**
     * The request method used when submitting this form.
     */
    protected String method = null;

    /**
     * The attribute key under which our associated bean is stored.
     */
    protected String name = null;

    /**
     * The onReset event script.
     */
    protected String onreset = null;

    /**
     * The onSubmit event script.
     */
    protected String onsubmit = null;

    /**
     * The scope (request or session) under which our associated bean
     * is stored.
     */
    protected String scope = null;

    /**
     * The ActionServlet instance we are associated with (so that we can
     * initialize the <code>servlet</code> property on any form bean that
     * we create).
     */
    protected ActionServlet servlet = null;

    /**
     * The style attribute associated with this tag.
     */
    protected String style = null;

    /**
     * The style class associated with this tag.
     */
    protected String styleClass = null;

    /**
     * The identifier associated with this tag.
     */
    protected String styleId = null;

    /**
     * The window target.
     */
    protected String target = null;

    /**
     * The Java class name of the bean to be created, if necessary.
     */
    protected String type = null;

    /**
     * The name of the form bean to (create and) use. This is either the same
     * as the 'name' attribute, if that was specified, or is obtained from the
     * associated <code>ActionMapping</code> otherwise.
     */
    protected String beanName = null;

    /**
     * The scope of the form bean to (create and) use. This is either the same
     * as the 'scope' attribute, if that was specified, or is obtained from the
     * associated <code>ActionMapping</code> otherwise.
     */
    protected String beanScope = null;

    /**
     * The type of the form bean to (create and) use. This is either the same
     * as the 'type' attribute, if that was specified, or is obtained from the
     * associated <code>ActionMapping</code> otherwise.
     */
    protected String beanType = null;

    // ------------------------------------------------------------- Properties

    /**
     * Return the name of the form bean corresponding to this tag. There is
     * no corresponding setter method; this method exists so that the nested
     * tag classes can obtain the actual bean name derived from other
     * attributes of the tag.
     */
    public String getBeanName() {

        return beanName;

    }

    /**
     * Return the action URL to which this form should be submitted.
     */
    public String getAction() {

        return (this.action);

    }

    /**
     * Set the action URL to which this form should be submitted.
     *
     * @param action The new action URL
     */
    public void setAction(String action) {

        this.action = action;

    }

    /**
     * Return the content encoding used when submitting this form.
     */
    public String getEnctype() {

        return (this.enctype);

    }

    /**
     * Set the content encoding used when submitting this form.
     *
     * @param enctype The new content encoding
     */
    public void setEnctype(String enctype) {

        this.enctype = enctype;

    }

    /**
     * Return the focus field name for this form.
     */
    public String getFocus() {

        return (this.focus);

    }

    /**
     * Set the focus field name for this form.
     *
     * @param focus The new focus field name
     */
    public void setFocus(String focus) {

        this.focus = focus;

    }

    /**
     * Return the request method used when submitting this form.
     */
    public String getMethod() {

        return (this.method);

    }

    /**
     * Set the request method used when submitting this form.
     *
     * @param method The new request method
     */
    public void setMethod(String method) {

        this.method = method;

    }

    /**
     * Return the attribute key name of our bean.
     */
    public String getName() {

        return (this.name);

    }

    /**
     * Set the attribute key name of our bean.
     *
     * @param name The new attribute key name
     */
    public void setName(String name) {

        this.name = name;

    }

    /**
     * Return the onReset event script.
     */
    public String getOnreset() {

        return (this.onreset);

    }

    /**
     * Set the onReset event script.
     *
     * @param onReset The new event script
     */
    public void setOnreset(String onReset) {

        this.onreset = onReset;

    }

    /**
     * Return the onSubmit event script.
     */
    public String getOnsubmit() {

        return (this.onsubmit);

    }

    /**
     * Set the onSubmit event script.
     *
     * @param onSubmit The new event script
     */
    public void setOnsubmit(String onSubmit) {

        this.onsubmit = onSubmit;

    }

    /**
     * Return the attribute scope of our bean.
     */
    public String getScope() {

        return (this.scope);

    }

    /**
     * Set the attribute scope of our bean.
     *
     * @param scope The new attribute scope
     */
    public void setScope(String scope) {

        this.scope = scope;

    }

    /**
     * Return the style attribute for this tag.
     */
    public String getStyle() {

        return (this.style);

    }

    /**
     * Set the style attribute for this tag.
     *
     * @param style The new style attribute
     */
    public void setStyle(String style) {

        this.style = style;

    }

    /**
     * Return the style class for this tag.
     */
    public String getStyleClass() {

        return (this.styleClass);

    }

    /**
     * Set the style class for this tag.
     *
     * @param styleClass The new style class
     */
    public void setStyleClass(String styleClass) {

        this.styleClass = styleClass;

    }

    /**
     * Return the style identifier for this tag.
     */
    public String getStyleId() {

        return (this.styleId);

    }

    /**
     * Set the style identifier for this tag.
     *
     * @param styleId The new style identifier
     */
    public void setStyleId(String styleId) {

        this.styleId = styleId;

    }

    /**
     * Return the window target.
     */
    public String getTarget() {

        return (this.target);

    }

    /**
     * Set the window target.
     *
     * @param target The new window target
     */
    public void setTarget(String target) {

        this.target = target;

    }

    /**
     * Return the Java class of our bean.
     */
    public String getType() {

        return (this.type);

    }

    /**
     * Set the Java class of our bean.
     *
     * @param type The new Java class
     */
    public void setType(String type) {

        this.type = type;

    }

    // --------------------------------------------------------- Public Methods

    /**
     * Render the beginning of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Look up the form bean name, scope, and type if necessary
        this.lookup();

        // Create an appropriate "form" element based on our parameters
        StringBuffer results = new StringBuffer();
        
        results.append(this.renderFormStartElement());

        results.append(this.renderToken());

        ResponseUtils.write(pageContext, results.toString());

        // Store this tag itself as a page attribute
        pageContext.setAttribute(Constants.FORM_KEY, this, PageContext.REQUEST_SCOPE);

        this.initFormBean();

        return (EVAL_BODY_INCLUDE);

    }

    /**
     * Locate or create the bean associated with our form.
     * @throws JspException
     * @since Struts 1.1
     */
    protected void initFormBean() throws JspException {
        int scope = PageContext.SESSION_SCOPE;
        if ("request".equalsIgnoreCase(beanScope)) {
            scope = PageContext.REQUEST_SCOPE;
        }
        
        Object bean = pageContext.getAttribute(beanName, scope);
        if (bean == null) {
            if (type != null) {
                // Backwards compatibility - use explicitly specified values
                try {
                    bean = RequestUtils.applicationInstance(beanType);
                    if (bean != null) {
                        ((ActionForm) bean).setServlet(servlet);
                    }
                } catch (Exception e) {
                    throw new JspException(
                        messages.getMessage("formTag.create", type, e.toString()));
                }
            } else {
                // New and improved - use the values from the action mapping
                bean =
                    RequestUtils.createActionForm(
                        (HttpServletRequest) pageContext.getRequest(),
                        mapping,
                        moduleConfig,
                        servlet);
            }
            if (bean instanceof ActionForm) {
                ((ActionForm) bean).reset(mapping, (HttpServletRequest) pageContext.getRequest());
            }
            if (bean == null) {
                throw new JspException(messages.getMessage("formTag.create", beanType));
            }
            pageContext.setAttribute(beanName, bean, scope);
        }
        pageContext.setAttribute(Constants.BEAN_KEY, bean, PageContext.REQUEST_SCOPE);
    }

    /**
     * Generates the opening <code>&lt;form&gt;</code> element with appropriate
     * attributes.
     * @since Struts 1.1
     */
    protected String renderFormStartElement() {
        HttpServletResponse response =
            (HttpServletResponse) this.pageContext.getResponse();
            
        StringBuffer results = new StringBuffer("<form");
        results.append(" name=\"");
        results.append(beanName);
        results.append("\"");
        results.append(" method=\"");
        results.append(method == null ? "post" : method);
        results.append("\" action=\"");
        results.append(
            response.encodeURL(
                RequestUtils.getActionMappingURL(this.action, this.pageContext)));
                
        results.append("\"");
        
        if (styleClass != null) {
            results.append(" class=\"");
            results.append(styleClass);
            results.append("\"");
        }
        if (enctype != null) {
            results.append(" enctype=\"");
            results.append(enctype);
            results.append("\"");
        }
        if (onreset != null) {
            results.append(" onreset=\"");
            results.append(onreset);
            results.append("\"");
        }
        if (onsubmit != null) {
            results.append(" onsubmit=\"");
            results.append(onsubmit);
            results.append("\"");
        }
        if (style != null) {
            results.append(" style=\"");
            results.append(style);
            results.append("\"");
        }
        if (styleId != null) {
            results.append(" id=\"");
            results.append(styleId);
            results.append("\"");
        }
        if (target != null) {
            results.append(" target=\"");
            results.append(target);
            results.append("\"");
        }
        results.append(">");
        return results.toString();
    }

    /**
     * Generates a hidden input field with token information, if any.
     * @return A hidden input field containing the token.
     * @since Struts 1.1
     */
    protected String renderToken() {
        StringBuffer results = new StringBuffer();
        HttpSession session = pageContext.getSession();

        if (session != null) {
            String token =
                (String) session.getAttribute(Globals.TRANSACTION_TOKEN_KEY);
                
            if (token != null) {
                results.append("<input type=\"hidden\" name=\"");
                results.append(Constants.TOKEN_KEY);
                results.append("\" value=\"");
                results.append(token);
                if (this.isXhtml()) {
                    results.append("\" />");
                } else {
                    results.append("\">");
                }
            }
        }

        return results.toString();
    }

    /**
     * Render the end of this form.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Remove the page scope attributes we created
        pageContext.removeAttribute(Constants.BEAN_KEY, PageContext.REQUEST_SCOPE);
        pageContext.removeAttribute(Constants.FORM_KEY, PageContext.REQUEST_SCOPE);

        // Render a tag representing the end of our current form
        StringBuffer results = new StringBuffer("</form>");

        // Render JavaScript to set the input focus if required
        if (this.focus != null) {
            results.append(this.renderFocusJavascript());
        }

        // Print this value to our output writer
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(results.toString());
        } catch (IOException e) {
            throw new JspException(messages.getMessage("common.io", e.toString()));
        }

        // Continue processing this page
        return (EVAL_PAGE);

    }

    /**
     * Generates javascript to set the initial focus to the form element given in the
     * tag's "focus" attribute.
     * @since Struts 1.1
     */
    protected String renderFocusJavascript() {
        StringBuffer results = new StringBuffer();

        results.append(lineEnd);
        results.append("<script type=\"text/javascript\"");
        if (!this.isXhtml()) {
            results.append(" language=\"JavaScript\"");
        }
        results.append(">");
        results.append(lineEnd);

        // xhtml script content shouldn't use the browser hiding trick
        if (!this.isXhtml()) {
            results.append("  <!--");
            results.append(lineEnd);
        }

        // Construct the control name that will receive focus.
        // This does not include any index.
        StringBuffer focusControl = new StringBuffer("document.forms[\"");
        focusControl.append(beanName);
        focusControl.append("\"].elements[\"");
        focusControl.append(this.focus);
        focusControl.append("\"]");

        results.append("  var focusControl = ");
        results.append(focusControl.toString());
        results.append(";");
        results.append(lineEnd);
        results.append(lineEnd);

        results.append("  if (focusControl.type != \"hidden\") {");
        results.append(lineEnd);

        // Construct the index if needed and insert into focus statement
        String index = "";
        if (this.focusIndex != null) {
            StringBuffer sb = new StringBuffer("[");
            sb.append(this.focusIndex);
            sb.append("]");
            index = sb.toString();
        }
        results.append("     focusControl");
        results.append(index);
        results.append(".focus();");
        results.append(lineEnd);

        results.append("  }");
        results.append(lineEnd);

        if (!this.isXhtml()) {
            results.append("  // -->");
            results.append(lineEnd);
        }

        results.append("</script>");
        results.append(lineEnd);
        return results.toString();
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        action = null;
        moduleConfig = null;
        enctype = null;
        focus = null;
        focusIndex = null;
        mapping = null;
        method = null;
        name = null;
        onreset = null;
        onsubmit = null;
        scope = null;
        servlet = null;
        style = null;
        styleClass = null;
        styleId = null;
        target = null;
        type = null;

    }

    // ------------------------------------------------------ Protected Methods


    /**
     * Look up values for the <code>name</code>, <code>scope</code>, and
     * <code>type</code> properties if necessary.
     *
     * @exception JspException if a required value cannot be looked up
     */
    protected void lookup() throws JspException {

        // Look up the module configuration information we need
        moduleConfig = RequestUtils.getModuleConfig(pageContext);

        if (moduleConfig == null) {
            JspException e = new JspException(messages.getMessage("formTag.collections"));
            pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
            throw e;
        }
        servlet =
            (ActionServlet) pageContext.getServletContext().getAttribute(
                Globals.ACTION_SERVLET_KEY);

        // Look up the action mapping we will be submitting to
        String mappingName = RequestUtils.getActionMappingName(action);
        mapping = (ActionMapping) moduleConfig.findActionConfig(mappingName);
        if (mapping == null) {
            JspException e = new JspException(messages.getMessage("formTag.mapping", mappingName));
            pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
            throw e;
        }

        // Were the required values already specified?
        if (name != null) {
            if (type == null) {
                JspException e = new JspException(messages.getMessage("formTag.nameType"));
                pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
                throw e;
            }
            beanName = name;
            beanScope = (scope == null ? "session" : scope);
            beanType = type;
            return;
        }

        // Look up the form bean definition
        FormBeanConfig formBeanConfig = moduleConfig.findFormBeanConfig(mapping.getName());
        if (formBeanConfig == null) {
            JspException e =
                new JspException(messages.getMessage("formTag.formBean", mapping.getName()));
            pageContext.setAttribute(Globals.EXCEPTION_KEY, e, PageContext.REQUEST_SCOPE);
            throw e;
        }

        // Calculate the required values
        beanName = mapping.getAttribute();
        beanScope = mapping.getScope();
        beanType = formBeanConfig.getType();
    }

    /**
     * Returns true if this tag should render as xhtml.
     */
    private boolean isXhtml() {
        return RequestUtils.isXhtml(this.pageContext);
    }

    /**
     * Returns the focusIndex.
     * @return String
     */
    public String getFocusIndex() {
        return focusIndex;
    }

    /**
     * Sets the focusIndex.
     * @param focusIndex The focusIndex to set
     */
    public void setFocusIndex(String focusIndex) {
        this.focusIndex = focusIndex;
    }

}
