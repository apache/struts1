/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.struts.tiles2.util;

import java.util.Enumeration;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.struts.config.PlugInConfig;

/**
 * Adapts a {@link PlugInConfig} object to become a context-like object,
 * exposing init parameters methods.
 */
public class PlugInConfigContextAdapter {

    /**
     * The internal plugin config object.
     */
    private PlugInConfig plugInConfigObject;

    /**
     * Constructor.
     *
     * @param plugInConfigObject The plugin config object to use.
     */
    public PlugInConfigContextAdapter(PlugInConfig plugInConfigObject) {
        this.plugInConfigObject = plugInConfigObject;
    }

    /**
     * Returns an initialization parameter.
     *
     * @param parameterName The name of the parameter.
     * @return The value of the parameter.
     */
    public String getInitParameter(String parameterName) {
        return plugInConfigObject.getProperties().get(parameterName).toString();
    }

    /**
     * Returns the names of all initialization parameters.
     *
     * @return The names of all initialization parameters.
     */
    public Enumeration getInitParameterNames() {
        return new IteratorEnumeration(plugInConfigObject.getProperties()
                .keySet().iterator());
    }
}