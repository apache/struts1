/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/article/SearchKey.java,v 1.3 2004/03/14 07:15:05 sraeburn Exp $
 * $Revision: 1.3 $
 * $Date: 2004/03/14 07:15:05 $
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
 * Select article by primary key.
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:05 $
 */
public class SearchKey extends Bean {


    /**
     * Column label for ModelResult description.
     */
    public static final String PROPERTY = "article";


    // ------------------------------------------------------------ Public Methods


    /**
     * Execute model for this bean, and return outcome in ModelResult.
     * @exception Collects and returns any Exceptions
     * @returns Null on success, or a collection of Exceptions
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {

        Integer key = getKeyInteger();
        if (key==null) {
            throw new ModelParameterException();
        }
        Collection result = Access.select(target,key);
        ModelResult modelResult = new ModelResultBase(result);
        modelResult.setDescription(key.toString(),PROPERTY);
        return modelResult;
   }

}