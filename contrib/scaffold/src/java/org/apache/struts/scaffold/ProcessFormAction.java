/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/ProcessFormAction.java,v 1.12 2004/03/14 14:32:19 husted Exp $
 * $Revision: 1.12 $
 * $Date: 2004/03/14 14:32:19 $
 *
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @version $Revision: 1.12 $ $Date: 2004/03/14 14:32:19 $
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