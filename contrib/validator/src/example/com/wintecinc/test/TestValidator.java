/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
 *
 */
package com.wintecinc.test;

import java.util.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.PageContext;
import java.net.*;
import java.io.*;

import junit.framework.*;

import org.apache.commons.cactus.*;
import org.apache.commons.cactus.util.*;

import org.apache.struts.action.ActionErrors;

import com.wintecinc.struts.validation.*;
import com.wintecinc.struts.example.validator.*;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.logic.EqualTag</code> class.
 *
 * @author David Winterfeldt
 */
public class TestValidator extends ServletTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestValidator(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestValidator.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestValidator.class);
    }

    //----- Test initApplication() method --------------------------------------

    /**
     * Create a <code>RegistrationForm</code> and validate it.
    */
    public void testRegistrationFormPass() throws ServletException,  javax.servlet.jsp.JspException {
	ServletContext application = config.getServletContext();
        ActionErrors errors = new ActionErrors();
	
        RegistrationForm bean = new RegistrationForm();
        bean.setFirstName("Joe");
        bean.setLastName("Smith");
        bean.setAddr("101 W. 4th St.");
        bean.getCityStateZip().setCity("Fairport");
        bean.getCityStateZip().setStateProv("NY");
        bean.getCityStateZip().setZipPostal(1, "10010");
        bean.setPhone("(212) 555-1212");
        bean.setEmail("js@xxx.com");

        runFormTest(application, errors, "registrationForm", bean);
        	
	assertEquals("Validation of the RegistrationForm", 0, errors.size());
    }

    /**
     * Create a <code>RegistrationForm</code> and validate it.
    */
    public void testRegistrationFormFail() throws ServletException,  javax.servlet.jsp.JspException {
	ServletContext application = config.getServletContext();
        ActionErrors errors = new ActionErrors();
	
        RegistrationForm bean = new RegistrationForm();
        bean.setFirstName("Joe");
        bean.setLastName("Smith");
        // Addr is required
        bean.setAddr("");
        bean.getCityStateZip().setCity("Fairport");
        bean.getCityStateZip().setStateProv("NY");
        bean.getCityStateZip().setZipPostal(1, "10010");
        bean.setPhone("");
        // E-mail is required
        bean.setEmail("");

        runFormTest(application, errors, "registrationForm", bean);	
        
	assertEquals("Validation of the RegistrationForm", 2, errors.size());
    }

    /**
     * Create a <code>RegistrationForm</code> and validate it.
    */
    public void testRegistrationFormFail2() throws ServletException,  javax.servlet.jsp.JspException {
	ServletContext application = config.getServletContext();
        ActionErrors errors = new ActionErrors();

        RegistrationForm bean = new RegistrationForm();
        bean.setFirstName("Joe");
        // Has a space in it
        bean.setLastName("Smith Jr.");
        bean.setAddr("101 W. 4th St.");
        bean.getCityStateZip().setCity("Fairport");
        bean.getCityStateZip().setStateProv("NY");
        bean.getCityStateZip().setZipPostal(1, "10010");
        // Has x at the end
        bean.setPhone("(212)555-1212x");
        // Last IP Address number is greater than 255
        bean.setEmail("js@172.12.26.256");

        runFormTest(application, errors, "registrationForm", bean);
	
	assertEquals("Validation of the RegistrationForm", 3, errors.size());
    }

    /**
     * Create a <code>TypeForm</code> and validate it.
    */
    public void testTypeFormFail1() throws ServletException,  javax.servlet.jsp.JspException {
	ServletContext application = config.getServletContext();
        ActionErrors errors = new ActionErrors();
	
        TypeForm bean = new TypeForm();
        bean.setByte("");
        bean.setShort("10");
        bean.setInteger("12");
        bean.setLong("");
        bean.setFloat("-15.00000005");
        bean.setDouble("15155198494151.0001450141");
        bean.setDate("02/29/2000");
        bean.setCreditCard("");
        

        runFormTest(application, errors, "typeForm", bean);
        	
	assertEquals("Validation of the TypeForm", 3, errors.size());
    }

    /**
     * Run a validation test using the <code>Validator</code>.
    */
    private void runFormTest(ServletContext application, ActionErrors errors, 
                             String actionName, Object bean) {

	ValidatorResources resources = ValidatorUtil.getValidatorResources(application);
	Locale locale = ValidatorUtil.getLocale(request);
	Validator validator = new Validator(resources, actionName);

 	validator.addResource(Validator.SERVLET_CONTEXT_KEY, application);
	validator.addResource(Validator.HTTP_SERVLET_REQUEST_KEY, request);
	validator.addResource(Validator.LOCALE_KEY, locale);
	validator.addResource(Validator.ACTION_ERRORS_KEY, errors);
	validator.addResource(Validator.BEAN_KEY, bean);
	
	try {
	   validator.validate();
        } catch (ValidatorException e) {
	   fail("An exception was thrown while calling Validator.validate()");
	}
    	
    }
    
    /**
     * Verify that one value generates and error and the other passes.
     */
    public void testManual() throws ServletException,  javax.servlet.jsp.JspException {
       ValidatorResources resources = new ValidatorResources(new DefaultValidatorLog());

       ValidatorAction va = new ValidatorAction();
       va.setName("capLetter");
       va.setClassname("com.wintecinc.test.TestValidator");
       va.setMethod("isCapLetter");
       va.setMethodParams("java.lang.Object,com.wintecinc.struts.validation.Field,java.util.List");
       
       FormSet fs = new FormSet();
       Form form = new Form();
       form.setName("testForm");
       Field field = new Field();
       field.setProperty("letter");
       field.setDepends("capLetter");
       form.addField(field);
       fs.addForm(form);
       
       resources.addValidatorAction(va);
       resources.put(fs);
       resources.process();

       List l = new ArrayList();

       TestBean bean = new TestBean();  
       bean.setLetter("A");
       
       Validator validator = new Validator(resources, "testForm");
       validator.addResource(Validator.BEAN_KEY, bean);
       validator.addResource("java.util.List", l);

       try {
          validator.validate();
       } catch (Exception e) {
          fail("An exception was thrown while calling Validator.validate()");
       }

       assertEquals("Validation of the letter 'A'.", 0, l.size());

       l.clear();       
       bean.setLetter("AA");

       try {
          validator.validate();
       } catch (Exception e) {
          fail("An exception was thrown while calling Validator.validate()");
       }
       
       assertEquals("Validation of the letter 'AA'.", 1, l.size());
    }

    /**
     * Verify that two <code>String</code>s match using the <code>EqualTag</code>.
     */
    public static boolean isCapLetter(Object bean, Field field, List l) {
       String value = ValidatorUtil.getValueAsString(bean, field.getProperty());

       if (value != null && value.length() == 1) {
          if (value.charAt(0) >= 'A' && value.charAt(0) <= 'Z') {
             return true;
          } else {
             l.add("Error");
             return false;
          }
       } else {
          l.add("Error");
          return false;
       }
    }
    
    public class TestBean {
       private String letter = null;
       
       public String getLetter() {
          return letter;
       }
       
       public void setLetter(String letter) {
          this.letter = letter;	
       }
    }

}
