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

package org.apache.struts.tiles2;

import org.apache.struts.action.Action;
import org.apache.tiles.ComponentContext;
import org.apache.tiles.TilesException;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.context.servlet.ServletTilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;

/**
 * Struts wrapper implementation of Controller.  This implementation wraps an
 * <code>Action</code> in a <code>Controller</code>.
 */
public class ActionPreparer extends ViewPreparerSupport {

    /**
     * Struts action wrapped.
     */
    private Action action = null;

    /**
     * Constructor.
     *
     * @param action Action to be wrapped.
     */
    public ActionPreparer(Action action) {
        this.action = action;
    }

    public void execute(TilesRequestContext tilesContext,
            ComponentContext componentContext) throws Exception {
        if (tilesContext instanceof ServletTilesRequestContext) {
            ServletTilesRequestContext servletTilesContext =
                    (ServletTilesRequestContext) tilesContext;
            this.action.execute(null, null, servletTilesContext.getRequest(),
                    servletTilesContext.getResponse());
        } else {
            throw new TilesException("Not using a ServletTilesRequestContext");
        }
    }
}
