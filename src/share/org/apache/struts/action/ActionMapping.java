/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionMapping.java,v 1.19 2001/10/04 03:02:27 craigmcc Exp $
 * $Revision: 1.19 $
 * $Date: 2001/10/04 03:02:27 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
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


package org.apache.struts.action;


import java.io.Serializable;


/**
 * An <strong>ActionMapping</strong> represents the information that the
 * controller servlet, <code>ActionServlet</code>, knows about the mapping
 * of a particular request to an instance of a particular action class.
 * The mapping is passed to the <code>perform()</code> method of the action
 * class itself, enabling access to this information directly.
 * <p>
 * An <code>ActionMapping</code> has the following minimal set of properties.
 * Additional properties can be added by a subclass, simply by
 * providing appropriate public "getter" and "setter" methods.
 * <ul>
 * <li><strong>attribute</strong> - Name of the request-scope or
 *     session-scope attribute under which our form bean is accessed, if it
 *     is other than the bean's specified name.</li>
 * <li><strong>forward</strong> - Context-relative path of the resource that
 *     should serve this request (via a call to
 *     <code>RequestDispatcher.forward()</code>) instead of instantiating the
 *     Action class specified by the <code>type</code> property.
 *     Exactly one of the <code>forward</code>, <code>include</code>, or
 *     <code>type</code> properties must be specified.</li>
 * <li><strong>forwards</strong> - The set of ActionForwards locally
 *     associated with this mapping.</li>
 * <li><strong>include</strong> - Context-relative path of the resource that
 *     should serve this request (via a call to
 *     <code>RequestDispatcher.include()</code>) instead of instantiating the
 *     Action class specified by the <code>type</code> property.
 *     Exactly one of the <code>forward</code>, <code>include</code>, or
 *     <code>type</code> properties must be specified.</li>
 * <li><strong>input</strong> - Context-relative path of the input form
 *     to which control should be returned if a validation error is
 *     encountered.</li>
 * <li><strong>mappings</strong> - The <code>ActionMappings</code>
 *     collection of which we are a part.</li>
 * <li><strong>name</strong> - Name of the form bean, if any, associated
 *     with this action.</li>
 * <li><strong>parameter</strong> - General purpose configuration parameter
 *     that can be used to pass extra information to the <code>Action</code>
 *     selected by this <code>ActionMapping</code>.</li>
 * <li><strong>path</strong> - Request URI path used to select this mapping.
 *     If extension mapping is used for the controller servlet, the extension
 *     will be stripped before comparisions against this value are made.</li>
 * <li><strong>prefix</strong> - Prefix used to match request parameter
 *     names to form bean property names, if any.</li>
 * <li><strong>scope</strong> - Identifier of the scope ("request" or
 *     "session" within which the form bean, if any, associated with this
 *     action will be created.</li>
 * <li><strong>suffix</strong> - Suffix used to match request parameter
 *     names when populating the properties of our <code>ActionForm</code>
 *     bean (if any).</li>
 * <li><strong>type</strong> - Fully qualified Java class name of the
 *     <code>Action</code> implementation class used by this mapping.
 *     Replaces the old <code>actionClass</code> property.  Exactly one of
 *     the <code>forward</code>, <code>include</code>, or <code>type</code>
 *     properties must be specified.</li>
 * <li><strong>unknown</strong> - Set to <code>true</code> if this action
 *     should be configured as the default for this application, to handle
 *     all requests not handled by another action.  Only one action can be
 *     defined as a default within a single application.</li>
 * <li><strong>validate</strong> - Set to <code>true</code> if the
 *     <code>validate()</code> method of the form bean (if any) associated
 *     with this mapping should be called.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.19 $ $Date: 2001/10/04 03:02:27 $
 */

public class ActionMapping implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * The name of the request-scope or session-scope attribute under which
     * our form bean, if any, will be created.
     */
    protected String attribute = null;


    /**
     * The context relative path of the servlet or JSP resource (to be called
     * via <code>RequestDispatcher.forward()</code>) that will process this
     * request, rather than instantiating and calling the Action class that is
     * specified by the <code>type</code> attribute.
     */
    protected String forward = null;


    /**
     * The set of ActionForward objects associated with this mapping.
     */
    protected ActionForwards forwards = new ActionForwards();


    /**
     * The context relative path of the servlet or JSP resource (to be called
     * via <code>RequestDispatcher.include()</code>) that will process this
     * request, rather than instantiating and calling the Action class that is
     * specified by the <code>type</code> attribute.
     */
    protected String include = null;


    /**
     * The context-relative path of the input form to which control should
     * be returned if a validation error is encountered.
     */
    protected String input = null;


    /**
     * The initialized <code>Action</code> instance for this mapping.
     */
    protected Action instance = null;


    /**
     * The <code>ActionMappings</code> collection of which we are a part.
     */
    protected ActionMappings mappings = null;
    
    /**
     * The fully qualified class name of the <code>MultipartRequestHandler</code>
     * implementation class used to process multipart request data for this mapping
     */
    protected String multipartClass;
    
    /**
     * The name of the form bean, if any, associated with this action.
     */
    protected String name = null;


    /**
     * General purpose configuration parameter for this mapping.
     */
    protected String parameter = null;


    /**
     * The context-relative path of the submitted request, starting with a
     * "/" character, and without the filename extension (if any), that is
     * mapped to this action.
     */
    protected String path = null;


    /**
     * The parameter name prefix used to select parameters for this action.
     */
    protected String prefix = null;


    /**
     * The identifier of the scope ("request" or "session") under which the
     * form bean associated with this mapping, if any, should be created.
     */
    protected String scope = "session";


    /**
     * The parameter name suffix used to select parameters for this action.
     */
    protected String suffix = null;


    /**
     * The fully qualified Java class name of the <code>Action</code>
     * implementation class to be used to process requests for this mapping.
     */
    protected String type = null;


    /**
     * Should this action be the default for this application?
     */
    protected boolean unknown = false;


    /**
     * Should the validate() method of our form bean be called?
     */
    protected boolean validate = true;


    // ------------------------------------------------------------- Properties


    /**
     * Return the attribute name for our form bean.
     */
    public String getAttribute() {

        if (this.attribute == null)
            return (getName());
        else
            return (this.attribute);

    }


    /**
     * Set the attribute name for our form bean.
     *
     * @param attribute The new attribute name
     */
    public void setAttribute(String attribute) {

        this.attribute = attribute;

    }


    /**
     * Return the forward path for this mapping.
     */
    public String getForward() {

        return (this.forward);

    }


    /**
     * Set the forward path for this mapping.
     *
     * @param forward The forward path for this mapping
     */
    public void setForward(String forward) {

        this.forward = forward;

    }


    /**
     * Return the include path for this mapping.
     */
    public String getInclude() {

        return (this.include);

    }


    /**
     * Set the include path for this mapping.
     *
     * @param include The include path for this mapping
     */
    public void setInclude(String include) {

        this.include = include;

    }


    /**
     * Return the input form path for this mapping.
     */
    public String getInput() {

        return (this.input);

    }


    /**
     * Set the input form path for this mapping.
     *
     * @param input The new input form path
     */
    public void setInput(String input) {

        this.input = input;

    }


    /**
     * Return the <code>ActionMappings</code> collection of which
     * we are a part.
     */
    public ActionMappings getMappings() {

        return (this.mappings);

    }
    
    /**
     * Get the name of the class used to handle multipart request data
     *
     * @return A fully qualified java class name representing the implementation of
     *         MultipartRequestHandler to use.
     */
    public String getMultipartClass() {
        return multipartClass;
    }



    /**
     * Set the <code>ActionMappings</code> collection of which
     * we are a part.
     *
     * @param mappings The new ActionMappings collection
     */
    public void setMappings(ActionMappings mappings) {

        this.mappings = mappings;

    }
    
    /** 
     * Set the name of the class used to handle multipart request data
     *
     * @param multipartClass The fully qualified class name representing the
     *        MultipartRequestHandler class to use.  If <code>null</code>, 
     *        the global class specified in "web.xml" will be used.
     */
    public void setMultipartClass(String multipartClass) {
        this.multipartClass = multipartClass;
    }



    /**
     * Return the name of the form bean for this mapping.
     */
    public String getName() {

        return (this.name);

    }


    /**
     * Set the name of the form bean for this mapping.
     *
     * @param name The new name
     */
    public void setName(String name) {

        this.name = name;

    }


    /**
     * Return the general purpose configuation parameter for this mapping.
     */
    public String getParameter() {

        return (this.parameter);

    }


    /**
     * Set the general purpose configuration parameter for this mapping.
     *
     * @param parameter The new configuration parameter
     */
    public void setParameter(String parameter) {

        this.parameter = parameter;

    }


    /**
     * Return the request URI path used to select this mapping.
     */
    public String getPath() {

        return (this.path);

    }


    /**
     * Set the request URI path used to select this mapping.
     *
     * @param path The new request URI path
     */
    public void setPath(String path) {

        this.path = path;

    }


    /**
     * Return the parameter name prefix for this mapping.
     */
    public String getPrefix() {

        return (this.prefix);

    }


    /**
     * Set the parameter name prefix for this mapping.
     *
     * @param prefix The new parameter name prefix
     */
    public void setPrefix(String prefix) {

        this.prefix = prefix;

    }


    /**
     * Return the attribute scope for this mapping.
     */
    public String getScope() {

        return (this.scope);

    }


    /**
     * Set the attribute scope for this mapping.
     *
     * @param scope The new attribute scope
     */
    public void setScope(String scope) {

        this.scope = scope;

    }


    /**
     * Return the parameter name suffix for this mapping.
     */
    public String getSuffix() {

        return (this.suffix);

    }


    /**
     * Set the parameter name suffix for this mapping.
     *
     * @param suffix The new parameter name suffix
     */
    public void setSuffix(String suffix) {

        this.suffix = suffix;

    }


    /**
     * Return the fully qualified Action class name.
     */
    public String getType() {

        return (this.type);

    }


    /**
     * Set the fully qualified Action class name.
     *
     * @param type The new class name
     */
    public void setType(String type) {

        this.type = type;

    }


    /**
     * Return the unknown flag for this mapping.
     */
    public boolean getUnknown() {

        return (this.unknown);

    }


    /**
     * Set the unknown flag for this mapping.
     *
     * @param unknown The new unknown flag
     */
    public void setUnknown(boolean unknown) {

        this.unknown = unknown;

    }


    /**
     * Return the validate flag for this mapping.
     */
    public boolean getValidate() {

        return (this.validate);

    }


    /**
     * Set the validate flag for this mapping.
     *
     * @param validate The new validate flag
     */
    public void setValidate(boolean validate) {

        this.validate = validate;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new <code>ActionForward</code> associated with this mapping.
     *
     * @param forward The ActionForward to be added
     */
    public void addForward(ActionForward forward) {

        forwards.addForward(forward);

    }


    /**
     * Return the <code>ActionForward</code> with the specified name,
     * if any; otherwise return <code>null</code>.  If there is no locally
     * defined forwarding for the specified name, but a global forwards
     * collection has been associated with this mapping, the global
     * collection will also be searched before returning.
     *
     * @param name Name of the forward entry to be returned
     */
    public ActionForward findForward(String name) {

        // First, check our locally defined forwards
        ActionForward forward = forwards.findForward(name);
        if (forward != null)
            return (forward);

        // Second, check the globally defined forwards
        ActionMappings mappings = getMappings();
        if (mappings == null)
            return (null);
        ActionServlet servlet = mappings.getServlet();
        if (servlet == null)
            return (null);
        else
            return (servlet.findForward(name));

    }


    /**
     * Return the logical names of all locally defined forwards for this
     * mapping.  If there are no such forwards, a zero-length array
     * is returned.
     */
    public String[] findForwards() {

        return (forwards.findForwards());

    }


    /**
     * Remove a <code>ActionForward</code> associated with this mapping.
     *
     * @param forward The ActionForward to be removed
     */
    public void removeForward(ActionForward forward) {

        forwards.removeForward(forward);

    }


    /**
     * Return a String version of this mapping.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("ActionMapping[path=");
        sb.append(path);
        if (include != null) {
            sb.append(", include=");
            sb.append(include);
        }
        if (type != null) {
            sb.append(", type=");
            sb.append(type);
        }
        sb.append("]");
        return (sb.toString());

    }


}
