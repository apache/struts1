/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/application/PropertyResolverImpl.java,v 1.1 2003/06/04 17:38:13 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/06/04 17:38:13 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
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

package org.apache.struts.faces.application;


import java.util.Map;
import javax.faces.el.PropertyNotFoundException;
import javax.faces.el.PropertyResolver;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.DynaActionForm;



/**
 * <p>Custom <code>PropertyResolver</code> implementation that adds support
 * for <code>DynaBean</code> property access to the facilities of the default
 * <code>PropertyResolver</code> provided by JavaServer Faces.</p>
 *
 * <p>This class implements the following specific rules:</p>
 * <ul>
 * <li>Indexed variants of each call are directly passed through to the
 *     <code>PropertyResolver</code> instance that we are wrapping.</li>
 * <li>If the specified base object is an instance of
 *     <code>DynaActionForm</code>, and the requested property name is
 *     <code>map</code>, maintain compatibility with the way that JSP and
 *     JSTL expressions can access this property:
 *     <ul>
 *     <li><code>getValue()</code> will return the result of calling
 *         <code>getMap()</code> on the base object.</li>
 *     <li><code>setValue()</code> will throw an exception, because the
 *         map of property values is a read-only property of the
 *         <code>DynaActionForm</code> class.</li>
 *     <li><code>isReadOnly()</code> returns <code>true</code>.</li>
 *     <li><code>getType()</code> returns the <code>Class</code> object
 *         for <code>java.util.Map</code>.</li>
 *     </ul></li>
 * <li>If the specified base object is an instance of
 *     <code>DynaBean</code>, provide access to its named properties
 *     as follows:
 *     <ul>
 *     <li><code>getValue()</code> will return the result of calling
 *         <code>get()</code> on the base object.</li>
 *     <li><code>setValue()</code> will call <code>set()</code>
 *         on the base object.</li>
 *     <li><code>isReadOnly()</code> returns <code>false</code> (because
 *         the DynaBean APIs provide no mechanism to make this determination,
 *         but most implementations will provide mutable properties).</li>
 *     <li><code>getType()</code> returns the <code>Class</code> object
 *         for the underlying dynamic property.</li>
 *     </ul></li>
 * <li>Named variant calls with any other type of base object are
 *     passed through to the <code>PropertyResolver</code> that we
 *     are wrapping.</li>
 * </ul>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/06/04 17:38:13 $
 */

public class PropertyResolverImpl extends PropertyResolver {


    // ------------------------------------------------------------ Constructor


    /**
     * <p>Construct a new <code>PropertyResolver</code> instance, wrapping the
     * specified instance using the Decorator pattern such that this class need
     * implement only the new functionality it supports, and not need to
     * re-implement the basic facilities.
     *
     * @param resolver The original resolver to be wrapped
     *
     * @exception NullPointerException if <code>resolver</code>
     *  is <code>null</code>
     */
    public PropertyResolverImpl(PropertyResolver resolver) {

        if (resolver == null) {
            throw new NullPointerException();
        }
        if (log.isDebugEnabled()) {
            log.debug("Creating new instance, wrapping resolver " +
                      resolver);
        }
        this.resolver = resolver;

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static final Log log =
        LogFactory.getLog(PropertyResolverImpl.class);


    /**
     * <p>The <code>PropertyResolver</code> instance that we are wrapping,
     * which will be used to perform operations on beans that are not
     * recognized by this class.</p>
     */
    private PropertyResolver resolver = null;


    // ----------------------------------------------- PropertyResolver Methods


    /**
     * <p>Return the value of the property with the specified name from
     * the specified base object.</p>
     *
     * @param base The base object whose property value is to be returned
     * @param name Name of the property to be returned
     *
     * @exception NullPointerException if <code>base</code> or
     *  <code>name</code> is <code>null</code>
     * @exception PropertyNotFoundException if the specified property name
     *  does not exist, or is not readable
     */
    public Object getValue(Object base, String name)
        throws PropertyNotFoundException {

        if ((base == null) || (name == null)) {
            throw new NullPointerException();
        } else if ((base instanceof DynaActionForm) &&
                   ("map".equals(name))) {
            if (log.isTraceEnabled()) {
                log.trace("Returning property map for DynaActionForm " + base
                          + "'");
            }
            return (((DynaActionForm) base).getMap());
        } else if (base instanceof DynaBean) {
            if (getDynaProperty((DynaBean) base, name) == null) {
                throw new PropertyNotFoundException(name);
            }
            Object value = ((DynaBean) base).get(name);
            if (log.isTraceEnabled()) {
                log.trace("Returning dynamic property '" + name +
                          "' for DynaBean '" + base + "' value '" +
                          value + "'");
            }
            return (value);
        } else {
            Object value = resolver.getValue(base, name);
            if (log.isTraceEnabled()) {
                log.trace("Delegating get of property '" + name +
                          "' for bean '" + base + "' value '" +
                          value + "'");
            }
            return (value);
        }

    }


    /**
     * <p>Return the value at the specified index of the specified
     * base object.</p>
     *
     * @param base The base object whose property value is to be returned
     * @param index Index of the value to return
     *
     * @exception IndexOutOfBoundsException if thrown by the underlying
     *  access to the base object
     * @exception NullPointerException if <code>base</code>
     *  is <code>null</code>
     * @exception PropertyNotFoundException if some other exception occurs 
     */
    public Object getValue(Object base, int index)
        throws PropertyNotFoundException {

        return (resolver.getValue(base, index));

    }


    /**
     * <p>Set the specified value of the property with the specified name on
     * the specified base object.</p>
     *
     * @param base The base object whose property value is to be set
     * @param name Name of the property to be set
     * @param value Value of the property to be set
     *
     * @exception NullPointerException if <code>base</code> or
     *  <code>name</code> is <code>null</code>
     * @exception PropertyNotFoundException if the specified property name
     *  does not exist, or is not writeable
     */
    public void setValue(Object base, String name, Object value)
        throws PropertyNotFoundException {

        if ((base == null) || (name == null)) {
            throw new NullPointerException();
        } else if ((base instanceof DynaActionForm) &&
                   ("map".equals(name))) {
            throw new PropertyNotFoundException(name);
        } else if (base instanceof DynaBean) {
            if (log.isTraceEnabled()) {
                log.trace("setting dynamic property '" + name +
                          "' for DynaBean '" + base +
                          "' to '" + value + "'");
            }
            if (getDynaProperty((DynaBean) base, name) == null) {
                throw new PropertyNotFoundException(name);
            }
            ((DynaBean) base).set(name, value);
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Delegating set of property '" + name +
                          "' for bean '" + base + "' to value '" +
                          value + "'");
            }
            resolver.setValue(base, name, value);
        }

    }


    /**
     * <p>Set the value at the specified index of the specified
     * base object.</p>
     *
     * @param base The base object whose property value is to be set
     * @param index Index of the value to set
     * @param value Value to be set
     *
     * @exception IndexOutOfBoundsException if thrown by the underlying
     *  access to the base object
     * @exception NullPointerException if <code>base</code>
     *  is <code>null</code>
     * @exception PropertyNotFoundException if some other exception occurs
     */
    public void setValue(Object base, int index, Object value)
        throws PropertyNotFoundException {

        resolver.setValue(base, index, value);

    }


    /**
     * <p>Return <code>true</code> if the specified property of the specified
     * base object is known to be immutable; otherwise, return
     * <code>false</code>.</p>
     *
     * @param base The base object whose property is to analyzed
     * @param name Name of the property to be analyzed
     *
     * @exception NullPointerException if <code>base</code> or
     *  <code>name</code> is <code>null</code>
     * @exception PropertyNotFoundException if the specified property name
     *  does not exist
     */
    public boolean isReadOnly(Object base, String name)
        throws PropertyNotFoundException {

        if ((base == null) || (name == null)) {
            throw new NullPointerException();
        } else if ((base instanceof DynaActionForm) &&
                   ("map".equals(name))) {
            return (true);
        } else if (base instanceof DynaBean) {
            if (getDynaProperty((DynaBean) base, name) == null) {
                throw new PropertyNotFoundException(name);
            }
            return (false);
        } else {
            return (resolver.isReadOnly(base, name));
        }

    }


    /**
     * <p>Return <code>true</code> if the value at the specified index of
     * the specified base object is known to be immutable; otherwise,
     * return <code>false</code>.</p>
     *
     * @param base The base object whose property is to analyzed
     * @param index Index of the value whose type is to be returned
     *
     * @exception IndexOutOfBoundsException if thrown by the underlying
     *  accessed to the indexed property
     * @exception NullPointerException if <code>base</code>
     *  is <code>null</code>
     * @exception PropertyNotFoundException if some other exception occurs
     */
    public boolean isReadOnly(Object base, int index)
        throws PropertyNotFoundException {

        return (resolver.isReadOnly(base, index));

    }


    /**
     * <p>Return the <code>java.lang.Class</code> representing the type of
     * the specified property of the specified base object, if it can be
     * determined; otherwise return <code>null</code>.</p>
     *
     * @param base The base object whose property is to analyzed
     * @param name Name of the property to be analyzed
     *
     * @exception NullPointerException if <code>base</code> or
     *  <code>name</code> is <code>null</code>
     * @exception PropertyNotFoundException if the specified property name
     *  does not exist
     */
    public Class getType(Object base, String name)
        throws PropertyNotFoundException {

        if ((base == null) || (name == null)) {
            throw new NullPointerException();
        } else if ((base instanceof DynaActionForm) &&
                   ("map".equals(name))) {
            return (Map.class);
        } else if (base instanceof DynaBean) {
            DynaProperty dynaProperty = getDynaProperty((DynaBean) base, name);
            if (dynaProperty != null) {
                return (dynaProperty.getType());
            } else {
                throw new PropertyNotFoundException(name);
            }
        } else {
            return (resolver.getType(base, name));
        }
    }


    /**
     * <p>Return the <code>java.lang.Class</code> representing the type of
     * value at the specified index of the specified base object, or
     * <code>null</code> if this value is <code>null</code>.</p>
     *
     * @param base The base object whose property is to analyzed
     * @param index Index of the value whose type is to be returned
     *
     * @exception IndexOutOfBoundsException if thrown by the underlying
     *  accessed to the indexed property
     * @exception NullPointerException if <code>base</code>
     *  is <code>null</code>
     * @exception PropertyNotFoundException if some other exception occurs
     */
    public Class getType(Object base, int index)
        throws PropertyNotFoundException {

        return (resolver.getType(base, index));

    }


    // -------------------------------------------------------- Private Methods


    /**
     * <p>Return the <code>DynaProperty</code> describing the specified
     * property of the specified <code>DynaBean</code>, or <code>null</code>
     * if there is no such property defined on the underlying
     * <code>DynaClass</code>.</p>
     *
     * @param bean <code>DynaBean</code> to be checked
     * @param name Name of the property to be checked
     */
    private DynaProperty getDynaProperty(DynaBean bean, String name)
        throws PropertyNotFoundException {

        DynaProperty dynaProperty = null;
        try {
            dynaProperty = bean.getDynaClass().getDynaProperty(name);
        } catch (IllegalArgumentException e) {
            ;
        }
        return (dynaProperty);

    }




}
