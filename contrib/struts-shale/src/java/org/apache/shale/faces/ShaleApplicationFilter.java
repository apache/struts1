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

package org.apache.shale.faces;

import java.io.IOException;

import javax.faces.FactoryFinder;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shale.view.DefaultViewControllerMapper;

/**
 * <p>{@link ShaleApplicationFilter} is a <code>Filter</code> implementation
 * that invokes the required <em>Application Controller</em> functionality on
 * every request.
 * In addition, it performs overall application startup and shutdown
 * operations on behalf of the framework.</p>
 *
 * $Id$
 */

public class ShaleApplicationFilter implements Filter {


    // ------------------------------------------------------ Instance Variables


    /**
     * <p>Filter configuration object for this <code>Filter</code>.</p>
     */
    private FilterConfig config = null;


    /**
     * <p>The <code>ServletContext</code> instance for our web application.</p>
     */
    private ServletContext context = null;


    /**
     * <p>The JSF <code>PhaseListener</code> that we have registered.</p>
     */
    private PhaseListener phaseListener = null;


    // ---------------------------------------------------------- Filter Methods


    /**
     * <p>Perform application shutdown finalization as necessary.</p>
     */
    public void destroy() {

        config = null;
        context = null;
        if (phaseListener != null) {
            getLifecycle().removePhaseListener(phaseListener);
        }
        phaseListener = null;

    }
    

    /**
     * <p>Perform per-request application controler functionality.</p>
     *
     * @param request The request we are processing
     * @param response The response we are creating
     * @param chain The filter chain for this request
     */
    public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

        ; // FIXME - do any required preprocessing
        chain.doFilter(request, response);
        ; // FIXME - do any required postprocessing

    }


    /**
     * <p>Perform application startup intiialization as necessary.</p>
     *
     * @param config <code>FilterConfig</code> for this filter
     */
    public void init(FilterConfig config) throws ServletException {

        this.config = config;
        context = config.getServletContext();
        phaseListener = new ShalePhaseListener();
        getLifecycle().addPhaseListener(phaseListener);
        // FIXME - make the mapper pluggable
        context.setAttribute(ShaleConstants.VIEW_MAPPER,
          new DefaultViewControllerMapper());

    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Return the JSF <code>Lifecycle</code> instance for this
     * web application.</p>
     */
    private Lifecycle getLifecycle() {

        String lifecycleId =
          context.getInitParameter("javax.faces.LIFECYCLE_ID");
        if (lifecycleId == null) {
            lifecycleId = LifecycleFactory.DEFAULT_LIFECYCLE;
        }
        LifecycleFactory factory = (LifecycleFactory)
          FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        return factory.getLifecycle(lifecycleId);

    }


}
