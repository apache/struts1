/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/HtmlTag.java,v 1.17 2003/07/31 00:19:04 dgraham Exp $
 * $Revision: 1.17 $
 * $Date: 2003/07/31 00:19:04 $
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

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

/**
 * Renders an HTML <html> element with appropriate language attributes if
 * there is a current Locale available in the user's session.
 *
 * @author Craig R. McClanahan
 * @author David Graham
 * @version $Revision: 1.17 $ $Date: 2003/07/31 00:19:04 $
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
     * @deprecated This will be removed after Struts 1.2.
     */
    protected boolean locale = false;

    /**
     * @deprecated This will be removed after Struts 1.2.
     */
    public boolean getLocale() {
        return (locale);
    }

    /**
     * @deprecated This will be removed after Struts 1.2.
     */
    public void setLocale(boolean locale) {
        this.locale = locale;
    }

    /**
     * Are we rendering an xhtml page?
     */
    protected boolean xhtml = false;
    
    /**
     * Are we rendering a lang attribute?
     * @since Struts 1.2
     */
    protected boolean lang = false;

    public boolean getXhtml() {
        return this.xhtml;
    }

    public void setXhtml(boolean xhtml) {
        this.xhtml = xhtml;
    }
    
    /**
     * Returns true if the tag should render a lang attribute.
     * @since Struts 1.2
     */
    public boolean getLang() {
        return this.lang;
    }

    /**
     * Sets whether the tag should render a lang attribute.
     * @since Struts 1.2
     */
    public void setLang(boolean lang) {
        this.lang = lang;
    }

    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        TagUtils.getInstance().write(this.pageContext, this.renderHtmlStartElement());

        return EVAL_BODY_INCLUDE;
    }

    /**
     * Renders an &lt;html&gt; element with appropriate language attributes.
     * @since Struts 1.2
     */
    protected String renderHtmlStartElement() {
        StringBuffer sb = new StringBuffer("<html");

        String language = null;
        String country = "";
                
        if (this.locale) {
            // provided for 1.1 backward compatibility, remove after 1.2
            language = this.getCurrentLocale().getLanguage();
        } else {
            Locale currentLocale =
                TagUtils.getInstance().getUserLocale(pageContext, Globals.LOCALE_KEY);

            language = currentLocale.getLanguage();
            country = currentLocale.getCountry();
        }

        boolean validLanguage = ((language != null) && (language.length() > 0));
        boolean validCountry = country.length() > 0;

        if (this.xhtml) {
            this.pageContext.setAttribute(
                Globals.XHTML_KEY,
                "true",
                PageContext.PAGE_SCOPE);
                
            sb.append(" xmlns=\"http://www.w3.org/1999/xhtml\"");
        }

        if ((this.lang || this.locale || this.xhtml) && validLanguage) {
            sb.append(" lang=\"");
            sb.append(language);
            if (validCountry) {
                sb.append("-");
                sb.append(country);
            }
            sb.append("\"");
        }

        if (this.xhtml && validLanguage) {
            sb.append(" xml:lang=\"");
            sb.append(language);
            if (validCountry) {
                sb.append("-");
                sb.append(country);
            }
            sb.append("\"");
        }

        sb.append(">");

        return sb.toString();
    }


    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        TagUtils.getInstance().write(pageContext, "</html>");

        // Evaluate the remainder of this page
        return (EVAL_PAGE);

    }

    /**
     * Release any acquired resources.
     */
    public void release() {
        this.locale = false;
        this.xhtml = false;
        this.lang=false;
    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the current Locale for this request.  If there is no locale in the session and
     * the locale attribute is set to "true", this method will create a Locale based on the 
     * client's Accept-Language header or the server's default locale and store it in the 
     * session.  This will always return a Locale and never null.
     * @since Struts 1.1
     * @deprecated This will be removed after Struts 1.2.
     */
    protected Locale getCurrentLocale() {

        Locale userLocale = TagUtils.getInstance().getUserLocale(pageContext, Globals.LOCALE_KEY);

        // Store a new current Locale, if requested
        if (this.locale) {
            HttpSession session = ((HttpServletRequest) this.pageContext.getRequest()).getSession();
            session.setAttribute(Globals.LOCALE_KEY, userLocale);
        }

        return userLocale;
    }



}
