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

import java.sql.Timestamp;

import org.apache.scaffold.model.ModelBeanBase;
import org.apache.scaffold.model.ModelException;
import org.apache.scaffold.model.ModelResult;

import org.apache.artimus.lang.Messages;


/**
 * Base class for other article queries.
 * @version $Rev$ $Date$
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