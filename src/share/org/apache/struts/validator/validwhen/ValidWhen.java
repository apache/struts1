/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/validator/validwhen/ValidWhen.java,v 1.7 2003/08/23 17:18:39 dgraham Exp $
 * $Revision: 1.7 $
 * $Date: 2003/08/23 17:18:39 $
 *
 * ====================================================================
 *
 *  The Apache Software License, Version 1.1
 *
 *  Copyright (c) 2003 The Apache Software Foundation.  All rights
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

import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;

/**
 * This class contains the validwhen validation that is used in the 
 * validator-rules.xml file.
 *
 * @author James Turner
 * @since Struts 1.2
 */
public class ValidWhen {

    /**
     * Returns true if <code>obj</code> is null or a String.
     */
    private static boolean isString(Object obj) {
        return (obj == null) ? true : String.class.isInstance(obj);
    }

    /**
     * Checks if the field matches the boolean expression specified in 
     * <code>test</code> parameter.
     *
     * @param bean The bean validation is being performed on.
     * 
     * @param va The <code>ValidatorAction</code> that is currently being 
     *      performed.
     * 
     * @param field The <code>Field</code> object associated with the current
     *      field being validated.
     * 
     * @param errors The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     * 
     * @param request Current request object.
     * 
     * @return <code>true</code> if meets stated requirements, 
     *      <code>false</code> otherwise.
     */
    public static boolean validateValidWhen(
        Object bean,
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

            final int leftBracket = key.indexOf("[");
            final int rightBracket = key.indexOf("]");

            if ((leftBracket > -1) && (rightBracket > -1)) {
                index =
                    Integer.parseInt(key.substring(leftBracket + 1, rightBracket));
            }
        }
        
        if (isString(bean)) {
            value = (String) bean;
        } else {
            value = ValidatorUtil.getValueAsString(bean, field.getProperty());
        }
        
        String test = field.getVarValue("test");
        if (test == null) {
            return false;
        }
        
        ValidWhenLexer lexer = new ValidWhenLexer(new StringReader(test));

        ValidWhenParser parser = new ValidWhenParser(lexer);

        parser.setForm(form);
        parser.setIndex(index);
        parser.setValue(value);

        try {
            parser.expression();
            valid = parser.getResult();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
            errors.add(
                field.getKey(),
                Resources.getActionMessage(request, va, field));
                
            return false;
        }
        
        if (!valid) {
            errors.add(
                field.getKey(),
                Resources.getActionMessage(request, va, field));
                
            return false;
        }
        
        return true;
    }
    
}
