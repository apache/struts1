/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ImageTag.java,v 1.3 2001/01/08 21:36:06 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/01/08 21:36:06 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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


package org.apache.struts.taglib.html;


import java.lang.reflect.Method;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspWriter;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;


/**
 * Tag for input fields of type "image".
 *
 * @author Oleg V Alexeev
 * @version $Revision: 1.3 $ $Date: 2001/01/08 21:36:06 $
 */

public class ImageTag extends SubmitTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The source of image for image button.
     */
    protected String src = null;

    /**
     * The path of image for image button.
     */
    protected String path = null;
    
    /**
     * The name attribute for the image button
     */
    protected String property = "";

    /**
     * If not null - src property will
     * treated as key to retrieve string 
     * from message bundle.
     */
    protected String isKey = null;

    /**
     * The servlet context attribute key for our resources.
     */
    protected String bundle = Action.MESSAGES_KEY;


    /**
     * The default Locale for our server.
     */
    protected static final Locale defaultLocale = Locale.getDefault();


    /**
     * The session scope key under which our Locale is stored.
     */
    protected String localeKey = Action.LOCALE_KEY;

    // ------------------------------------------------------------- Properties

    
    /**
     * Return the property.
     */
    public String getProperty() {

	return (this.property);

    }


    /**
     * Set the property name.
     *
     * @param property The property name
     */
    public void setProperty(String property) {
   
	this.property = property;

    }

    /**
     * Return the bundle key.
     */
    public String getBundle() {

        return (this.bundle);

    }


    /**
     * Set the bundle key.
     *
     * @param bundle The new bundle key.
     */
    public void setBundle(String bundle) {

        this.bundle = bundle;

    }

    /**
     * Return the locale key.
     */
    public String getLocale() {

        return (this.localeKey);

    }


    /**
     * Set the locale key.
     *
     * @param locale The new locale key
     */
    public void setLocale(String locale) {

        this.localeKey = locale;

    }

    /**
     * Return the src of image.
     */
    public String getSrc() {

        return (this.src);

    }


    /**
     * Set the image src.
     *
     * @param value The image src.
     */
    public void setSrc(String src) {

        this.src = src;

    }

    /**
     * Return the path of image.
     */
    public String getPath() {

        return (this.path);

    }


    /**
     * Set the image path.
     *
     * @param value The image path.
     */
    public void setPath(String path) {

        this.path = path;

    }

    /**
     * Return the isKey.
     */
    public String getIsKey() {

        return (this.isKey);

    }


    /**
     * Set the isKey value.
     *
     * @param value The isKey switcher.
     */
    public void setIsKey(String isKey) {

        this.isKey = isKey;

    }



    // --------------------------------------------------------- Public Methods


    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        return (EVAL_BODY_TAG);

    }



    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        if( isKey!=null ) {
          MessageResources resources = (MessageResources)
             pageContext.getAttribute( bundle, PageContext.APPLICATION_SCOPE);
          if (resources == null)
             throw new JspException
                (messages.getMessage("messageTag.resources", bundle));
          Locale locale = null;
          try {
              locale = (Locale)
                  pageContext.getAttribute( localeKey, PageContext.SESSION_SCOPE);
          } catch (IllegalStateException e) {     // Invalidated session
              locale = null;
          }
          if (locale == null)
              locale = defaultLocale;
          src = resources.getMessage( locale, src);
          if( src==null )
           src = "";
        }

        // Acquire the alt label value we will be generating
        String alt = value;
        if ((alt== null) && (bodyContent != null))
            alt = bodyContent.getString().trim();
        if ((alt == null) || (alt.length() < 1))
            alt = "Submit";

        // Generate an HTML element
        StringBuffer results = new StringBuffer();
        results.append("<input type=\"image\" name=\"");
        results.append(property);
        results.append("\" alt=\"");
        results.append(alt);
        results.append("\" value=\"");
        results.append(value);
        results.append("\"");
        if (accesskey != null) {
            results.append(" accesskey=\"");
            results.append(accesskey);
            results.append("\"");
        }
        if (tabindex != null) {
            results.append(" tabindex=\"");
            results.append(tabindex);
            results.append("\"");
        }
        results.append(" src=\"");
        if( path != null )
         results.append(path);         
        results.append(src);
        results.append("\"");
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(">");

        // Render this element to our writer
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(results.toString());
        } catch (IOException e) {
            throw new JspException
                (messages.getMessage("common.io", e.toString()));
        }
        return (EVAL_PAGE);
    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        src = "submit";

    }


}
