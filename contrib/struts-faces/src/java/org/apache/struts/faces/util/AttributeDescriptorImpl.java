/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/util/Attic/AttributeDescriptorImpl.java,v 1.1 2003/03/07 03:22:45 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2003/03/07 03:22:45 $
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

package org.apache.struts.faces.util;


import java.util.Locale;
import javax.faces.component.AttributeDescriptor;


/**
 * <p>Concrete implementation of
 * <code>javax.faces.component.AttributeDescriptor</code> for the
 * <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2003/03/07 03:22:45 $
 */

public class AttributeDescriptorImpl extends AttributeDescriptor {


    /**
     * <p>The fully qualified class name of the implementation class for
     * this attribute.</p>
     */
    private String className = null;

    public String getClassName() {
        return (this.className);
    }

    public void setClassName(String className) {
        this.className = className;
    }


    /**
     * <p>The help text describing this attribute.</p>
     */
    private String description = null;

    public String getDescription() {
        return (this.description);
    }

    public void setDescription(String description) {
        if (description != null) {
            description = description.trim();
            if (description.length() > 0) {
                this.description = description;
            }
        } else {
            this.description = null;
        }
    }

    // FIXME - i18n
    public String getDescription(Locale locale) {
        return (getDescription());
    }


    /**
     * <p>The display name of this attribute.</p>
     */
    private String displayName = null;

    public String getDisplayName() {
        return (this.displayName);
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // FIXME - i18n
    public String getDisplayName(Locale locale) {
        return (this.displayName);
    }


    /**
     * <p>The attribute name of this attribute.</p>
     */
    private String name = null;

    public String getName() {
        return (this.name);
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * <p>Return the Java class representing the data type of the legal
     * values for this attribute.</p>
     */
    public Class getType() {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = getClass().getClassLoader();
        }
        try {
            return (loader.loadClass(getClassName()));
        } catch (Exception e) {
            return (null);
        }

    }



}
