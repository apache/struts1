/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionMapping.java,v 1.5 2000/06/30 01:19:32 craigmcc Exp $
 * $Revision: 1.5 $
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


/**
 * An <strong>ActionMapping</strong> represents the information that the
 * controller servlet, <code>ActionServlet</code>, knows about the mapping
 * of a particular request to an instance of a particular action class.
 * The mapping is passed to the <code>perform()</code> method of the action
 * class itself, enabling access to this information directly.
 * <p>
 * An <code>ActionMapping</code> has the following minimal set of properties.
 * Additional properties can be added by an implementation, simply by
 * providing appropriate public "getter" and "setter" methods.
 * <ul>
 * <li><strong>actionClass</strong> - Fully qualified Java class name of the
 *     <code>Action</code> implementation class used by this mapping.  This
 *     property is required.
 * <li><strong>formAttribute</strong> - Name of the session attribute under
 *     which an <code>ActionForm</code> bean is created and/or updated for
 *     this mapping.  If not present, no <code>ActionForm</code> bean will
 *     be maintained automatically.
 * <li><strong>formClass</strong> - Fully qualified Java class name of the
 *     <code>ActionForm</code> implementation class used by this mapping
 *     (if any).
 * <li><strong>formPrefix</strong> - Prefix used to match request parameter
 *     names when populating the properties of our <code>ActionForm</code>
 *     bean (if any).
 * <li><strong>formSuffix</strong> - Suffix used to match request parameter
 *     names when populating the properties of our <code>ActionForm</code>
 *     bean (if any).
 * <li><strong>path</strong> - Request URI path used to select this mapping.
 *     If extension mapping is used for the controller servlet, the extension
 *     will be stripped before comparisions against this value are made.
 *     This parameter is required.
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2000/06/30 01:19:32 $
 */

public interface ActionMapping {


    // ------------------------------------------------------------- Properties


    /**
     * Return the action class name for this mapping.
     */
    public String getActionClass();


    /**
     * Set the action class name for this mapping.
     *
     * @param actionClass The new action class name
     */
    public void setActionClass(String actionClass);


    /**
     * Return the form session attribute key for this mapping, if any.
     */
    public String getFormAttribute();


    /**
     * Set the form session attribute key for this mapping.
     *
     * @param formAttribute The new form session attribute key
     */
    public void setFormAttribute(String formAttribute);


    /**
     * Return the form class name for this mapping.
     */
    public String getFormClass();


    /**
     * Set the form class name for this mapping.
     *
     * @param formClass The new form class name
     */
    public void setFormClass(String formClass);


    /**
     * Return the form parameter name prefix for this mapping.
     */
    public String getFormPrefix();


    /**
     * Set the form parameter name prefix for this mapping.
     *
     * @param formPrefix The new form prefix
     */
    public void setFormPrefix(String formPrefix);


    /**
     * Return the form parameter name suffix for this mapping.
     */
    public String getFormSuffix();


    /**
     * Set the form parameter name suffix for this mapping.
     *
     * @param formSuffix The new form suffix
     */
    public void setFormSuffix(String formSuffix);


    /**
     * Return the global forwards collection associated with this mapping.
     */
    public ActionForwards getForwards();


    /**
     * Set the global forwards collection associated with this mapping.
     *
     * @param forwards The associated forwards collection
     */
    public void setForwards(ActionForwards forwards);


    /**
     * Return the input form URI for this mapping.
     */
    public String getInputForm();


    /**
     * Set the input form URI for this mapping.
     *
     * @param inputForm The new input form URI
     */
    public void setInputForm(String inputForm);


    /**
     * Return the request URI path used to select this mapping.
     */
    public String getPath();


    /**
     * Set the request URI path used to select this mapping.
     *
     * @param path The new request URI path
     */
    public void setPath(String path);


    // --------------------------------------------------------- Public Methods


    /**
     * Add a new <code>ActionForward</code> associated with this mapping.
     *
     * @param forward The ActionForward to be added
     */
    public void addForward(ActionForward forward);


    /**
     * Return an initialized instance of our Action class for this mapping.
     * If instantiation fails for any reason, <code>null</code> is returned.
     */
    public Action createActionInstance();


    /**
     * Create and return an initialized instance of our form class.  If
     * instantiation fails for any reason, <code>null</code> is returned.
     */
    public ActionForm createFormInstance();


    /**
     * Return the <code>ActionForward</code> with the specified name,
     * if any; otherwise return <code>null</code>.  If there is no locally
     * defined forwarding for the specified name, but a global forwards
     * collection has been associated with this mapping, the global
     * collection will also be searched before returning.
     *
     * @param name Name of the forward entry to be returned
     */
    public ActionForward findForward(String name);


    /**
     * Return the logical names of all locally defined forwards for this
     * mapping.  If there are no such forwards, a zero-length array
     * is returned.
     */
    public String[] findForwards();


    /**
     * Remove a <code>ActionForward</code> associated with this mapping.
     *
     * @param forward The ActionForward to be removed
     */
    public void removeForward(ActionForward forward);


}
