/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ButtonTag.java,v 1.14 2002/11/16 06:05:21 dgraham Exp $
 * $Revision: 1.14 $
 * $Date: 2002/11/16 06:05:21 $
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


package org.apache.struts.taglib.html;


import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;


/**
 * Renders an HTML BUTTON tag within the Struts framework.
 *
 * @author Don Clasen
 * @version $Revision: 1.14 $ $Date: 2002/11/16 06:05:21 $
 */

public class ButtonTag extends BaseHandlerTag {


    // ----------------------------------------------------- Instance Variables

    /**
     * The property name of the generated button.
     */
    protected String property = null;


    /**
     * The body content of this tag (if any).
     */
    protected String text = null;


    /**
     * The value of the button label.
     */
    protected String value = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the property name.
     */
    public String getProperty() {
        return (property);
    }

    /**
     * Set the property name.
     *
     * @param property The property name
     */
    public void setProperty(String property) {
        this.property = property;
    }


    /**
     * Return the label value.
     */
    public String getValue() {
        return (value);
    }


    /**
     * Set the label value.
     * @param value The label value
     */
    public void setValue(String value) {
        this.value = value;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Process the start of this tag.
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Do nothing until doEndTag() is called
        this.text = null;
        return (EVAL_BODY_TAG);

    }


    /**
     * Save the associated label from the body content (if any).
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            String value = bodyContent.getString().trim();
            if (value.length() > 0)
                text = value;
        }
        return (SKIP_BODY);

    }


    /**
     * Process the end of this tag.
     * <p>
     * Support for indexed property since Struts 1.1
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Acquire the label value we will be generating
        String label = value;
        if ((label == null) && (text != null))
            label = text;
        if ((label == null) || (label.trim().length() < 1))
            label = "Click";

        // Generate an HTML element
        StringBuffer results = new StringBuffer();
        results.append("<input type=\"button\"");
        if (property != null) {
            results.append(" name=\"");
            results.append(property);
            // * @since Struts 1.1
            if( indexed )
                prepareIndex( results, null );
            results.append("\"");
        }
        if (accesskey != null) {
            results.append(" accesskey=\"");
            results.append(accesskey);
            results.append("\"");
        }
        if (tabindex != null) {
            results.append(" tabindex=\"");
            results.append(tabindex);
            results.append("\"");
        }
        results.append(" value=\"");
        results.append(label);
        results.append("\"");
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(getElementClose());

        // Render this element to our writer
        ResponseUtils.write(pageContext, results.toString());

        // Evaluate the remainder of this page
        return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        property = null;
        text = null;
        value = null;

    }


}
