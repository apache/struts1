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
package org.apache.strutsel.taglib.utils;

import org.apache.struts.action.ActionForm;

public class TestFormBean extends ActionForm {
    private String stringProperty;
    private Object[] arrayProperty;

    public String getStringProperty() {
        return (stringProperty);
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public Object[] getArrayProperty() {
        return (arrayProperty);
    }

    public void setArrayProperty(Object[] arrayProperty) {
        this.arrayProperty = arrayProperty;
    }
}
