/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/SubmitTag.java,v 1.22 2004/09/23 00:34:14 niallp Exp $
 * $Revision: 1.22 $
 * $Date: 2004/09/23 00:34:14 $
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
 * Tag for input fields of type "submit".
 *
 * @version $Revision: 1.22 $ $Date: 2004/09/23 00:34:14 $
 */
public class SubmitTag extends BaseHandlerTag {


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
                text = value;
            }
        }
        return (SKIP_BODY);

    }


    /**
     * Process the end of this tag.
     * <p>
     * Support for Indexed property since Struts 1.1
     *
     * @exception JspException if a JSP exception has occurred
     */
    public int doEndTag() throws JspException {

        // Generate an HTML element
        StringBuffer results = new StringBuffer();
        results.append(getElementOpen());
        prepareName(results);
        prepareButtonAttributes(results);
        results.append(prepareEventHandlers());
        results.append(prepareStyles());
        prepareOtherAttributes(results);
        results.append(getElementClose());

        TagUtils.getInstance().write(pageContext, results.toString());

        return (EVAL_PAGE);

    }

    /**
     * Render the openning element
     * @param results The StringBuffer that output will be appended to.
     */
    protected String getElementOpen() {
        return "<input type=\"submit\"";
    }

    /**
     * Render the name element
     * @param results The StringBuffer that output will be appended to.
     */
    protected void prepareName(StringBuffer results) throws JspException {

        if (property != null) {
            results.append(" name=\"");
            results.append(property);
            // * @since Struts 1.1
            if( indexed )
                prepareIndex( results, null );
            results.append("\"");
        }

    }

    /**
     * Render the button attributes
     * @param results The StringBuffer that output will be appended to.
     */
    protected void prepareButtonAttributes(StringBuffer results) 
                      throws JspException {
        prepareAttribute(results, "accesskey", getAccesskey());
        prepareAttribute(results, "tabindex", getTabindex());
        prepareValue(results);
    }

    /**
     * Render the value element
     * @param results The StringBuffer that output will be appended to.
     */
    protected void prepareValue(StringBuffer results) {

        // Acquire the label value we will be generating
        String label = value;
        if ((label == null) && (text != null))
            label = text;
        if ((label == null) || (label.trim().length() < 1))
            label = getDefaultValue();

        prepareAttribute(results, "value", label);

    }

    /**
     * Return the default value
     * @param defaultValue The default value if none supplied
     */
    protected String getDefaultValue() {
        return "Submit";
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
