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


import org.apache.scaffold.lang.Tokens;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;
import org.apache.scaffold.model.ModelResultBase;


/**
 * Deletes article.
 * @version $Rev$ $Date$
 */
public class Delete extends Bean {


    // ------------------------------------------------------------ Public Methods


    /**
     * Execute model for this bean, and return outcome in ModelResult.
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {

        int result = Access.delete(getKeyInteger());

        ModelResult modelResult = new ModelResultBase(target);
            if (result==0)
                modelResult.addMessage(Tokens.DATA_ACCESS_EMPTY);
            else
                modelResult.addMessage(Tokens.DATA_RECORD_DELETED);
            modelResult.addMessage(getKey());

        return modelResult;
    }
}