/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/actions/LocaleAction.java,v 1.3 2004/01/13 12:48:45 husted Exp $
 * $Revision: 1.3 $
 * $Date: 2004/01/13 12:48:45 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2000-2003 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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


package org.apache.struts.actions;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Implementation of <strong>Action</strong> that changes the user's
 * @link(java.util.Locale and forwards to a page, based on request level
 * parameters that are set  (language, country, &amp; page).
 *
*/
public final class LocaleAction extends Action {

    /**
     * Commons Logging instance.
    */
    private Log log = LogFactory.getFactory().getInstance(this.getClass().getName());

    /**
     * <p>
     * Change the user's @link(java.util.Locale) based on @link(ActionForm)
     * properties.
     * </p>
     * <p>
     * This <code>Action</code> looks for <code>language</code> and
     * <code>country</code> properties on the given form, constructs an
     * appropriate Locale object, and sets it as the Struts Locale for this
     * user's session.
     * Any <code>ActionForm, including a @link(DynaActionForm), may be used.
     * </p>
     * <p>
     * If a <code>page</code> property is also provided, then after
     * setting the Locale, control is forwarded to that URI path.
     * Otherwise, control is forwarded to "success".
     * </p>
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @return Action to forward to
     * @exception java.lang.Exception if an input/output error or servlet exception occurs
     */
    public ActionForward execute(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response)
    throws Exception {

    // Extract attributes we will need
    HttpSession session = request.getSession();
    Locale locale = getLocale(request);

    String language = null;
    String country = null;
    String page = null;

    try {
            language = (String)
              PropertyUtils.getSimpleProperty(form, "language");
            country = (String)
              PropertyUtils.getSimpleProperty(form, "country");
            page = (String)
              PropertyUtils.getSimpleProperty(form, "page");
        } catch (Exception e) {
           log.error(e.getMessage(), e);
        }

        if ((language != null && language.length() > 0) &&
            (country != null && country.length() > 0)) {
           locale = new java.util.Locale(language, country);
        } else if (language != null && language.length() > 0) {
           locale = new java.util.Locale(language, "");
    }

        session.setAttribute(Globals.LOCALE_KEY, locale);

        if (null==page) return mapping.findForward("success");
        else return new ActionForward(page);

    }

}
