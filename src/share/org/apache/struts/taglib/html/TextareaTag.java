/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/TextareaTag.java,v 1.12 2002/12/16 03:41:43 craigmcc Exp $
 * $Revision: 1.12 $
 * $Date: 2002/12/16 03:41:43 $
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


import java.lang.reflect.InvocationTargetException;
import javax.servlet.jsp.JspException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Custom tag for input fields of type "textarea".
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.12 $ $Date: 2002/12/16 03:41:43 $
 */

public class TextareaTag extends BaseInputTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The name of the bean containing our underlying property.
     */
    protected String name = Constants.BEAN_KEY;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Generate the required input tag.
     * Support for indexed since Struts 1.1
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Create an appropriate "input" element based on our parameters
        StringBuffer results = new StringBuffer("<textarea");
        results.append(" name=\"");
        // @since Struts 1.1
        if( indexed )
                prepareIndex( results, name );
        results.append(property);
        results.append("\"");
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
        if (cols != null) {
            results.append(" cols=\"");
            results.append(cols);
            results.append("\"");
        }
        if (rows != null) {
            results.append(" rows=\"");
            results.append(rows);
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(">");
        if (value != null) {
            results.append(ResponseUtils.filter(value));
        } else {
            Object bean = RequestUtils.lookup(pageContext, name, null);
            if (bean == null)
                throw new JspException
                    (messages.getMessage("getter.bean", name));
            try {
                String value = BeanUtils.getProperty(bean, property);
                if (value == null)
                    value = "";
                results.append(ResponseUtils.filter(value));
            } catch (IllegalAccessException e) {
                throw new JspException
                    (messages.getMessage("getter.access", property, name));
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                throw new JspException
                    (messages.getMessage("getter.result",
                                         property, t.toString()));
            } catch (NoSuchMethodException e) {
                throw new JspException
                    (messages.getMessage("getter.method", property, name));
            }
        }
        results.append("</textarea>");

        // Print this field to our output writer
        ResponseUtils.write(pageContext, results.toString());

        // Continue processing this page
        return (EVAL_BODY_TAG);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        name = Constants.BEAN_KEY;

    }


}
