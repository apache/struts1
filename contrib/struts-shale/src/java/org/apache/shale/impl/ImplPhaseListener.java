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

package org.apache.shale.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.shale.ViewController;

/**
 * <p>JavaServer Faces <code>PhaseListener</code> that implements phase
 * related functionality.</p>
 *
 * $Id$
 */

public class ImplPhaseListener implements PhaseListener {
    

    // --------------------------------------------------- PhaseListener Methods


    /**
     * <p>Perform after-phase processing for the phase defined in the
     * specified event.</p>
     *
     * @param event <code>PhaseEvent</code> for the current event
     */
    public void afterPhase(PhaseEvent event) {

        PhaseId phaseId = event.getPhaseId();
        if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
            afterRenderResponse(event);
        }

    }

    
    /**
     * <p>Perform before-phase processing for the phase defined in the
     * specified event.</p>
     *
     * @param event <code>PhaseEvent</code> for the current event
     */
    public void beforePhase(PhaseEvent event) {

        PhaseId phaseId = event.getPhaseId();
        if (PhaseId.RENDER_RESPONSE.equals(phaseId)) {
            beforeRenderResponse(event);
        }

    }

    
    /**
     * <p>Return <code>PhaseId.ANY_PHASE</code>, indicating our interest
     * in all phases of the request processing lifecycle.</p>
     */
    public PhaseId getPhaseId() {

        return PhaseId.ANY_PHASE;

    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Call the <code>destroy()</code> method of the {@link ViewController}s
     * that have been registered during this request (if any).</p>
     *
     * @param event <code>PhaseEvent</code> for the current event
     */
    private void afterRenderResponse(PhaseEvent event) {

        Map map = event.getFacesContext().getExternalContext().getRequestMap();
        List list = (List) map.get(ImplConstants.VIEWS_INITIALIZED);
        if (list == null) {
            return;
        }
        Iterator vcs = list.iterator();
        while (vcs.hasNext()) {
            ViewController vc = (ViewController) vcs.next();
            vc.destroy();
        }
        map.remove(ImplConstants.VIEWS_INITIALIZED);

    }



    /**
     * <p>Call the <code>prepare()</code> method of the {@link ViewController}
     * for the view about to be rendered (if any).</p>
     *
     * @param event <code>PhaseEvent</code> for the current event
     */
    private void beforeRenderResponse(PhaseEvent event) {

        Map map = event.getFacesContext().getExternalContext().getRequestMap();
        ViewController vc = (ViewController)
          map.get(ImplConstants.VIEW_RENDERED);
        if (vc == null) {
            return;
        }
        vc.prepare();
        map.remove(ImplConstants.VIEW_RENDERED);

    }


}
