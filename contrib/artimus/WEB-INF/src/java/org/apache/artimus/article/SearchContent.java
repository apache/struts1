/*
 * $Header: /home/cvs/jakarta-struts/contrib/artimus/WEB-INF/src/java/org/apache/artimus/article/SearchContent.java,v 1.4 2004/03/14 07:15:05 sraeburn Exp $
 * $Revision: 1.4 $
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


import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelParameterException;
import org.apache.scaffold.model.ModelResult;
import org.apache.scaffold.model.ModelResultBase;


/**
 * Search article content.
 * @version $Revision: 1.4 $ $Date: 2004/03/14 07:15:05 $
 */
public class SearchContent extends SearchProperty {


    /**
     * Property being searched.
     */
    private static final String PROPERTY = "content";


    // ------------------------------------------------------------ Constructor


    /**
     * Default constructor to set property.
     */
    public SearchContent() {

        super();
        setProperty(PROPERTY);

    }
}