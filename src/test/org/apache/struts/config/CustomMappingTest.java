/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/config/CustomMappingTest.java,v 1.4 2004/03/14 06:23:48 sraeburn Exp $
 * $Revision: 1.4 $
 * $Date: 2004/03/14 06:23:48 $
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

package org.apache.struts.config;

import org.apache.struts.action.ActionMapping;

/**
 *  Custom action mapping used by TestModuleConfing
 *
 */
public class CustomMappingTest extends ActionMapping {
    
    private boolean pub = false;

    public void setPublic(boolean val) {
        this.pub = val;
    }

    public boolean getPublic() {
        return pub;
    }    
}

