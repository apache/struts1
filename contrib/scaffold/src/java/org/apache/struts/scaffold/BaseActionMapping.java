/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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
 

package org.apache.struts.scaffold;


import org.apache.struts.action.RequestActionMapping;

/**
 * Enhanced base ActionMapping.
 * @version $Rev$ $Date$
 */
public class BaseActionMapping extends RequestActionMapping {

    /**
     * Forward compatabilty with Struts 1.1.
     * Comment or remove this field for Stuts 1.1
     */
    private static boolean configured = false;

    /**
     * Message if they try to set a frozen configuration property.
     */
    private static String FROZEN_MESSAGE = "Configuration is frozen";

    /**
     * Adds submitPath to RequestActionMapping.
     *
     * This is useful when using one mapping to acquire a
     * blank or prepopulated form, and then submitting that
     * form to another mapping to complete the insert or update.
     *
     * It is also useful when reusing the same base form
     * or result list in different workflows.
     * Each mapping can indicate where to return,
     * for its own workflow.
     *
     * The current mapping is stored in the request under
     * the Globals.MAPPING_KEY (or the equivalent Struts 1.0
     * Action.MAPPING_KEY).
     *
     * You can access this property, or any mapping property,
     * using <bean:define>, or the equivalent, and then using
     * a Runtime Expression to use the property with the
     * <code>action</code> property of the html:form tag.
     *
     * To modify runtime behavior depending on whether returnPath
     * has been set, use the <logic:present> tag to either
     * use the returnPath or some alternate property.
     *
     * To use more than one-time property in a response,
     * pass a helper bean from the action instead.
     */
    protected String submitPath = null;
    public String getSubmitPath() {
        return this.submitPath;
    }
    public void setSubmitPath(String submitPath) {
        if (configured) {
            throw new IllegalStateException(FROZEN_MESSAGE);
        }
        this.submitPath = submitPath;
    }


} // end BaseActionMapping