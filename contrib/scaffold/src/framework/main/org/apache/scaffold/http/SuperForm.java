package org.apache.scaffold.http;


import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * Standard base ActionForm with enhanced functionality.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2002/01/24 15:24:50 $
 */
// public class ScaffoldForm extends ValidatorForm {
public class SuperForm extends ActionForm {

// --------------------------------------------------- Instance Variables


// ----------------------------------------------------------- Properties


    /**
     * The locale property.
     */
    private Locale locale = null;


    /**
     * Set the locale.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    /**
     * Retrieve the mutable.
     */
    public Locale getLocal() {
        return this.locale;
    }



    /**
     * The map for field properties.
     * Can be used since 1.1 as an alternative
     * to individual properties for each field.
     */
    private Map properties = null;


    /**
     * Set the properties map.
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }


    /**
     * Get the properties map.
     */
    public Map getProperties() {
        return this.properties;
    }


    /**
     * Reset the locale to the current session.
     */
    protected void resetLocale(HttpServletRequest request) {

        this.locale = null;

        HttpSession session = request.getSession();
        if (session!=null) {
            this.locale = (Locale)
                session.getAttribute(Action.LOCALE_KEY);
        }

        if (this.locale==null) {
            this.locale= Locale.getDefault();
        }
    }


    /**
     * The mutable property for properites
     * subject to autopopulation.
     */
    private boolean mutable = true;


    /**
     * Set the mutable.
     */
    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }


    /**
     * Retrieve the mutable.
     */
    public boolean isMutable() {
        return this.mutable;
    }


    /**
     * The action task property.
     */
    public String actionTask = null;


    /**
     * Set the action task.
     */
    public void setActionTask(String actionTask) {
        if (isMutable()) this.actionTask = actionTask;
    }


    /**
     * Get the action task.
     */
    public String getActionTask() {
        return this.actionTask;
    }


// --------------------------------------------------------- Public Methods


    /**
     */
    public void setProperty(String key, Object value) {
        if (isMutable()) getProperties().put(key,value);
    }


    /**
     */
    public Object getProperty(String key) {
        return getProperties().get(key);
    }



    /**
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        resetLocale(request);

        // if (isMutable()) ...

    }



// ----- end SuperForm -----

}


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
