/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/BaseHelperAction.java,v 1.5 2004/03/14 14:32:19 husted Exp $
 * $Revision: 1.5 $
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
 * @version $Revision: 1.5 $ $Date: 2004/03/14 14:32:19 $
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