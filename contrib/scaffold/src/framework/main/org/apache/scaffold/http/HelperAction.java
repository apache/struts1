package org.apache.scaffold.http;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.util.MessageResources;

import org.apache.scaffold.lang.Tokens;


/**
 * Base class to instantiate helper objects from
 * parameter mapping. Subclasses should
 * override the alternate perform to provide
 * functionality. The standard perform will
 * instantiate the object, and call the
 * other perform method.
 * <p>
 * Multiple helper classes may be specified in a
 * comma delimited list, which will be instantiated
 * in the helpers array in the order given.
 * <p>
 * Will also look for cancel and token mappings,
 * and automatically redirect if those are present
 * and an error condition is detected.
 * <p>
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/12/23 19:32:50 $
 */
public class HelperAction extends Action {


// --------------------------------------------------------- Public Methods

    /**
     * Return the locale for the given request. If no session is set,
     * or if the session has no locale set, the default locale
     * is returned.
     * @author  François Rey (FREY - francois.rey@capco.com)
     * @author  Eric Bariaux (EBRX - eric.bariaux@capco.com)
     */
    public Locale getLocale(HttpServletRequest request) {
        Locale result = null;
        HttpSession session = request.getSession();
        if (session!=null) {
            result = (Locale) session.getAttribute(Action.LOCALE_KEY);
            if (result == null) result = Locale.getDefault();
        } else {
            result = Locale.getDefault();
        }
        return result;
    }


    /**
     * Return the application resources for this web application, if any.
     */
    public MessageResources getMessageResources() {
        return servlet.getResources();
    }


    /**
     * Number of replacement parameters permitted in Struts 1.0.
     * See also saveConfirm.
     */
    public static int CONFIRM_MAX = 5; // (Message Key, plus 1..4)


    /**
     * Retrieves a base messages and up to four replaceable
     * parameters from a List, and adds them as an ActionError.
     */
    public boolean saveMessage(ActionErrors errors, List messages) {
        if ((messages==null) || (messages.size()==0)) {
            return false;
        }
        int size = messages.size();
            // Struts 1.0 allows up to 4 parameters, 1..4
        if (size > CONFIRM_MAX) size = CONFIRM_MAX;
        String[] confirm = new String[size];
        for (int i=0; i<size; i++) {
            confirm[i] = (String) messages.get(i);
        }
        switch (size) {
           case 5:
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(confirm[0],
                        confirm[1],confirm[2],confirm[3],confirm[4]));
                break;
           case 4:
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(confirm[0],
                        confirm[1],confirm[2],confirm[3]));
                break;
           case 3:
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(confirm[0],
                        confirm[1],confirm[2]));
                break;
           case 2:
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(confirm[0],
                        confirm[1]));
                break;
           case 1:
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(confirm[0]));
        }
        return true;
    }


    /**
     * Simple test to render String version of instantiated
     * helpers. Should be overridden to provide functionality
     * (without calling super!).
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @param helper The object instantiated from type given as parameter.
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response,
                 Object[] helpers)
    throws IOException, ServletException {
        // override to provide new functionality
        response.setContentType("text/plain");
        for (int i = 0; i < helpers.length; i++)
            response.getWriter().print(helpers[i].toString());
        return (null);
    }


    /**
     * Instantiate helper objects from the type given as the
     * ActionMapping parameter (delimited with semicolons), and call
     * the alternate perform.
     * Will not call "helper" perform if helper creation fails.
     * The error is logged, and null returned.
     * <p>
     * Also checks for cancel and token conditions, if an
     * appropriate forward is present.
     * @param mapping The ActionMapping used to select this instance
     * @param form The ActionForm
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * <p>
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response)
    throws IOException, ServletException {

        // Check for cancelled
        ActionForward forward = mapping.findForward(Tokens.CANCEL);
        if ((forward!=null) && (isCancelled(request))) {
            return (forward);
        }

        // Check for missing token
        forward = mapping.findForward(Tokens.TOKEN);
        if ((forward!=null) && (!isTokenValid(request))) {
            return (forward);
        }

        // This creates a helper for each request.
        // Using data fields in the helper object is thread safe.
        StringTokenizer helperClasses =
            new StringTokenizer(mapping.getParameter(),";");
        int i = -1;
        Object[] helpers = new Object[helperClasses.countTokens()];
        while (helperClasses.hasMoreTokens()) {
            String helperClass = helperClasses.nextToken().trim();
            if ((helperClass==null) || (helperClass.length()==0)) continue;
            servlet.log("  Creating Helper for class " + helperClass,1);
            try {
                helpers[++i] = Class.forName(helperClass).newInstance();
            } catch (Throwable t) {
                servlet.log("Error creating Helper instance for path '" +
                mapping.getPath() + "', class name '" +
                helperClass + "'",t);
                return (null);
            }
            servlet.log("  Helper created " + helperClass,2);
            // Pass control to alternate perform
            // which should be overridden by subclass.
        }

        return perform(mapping,form,request,response,helpers);

    } // end perform

} // end HelperAction


/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/framework/main/org/apache/scaffold/http/Attic/HelperAction.java,v 1.1 2001/12/23 19:32:50 vmassol Exp $
 * $Revision: 1.1 $
 * $Date: 2001/12/23 19:32:50 $
 *
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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
