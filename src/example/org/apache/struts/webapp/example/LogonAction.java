/*
 * $Header: /home/cvs/jakarta-struts/src/example/org/apache/struts/webapp/example/LogonAction.java,v 1.22 2004/03/12 02:32:41 husted Exp $
 * $Revision: 1.22 $
 * $Date: 2004/03/12 02:32:41 $
 *
 * Copyright 2000-2004 Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.webapp.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * <p>Validate a user logon.</p>
 *
 * @version $Revision: 1.22 $ $Date: 2004/03/12 02:32:41 $
 */
public final class LogonAction extends BaseAction {

    /**
     * Name of username field ["username"].
     */
    private static String USERNAME = "username";

    /**
     * Name of password field ["password"].
     */
    private static String PASSWORD = "password";

    // ------------------------------------------------------ Protected Methods

    /**
     * <p>Confirm user credentials. Post any errors and return User object
     * (or null).</p>
     *
     * @param database Database in which to look up the user
     * @param username Username specified on the logon form
     * @param password Password specified on the logon form
     * @param errors ActionMessages queue to passback errors
     *
     * @return Validated User object or null
     * @throws ExpiredPasswordException to be handled by Struts exception
     * processor via the action-mapping
     */
    protected User getUser(UserDatabase database, String username,
                           String password, ActionMessages errors) throws ExpiredPasswordException {

        User user = null;
        if (database == null){
            errors.add(
                ActionMessages.GLOBAL_MESSAGE,
                new ActionMessage("error.database.missing"));
        }
        else {
            user = database.findUser(username);
            if ((user != null) && !user.getPassword().equals(password)) {
                user = null;
            }
            if (user == null) {
                errors.add(
                    ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage("error.password.mismatch"));
            }
        }

        return user;

    }


    /**
     * <p>Store User object in client session.
     * If user object is null, any existing user object is removed.</p>
     *
     * @param request The request we are processing
     * @param user The user object returned from the database
     */
    protected void SaveUser(HttpServletRequest request, User user) {

        HttpSession session = request.getSession();
        session.setAttribute(Constants.USER_KEY, user);
        if (log.isDebugEnabled()) {
            log.debug(
                "LogonAction: User '"
                    + user.getUsername()
                    + "' logged on in session "
                    + session.getId());
        }

    }

    // --------------------------------------------------------- Public Methods

    /**
     * Use "username" and "password" fields from ActionForm to retrieve a User
     * object from the database. If credentials are not valid, or database
     * has disappeared, post error messages and forward to input.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param form The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception Exception if the application business logic throws
     *  an exception
     */
    public ActionForward execute(
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {

        // Local variables
        UserDatabase database = getUserDatabase(request);
        String username = (String) PropertyUtils.getSimpleProperty(form,
                USERNAME);
        String password = (String) PropertyUtils.getSimpleProperty(form,
                PASSWORD);
        ActionMessages errors = new ActionMessages();

        // Retrieve user
        User user = getUser(database,username,password,errors);

        // Save (or clear) user object
        SaveUser(request,user);

        // Report back any errors, and exit if any
        if (!errors.isEmpty()) {
            this.saveErrors(request, errors);
            return (mapping.getInputForward());
        }

        // Otherwise, return "success"
        return (findSuccess(mapping));

    }

}