/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/BaseMapForm.java,v 1.5 2004/03/14 14:32:19 husted Exp $
 * $Revision: 1.5 $
 * $Date: 2004/03/14 14:32:19 $
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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
package org.apache.struts.scaffold;


import java.util.Map;


/**
 * Enhanced base ActionForm for Struts 1.1 and later.
 *
 * @version $Revision: 1.5 $ $Date: 2004/03/14 14:32:19 $
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
     * @return The map being used for our properties
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
     * @exception Exception Passes through any Exception thrown by underlying
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
     * @exception Exception Passes through any Exception thrown by underlying
     * hashmap.
     */
    public Object getValue(String key) throws Exception {
        return getMap().get(key);
    }

// end BaseMapForm

}