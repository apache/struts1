/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/BaseTag.java,v 1.2 2001/02/02 20:14:18 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2001/02/02 20:14:18 $
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

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.action.Action;
import org.apache.struts.util.MessageResources;

/**
 * Renders an HTML <base> element with an href 
 * attribute pointing to the absolute location of the enclosing JSP page. This 
 * tag is only valid when nested inside a head tag body. The presence 
 * of this tag allows the browser to resolve relative URL's to images,
 * CSS stylesheets  and other resources in a manner independent of the URL
 * used to call the ActionServlet.  There are no attributes associated with
 * this tag.
 *
 * @author Luis Arias <luis@elysia.com>
 * @version $Revision: 1.2 $ $Date: 2001/02/02 20:14:18 $
 */

public class BaseTag extends TagSupport {
  
  /**
   * The message resources for this package.
   */
  protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

  /**
   * Process the start of this tag.
   *
   * @exception JspException if a JSP exception has occurred
   */
  public int doStartTag() throws JspException {
    HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
    StringBuffer buf = new StringBuffer("<base href=\""); 
    buf.append(request.getScheme());
    buf.append("://");
    buf.append(request.getServerName());
    if ("http".equals(request.getScheme()) &&
        (80 == request.getServerPort())) {
        ;
    } else if ("https".equals(request.getScheme()) &&
               (443 == request.getServerPort())) {
        ;
    } else {
        buf.append(":");
        buf.append(request.getServerPort());
    }
    buf.append(request.getRequestURI());
    buf.append("\">");
    JspWriter out = pageContext.getOut();
    try {
        out.write(buf.toString());
    }
    catch (IOException e) {
        pageContext.setAttribute(Action.EXCEPTION_KEY, e,
                                 PageContext.REQUEST_SCOPE);
        throw new JspException(messages.getMessage("common.io", e.toString()));
    }
    return EVAL_BODY_INCLUDE;
  }
}
