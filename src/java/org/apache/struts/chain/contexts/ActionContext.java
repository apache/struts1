/*
 * $Id$
 *
 * Copyright 2005 The Apache Software Foundation.
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
package org.apache.struts.chain.contexts;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.chain.Context;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;
import org.apache.struts.action.ActionForm;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.action.Action;


/**
 * <p>An ActionContext represents a view of a commons-chain
 * <code>Context</code> which encapsulates access to request
 * and session-scoped resources and services</p>
 */
public interface ActionContext extends Context {

    public static final String APPLICATION_SCOPE = "application";

    public static final String SESSION_SCOPE = "session";

    public static final String REQUEST_SCOPE = "request";

    // -------------------------------
    // General Application Support
    // -------------------------------
    /**
     * Signal to the instance that it will not be used any more, so that any resources which should
     * be cleaned up can be cleaned up.
     */
    void release();

    /**
     * <p>Return a <code>Map</code> of Application scoped values.</p>
     *
     * <p>This is implemented in analogy with the Application scope in
     * the Servlet API, but it seems reasonable to expect that any
     * Struts implementation will have an equivalent concept.</p>
     *
     * <p>The ultimate meaning of "application scope" is an implementation detail
     * left unspecified by Struts.</p>
     * @return
     */
    Map getApplicationScope();

    /**
     * <p>Return a <code>Map</code> of Session scoped values.  A session
     * is understood as a sequence of requests made by the same user.</p>
     *
     * <p>This is implemented in analogy with the Session scope in
     * the Servlet API, but it seems reasonable to expect that any
     * Struts implementation will have an equivalent concept.</p>
     *
     * <p>The ultimate meaning of "session scope" is an implementation detail
     * left unspecified by Struts.</p>
     * @return
     */
    Map getSessionScope();

    /**
     * <p>Return a <code>Map</code> of request scoped values.  A
     * request is understood as the fundamental motivation for any
     * particular instance of an <code>ActionContext</code>.</p>
     *
     * <p>This is implemented in analogy with the Request Context in
     * the Servlet API, but it seems reasonable to expect that any
     * Struts implementation will have an equivalent concept.</p>
     *
     * <p>The ultimate meaning of "request scope" is an implementation detail
     * left unspecified by Struts.</p>
     * @return
     */
    Map getRequestScope();

    /**
     * Return the Map representing the scope identified by <code>scopeName</code>.
     * Implementations should support at minimum the names associated with the constants
     * <code>APPLICATION_SCOPE</code>, <code>SESSION_SCOPE</code>, and
     * <code>REQUEST_SCOPE</code>, but are permitted to support others as well.
     * @param scopeName
     * @return
     */
    Map getScope(String scopeName);

    /**
     * <p>Return a <code>Map</code> of parameters submitted by the user
     * as part of this request.  The keys to this map will be request parameter
     * names (of type <code>String</code>), and the values will be <code>String[]</code>.</p>
     *
     * <p>This is implemented in analogy with the Request parameters of
     * the Servlet API, but it seems reasonable to expect that any
     * Struts implementation will have an equivalent concept.</p>
     *
     * @return
     */
    Map getParameterMap();

    // -------------------------------
    // General Struts properties
    // -------------------------------

    /**
     * Set the action which has been identified to be executed as part
     * of processing this request.
     * @param action
     */
    void setAction(Action action);

    /**
     * Get the action which has been identified to be executed as part
     * of processing this request.
     *
     * @return
     */
    Action getAction();

    /**
     * Set the ActionForm instance which will carry any data submitted
     * as part of this request.

     * @param form
     */
    void setActionForm(ActionForm form);

    /**
     * Get the ActionForm instance which will carry any data submitted as
     * part of this request.
     *
     * @return
     */
    ActionForm getActionForm();

    /**
     * Set the ActionConfig class contains the details
     * for processing this request.
     * @param config
     */
    void setActionConfig(ActionConfig config);

    /**
     * Get the ActionConfig which contains the details for processing this request.
     * @return
     */
    ActionConfig getActionConfig();

    /**
     * Set the ForwardConfig which should be used as the basis of the view segment
     * of the overall processing.  This is the primary method of "communication" with
     * the "view" sub-chain.
     *
     * @param forward
     */
    void setForwardConfig(ForwardConfig forward);

    /**
     * Get the ForwardConfig which has been identified as the basis for view-processing.
     *
     * @return
     */
    ForwardConfig getForwardConfig();

    /**
     * Set the include path which should be processed as part of processing this request.
     *
     * @param forward
     */
    void setInclude(String include);

    /**
     * Get the include path which should be processed as part of processing this request.
     *
     * @return
     */
    String getInclude();

    /**
     * Set the ModuleConfig which is operative for the current request.
     *
     * @param config
     */
    void setModuleConfig(ModuleConfig config);

    /**
     * Get the ModuleConfig which is operative for the current request.
     * @return
     */
    ModuleConfig getModuleConfig();

    /**
     * Is the ActionForm for this context valid?  This <em>does not</em> actually perform
     * form validation.  It is simply a holder property where processes which perform validation
     * can store the results of the validation for other processes' benefit.
     *
     * @return <code>Boolean.TRUE</code> if the form passed validation; <code>Boolean.FALSE</code>
     * if the form failed validation; null if the form has not yet been validated.
     */
    Boolean getFormValid();

    /**
     * Store the result of the validation of the Context's ActionForm.
     * @param valid
     */
    void setFormValid(Boolean valid);

    /**
     * Retrieve an exception which may have been caught by some code using this ActionContext.
     * @return
     */
    Exception getException();

    /**
     * Store an exception in this context for use by other handling code.
     * @param e
     */
    void setException(Exception e);

    // -------------------------------
    // ActionMessage Processing
    // -------------------------------

    /**
     * Adds the specified messages keys into the appropriate request
     * attribute for use by the &lt;html:messages&gt; tag (if
     * messages="true" is set), if any messages are required.
     * Initialize the attribute if it has not already been.
     * Otherwise, ensure that the request attribute is not set.
     *
     * @param messages  Messages object
     */
    void addMessages(ActionMessages messages);

    /**
     * Adds the specified errors keys into the appropriate request
     * attribute for use by the for use by the &lt;html:errors&gt; tag,
     * if any messages are required.
     * Initialize the attribute if it has not already been.
     * Otherwise, ensure that the request attribute is not set.
     *
     * @param errors  Errors object
     */
    void addErrors(ActionMessages errors);

    /**
     * Retrieves any existing errors placed in the request by previous actions.  This method could be called instead
     * of creating a <code>new ActionMessages()<code> at the beginning of an <code>Action<code>
     * This will prevent saveErrors() from wiping out any existing Errors
     *
     * @return the Errors that already exist in the request, or a new ActionMessages object if empty.
     * @param request The servlet request we are processing
     */
    ActionMessages getErrors();

    /**
     * Retrieves any existing messages placed in the request by previous actions.  This method could be called instead
     * of creating a <code>new ActionMessages()<code> at the beginning of an <code>Action<code>
     * This will prevent saveMessages() from wiping out any existing Messages
     *
     * @return the Messages that already exist in the request, or a new ActionMessages object if empty.
     * @param request The servlet request we are processing
     */
    ActionMessages getMessages();

    /**
     * <p>Save the specified error messages keys into the appropriate request
     * attribute for use by the &lt;html:errors&gt; tag, if any messages
     * are required. Otherwise, ensure that the request attribute is not
     * created.</p>
     *
     * @param request The servlet request we are processing
     * @param errors Error messages object
     */
    void saveErrors(ActionMessages errors);


    /**
     * <p>Save the specified messages keys into the appropriate request
     * attribute for use by the &lt;html:messages&gt; tag (if
     * messages="true" is set), if any messages are required. Otherwise,
     * ensure that the request attribute is not created.</p>
     *
     * @param messages The messages to save. <code>null</code> or empty
     * messages removes any existing ActionMessages in the request.
     *
     */
    void saveMessages(ActionMessages messages);


    /**
     * <p>Save the specified messages keys into the appropriate scope
     * for use by the &lt;html:messages&gt; tag (if
     * messages="true" is set), if any messages are required. Otherwise,
     * ensure that the session attribute is not created.</p>
     *
     * @param messages The messages to save. <code>null</code> or empty
     * messages removes any existing ActionMessages in the session.
     *
     */
    void saveMessages(String scope, ActionMessages messages);

    // -------------------------------
    // Token Processing
    // -------------------------------

    /**
     * <p>Generate a new transaction token, to be used for enforcing a single
     * request for a particular transaction.</p>
     *
     */
    String generateToken();

    /**
     * <p>Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it. Returns <code>false</code>
     * under any of the following circumstances:</p>
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     */
    boolean isTokenValid();


    /**
     * <p>Return <code>true</code> if there is a transaction token stored in
     * the user's current session, and the value submitted as a request
     * parameter with this action matches it. Returns <code>false</code>.</p>
     * <ul>
     * <li>No session associated with this request</li>
     * <li>No transaction token saved in the session</li>
     * <li>No transaction token included as a request parameter</li>
     * <li>The included transaction token value does not match the
     *     transaction token in the user's session</li>
     * </ul>
     *
     * @param request The servlet request we are processing
     * @param reset Should we reset the token after checking it?
     */
    boolean isTokenValid(boolean reset);


    /**
     * <p>Reset the saved transaction token in the user's session. This
     * indicates that transactional token checking will not be needed
     * on the next request that is submitted.</p>
     *
     * @param request The servlet request we are processing
     */
    void resetToken();



    /**
     * <p>Save a new transaction token in the user's current session, creating
     * a new session if necessary.</p>
     *
     * @param request The servlet request we are processing
     */
    void saveToken();





    // -------------------------------
    // Cancel Processing
    // -------------------------------

    /**
     * <p>Returns <code>Boolean.TRUE</code> if the current form's cancel button was
     * pressed. Rather than evaluating any system state,
     * this simply reflects the value of the <code>cancelled</code> property,
     * assuming it was set elsewhere by some other process.</p>
     *
     * @see org.apache.struts.taglib.html.CancelTag
     */
    Boolean getCancelled();

    /**
     * <p>Indicate that the current form was detected to be cancelled.</p>
     * @param cancelled
     */
    void setCancelled(Boolean cancelled);


    // -------------------------------
    // MessageResources Processing
    // -------------------------------

    /**
     * <p>Return the default message resources for the current module.</p>
     *
     */
    MessageResources getMessageResources();

    /**
     * <p>Set the default message resources for the current module.</p>
     *
     * @return the default message resources for the current module.
     */
    void setMessageResources(MessageResources resources);


    /**
     * <p>Return the specified message resources for the current module.</p>
     *
     * @param request The servlet request we are processing
     * @param key The key specified in the
     *  <code>&lt;message-resources&gt;</code> element for the
     *  requested bundle
     *
     */
    MessageResources getMessageResources(String key);

    // -------------------------------
    // Locale Processing
    // -------------------------------

    /**
     * <p>Return the user's currently selected Locale.</p>
     *
     */
    Locale getLocale();



    /**
     * <p>Set the user's currently selected <code>Locale</code>.</p>
     *
     * @param locale The user's selected Locale to be set, or null
     *  to select the server's default Locale
     */
    void setLocale(Locale locale);
}
