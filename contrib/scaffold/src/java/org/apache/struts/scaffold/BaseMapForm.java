package org.apache.struts.scaffold;


import java.util.Map;


/**
 * Enhanced base ActionForm for Struts 1.1 and later.
 *
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2002/08/14 18:30:08 $
 */
public class BaseMapForm extends BaseForm {

// ----------------------------------------------------------- Properties

    /**
     * The map for field properties [null].
     * Can be used since 1.1 as an alternative
     * to individual properties for each field.
     */
    private Map map = null;


    /**
     * Set the properties map.
     *
     * @param map The map to use for our properties
     */
    public void setMap(Map map) throws Exception {
        this.map = map;
    }


    /**
     * Get the properties map.
     *
     * @returns The map being used for our properties
     */
    public Map getMap() throws Exception {
        return this.map;
    }


    /**
     * Associates the specified value with the specified key in
     * the property map for this object
     * -- the equivalent of <code>getMap().put(key,value).
     * Observes the mutable property and only set new value when
     * <code>isMutable()</code> is true.
     * <p>
     * In a Struts 1.1 application, this will set an element to the
     * map. See also <code>getValue()</code>.
     * <p>
     * @exception Passes through any Exception thrown by underlying
     * hashmap.
     * @param key - key with which the specified value is to be associated.
     * @param value - value to be associated with the specified key.*/
    public void setValue(String key, Object value) throws Exception {
        if (isMutable()) getMap().put(key,value);
    }


    /**
     * Returns the value to which this map maps the specified key
     * -- the equivalent of <code>Object getMap().get(key)</code>.
     * <p>
     * In a Struts 1.1 application, this can be used to retrieve a
     * property from the Map, e.g., property="value(username)"
     * <p>
     * @param key - key whose associated value is to be returned.
     * @exception Passes through any Exception thrown by underlying
     * hashmap.
     */
    public Object getValue(String key) throws Exception {
        return getMap().get(key);
    }

// end BaseMapForm

}


/*
 *
 *    Copyright (c) 2002 Synthis Corporation.
 *    430 10th Street NW, Suite S-108, Atlanta GA 30318, U.S.A.
 *    All rights reserved.
 *
 *    This software is licensed to you free of charge under
 *    the Apache Software License, so long as this copyright
 *    statement, list of conditions, and comments,  remains
 *    in the source code.  See bottom of file for more
 *    license information.
 *
 *    This software was written to support code generation
 *    for the Apache Struts J2EE architecture by Synthis'
 *    visual application modeling tool Adalon.
 *
 *    For more information on Adalon and Struts code
 *    generation please visit http://www.synthis.com
 *
 */


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
  * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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

