/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/services/factory/src/org/apache/struts/service/factory/Attic/ParameterMapping.java,v 1.2 2004/01/18 13:43:09 husted Exp $
 * $Revision: 1.2 $
 * $Date: 2004/01/18 13:43:09 $
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

import java.lang.Class;
import java.lang.ClassNotFoundException;

/** Container to store parameter info for bean template.
 *
 * @version $Revision: 1.2 $ $Date: 2004/01/18 13:43:09 $
 */
public class ParameterMapping {

        //------------------------------------------- protected variables

        /** Name of the parameter. Used to refer to parameter
         *  in action's bean registration from parameter-value tag
         */
        protected String name = null;

        /** Type of the parameter.
         */
        protected String type = null;

        /** Default value of the parameter
         */
        protected String value = null;

        /** Flag to override all values from another soures
         *  and use default value from <code>value</code>
         *  attribuet
         */
        protected boolean force = false;

        /** Source alias to search value for the parameter. 
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
         */
        protected String source = null;

        //---------------------------------------- properties support

        /** source setter
         *  @return String
         */
        public String getSource() {
                return source;
        }

        /** source getter
         * @param source New source value
         */
        public void setSource( String source ) {
                this.source = source;
        }

        /** return class for type, specified in
         *  <code>type</code> attribute
         *  @return Class
         *  @exception java.lang.ClassNotFoundException 
         */
        public Class getStub() throws ClassNotFoundException {
                return Class.forName( type );
        }

        /** name getter
         * @return String 
         */
        public String getName() {
                return name;
        }

        /** name setter
         *  @param name New name vaue
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
         *  @param type New type value
         */
        public void setType( String type ) {
                this.type = type;
        }

        /** value getter
         *  @return String
         */
        public String getValue() {
                return value;
        }

        /** value setter
         *  @param value New value for value attribute
         */
        public void setValue( String value ) {
                this.value = value;
        }

        /** force getter
         *  @return boolean
         */
        public boolean getForce() {
                return force;
        }

        /** force setter
         *  @param force New value for force attribute
         */
        public void setForce( boolean force ) {
                this.force = force;
        }

}
