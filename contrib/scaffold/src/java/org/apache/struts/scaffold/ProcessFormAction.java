package org.apache.struts.scaffold;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;

/**
 * ProcessAction subclass that checks to see if a another
 * bean exists in the request scope before overwriting it.
 * If another bean is present, it is populated from the
 * ProcessBean.
 * <P>
 * Any JavaBean can be used to populate a Struts presentation
 * page, and it is not always necessary to transfer data from
 * a business bean to a form bean.
 * Other times, the form bean will have special helper
 * properties for the benefit of the presentation layer.
 * For example, a second password field may be needed to
 * validate the user's data-entry but would not be part of
 * the business API.
 * <P>
 * The <CODE>ProcessFormAction</CODE> will check to see if
 * another bean is in scope (presumably a form bean).
 * If another bean exists, ProcessFormAction populates it
 * from the business bean,
 * The net result is an automatic data transfer from a
 * business bean to the form bean.
 *
 * @author Ted Husted
 * @author OK State DEQ
 * @version $Revision: 1.9 $ $Date: 2003/01/15 10:19:26 $
 */
public class ProcessFormAction extends ProcessAction {


// --------------------------------------------------------- Public Methods


    /**
     * Exposes result in a servlet context.
     * If there is an existing bean with same attribute name in the target
     * context,
     * then the matching properties on that bean are populated with the
     * result.
     * :TODO: Change from BeanUtil.populate to copyProperties
     * in 1.1 version.
     *
     * @param request the request being serviced
     * @param name The name to use in scope
     * @param scope The scope to set the attribute in
     * @param bean The attribute to be set
     */
    protected void exposeInScope(
            HttpServletRequest request,
            HttpServletResponse response,
            String name,
            String scope,
            Object bean) {

        if (null==scope) {
            servlet.log(Log.PROCESS_BEAN_NULL_SCOPE,Log.DEBUG);
        }
        if (null==bean) super.exposeInScope(request,response,name,scope,bean);
        else if (Tokens.REQUEST.equals(scope)) {
            Object form = request.getAttribute(name);
            if (null==form) {
                request.setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else if (Tokens.SESSION.equals(scope)) {
            Object form = request.getSession().getAttribute(name);
            if (null==form) {
                request.getSession().setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else if (Tokens.APPLICATION.equals(scope)) {
            Object form = servlet.getServletContext().getAttribute(name);
            if (null==form) {
                servlet.getServletContext().setAttribute(name,bean);
            }
            else {
                try {
                    BeanUtils.copyProperties(form,bean);
                }
                catch (Exception e) {
                    throw new RuntimeException(e.toString());
                }
            }
        }
        else {
            StringBuffer sb = new StringBuffer("exposeInScope: ");
            sb.append(scope);
            sb.append(Tokens.INVALID_SCOPE);
            servlet.log(sb.toString(),Log.DEBUG);
            throw new IllegalArgumentException(sb.toString());
        }

    } // end exposeInScope()

} // end ProcessFormAction


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


