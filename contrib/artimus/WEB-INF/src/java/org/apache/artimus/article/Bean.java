package org.apache.artimus.article;

import java.sql.Timestamp;

import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;

import org.apache.artimus.lang.Messages;


/**
 * Base class for other article queries.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:12 $
 */
public class Bean extends ModelBeanBase {

    // --------------------------------------------------- Instance Variables
    // ----------------------------------------------------------- Properties


    /**
     */
    protected Integer article = null;


    /**
     */
    public Integer getArticle() {
        return (this.article);
    }


    /**
     */
    public void setArticle(Integer article) {
        this.article = article;
            // Advise ancestor property
        if (article==null)
            setKey(null);
        else
            setKey(article.toString());
    }


    /**
     * The contributed of the article.
     */
    private Timestamp contributed = null;


    /**
     * Return the contributed of the article.
     * <p>
     * @return the contributed
     */
    public Timestamp getContributed() {
        return (this.contributed);
    }


    /**
     * Set the contributed of the article.
     * <p>
     * @param contributed The new contributed
     */
    public void setContributed(Timestamp contributed) {
        this.contributed = contributed;
    }


    /**
     * The contributor of the article.
     */
    protected String contributor = null;


    /**
     * Return the contributor of the article.
     * @return the contributor
     */
    public String getContributor() {
        return (this.contributor);
    }


    /**
     * Set the contributor of the article.
     * @param contributor The new contributor
     */
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }


    /**
     * The creator of the article.
     * May also be the contributor.
     */
    protected String creator = null;


    /**
     * Return the creator of the article.
     * @return the creator
     */
    public String getCreator() {
        return (this.creator);
    }


    /**
     * Set the creator of the article.
     * @param creator The new creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }


    /**
     * The title of the article.
     * This is displayed as a headline.
     */
    protected String title = null;


    /**
     * Return the title of the article.
     * @return the title
     */
    public String getTitle() {
        return (this.title);
    }


    /**
     * Set the title of the article.
     * @param title The new title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * The content of the article in HTML.
     */
    protected String content = null;


    /**
     * Return the content of the article.
     * @return the content
     */
    public String getContent() {
        return (this.content);
    }


    /**
     * Set the content of the article.
     * @param content The new content
     */
    public void setContent(String content) {
        this.content = content;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Set the contributed of the article.
     * <p>
     * @param contributed The new contributed
     */
    public void setContributedText(String contributedText) {
        if (contributedText==null)
            this.contributed = null;
        else
            this.contributed = Timestamp.valueOf(contributedText);
    }


    /**
     * Set the contributed of the article.
     * <p>
     * @param contributed The new contributed
     */
    public String getContributedText() {
        Timestamp contributed = getContributed();
        if (contributed==null)
            return null;
        else
            return contributed.toString();
    }


    /**
     * Execute model for this bean, and return outcome in ModelResult.
     * @exception Collects and returns any Exceptions
     * @returns Null on success, or a collection of Exceptions
     */
    public ModelResult execute(Object source, Object target)
            throws ModelException {
        throw new ModelException(Messages.NOT_IMPLEMENTED_EXCEPTION);
    }


} // end Article

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
