/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/services/factory/src/org/apache/struts/service/factory/Attic/Template.java,v 1.1 2001/07/25 20:42:22 oalexeev Exp $
 * $Revision: 1.1 $
 * $Date: 2001/07/25 20:42:22 $
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

import java.util.ArrayList;
import java.util.Iterator;

/** Bean template. Contains info about type of the target class 
 *  (result can be bean of another class, for example, collection
 *  or array of beans of target class).
 *
 * @author Oleg V Alexeev
 * @version $Revision: 1.1 $ $Date: 2001/07/25 20:42:22 $
 */
public class Template {

        //---------------------------------------------- protected variables

        /** Name of the bean template. Bean registrations refers
         *  to this template by the name attribute.
         */
        protected String name = null;

        /** Type of the bean.
         */
        protected String type = null;


        /** List of parameter mappings needed to be passed to the
         *  create method in factory class to create target bean.
         */
        protected ArrayList parameters = null;

        //---------------------------------------------- protected methods

        /** Proxy accessor to the list of parameter mappings
         */
        protected ArrayList initStore() {
                if( parameters==null )
                        parameters = new ArrayList();
                return parameters;
        }

        //---------------------------------------------- public methods

        /** Adds parameter mapping to the internal list. Digester support.
         *  
         *  @param parameter New parameter mapping
         */
        public void addParameter( ParameterMapping parameter ) {
                initStore().add( parameter );
        }

        /** Clears internal list of parameter mappings.
         */
        public void destroy() {
                if( parameters!=null )
                        parameters.clear();
        }

        //---------------------------------------------- properties support

        /** Accessor to the internal list of parameter mappings.
         *  
         *  @return Iterator Iterator via internal list of paraeter mappings.
         */
        public Iterator getParameters() {
                if( parameters!=null )
                        return parameters.iterator();
                else
                        return null;                                
        }

        /** Returns count of registered parameter mappings.
         * 
         *  @return int
         */
        public int getParametersCount() {
                if( parameters!=null )
                        return parameters.size();
                else
                        return 0;                                
        }

        /** name getter
         *  @return String
         */
        public String getName() {
                return name;
        }

        /** name setter
         *  @param name New name value
         */
        public void setName( String name ) {
                this.name = name;
        }

        /** type getter
         *  @return String
         */
        public String getType() {
                return type;
        }

        /** type setter
         *  @param type New typa value
         */
        public void setType( String type ) {
                this.type = type;
        }

}
