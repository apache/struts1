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
package org.apache.struts.chain.contexts;


import java.util.Map;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.apache.commons.chain.web.WebContext;

/**
 * Subclass of ActionContextBase which is understood to be wrapping 
 * an instance of <code>org.apache.commons.chain.web.WebContext</code>.
 */
public class WebActionContext extends ActionContextBase {

    public WebActionContext(WebContext context) {
        super(context);
    }

    protected WebContext wcontext() {
        return (WebContext) this.getBaseContext();
    }

    public void release() {
        super.release();
    }
    
    public Map getApplicationScope()
    {
        return wcontext().getApplicationScope();
    }

    public Map getHeader()
    {
        return wcontext().getHeader();
    }

    public Map getHeaderValues()
    {
        return wcontext().getHeaderValues();
    }

    public Map getInitParam()
    {
        return wcontext().getInitParam();
    }

    public Map getParam()
    {
        return wcontext().getParam();
    }

    public Map getParamValues()
    {
        return wcontext().getParamValues();
    }

    public Map getRequestScope()
    {
        return wcontext().getRequestScope();
    }

    /**
     * <p>Return the map returned by our nested <code>WebContext</code>'s 
     * <code>getParamValues()</code> method. </p>
     */
    public Map getParameterMap()
    {
        return getParamValues();
    }
    
    public Map getSessionScope()
    {
        return wcontext().getSessionScope();
    }

    /*
     * @todo AbstractSelectModule set the precedent of doing this at the "web context" level
     * instead of the ServletWebContext level.  Consider whether that's how we want to do it
     * universally for other manipulations of the RequestScope or not...
     */
    public void setModuleConfig(ModuleConfig moduleConfig) {
        super.setModuleConfig(moduleConfig);
        this.getRequestScope().put(Globals.MODULE_KEY, moduleConfig);
    }

    /*
     *  (non-Javadoc)
     * Should thi
     * @see org.apache.struts.chain.contexts.ActionContext#getModuleConfig()
     */
    public ModuleConfig getModuleConfig() {
        ModuleConfig mc = super.getModuleConfig();
        if (mc == null) {
            mc = (ModuleConfig) this.getRequestScope().get(Globals.MODULE_KEY);
        }
        return mc;
    }
    
    /*
     * @todo AbstractSelectModule set the precedent of doing this at the "web context" level
     * instead of the ServletWebContext level.  Consider whether that's how we want to do it
     * universally for other manipulations of the RequestScope or not...
     */
    public void setResources(MessageResources messageResources) {
        super.setMessageResources(messageResources);
        this.getRequestScope().put(Globals.MESSAGES_KEY, messageResources);

    }
    
    /*
     * @todo AbstractSelectModule set the precedent of doing this at the "web context" level
     * instead of the ServletWebContext level.  Consider whether that's how we want to do it
     * universally for other manipulations of the RequestScope or not...
     */
    public void setCancelled(Boolean cancelled) {
        super.setCancelled(cancelled);
        // historic semantics of "isCancelled" are to consider any non-null
        // value in the request under Globals.CANCEL_KEY as "yes, this was cancelled."
        if (cancelled != null && cancelled.booleanValue()) {
            this.getRequestScope().put(Globals.CANCEL_KEY, cancelled);
        } else {
            this.getRequestScope().remove(Globals.CANCEL_KEY);
        }

    }

     
    public Boolean getCancelled() {
        Boolean c = super.getCancelled();
        if (c == null) {
            c = (Boolean) this.getRequestScope().get(Globals.CANCEL_KEY);
        }
        return c;
    }
}