/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/CancelTag.java,v 1.17 2004/09/23 00:34:14 niallp Exp $
 * $Revision: 1.17 $
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

/**
 * Tag for input fields of type "cancel".
 *
 * @version $Revision: 1.17 $ $Date: 2004/09/23 00:34:14 $
 */

public class CancelTag extends SubmitTag {


    // ------------------------------------------------------------- Properties


    /** Returns the onClick event handler. */
    public String getOnclick() {
        return super.getOnclick() == null ? "bCancel=true;"
                               : super.getOnclick();
    }

    // --------------------------------------------------------- Constructor

    public CancelTag() {

        super();
        property = Constants.CANCEL_PROPERTY;

    }

    // --------------------------------------------------------- Protected Methods

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
    protected void prepareName(StringBuffer results) {
        prepareAttribute(results, "name", property);
    }

    /**
     * Return the default value
     * @param defaultValue The default value if none supplied
     */
    protected String getDefaultValue() {
        return "Cancel";
    }

    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        property = Constants.CANCEL_PROPERTY;

    }

}
