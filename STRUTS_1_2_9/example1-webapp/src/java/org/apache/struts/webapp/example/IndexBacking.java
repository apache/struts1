/*
 * Copyright 1999-2002,2004 The Apache Software Foundation.
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


package org.apache.struts.webapp.example;


import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>Backing bean for the <code>index.jsp</code> page.</p>
 */

public class IndexBacking extends AbstractBacking {


    // -------------------------------------------------------- Static Variables


    private static final Log log = LogFactory.getLog(IndexBacking.class);


    // ----------------------------------------------------------------- Actions


    /**
     * <p>Forward to the <em>Create Registration</em> action.</p>
     */
    public String create() {

        if (log.isDebugEnabled()) {
            log.debug("create()");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        StringBuffer sb = registration(context);
        sb.append("?action=Create");
        forward(context, sb.toString());
        return (null);

    }


    /**
     * <p>Forward to the <em>Logon</em> page.</p>
     */
    public String logon() {

        if (log.isDebugEnabled()) {
            log.debug("logon()");
        }
        return "logon";

    }


}
