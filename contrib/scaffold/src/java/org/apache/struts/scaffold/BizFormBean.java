/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/BizFormBean.java,v 1.3 2004/03/14 07:15:03 sraeburn Exp $
 * $Revision: 1.3 $
 * $Date: 2004/03/14 07:15:03 $
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


import org.apache.struts.action.ActionFormBean;

/**
 * An <code>ActionFormBean</code> for specifying a <code>BizForm</code>.
 *
 * @version $Revision: 1.3 $ $Date: 2004/03/14 07:15:03 $
 */
public class BizFormBean extends ActionFormBean {

    /**
     * The business request type to create, populate, and validate.
     */
    private String bizType = null;

	// see interface for Javadoc
    public String getBizType() {
        return (this.bizType);
    }

	// see interface for Javadoc
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

} // end BizFormBean