/*
 * Copyright 2003,2004 The Apache Software Foundation.
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

package org.apache.struts.chain.servlet;


import javax.servlet.http.HttpServletResponse;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.web.servlet.ServletWebContext;
import org.apache.struts.chain.AbstractSetContentType;


/**
 * <p>Check to see if the content type is set, and if so, set it for this
 * response.</p>
 *
 * @author Don Brown
 * @version $Revision: 1.3 $ $Date: 2004/06/24 01:27:31 $
 */

public class SetContentType extends AbstractSetContentType {


    // ------------------------------------------------------- Protected Methods


    protected void setContentType(Context context, String contentType) {

        ServletWebContext swcontext = (ServletWebContext) context;
        HttpServletResponse response = swcontext.getResponse();
        
        response.setContentType(contentType);

    }


}
