/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionServletWrapper.java,v 1.8 2003/05/01 16:16:49 rleland Exp $
 * $Revision: 1.8 $
 * $Date: 2003/05/01 16:16:49 $
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


package org.apache.struts.action;

import org.apache.struts.upload.MultipartRequestHandler;


/**
 * Provide a wrapper around an ActionServlet to expose only
 * those methods needed by other objects. When used with an
 * ActionForm, subclasses must be careful that they do
 * not return an object with public getters and setters that
 * could be exploited by automatic population of properties.
 * @author Craig R. McClanahan
 * @author Ted Husted
 * @version $Revision: 1.8 $ $Date: 2003/05/01 16:16:49 $
 * @since Struts 1.0.1
 */
public class ActionServletWrapper {


    /**
     * The controller servlet instance to which we are attached.
     */
    protected transient ActionServlet servlet = null;



    /**
     * Log the specified message if the current debugging detail level for
     * this servlet has been set to an equal or higher value.  Otherwise,
     * ignore this message.
     *
     * @param message Message to be logged
     * @param level Debugging detail level of this message
     * @deprecated  Logging should now use the commons logging
     */
    public void log(String message, int level) {

           servlet.log(message,level);

    }

    /**
     * Log message.
     * @param message
     */
    public void log(String message) {

            servlet.log(message);

    }

    /**
     * Set servlet to a MultipartRequestHandler.
     * @param object The MultipartRequestHandler
     * :FIXME: Should this be based on an "setServlet"
     * interface or introspection for a setServlet method?
     * Or, is it safer to just add the types we want as we want them?
     */
     public void setServletFor(MultipartRequestHandler object) {
        object.setServlet(this.servlet);
    }


    /**
     * Create object and set servlet property.
     * @param servlet <code>ActionServlet</code> to wrap
     */
     public ActionServletWrapper(ActionServlet servlet) {
        super();
        this.servlet = servlet;
    }

}
