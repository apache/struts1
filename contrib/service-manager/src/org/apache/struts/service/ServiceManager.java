/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/src/org/apache/struts/service/Attic/ServiceManager.java,v 1.3 2001/07/23 12:35:07 oalexeev Exp $
 * $Revision: 1.3 $
 * $Date: 2001/07/23 12:35:07 $
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

package org.apache.struts.service;

import java.io.Serializable;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.NoSuchMethodException;
import java.util.MissingResourceException;
import javax.servlet.UnavailableException;
import java.util.MissingResourceException;
import java.lang.reflect.Method;
import org.xml.sax.SAXException;
import org.apache.commons.digester.Digester;
import org.apache.struts.util.MessageResources;

/** 
 * @author Oleg V Alexeev
 * @version $Revision: 1.3 $ $Date: 2001/07/23 12:35:07 $
 */
public class ServiceManager implements Serializable {

        protected static final String serviceRegistrationClass = "org.apache.struts.service.ServiceRegistration";

        protected static final String processRegistrationClass = "org.apache.struts.service.ProcessRegistration";

        protected static final String processSubscriptionClass = "org.apache.struts.service.ProcessSubscription";

        protected String configLocation = "service-manager.xml";

        protected String configLocationPropertyName = "org.apache.struts.service.ConfigLocation";

        protected String configDetailPropertyName = "org.apache.struts.service.ConfigDetail";

        protected Object parent = null;

        protected ArrayList emptyList = new ArrayList();

        protected HashMap processRegistrations = new HashMap();

        protected HashMap serviceRegistrations = new HashMap();

        protected ArrayList services = new ArrayList();

        protected LogBridge logHelper = null;

        protected int debug = 0;

        /**
        * The resources object for our internal resources.
        */
        protected MessageResources internal = null;

        /**
        * The Java base name of our internal resources.
        */
        protected String internalName = "org.apache.struts.service.ServiceManagerResources";

        protected Iterator getEmptyIterator() {
                return emptyList.iterator();
        }

        protected void initLogHelper() {
                logHelper = new LogBridge( this );
        }

        public ServiceManager() {
                setParent( null );        
        }

        public ServiceManager( Object parent ) {
                setParent( parent );
        }

        public int getDebugLevel() {
                return debug;
        }

        public void setDebugLevel( int debug ) {
                this.debug = debug;
        }

        public HashMap getProcessRegistrations() {
                return processRegistrations;
        }

        public HashMap getServiceRegistrations() {
                return serviceRegistrations;
        }

        public void setLogHelper( LogBridge logHelper ) {
                this.logHelper = logHelper;
        }

        public LogBridge getLogHelper() {
                if( logHelper==null )
                        initLogHelper();
                return logHelper;
        }

        public void setParent( Object parent ) {
                if( parent==null )
                        this.parent = this;
                else
                        this.parent = parent;
        }

        public Object getParent() {
                return parent;
        }

        public void registerProcess( ProcessRegistration registration ) {

                if( debug>0 )
                        logHelper.log( "Register process '" + registration.getName() + "'" );

                registration.setServiceManager( this );
                processRegistrations.put( registration.getName(), registration );
        }

        public void registerService( ServiceRegistration registration ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Register service '" + registration.getName() + 
                                "' class - '" + registration.getType() + "'" );

                registration.setServiceManager( this );
                serviceRegistrations.put( registration.getName(), registration );
                Service service = registration.getService();
                services.add( service );
                service.setLogHelper( getLogHelper() );
        }

        //------------------------------------------------------- Init methods

        /** Single init method. Use this method to init ServiceManager
         *  with its own service-manager.xml config file.
         */
        public void init() 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Init ServiceManager class - '" + this.getClass().getName() + "'" );

                initStructures();
                initConfig();
        }

        /** Init internal structures to fill it later in init process
         */
        public void initStructures() 
                throws Exception {
                initInternal();
                processRegistrations.clear();
                serviceRegistrations.clear();
                services.clear();
        }

        /**
        * Initialize our internal MessageResources bundle.
        *
        * @exception UnavailableException if we cannot initialize these resources
        */
        protected void initInternal() throws Exception {

                try {
                        internal = MessageResources.getMessageResources(internalName);
                } catch (MissingResourceException e) {
                        logHelper.log( "Cannot load internal resources from '" + 
                                internalName + "'", e);
                        throw e;
                }
        }

        /** Read self-contained config with help of digester.
         */
        public void initConfig() 
                throws Exception {

                initVariables();

                int detail;
                try {
                        String value = System.getProperty( configDetailPropertyName );
                        detail = Integer.parseInt(value);
                } catch (Throwable t) {
                        detail = 0;
                }

                // Acquire an input stream to our configuration resource
                InputStream input = getConfigResource();

                // Build a digester to process our configuration resource
                Digester digester = initDigester(detail);
        
                // Parse the input stream to configure our mappings
                try {
                    digester.parse(input);
                } catch (SAXException e) {
                    throw e;
                } finally {
                    input.close();
                }
        
        }

        /** Init internal variables to be used at init process;
         */
        public void initVariables() 
                throws Exception {
                String value = null;

                try {
                        value = System.getProperty( configLocationPropertyName );        
                } catch ( SecurityException e ) {
                        throw e;
                } catch ( Exception e ) {
                        logHelper.log( internal.getMessage( 
                                "configLocationProperty.emptyProperty",
                                configLocationPropertyName,
                                configLocation ) );
                }
                if( value != null ) 
                        configLocation = value;
        }

        /** Connect to the config. 
         */
        protected InputStream getConfigResource() 
                throws Exception {
                return new FileInputStream( configLocation );
        }

        /** Init digester to be used with independent
         *  service manager config. 
         */
        public Digester initDigester( int detail ) {
                Digester digester = new Digester();
                digester.setDebug(detail);
                digester.setNamespaceAware(true);
                digester.setValidating(false);
                return initDigester( digester, null );
        }

        /** Init digester to parse config
         */
        public Digester initDigester( Digester digester, String path ) {

                if( debug>0 )
                        logHelper.log( "Init digester in ServiceManager class - '" + this.getClass().getName() + "'" );

                if( path!=null ) 
                        path += "/";
                else
                        path = "";

                String serviceManagerPath = 
                        path + "service-manager";
                String processRegistrationPath = 
                        serviceManagerPath + "/process-registrations/process-registration";
                String serviceRegistrationPath = 
                        serviceManagerPath + "/service-registrations/service-registration";
                String processSubscriptionPath = 
                        serviceRegistrationPath + "/process-subscription";

                digester.addRule( serviceManagerPath, 
                        new InitServiceManagerRule( digester, this ) );

                // parse process registrations
                digester.addObjectCreate( processRegistrationPath, processRegistrationClass );
                digester.addSetProperties( processRegistrationPath );
                digester.addSetNext( processRegistrationPath, "registerProcess",
                        processRegistrationClass );

                // parse service registrations
                digester.addObjectCreate( serviceRegistrationPath, serviceRegistrationClass);
                digester.addSetProperties( serviceRegistrationPath );
                digester.addSetNext( serviceRegistrationPath, "registerService",
                        serviceRegistrationClass );
                digester.addRule( serviceRegistrationPath, 
                        new InitServiceRule( digester, new String( serviceRegistrationPath ), this ) );

                // parse process subscriptions
                digester.addObjectCreate( processSubscriptionPath, processSubscriptionClass );
                digester.addSetProperties( processSubscriptionPath );
                digester.addSetNext( processSubscriptionPath, "subscribeTo",
                        processSubscriptionClass );

                return digester;
        }

        //------------------------------------------------------- Destroy methods

        public void destroy() {
                if( debug>0 )
                        logHelper.log( "Destroy ServiceManager class - '" + this.getClass().getName() + "'" );
                try {
                        Iterator iterator = services.iterator();
                        Service service = null;
                        ProcessRegistration processRegistration = null;
                        ServiceRegistration serviceRegistration = null;
                        String key = null;
                        while( iterator.hasNext() ) {
                                service = (Service)iterator.next();
                                service.destroy();
                        }
                        services.clear();
                        parent = null;
                        iterator = processRegistrations.keySet().iterator();
                        while( iterator.hasNext() ) {
                                key = (String)iterator.next();
                                processRegistration = (ProcessRegistration)processRegistrations.get( key );
                                processRegistration.getServices().clear();
                        }
                        processRegistrations.clear();
                        iterator = serviceRegistrations.keySet().iterator();
                        while( iterator.hasNext() ) {
                                key = (String)iterator.next();
                                serviceRegistration = (ServiceRegistration)serviceRegistrations.get( key );
                                serviceRegistration.getSubscriptions().clear();
                        }
                        serviceRegistrations.clear();
                } catch ( Exception e ) {
                        logHelper.log( "Exception in destroy of ServiceManager - ", e );
                }
        }
        
        protected String makeMethodName( String processName ) {
                return "process" + processName.substring( 0, 1 ).toUpperCase() +
                        processName.substring( 1 ).toLowerCase();    
        }

        protected Iterator servicesIterator( String processName ) {

                Iterator test = processRegistrations.keySet().iterator();
                while( test.hasNext() ) {
                        String key = (String)test.next();
                        logHelper.log( "Test: processRegistrations has '" + key + "' key" );
                }

                ProcessRegistration processRegistration = 
                        (ProcessRegistration)processRegistrations.get( processName );
                if( processRegistration != null ) {

                        if( debug>0 )
                                logHelper.log( "Found processRegistration for process name '" + processName + "'" );

                        return processRegistration.getServicesIterator();
                } else {
                        getLogHelper().log( "Can not found process registration for process name '" + 
                                        processName + "'" );
                        return getEmptyIterator();
                }
        }

        protected Class[] makeTypes( Object[] parameters ) {
                Class[] types = new Class[ parameters.length ];
                for( int i = 0; i < parameters.length; i++ ) {
                        types[ i ] = parameters[ i ].getClass();
                }
                return types;
        }

        public void performCall( String processName, Object[] parameters ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Call to perfomCall for process '" + processName + "'" );

                String methodName = makeMethodName( processName );
                Iterator iterator = servicesIterator( processName );
                Service service = null;
                Method method = null;
                Class[] types = makeTypes( parameters );

                while( iterator.hasNext() ) {
                        service = (Service)iterator.next();

                        if( debug>0 )
                                logHelper.log( "Call to Service '" + service.getClass().getName() + "'" );

                        try {
                                method = service.getClass().getMethod( methodName, new Class[]{ parameters.getClass() } );
                        } catch ( NoSuchMethodException e ) {
                                method = null;
                        }

                        if( method!=null ) {

                                if( debug>0 )
                                        logHelper.log( "Try to invoke method '" + methodName+ 
                                                "' for class '" + service.getClass().getName() + "'" );

                                method.invoke( service, new Object[]{ parameters } );

                        } else {
                                if( debug>0 )
                                        logHelper.log( "Can not find method '" + methodName + 
                                                "' with parameters '" + parameters + 
                                                "' of types '" + types +
                                                "' for class '" + service.getClass().getName() + "'" );
                                for( int i = 0; i < types.length; i++ ) {
                                        logHelper.log( "parameter " + Integer.toString( i ) + 
                                                ", type = '" + types[i].getName() + "'" );
                                }
                                for( int i = 0; i < parameters.length; i++ ) {
                                        logHelper.log( "parameter " + Integer.toString( i ) + 
                                                ", type = '" + parameters[i].getClass().getName() + "'" );
                                }
                        }
                }
        }

        public boolean performBooleanCall( String processName, Object[] parameters, boolean type ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Call to perfomBooleanCall for process '" + 
                                processName + "' and call type - '" + ((type)?"AND":"OR") + "'" );

                String methodName = makeMethodName( processName );
                Iterator iterator = servicesIterator( processName );
                Service service = null;
                Method method = null;
                Class[] types = makeTypes( parameters );
                Boolean result = null;
                boolean collector = false;

                while( iterator.hasNext() ) {
                        service = (Service)iterator.next();

                        if( debug>0 )
                                logHelper.log( "Call to Service '" + service.getClass().getName() + "'" );

                        try {
                                method = service.getClass().getMethod( methodName, new Class[]{ parameters.getClass() } );
                        } catch ( NoSuchMethodException e ) {
                                method = null;
                        }

                        if( method!=null ) {

                                if( debug>0 )
                                        logHelper.log( "Try to invoke method '" + methodName+ 
                                                "' for class '" + service.getClass().getName() + "'" );

                                result = (Boolean)method.invoke( service, new Object[]{ parameters } );
                                if( result!=null ) {
                                        if( type ) {
                                                if( !result.booleanValue() ) return false;
                                        } else {
                                                if( result.booleanValue() ) collector = true;
                                        }
                                }
                        } else {

                                if( debug>0 )
                                        logHelper.log( "Can not find method '" + methodName + 
                                                "' with parameters '" + parameters + 
                                                "' of types '" + types +
                                                "' for class '" + service.getClass().getName() + "'" );

                                for( int i = 0; i < types.length; i++ ) {
                                        logHelper.log( "parameter " + Integer.toString( i ) + 
                                                ", type = '" + types[i].getName() + "'" );
                                }
                                for( int i = 0; i < parameters.length; i++ ) {
                                        logHelper.log( "parameter " + Integer.toString( i ) + 
                                                ", type = '" + parameters[i].getClass().getName() + "'" );
                                }
                        }
                }

                if( type ) 
                        return true;
                else
                        return collector;

        }

}

