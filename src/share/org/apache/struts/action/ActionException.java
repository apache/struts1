/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionException.java,v 1.3 2002/06/24 17:10:35 husted Exp $
 * $Revision: 1.3 $
 * $Date: 2002/06/24 17:10:35 $
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
package org.apache.struts.action;


import org.apache.struts.config.ExceptionConfig;


/**
 * An <strong>ActionException</strong> represents a potential exception
 * that may occur during delegation to an Action class.
 * Instances of this class may be configured in association
 * with an <code>ActionMapping</code> instance for named lookup of potentially
 * multiple destinations for a particular mapping instance.
 * <p>
 * An <code>ActionException</code> has the following minimal set of properties.
 * Additional properties can be provided as needed by subclassses.
 * <ul>
 * <li><strong>type</strong> - The fully qualified class name of the
 * exception to be associated to a particular <code>ActionMapping</code>.
 * <li><strong>key</strong> - (Optional) Message key associated with the particular
 *     exception.
 * <li><strong>path</strong> - (Optional) Context releative URI that should
 *     be redirected to as a result of the exception occuring.  Will overide the
 *     input form of the associated ActionMapping if one is provided.
 * <li><strong>scope</strong> - (Optional) The scope to store the exception in
 *     if a problem should occur - defaults to 'request'.  Valid values are
 *     'request' and 'session'.
 * <li><strong>hierarchical</strong> - (Optional) Defines whether or not the
 *     Exception hierarchy should be used when determining if an occuring
 *     exception can be assigned to a mapping instance.  Default is true.
 * <li><strong>handler</strong> - (Optional) The fully qualified class name
 *     of the handler, which is responsible to handle this exception.
 *     Default is 'org.apache.struts.action.ExceptionHandler'.
 * </ul>
 *
 * @author ldonlan
 * @version $Revision: 1.3 $ $Date: 2002/06/24 17:10:35 $
 *
 * @since Struts 1.1
 * @deprecated Replaced by org.apache.struts.config.ExceptionConfig
 */
public class ActionException extends ExceptionConfig {

    /**
     *Returns an instance of an <b>ActionError</b> configured for
     *this exception.
     *@return ActionError
     */
    public ActionError getError() {
        return new ActionError(this.key);
    }

}
