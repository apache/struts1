package org.apache.artimus.article;

import java.sql.SQLException;

import org.apache.scaffold.lang.Tokens;
import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;
import org.apache.scaffold.model.ModelResultBase;

import org.apache.artimus.lang.Messages;
import org.apache.artimus.article.http.Form;


/**
 * Insert or update an article.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:12 $
 */
public class Store extends Bean {

    // ------------------------------------------------------------ Public Methods


    /**
     * Allocate next key for article table.
     * @return key to use for insertion
     * @exception SQLException if SQL error occurs
     */
    public void allocateKey() throws ModelException {

        Integer key = Access.allocateKey();

        setArticle(key);
    }


    /**
     * Insert <code>key</code> record.
     * @returns Number of records inserted.
     */
    public void insert() throws ModelException {

        allocateKey();

        Access.insert(
            getArticle(),
            getContributed(),
            getContributor(),
            getCreator(),
            getTitle(),
            getContent()
        );

    }


    /**
     * Update <code>key</code> record.
     * @returns Number of records updated.
     */
    public void update() throws ModelException {

        Access.update(
            getArticle(),
            getContributed(),
            getContributor(),
            getCreator(),
            getTitle(),
            getContent()
        );

    }


    /**
     * Execute model for this bean, and return outcome in ModelResult.
     * @exception Collects and returns any Exceptions
     * @returns Null on success, or a collection of Exceptions
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {

        ModelResult modelResult = new ModelResultBase();

        if (isBlank(getKey())) {
            insert();
            modelResult.addMessage(Tokens.DATA_RECORD_INSERTED);
        }
        else {
            update();
            modelResult.addMessage(Tokens.DATA_RECORD_UPDATED);
        }
        modelResult.addMessage(getKey());

        modelResult.add(target);
        return modelResult;
    }

 }


/*
    public ModelResult perform(Iterator rows) throws ModelException {
        if (this.article==null)
            insert(rows);
        else
            update(rows);
            // :FIXME: Should provide a batch insert method
        return ModelResult.factory(this);
    }

    public void update(Iterator rows) throws SQLException {
        while (rows.hasNext())
            update((ModelBean) rows.next());
    }

    public void insert(Iterator rows) throws SQLException {
        int count = 0;
        while (rows.hasNext())
            insert((ModelBean) rows.next());
        return count;
    }
*/


/*
 *
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
