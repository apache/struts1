/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ImgTag.java,v 1.7 2001/02/20 02:59:00 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2001/02/20 02:59:00 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import org.apache.struts.action.Action;
import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyUtils;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;


/**
 * Generate an IMG tag to the specified image URI.
 * <p>
 * TODO:
 * <ul>
 *   <li>make the <strong>alt</strong>, <strong>src</strong>, and
 *       <strong>lowsrc</strong> settable from properties (for i18n)</li>
 *   <li>handle <strong>onLoad</strong>, <strong>onAbort</strong>, and
 *       <strong>onError</strong> events (my JavaScript book is very old,
 *       there may be more unsupported events in the past couple of IE
 *       versions)
 * </ul>
 *
 * @author Michael Westbay
 * @author Craig McClanahan
 * @version $Revision: 1.7 $
 */

public class ImgTag extends BaseHandlerTag {


    // ------------------------------------------------------------- Properties


    /**
     * The property to specify where to align the image.
     */
    protected String align = null;

    public String getAlign() {
        return (this.align);
    }

    public void setAlign(String align) {
        this.align = align;
    }


    /**
     * The alternate text to display for the image.  This is used for
     * text based browsers and/or as a "tool-tip" for the image.
     */
    protected String alt = null;

    public String getAlt() {
        return (this.alt);
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }


    /**
     * The message lookup key used to look up internationalized messages.
     */
    protected String altKey = null;

    public String getAltKey() {
        return (this.altKey);
    }

    public void setAltKey(String altKey) {
        this.altKey = altKey;
    }


    /**
     * The border size around the image.
     */
    protected String border = null;

    public String getBorder() {
        return (this.border);
    }

    public void setBorder(String border) {
        this.border = border;
    }


    /**
     * The name of the servlet context attribute containing our message
     * resources.
     */
    protected String bundle = Action.MESSAGES_KEY;

    public String getBundle() {
        return (this.bundle);
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }


    /**
     * The default Locale for our server.
     */
    protected static final Locale defaultLocale = Locale.getDefault();


    /**
     * The image height.
     */
    protected String height = null;

    public String getHeight() {
        return (this.height);
    }

    public void setHeight(String height) {
        this.height = height;
    }


    /**
     * The horizontal spacing around the image.
     */
    protected String hspace = null;

    public String getHspace() {
        return (this.hspace);
    }

    public void setHspace(String hspace) {
        this.hspace = hspace;
    }


    /**
     * The image name for named images.
     */
    protected String imageName = null;

    public String getImageName() {
        return (this.imageName);
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    /**
     * Server-side image map declaration.
     */
    protected String ismap = null;

    public String getIsmap() {
        return (this.ismap);
    }

    public void setIsmap(String ismap) {
        this.ismap = ismap;
    }


    /**
     * The name of the attribute containing the Locale to be used for
     * looking up internationalized messages.
     */
    protected String locale = Action.LOCALE_KEY;

    public String getLocale() {
        return (this.locale);
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }


    /**
     * The low resolution image source URI.
     */
    protected String lowsrc = null;

    public String getLowsrc() {
	return (this.lowsrc);
    }

    public void setLowsrc(String lowsrc) {
	this.lowsrc = lowsrc;
    }


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


    /**
     * The JSP bean name for query parameters.
     */
    protected String name = null;

    public String getName() {
	return (this.name);
    }

    public void setName(String name) {
	this.name = name;
    }


    /**
     * The context-relative path, starting with a slash character, of the
     * image to be displayed by this rendered tag.
     */
    protected String page = null;

    public String getPage() {
        return (this.page);
    }

    public void setPage(String page) {
        this.page = page;
    }


    /**
     * The message resources key under which we should look up the
     * <code>page</code> attribute for this generated tag, if any.
     */
    protected String pageKey = null;

    public String getPageKey() {
        return (this.pageKey);
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }


    /**
     * In situations where an image is dynamically generated (such as to create
     * a chart graph), this specifies the single-parameter request parameter
     * name to generate.
     */
    protected String paramId = null;

    public String getParamId() {
        return (this.paramId);
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }


    /**
     * The single-parameter JSP bean name.
     */
    protected String paramName = null;

    public String getParamName() {
        return (this.paramName);
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }


    /**
     * The single-parameter JSP bean property.
     */
    protected String paramProperty = null;

    public String getParamProperty() {
        return (this.paramProperty);
    }

    public void setParamProperty(String paramProperty) {
        this.paramProperty = paramProperty;
    }


    /**
     * The single-parameter JSP bean scope.
     */
    protected String paramScope = null;

    public String getParamScope() {
        return (this.paramScope);
    }

    public void setParamScope(String paramScope) {
        this.paramScope = paramScope;
    }


    /**
     * The JSP bean property name for query parameters.
     */
    protected String property = null;

    public String getProperty() {
	return (this.property);
    }

    public void setProperty(String property) {
	this.property = property;
    }


    /**
     * The scope of the bean specified by the name property, if any.
     */
    protected String scope = null;

    public String getScope() {
        return (this.scope);
    }

    public void setScope(String scope) {
        this.scope = scope;
    }


    /**
     * The image source URI.
     */
    protected String src = null;

    public String getSrc() {
	return (this.src);
    }

    public void setSrc(String src) {
	this.src = src;
    }


    /**
     * The message resources key under which we should look up the
     * <code>src</code> attribute for this generated tag, if any.
     */
    protected String srcKey = null;

    public String getSrcKey() {
        return (this.srcKey);
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }


    /**
     * Client-side image map declaration.
     */
    protected String usemap = null;

    public String getUsemap() {
        return (this.usemap);
    }

    public void setUsemap(String Usemap) {
        this.usemap = usemap;
    }


    /**
     * The vertical spacing around the image.
     */
    protected String vspace = null;

    public String getVspace() {
        return (this.vspace);
    }

    public void setVspace(String vspace) {
        this.vspace = vspace;
    }


    /**
     * The image width.
     */
    protected String width = null;

    public String getWidth() {
        return (this.width);
    }

    public void setWidth(String width) {
        this.width = width;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Render the beginning of the IMG tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

	// Evaluate the body of this tag
	return (EVAL_BODY_TAG);

    }


    /**
     * Render the end of the IMG tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

	// Generate the name definition or image element
        HttpServletRequest request =
	  (HttpServletRequest) pageContext.getRequest();
	HttpServletResponse response =
	  (HttpServletResponse) pageContext.getResponse();
	StringBuffer results = new StringBuffer("<img");
        String tmp = src();
        String srcurl = url(tmp);
        if (srcurl != null) {
            results.append(" src=\"");
            results.append(response.encodeURL(ResponseUtils.filter(srcurl)));
            results.append("\"");
        }
        String lowsrcurl = url(this.lowsrc);
        if (lowsrcurl != null) {
            results.append(" lowsrc=\"");
            results.append
                (response.encodeURL(ResponseUtils.filter(lowsrcurl)));
            results.append("\"");
        }
        tmp = alt();
        if (tmp != null) {
            results.append(" alt=\"");
            results.append(tmp);
            results.append("\"");
        }
        if (imageName != null) {
            results.append(" name=\"");
            results.append(imageName);
            results.append("\"");
        }
        if (height != null) {
            results.append(" height=\"");
            results.append(height);
            results.append("\"");
        }
        if (width != null) {
            results.append(" width=\"");
            results.append(width);
            results.append("\"");
        }
        if (align != null) {
            results.append(" align=\"");
            results.append(align);
            results.append("\"");
        }
        if (border != null) {
            results.append(" border=\"");
            results.append(border);
            results.append("\"");
        }
        if (hspace != null) {
            results.append(" hspace=\"");
            results.append(hspace);
            results.append("\"");
        }
        if (vspace != null) {
            results.append(" vspace=\"");
            results.append(vspace);
            results.append("\"");
        }
        if (ismap != null) {
            results.append(" ismap=\"");
            results.append(ismap);
            results.append("\"");
        }
        if (usemap != null) {
            results.append(" usemap=\"");
            results.append(usemap);
            results.append("\"");
        }
        results.append(prepareStyles());
        results.append(prepareEventHandlers());
	results.append(">");

	// Print this element to our output writer
        ResponseUtils.write(pageContext, results.toString());

        // Evaluate the reaminder of this page
	return (EVAL_PAGE);

    }


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
        alt = null;
        altKey = null;
        border = null;
        bundle = Action.MESSAGES_KEY;
        height = null;
        hspace = null;
        imageName = null;
        ismap = null;
        locale = Action.LOCALE_KEY;
        lowsrc = null;
	name = null;
        page = null;
        pageKey = null;
        paramId = null;
        paramName = null;
        paramProperty = null;
        paramScope = null;
	property = null;
        scope = null;
	src = null;
        srcKey = null;
        usemap = null;
        vspace = null;
        width = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the alternate text to be included on this generated element,
     * or <code>null</code> if there is no such text.
     *
     * @exception JspException if an error occurs
     */
    protected String alt() throws JspException {

        if (this.alt != null) {
            if (this.altKey != null) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.alt"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            } else {
                return (this.alt);
            }
        } else if (this.altKey != null) {
            MessageResources resources = (MessageResources)
                pageContext.getAttribute(this.bundle,
                                         PageContext.APPLICATION_SCOPE);
            if (resources == null) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.bundle", this.bundle));
                throw e;
            }
            Locale locale = null;
            try {
                locale = (Locale)
                    pageContext.getAttribute(this.locale,
                                             PageContext.SESSION_SCOPE);
            } catch (IllegalStateException e) {
                locale = null; // Invalidated session
            }
            if (locale == null)
                locale = defaultLocale;
            return (resources.getMessage(locale, this.altKey));
        } else {
            return (null);
        }

    }


    /**
     * Return the base source URL that will be rendered in the <code>src</code>
     * property for this generated element, or <code>null</code> if there is
     * no such URL.
     *
     * @exception JspException if an error occurs
     */
    protected String src() throws JspException {

        // Deal with a direct context-relative page that has been specified
        if (this.page != null) {
            if ((this.src != null) || (this.srcKey != null) ||
                (this.pageKey != null)) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            return (request.getContextPath() + this.page);
        }

        // Deal with an indirect context-relative page that has been specified
        if (this.pageKey != null) {
            if ((this.src != null) || (this.srcKey != null)) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            HttpServletRequest request =
                (HttpServletRequest) pageContext.getRequest();
            return (request.getContextPath() +
                    RequestUtils.message(pageContext, bundle,
                                         locale, this.pageKey));
        }

        // Deal with an absolute source that has been specified
        if (this.src != null) {
            if (this.srcKey != null) {
                JspException e = new JspException
                    (messages.getMessage("imgTag.src"));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }
            return (this.src);
        }

        // Deal with an indirect source that has been specified
        if (this.srcKey == null) {
            JspException e = new JspException
                (messages.getMessage("imgTag.src"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
        return (RequestUtils.message(pageContext, bundle,
                                     locale, this.srcKey));

    }


    /**
     * Return the specified src URL, modified as necessary with optional
     * request parameters.
     * <p>
     * This is based on the way a
     * <a href="LinkTag.html#hyperlink">hyperlink</a>
     * is generated for the <a href="LinkTag.html">LinkTag</a>.
     *
     * @param url The URL to be modified (or null if this url will not be used)
     *
     * @exception JspException if an error occurs preparing the URL
     * @see LinkTag#hyperlink
     */
    protected String url(String url) throws JspException {

        if (url == null)
            return (url);

        // Start with an unadorned URL as specified
	StringBuffer  src = new StringBuffer(url);


        // Append a single-parameter name and value, if requested
        if ((paramId != null) && (paramName != null)) {
            if (src.toString().indexOf('?') < 0)
                src.append('?');
            else
                src.append('&');
            src.append(paramId);
            src.append('=');
            Object value = RequestUtils.lookup(pageContext, paramName,
                                              paramProperty, paramScope);
            if (value != null)
                src.append(value.toString());
        }

	// Just return the URL if there is no bean to look up
	if ((property != null) && (name == null)) {
	    JspException e = new JspException
		(messages.getMessage("getter.name"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }
	if (name == null)
	    return (src.toString());

	// Look up the map we will be using
        Object mapObject = RequestUtils.lookup(pageContext, name,
                                               property, scope);
	Map map = null;
        try {
            map = (Map) mapObject;
        } catch (ClassCastException e) {
            RequestUtils.saveException(pageContext, e);
            throw new JspException
                (messages.getMessage("imgTag.type"));
	}

	// Append the required query parameters
	boolean question = (src.toString().indexOf("?") >= 0);
	Iterator keys = map.keySet().iterator();
        while (keys.hasNext()) {
	    String key = (String) keys.next();
	    Object value = map.get(key);
            if (value == null) {
                if (question)
                    src.append('&');
                else {
                    src.append('?');
                    question = true;
                }
                src.append(key);
                src.append('=');
                // Interpret null as "no value specified"
	    } else if (value instanceof String[]) {
		String values[] = (String[]) value;
		for (int i = 0; i < values.length; i++) {
		    if (question)
			src.append('&');
		    else {
			src.append('?');
			question = true;
		    }
		    src.append(key);
		    src.append('=');
		    src.append(URLEncoder.encode(values[i]));
		}
	    } else {
		if (question)
		    src.append('&');
		else {
		    src.append('?');
		    question = true;
		}
		src.append(key);
		src.append('=');
		src.append(URLEncoder.encode(value.toString()));
	    }
	}

	// Return the final result
	return (src.toString());

    }


}
