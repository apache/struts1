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
 

package org.apache.artimus.keys;


import java.sql.SQLException;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResourceException;

import org.apache.scaffold.sql.StatementUtils;

import org.apache.artimus.keys.sql.Commands;


/**
 * Data access methods for Articles application.
 * <p>
 * @version $Rev$ $Date$
 */
public final class Access {


// ---- KEYS ----


    /**
     * Creates KEYS table. This is a wrapper to pass
     * KEYS_TABLE and KEYS_CREATE to CreateTable(),
     * thereby encapsulating the implementation.
     * <p>
     * If create succeeds, will insert starter primary
     * keys for other tables (CollectionS and ARTICLE).
     * <p>
     * @exception SQL exception if SQL error occurs
     * @version $Rev$ $Date$
     **/
    public static final void createTable() throws ModelException {

        try {

             int result = StatementUtils.createTable(null,
                Commands.KEYS_TABLE, Commands.KEYS_CREATE
             );
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }

    }



} // ---- End Data -----