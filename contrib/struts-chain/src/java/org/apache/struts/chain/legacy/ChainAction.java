/*
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.struts.chain.legacy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>An <code>Action</code> implementation that delegates to a
 * configured Chain (or Command) for performing the actual business
 * logic related to a request.  The name of the chain to be executed
 * is specified by setting the <code>parameter</code> attribute of
 * the <code>&lt;action&gt;</code> element configuring this action.
 * For example:</p>
 *
 * <pre>
 *   &lt;action path="/myaction"
 *           type="org.apache.struts.chain.legacy.ChainAction"
 *           name="myform"
 *          scope="request"
 *          input="/mypage.jsp"
 *      parameter="name-of-chain-to-execute"
 * </pre>
 *
 * <p>Prior to calling the specified chain (or command), this action
 * sets up a <code>Context</code> object containing the relevant
 * properties, along with the following additional attributes:</p>
 * <ul>
 * <li><strong>mapping</strong> - The <code>ActionMapping</code> passed
 *     to our <code>execute()</code> method</li>
 * <li><strong>form</strong> - The <code>ActionForm</code> passed to
 *     our <code>execute()</code> method</li>
 * </ul>
 *
 * <p>After execution of the specified command or chain is completed,
 * the following context attributes are examined (in this order) to
 * determine how to proceed.</p>
 * <ul>
 * <li><strong>exception</strong> - If a <code>java.lang.Exception</code>
 *     is found here, it will be rethrown as the outcome of this action.</li>
 * <li><strong>forward</strong> - If an
 *     <code>org.apache.struts.action.ActionForward</code> is found here,
 *     it will be returned as the outcome of this action.  Otherwise,
 *     <code>null</code> will be returned.</li>
 * </ul>
 */

public class ChainAction extends Action {


    // ------------------------------------------------------- Instance Varibles


    /**
     * <p>The <code>Catalog</code> that will be consulted to look up
     * the <code>Command</code> to be executed.</p>
     */
    private Catalog catalog = null;


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Delegate to the command chain specified in our configuration.</p>
     *
     * @param mapping <code>ActionMapping</code> configuring this action
     * @param form <code>ActionForm</code> for this request (if any)
     * @param request <code>HttpServletRequest</code> we are processing
     * @param response <code>HttpServletResponse</code> we are creating
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception {

        // Set up a context for this request
        Context context = new ServletWebContext
            (getServlet().getServletContext(), request, response);
        context.put("mapping", mapping);
        context.put("form", form);

        // Delegate to the specified command
        Command command = getCatalog().getCommand(mapping.getParameter());
        command.execute(context); // Ignore return state

        // Return results as appropriate
        Exception exception = null;
        try {
            exception = (Exception) context.get("exception");
            if (exception != null) {
                throw exception;
            }
        } catch (ClassCastException e) {
            ; // It is not an Exception
        }
        ActionForward forward = null;
        try {
            forward = (ActionForward) context.get("forward");
        } catch (ClassCastException e) {
            forward = null; // It is not an ActionForward
        }
        return forward;

    }


    // ------------------------------------------------------- Protected Methods


    /**
     * <p>Return the <code>Catalog</code> we will use to acquire the
     * <code>Command</code> to be executed.  NOTE: Any race condition
     * calling this method is harmless, so do not bother to synchronize.</p>
     */
    protected Catalog getCatalog() {

        if (catalog == null) {
            catalog = CatalogFactory.getInstance().getCatalog();
        }
        return catalog;

    }


}
