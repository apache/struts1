/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/HtmlTag.java,v 1.2 2001/02/11 00:14:50 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2001/02/11 00:14:50 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;

/**
 * Renders an HTML <html> element with appropriate language attributes if
 * there is a current Locale available in the user's session.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2001/02/11 00:14:50 $
 */

public class HtmlTag extends TagSupport {
  

    // ------------------------------------------------------------- Properties


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


    /**
     * Should we set the current Locale for this user if needed?
     */
    protected boolean locale = false;

    public boolean getLocale() {
        return (locale);
    }

    public void setLocale(boolean locale) {
        this.locale = locale;
    }


    /**
     * Are we rendering an xhtml page?
     */
    protected boolean xhtml = false;

    public boolean getXhtml() {
        return (xhtml);
    }

    public void setXhtml(boolean xhtml) {
        this.xhtml = xhtml;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        StringBuffer sb = new StringBuffer("<html");

        // Use the current Locale to set our language preferences
        Locale currentLocale = currentLocale();
        if (currentLocale != null) {
            String lang = currentLocale.getLanguage();
            if ((lang != null) && (lang.length() > 0)) {
                sb.append(" lang=\"");
                sb.append(lang);
                sb.append("\"");
                if (xhtml) {
                    sb.append(" xml:lang=\"");
                    sb.append(lang);
                    sb.append("\"");
                }
            }
        }
        sb.append(">");

        // Write out the beginning tag for this page
        JspWriter out = pageContext.getOut();
        try {
            out.println(sb.toString());
        } catch (IOException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("common.io", e.toString()));
        }
        return (EVAL_BODY_INCLUDE);

    }


    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        JspWriter out = pageContext.getOut();
        try {
            out.println("</html>");
        } catch (IOException e) {
            pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                     PageContext.REQUEST_SCOPE);
            throw new JspException
                (messages.getMessage("common.io", e.toString()));
        }
        return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        locale = false;
        xhtml = false;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the current Locale for this request, creating a new one if
     * necessary.  If there is no current Locale, and locale support is not
     * requested, return <code>null</code>.
     */
    protected Locale currentLocale() {

        // Create a new session if necessary
        HttpSession session = pageContext.getSession();
        if (locale && (session == null))
            session =
                ((HttpServletRequest) pageContext.getRequest()).getSession();
        if (session == null)
            return (null);

        // Return any currently set Locale in our session
        Locale current = (Locale) session.getAttribute(Action.LOCALE_KEY);
        if (current != null)
            return (current);

        // Configure a new current Locale, if requested
        if (!locale)
            return (null);
        current = pageContext.getRequest().getLocale();
        if (current != null)
            session.setAttribute(Action.LOCALE_KEY, current);
        return (current);

    }


}
