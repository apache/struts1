package org.apache.artimus.wizard;


import java.io.IOException;
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
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
 */
public final class Model {

    /**
     * Return next key for WIZARD table.
     * @return The Integer key value to be inserted
     * @exception ModelException if SQL error occurs
     * @author Ted Husted
     * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
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
     * @author Ted Husted
     * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
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
 *    notice, this Collection of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this Collection of conditions and the following disclaimer in
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
