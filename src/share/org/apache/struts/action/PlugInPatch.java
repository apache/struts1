/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/PlugInPatch.java,v 1.1 2002/11/09 05:39:38 rleland Exp $
 * $Revision: 1.1 $
 * $Date: 2002/11/09 05:39:38 $
 *
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


import javax.servlet.ServletException;
import org.apache.struts.config.ModuleConfig;


/**
 * <p>
 * This interface defines an <strong>optional</strong> PlugIn init(ActionServlet, ModuleConfig) method.
 * It is used <strong>in addition to</strong> the
 * <code>Plugin</code> interface and <strong>does not</strong> replace it!
 * It allows <code>PlugIn</code> developers to implement this new interface
 * as they have time. During initialization, if the <code>PlugInPatch</code>
 * interface is implemented then its' <code>init()</code> method will be called.
 * If only the <code>PlugIn</code> interface is implemented
 * then its' <code>init()</code> method will be called.
 * Sometime after the official 1.1 release of struts the
 * <code>init(ActionServlet, ModuleConfig)</code>  method will be incorporated
 * into the <code>PlugIn</code> interface. At which time the <code>PlugInPatch</code>
 * interface will have its'<code>init()</code>  method removed. </p>
 * <p>See the Validator and
 * Tiles Plugin for an example, conversion time should be
 * <strong>2</strong> minutes or less.
 * </p>
 *
 * @author Rob Leland
 * @version $Revision: 1.1 $ $Date: 2002/11/09 05:39:38 $
 * @since Struts 1.1
 */

public interface PlugInPatch {

    /**
     * <p>Receive notification that the specified module is being
     * started up.</p>
     *
     * @param servlet ActionServlet that is managing all the application
     *  modules in this web application
     * @param config ModuleConfig for the module with which
     *  this plug-in is associated
     *
     * @exception ServletException if this <code>PlugIn</code> cannot
     *  be successfully initialized
     */
    public void init(ActionServlet servlet, ModuleConfig config)
        throws ServletException;


}
