/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/services/factory/src/org/apache/struts/service/factory/Attic/FactoryService.java,v 1.2 2002/12/08 07:53:31 rleland Exp $
 * $Revision: 1.2 $
 * $Date: 2002/12/08 07:53:31 $
 *
 * ====================================================================
 *
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

package org.apache.struts.service.factory;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.MissingResourceException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.service.Service;
import org.apache.struts.service.ServiceManager;
import org.apache.struts.service.ServletServiceManager;
import org.apache.struts.action.ServicesServlet;
import org.xml.sax.Attributes;

public class FactoryService extends Service {

        /** Default class name to register bean factory mapping  
        */
        protected String factoryMappingClass = "org.apache.struts.service.factory.FactoryMapping";

        /** Default class name to register bean template
        */
        protected String templateClass = "org.apache.struts.service.factory.Template";

        /** Default class name to register parameter in bean template
        */
        protected String parameterMappingClass = "org.apache.struts.service.factory.ParameterMapping";

        /** Default class name to register link between bean template and action
        */
        protected String registrationClass = "org.apache.struts.service.factory.Registration";

        /** Default class name to register parameter setter for bean registration
        */
        protected String parameterValueClass = "org.apache.struts.service.factory.ParameterValue";

        /** Default scope to store created beans
        */
        protected String defaultBeanSaveScope = "request";

        /** Default scope to search parameter value
        */
        protected String defaultParameterSource = "request.parameter";

        /** Bean registrations map to store links between bean templates and actions.
        *  Every map entry is pair of key  and value.
        *  Key is a action path - equal to path with which appropriate action 
        *  is stored. Servlet uses it to find list of bean registrations
        *  for corresponed action.
        *  Value is a ArrayList with bean registrations. Each registration
        *  holds name of the bean template, name of the factory, etc.
        */        
        protected HashMap registrations = new HashMap();

        /** Factories map - all created at processing phase
        *  factories stay here.
        */    
        protected HashMap factories = new HashMap();

        /** FactoryMapping map - container to store factories descriptions.
        */
        protected HashMap factoryMappings = new HashMap();

        /** Templates map - to store info about registered in application
        *  bean templates. Each bean template description contains info
        *  about bean type, template name, to refer it from bean registrations and
        *  list of parameters descriptions to use it at point of bean creation
        *  in factory.
        */
        protected HashMap templates = new HashMap();

        /**
        * The resources object for our internal resources.
        */
        protected MessageResources internal = null;

        /**
        * The Java base name of our internal resources.
        */
        protected String internalName = "org.apache.struts.service.factory.FactoryServiceResources";

        /**
        * Initialize our internal MessageResources bundle.
        *
        * @exception ServletException if we cannot initialize these resources
        */
        protected void initInternal() throws Exception {

                try {
                        internal = MessageResources.getMessageResources(internalName);
                } catch (MissingResourceException e) {
                        log("Cannot load internal resources from '" + internalName + "'", e);
                        throw new UnavailableException
                                ("Cannot load internal resources from '" + internalName + "'");
                }
        }

        public void init() throws Exception {
                initInternal();
        }

        /** Adds bean registrationto be used in action processing.
        * @param beanRegistration The bean registration.
        */    
        public void addRegistration( Registration registration ) {

                String key = registration.getPath();
                ArrayList beanList = (ArrayList)registrations.get( key );
                if( beanList == null ) {
                        beanList  = new ArrayList();
                        registrations.put( key, beanList );
                }
                beanList.add( registration );
        }

        /**
        * Register a bean factory definition to the set configured for this servlet.
        *
        * @param beanFactoryMapping The bean factory definition.
        */
        public void addFactoryMapping( FactoryMapping factoryMapping) {
                factoryMappings.put( factoryMapping.getName(), factoryMapping );
        }

        /**
        * Register a bean template definition to the set configured for this servlet.
        *
        * @param beanTemplate The bean template definition to be added
        */
        public void addTemplate( Template template) {
                templates.put( template.getName(), template);
        }

        /** Return the bean factory definition associated with the specified
        * logical name, if any; otherwise return <code>null</code>.
        *
        * @param name Logical name of the requested bean factory definition
        * @return BeanFactoryMapping
        */
        public FactoryMapping findFactoryMapping(String name) {
                return ((FactoryMapping)factoryMappings.get(name));
        }

        /** Return the bean template definition associated with the specified
        * logical name, if any; otherwise return <code>null</code>.
        *
        * @param name Logical name of the requested bean template definition
        * @return BeanTemplate
        */
        public Template findTemplate(String name) {
                return ((Template)templates.get(name));
        }

        protected ServicesServlet getServlet() {
                return (ServicesServlet)((ServletServiceManager)manager).getServlet();
        }

        /**
        * General purpose preprocessing hook used to generate beans according
        * to list of bean registrations.
        *
        * @param request The servlet request we are processing
        * @param response The servlet response we are generating
        *
        * @return <code>true</code> if the remainder of the standard processing
        *  should be performed, or <code>false</code> if the response has already
        *  been created so the calling method should immediately exit
        *
        * @exception IOException if an input/output error occurs
        * @exception ServletException if a servlet exception occurs
        */
//        public boolean processPreprocess( HttpServletRequest request,
//                                             HttpServletResponse response)
        public boolean processPreprocess( Object parameters )
        throws IOException, ServletException {

        Object[] parList = (Object[])parameters;
        HttpServletRequest request = (HttpServletRequest)parList[0];
        HttpServletResponse response = (HttpServletResponse)parList[1];

        String path = getServlet().processPath(request);
        if (path == null) {
            if (getDebug() >= 1)
                log(" No path available for request URI " +
                    request.getRequestURI());
            response.sendError( HttpServletResponse.SC_BAD_REQUEST,
                                internal.getMessage("processPath"));
            return false;
        }
        if (getDebug() >= 1)
            log("Processing a " + request.getMethod() + " for " + path);

        ActionMapping mapping = getServlet().processMapping(path, request);
        if (mapping == null) {
            if (getDebug() >= 1)
                log(" No mapping available for path " + path);
            response.sendError( HttpServletResponse.SC_BAD_REQUEST,
                                internal.getMessage( "processInvalid", path));
            return false;
        }

        ArrayList beanList = ( ArrayList )registrations.get( mapping.getPath() );

        // Bean list is not null - there are some beans registered to be created
        // before this action processing
        if( beanList != null ) {

                if (getDebug() >= 1)
                        log("BeanList not null! Start to create beans");

                Iterator iterator = beanList.iterator();
                while( iterator.hasNext() ) {
                        if( !processRegistration( (Registration)iterator.next(), request, response ) )
                        return false;
                }
        }

        return ( true );

    }

    /** Process bean registration. It returns false if some errors
     *  occured and true as flag of successful operation. Successful
     *  issue is after such steps as - <br>
     *  <p>
     *  <li> search bean template
     *  <li> search factory
     *  <li> create bean with this factory from bean template
     *  <li> store bean in session or request scope</p>
     *
     *  @param beanRegistration Bean registration to be processed
     *  @param request The servlet request we are processing
     *  @param response The servlet response we are generating
     *
     *  @return boolean
     *
     *  @throws IOExceprion
     *  @throws ServletExceprion
     */
    public boolean processRegistration( 
                Registration registration,
                HttpServletRequest request,
                HttpServletResponse response )
                throws IOException, ServletException {
        String name = null;
        FactoryMapping factoryMapping = null;
        Template template = null;
        Object bean = null;

        if (getDebug() >= 1)
                log( "Process Registration for name = '" + 
                registration.getName() + "' and factory '" + 
                registration.getFactory() + "'");

        factoryMapping = findFactoryMapping( registration.getFactory() );
        // find bean factory or exit with error
        if (factoryMapping == null) {
                if ( getDebug() >= 1 )
                        log(" No factory available for name " + registration.getFactory() );
                response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,
                        "Invalid factory name" );
                return false;
        }
        // find bean template or exit with error
        template = findTemplate( registration.getName() );
        if (template == null) {
                if (getDebug() >= 1)
                        log(" No template available for name " + registration.getName() );
                response.sendError( HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,
                        "Invalid template name" );
                return false;
        }
        try {
                bean = processCreateBean( template, factoryMapping, registration, request );
        } catch ( Throwable e ) {
                log( "Exception at create instance process!", e );
                e.printStackTrace( System.out );
        }
        if ( bean == null ) {
                if (getDebug() >= 1)
                        log(" Bean not created! Bean template name - " + name );
                if ( registration.getNecessary() ) {
                        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,
                         "Can not create internal resource");
                        return false;
                }
        } else {
                String beanName = null;
                String beanScope = registration.getScope();
                if( registration.getAlias() != null )
                        beanName = registration.getAlias();
                else
                        beanName = registration.getName();
                if( beanScope == null )
                        beanScope = defaultBeanSaveScope;
                if( "session".equals ( beanScope ) ) {
                        request.getSession().setAttribute( beanName, bean );
                        if (getDebug() >= 1)
                                log("Save bean in session context under name - " + beanName );
                } else if ( "request".equals ( beanScope ) ) {
                        request.setAttribute( beanName, bean );
                        if (getDebug() >= 1) 
                                log("Save bean in request context under name - " + beanName + 
                                " object saved in request - " + request.getAttribute( beanName ) );
                } else {
                        if (getDebug() >= 1)
                                log("Bean is not saved in scope - " + registration.getScope() );
                }
        }
        return true;
    }

    /** Create bean from bean template with help of bean factory. Bind all
     *  parameters, find or create factory and call <code>Factory.create</code>
     *  method to create target bean.
     *
     *  @param beanTemplate Template of bean to be created (type, parameters, etc.)
     *  @param beanFactoryMapping Factory mapping to find and use target factory
     *  @param beanRegistration Bean registration
     *  @param request The servlet request we are processing
     *
     *  @return Object
     * 
     *  @throws Throwable
     */
    public Object processCreateBean( 
                Template template, 
                FactoryMapping factoryMapping, 
                Registration registration,
                HttpServletRequest request ) 
        throws Throwable {
        Object bean = null;                
        Parameter[] parameters = null;
        Factory factory = null;
        int parametersCount = template.getParametersCount();
        if( parametersCount > 0 ) {
                parameters = new Parameter[ parametersCount ];
                Iterator parametersIterator = template.getParameters();
                for( int i = 0; parametersIterator.hasNext(); i++ ) {
                        parameters[ i ] = processParameterInit( ( ParameterMapping )parametersIterator.next(),
                                                registration, request );
                }
        }
        factory = processFactoryCreate( factoryMapping, request );
        if( factory!=null ) {
                if (getDebug() >= 1)
                        log("Factory created or found - " + factory );
                bean = factory.create( template.getType(), parameters );
                if (getDebug() >= 1) {
                        if( bean!=null )
                                log("Bean created - " + bean );
                        else
                                log("Bean not created for template - " + template.getName() );
                }
        } else {
                if (getDebug() >= 1)
                        log("Factory not created! - " + factory );
        }
        return bean;
    }

    /** Initialize parameter to be used at bean creation process in bean factory.
     *  Strategy to choose value to set tho the bean parameter - <br><p>
     *  <li>if bean registration contains direct parameter setter  - 
     *      parameter-value tag with value to be used for this parameter
     *      then new parameter will initialized with it
     *  <li>if parameter mapping has <code>force</code> attribute with 
     *      <code>true</code> value then value of the property 
     *      <code>value</code> from the <cede>ParameterMapping</code>
     *      will be used as value for the parameter
     *  <li>if <code>source</code> parameter supplied in parameter mapping
     *      then retrieve value from specified source (default source - 
     *      request.parameter). If nothing found in specified source
     *      (null value) then <code>value</code> attribute from 
     *      paramater mapping will be used as default value for parameter.</p>
     *  
     *  <p>Available sources - <br>
     *  <li><code>request.parameter</code> - parameter of the request by name from 
     *      <code>name</code> attribute from parameterMapping
     *  <li><code>request</code> or <code>request.attribute</code> - attribute
     *      from request named by name from <code>name</code> attribute from 
     *      parameterMapping
     *  <li><code>session</code> or <code>session.attribute</code> - attribute
     *      from session named by name from <code>name</code> attribute from 
     *      parameterMapping
     *  <li><code>application</code> or <code>application.attribute</code> - 
     *      attribute from application scope named by name from <code>name</code> 
     *      attribute from parameterMapping 
     *  <li><code>application.property</code> - value of the servlet property
     *      named by name from <code>name</code> attribute from parameterMapping
     *  <li><code>form</code> or <code>form.property</code> - value from form
     *      mapped to this action if exists one. <code>name</code> attribute 
     *      from parameterMapping used as name of the property
     *  </p>
     *  
     *  @param parameterMapping Mapping for parameter
     *  @param registration Bean registration
     *  @param request The servlet request we are processing
     *
     *  @return Parameter
     */
    public Parameter processParameterInit( 
                ParameterMapping parameterMapping, 
                Registration registration,
                HttpServletRequest request )
        throws Throwable {
        Parameter parameter = new Parameter( parameterMapping );
        Object parValue = registration.getParameterValue( parameterMapping.getName() );
        if( parValue!=null ) {
                parameter.setValue( ConvertUtils.convert( (String)parValue, 
                        parameterMapping.getStub() ) );
        } else if( parameterMapping.getForce() ) {
                parameter.setValue( ConvertUtils.convert( parameterMapping.getValue(), 
                        parameterMapping.getStub() ) );
        } else {
                String parameterSource = parameterMapping.getSource();
                if( parameterSource == null )
                        parameterSource = defaultParameterSource;
                if( "request".equals( parameterSource ) ||
                        "request.attribute".equals( parameterSource ) ) {
                        parValue = request.getAttribute( parameterMapping.getName() );
                        if( parValue!=null &&
                            parValue.getClass().getName().equals( parameterMapping.getType() ) ) {
                                parameter.setValue( parValue);
                        } else {
                                if (getDebug() >= 1)
                                log("Attribute for parameter '" + parameterMapping.getName() + "' not found in request scope or type is different");
                        }
                } else if( "session".equals( parameterSource ) ||
                           "session.attribute".equals( parameterSource ) ) {
                        HttpSession session = ((HttpServletRequest)request).getSession( false );
                        if( session!=null ) {
                                parValue = session.getAttribute( parameterMapping.getName() );
                                if( parValue!=null && 
                                    parValue.getClass().getName().equals( parameterMapping.getType() ) ) {
                                        parameter.setValue( parValue);
                                } else {
                                        if (getDebug() >= 1)
                                        log("Attribute for parameter '" + parameterMapping.getName() + "' not found in session scope or type is different");
                                }
                        }
                } else if( "application".equals( parameterSource ) ||
                           "application.attribute".equals( parameterSource ) ) {
                        parValue = getServlet().getServletContext().getAttribute( parameterMapping.getName() );
                        if( parValue!=null &&
                            parValue.getClass().getName().equals( parameterMapping.getType() ) ) {
                                parameter.setValue( parValue);
                        } else {
                                if (getDebug() >= 1)
                                        log("Attribute for parameter '" + parameterMapping.getName() + "' not found in application scope or type is different");
                        }
                } else if( "application.property".equals( parameterSource ) ) {
                        parameter.setValue( PropertyUtils.getProperty( this, parameterMapping.getName() ) );
                } else if( "form".equals( parameterSource ) ||
                           "form.property".equals( parameterSource ) ) {
                        parameter.setValue( PropertyUtils.getProperty( this, parameterMapping.getName() ) );
                } else {
                        parameter.setValue( ConvertUtils.convert(
                                request.getParameter( parameterMapping.getName() ), parameterMapping.getStub() ) );
                }
                if( parameter.getValue()==null ) {
                        parameter.setValue( ConvertUtils.convert( parameterMapping.getValue(), 
                        parameterMapping.getStub() ) );
                }
        }
        return parameter;
    }

    /** Returns ready to use bean factory. First time it try to
      * find it in map of early created factories. It returns one if finds
      * any. Otherwise new factory will be instantiated, stored in
      * in cash and method returns it.
      * @param factoryMapping - factory mapping with factory definitions
      * @param request - http request link
     */
    protected Factory processFactoryCreate( FactoryMapping factoryMapping,
                                            HttpServletRequest request) {

        // Acquire the Action instance we will be using
        String factoryClass = factoryMapping.getType();

        if (getDebug() >= 1)
            log(" Looking for Factory instance for class " + factoryClass );

        Factory factoryInstance = (Factory) factories.get(factoryClass);
        if (factoryInstance == null) {
            synchronized (factories) {

                if (getDebug() >= 1)
                    log("  Double checking for Factory instance already there");

                // Double check to avoid a race condition
                factoryInstance = (Factory) factories.get(factoryClass);
                if (factoryInstance != null)
                    return ( factoryInstance );

                try {

                    if (getDebug() >= 1)
                        log("  Creating new Factory instance for class - " + factoryClass );

                    factoryInstance = factoryMapping.instantiateFactory();
                    factoryInstance.setFactoryService( this );
                    factories.put( factoryClass, factoryInstance);
                } catch (Throwable t) {
                    log("Error creating bean Factory instance for name '" +
                        factoryMapping.getName() + "', class name '" +
                        factoryClass + "'", t);
                    return (null);
                }
            }
        }
        return ( factoryInstance );

    }

        public void initDigester( Digester digester, String path ) {

                String factoryPath = path + "/factories/factory";
                String templatePath = path + "/templates/template";
                String templateParameterPath = templatePath + "/parameter";
                String beanPath = "struts-config/action-mappings/action/bean";
                String parameterValuePath = beanPath + "/parameter-value";

                digester.addObjectCreate( factoryPath, factoryMappingClass, "className" );
                digester.addSetProperties( factoryPath );
                digester.addSetNext( factoryPath, "addFactoryMapping", factoryMappingClass );
//                digester.addSetProperty( factoryPath + , nameProperty, nameValue);
        
                digester.addObjectCreate( templatePath, templateClass, "className" );
                digester.addSetProperties( templatePath );
                digester.addSetNext( templatePath, "addTemplate", templateClass );
//                digester.addSetProperty( templatePath + setPropertyPath, nameProperty, nameValue);

                digester.addObjectCreate( templateParameterPath, parameterMappingClass, "className" );
                digester.addSetProperties( templateParameterPath );
                digester.addSetNext( templateParameterPath, "addParameter", parameterMappingClass );

                // Add bean-creation rules to support auto bean generation

                digester.addObjectCreate( beanPath, registrationClass, "className" );
                digester.addSetProperties( beanPath );
                digester.addRule( beanPath, new AddBeanRegistrationRule( digester, this ) );
//                digester.addSetProperty( beanRegisterPath + setPropertyPath, nameProperty, nameValue);

                digester.addObjectCreate( parameterValuePath, parameterValueClass, "className" );
                digester.addSetProperties( parameterValuePath );
                digester.addSetNext( parameterValuePath, "addParameterValue", parameterValueClass );

        }


}

// ------------------------------------------------------------ Private Classes


/** Rule to process bean registration in action mapping.
 */
final class AddBeanRegistrationRule extends Rule {

        protected FactoryService service = null;


    /** Public constructor to create rule.
     * @param digester Parent digester to accept this rule.
     */    
    public AddBeanRegistrationRule(Digester digester, FactoryService service ) {

        super(digester);

        this.service = service;

    }

    /** Initial point to start processing.
     * @param attributes Tag attributes.
     * @throws Exception at some kind of trouble in process
     */    
    public void begin(Attributes attributes) throws Exception {

        Registration registration = (Registration)digester.peek(0);
        ActionMapping parent = (ActionMapping)digester.peek(1);

        registration.setPath( parent.getPath() );
        service.addRegistration( registration );

    }

}

