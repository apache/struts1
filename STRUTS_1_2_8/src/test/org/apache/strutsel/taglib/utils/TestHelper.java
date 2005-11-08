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

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class TestHelper {
    private static Log log = LogFactory.getLog(TestHelper.class);

    public static void printResponse(com.meterware.httpunit.WebResponse testResponse)
                              throws IOException {
        log.debug("response text[" + testResponse.getText() + "]");
    }

    public static String getAttrErrMess(String  tagName,
                                        String  attrName,
                                        String  requiredValue,
                                        String  actualValue)
    {
        return ("The <" + tagName + "> \"" + attrName + "\" attribute " +
                "should have had value \"" + requiredValue + "\", but it " +
                "instead had value \"" + actualValue + "\".");
    }
}
