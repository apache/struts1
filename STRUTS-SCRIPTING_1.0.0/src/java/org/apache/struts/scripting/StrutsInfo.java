package org.apache.struts.scripting;

// struts imports:
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;


/**
 *  Holds Struts objects
 */
public class StrutsInfo {
    private String forwardName = null;
    private ActionForward forward = null;
    private ActionForm form = null;
    private ActionMapping mapping = null;
    private ScriptAction action = null;
    private MessageResources res = null;

    /**
     *  Constructor
     *
     * @param action The action instance
     * @param mapping The action mapping
     * @param form  The action form
     * @param res   The message resources for the current locale
     */
    public StrutsInfo(ScriptAction action, ActionMapping mapping,
        ActionForm form, MessageResources res) {
        this.action = action;
        this.mapping = mapping;
        this.form = form;
        this.res = res;
    }

    /**
     *  Sets the forward name
     *
     * @param f The forward name
     */
    public void setForwardName(String f) {
        forwardName = f;
    }

    /**
     *  Gets the forward object.  If none is set, it tries to find the set
     *  forward name.
     *
     * @return The action forward
     */
    public ActionForward getForward() {
        if (forward == null) {
            if (forwardName != null) {
                return mapping.findForward(forwardName);
            }
        }
        return forward;
    }

    /**
     *  Sets the action forward object
     *
     * @param f The action forward
     */
    public void setForward(ActionForward f) {
        forward = f;
    }

    /**
     *  Sets the action form
     *
     * @param form The action form
     */
    public void setForm(ActionForm form) {
        this.form = form;
    }

    /**
     *  Sets the action mapping
     *
     * @param mapping The action mapping
     */
    public void setMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    /**
     *  Sets the action instance
     *
     * @param action The Struts action
     */
    public void setAction(ScriptAction action) {
        this.action = action;
    }

    /**
     *  Sets the message resources
     *
     * @param res The message resources
     */
    public void setMessages(MessageResources res) {
        this.res = res;
    }

    /**
     *  Gets the action form
     *
     * @return The action form
     */
    public ActionForm getForm() {
        return form;
    }

    /**
     *  Gets the action mapping
     *
     * @return The action mapping
     */
    public ActionMapping getMapping() {
        return mapping;
    }

    /**
     *  Gets the action instance
     *
     * @return The Struts action
     */
    public ScriptAction getAction() {
        return action;
    }

    /**
     *  Gets the message resources
     *
     * @return The message resources
     */
    public MessageResources getMessages() {
        return res;
    }
}

