/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/ResetTag.java,v 1.17 2004/03/14 06:23:46 sraeburn Exp $
 * $Revision: 1.17 $
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
import org.apache.struts.util.MessageResources;

/**
 * Tag for input fields of type "reset".
 *
 * @version $Revision: 1.17 $ $Date: 2004/03/14 06:23:46 $
 */
public class ResetTag extends BaseHandlerTag {

    // ----------------------------------------------------- Instance Variables

    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
        MessageResources.getMessageResources(Constants.Package + ".LocalStrings");

    /**
     * The name of the generated input field.
     */
    protected String property = null;

    /**
     * The body content of this tag (if any).
     */
    protected String text = null;

    /**
     * The value of the button label.
     */
    protected String value = null;

    // ------------------------------------------------------------- Properties

    /**
     * Return the field name.
     */
    public String getProperty() {

        return (this.property);

    }

    /**
     * Set the field name.
     *
     * @param property The field name
     */
    public void setProperty(String property) {

        this.property = property;

    }

    /**
     * Return the label value.
     */
    public String getValue() {

        return (this.value);

    }

    /**
     * Set the label value.
     *
     * @param value The label value
     */
    public void setValue(String value) {

        this.value = value;

    }

    // --------------------------------------------------------- Public Methods

    /**
     * Process the start of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {

        // Do nothing until doEndTag() is called
        this.text = null;
        return (EVAL_BODY_TAG);

    }

    /**
     * Save the associated label from the body content.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {

        if (bodyContent != null) {
            String value = bodyContent.getString().trim();
            if (value.length() > 0) {
                this.text = value;
            }
        }
        return (SKIP_BODY);

    }

    /**
     * Process the end of this tag.
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        TagUtils.getInstance().write(pageContext, renderResetElement(label()));

        return (EVAL_PAGE);
    }

    /**
     * Return the label value to display in the reset button.
     * @since Struts 1.1
     */
    protected String label() {
        String label = this.value;
        
        if ((label == null) && (this.text != null)) {
            label = this.text;
        }
        
        if ((label == null) || (label.length() < 1)) {
            label = "Reset";
        }
        
        return label;
    }

    /**
     * Generate an HTML reset button.
     * @param label The text to be displayed on the button.
     * @return A fully formed HTML reset button.
     * @throws JspException
     * @since Struts 1.1
     */
    protected String renderResetElement(String label) throws JspException {
        StringBuffer results = new StringBuffer("<input type=\"reset\"");
        
        if (property != null) {
            results.append(" name=\"");
            results.append(property);
            results.append("\"");
        }
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
        results.append(" value=\"");
        results.append(label);
        results.append("\"");
        results.append(this.prepareEventHandlers());
        results.append(this.prepareStyles());
        results.append(this.getElementClose());
        
        return results.toString();
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        property = null;
        text = null;
        value = null;

    }

}
