package org.apache.struts.scaffold;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;


/**
 * Base class to instantiate helper objects from parameter property.
 * Subclasses should override the alternate executeLogic to provide
 * functionality.
 * The standard executeLogic will instantiate the object(s),
 * and call the alternate executeLogic method, passing the helpers
 * in an array.
 * <p>
 * Multiple helper classes may be specified in a semi-colon delimited list,
 * which will be instantiated in the helpers array in the order given.
 *
 * @version $Revision: 1.3 $ $Date: 2004/01/18 13:43:09 $
 */
public class BaseHelperAction extends BaseAction {


// --------------------------------------------------------- Public Methods


    /**
     * Simple test to render String version of instantiated
     * helpers. Should be overridden to provide functionality
     * (without calling super!).
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param helper The object instantiated from type given as parameter.
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    protected void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response,
            Object[] helpers)
        throws Exception {
        // override to provide new functionality
        response.setContentType(Tokens.TEXT_PLAIN);
        for (int i = 0; i < helpers.length; i++)
            response.getWriter().print(helpers[i].toString());
    }


    /**
     * Instantiate helper objects from the type given as the
     * ActionMapping parameter (delimited with semicolons), and call
     * the alternate executeLogic.
     * Will not call "helper" perform if helper creation fails.
     * The error is logged, and null returned.
     * <p>
     * Also checks for cancel and token conditions, if an
     * appropriate forward is present.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * <p>
     * @exception Exception if error occurs
     */
    public void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {

        servlet.log(Log.TOKENS_PARSING,Log.DEBUG);
        String[] tokens = tokenize(mapping.getParameter());
        int helperCount = tokens.length;
        Object[] helpers = new Object[helperCount];

        for (int i=0; i<helperCount; i++) {
            helpers[i] = createHelperObject(request,tokens[i]);
        }

        servlet.log(Log.HELPER_EXECUTING,Log.DEBUG);
        executeLogic(mapping,form,request,response,helpers);
    }

} // end BaseHelperAction


/*
 *
 *    Copyright (c) 2002 Synthis Corporation.
 *    430 10th Street NW, Suite S-108, Atlanta GA 30318, U.S.A.
 *    All rights reserved.
 *
 *    This software is licensed to you free of charge under
 *    the Apache Software License, so long as this copyright
 *    statement, list of conditions, and comments,  remains
 *    in the source code.  See bottom of file for more
 *    license information.
 *
 *    This software was written to support code generation
 *    for the Apache Struts J2EE architecture by Synthis'
 *    visual application modeling tool Adalon.
 *
 *    For more information on Adalon and Struts code
 *    generation please visit http://www.synthis.com
 *
 */


 /*
  * ====================================================================
  *
  * The Apache Software License, Version 1.1
  *
  * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
  * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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


