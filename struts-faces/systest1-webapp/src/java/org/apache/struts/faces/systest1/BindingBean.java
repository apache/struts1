/*
 * Copyright 2002,2004 The Apache Software Foundation.
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
 *
 * $Id$
 */

package org.apache.struts.faces.systest1;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


/**
 * <p>Bean used to test the <code>binding</code> attribute for
 * selected components.</p>
 */

public class BindingBean {


    private UIComponent write1 = null;
    public UIComponent getWrite1() { return this.write1; }
    public void setWrite1(UIComponent write1) { this.write1 = write1; }


    public String getWrite1ClientId() {
        return write1.getClientId(FacesContext.getCurrentInstance());
    }


}
