/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/logic/ELMatchSupport.java,v 1.3 2002/10/01 04:25:51 dmkarr Exp $
 * $Revision: 1.3 $
 * $Date: 2002/10/01 04:25:51 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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

package org.apache.strutsel.taglib.logic;

import org.apache.struts.util.MessageResources;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.RequestUtils;

/**
 * This class is used as a helper class for both the
 * <code>org.apache.strutsel.taglib.logic.ELMatchTag</code> and
 * <code>org.apache.strutsel.taglib.logic.ELNotMatchTag</code> classes.  It's
 * <code>condition</code> method encapsulates the common logic needed to
 * examine the <code>location</code> attribute to determine how to do the
 * comparison.
 */
class ELMatchSupport {
    /**
     * Performs a comparison of an expression and a value, with an optional
     * location specifier in the expression (start or end).
     *
     * @param desired Indication of whether the "truth" value of the comparison
     * is whether the expression and value are equal, or not equal.
     * @param expr Expression to test against a value.
     * @param value Value to test against an expression.
     * @param location if set, is "start" or "end" to indicate to look at the
     * start or end of the expression for the value.  If null, look anywhere in
     * the expression.
     * @param messages <code>MessageResources</code> object to reference for
     * error message text.
     * @param pageContext used to save exception information, if needed.
     * @return true if comparison result equals desired value, false
     * otherwise.
     */
    public static boolean condition(boolean           desired,
                                    String            expr,
                                    String            value,
                                    String            location,
                                    MessageResources  messages,
                                    PageContext       pageContext)
        throws JspException
    {
        boolean   result   = false;

        if (expr != null) {
            // Perform the comparison requested by the location attribute
            boolean matched = false;
            if (location == null) {
                matched = (expr.indexOf(value) >= 0);
            } else if (location.equals("start")) {
                matched = expr.startsWith(value);
            } else if (location.equals("end")) {
                matched = expr.endsWith(value);
            } else {
                JspException e = new JspException
                    (messages.getMessage("logic.location", location));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }

            result   = (matched == desired);
        }

        return (result);
    }
}
