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
 

package org.apache.artimus.wizard;


import java.util.Collection;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResourceException;
import org.apache.scaffold.sql.StatementUtils;

import org.apache.artimus.wizard.sql.Commands;
import org.apache.artimus.wizard.sql.Statements;


/**
 * Data access methods for Wizard package.
 * This class brings together SQL Commands and SQL Statements
 * to execute update queries or return collections of beans
 * from the database.
 * From the package's viewpoint, this class <b>is</b>
 * the database (or "model").
 * <p>
 * This class could be based on an interface if another
 * (non-SQL) type of access was needed.
 * @version $Rev$ $Date$
 */
public final class Model {

    /**
     * Return next key for WIZARD table.
     * @return The Integer key value to be inserted
     * @exception ModelException if SQL error occurs
     * @version $Rev$ $Date$
    **/
    public synchronized static final Integer allocateKey()
            throws ModelException {
        try {

             return org.apache.artimus.keys.sql.Statements.allocateKey(
                 Commands.WIZARD_TABLE);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }

    }


    /**
     * Insert new entry into ARTICLE table.
     * <p>
     * @return 0 if fails
     * @exception ModelException if SQL error occurs
    **/
    public static final int insert (
                 Integer id, Timestamp date, Float amount, Byte check,
                 String phone, String zip, String email, String text,
                 Integer wizard
            ) throws ModelException {
        try {

            return Statements.execute(Commands.WIZARD_INSERT,
                id, date,  amount,  check,
                phone,  zip,  email,  text,  wizard
            );
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Update entry in ARTICLE table.
     * <p>
     * @return 0 if fails
     * @exception ModelException if SQL error occurs
    **/
    public static final int update (
                 Integer id, Timestamp date, Float amount, Byte check,
                 String phone, String zip, String email, String text,
                 Integer wizard
            ) throws ModelException {
         try {

            return Statements.execute(Commands.WIZARD_UPDATE,
                  id, date,  amount,  check,
                  phone,  zip,  email,  text, wizard
            );
        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
    }


    /**
     * Select record from WIZARD table by primary key.
     * <p>
     * @return Collection with record or empty collection
     * @exception ModelException if SQL error occurs
     * @param key
     * @param target Object object to create Collection from ResultSet
     * @version $Rev$ $Date$
    **/
    public static final Collection select(Object target, int key)
            throws ModelException {
        try {

            return StatementUtils.getCollection("WIZARD",
                target,Commands.WIZARD_SELECT_KEY,key);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
   }

    public static final Collection select(Object target)
            throws ModelException {
        try {

            return StatementUtils.getCollection("WIZARD",
                target,Commands.WIZARD_SEARCH_BASE);

        }
        catch (SQLException e) {
            throw new ModelResourceException(e);
        }
   }

} // ---- End Model -----