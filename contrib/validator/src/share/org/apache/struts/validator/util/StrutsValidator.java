/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
 
 
package org.apache.struts.validator.util;

import java.io.Serializable;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogSource;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.regexp.RESyntaxException;
import org.apache.struts.action.ActionErrors;


/**
 * <p>This class performs validations.  The methods are can be configured to be 
 * used in the framework in the validation.xml file.  (See example webapp)</p>
 *
 * @author David Winterfeldt
*/
public class StrutsValidator implements Serializable {

    /**
     * Commons Logging instance.
    */
    private static Log LOG = LogSource.getInstance(StrutsValidator.class.getName());
    
    /**
     * <p>Checks if the field isn't null and length of the field is greater than zero not 
     * including whitespace.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateRequired(Object bean, 
			                   ValidatorAction va, Field field, 
			                   ActionErrors errors, 
                                           HttpServletRequest request, ServletContext application) {

       String value = null;
       if (field.getProperty() != null && field.getProperty().length() > 0)
       	  value = ValidatorUtil.getValueAsString(bean, field.getProperty());

       if (GenericValidator.isBlankOrNull(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));

          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field matches the regular expression in the field's mask attribute.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateMask(Object bean, 
			               ValidatorAction va, Field field, 
			               ActionErrors errors, 
                                       HttpServletRequest request, ServletContext application) {
         
         String mask = field.getVarValue("mask");
	 
	 if (field.getProperty() != null && field.getProperty().length() > 0) {
	    String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
            
            try {
	       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.matchRegexp(value, mask)) {
	          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
            
                  return false;
               } else {
                  return true;	
               }
	    } catch (RESyntaxException e) {
	       LOG.error(e.getMessage(), e);
	    } catch (Exception e) {
	       LOG.error(e.getMessage(), e);
	    }
         }
         
         return true;
    }        
    

    /**
     * <p>Checks if the field can safely be converted to a byte primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateByte(Object bean, 
			     ValidatorAction va, Field field, 
			     ActionErrors errors, 
                             HttpServletRequest request, ServletContext application) {
       
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());

       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isByte(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field can safely be converted to a short primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateShort(Object bean, 
			      ValidatorAction va, Field field, 
			      ActionErrors errors, 
                              HttpServletRequest request, ServletContext application) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isShort(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field can safely be converted to an int primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateInteger(Object bean, 
			        ValidatorAction va, Field field, 
			        ActionErrors errors, 
                                HttpServletRequest request, ServletContext application) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isInt(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field can safely be converted to a long primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */   
    public static boolean validateLong(Object bean, 
			     ValidatorAction va, Field field, 
			     ActionErrors errors, 
                             HttpServletRequest request, ServletContext application) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isLong(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field can safely be converted to a float primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateFloat(Object bean, 
			      ValidatorAction va, Field field, 
			      ActionErrors errors, 
                              HttpServletRequest request, ServletContext application) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isFloat(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field can safely be converted to a double primitive.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateDouble(Object bean, 
			       ValidatorAction va, Field field, 
			       ActionErrors errors, 
                               HttpServletRequest request, ServletContext application) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isDouble(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
          return true;	
       }
    }

    /**
     * <p>Checks if the field is a valid date.  If the field has a datePattern variable, 
     * that will be used to format <code>java.text.SimpleDateFormat</code>.  If the field 
     * has a datePatternStrict variable, that will be used to format 
     * <code>java.text.SimpleDateFormat</code> and the length will be checked so '2/12/1999' 
     * will not pass validation with the format 'MM/dd/yyyy' because the month isn't two digits.
     * If no datePattern variable is specified, then the field gets the DateFormat.SHORT 
     * format for the locale.  The setLenient method is set to <code>false</code> for all 
     * variations.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateDate(Object bean, 
			     ValidatorAction va, Field field, 
			     ActionErrors errors, 
                             HttpServletRequest request, ServletContext application) {

	boolean bValid = true;

        String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
        String datePattern = field.getVarValue("datePattern");
        String datePatternStrict = field.getVarValue("datePatternStrict");
        Locale locale = StrutsValidatorUtil.getLocale(request);

	if (!GenericValidator.isBlankOrNull(value)) {
	   try {
              if (datePattern != null && datePattern.length() > 0) {
                 bValid = GenericValidator.isDate(value, datePattern, false);
              } else if (datePatternStrict != null && datePatternStrict.length() > 0) {
                 bValid = GenericValidator.isDate(value, datePatternStrict, true);
	      } else {
	         bValid = GenericValidator.isDate(value, locale);
              }
    	   } catch (Exception e) {
    	      bValid = false;
           }
        }

        if (!bValid)
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
        
        return bValid;
    }	




    /**
     * <p>Checks if a fields value is within a range (min &amp; max specified 
     * in the vars attribute).</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateRange(Object bean, 
			      ValidatorAction va, Field field, 
			      ActionErrors errors, 
                              HttpServletRequest request, ServletContext application) {
       
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       String sMin = field.getVarValue("min");
       String sMax = field.getVarValue("max");

       if (!GenericValidator.isBlankOrNull(value)) {
          try {
             int iValue = Integer.parseInt(value);
             int min = Integer.parseInt(sMin);
             int max = Integer.parseInt(sMax);
             
             if (!GenericValidator.isInRange(iValue, min, max)) {
                errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
                
                return false;
             }
          } catch (Exception e) {
             errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
             return false;
          }
       }
       
       return true;
    }

    /**
     * <p>Checks if the field is a valid credit card number.</p>
     * <p>Translated to Java by Ted Husted (<a href="mailto:husted@apache.org">husted@apache.org</a>).<br>
     * &nbsp;&nbsp;&nbsp; Reference Sean M. Burke's script at http://www.ling.nwu.edu/~sburke/pub/luhn_lib.pl</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateCreditCard(Object bean, 
			           ValidatorAction va, Field field, 
			           ActionErrors errors, 
                                   HttpServletRequest request, ServletContext application) {
 
       if (field.getProperty() != null && field.getProperty().length() > 0) {
          String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
          if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isCreditCard(value)) {
             errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
            
             return false;
          } else {
             return true;	
          }
       } else {
          return true;
       }
    }

    /**
     * <p>Checks if a field has a valid e-mail address.</p>
     * <p>Based on a script by Sandeep V. Tamhankar (stamhankar@hotmail.com), 
     * http://javascript.internet.com</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateEmail(Object bean, 
			      ValidatorAction va, Field field, 
			      ActionErrors errors, 
                              HttpServletRequest request, ServletContext application) {
       
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       
       if (!GenericValidator.isBlankOrNull(value) && !GenericValidator.isEmail(value)) {
          errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
          return false;
       } else {
       	  return true;
       }
    }

    /**
     * <p>Checks if the field's length is less than or equal to the maximum value.  A <code>Null</code> 
     * will be considered an error.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateMaxLength(Object bean, 
			                    ValidatorAction va, Field field, 
			                    ActionErrors errors, 
                                            HttpServletRequest request, ServletContext application) {

       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       String sMaxLength = field.getVarValue("maxlength");

       if (value != null) {
          try {
             int max = Integer.parseInt(sMaxLength);
             
             if (!GenericValidator.maxLength(value, max)) {
                errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
                
                return false;
             }
          } catch (Exception e) {
             errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
             return false;
          }
       }
       
       return true;
    }


    /**
     * <p>Checks if the field's length is greater than or equal to the minimum value.  
     * A <code>Null</code> will be considered an error.</p>
     *
     * @param 	bean 		The bean validation is being performed on.
     * @param 	va 		The <code>ValidatorAction</code> that is currently being performed.
     * @param 	field 		The <code>Field</code> object associated with the current field 
     *				being validated.
     * @param 	errors	 	The <code>ActionErrors</code> object to add errors to if any 
     *                          validation errors occur.
     * @param 	request         Current request object.
     * @param 	application     The application's <code>ServletContext</code>.
    */
    public static boolean validateMinLength(Object bean, 
			                    ValidatorAction va, Field field, 
			                    ActionErrors errors, 
                                            HttpServletRequest request, ServletContext application) {

       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());
       String sMinLength = field.getVarValue("minlength");

       if (value != null) {
          try {
             int min = Integer.parseInt(sMinLength);
             
             if (!GenericValidator.minLength(value, min)) {
                errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
                
                return false;
             }
          } catch (Exception e) {
             errors.add(field.getKey(), StrutsValidatorUtil.getActionError(application, request, va, field));
             return false;
          }
       }
       
       return true;
    }

}
