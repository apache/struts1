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

/**
 * <p>{@link ViewController} is a "backing bean" interface which adds several
 * extension points to the standard JavaServer Faces lifecycle. The extension
 * points help Struts interact with JSF <code>UIComponents</code>.
 * </p>
  * <p>
 * A "backing bean" represents a convenient place to retrieve and store
 * dynamic values associated with the user interface components that comprise
 * the view, as well as to code event handlers triggered by state changes on
 * those components. A JavaServer Faces <em>view</em> is most often a JSP page,
 * but any JSF view rendering system can be used.
 * </p>
 * <p>
 * Essentially, the ViewController is a
 * <a href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/ViewHelper.html">
 * View Helper</a> interface for a backing bean and its associated View.
 * </p>
 * <p><strong>NOTE</strong> - JavaServer Faces imposes no restrictions on
 * the inheritance hierarchy (or interface implementation) for backing beans
 * associated with a view.  Therefore, the use of this interface for your own
 * backing beans is entirely optional.  That being said, having your
 * backing beans implement this interface (typically by extending
 * {@link org.apache.shale.view.AbstractViewController}) will receive the
 * benefit of the extra services described by this interface, all of which will be
 * provided automatically.</p>
 * <p>
 * To be useful, the ViewController must be plugged into the application
 * lifecycle through a custom JSF ViewHandler, like the
 * {@link org.apache.shale.faces.ShaleViewHandler ShaleViewHandler}.
 * </p>
 *
 * <h3>Registering a ViewController backing bean</h3>
 *
 * <p>For each JSF view that you wish to associate with a ViewController
 * backing bean, you must do the following:</p>
 * <ul>
 * <li>First, as required by the JavaBeans specification, be sure to provide a public no-args
 *     constructor in your ViewController backing bean implementation class.
 *     Configuration of ViewController beans is performed using setter injection,
 *     rather than through "rich" constructors.</li>
 * <li>Register this class as a JSF <code>&lt;managed-bean&gt;</code>,
 *     using a <code>&lt;managed-bean-name&gt;</code> that corresponds to
 *     the <code>view identifier</code> of the JSF view.  Mapping between
 *     <code>view id</code> and managed bean names will be performed by
 *     a {@link ViewControllerMapper} configured for this application.
 *     <ul>
 *     <li>Under nearly all circumstances, you will want to register this
 *         managed bean to be stored in request scope, so that its lifetime
 *         matches the corresponding view.</li>
 *     <li>Optionally, you may use <code>&lt;managed-property&gt;</code>
 *         elements within the <code>&lt;managed-bean&gt;</code> element
 *         to configure the behavior of your ViewController bean.</li>
 *     </ul></li>
 * </ul>
 *
 * <p>Since the ViewController is a backing bean, you have the option of
 * establishing other links with the UIComponents, such as:</p>
 *
 * <ul>
 * <li>You may use the <code>binding</code> property of any JSF
 *     <code>UIComponent</code> to establish a linkage between a component
 *     instance in the component tree representing this view, and a propery
 *     (of type <code>UIComponent</code> or an appropriate subclass) in your
 *     backing bean.  This technique is useful if you need to programmatically
 *     manipulate properties of the corresponding component in an event
 *     handler in the backing bean.</li>
 * <li>You may use the <code>value</code> property of any JSF
 *     component that implements <code>ValueHolder</code> (for example, any
 *     component based on <code>UIInput</code> or <code>UIOutput</code>) to
 *     establish a linkage between the dynamic value to be rendered or stored,
 *     and a property (of some appropriate type relevant to the model tier
 *     of your application) in your backing bean.  This technique is convenient
 *     if you are primarily interested in manipulating the <em>values</em> to
 *     be rendered or stored, and/or you wish to leverage JSF's support for
 *     implicit registration of a <code>Converter</code>.</li>
 * </ul>
 *
 * <h3>ViewController Lifecycle</h3>
 *
 * <p>Once you have configured the use of a {@link ViewController} backing bean
 * associated with a JSF view, Struts will provide the following services:</p>
 * <ul>
 * <li>Whenever a JSF view with the appropriate <code>view identifier</code>
 *     is created or restored, an appropriate instance of the corresponding
 *     {@link ViewController} class will be created via the managed beans
 *     facility (if such a bean does not already exist), using a key derived
 *     from the {@link ViewControllerMapper} for this application.  As a
 *     side effect, property setters wil be called for any
 *     <code>&lt;managed-property&gt;</code> configuration you have
 *     specified.</li>
 * <li>Prior to any other method calls, the following additional property
 *     setters will be called:
 *     <ul>
 *     <li><code>setPostBack()</code> will be called with a flag indicating
 *         whether this backing bean was created as result of a "post back"
 *         (i.e. to handle an HTTP request submitted by the client) or as a
 *         result of navigating to a different page.</li>
 *     </ul></li>
 * <li>The <code>init()</code> method will be called, allowing the backing bean
 *     to acquire data from the model tier as needed to prepare for execution
 *     of the JSF request processing lifecycle for this view.</li>
 * <li>Standard JSF processing and event handling is performed.  For a restored
 *     view, the entire lifecycle is executed.  For a newly created view, only
 *     the <em>Render Response</em> phase is executed.</li>
 * <li>Immediately prior to the <em>Render Response</em> phase, the
 *     <code>prepare()</code> method will be called, but only on the
 *     {@link ViewController} for whose view JSF will actually perform the
 *     rendering.  If your {@link ViewController} performed navigation to
 *     a different view (either directly via the <code>NavigationHandler</code>
 *     or indirectly by virtue of a non-null return from an action method),
 *     this method will <strong>not</strong> be called.</p>
 * <li>The <code>destroy()</code> method will be called, allowing the backing
 *     bean to clean up any resources that it has allocated before processing
 *     for this HTTP request is completed.</li>
 * </ul>
 *
 * $Id$
 */

public interface ViewController {
    

    // -------------------------------------------------------------- Properties


    /**
     * <p>Return the {@link DialogController} instance for the dialog that this
     * view is participating in, if any; otherwise, return <code>null</code>.
     * </p>
     */
    public DialogController getDialog();


    /**
     * <p>Set the {@link DialogController} instance for the dialog that this
     * view is participating in, if any.</p>
     *
     * @param dialog The appropriate {@link DialogController}, or
     *  <code>null</code> if we are not participating in a dialg
     */
    public void setDialog(DialogController dialog);
    
    
    /**
     * <p>Return a flag indicating whether this request is a "post back" (that
     * is, the view was restored in order to respond to a submit from the
     * client), or a newly created view.  This method must return any value
     * passed to the <code>setPostBack()</code> method.</p>
     */
    public boolean isPostBack();
    
    
    /**
     * <p>Set a flag indicating whether this request is a "post back" (that is,
     * the view was restored in order to respond to a submit from the client),
     * or a newly created view.</p>
     *
     * @param postBack <code>true</code> for a post back request, or
     *  <code>false</code> for a newly created request
     */
    public void setPostBack(boolean postBack);


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Called after the JSF request processing lifecycle has been completed
     * for the current request.  This allows a {@link ViewController} to clean
     * up any resources it has allocated (perhaps during earlier execution of
     * the <code>init()</code> method).</p>
     */
    public void destroy();


    /**
     * <p>Called after this {@link ViewController} has been instantiated, and
     * after all of the property setters specified above have been called, but
     * before the JSF request processing lifecycle processing and events related
     * to our corresponding view are executed.  Within this method, you may
     * consult the <code>isPostBack()</code> method to vary the initialization
     * behavior based on whether a post back is being processed or not.</p>
     */
    public void init();


    /**
     * <p>Called before the <em>Render Response</em> processing for this request
     * is performed, whether or not this is a post back request.  This method
     * will be called only for the view that will actually be rendered.  For
     * example, it will not be called if you have performed navigation to a
     * different view.  As such, it makes a good place to acquire information
     * from your model tier that is required to complete this view's
     * presentation.</p>
     */
    public void prepare();


}
