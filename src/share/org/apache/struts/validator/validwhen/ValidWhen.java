/*
 *  The Apache Software License, Version 1.1
 *
 *  Copyright (c) 1999 The Apache Software Foundation.  All rights
 *  reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  3. The end-user documentation included with the redistribution, if
 *  any, must include the following acknowlegement:
 *  "This product includes software developed by the
 *  Apache Software Foundation (http://www.apache.org/)."
 *  Alternately, this acknowlegement may appear in the software itself,
 *  if and wherever such third-party acknowlegements normally appear.
 *
 *  4. The names "The Jakarta Project", "Struts", and "Apache Software
 *  Foundation" must not be used to endorse or promote products derived
 *  from this software without prior written permission. For written
 *  permission, please contact apache@apache.org.
 *
 *  5. Products derived from this software may not be called "Apache"
 *  nor may "Apache" appear in their names without prior written
 *  permission of the Apache Group.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 *  ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 *  USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *  SUCH DAMAGE.
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many
 *  individuals on behalf of the Apache Software Foundation.  For more
 *  information on the Apache Software Foundation, please see
 *  <http://www.apache.org/>.
 */

package org.apache.struts.validator.validwhen;

import org.apache.commons.validator.*;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.validator.Resources;

/**
 *  <p>
 *
 *  This class contains the validwhen validation that is used in the validator-rules.xml
 *  file.</p>
 *
 *
 *@author     James Turner
 *@since      Struts 1.1
 */

public class ValidWhen {
  private static Class stringClass = new String().getClass();

  private static boolean isString(Object o) {
    if (o == null) return true;
    return (stringClass.isInstance(o));
  }

    /**
     *  <p>
     *
     *  Checks if the field matches the boolean expression specified in <code>test</code> parameter</p>
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if meets stated requirements, False otherwise
     */

  public static boolean validateValidWhen(Object bean,
      ValidatorAction va,
      Field field,
      ActionErrors errors,
      Validator validator,
      HttpServletRequest request) {
    Object form = validator.getResource(Validator.BEAN_KEY);
    String value = null;
    boolean valid = false;
    int index = -1;
     if (field.isIndexed()) {
        String key = field.getKey();

        if ((key.indexOf("[") > -1) &&
            (key.indexOf("]") > -1)) {
          index = Integer.parseInt(key.substring(key.indexOf("[")+1, key.indexOf("]")));
        }
      }
    if (isString(bean)) {
      value = (String) bean;
    } else {
      value = ValidatorUtil.getValueAsString(bean, field.getProperty());
    }
    String test = field.getVarValue("test");
    if (test == null) return false;
    try {
	valid = ValidWhenParser.evaluateExpression(test, form, index, value);
    } catch  (ParseException ex) {
	return false;
    }
    if (!valid) {
        errors.add(field.getKey(), Resources.getActionError(request, va, field));
        return false;
    }
    return true;
  }
}
