/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionMappingBase.java,v 1.6 2000/06/30 01:19:32 craigmcc Exp $
 * $Revision: 1.6 $
 * $Date: 2000/06/30 01:19:32 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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


package org.apache.struts.action;


import java.util.Hashtable;


/**
 * A minimal implementation of <strong>ActionMapping</strong> that contains
 * only the required properties.  Additional properties can be provided by
 * subclassing this class and adding new "getter" and "setter" methods.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.6 $ $Date: 2000/06/30 01:19:32 $
 */

public class ActionMappingBase implements ActionMapping {


    // ----------------------------------------------------- Instance Variables


    /**
     * The fully qualified Java class name of the <code>Action</code>
     * implementation class for this mapping.
     */
    protected String actionClass = null;


    /**
     * The initialized instance of the Action class for this mapping.
     */
    protected Action actionInstance = null;


    /**
     * The session attribute key under which an instance of our form class
     * is stored, if any.
     */
    protected String formAttribute = null;


    /**
     * The fully qualified Java class name of the <code>ActionForm</code>
     * JavaBean optionally associated with this mapping.
     */
    protected String formClass = null;


    /**
     * An instance of the form class itself.
     */
    protected Class formClassInstance = null;


    /**
     * The prefix for HTTP request parameter names to use when matching
     * against form instance properties (if any).
     */
    protected String formPrefix = null;


    /**
     * The suffix for HTTP request parameter names to use when matching
     * against form instance properties (if any).
     */
    protected String formSuffix = null;


    /**
     * The set of <code>ActionForward</code> instances associated with
     * this <code>ActionMapping</code>.
     */
    protected ActionForwards forwards = new ActionForwards();


    /**
     * The global ActionForwards collection associated with this mapping.
     */
    protected ActionForwards globals = null;


    /**
     * The input form URI for this mapping.
     */
    protected String inputForm = null;


    /**
     * The request URI path used to select this particular mapping.
     */
    protected String path = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the action class name for this mapping.
     */
    public String getActionClass() {

	return (this.actionClass);

    }


    /**
     * Set the action class name for this mapping.
     *
     * @param actionClass The new action class name
     */
    public void setActionClass(String actionClass) {

	this.actionClass = actionClass;
	this.actionInstance = null;

    }


    /**
     * Return the form session attribute key for this mapping.
     */
    public String getFormAttribute() {

	return (this.formAttribute);

    }


    /**
     * Set the form session attribute key for this mapping.
     *
     * @param formAttribute The new form session attribute key
     */
    public void setFormAttribute(String formAttribute) {

	this.formAttribute = formAttribute;

    }


    /**
     * Return the form class name for this mapping.
     */
    public String getFormClass() {

	return (this.formClass);

    }


    /**
     * Set the form class name for this mapping.
     *
     * @param formClass The new form class name
     */
    public void setFormClass(String formClass) {

	this.formClass = formClass;

    }


    /**
     * Return the form prefix for this mapping.
     */
    public String getFormPrefix() {

	return (this.formPrefix);

    }


    /**
     * Set the form prefix for this mapping.
     *
     * @param formPrefix The new form prefix
     */
    public void setFormPrefix(String formPrefix) {

	this.formPrefix = formPrefix;

    }


    /**
     * Return the form suffix for this mapping.
     */
    public String getFormSuffix() {

	return (this.formSuffix);

    }


    /**
     * Set the form suffix for this mapping.
     *
     * @param formSuffix The new form suffix
     */
    public void setFormSuffix(String formSuffix) {

	this.formSuffix = formSuffix;

    }


    /**
     * Return the global forwards collection associated with this mapping.
     */
    public ActionForwards getForwards() {

	return (this.globals);

    }


    /**
     * Set the global forwards collection associated with this mapping.
     *
     * @param forwards The associated forwards collection
     */
    public void setForwards(ActionForwards forwards) {

	this.globals = forwards;

    }


    /**
     * Return the input form URI for this mapping.
     */
    public String getInputForm() {

	return (this.inputForm);

    }


    /**
     * Set the input form URI for this mapping.
     *
     * @param inputForm The new input form URI
     */
    public void setInputForm(String inputForm) {

	this.inputForm = inputForm;

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
     * Return an initialized instance of our Action class for this mapping.
     */
    public Action createActionInstance() {

	// Return the already instantiated instance (if any)
	if (actionInstance != null)
	    return (actionInstance);

	// Instantiate and return a new instance of the action class
	try {
	    Class actionClassInstance = Class.forName(actionClass);
	    actionInstance = (Action) actionClassInstance.newInstance();
	} catch (Throwable throwable) {
	    actionInstance = null;
	}
	return (actionInstance);

    }


    /**
     * Create and return an initialized instance of our form class.
     */
    public ActionForm createFormInstance() {

	ActionForm formInstance = null;
	try {
	    if (formClassInstance == null)
		formClassInstance = Class.forName(formClass);
	    formInstance = (ActionForm) formClassInstance.newInstance();
	} catch (Throwable throwable) {
	    System.out.println("createFormInstance:  " + throwable);
	    throwable.printStackTrace(System.out);
	    formInstance = null;
	}
	return (formInstance);

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

	ActionForward forward = forwards.findForward(name);
	if ((forward == null) && (globals != null))
	    forward = globals.findForward(name);
	return (forward);

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

	return ("ActionMapping[" + path + "]");

    }


    // ------------------------------------------------------ Protected Methods


}
