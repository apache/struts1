/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/Attic/ActionMappings.java,v 1.5 2000/12/30 00:39:05 craigmcc Exp $
 * $Revision: 1.5 $
 * $Date: 2000/12/30 00:39:05 $
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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.util.FastHashMap;


/**
 * Encapsulate a collection of ActionMapping objects that can be
 * administered and searched, while hiding the internal implementation.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2000/12/30 00:39:05 $
 */

public class ActionMappings implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * The collection of ActionMapping instances, keyed by request path.
     */
    protected FastHashMap mappings = new FastHashMap();


    /**
     * The ActionServlet instance of our owning application.
     */
    transient protected ActionServlet servlet = null;


    /**
     * The ActionMapping that should handle unknown request paths, if any.
     */
    protected ActionMapping unknown = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the "fast" mode flag.
     */
    public boolean getFast() {

        return (mappings.getFast());

    }


    /**
     * Set the "fast" mode flag.
     *
     * @param fast The new fast mode flag
     */
    public void setFast(boolean fast) {

        mappings.setFast(fast);

    }


    /**
     * Return the Action that should handle unknown request paths, if any.
     * The default implementation casts the request to HttpServletRequest
     * and calls the corresponding version of this method.
     *
     * @param request The servlet request we are processing
     */
    public ActionMapping getUnknown(ServletRequest request) {

        return (getUnknown((HttpServletRequest) request));

    }


    /**
     * Return the Action that should handle unknown request paths, if any.
     *
     * @param request The servlet request we are processing
     */
    public ActionMapping getUnknown(HttpServletRequest request) {

        if (unknown != null)
            return (unknown);

        String paths[] = findMappings();
        for (int i = 0; i < paths.length; i++) {
            ActionMapping mapping = findMapping(paths[i]);
            if (mapping.getUnknown()) {
                unknown = mapping;
                return (mapping);
            }
        }


        return (null);

    }


    /**
     * Return the <code>ActionServlet</code> instance of our owning
     * application.
     */
    public ActionServlet getServlet() {

        return (this.servlet);

    }


    /**
     * Set the <code>ActionServlet</code> instance of our owning application.
     *
     * @param servlet The new servlet instance
     */
    public void setServlet(ActionServlet servlet) {

        this.servlet = servlet;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Register a logical mapping to the set configured for this servlet.
     *
     * @param mapping The mapping to be added
     */
    public void addMapping(ActionMapping mapping) {

	mappings.put(mapping.getPath(), mapping);
        mapping.setMappings(this);

    }


    /**
     * Return the mapping associated with the specified logical name,
     * if any; otherwise return <code>null</code>.
     *
     * @param path The request path for which to retrieve a mapping
     */
    public ActionMapping findMapping(String path) {

	return ((ActionMapping) mappings.get(path));

    }


    /**
     * Return the set of paths for mappings defined in this collection.
     * If there are no such mappings, a zero-length array is returned.
     */
    public String[] findMappings() {

        return
           ((String[]) mappings.keySet().toArray(new String[mappings.size()]));

    }


    /**
     * Deregister a mapping from the set configured for this servlet.
     *
     * @param mapping The mapping to be deregistered
     */
    public void removeMapping(ActionMapping mapping) {

	mappings.remove(mapping.getPath());
        mapping.setMappings(null);

    }


}
