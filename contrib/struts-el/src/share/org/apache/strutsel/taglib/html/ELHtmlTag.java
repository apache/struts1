/*
 * $Id$ 
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.HtmlTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Renders an HTML <html> element with appropriate language attributes if
 * there is a current Locale available in the user's session.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.HtmlTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @version $Rev$
 */
public class ELHtmlTag extends HtmlTag {

    /**
     * Instance variable mapped to "lang" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String langExpr;
    /**
     * Instance variable mapped to "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String localeExpr;
    /**
     * Instance variable mapped to "xhtml" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String xhtmlExpr;

    /**
     * Getter method for "lang" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLangExpr() { return (langExpr); }
    /**
     * Getter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getLocaleExpr() { return (localeExpr); }
    /**
     * Getter method for "xhtml" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getXhtmlExpr() { return (xhtmlExpr); }

    /**
     * Setter method for "lang" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLangExpr(String langExpr) { this.langExpr = langExpr; }
    /**
     * Setter method for "locale" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setLocaleExpr(String localeExpr) { this.localeExpr = localeExpr; }
    /**
     * Setter method for "xhtml" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setXhtmlExpr(String xhtmlExpr) { this.xhtmlExpr = xhtmlExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setLangExpr(null);
        setLocaleExpr(null);
        setXhtmlExpr(null);
    }
    
    /**
     * Process the start tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {
        Boolean bool    = null;
        String  string  = null;

        if ((bool = EvalHelper.evalBoolean("lang", getLangExpr(),
                                           this, pageContext)) != null)
            setLang(bool.booleanValue());

        if ((bool = EvalHelper.evalBoolean("locale", getLocaleExpr(),
                                           this, pageContext)) != null)
            setLocale(bool.booleanValue());

        if ((bool = EvalHelper.evalBoolean("xhtml", getXhtmlExpr(),
                                           this, pageContext)) != null)
            setXhtml(bool.booleanValue());
    }
}
