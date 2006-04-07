/*
 * $Id$ 
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


package org.apache.struts.taglib;


import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;


/**
 * Simple Form bean for the Cactus test cases. Feel free to add whatever you
 * need to help with testing.
 *
 * @version $Rev$ $Date: 2004-10-16 12:38:42 -0400 (Sat, 16 Oct 2004)
 *          $
 */

public final class SimpleFormBeanForTesting extends ValidatorForm {


    private String field1 = null;
    private String field2 = null;

    // --------------------------------------------------------- Public Methods


    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        this.field1 = null;
        this.field2 = null;

    }


    /**
     * Returns the field1.
     *
     * @return String
     */
    public String getField1() {
        return field1;
    }

    /**
     * Returns the field2.
     *
     * @return String
     */
    public String getField2() {
        return field2;
    }

    /**
     * Sets the field1.
     *
     * @param field1 The field1 to set
     */
    public void setField1(String field1) {
        this.field1 = field1;
    }

    /**
     * Sets the field2.
     *
     * @param field2 The field2 to set
     */
    public void setField2(String field2) {
        this.field2 = field2;
    }

}
