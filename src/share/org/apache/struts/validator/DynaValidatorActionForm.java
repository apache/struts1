/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/validator/DynaValidatorActionForm.java,v 1.13 2004/03/14 06:23:47 sraeburn Exp $
 * $Revision: 1.13 $
 * $Date: 2004/03/14 06:23:47 $
 *
 * Copyright 2000-2004 The Apache Software Foundation.
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

package org.apache.struts.validator;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * <p>This class extends <strong>DynaValidatorForm</strong> and provides
 * basic field validation based on an XML file.  The key passed into the
 * validator is the action element's 'path' attribute from the
 * struts-config.xml which should match the form element's name attribute
 * in the validation.xml.</p>
 *
 * <ul><li>See <code>ValidatorPlugin</code> definition in struts-config.xml
 * for validation rules.</li></ul>
 *
 * @version $Revision: 1.13 $ $Date: 2004/03/14 06:23:47 $
 * @since Struts 1.1
 */
public class DynaValidatorActionForm extends DynaValidatorForm implements DynaBean, Serializable {

    /**
     * Commons Logging instance.
     */
    private static Log log = LogFactory.getLog(DynaValidatorActionForm.class);

    /**
     * Validate the properties that have been set from this HTTP request,
     * and return an <code>ActionErrors</code> object that encapsulates any
     * validation errors that have been found.  If no errors are found, return
     * <code>null</code> or an <code>ActionErrors</code> object with no
     * recorded error messages.
     *
     * @param mapping The mapping used to select this instance.
     * @param request The servlet request we are processing.
     * @return ActionErrors containing validation errors.
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        this.setPageFromDynaProperty();
        
        ServletContext application = getServlet().getServletContext();
        ActionErrors errors = new ActionErrors();

        Validator validator =
            Resources.initValidator(mapping.getPath(), this, application, request, errors, page);

        try {
            validatorResults = validator.validate();
        } catch (ValidatorException e) {
            log.error(e.getMessage(), e);
        }

        return errors;
    }

}
