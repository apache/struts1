/*
 * Copyright 2002-2004 The Apache Software Foundation.
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

package org.apache.struts.faces.taglib;


import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentBodyTag;
import javax.servlet.jsp.JspException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SubviewTag extends UIComponentBodyTag {


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static final Log log = LogFactory.getLog(SubviewTag.class);


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>Return the component type for the component to be created.</p>
     */
    public String getComponentType() {
        return ("NamingContainer");
    }


    /**
     * <p>Return the renderer type for the component to be created.</p>
     */
    public String getRendererType() {
        return (null);
    }


    /**
     * <p>Log this method call and delegate to our superclass.</p>
     */
    public int doStartTag() throws JspException {
        if (log.isDebugEnabled()) {
            log.debug("doStartTag(" + getId() + ")");
        }
        return (super.doStartTag());
    }


    /**
     * <p>Log this method call and delegate to our superclass.</p>
     */
    public int doEndTag() throws JspException {
        if (log.isDebugEnabled()) {
            FacesContext context = getFacesContext();
            log.debug("doEndTag  (" + getId() + ")");
            log.debug(" component=" + getComponentInstance());
            log.debug("   context=" + context);
            log.debug("  viewRoot=" + context.getViewRoot());
            log.debug("      rkId=" + context.getViewRoot().getRenderKitId());
            log.debug("    parent=" + getComponentInstance().getParent());
        }
        return (super.doEndTag());
    }


}
