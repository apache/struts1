/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ErrorsTag.java,v 1.21 2003/05/15 23:43:42 dgraham Exp $
 * $Revision: 1.21 $
 * $Date: 2003/05/15 23:43:42 $
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

import java.util.Iterator;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

/**
 * Custom tag that renders error messages if an appropriate request attribute
 * has been created.  The tag looks for a request attribute with a reserved
 * key, and assumes that it is either a String, a String array, containing
 * message keys to be looked up in the module's MessageResources, or
 * an object of type <code>org.apache.struts.action.ActionErrors</code>.
 * <p>
 * The following optional message keys will be utilized if corresponding
 * messages exist for them in the application resources:
 * <ul>
 * <li><b>errors.header</b> - If present, the corresponding message will be
 *     rendered prior to the individual list of error messages.</li>
 * <li><b>errors.footer</b> - If present, the corresponding message will be
 *     rendered following the individual list of error messages.</li>
 * <li><b>errors.prefix</b> - If present, the corresponding message will be
 *     rendered before each individual error message.</li>
 * <li><b>errors.suffix</b> - If present, the corresponding message will be
 *     rendered after each individual error message.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.21 $ $Date: 2003/05/15 23:43:42 $
 */
public class ErrorsTag extends TagSupport {

    // ----------------------------------------------------------- Properties

    /**
     * The servlet context attribute key for our resources.
     */
    protected String bundle = null;

    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    /**
     * The default locale on our server.
     * @deprecated Use Locale.getDefault() directly.
     */
    protected static Locale defaultLocale = Locale.getDefault();

    /**
     * The line ending string.
     */
    protected static String lineEnd = System.getProperty("line.separator");

    /**
     * The session attribute key for our locale.
     */
    protected String locale = Globals.LOCALE_KEY;

    public String getLocale() {
        return (this.locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

    /**
     * The request attribute key for our error messages (if any).
     */
    protected String name = Globals.ERROR_KEY;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The name of the property for which error messages should be returned,
     * or <code>null</code> to return all errors.
     */
    protected String property = null;

    public String getProperty() {
        return (this.property);
    }

    public void setProperty(String property) {
        this.property = property;
    }

    // ------------------------------------------------------- Public Methods

    /**
     * Render the specified error messages if there are any.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Were any error messages specified?
        ActionErrors errors = null;
        try {
            errors = RequestUtils.getActionErrors(pageContext, name);
        } catch (JspException e) {
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        if ((errors == null) || errors.isEmpty()) {
            return (EVAL_BODY_INCLUDE);
        }

        boolean headerPresent =
            RequestUtils.present(pageContext, bundle, locale, "errors.header");

        boolean footerPresent =
            RequestUtils.present(pageContext, bundle, locale, "errors.footer");

        boolean prefixPresent =
            RequestUtils.present(pageContext, bundle, locale, "errors.prefix");

        boolean suffixPresent =
            RequestUtils.present(pageContext, bundle, locale, "errors.suffix");

        // Render the error messages appropriately
        StringBuffer results = new StringBuffer();
        boolean headerDone = false;
        String message = null;
        Iterator reports = (property == null) ? errors.get() : errors.get(property);

        while (reports.hasNext()) {
            ActionError report = (ActionError) reports.next();
            if (!headerDone) {
                if (headerPresent) {
                    message =
                        RequestUtils.message(
                            pageContext,
                            bundle,
                            locale,
                            "errors.header");
                            
                    results.append(message);
                    results.append(lineEnd);
                }
                headerDone = true;
            }
            
            if (prefixPresent) {
                message =
                    RequestUtils.message(
                        pageContext,
                        bundle,
                        locale,
                        "errors.prefix");
                results.append(message);
            }
            
            message =
                RequestUtils.message(
                    pageContext,
                    bundle,
                    locale,
                    report.getKey(),
                    report.getValues());
                    
            if (message != null) {
                results.append(message);
                results.append(lineEnd);
            }
            
            if (suffixPresent) {
                message =
                    RequestUtils.message(
                        pageContext,
                        bundle,
                        locale,
                        "errors.suffix");
                results.append(message);
            }
        }
        
        if (headerDone && footerPresent) {
            message =
                RequestUtils.message(pageContext, bundle, locale, "errors.footer");
            results.append(message);
            results.append(lineEnd);
        }

        ResponseUtils.write(pageContext, results.toString());

        return (EVAL_BODY_INCLUDE);

    }

    /**
     * Release any acquired resources.
     */
    public void release() {
        super.release();
        bundle = Globals.MESSAGES_KEY;
        locale = Globals.LOCALE_KEY;
        name = Globals.ERROR_KEY;
        property = null;
    }

}
