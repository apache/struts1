/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELMessagesTag.java,v 1.7 2004/01/18 13:43:11 husted Exp $
 * $Revision: 1.7 $
 * $Date: 2004/01/18 13:43:11 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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

package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.MessagesTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Custom tag that iterates the elements of a message collection.
 * It defaults to retrieving the messages from <code>Action.ERROR_KEY</code>,
 * but if the message attribute is set to true then the messages will be
 * retrieved from <code>Action.MESSAGE_KEY</code>. This is an alternative
 * to the default <code>ErrorsTag</code>.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.MessagesTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @version $Revision: 1.7 $
 */
public class ELMessagesTag extends MessagesTag {

    /**
     * Instance variable mapped to "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String idExpr;
    /**
     * Instance variable mapped to "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String bundleExpr;
    /**
     * Instance variable mapped to "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String localeExpr;
    /**
     * Instance variable mapped to "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String nameExpr;
    /**
     * Instance variable mapped to "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String propertyExpr;
    /**
     * Instance variable mapped to "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String headerExpr;
    /**
     * Instance variable mapped to "footer" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String footerExpr;
    /**
     * Instance variable mapped to "message" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String messageExpr;

    /**
     * Getter method for "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getIdExpr() { return (idExpr); }
    /**
     * Getter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getBundleExpr() { return (bundleExpr); }
    /**
     * Getter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLocaleExpr() { return (localeExpr); }
    /**
     * Getter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getNameExpr() { return (nameExpr); }
    /**
     * Getter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getPropertyExpr() { return (propertyExpr); }
    /**
     * Getter method for "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getHeaderExpr() { return (headerExpr); }
    /**
     * Getter method for "footer" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getFooterExpr() { return (footerExpr); }
    /**
     * Getter method for "message" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getMessageExpr() { return (messageExpr); }

    /**
     * Setter method for "id" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setIdExpr(String idExpr) { this.idExpr = idExpr; }
    /**
     * Setter method for "bundle" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setBundleExpr(String bundleExpr) { this.bundleExpr = bundleExpr; }
    /**
     * Setter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLocaleExpr(String localeExpr) { this.localeExpr = localeExpr; }
    /**
     * Setter method for "name" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setNameExpr(String nameExpr) { this.nameExpr = nameExpr; }
    /**
     * Setter method for "property" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setPropertyExpr(String propertyExpr) { this.propertyExpr = propertyExpr; }
    /**
     * Setter method for "header" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setHeaderExpr(String headerExpr) { this.headerExpr = headerExpr; }
    /**
     * Setter method for "footer" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setFooterExpr(String footerExpr) { this.footerExpr = footerExpr; }
    /**
     * Setter method for "message" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setMessageExpr(String messageExpr) { this.messageExpr = messageExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setIdExpr(null);
        setBundleExpr(null);
        setLocaleExpr(null);
        setNameExpr(null);
        setPropertyExpr(null);
        setHeaderExpr(null);
        setFooterExpr(null);
        setMessageExpr(null);
    }
    
    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        String  string  = null;

        if ((string = EvalHelper.evalString("id", getIdExpr(),
                                            this, pageContext)) != null)
            setId(string);

        if ((string = EvalHelper.evalString("bundle", getBundleExpr(),
                                            this, pageContext)) != null)
            setBundle(string);

        if ((string = EvalHelper.evalString("locale", getLocaleExpr(),
                                            this, pageContext)) != null)
            setLocale(string);

        if ((string = EvalHelper.evalString("name", getNameExpr(),
                                            this, pageContext)) != null)
            setName(string);

        if ((string = EvalHelper.evalString("property", getPropertyExpr(),
                                            this, pageContext)) != null)
            setProperty(string);

        if ((string = EvalHelper.evalString("header", getHeaderExpr(),
                                            this, pageContext)) != null)
            setHeader(string);

        if ((string = EvalHelper.evalString("footer", getFooterExpr(),
                                            this, pageContext)) != null)
            setFooter(string);

        if ((string = EvalHelper.evalString("message", getMessageExpr(),
                                            this, pageContext)) != null)
            setMessage(string);
    }
}
