/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/action/ActionFormBean.java,v 1.3 2001/02/21 00:35:43 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/02/21 00:35:43 $
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
 * An <strong>ActionFormBean</strong> is the definition of a form bean that
 * is loaded from a <code>&lt;form-bean&gt;</code> element in the Struts
 * configuration file.  It can be subclassed as necessary to add additional
 * properties.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/02/21 00:35:43 $
 */

public class ActionFormBean implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * The bean name of this action form bean.
     */
    private String name = null;


    /**
     * The Java class name of this action form bean.
     */
    private String type = null;


    // ------------------------------------------------------------- Properties


    /**
     * Return the bean name of this action form bean.
     */
    public String getName() {

	return (this.name);

    }


    /**
     * Set the bean name of this action form bean.
     *
     * @param name The new bean name
     */
    public void setName(String name) {

	this.name = name;

    }


    /**
     * Return the Java class name of this action form bean.
     */
    public String getType() {

	return (this.type);

    }


    /**
     * Set the Java class name of this action form bean.
     *
     * @param type The new Java class name
     */
    public void setType(String type) {

	this.type = type;

    }


    // --------------------------------------------------------- Public Methods


    /**
     * Return a string representation of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("ActionFormBean[");
        sb.append(this.name);
        sb.append(']');
        return (sb.toString());

    }


}
