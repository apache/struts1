/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.struts.chain.commands.servlet;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.Constants;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ModuleConfig;

/**
 * <p>Concrete implementation of <code>org.apache.struts.chain.commands.CreateAction</code> 
 * for use in a Servlet API chain.  Expects that the ActionContext passed into it
 * can safely be cast to <code>ServletActionContext</code>.</p>
 */
public class CreateAction extends org.apache.struts.chain.commands.AbstractCreateAction {


    // ------------------------------------------------------ Instance Variables
    private static final Log log = LogFactory.getLog(CreateAction.class);


    /* (non-Javadoc)
     * @see org.apache.struts.chain.commands.CreateAction#getAction(org.apache.commons.chain.Context, java.lang.String, org.apache.struts.config.ActionConfig)
     */
    protected synchronized Action getAction(ActionContext context, String type,
            ActionConfig actionConfig) throws Exception  {

        ModuleConfig moduleConfig = actionConfig.getModuleConfig();
        ServletActionContext
        saContext = (ServletActionContext) context;
        String actionsKey = Constants.ACTIONS_KEY + moduleConfig.getPrefix();
        Map actions = (Map) saContext.getApplicationScope().get(actionsKey);
        if (actions == null) {
            actions = new HashMap();
            saContext.getApplicationScope().put(actionsKey, actions);
        }

        Action action = null;

        synchronized (actions) {
            action = (Action) actions.get(type);
            if (action == null) {
                log.info("Initialize action of type: " + type);
                action = (Action) ClassUtils.getApplicationInstance(type);
                ActionServlet actionServlet = saContext.getActionServlet();
                action.setServlet(actionServlet);
                actions.put(type, action);
            }
        }


        return (action);

    }


}
