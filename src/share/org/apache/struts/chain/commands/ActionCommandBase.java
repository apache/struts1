/*
 * $Id$
 * 
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

import org.apache.commons.chain.Context;
import org.apache.struts.chain.contexts.ActionContext;

/**
 * <p>Simple abstract class which avoids frequent casting to <code>ActionContext</code>
 * in commands explicitly intended for use with that class.</code>.</p> 
 */
public abstract class ActionCommandBase implements ActionCommand {

    public abstract boolean execute(ActionContext actionContext) throws Exception;

    /**
     * <p>Simply cast the <code>Context</code> to <code>ActionContext</code>
     * and call <code>execute(ActionContext)</code>.</p>
     */
    public boolean execute(Context context) throws Exception {
        return execute((ActionContext) context);
    }

}


