/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/src/core/org/apache/struts/service/Attic/ServiceManager.java,v 1.2 2002/12/08 07:53:32 rleland Exp $
 * $Revision: 1.2 $
 * $Date: 2002/12/08 07:53:32 $
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
 * @version $Revision: 1.2 $ $Date: 2002/12/08 07:53:32 $
 */
public class ServiceManager implements Serializable {

        //------------------------------------------------------ Constants

        /** Name of class to register service
         */
        protected static final String serviceRegistrationClass = "org.apache.struts.service.ServiceRegistration";

        /** Name of class to register process
         */
        protected static final String processRegistrationClass = "org.apache.struts.service.ProcessRegistration";  

        /** Name of class to subscribe service to handle processing point
         */
        protected static final String processSubscriptionClass = "org.apache.struts.service.ProcessSubscription";

        protected static final Class[] parList = new Class[]{ Object.class };

        //------------------------------------------------------ Variables

        /** Parent object for ServiceManager
         */
        protected Object parent = null;

        /** Empty list to use it as cork
         */
        protected ArrayList emptyList = new ArrayList();

        /** Map to store process registrations
         */
        protected HashMap processRegistrations = new HashMap();

        /** Map to store service registrations
         */
        protected HashMap serviceRegistrations = new HashMap();

        /** List ofinstantiated services
         */
        protected ArrayList services = new ArrayList();

        /** Log helper to support external logging. 
         */
        protected LogBridge logHelper = null;

        /** Debug level.
         */
        protected int debug = 0;

        /**
        * The resources object for our internal resources.
        */
        protected MessageResources internal = null;

        /**
        * The Java base name of our internal resources.
        */
        protected String internalName = "org.apache.struts.service.ServiceManagerResources";

        //------------------------------------------------------ Protected methods

        /** Return Iterator via empty list.
         */
        protected Iterator getEmptyIterator() {
                return emptyList.iterator();
        }

        /** Init log helper reference by default log helper class.
         */
        protected void initLogHelper() {
                logHelper = new ConsoleLogBridge();
        }

        /** Builds method name to call it in Service
         */
        protected String makeMethodName( String processName ) {
                return "process" + processName.substring( 0, 1 ).toUpperCase() +
                        processName.substring( 1 ).toLowerCase();    
        }

        /** Return Iterator via Services, subscribed to the specified process.
         */
        protected Iterator servicesIterator( String processName ) {

                ProcessRegistration processRegistration = 
                        (ProcessRegistration)processRegistrations.get( processName );
                if( processRegistration != null ) {

                        if( debug>0 )
                                logHelper.log( "Found processRegistration for process name '" + processName + "'" );

                        return processRegistration.getServicesIterator();
                } else {
                        logHelper.log( "Can not found process registration for process name '" + 
                                        processName + "'" );
                        return getEmptyIterator();
                }
        }

        //------------------------------------------------------ Constructors

        /** Default constructor.
         */
        public ServiceManager() {
                setParent( null );
                initLogHelper();
        }

        /** Constructor to mount ServiceManager to the parent object.
         */
        public ServiceManager( Object parent ) {
                setParent( parent );
                initLogHelper();
        }

        //------------------------------------------------------ Properties support

        /** Debug getter
         */
        public int getDebug() {
                return debug;
        }

        /** Debug setter
         */
        public void setDebug( int debug ) {
                this.debug = debug;
        }

        /** ProcessRegistrations getter
         */
        public HashMap getProcessRegistrations() {
                return processRegistrations;
        }

        /** ServiceRegistrations getter
         */
        public HashMap getServiceRegistrations() {
                return serviceRegistrations;
        }

        /** LogHelper setter
         */
        public void setLogHelper( LogBridge logHelper ) {
                this.logHelper = logHelper;
        }

        /** LogHelper getter
         */
        public LogBridge getLogHelper() {
                if( logHelper==null )
                        initLogHelper();
                return logHelper;
        }

        /** Parent object setter
         */
        public void setParent( Object parent ) {
                this.parent = parent;
        }

        /** Parent object getter
         */
        public Object getParent() {
                return parent;
        }

        //------------------------------------------------------ Public methods

        /** Process registration handler for digester
         */
        public void registerProcess( ProcessRegistration registration ) {

                if( debug>0 )
                        logHelper.log( "Register process '" + registration.getName() + "'" );

                registration.setServiceManager( this );
                processRegistrations.put( registration.getName(), registration );
        }

        /** Service registration handler for digester
         */
        public void registerService( ServiceRegistration registration ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Register service '" + registration.getName() + 
                                "' class - '" + registration.getType() + "'" );

                registration.setServiceManager( this );
                serviceRegistrations.put( registration.getName(), registration );
                services.add( registration.getService() );
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

                String value = null;
                String configLocation = "service-manager.xml";


                try {
                        value = System.getProperty( "org.apache.struts.service.Debug" );
                        setDebug(  Integer.parseInt(value) );
                } catch (Throwable t) {
                        setDebug( 0 );
                }

                try {
                        value = System.getProperty( "org.apache.struts.service.ConfigLocation" );        
                } catch ( SecurityException e ) {
                        throw e;
                } catch ( Exception e ) {
                        if( debug>0 )
                                logHelper.log( internal.getMessage( 
                                        "configLocationProperty.emptyProperty", configLocation ) );
                }
                if( value != null ) 
                        configLocation = value;

                int detail;
                try {
                        value = System.getProperty( "org.apache.struts.service.ConfigDetail" );
                        detail = Integer.parseInt(value);
                } catch (Throwable t) {
                        detail = 0;
                }

                // Acquire an input stream to our configuration resource
                InputStream input = new FileInputStream( configLocation );

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

        /** Init digester to be used with independent
         *  service manager config. 
         */
        public Digester initDigester( int detail ) {
                Digester digester = new Digester();
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
        
        //------------------------------------------------------ Perform methods

        /** Perform call with specified parameters to every Service, subscribed 
         *  to the specified process.
         */  
        public void performCall( String processName, Object[] parameters ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Call to perfomCall for process '" + processName + "'" );

                String methodName = makeMethodName( processName );
                Iterator iterator = servicesIterator( processName );
                Service service = null;
                Method method = null;

                if( parameters==null )
                        parameters = new Object[]{ null };

                while( iterator.hasNext() ) {
                        service = (Service)iterator.next();

                        if( debug>0 )
                                logHelper.log( "Call to Service '" + service.getClass().getName() + "'" );

                        try {
                                method = service.getClass().getMethod( methodName, parList );
                        } catch ( NoSuchMethodException e ) {
                                method = null;
                        }

                        if( method!=null ) {

                                if( debug>0 )
                                        logHelper.log( "Try to invoke method '" + methodName+ 
                                                "' for class '" + service.getClass().getName() + "'" );

                                method.invoke( service, parameters );

                        } else {
                                if( debug>0 )
                                        logHelper.log( "Can not find method '" + methodName + "'" );
                        }
                }
        }

        /** Perform call with specified parameters to Services, subscribed 
         *  to the specified process. Third input parameter indicate type of the call - 
         *  AND (true) or OR (false). AND call to this methods iterate via Services,
         *  subscribed to specified process thus far one of the Services methods return false.
         *  OR call collects all results from calls to the Services methods and perform
         *  'or' operation under this results. So every method in Services, called via 
         *  performBooleanCall() must return boolean or Boolean.
         */  
        public boolean performBooleanCall( String processName, Object[] parameters, boolean type ) 
                throws Exception {

                if( debug>0 )
                        logHelper.log( "Call to perfomBooleanCall for process '" + 
                                processName + "' and call type - '" + ((type)?"AND":"OR") + "'" );

                String methodName = makeMethodName( processName );
                Iterator iterator = servicesIterator( processName );
                Service service = null;
                Method method = null;
                Boolean result = null;
                boolean collector = false;

                if( parameters==null )
                        parameters = new Object[]{ null };

                while( iterator.hasNext() ) {
                        service = (Service)iterator.next();

                        if( debug>0 )
                                logHelper.log( "Call to Service '" + service.getClass().getName() + "'" );

                        try {
                                method = service.getClass().getMethod( methodName, parList );
                        } catch ( NoSuchMethodException e ) {
                                method = null;
                        }

                        if( method!=null ) {

                                if( debug>0 )
                                        logHelper.log( "Try to invoke method '" + methodName+ 
                                                "' for class '" + service.getClass().getName() + "'" );

                                result = (Boolean)method.invoke( service, parameters );
                                if( result!=null ) {
                                        if( type ) {
                                                if( !result.booleanValue() ) return false;
                                        } else {
                                                if( result.booleanValue() ) collector = true;
                                        }
                                }
                        } else {

                                if( debug>0 )
                                        logHelper.log( "Can not find method '" + methodName + "'" );
                        }
                }

                if( type ) 
                        return true;
                else
                        return collector;

        }

}

