/*
 * $Id$ 
 *
 * Copyright 2004 The Apache Software Foundation.
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
 
 
package org.apache.struts.scaffold.SetPathsAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.scaffold.BaseAction;
import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;


/**
 * Standard Action to set the paths from
 * enumerated forwards as request attributes.
 *
 * @version $Rev$ $Date$
 */
public final class SetPathsAction extends BaseAction {


    /**
     * Lookup the forward each token given on the parameter property,
     * and set its path as a request attribute under the token name.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * <p>
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
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

        for (int i=0; i<helperCount; i++) {
            request.setAttribute(
                tokens[i],
                mapping.findForward(tokens[i]).getPath());
        }

    }

} // end SetPathsAction