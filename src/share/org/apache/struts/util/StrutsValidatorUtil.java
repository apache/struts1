/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/StrutsValidatorUtil.java,v 1.10 2003/02/25 04:58:30 dgraham Exp $
 * $Revision: 1.10 $
 * $Date: 2003/02/25 04:58:30 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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


package org.apache.struts.util;

import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;



/**
 * <p>This class helps provides some useful methods for retrieving objects
 * from different scopes of the application.</p>
 *
 * @deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.Resources}
 * @author David Winterfeldt
 * @author Eddie Bush
 * @version $Revision: 1.10 $ $Date: 2003/02/25 04:58:30 $
 * @since Struts 1.1
*/
public class StrutsValidatorUtil  {

   /**
    * Resources key the <code>ServletContext</code> is stored under.
   */
   public static String SERVLET_CONTEXT_KEY = "javax.servlet.ServletContext";

   /**
    * Resources key the <code>HttpServletRequest</code> is stored under.
   */
   public static String HTTP_SERVLET_REQUEST_KEY = "javax.servlet.http.HttpServletRequest";

   /**
    * Resources key the <code>ActionErrors</code> is stored under.
   */
   public static String ACTION_ERRORS_KEY = "org.apache.struts.action.ActionErrors";

   /**
    * Retrieve <code>ValidatorResources</code> for the module.
    *
    * @deprecated In Struts 1.1 This method can only return the resources for the default
    *  module.  Use getValidatorResources(HttpServletRequest, ServletContext)
    *  to get the resources for the current application module.
   */
   public static ValidatorResources getValidatorResources(ServletContext application) {
       return org.apache.struts.validator.Resources.getValidatorResources(application);
   }

  /**
   * Retrieve <code>ValidatorResources</code> for the current module.
   */
   public static ValidatorResources getValidatorResources(ServletContext application,HttpServletRequest request) {
      return org.apache.struts.validator.Resources.getValidatorResources(application,request);
   }

   /**
    * Retrieve <code>MessageResources</code> for the application module.
    *
    * @deprecated This method can only return the resources for the default
    *  module.  Use getMessageResources(HttpServletRequest) to get the
    *  resources for the current application module.
   */
   public static MessageResources getMessageResources(ServletContext application) {
       return org.apache.struts.validator.Resources.getMessageResources(application);
   }

   /**
    * Retrieve <code>MessageResources</code> for the application module.
   */
   public static MessageResources getMessageResources(HttpServletRequest request) {
       return org.apache.struts.validator.Resources.getMessageResources(request);
   }

   /**
    * Get the <code>Locale</code> of the current user.
   */
   public static Locale getLocale(HttpServletRequest request) {
       return org.apache.struts.validator.Resources.getLocale(request);
   }

   /**
    * Gets the <code>Locale</code> sensitive value based on the key passed in.
   */
   public static String getMessage(MessageResources messages, Locale locale, String key) {
       return org.apache.struts.validator.Resources.getMessage(messages,locale,key);
   }

   /**
    * Gets the <code>Locale</code> sensitive value based on the key passed in.
   */
   public static String getMessage(HttpServletRequest request, String key) {
       return org.apache.struts.validator.Resources.getMessage(request,key);
   }

   /**
    * Gets the locale sensitive message based on the <code>ValidatorAction</code> message and the
    * <code>Field</code>'s arg objects.
    *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.Resources#getMessage(MessageResources, Locale, ValidatorAction , Field)}
   */
   public static String getMessage(MessageResources messages, Locale locale,
                                   ValidatorAction va, Field field) {
       return org.apache.struts.validator.Resources.getMessage(messages,locale,va,field);
   }

   /**
    * Gets the <code>ActionError</code> based on the <code>ValidatorAction</code> message and the
    * <code>Field</code>'s arg objects.
    *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.Resources#getActionError(HttpServletRequest, ValidatorAction, Field)}
   */
   public static ActionError getActionError(HttpServletRequest request,
                                            ValidatorAction va, Field field) {

      return org.apache.struts.validator.Resources.getActionError(request,va,field);
   }

   /**
    * Gets the message arguments based on the current <code>ValidatorAction</code>
    * and <code>Field</code>.
    *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.Resources#getArgs(String,MessageResources,Locale,Field)}
   */
   public static String[] getArgs(String actionName, MessageResources messages,
                                  Locale locale, Field field) {

      return org.apache.struts.validator.Resources.getArgs(actionName,messages,locale,field);
   }

   /**
    * Initialize the <code>Validator</code> to perform validation.
    *@deprecated  As of Struts 1.1, replaced by {@link org.apache.struts.validator.Resources#initValidator(String,Object,ServletContext,HttpServletRequest,ActionErrors,int)}
    *
    * @param    key     The key that the validation rules are under
    *               (the form elements name attribute).
    * @param    request     The current request object.
    * @param    errors      The object any errors will be stored in.
   */
   public static Validator initValidator(String key, Object bean,
                                         ServletContext application, HttpServletRequest request,
                                         ActionErrors errors, int page) {

       return org.apache.struts.validator.Resources.initValidator(key,bean,application,request,errors,page);

   }

}
