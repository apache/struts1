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


/**
 * <p>Simple JavaBean for use in managed bean and value binding tests.</p>
 */

public class SimpleBean {


    public String getEternity() {
        return "Eternity";
    }

    public String getSimpleResource() {
        return "resource.simple";
    }

    public String getSimpleContent() {
        return "Retrieved Simple Content";
    }


    public String getFilteredContent() {
        return "Retrieved <b>Filtered</b> Content";
    }


    public String getUnfilteredContent() {
        return "Retrieved <b>Unfiltered</b> Content";
    }


}
