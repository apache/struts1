/*
 * Copyright 2004 The Apache Software Foundation.
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

package org.apache.shale.view;

import org.apache.shale.ViewControllerMapper;

/**
 * <p>Default implementation of {@link ViewControllerMapper}.  The following
 * algorithm is implemented:</p>
 * <ul>
 * <li>Strip any leading slash ("/") character.</li>
 * <li>Strip any traling extension (".xxx"), as long as it occurs
 *     after any remaining slash ("/") character.</li>
 * <li>Convert each instance of a slash ("/") or period (".")
 *     character into an underscore ("_") character.</li>
 * </ul>
 *
 * $Id$
 */

public class DefaultViewControllerMapper implements ViewControllerMapper {
    

    // ---------------------------------------------------------- Public Methods


    // Specified by ViewControllerMapper
    public String mapViewId(String viewId) {
        
        if (viewId == null) {
            return null;
        }
        if (viewId.startsWith("/")) {
            viewId = viewId.substring(1);
        }
        int slash = viewId.lastIndexOf("/");
        int period = viewId.lastIndexOf(".");
        if ((period >= 0) && (period > slash)) {
            viewId = viewId.substring(0, period);
        }
        return viewId.replace('/', '_').replace('.', '_');

    }


}
