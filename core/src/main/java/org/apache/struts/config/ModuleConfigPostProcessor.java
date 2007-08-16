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
package org.apache.struts.config;

/**
 * This interface is to be implemented by any plugin for custom modification of
 * a module after it is been configured but before it is frozen. This allows for
 * overriding or adding properties after standard initialization.
 * 
 * @see ModuleConfig
 * @see ModuleConfig#freeze()
 * @since Struts 1.4
 * @version $Rev$
 */
public interface ModuleConfigPostProcessor {

    /**
     * Modify the specified module after its standard initialization.
     * 
     * @param config the module
     */
    void postProcessModule(ModuleConfig config);

}
