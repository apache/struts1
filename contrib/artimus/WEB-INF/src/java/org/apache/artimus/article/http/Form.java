package org.apache.artimus.article.http;


import java.io.IOException;
import java.util.Map;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.BeanUtils;


/**
 * Article ActionForm bean.
 * An ActionForm bean serves as an adapter to make data entered into an HTML
 * available to the rest of a Web application, usually by transferring data
 * to an internal object that uses native types and implements a business
 * logic interface.
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/11/10 12:04:13 $
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
     * @param contributed The new contributed
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
     * Set the key
     * Generic mutator linked to this object's key field.
     * <p>
     * @param key The new key
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
