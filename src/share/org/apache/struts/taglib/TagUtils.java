/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/TagUtils.java,v 1.5 2003/07/26 01:22:31 dgraham Exp $
 * $Revision: 1.5 $
 * $Date: 2003/07/26 01:22:31 $
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

package org.apache.struts.taglib;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

/**
 * Provides helper methods for JSP tags.
 * 
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @author James Turner
 * @author David Graham
 * @version $Revision: 1.5 $
 * @since Struts 1.2
 */
public class TagUtils {

    /**
     * The Singleton instance.
     */
    private static final TagUtils instance = new TagUtils();

    /**
     * Commons logging instance.
     */
    private static final Log log = LogFactory.getLog(TagUtils.class);

    /**
     * The message resources for this package.
     * TODO We need to move the relevant messages out of this properties file.
     */
    private static final MessageResources messages =
        MessageResources.getMessageResources("org.apache.struts.util.LocalStrings");

    /**
     * Maps lowercase JSP scope names to their PageContext integer constant 
     * values. 
     */
    private static final Map scopes = new HashMap();

    /**
     * Initialize the scope names map.
     */
    static {
        scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
        scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
        scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
        scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
    }

    /**
     * Constructor for TagUtils.
     */
    protected TagUtils() {
        super();
    }

    /**
     * Returns the Singleton instance of TagUtils.
     */
    public static TagUtils getInstance() {
        return instance;
    }

    /**
     * Retrieves the value from request scope and if it isn't already an 
     * <code>ErrorMessages</code> some classes are converted to one.
     *
     * @param pageContext The PageContext for the current page
     * @param paramName Key for parameter value
     * @return ActionErrors from request scope
     * @exception JspException
     */
    public ActionErrors getActionErrors(PageContext pageContext, String paramName)
        throws JspException {

        ActionErrors errors = new ActionErrors();

        Object value = pageContext.findAttribute(paramName);

        try {
            if (value == null) {
                ;

            } else if (value instanceof String) {
                errors.add(
                    ActionErrors.GLOBAL_ERROR,
                    new ActionError((String) value));

            } else if (value instanceof String[]) {
                String keys[] = (String[]) value;
                for (int i = 0; i < keys.length; i++) {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(keys[i]));
                }

            } else if (value instanceof ActionErrors) {
                errors = (ActionErrors) value;

            } else {
                throw new JspException(
                    messages.getMessage(
                        "actionErrors.errors",
                        value.getClass().getName()));
            }

        } catch (JspException e) {
            throw e;
        } catch (Exception e) {
            log.debug(e, e);
        }

        return errors;
    }

    /**
     * Return the ModuleConfig object if it exists, null if otherwise.
     * @param pageContext The page context.
     * @return the ModuleConfig object
     */
    public ModuleConfig getModuleConfig(PageContext pageContext) {
        return RequestUtils.getModuleConfig(
            (HttpServletRequest) pageContext.getRequest(),
            pageContext.getServletContext());
    }

    /**
     * Converts the scope name into its corresponding PageContext constant value.
     * @param scopeName Can be "page", "request", "session", or "application" in any
     * case.
     * @return The constant representing the scope (ie. PageContext.REQUEST_SCOPE).
     * @throws JspException if the scopeName is not a valid name.
     */
    public int getScope(String scopeName) throws JspException {
        Integer scope = (Integer) scopes.get(scopeName.toLowerCase());

        if (scope == null) {
            throw new JspException(messages.getMessage("lookup.scope", scope));
        }

        return scope.intValue();
    }

    /**
     * Look up and return current user locale, based on the specified parameters.
     *
     * @param pageContext The PageContext associated with this request
     * @param locale Name of the session attribute for our user's Locale.  If this is 
     * <code>null</code>, the default locale key is used for the lookup.
     * @return current user locale
     */
    public Locale getUserLocale(PageContext pageContext, String locale) {
        return RequestUtils.getUserLocale(
            (HttpServletRequest) pageContext.getRequest(),
            locale);
    }

}
