/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/logic/PresentTag.java,v 1.14 2003/03/24 04:33:42 dgraham Exp $
 * $Revision: 1.14 $
 * $Date: 2003/03/24 04:33:42 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
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


package org.apache.struts.taglib.logic;


import java.security.Principal;
import java.util.StringTokenizer;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import org.apache.struts.util.RequestUtils;


/**
 * Evalute the nested body content of this tag if the specified value
 * is present for this request.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.14 $ $Date: 2003/03/24 04:33:42 $
 */

public class PresentTag extends ConditionalTagBase {


    public static final String ROLE_DELIMITER = ",";
    
    // ------------------------------------------------------ Protected Methods


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition() throws JspException {

        return (condition(true));

    }


    /**
     * Evaluate the condition that is being tested by this particular tag,
     * and return <code>true</code> if the nested body content of this tag
     * should be evaluated, or <code>false</code> if it should be skipped.
     * This method must be implemented by concrete subclasses.
     *
     * @param desired Desired outcome for a true result
     *
     * @exception JspException if a JSP exception occurs
     */
    protected boolean condition(boolean desired) throws JspException {
        // Evaluate the presence of the specified value
        boolean present = false;
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        
        if (cookie != null) {
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookie.equals(cookies[i].getName())) {
                        present = true;
                        break;
                    }
                }
            }
            
        } else if (header != null) {
            String value = request.getHeader(header);
            present = (value != null);
            
        } else if (name != null) {
            Object value = null;
            try {
                if (property != null) {
                    value = RequestUtils.lookup(pageContext, name, property, scope);
                } else {
                    value = RequestUtils.lookup(pageContext, name, scope);
                }
            } catch (JspException e) {
                value = null;
            }
            present = (value != null);
            
        } else if (parameter != null) {
            String value = request.getParameter(parameter);
            present = (value != null);
            
        } else if (role != null) {
            StringTokenizer st = new StringTokenizer(role, ROLE_DELIMITER, false);
            while (!present && st.hasMoreTokens()) {
                present = request.isUserInRole(st.nextToken());
            }
            
        } else if (user != null) {
            Principal principal = request.getUserPrincipal();
            present = (principal != null) && user.equals(principal.getName());
            
        } else {
            JspException e = new JspException
                (messages.getMessage("logic.selector"));
            RequestUtils.saveException(pageContext, e);
            throw e;
        }

        return (present == desired);

    }


}
