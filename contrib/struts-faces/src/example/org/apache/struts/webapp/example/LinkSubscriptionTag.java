/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/example/org/apache/struts/webapp/example/LinkSubscriptionTag.java,v 1.1 2003/03/07 03:22:42 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:42 $
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


package org.apache.struts.webapp.example;


import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.webapp.FacesTag;
import javax.servlet.jsp.JspException;


/**
 * Generate a URL-encoded hyperlink to the specified URI, with
 * associated query parameters selecting a specified Subscription.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:42 $
 */

public class LinkSubscriptionTag extends FacesTag {


    // ------------------------------------------------------------- Attributes


    /**
     * The attribute name.
     */
    protected String name = "subscription";

    public void setName(String name) {
        this.name = name;
    }


    /**
     * The context-relative URI.
     */
    protected String page = null;

    public void setPage(String page) {
        this.page = page;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * <p>Create a new component for this tag.</p>
     */
    public UIComponent createComponent() {

        return (new UIOutput());

    }


    /**
     * <p>Return the renderer type associated with this tag.</p>
     */
    public String getRendererType() {

        return ("LinkSubscription");

    }


    /**
     * <p>Release resources allocated to this tag instance.</p>
     */
    public void release() {

        super.release();
        this.name = "subscription";
        this.page = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Override attributes set on this tag instance.</p>
     *
     * @param component Component whose attributes should be overridden
     */
    protected void overrideProperties(UIComponent component) {

        super.overrideProperties(component);
        if ((name != null) &&
            (component.getAttribute("name") == null)) {
            component.setAttribute("name", name);
        }
        if ((page != null) &&
            (component.getAttribute("page") == null)) {
            component.setAttribute("page", page);
        }

    }


}
