package org.apache.struts.scaffold;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

import org.apache.struts.util.MessageResources;

import org.apache.commons.scaffold.lang.BaseException;
import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.text.ConvertUtils;


/**
 * Enhanced base Action.
 * See the documentation for the <code>execute</code> method for
 * operational details.
 * A <code>perform</code> method is also provided for backwards
 * compatibility with 1_0.
 * :TODO: Remove deprecations after formal 1.0 Scaffod release.
 *
 * @author Ted Husted
 * @version $Revision: 1.7 $ $Date: 2002/11/23 19:09:19 $
 */
public class BaseAction extends Action {


// ---------------------------------------------------------- UTIITIES


    /**
     * Return whether Struts 1.0 compatibility should be used.
     *
     * @return True if built for Struts 1.0.x, false otherwise.
     * @deprecated Will be removed after Struts 1.1 final ships.
     */
    private final boolean isStruts_1_0() {
        return true;  // Struts 1.0.x
//      return false; // Struts 1.1
    }

// ---------------------------------------------------------------- Log

    /**
     * Is logging set this level or higher?
     *
     * @param level The debug level to test
     */
    protected boolean isLogLevel(int level) {
        return (servlet.getDebug()>=level);
    }


    /**
     * Is logging set to debugging?
     */
    protected boolean isDebug() {
        return (servlet.getDebug()>=Log.DEBUG);
    }


    /**
     * Is logging set to verbose?
     */
    protected boolean isVerbose() {
        return (servlet.getDebug()>=Log.VERBOSE);
    }


// ---------------------------------------------------------- Exception


    /**
     * Set exception to request under public key [Action.EXCEPTION].
     *
     * @param request The HTTP request we are processing
     * @param e The new Exception
     */
    protected void setException(
            HttpServletRequest request,
            Exception e) {
        request.setAttribute(EXCEPTION_KEY,e);
    }


    /**
     * Return exception stored in request under public key [Action.EXCEPTION].
     *
     * @param request The HTTP request we are processing
     */
    protected Exception getException(HttpServletRequest request) {
        return (Exception) request.getAttribute(EXCEPTION_KEY);
    }


// ------------------------------------------------------------- Locale

    /**
     * Return the framework locale object for the user session
     * for given request.
     * If no session is set, or if the session has no locale
     * set, the default locale is returned.
     *
     * @param request The HTTP request we are processing
     * author  François Rey (FREY - francois.rey@capco.com)
     * author  Eric Bariaux (EBRX - eric.bariaux@capco.com)
     */
    protected Locale getLocale(HttpServletRequest request) {

        Locale result = null;
        HttpSession session = request.getSession();
        if (session!=null) {
            result = (Locale) session.getAttribute(Action.LOCALE_KEY);
            if (result == null) result = Locale.getDefault();
        } else {
            result = Locale.getDefault();
        }

        return result;

    } // end getLocale()


    /**
     * Set the framework locale object in the session for this request.
     * If a session context does not exist, one is created.
     *
     * @param request The HTTP request we are processing
     * @param locale The locale to use for this session
     */
    protected void setLocale(
            HttpServletRequest request,
            Locale locale) {

        HttpSession session = request.getSession(true);
        session.setAttribute(Action.LOCALE_KEY,locale);

    } // end setLocale()



// ------------------------------------------------------ Remote Node


    /**
     * Returns the RemoteHost IP as an Integer.
     */
    protected Integer getRemoteNode(HttpServletRequest request) {
        return new Integer(0); // :FIXME: Non functional
    }


// ------------------------------------------------------ Remote Server

    /**
     * Default name for a remote server that may be found
     * in application scope ["REMOTE_SERVER"].
     */
    public static String REMOTE_SERVER_KEY = "REMOTE_SERVER";


    /**
     * Returns name of result server to be used by this Action,
     * e.g, RESULT_SERVER_KEY.
     */
    protected String getRemoteServerName() {
        return REMOTE_SERVER_KEY;
    }


    /**
     * Checks application scope for the remote server object
     * specified by <code>getRemoteServerName</code>
     */
    protected Object getRemoteServer() {
return servlet.getServletContext().getAttribute(getRemoteServerName());
    }

// ----------------------------------------------------------- Messages

    /**
     * Return the application properties for this web application,
     * if any.
     */
    protected Properties getProperties() {
        return (Properties) servlet.getServletContext().getAttribute(
            org.apache.commons.scaffold.lang.Tokens.PROPERTIES_KEY);
    }


    /**
     * Return the application resources for this web application,
     * if any.
     */
    protected MessageResources getMessageResources() {
        return servlet.getResources();
    }


    /**
     * Number of replacement parameters permitted in Struts 1.0.
     * See also saveConfirm.
     */
    private static int CONFIRM_MAX = 5; // (Message Key, plus 1..4)


    /**
     * Retrieves a base messages and up to four replaceable
     * parameters from a List, and adds them to an ActionErrors
     * collection.
     *
     * // :FIXME: In 1.1 this should be updated to use the
     * new ActionMessages superclass.
     *
     * @param request The request we are servicing
     * @param alerts Our ActionErrors collection
     * @param list our list of replaceable parameters
     */
    protected void mergeAlerts(
            HttpServletRequest request,
            ActionErrors alerts,
            List list) {

        if ((null!=list) && (0!=list.size())) {

            int size = list.size();
                // Struts 1.0 allows up to 4 parameters, 1..4
            if (size > CONFIRM_MAX) size = CONFIRM_MAX;
            Object[] confirm = new Object[size];

            for (int i=0; i<size; i++) {
                confirm[i] = list.get(i);
            }

            switch (size) {
               case 5:
                    alerts.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError((String) confirm[0],
                            confirm[1],confirm[2],confirm[3],
                            confirm[4]));
                    break;
               case 4:
                    alerts.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError((String) confirm[0],
                            confirm[1],confirm[2],confirm[3]));
                    break;
               case 3:
                    alerts.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError((String) confirm[0],
                            confirm[1],confirm[2]));
                    break;
               case 2:
                    alerts.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError((String) confirm[0],
                            confirm[1]));
                    break;
               case 1:
                    alerts.add(ActionErrors.GLOBAL_ERROR,
                        new ActionError((String) confirm[0]));
            }

        }

    } // end mergeAlerts()


    /**
     * Check for pending message collection.
     * If it doesn't exist, and create is true, a new collection is
     * returned.
     *
     * :FIXME: In 1.1 this should be the ActionMessage superclass
     * @param request The HTTP request we are processing
     * @param create Whether to create a new collection if one does
     * not exist
     * @return The pending ActionError queue
     */
    protected ActionErrors getMessages(
            HttpServletRequest request,
            boolean create) {

            // :FIXME: In 1.1 should use public static
        final String
            MESSAGE_KEY = "org.apache.struts.action.ACTION_MESSAGE";

        ActionErrors alerts = (ActionErrors)
            request.getAttribute(MESSAGE_KEY);

        if ((null==alerts) && (create)) {
            alerts = new ActionErrors();
                // Bypass Action.SaveMessage() since it
                // won't accept a blank collection
            request.setAttribute(MESSAGE_KEY,alerts);
        }

        return alerts;

    } // end getMessages()


    /**
     * Merge incoming messages and save to messages
     * collection. The collection is automatically saved to the request,
     * so <code>Action.saveErrors(request,errors)</code> does not need
     * to be called. If this method called more than once, the new
     * messages are appended.
     * <p>
     * This method is upwardly compatabile with Struts 1.1 and uses the
     * messages queue introduced with that release.
     *
     * @param request The HTTP request we are processing
     * @param list The ResourceBundle token followed by 0 or more
     * parameters
     */
    protected void saveMessages(
            HttpServletRequest request,
            List list) {

        ActionErrors alerts = getMessages(request,true);
        mergeAlerts(request,alerts,list);

    } // end saveMessages()


    /**
     * Return whether there is an informational alert collection pending.
     *
     * @param request The HTTP request we are processing
     * @return True if an informational alert collection exists
     */
    protected boolean isMessages(HttpServletRequest request) {
        return (null!=getMessages(request,false));
    }


    /**
     * Merge incoming messages and save to errors
     * collection. The collection is automatically saved to the request,
     * so <code>Action.saveErrors(request,errors)</code> does not need
     * to be called. If this method called more than once, the new
     * messages are appended.
     *
     * @param request The HTTP request we are processing
     * @param list The ResourceBundle token followed by 0 or more
     * parameters
     */
    protected void saveErrors(
            HttpServletRequest request,
            List list) {

        ActionErrors alerts = getErrors(request,true);
        mergeAlerts(request,alerts,list);

    } // end saveErrors


    /**
     * Return whether there is an errors alert collection pending.
     *
     * @param request The HTTP request we are processing
     * @return True if an errors alert collection exists
     */
    protected ActionErrors getErrors(
            HttpServletRequest request,
            boolean create) {

        ActionErrors alerts = (ActionErrors)
            request.getAttribute(ERROR_KEY);

        if ((null==alerts) && (create)) {

            alerts = new ActionErrors();
                // Bypass Action.SaveError() since it
                // won't accept a blank collection
            request.setAttribute(ERROR_KEY,alerts);

        }

        return alerts;

    } // end getErrors()


    /**
     * Return whether there is an errors alert collection pending.
     *
     * @param request The HTTP request we are processing
     * @return True if an errors alert collection exists
     */
    protected boolean isErrors(HttpServletRequest request) {
        return (null!=getErrors(request,false));
    }


// ------------------------------------------------------------ Helpers

    /**
     * Default separator character for list of tokens [";"] (semi-colon).
     */
    public static final String TOKEN_SEP = ";";


    /**
     * Return separator character for list of tokens
     */
    public String getTokenSep() {
        return TOKEN_SEP;
    }


    /**
     * Return array of tokens,
     * using the result of <code>getTokeSep()</code> as the
     * separator.
     * Blanks are trimmed from tokens.
     *
     * @param parameter The string to tokenize into an array
     */
    public String[] tokenize(String parameter) {
        
        return ConvertUtils.tokensToArray(parameter,getTokenSep());
             
/*        
        StringTokenizer tokenizer =
            new StringTokenizer(parameter,getTokenSep());
        int i = 0;
        String[] tokens = new String[tokenizer.countTokens()];
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if ((token==null) || (token.length()==0)) continue;
            tokens[i++] = token;
        }
        return tokens;
*/
    } // end tokenize()


    /**
     * Create an object of the specified class,
     * throwing a runtime exception if any error occurs.
     * If method succeeds, then helpers are guaranteed
     * to exist.
     *
     * @param request The HTTP request we are processing
     * @param helperClass The name of the class
     * @throws IllegalArgumentException if helper cannot be
     * instantiated
     */
    public Object createHelperObject(
            HttpServletRequest request,
            String helperClass) {

        StringBuffer sb = new StringBuffer();

        if (isDebug()) {
            sb.append(Log.HELPER_CREATING);
            sb.append(helperClass);
            servlet.log(sb.toString());
        }

            // Try the create
        Object helper = null;
        try {
            helper = Class.forName(helperClass).newInstance();
        }
        catch (Throwable t) {
           helper = null;
               // assemble message: {class}: {exception}
           sb.setLength(0);
           sb.append(Log.CREATE_OBJECT_ERROR);
           sb.append(Log.CLASS);
           sb.append(helperClass);
           sb.append(Log.SPACE);
           sb.append(Log.ACTION_EXCEPTION);
           sb.append(t.toString());
                // throw runtime exception
           throw new IllegalArgumentException(sb.toString());
        }

        if (isDebug()) {
            sb.setLength(0);
            sb.append(Log.HELPER_CREATED);
            sb.append(Log.CLASS);
            sb.append(helperClass);
            servlet.log(sb.toString());
        }

        return helper;

     } // createHelperObject()


    /**
     * Return an instance of the form-bean associated with the
     * specified name, if any; otherwise return <code>null</code>.
     * May be used to create an bean registered under a known name or
     * a form-bean name passed with the mapping.
     * <p>
     * It is not required that the form-bean specified here be an
     * ActionForm subclass. This allows other helper classes to be
     * registered as form-beans and then instantiated by the Action.
     *
     * @param request The HTTP request we are processing
     * @param helperName name of the form-bean helper
     */
    protected Object createHelperBean(
            HttpServletRequest request,
            String helperName) {

        StringBuffer sb = new StringBuffer();

        if (isDebug()) {
            sb.append(Log.HELPER_CREATING);
            sb.append(Log.NAME);
            sb.append(helperName);
            servlet.log(sb.toString());
        }

        Object bean = null;

        ActionFormBean formBean = servlet.findFormBean(helperName);
        if (formBean != null) {
            String className = null;
            className = formBean.getType();
            try {
                Class clazz = Class.forName(className);
                bean = clazz.newInstance();
            } catch (Throwable t) {
                bean = null;
                    // assemble message: {class}: {exception}
                sb.setLength(0);
                sb.append(Log.CREATE_OBJECT_ERROR);
                sb.append(Log.NAME);
                sb.append(helperName);
                sb.append(Log.SPACE);
                sb.append(Log.ACTION_EXCEPTION);
                sb.append(t.toString());
                String message = sb.toString();
                servlet.log(message);
                   // echo log message as error
                ActionErrors errors = getErrors(request,true);
                errors.add(ActionErrors.GLOBAL_ERROR,
                    new ActionError(Tokens.HELPER_ACCESS_ERROR,
                        message));
            }
            if (isDebug()) {
                sb.setLength(0);
                sb.append(Log.HELPER_CREATED);
                sb.append(Log.NAME);
                sb.append(helperName);
                servlet.log(sb.toString());
            }
        }
        else {
                // Not found
            sb.setLength(0);
            sb.append(Log.CREATE_OBJECT_ERROR);
            sb.append(Log.NAME);
            sb.append(helperName);
            sb.append(Log.SPACE);
            sb.append(Log.NOT_FOUND);
            String message = sb.toString();
            servlet.log(message);
                // echo log message as error
            ActionErrors errors = getErrors(request,true);
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError(Tokens.HELPER_ACCESS_ERROR,
                    message));
        }

        return bean;

    } // createHelperBean()



// --------------------------------------------------- EXTENSION POINTS


    /**
     * Optional extension point for pre-processing.
     * Default method does nothing.
     * To branch to another URI, return an non-null ActionForward.
     * If errors are logged (getErrors() et al),
     * default behaviour will branch to findFailure().
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     */
    protected void preProcess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        // override to provide functionality

    } // end preProcess()


     /**
      * Return the appropriate ActionForward for an
      * error condition.
      * The default method returns a forward to input,
      * when there is one, or "error" when not.
      * The application must provide an "error" forward.
      * An advanced implementation could check the errors
      * and provide different forwardings for different circumstances.
      * One possible error may be whether the form is null.
      *
      * @param mapping The ActionMapping used to select this instance
      * @param form The optional ActionForm bean for this request
      * @param request The HTTP request we are processing
      * @param response The resonse we are creating
      * @return The ActionForward representing FAILURE
      * or null if a FAILURE forward has not been specified.
      */
     protected ActionForward findFailure(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

            // If input page, use that
        if (mapping.getInput()!=null)
            return (new ActionForward(mapping.getInput()));

            // If no input page, use error forwarding
        return mapping.findForward(Tokens.FAILURE);

    } // end findFailure()


    /**
     * Execute the business logic for this Action.
     * <p>
     * The default method logs the executeLogic() "event"
     * when the logging level is set to DEBUG.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     */
    public void executeLogic(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
        throws Exception {

            // override to provide functionality, like
            // myBusinessObject.execute(form);
        servlet.log(Log.BASE_LOGIC_EXECUTING,Log.DEBUG);

    } // end executeLogic()


    /**
     * Token to print before short description
     * of an exception.
     */
    private static final String ERROR = " ERROR: ";

    /**
     * Token to print between short and long description
     * of an exception.
     */
    private static final String DETAILS = " DETAILS: ";


    /**
     * Process the exception handling for this Action.
     *
     * If Exception is subclass of BaseException, will
     * report on everything in chain.
     *
     * Default behaviour should suffice for most circumstances.
     * If overridden, if an alert is logged to the errors
     * queue (getErrors()), then default behaviour will branch
     * to findFailure().
     * :TODO: Use a StringBufferOUTPUTStream to capture trace for error queue
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     */
    protected void catchException(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response) {


            // Retrieve, log and print to error console
        Exception exception = getException(request);
        servlet.log(Log.ACTION_EXCEPTION, exception);
        exception.printStackTrace();

            // General error message
        ActionErrors errors = getErrors(request,true);
        errors.add(ActionErrors.GLOBAL_ERROR,
            new ActionError(Tokens.ERROR_GENERAL));

            // Wrap exception messages in ActionError
            // If chained, descend down chain
            // appending messages to StringBuffer
        StringBuffer sb = new StringBuffer();
        if (exception instanceof BaseException) {
           BaseException e = (BaseException) exception;
           e.getMessage(sb);
        }
        else {
            sb.append(ConvertUtils.LINE_FEED);
            sb.append(ERROR);
            sb.append(exception.toString());
            String message = exception.getMessage();
            if (null!=message) {
                sb.append(ConvertUtils.LINE_FEED);
                sb.append(DETAILS);
                sb.append(message);
                sb.append(ConvertUtils.LINE_FEED);
            }
            // :TODO: Use a StringBufferOUTPUTStream to capture trace
        }

        errors.add(ActionErrors.GLOBAL_ERROR,
            new ActionError(Tokens.ERROR_DETAIL,sb.toString()));

    } // end catchException()


    /**
     * Optional extension point for post-processing.
     * Default method does nothing.
     * This is called from a finally{} clause,
     * and so is guaranteed to be called after executeLogic() or
     * catchException().
     * Use getException() to check if error occured.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The resonse we are creating
     */
    protected void postProcess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        // override to provide functionality

    } // end postProcess()


    /**
     * Return the appropriate ActionForward for the nominal,
     * non-error state.
     * The default returns mapping.findForward("continue");
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The response we are creating
     * @return The ActionForward representing SUCCESS
     * or null if a SUCCESS forward has not been specified.
     */
    protected ActionForward findSuccess(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        return mapping.findForward(Tokens.SUCCESS);

    } // end findSuccess()


// --------------------------------------------------- ANCESTOR METHODS


    /**
     * Wrapper around original <code>perform()</code> method
     * to call now-preferred <code>execute()</code> method,
     * This provides forward compatibility for Struts 1.0.x a
     * applications.
     * A 1.0.x application will call this method, which will return the
     * outcome of the <code>execute()</code> method.
     * A 1.1+ application will ignore this wrapper method, and call
     * <code>execute()</code> directly.
     * Good practice says that the <code>execute()</code> method
     * should cope with any exceptions and forward to the appropriate
     * error page.
     * But if any were to slip through, they are wrapped as
     * <code>ServetExceptions</code> and rethrown.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward perform(
                 ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response)
        throws IOException, ServletException {

        try {

            return execute(
                mapping,
                form,
                request,
                response
            );
        }

        catch (Exception e) {
            throw new ServletException(e);
        }
    }


    /**
     * Skeleton method that calls the other extension points
     * (or "hotspots") provided by this class. These are:
     * <ul>
     * <li><code>preProcess(),</code></li>
     * <li><code>executeLogic(),</code></li>
     * <li><code>catchException(),</code></li>
     * <li><code>postProcess(),</code></li>
     * <li><code>findFailure(),</code></li>
     * <li><code>findSuccess()</code></li>
     * </ul>
     * Typically, you can just override the other extension points
     * as needed, and leave this one as is.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward execute(
                 ActionMapping mapping,
                 ActionForm form,
                 HttpServletRequest request,
                 HttpServletResponse response) throws Exception {


            // Check for precondition errors; fail if found
        preProcess(mapping,form,request,response);

        if (isErrors(request)) {
            return findFailure(mapping,form,request,response);
        }

            // Try the logic; Call catchException() if needed
        try {

            executeLogic(mapping,form,request,response);
        }

        catch (Exception e) {
                // Store Exception; call extension point
            setException(request,e);
            catchException(mapping,form,request,response);
        }

        finally {
            postProcess(mapping,form,request,response);
        }

            // If errors queued, fail
        if (isErrors(request)) {
            return findFailure(mapping,form,request,response);
        }

            // Otherwise, check for messages and succeed (only 1_0)
        if ((isStruts_1_0()) && (isMessages(request))) {
            saveErrors(request,getMessages(request,false));
        }

        return findSuccess(mapping,form,request,response);

    } // end execute()

} // end BaseAction


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


