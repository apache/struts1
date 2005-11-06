/*
 * Copyright 2005 The Apache Software Foundation.
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
package org.apache.struts.chain.commands;

/**
 *  <p>Exception thrown when no mapping can be found for the specified path.</p>
 *
 * @version $Rev$ $Date$
 */

public class InvalidPathException extends Exception {

    private String path;

    /**  Constructor */
    public InvalidPathException() {
        super();
    }

    /**
     * Constructor.
     *
     * @param  message  The error or warning message.
     * @param  path The invalid path.
     */
    public InvalidPathException(String message, String path) {
        super(message);
        this.path = path;
    }

    /**
     * Return the path.
     */
    public String getPath() {
        return path;
    }

}

