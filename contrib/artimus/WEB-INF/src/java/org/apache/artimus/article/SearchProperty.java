/*
 * $Id$ 
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
 

package org.apache.artimus.article;


import java.util.Collection;

import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelParameterException;
import org.apache.scaffold.model.ModelResult;
import org.apache.scaffold.model.ModelResultBase;


/**
 * Search for article author.
 * @version $Rev$ $Date$
 */
public class SearchProperty extends Bean {


    /**
     * Property being searched.
     */
    protected String property = null;


    /**
     * Return the property being searched.
     * @return the property
     */
    public String getProperty() {
        return this.property;
    }


    /**
     * Set the property being searched.
     */
    public void setProperty(String property) {
        this.property = property;
    }


    // ------------------------------------------------------------ Public Methods

    /**
     * Call Access.searchProperty with key from this bean and
     * this class's property. A subclass can set the property
     * in a default constructor.
     * @exception Throws ModelParameterException if result is null.
     * @returns ModelResultBase with a collection of matching beans
     * (based on target); collection may have zero elements.
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {

        String key = getKey();
        String property = getProperty();

        if ( (isBlank(key)) || (isBlank(property)) ) {
            throw new ModelParameterException();
        }

        Collection result = Access.searchProperty(target,key,property);

        ModelResult modelResult = new ModelResultBase(result);
            modelResult.setDescription(key,property);

        return modelResult;
   }

}