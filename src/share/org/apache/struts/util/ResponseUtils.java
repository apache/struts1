/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/ResponseUtils.java,v 1.11 2004/03/14 06:23:51 sraeburn Exp $
 * $Revision: 1.11 $
 * $Date: 2004/03/14 06:23:51 $
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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

package org.apache.struts.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.TagUtils;

/**
 * General purpose utility methods related to generating a servlet response
 * in the Struts controller framework.
 *
 * @version $Revision: 1.11 $ $Date: 2004/03/14 06:23:51 $
 * @deprecated Use the corresponding TagUtils methods instead.  
 * This class will be removed after Struts 1.2.
 */
public class ResponseUtils {


    // ------------------------------------------------------- Static Variables


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources
        ("org.apache.struts.util.LocalStrings");



    // --------------------------------------------------------- Public Methods


    /**
     * Filter the specified string for characters that are senstive to
     * HTML interpreters, returning the string with these characters replaced
     * by the corresponding character entities.
     *
     * @param value The string to be filtered and returned
     * @deprecated
     */
    public static String filter(String value) {
        return TagUtils.getInstance().filter(value);
    }


    /**
     * Write the specified text as the response to the writer associated with
     * this page.  <strong>WARNING</strong> - If you are writing body content
     * from the <code>doAfterBody()</code> method of a custom tag class that
     * implements <code>BodyTag</code>, you should be calling
     * <code>writePrevious()</code> instead.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     * @deprecated
     */
    public static void write(PageContext pageContext, String text)
        throws JspException {

        TagUtils.getInstance().write(pageContext, text);

    }


    /**
     * Write the specified text as the response to the writer associated with
     * the body content for the tag within which we are currently nested.
     *
     * @param pageContext The PageContext object for this page
     * @param text The text to be written
     *
     * @exception JspException if an input/output error occurs (already saved)
     * @deprecated
     */
    public static void writePrevious(PageContext pageContext, String text)
        throws JspException {

        TagUtils.getInstance().writePrevious(pageContext, text);

    }


}
