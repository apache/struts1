/*
 *  The Apache Software License, Version 1.1
 *
 *  Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.util;

import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.apache.struts.action.ActionErrors;

/**
 *  <p>
 *
 *  This class contains the default validations that are used in the validator-rules.xml
 *  file.</p>
 *
 * In general passin in a null or blank will return a null Object or a false
 * boolean. However, nulls and blanks do not result in an error being added to the
 * errors.
 *@deprecated  As of Struts 1.1, replaced by    {@link org.apache.struts.validator.FieldChecks }
 *
 *@author     David Winterfeldt
 *@author     James Turner

 *@since      Struts 1.1
 */
public class StrutsValidator implements Serializable {

    /**
     *  Commons Logging instance.
     */
    public final static String FIELD_TEST_NULL = "NULL";
    public final static String FIELD_TEST_NOTNULL = "NOTNULL";
    public final static String FIELD_TEST_EQUAL = "EQUAL";


    /**
     *  <p>
     *
     *  Checks if the field isn't null and length of the field is greater than zero
     *  not including whitespace.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateRequired(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
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
    public static boolean validateRequired(Object bean,
                                           ValidatorAction va, Field field,
                                           ActionErrors errors,
                                           HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateRequired(bean,va,field,errors,request);
    }



    /**
     *  <p>
     *
     *  Checks if the field matches the regular expression in the field's mask attribute.
     *  </p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateMask(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if field matches mask, false otherwise.
     */
    public static boolean validateMask(Object bean,
                                       ValidatorAction va, Field field,
                                       ActionErrors errors,
                                       HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateMask(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to a byte primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateByte(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Byte if valid, a null otherwise.
     */
    public static Byte validateByte(Object bean,
                                    ValidatorAction va, Field field,
                                    ActionErrors errors,
                                    HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateByte(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to a short primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateShort(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Short if valid, otherwise a null.
     */
    public static Short validateShort(Object bean,
                                      ValidatorAction va, Field field,
                                      ActionErrors errors,
                                      HttpServletRequest request) {
        return org.apache.struts.validator.FieldChecks.validateShort(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to an int primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateInteger(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          An Integer if valid, a null otherwise.
     */
    public static Integer validateInteger(Object bean,
                                          ValidatorAction va, Field field,
                                          ActionErrors errors,
                                          HttpServletRequest request) {
       return org.apache.struts.validator.FieldChecks.validateInteger(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to a long primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateLong(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Long if valid, a null otherwise.
     */
    public static Long validateLong(Object bean,
                                    ValidatorAction va, Field field,
                                    ActionErrors errors,
                                    HttpServletRequest request) {
        return org.apache.struts.validator.FieldChecks.validateLong(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to a float primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateFloat(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Float if valid, a null otherwise.
     */
    public static Float validateFloat(Object bean,
                                      ValidatorAction va, Field field,
                                      ActionErrors errors,
                                      HttpServletRequest request) {
       return org.apache.struts.validator.FieldChecks.validateFloat(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field can safely be converted to a double primitive.</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateDouble(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Double if valid, a null otherwise.
     */
    public static Double validateDouble(Object bean,
                                        ValidatorAction va, Field field,
                                        ActionErrors errors,
                                        HttpServletRequest request) {
       return org.apache.struts.validator.FieldChecks.validateDouble(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field is a valid date. If the field has a datePattern variable,
     *  that will be used to format <code>java.text.SimpleDateFormat</code>. If the
     *  field has a datePatternStrict variable, that will be used to format <code>java.text.SimpleDateFormat</code>
     *  and the length will be checked so '2/12/1999' will not pass validation with
     *  the format 'MM/dd/yyyy' because the month isn't two digits. If no datePattern
     *  variable is specified, then the field gets the DateFormat.SHORT format for
     *  the locale. The setLenient method is set to <code>false</code> for all variations.
     *  </p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateDate(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          A Date if valid, a null if blank or invalid.
     */
    public static Date validateDate(Object bean,
                                    ValidatorAction va, Field field,
                                    ActionErrors errors,
                                    HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateDate(bean,va,field,errors,request);
    }

    /**
     *  <p>
     *
     *  Checks if a fields value is within a range (min &amp; max specified in the
     *  vars attribute).</p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateIntRange(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if in range, false otherwise.
     */
    public static boolean validateRange(Object bean,
                                        ValidatorAction va, Field field,
                                        ActionErrors errors,
                                        HttpServletRequest request) {
        return org.apache.struts.validator.FieldChecks.validateIntRange(bean, va, field, errors, request);
    }


    /**
     *  <p>
     *
     *  Checks if the field is a valid credit card number.</p> <p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateCreditCard(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *  Translated to Java by Ted Husted (<a href="mailto:husted@apache.org">husted@apache.org
     *  </a>).<br>
     *  &nbsp;&nbsp;&nbsp; Reference Sean M. Burke's script at http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl
     *  </p>
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          The credit card as a Long, a null if invalid, blank, or null.
     */
    public static Long validateCreditCard(Object bean,
                                          ValidatorAction va, Field field,
                                          ActionErrors errors,
                                          HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateCreditCard(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if a field has a valid e-mail address.</p> <p>
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateEmail(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *
     *  Based on a script by Sandeep V. Tamhankar (stamhankar@hotmail.com), http://javascript.internet.com
     *  </p>
     *
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if valid, false otherwise.
     */
    public static boolean validateEmail(Object bean,
                                        ValidatorAction va, Field field,
                                        ActionErrors errors,
                                        HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateEmail(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field's length is less than or equal to the maximum value.
     *  A <code>Null</code> will be considered an error.</p>
     *
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateMaxLength(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if stated conditions met.
     */
    public static boolean validateMaxLength(Object bean,
                                            ValidatorAction va, Field field,
                                            ActionErrors errors,
                                            HttpServletRequest request) {

       return org.apache.struts.validator.FieldChecks.validateMaxLength(bean,va,field,errors,request);
    }


    /**
     *  <p>
     *
     *  Checks if the field's length is greater than or equal to the minimum value.
     *  A <code>Null</code> will be considered an error.</p>
     *
     *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.FieldChecks#validateMinLength(Object,ValidatorAction,Field,ActionErrors,HttpServletRequest)}
     *@param  bean     The bean validation is being performed on.
     *@param  va       The <code>ValidatorAction</code> that is currently being performed.
     *@param  field    The <code>Field</code> object associated with the current
     *      field being validated.
     *@param  errors   The <code>ActionErrors</code> object to add errors to if any
     *      validation errors occur.
     *@param  request  Current request object.
     *@return          True if stated conditions met.
     */
    public static boolean validateMinLength(Object bean,
                                            ValidatorAction va, Field field,
                                            ActionErrors errors,
                                            HttpServletRequest request) {

        return org.apache.struts.validator.FieldChecks.validateMinLength(bean,va,field,errors,request);
    }

}
