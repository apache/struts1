/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionErrors.java,v 1.11 2003/04/15 00:18:45 dgraham Exp $
 * $Revision: 1.11 $
 * $Date: 2003/04/15 00:18:45 $
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

import java.io.Serializable;

/**
 * <p>A class that encapsulates the error messages being reported by
 * the <code>validate()</code> method of an <code>ActionForm</code>.
 * Validation errors are either global to the entire <code>ActionForm</code>
 * bean they are associated with, or they are specific to a particular
 * bean property (and, therefore, a particular input field on the corresponding
 * form).</p>
 *
 * <p>Each individual error is described by an <code>ActionError</code>
 * object, which contains a message key (to be looked up in an appropriate
 * message resources database), and up to four placeholder arguments used for
 * parametric substitution in the resulting message.</p>
 *
 * <p><strong>IMPLEMENTATION NOTE</strong> - It is assumed that these objects
 * are created and manipulated only within the context of a single thread.
 * Therefore, no synchronization is required for access to internal
 * collections.</p>
 *
 * @author David Geary
 * @author Craig R. McClanahan
 * @version $Revision: 1.11 $ $Date: 2003/04/15 00:18:45 $
 */

public class ActionErrors extends ActionMessages implements Serializable {

    // ----------------------------------------------------- Manifest Constants

    /**
     * The "property name" marker to use for global errors, as opposed to
     * those related to a specific property.
     */
    public static final String GLOBAL_ERROR = "org.apache.struts.action.GLOBAL_ERROR";

    // --------------------------------------------------------- Public Methods

    /**
     * Create an empty <code>ActionErrors</code> object.
     */
    public ActionErrors() {
        super();
    }

    /**
     * Create an <code>ActionErrors</code> object initialized with the given 
     * messages.
     * 
     * @param messages The messages to be initially added to this object.
     * This parameter can be <code>null</code>.
     * @since Struts 1.1
     */
    public ActionErrors(ActionErrors messages) {
        super(messages);
    }

    /**
     * Add an error message to the set of errors for the specified property.
     *
     * @param property Property name (or ActionErrors.GLOBAL_ERROR)
     * @param error The error message to be added
     */
    public void add(String property, ActionError error) {

        super.add(property, error);

    }

}
