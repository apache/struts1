/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELRewriteTagBeanInfo.java,v 1.2 2003/02/19 03:53:49 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2003/02/19 03:53:49 $
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
 * <code>org.apache.strutsel.taglib.html.ELRewriteTag</code> class.  It is
 * needed to override the default mapping of custom tag attribute names to
 * class attribute names.
 *<p>
 * This is necessary because the base class,
 * <code>org.apache.struts.taglib.html.RewriteTag</code> defines some
 * attributes whose type is not <code>java.lang.String</code>, so the subclass
 * needs to define setter methods of a different name, which this class maps
 * to.
 *<p>
 * Unfortunately, if a <code>BeanInfo</code> class needs to be provided to
 * change the mapping of one attribute, it has to specify the mappings of ALL
 * attributes, even if all the others use the expected mappings of "name" to
 * "method".
 */
public class ELRewriteTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[12];

        try {
            result[0] = new PropertyDescriptor("anchor", ELRewriteTag.class,
                                               null, "setAnchorExpr");
            result[1] = new PropertyDescriptor("forward", ELRewriteTag.class,
                                               null, "setForwardExpr");
            result[2] = new PropertyDescriptor("href", ELRewriteTag.class,
                                               null, "setHrefExpr");
            result[3] = new PropertyDescriptor("name", ELRewriteTag.class,
                                               null, "setNameExpr");
            result[4] = new PropertyDescriptor("page", ELRewriteTag.class,
                                                null, "setPageExpr");
            result[5] = new PropertyDescriptor("paramId", ELRewriteTag.class,
                                                null, "setParamIdExpr");
            result[6] = new PropertyDescriptor("paramName", ELRewriteTag.class,
                                                null, "setParamNameExpr");
            result[7] = new PropertyDescriptor("paramProperty",
                                                ELRewriteTag.class,
                                                null, "setParamPropertyExpr");
            result[8] = new PropertyDescriptor("paramScope",
                                                ELRewriteTag.class,
                                                null, "setParamScopeExpr");
            result[9] = new PropertyDescriptor("property", ELRewriteTag.class,
                                                null, "setPropertyExpr");
            result[10] = new PropertyDescriptor("scope", ELRewriteTag.class,
                                                null, "setScopeExpr");
            result[11] = new PropertyDescriptor("transaction",
                                                ELRewriteTag.class,
                                                null, "setTransactionExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
