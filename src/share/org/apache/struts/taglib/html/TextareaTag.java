/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/TextareaTag.java,v 1.19 2004/03/14 06:23:46 sraeburn Exp $
 * $Revision: 1.19 $
 * $Date: 2004/03/14 06:23:46 $
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

package org.apache.struts.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.TagUtils;

/**
 * Custom tag for input fields of type "textarea".
 *
 * @version $Revision: 1.19 $ $Date: 2004/03/14 06:23:46 $
 */
public class TextareaTag extends BaseInputTag {


    // ----------------------------------------------------- Instance Variables


    /**
     * The name of the bean containing our underlying property.
     */
    protected String name = Constants.BEAN_KEY;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * Generate the required input tag.
     * Support for indexed since Struts 1.1
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        
        TagUtils.getInstance().write(pageContext, this.renderTextareaElement());

        return (EVAL_BODY_TAG);
    }

    /**
     * Generate an HTML &lt;textarea&gt; tag.
     * @throws JspException
     * @since Struts 1.1
     */
    protected String renderTextareaElement() throws JspException {
        StringBuffer results = new StringBuffer("<textarea");
        
        results.append(" name=\"");
        // @since Struts 1.1
        if (indexed) {
            prepareIndex(results, name);
        }
        results.append(property);
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
        if (cols != null) {
            results.append(" cols=\"");
            results.append(cols);
            results.append("\"");
        }
        if (rows != null) {
            results.append(" rows=\"");
            results.append(rows);
            results.append("\"");
        }
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        results.append(">");
        
        results.append(this.renderData());
        
        results.append("</textarea>");
        return results.toString();
    }

    /**
     * Renders the value displayed in the &lt;textarea&gt; tag.
     * @throws JspException
     * @since Struts 1.1
     */
    protected String renderData() throws JspException {
        String data = this.value;

        if (data == null) {
            data = this.lookupProperty(this.name, this.property);
        }
        
        return (data == null) ? "" : TagUtils.getInstance().filter(data);
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        name = Constants.BEAN_KEY;

    }

}
