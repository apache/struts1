/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/RedirectTag.java,v 1.14 2002/09/23 05:22:08 martinc Exp $
 * $Revision: 1.14 $
 * $Date: 2002/09/23 05:22:08 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


package org.apache.struts.taglib.logic;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;


/**
 * Generate a URL-encoded redirect to the specified URI.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.14 $ $Date: 2002/09/23 05:22:08 $
 */

public class RedirectTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The anchor to be added to the end of the generated hyperlink.
     */
    protected String anchor = null;

    public String getAnchor() {
        return (this.anchor);
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }


    /**
     * The logical forward name from which to retrieve the redirect URI.
     */
    protected String forward = null;

    public String getForward() {
    return (this.forward);
    }

    public void setForward(String forward) {
    this.forward = forward;
    }


    /**
     * The redirect URI.
     */
    protected String href = null;

    public String getHref() {
    return (this.href);
    }

    public void setHref(String href) {
    this.href = href;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources
        ("org.apache.struts.taglib.logic.LocalStrings");


    /**
     * The JSP bean name for query parameters.
     */
    protected String name = null;

    public String getName() {
    return (this.name);
    }

    public void setName(String name) {
    this.name = name;
    }


    /**
     * The module-relative page URL (beginning with a slash) to which
     * this redirect will be rendered.
     */
    protected String page = null;

    public String getPage() {
        return (this.page);
    }

    public void setPage(String page) {
        this.page = page;
    }


    /**
     * The single-parameter request parameter name to generate.
     */
    protected String paramId = null;

    public String getParamId() {
        return (this.paramId);
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }


    /**
     * The single-parameter JSP bean name.
     */
    protected String paramName = null;

    public String getParamName() {
        return (this.paramName);
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }


    /**
     * The single-parameter JSP bean property.
     */
    protected String paramProperty = null;

    public String getParamProperty() {
        return (this.paramProperty);
    }

    public void setParamProperty(String paramProperty) {
        this.paramProperty = paramProperty;
    }


    /**
     * The single-parameter JSP bean scope.
     */
    protected String paramScope = null;

    public String getParamScope() {
        return (this.paramScope);
    }

    public void setParamScope(String paramScope) {
        this.paramScope = paramScope;
    }


    /**
     * The JSP bean property name for query parameters.
     */
    protected String property = null;

    public String getProperty() {
    return (this.property);
    }

    public void setProperty(String property) {
    this.property = property;
    }


    /**
     * The scope of the bean specified by the name property, if any.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    /**
     * Include our transaction control token?
     */
    protected boolean transaction = false;

    public boolean getTransaction() {
        return (this.transaction);
    }

    public void setTransaction(boolean transaction) {
        this.transaction = transaction;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Defer generation until the end of this tag is encountered.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        return (SKIP_BODY);

    }


    /**
     * Render the redirect and skip the remainder of this page.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Calculate the redirect URL
        Map params = RequestUtils.computeParameters
            (pageContext, paramId, paramName, paramProperty, paramScope,
             name, property, scope, transaction);
        String url = null;
        try {
            url = RequestUtils.computeURL(pageContext, forward, href,
                                          page, params, anchor, true);
        } catch (MalformedURLException e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("redirect.url", e.toString()));
        }

        // Perform the redirection
    HttpServletResponse response =
      (HttpServletResponse) pageContext.getResponse();
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException(e.getMessage());
        }

        // Skip the remainder of this apge
        return (SKIP_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

    super.release();
        anchor = null;
    forward = null;
    href = null;
    name = null;
        page = null;
    paramId = null;
    paramName = null;
    paramProperty = null;
    paramScope = null;
    property = null;
        scope = null;

    }


}
