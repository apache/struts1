/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELFormTagBeanInfo.java,v 1.1 2003/02/19 03:45:25 dmkarr Exp $
 * $Revision: 1.1 $
 * $Date: 2003/02/19 03:45:25 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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

package org.apache.strutsel.taglib.html;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.SimpleBeanInfo;

/**
 * This is the <code>BeanInfo</code> descriptor for the
 * <code>org.apache.strutsel.taglib.html.ELFormTag</code> class.  It is
 * needed to override the default mapping of custom tag attribute names to
 * class attribute names.
 */
public class ELFormTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[14];

        try {
            result[0] = new PropertyDescriptor("action", ELFormTag.class,
                                               null, "setActionExpr");
            result[1] = new PropertyDescriptor("enctype", ELFormTag.class,
                                               null, "setEnctypeExpr");
            result[2] = new PropertyDescriptor("focus", ELFormTag.class,
                                               null, "setFocusExpr");
            result[3] = new PropertyDescriptor("focusIndex", ELFormTag.class,
                                               null, "setFocusIndexExpr");
            result[4] = new PropertyDescriptor("method", ELFormTag.class,
                                               null, "setMethodExpr");
            result[5] = new PropertyDescriptor("name", ELFormTag.class,
                                               null, "setNameExpr");
            result[6] = new PropertyDescriptor("onreset", ELFormTag.class,
                                               null, "setOnresetExpr");
            result[7] = new PropertyDescriptor("onsubmit", ELFormTag.class,
                                               null, "setOnsubmitExpr");
            result[8] = new PropertyDescriptor("scope", ELFormTag.class,
                                               null, "setScopeExpr");
            result[9] = new PropertyDescriptor("style", ELFormTag.class,
                                               null, "setStyleExpr");
            result[10] = new PropertyDescriptor("styleClass", ELFormTag.class,
                                               null, "setStyleClassExpr");
            result[11] = new PropertyDescriptor("styleId", ELFormTag.class,
                                               null, "setStyleIdExpr");
            result[12] = new PropertyDescriptor("target", ELFormTag.class,
                                               null, "setTargetExpr");
            result[13] = new PropertyDescriptor("type", ELFormTag.class,
                                               null, "setTypeExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
