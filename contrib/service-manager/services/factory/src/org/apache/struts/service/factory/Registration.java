/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/services/factory/src/org/apache/struts/service/factory/Attic/Registration.java,v 1.1 2001/07/25 20:42:22 oalexeev Exp $
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

import java.util.HashMap;

/** Bean registration in action mapping.
 *
 * @author Oleg V Alexeev
 * @version $Revision: 1.1 $ $Date: 2001/07/25 20:42:22 $
 */
public class Registration {

        //---------------------------------------- protected variables

        /** Bean template name to use
         */
        protected String name = null;

        /** Factory mapping name to use
         */
        protected String factory = null;

        /** Action path to limk to
         */
        protected String path = null;

        /** Target scope to save bean - session or request
         */
        protected String scope = null;

        /** Is this bean necessary to this action?
         *  If true then Error 500 will be generated 
         *  after bean creation fail
         */
        protected boolean necessary = false;

        /** Alias to store bean in target scope.
         *  If nothing specified then name attribute used.
         */
        protected String alias = null;

        /** Hard parameter values mapping for this registration
         */
        protected HashMap parameterValues = null;

        //-------------------------------------------- protected methods

        /** Return HashMap instance to be used as Map of
         *  hard parameter value mappings.
         */
        protected HashMap initParameterValues() {
                if( parameterValues==null ) {
                        parameterValues = new HashMap();
                }
                return parameterValues;
        }

        //-------------------------------------------- public methods

        /** Search for hard parameter mapping and return it if
         *  find one.
         *  
         *  @param name Name of the parameter to search
         *  @return String
         */
        public String getParameterValue( String name ) {
                if( parameterValues==null )
                        return null;
                else
                        return (String)parameterValues.get( name );
        }

        /** Adds hard parameter value mapping. Digester support.
         *  
         *  @param parameter Harad parameter mapping
         */
        public void addParameterValue( ParameterValue parameter ) {
                initParameterValues().put( parameter.getName(), parameter.getValue() );
        }

        //-------------------------------------------- properties support

        /** name getter
         *  @return String
         */
        public String getName() {
                return name;
        }

        /** name setter
         *  @param  name New name value
         */
        public void setName( String name ) {
                this.name = name;
        }

        /** factory name getter
         *  @return String
         */
        public String getFactory() {
                return factory;
        }

        /** factory name setter
         *  @param factory New factory name
         */
        public void setFactory( String factory ) {
                this.factory = factory;
        }

        /** path getter
         *  @return String
         */
        public String getPath() {
                return path;
        }

        /** path setter
         *  @param path New path value
         */
        public void setPath( String path ) {
                this.path = path;
        }

        /** scope getter
         *  @return String
         */
        public String getScope() {
                return scope;
        }

        /** scope setter
         *  @param scope
         */
        public void setScope( String scope ) {
                this.scope = scope;
        }

        /** necessary getter
         *  @return boolean
         */
        public boolean getNecessary() {
                return necessary;
        }

        /** neccesary setter
         *  @param necessary New value for necessary attribute
         */
        public void setNecessary( boolean necessary ) {
                this.necessary = necessary;
        }

        /** alias getter
         *  @return String
         */
        public String getAlias() {
                return alias;
        }

        /** alias setter
         *  @param alias New alias value
         */
        public void setAlias( String alias ) {
                this.alias = alias;
        }

}
