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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.shale.ViewController;
import org.apache.shale.ViewControllerMapper;

/**
 * <p>Custom implementation of <code>ViewHandler</code> that adds support
 * for on-demand creation and configuration of {@link ViewController}
 * instances.</p>
 * 
 * $Id$
 */

public class ImplViewHandler extends ViewHandler {
    

    // ------------------------------------------------------------- Constructor


    /**
     * <p>Create a {@link ImplViewHandler} instance that decorates the
     * specified <code>ViewHandler</code> provided by the JSF runtime
     * implementation.</p>
     *
     * @param original Original <code>ViewHandler</code> to be decorated
     */
    public ImplViewHandler(ViewHandler original) {
        this.original = original;
        if (log.isDebugEnabled()) {
            log.debug("ImplViewHandler instance is decorating " +
              original.toString());
        }
    }


    // -------------------------------------------------------- Static Variables

    
    /**
     * <p>Log instance for this class.</p>
     */
    private static final Log log = LogFactory.getLog(ImplViewHandler.class);


    // ------------------------------------------------------ Instance Variables

    
    /**
     * <p>Cached {@link ViewControllerMapper} we will use to translate
     * view identifiers to the class name of a {@link ViewController}.</p>
     */
    private ViewControllerMapper mapper = null;


    /**
     * <p>The <code>ViewHandler</code> instance we are decorating.  All requests
     * are delegated to this instance, before or after any special handling that
     * is required.</p>
     */
    private ViewHandler original = null;


    // ----------------------------------------------------- ViewHandler Methods


    // Specified by ViewHandler
    public Locale calculateLocale(FacesContext context) {
        return original.calculateLocale(context);
    }


    // Specified by ViewHandler
    public String calculateRenderKitId(FacesContext context) {
        return original.calculateRenderKitId(context);
    }


    /**
     * <p>After delegating to our original <code>ViewHandler</code>,
     * create and initialize any {@link ViewController} associated with
     * the specified view identifier.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param viewId View identifier of the view to be created
     */
    public UIViewRoot createView(FacesContext context, String viewId) {
        UIViewRoot view = original.createView(context, viewId);
        setupViewController(context, view, false);
        return view;
    }


    // Specified by ViewHandler
    public String getActionURL(FacesContext context, String viewId) {
        return original.getActionURL(context, viewId);
    }

    
    // Specified by ViewHandler
    public String getResourceURL(FacesContext context, String path) {
        return original.getResourceURL(context, path);
    }


    // Specified by ViewHandler
    public void renderView(FacesContext context, UIViewRoot view)
      throws IOException, FacesException {
        original.renderView(context, view);
    }


    /**
     * <p>After delegating to our original <code>ViewHandler</code>,
     * create and initialize any {@link ViewController} associated with
     * the specified view identifier.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param viewId View identifier of the view to be restored
     */
    public UIViewRoot restoreView(FacesContext context, String viewId) {
        UIViewRoot view = original.restoreView(context, viewId);
        setupViewController(context, view, true);
        return view;
    }


    // Specified by ViewHandler
    public void writeState(FacesContext context) throws IOException {
        original.writeState(context);
    }


    // --------------------------------------------------------- Private Methods


    /**
     * <p>Return the {@link ViewControllerMapper} instance we will use to
     * map view identifiers to class names of the corresponding
     * {@link ViewController} class.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     */
    private ViewControllerMapper getViewControllerMapper(FacesContext context) {
        if (mapper != null) {
            Map map = context.getExternalContext().getApplicationMap();
            mapper = (ViewControllerMapper) map.get(ImplConstants.VIEW_MAPPER);
        }
        return mapper;
    }


    /**
     * <p>Create and initialize an appropriate {@link ViewController} instance
     * associated with the specified view, which was just created or just
     * restored.</p>
     *
     * @param context <code>FacesContext</code> for the current request
     * @param view <code>UIViewRoot</code> just created or restored
     *  (or <code>null</code> if there was no such view)
     * @param postBack <code>true</code> if this is a post back to
     *  an existing view
     */
    private void setupViewController(FacesContext context, UIViewRoot view,
                                     boolean postBack) {

        // Is there actually a view for us to procses?
        if (view == null) {
            return;
        }
        String viewId = view.getViewId();
        if (log.isDebugEnabled()) {
            log.debug("setupViewController(" + viewId + ")");
        }

        // Map our view identifier to a corresponding managed bean name
        ViewControllerMapper mapper = getViewControllerMapper(context);
        if (mapper == null) {
            log.warn("No ViewControllerMapper configured for this application");
            return;
        }
        String viewName = mapper.mapViewId(viewId);

        // Retrieve an existing instance, or one created and configured by
        // the managed bean facility
        ValueBinding vb =
            context.getApplication().createValueBinding("#{" + viewName + "}");
        ViewController vc = null;
        try {
            vc = (ViewController) vb.getValue(context);
        } catch (ClassCastException e) {
            log.warn("Bean for viewId '" + viewId + "' under name '" +
              viewName + "' is not a ViewController");
            return;
        }
        if (vc == null) {
            log.warn("No ViewController for viewId '" + viewId +
              "' found under name '" + viewName + "'");
            return;
        }

        // Configure the instance properties and initialize it
        vc.setPostBack(postBack);
        vc.init();

        // Schedule this instance for later processing as needed
        Map map = context.getExternalContext().getRequestMap();
        map.put(ImplConstants.VIEW_RENDERED, vc);
        List list = (List) map.get(ImplConstants.VIEWS_INITIALIZED);
        if (list == null) {
            list = new ArrayList();
            map.put(ImplConstants.VIEWS_INITIALIZED, list);
        }
        list.add(vc);

    }


}
