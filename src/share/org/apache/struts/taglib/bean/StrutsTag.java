/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/bean/StrutsTag.java,v 1.6 2001/02/12 01:26:57 craigmcc Exp $
 * $Revision: 1.6 $
 * $Date: 2001/02/12 01:26:57 $
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


package org.apache.struts.taglib.bean;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionFormBeans;
import org.apache.struts.action.ActionForwards;
import org.apache.struts.action.ActionMappings;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;


/**
 * Define a scripting variable that exposes the requested Struts
 * internal configuraton object.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2001/02/12 01:26:57 $
 */

public class StrutsTag extends TagSupport {


    // ------------------------------------------------------------- Properties


    /**
     * The name of the scripting variable that will be exposed as a page
     * scope attribute.
     */
    protected String id = null;

    public String getId() {
        return (this.id);
    }

    public void setId(String id) {
        this.id = id;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources
        ("org.apache.struts.taglib.bean.LocalStrings");


    /**
     * The name of the <code>ActionFormBean</code> object to be exposed.
     */
    protected String formBean = null;

    public String getFormBean() {
        return (this.formBean);
    }

    public void setFormBean(String formBean) {
        this.formBean = formBean;
    }


    /**
     * The name of the <code>ActionForward</code> object to be exposed.
     */
    protected String forward = null;

    public String getForward() {
        return (this.forward);
    }

    public void setForward(String forward) {
        this.forward = forward;
    }


    /**
     * The name of the <code>ActionMapping</code> object to be exposed.
     */
    protected String mapping = null;

    public String getMapping() {
        return (this.mapping);
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Retrieve the required configuration object and expose it as a
     * scripting variable.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Validate the selector arguments
        int n = 0;
        if (formBean != null)
            n++;
        if (forward != null)
            n++;
        if (mapping != null)
            n++;
        if (n != 1) {
            JspException e = new JspException
                (messages.getMessage("struts.selector"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Retrieve the requested object to be exposed
        Object object = null;
        String selector = null;
        if (formBean != null) {
            selector = formBean;
            ActionFormBeans collection = (ActionFormBeans)
                pageContext.getAttribute(Action.FORM_BEANS_KEY,
                                         PageContext.APPLICATION_SCOPE);
            if (collection != null)
                object = collection.findFormBean(formBean);
        } else if (forward != null) {
            selector = forward;
            ActionForwards collection = (ActionForwards)
                pageContext.getAttribute(Action.FORWARDS_KEY,
                                         PageContext.APPLICATION_SCOPE);
            if (collection != null)
                object = collection.findForward(forward);
        } else if (mapping != null) {
            selector = mapping;
            ActionMappings collection = (ActionMappings)
                pageContext.getAttribute(Action.MAPPINGS_KEY,
                                         PageContext.APPLICATION_SCOPE);
            if (collection != null)
                object = collection.findMapping(mapping);
        }
        if (object == null) {
            JspException e = new JspException
                (messages.getMessage("struts.missing", selector));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        // Expose this value as a scripting variable
        pageContext.setAttribute(id, object);
        return (SKIP_BODY);

    }


    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        id = null;
        formBean = null;
        forward = null;
        mapping = null;

    }


}
