/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELLinkTagBeanInfo.java,v 1.2 2002/12/31 04:17:26 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2002/12/31 04:17:26 $
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
 * <code>org.apache.strutsel.taglib.html.ELLinkTag</code> class.  It is needed
 * to override the default mapping of custom tag attribute names to class
 * attribute names.
 *<p>
 * This is necessary because the base class,
 * <code>org.apache.struts.taglib.html.LinkTag</code> defines some attributes
 * whose type is not <code>java.lang.String</code>, so the subclass needs to
 * define setter methods of a different name, which this class maps to.
 *<p>
 * Unfortunately, if a <code>BeanInfo</code> class needs to be provided to
 * change the mapping of one attribute, it has to specify the mappings of ALL
 * attributes, even if all the others use the expected mappings of "name" to
 * "method".
 */
public class ELLinkTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[36];

        try {
            result[0] = new PropertyDescriptor("accesskey", ELLinkTag.class,
                                               null, "setAccesskey");
            result[1] = new PropertyDescriptor("action", ELLinkTag.class,
                                               null, "setAction");
            result[2] = new PropertyDescriptor("anchor", ELLinkTag.class,
                                               null, "setAnchor");
            result[3] = new PropertyDescriptor("forward", ELLinkTag.class,
                                               null, "setForward");
            result[4] = new PropertyDescriptor("href", ELLinkTag.class,
                                               null, "setHref");
            // This attribute has a non-standard mapping.
            result[5] = new PropertyDescriptor("indexed", ELLinkTag.class,
                                               null, "setIndexedExpr");
            result[6] = new PropertyDescriptor("indexId", ELLinkTag.class,
                                               null, "setIndexId");
            result[7] = new PropertyDescriptor("linkName", ELLinkTag.class,
                                               null, "setLinkName");
            result[8] = new PropertyDescriptor("name", ELLinkTag.class,
                                               null, "setName");
            result[9] = new PropertyDescriptor("onblur", ELLinkTag.class,
                                               null, "setOnblur");
            result[10] = new PropertyDescriptor("onclick", ELLinkTag.class,
                                               null, "setOnclick");
            result[11] = new PropertyDescriptor("ondblclick",
                                               ELLinkTag.class,
                                               null, "setOndblclick");
            result[12] = new PropertyDescriptor("onfocus", ELLinkTag.class,
                                               null, "setOnfocus");
            result[13] = new PropertyDescriptor("onkeydown",
                                                ELLinkTag.class,
                                               null, "setOnkeydown");
            result[14] = new PropertyDescriptor("onkeypress",
                                                ELLinkTag.class,
                                               null, "setOnkeypress");
            result[15] = new PropertyDescriptor("onkeyup", ELLinkTag.class,
                                               null, "setOnkeyup");
            result[16] = new PropertyDescriptor("onmousedown",
                                               ELLinkTag.class,
                                               null, "setOnmousedown");
            result[17] = new PropertyDescriptor("onmousemove",
                                               ELLinkTag.class,
                                               null, "setOnmousemove");
            result[18] = new PropertyDescriptor("onmouseout",
                                               ELLinkTag.class,
                                               null, "setOnmouseout");
            result[19] = new PropertyDescriptor("onmouseover",
                                               ELLinkTag.class,
                                               null, "setOnmouseover");
            result[20] = new PropertyDescriptor("onmouseup",
                                                ELLinkTag.class,
                                               null, "setOnmouseup");
            result[21] = new PropertyDescriptor("page", ELLinkTag.class,
                                               null, "setPage");
            result[22] = new PropertyDescriptor("paramId", ELLinkTag.class,
                                               null, "setParamId");
            result[23] = new PropertyDescriptor("paramName", ELLinkTag.class,
                                               null, "setParamName");
            result[24] = new PropertyDescriptor("paramProperty",
                                                ELLinkTag.class,
                                               null, "setParamProperty");
            result[25] = new PropertyDescriptor("paramScope", ELLinkTag.class,
                                               null, "setParamScope");
            result[26] = new PropertyDescriptor("property", ELLinkTag.class,
                                               null, "setProperty");
            result[27] = new PropertyDescriptor("scope", ELLinkTag.class,
                                               null, "setScope");
            result[28] = new PropertyDescriptor("style", ELLinkTag.class,
                                               null, "setStyle");
            result[29] = new PropertyDescriptor("styleClass",
                                               ELLinkTag.class,
                                               null, "setStyleClass");
            result[30] = new PropertyDescriptor("styleId", ELLinkTag.class,
                                               null, "setStyleId");
            result[31] = new PropertyDescriptor("tabindex", ELLinkTag.class,
                                               null, "setTabindex");
            result[32] = new PropertyDescriptor("target", ELLinkTag.class,
                                               null, "setTarget");
            result[33] = new PropertyDescriptor("title", ELLinkTag.class,
                                               null, "setTitle");
            result[34] = new PropertyDescriptor("titleKey", ELLinkTag.class,
                                               null, "setTitleKey");
            result[35] = new PropertyDescriptor("transaction", ELLinkTag.class,
                                               null, "setTransactionExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
