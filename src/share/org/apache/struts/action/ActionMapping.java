/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionMapping.java,v 1.27 2003/02/25 04:58:29 dgraham Exp $
 * $Revision: 1.27 $
 * $Date: 2003/02/25 04:58:29 $
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


import java.util.ArrayList;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;


/**
 * <p>An <strong>ActionMapping</strong> represents the information that the
 * controller servlet, <code>ActionServlet</code>, knows about the mapping
 * of a particular request to an instance of a particular action class.
 * The ActionMapping instance used to select a particular Action is passed
 * on to that Action, thereby providing access to any custom configuration
 * information included with the ActionMapping object.</p>
 *
 * <p>Since Struts 1.1 this class extends <code>ActionConfig</code>.
 *
 * <p><strong>NOTE</strong> - This class would have been deprecated and
 * replaced by <code>org.apache.struts.config.ActionConfig</code> except
 * for the fact that it is part of the public API that existing applications
 * are using.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.27 $ $Date: 2003/02/25 04:58:29 $
 */

public class ActionMapping extends ActionConfig {


    /**
     * <p>Find and return the <code>ExceptionConfig</code> instance defining
     * how exceptions of the specified type should be handled.  This is
     * performed by checking local and then global configurations for the
     * specified exception's class, and then looking up the superclass chain
     * (again checking local and then global configurations).  If no handler
     * configuration can be found, return <code>null</code>.</p>
     *
     * @param type Exception class for which to find a handler
     * @since Struts 1.1
     */
    public ExceptionConfig findException(Class type) {

        // Check through the entire superclass hierarchy as needed
        ExceptionConfig config = null;
        while (true) {

            // Check for a locally defined handler
            String name = type.getName();
            config = findExceptionConfig(name);
            if (config != null) {
                return (config);
            }

            // Check for a globally defined handler
            config = getModuleConfig().findExceptionConfig(name);
            if (config != null) {
                return (config);
            }

            // Loop again for our superclass (if any)
            type = type.getSuperclass();
            if (type == null) {
                break;
            }

        }
        return (null); // No handler has been configured

    }


    /**
     * <p>Find and return the <code>ForwardConfig</code> instance defining
     * how forwarding to the specified logical name should be handled.  This is
     * performed by checking local and then global configurations for the
     * specified forwarding configuration.  If no forwarding configuration
     * can be found, return <code>null</code>.</p>
     *
     * @param name Logical name of the forwarding instance to be returned
     */
    public ActionForward findForward(String name) {

        ForwardConfig config = findForwardConfig(name);
        if (config == null) {
            config = getModuleConfig().findForwardConfig(name);
        }
        return ((ActionForward) config);

    }


    /**
     * <p>Return the logical names of all locally defined forwards for this
     * mapping.  If there are no such forwards, a zero-length array
     * is returned.
     */
    public String[] findForwards() {

        ArrayList results = new ArrayList();
        ForwardConfig fcs[] = findForwardConfigs();
        for (int i = 0; i < fcs.length; i++) {
            results.add(fcs[i].getName());
        }
        return ((String[]) results.toArray(new String[results.size()]));

    }


    /**
     * <p>Create (if necessary) and return an {@link ActionForward} that
     * corresponds to the <code>input</code> property of this Action.
     *
     * @since Struts 1.1
     */
    public ActionForward getInputForward() {

        if (getModuleConfig().getControllerConfig().getInputForward()) {
            return (findForward(getInput()));
        } else {
            return (new ActionForward(getInput()));
        }

    }


}
