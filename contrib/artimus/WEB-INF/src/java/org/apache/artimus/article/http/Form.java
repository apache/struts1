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
 

package org.apache.artimus.article.http;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * Article ActionForm bean.
 * An ActionForm bean serves as an adapter to make data entered into an HTML
 * available to the rest of a Web application, usually by transferring data
 * to an internal object that uses native types and implements a business
 * logic interface.
 * @version $Rev$ $Date$
 */
public class Form extends ActionForm {


// --------------------------------------------------- Instance Variables
// ----------------------------------------------------------- Properties


    /**
     * The article's primary key as a String.
     * <p>
     * Another accessor is provided to return the primary key
     * as an int (integer).
     * <p>
     * @see getArticleInt()
     */
    private String article = null;


    /**
     * Return the article primary key.
     * <p>
     * @return the article primary key
     */
    public String getArticle() {
        return (this.article);
    }


    /**
     * Set the article primary key.
     * <p>
     * Usually only on creation.
     * Changing primary keys is not a task for mere mortals.
     * @param article The new article
     */
    public void setArticle(String article) {
        this.article = article;
    }


    /**
     * The contributor of the article.
     */
    private String contributor = null;


    /**
     * Return the contributor of the article.
     * <p>
     * @return the contributor
     */
    public String getContributor() {
        return (this.contributor);
    }


    /**
     * Set the contributor of the article.
     * <p>
     * @param contributor The new contributor
     */
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }


    /**
     * The contributed of the article.
     */
    private String contributedText = null;


    /**
     * Return the contributed of the article.
     * <p>
     * @return the contributed
     */
    public String getContributedText() {
        return this.contributedText;
    }

    /**
     * Set the contributed of the article.
     * <p>
     * @param contributedText The new contributed
     */
    public void setContributedText(String contributedText) {
        this.contributedText = contributedText;
    }


    /**
     * The creator of the article.
     * <p>
     * May also be the contributor.
     */
    private String creator = null;


    /**
     * Return the creator of the article.
     * <p>
     * @return the creator
     */
    public String getCreator() {
        return (this.creator);
    }


    /**
     * Set the creator of the article.
     * <p>
     * @param creator The new creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }


    /**
     * The title of the article.
     * <p>
     * This is displayed as a headline.
     */
    private String title = null;


    /**
     * Return the title of the article.
     * <p>
     * @return the title
     */
    public String getTitle() {
        return (this.title);
    }


    /**
     * Set the title of the article.
     * <p>
     * @param title The new title
     */
    public void setTitle(String title) {
        this.title = title;
    }



    /**
     * The content of the article in HTML.
     */
    private String content = null;


    /**
     * Return the content of the article.
     * <p>
     * @return the content
     */
    public String getContent() {
        return (this.content);
    }


    /**
     * Set the content of the article.
     * <p>
     * @param content The new content
     */
    public void setContent(String content) {
        this.content = content;
    }


// --------------------------------------------------------- Public Methods


    /**
     * Return the key for this article.
     * <p>
     * Generic accessor linked to this object's key field.
     * <p>
     * @return the key
     */
    public String getKey() {
        return getArticle();
    }


    /**
     * Set the article
     * Generic mutator linked to this object's key field.
     * <p>
     * @param article The new article
     */
    public void setKey(String article) {
        setArticle(article);
    }




    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found. If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     * <p>
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

       ActionErrors errors = new ActionErrors();

        if ((creator == null) || ("".equals(creator))) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("errors.required","Author"));
        }

        if ((title == null) || ("".equals(title))) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("errors.required","Title"));
        }

        if ((content == null) || ("".equals(content))) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                new ActionError("errors.required","Article text"));
        }

        return (errors);
    }


    /**
     * Reset all properties to their default values.
     * <p>
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.article = null;
        this.contributor = null;
        this.creator = null;
        this.title = null;
        this.content = null;
    }


// ----- end ArticleForm -----

}