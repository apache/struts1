/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELBaseTag.java,v 1.8 2004/01/18 13:43:11 husted Exp $
 * $Revision: 1.8 $
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

import org.apache.struts.taglib.html.BaseTag;
import javax.servlet.jsp.JspException;
import org.apache.strutsel.taglib.utils.EvalHelper;

/**
 * Renders an HTML <base> element with an href 
 * attribute pointing to the absolute location of the enclosing JSP page. This 
 * tag is only valid when nested inside a head tag body. The presence 
 * of this tag allows the browser to resolve relative URL's to images,
 * CSS stylesheets  and other resources in a manner independent of the URL
 * used to call the ActionServlet.
 *<p>
 * This class is a subclass of the class
 * <code>org.apache.struts.taglib.html.BaseTag</code> which provides most of
 * the described functionality.  This subclass allows all attribute values to
 * be specified as expressions utilizing the JavaServer Pages Standard Library
 * expression language.
 *
 * @version $Revision: 1.8 $
 */
public class ELBaseTag extends BaseTag {

    /**
     * Instance variable mapped to "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String targetExpr;
    /**
     * Instance variable mapped to "server" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    private String serverExpr;

    /**
     * Getter method for "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getTargetExpr() { return (targetExpr); }
    /**
     * Getter method for "server" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public String getServerExpr() { return (serverExpr); }

    /**
     * Setter method for "target" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setTargetExpr(String targetExpr) { this.targetExpr = targetExpr; }
    /**
     * Setter method for "server" tag attribute.
     * (Mapping set in associated BeanInfo class.)
     */
    public void setServerExpr(String serverExpr) { this.serverExpr = serverExpr; }

    /**
     * Resets attribute values for tag reuse.
     */
    public void release()
    {
        super.release();
        setTargetExpr(null);
        setServerExpr(null);
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
        String  string  = null;

        if ((string = EvalHelper.evalString("target", getTargetExpr(),
                                            this, pageContext)) != null)
            setTarget(string);

        if ((string = EvalHelper.evalString("server", getServerExpr(),
                                            this, pageContext)) != null)
            setServer(string);
    }
}
