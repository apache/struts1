/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/src/org/apache/struts/service/Attic/InitServiceManagerRule.java,v 1.2 2001/07/18 04:22:19 oalexeev Exp $
 * $Revision: 1.2 $
 * $Date: 2001/07/18 04:22:19 $
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

import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.xml.sax.AttributeList;

/** 
 * @author Oleg V Alexeev
 * @version $Revision: 1.2 $ $Date: 2001/07/18 04:22:19 $
 */
public class InitServiceManagerRule extends Rule {

        protected ServiceManager manager = null;
        
        public InitServiceManagerRule( Digester digester, ServiceManager manager ) {
                super( digester );
                this.manager = manager;
        }

        public void begin( AttributeList attributes ) throws Exception {
                digester.push( manager );
        }

        public void end() throws Exception {

                manager.getLogHelper().log( "Rebuild registrations for ServiceManager class - '" +  
                        manager.getClass().getName() + "'" );

                HashMap serviceRegistrations = manager.getServiceRegistrations();
                HashMap processRegistrations = manager.getProcessRegistrations();
                HashMap processSubscriptions = null;
                Iterator serviceIterator = serviceRegistrations.keySet().iterator();
                Iterator subscrIterator = null;
                ServiceRegistration serviceRegistration = null;
                ProcessRegistration processRegistration = null;
                ProcessSubscription processSubscription = null;
                while( serviceIterator.hasNext() ) {
                        serviceRegistration = (ServiceRegistration)serviceRegistrations.get( 
                                (String)serviceIterator.next() );

                        manager.getLogHelper().log( "Process service registration name - " +  
                                "'" + serviceRegistration.getName() + "'" );

                        processSubscriptions = serviceRegistration.getSubscriptions() ;
                        subscrIterator = processSubscriptions.keySet().iterator();
                        while( subscrIterator.hasNext() ) {
                                processSubscription = (ProcessSubscription)processSubscriptions.get( 
                                        (String)subscrIterator.next() );

                                manager.getLogHelper().log( "Process subscription for process - '" +  
                                        processSubscription.getProcessName() + "'" );

                                processRegistration = (ProcessRegistration)processRegistrations.get( 
                                        processSubscription.getProcessName() );

                                manager.getLogHelper().log( "Process registration of process - '" +  
                                        processRegistration.getName() + 
                                        "' subscribe service '" + serviceRegistration.getName() + 
                                        "' of class '" + serviceRegistration.getService().getClass().getName() +
                                        "' to process '" + processRegistration.getName() + "'" );

                                processRegistration.subscribeService( serviceRegistration.getService() );


                        }
                }

                digester.pop();

        }

        public void finish() throws Exception {
                manager = null;
        }

}
