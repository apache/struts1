/*
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

package org.apache.shale;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>{@link DialogController} is an interface describing a JavaBean that
 * manages the current state of a dialog (that is, a conversation that
 * requires multiple HTTP requests to interact with the user).  In addition,
 * processing methods are provided so that event handlers in the
 * {@link ViewController} for an individual view can navigate to various
 * portions of the dialog, without having to be aware of the identifers of
 * the other views that comprise the overall dialog.</p>
 *
 * <p>The basic {@link DialogController} interface provides a generic
 * set of attributes in which an application may store arbitrary information
 * representing the state of the overall computation.  To operate robustly
 * in servlet containers that support session migration and/or saving and
 * restoring on application restart, all such attributes (and, indeed, any
 * implementation of the {@link DialogController} interface) must be
 * Serializable.</p>
 *
 * <p>Best practices for creating {@link DialogController} implementations
 * include the following:</p>
 * <ul>
 * <li>Because {@link DialogController} instances will typically be saved
 *     across HTTP requests (in an <code>HttpSession</code>, cached in a
 *     database or filesystem, etc.) you should minimize the amount of state
 *     information that is maintained.</li>
 * <li>Do not maintain state information that can be easily recalculated.
 *     For example, keep primary keys of relevant database rows rather than
 *     the complete rows themselves -- that data can be re-acquired as
 *     needed in some later step of the dialog.</li>
 * <li>In general, no changes to the underlying persistent data in your
 *     application's model tier should be made until the user selects a
 *     "finish" or "apply" command.  This allows easy implementation of
 *     a "cancel" operation, which normally involves just throwing away
 *     the {@link DialogController} instance.</li>
 * <li>Although such access will be uncommon, {@link DialogController}
 *     instances will typically be visible to multiple processing threads,
 *     so appropriate synchronizations should be performed when saving
 *     and restoring the state information stored in an instance.</li>
 * </ul>
 *
 * <p><strong>WARNING</strong> - this is a very early prototype of what a
 * {@link DialogController} might look like.  The final design might assign
 * responsibilities in a different manner (for example, performing some of
 * the updates to the persistent data inside what are currently defined as
 * methods to provide navigation control).</p>
 *
 * $Id$
 */

public interface DialogController extends Serializable {
    

    // -------------------------------------------------------------- Properties

    
    /**
     * <p>Return a <code>Map</code> in which event handlers executing on behalf
     * of a dialog may store general purpose information, which will be
     * maintained by Struts across HTTP requests.  To operate robustly in
     * containers that support session migration or saving/restoring on
     * server (or application) restart, all keys and values stored in this
     * <code>Map</code> must be Serializable.</p>
     */
    public Map getAttributes();
    

    // ------------------------------------------------------ Navigation Methods


    /**
     * <p>Return a logical outcome that will navigate to a view informing
     * the user that this dialog has been terminated abnormally.  The caller
     * should ensure that no persistent state change be performed as a
     * result of the partial completion of this dialog.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String cancel();
    
    
    /**
     * <p>Return a logical outcome that will navigate to some view external
     * to the current dialog, or <code>null</code> if there is no need to
     * perform such navigation (perhaps because a pop-up window containing
     * the dialog will be closing itself.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String exit();
    
    
    /**
     * <p>Return a logical outcome that will navigate to a view confirming
     * to the user that any persistent state changes required to reflect
     * the execution of this dialog have been completed.  The caller should
     * ensure that all such changes have been performed before navigation
     * to this confirmation view is actually performed, because the user is
     * likely to cancel this view (if it appears in a popup window) using
     * browser based controls instead of any application provided controls.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String finish();
    
    
    /**
     * <p>Return a logical outcome that will navigate to the first view
     * belonging to this dialog, or <code>null</code> if control should
     * remain on the current view.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String first();


    /**
     * <p>Return a logical outcome that will navigate to the last view
     * belonging to this dialog, or <code>null</code> if control should
     * remain on the current view.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String last();


    /**
     * <p>Return a logical outcome that will navigate to the next view
     * belonging to this dialog, or <code>null</code> if control should
     * remain on the current view.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String next();


    /**
     * <p>Return a logical outcome that will navigate to the previous view
     * belonging to this dialog, or <code>null</code> if control should
     * remain on the current view.</p>
     *
     * @exception UnsupportedOperationException if not supported for
     *  this dialog
     */
    public String previous();


}
