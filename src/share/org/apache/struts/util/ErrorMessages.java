/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/ErrorMessages.java,v 1.1 2000/06/20 16:34:10 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/06/20 16:34:10 $
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


package org.apache.struts.util;


import java.util.Vector;


/**
 * Utility class that is useful for accumulating error message keys in
 * action classes or validation methods.  Use this class as follows:
 * <ul>
 * <li>At the beginning of your action class or validation method,
 *     instantiate an instance of this class in a local variable.</li>
 * <li>Whenever you wish to add a new error message key, call the
 *     <code>addError()</code> method to add it.</li>
 * <li>To return the String array of error message keys required by
 *     other Struts components, call <code>getErrors()</code>.
 * </ul>
 *
 * @author David Geary
 * @revision $Revision: 1.1 $ $Date: 2000/06/20 16:34:10 $
 */

public final class ErrorMessages {


    // ----------------------------------------------------- Instance Variables


    /**
     * The accumulated set of error message keys.
     */
    private Vector errors = new Vector();


    // --------------------------------------------------------- Public Methods


    /**
     * Add an error message key to the accumulated set of errors.
     *
     * @param key The error message key to be added
     */
    public void addError(String key) {

	errors.addElement(key);

    }


    /**
     * Return the set of error message keys we have accumulated.
     */
    public String[] getErrors() {

	if (errors.size() > 0) {
	    String array[] = new String[errors.size()];
	    for (int i = 0; i < array.length; i++)
		array[i] = (String) errors.elementAt(i);
	    return (array);
	}
	return (null);

    }


    /**
     * Return the number of error messages we have accumulated so far.
     */
    public int getSize() {

	return (errors.size());

    }


}
