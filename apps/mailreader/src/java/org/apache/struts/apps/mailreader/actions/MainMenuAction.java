package org.apache.struts.apps.mailreader.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.apps.mailreader.dao.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Forward to logon result if user object is missing,
 * otherwise forward to the MainMenu result (success).
 * </p>
 */
public final class MainMenuAction extends BaseAction {

    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        User user = doGetUser(request);
        if (user == null) {
            return doFindLogon(mapping);
        }
        return doFindSuccess(mapping);
    }

}
