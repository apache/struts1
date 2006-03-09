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

import java.util.HashMap;
import org.apache.cactus.JspTestCase;

public class JspTagTestCase extends JspTestCase
{
    public JspTagTestCase(String theName) {
        super(theName);
    }

    protected void
        checkAttrValue(HashMap                              attrMap,
                       com.meterware.httpunit.WebResponse   testResponse,
                       String                               headerKey,
                       String                               tagName,
                       String                               attrName)
    {
        String attrValue         = (String)attrMap.get(attrName);
        String requiredAttrValue =
            (String)testResponse.getHeaderField(headerKey);

        if (!attrValue.equals(requiredAttrValue)) {
            fail(TestHelper.getAttrErrMess(tagName, attrName,
                                           requiredAttrValue, attrValue));
        }
    }
}
