/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELFrameTagBeanInfo.java,v 1.3 2003/02/19 03:52:49 dmkarr Exp $
 * $Revision: 1.3 $
 * $Date: 2003/02/19 03:52:49 $
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
 * <code>org.apache.strutsel.taglib.html.ELFrameTag</code> class.  It is needed
 * to override the default mapping of custom tag attribute names to class
 * attribute names.
 *<p>
 * This is necessary because the base class,
 * <code>org.apache.struts.taglib.html.FrameTag</code> defines some
 * attributes whose type is not <code>java.lang.String</code>, so the subclass
 * needs to define setter methods of a different name, which this class maps
 * to.
 *<p>
 * Unfortunately, if a <code>BeanInfo</code> class needs to be provided to
 * change the mapping of one attribute, it has to specify the mappings of ALL
 * attributes, even if all the others use the expected mappings of "name" to
 * "method".
 */
public class ELFrameTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[24];

        try {
            result[0] = new PropertyDescriptor("anchor", ELFrameTag.class,
                                               null, "setAnchorExpr");
            result[1] = new PropertyDescriptor("forward", ELFrameTag.class,
                                               null, "setForwardExpr");
            result[2] = new PropertyDescriptor("frameborder", ELFrameTag.class,
                                               null, "setFrameborderExpr");
            result[3] = new PropertyDescriptor("frameName", ELFrameTag.class,
                                               null, "setFrameNameExpr");
            result[4] = new PropertyDescriptor("href", ELFrameTag.class,
                                               null, "setHrefExpr");
            result[5] = new PropertyDescriptor("longdesc", ELFrameTag.class,
                                               null, "setLongdescExpr");
            result[6] = new PropertyDescriptor("marginheight", ELFrameTag.class,
                                               null, "setMarginheightExpr");
            result[7] = new PropertyDescriptor("marginwidth", ELFrameTag.class,
                                               null, "setMarginwidthExpr");
            result[8] = new PropertyDescriptor("name", ELFrameTag.class,
                                               null, "setNameExpr");
            result[9] = new PropertyDescriptor("noresize",
                                               ELFrameTag.class,
                                               null, "setNoresizeExpr");
            result[10] = new PropertyDescriptor("page", ELFrameTag.class,
                                               null, "setPageExpr");
            result[11] = new PropertyDescriptor("paramId",
                                                ELFrameTag.class,
                                               null, "setParamIdExpr");
            result[12] = new PropertyDescriptor("paramName",
                                                ELFrameTag.class,
                                               null, "setParamNameExpr");
            result[13] = new PropertyDescriptor("paramProperty",
                                                ELFrameTag.class,
                                               null, "setParamPropertyExpr");
            result[14] = new PropertyDescriptor("paramScope",
                                               ELFrameTag.class,
                                               null, "setParamScopeExpr");
            result[15] = new PropertyDescriptor("property",
                                               ELFrameTag.class,
                                               null, "setPropertyExpr");
            result[16] = new PropertyDescriptor("scope",
                                               ELFrameTag.class,
                                               null, "setScopeExpr");
            result[17] = new PropertyDescriptor("scrolling",
                                               ELFrameTag.class,
                                               null, "setScrollingExpr");
            result[18] = new PropertyDescriptor("style", ELFrameTag.class,
                                               null, "setStyleExpr");
            result[19] = new PropertyDescriptor("styleClass",
                                               ELFrameTag.class,
                                               null, "setStyleClassExpr");
            result[20] = new PropertyDescriptor("styleId", ELFrameTag.class,
                                               null, "setStyleIdExpr");
            result[21] = new PropertyDescriptor("title", ELFrameTag.class,
                                               null, "setTitleExpr");
            result[22] = new PropertyDescriptor("titleKey", ELFrameTag.class,
                                               null, "setTitleKeyExpr");
            result[23] = new PropertyDescriptor("transaction", ELFrameTag.class,
                                               null, "setTransactionExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
