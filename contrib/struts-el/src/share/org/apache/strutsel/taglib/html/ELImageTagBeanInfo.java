/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELImageTagBeanInfo.java,v 1.3 2003/02/19 03:53:49 dmkarr Exp $
 * $Revision: 1.3 $
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
 * <code>org.apache.strutsel.taglib.html.ELImageTag</code> class.  It is needed
 * to override the default mapping of custom tag attribute names to class
 * attribute names.
 *<p>
 * This is necessary because the base class,
 * <code>org.apache.struts.taglib.html.ImageTag</code> defines some attributes
 * whose type is not <code>java.lang.String</code>, so the subclass needs to
 * define setter methods of a different name, which this class maps to.
 *<p>
 * Unfortunately, if a <code>BeanInfo</code> class needs to be provided to
 * change the mapping of one attribute, it has to specify the mappings of ALL
 * attributes, even if all the others use the expected mappings of "name" to
 * "method".
 */
public class ELImageTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[34];

        try {
            result[0] = new PropertyDescriptor("accesskey", ELImageTag.class,
                                               null, "setAccesskeyExpr");
            result[1] = new PropertyDescriptor("align", ELImageTag.class,
                                               null, "setAlignExpr");
            result[2] = new PropertyDescriptor("alt", ELImageTag.class,
                                               null, "setAltExpr");
            result[3] = new PropertyDescriptor("altKey", ELImageTag.class,
                                               null, "setAltKeyExpr");
            result[4] = new PropertyDescriptor("border", ELImageTag.class,
                                               null, "setBorderExpr");
            result[5] = new PropertyDescriptor("bundle", ELImageTag.class,
                                               null, "setBundleExpr");
            result[6] = new PropertyDescriptor("disabled", ELImageTag.class,
                                               null, "setDisabledExpr");
            result[7] = new PropertyDescriptor("indexed", ELImageTag.class,
                                               null, "setIndexedExpr");
            result[8] = new PropertyDescriptor("locale", ELImageTag.class,
                                               null, "setLocaleExpr");
            result[9] = new PropertyDescriptor("onblur", ELImageTag.class,
                                               null, "setOnblurExpr");
            result[10] = new PropertyDescriptor("onchange", ELImageTag.class,
                                               null, "setOnchangeExpr");
            result[11] = new PropertyDescriptor("onclick", ELImageTag.class,
                                               null, "setOnclickExpr");
            result[12] = new PropertyDescriptor("ondblclick",
                                               ELImageTag.class,
                                               null, "setOndblclickExpr");
            result[13] = new PropertyDescriptor("onfocus", ELImageTag.class,
                                               null, "setOnfocusExpr");
            result[14] = new PropertyDescriptor("onkeydown",
                                                ELImageTag.class,
                                               null, "setOnkeydownExpr");
            result[15] = new PropertyDescriptor("onkeypress",
                                                ELImageTag.class,
                                               null, "setOnkeypressExpr");
            result[16] = new PropertyDescriptor("onkeyup", ELImageTag.class,
                                               null, "setOnkeyupExpr");
            result[17] = new PropertyDescriptor("onmousedown",
                                               ELImageTag.class,
                                               null, "setOnmousedownExpr");
            result[18] = new PropertyDescriptor("onmousemove",
                                               ELImageTag.class,
                                               null, "setOnmousemoveExpr");
            result[19] = new PropertyDescriptor("onmouseout",
                                               ELImageTag.class,
                                               null, "setOnmouseoutExpr");
            result[20] = new PropertyDescriptor("onmouseover",
                                               ELImageTag.class,
                                               null, "setOnmouseoverExpr");
            result[21] = new PropertyDescriptor("onmouseup",
                                                ELImageTag.class,
                                               null, "setOnmouseupExpr");
            result[22] = new PropertyDescriptor("page", ELImageTag.class,
                                               null, "setPageExpr");
            result[23] = new PropertyDescriptor("pageKey", ELImageTag.class,
                                               null, "setPageKeyExpr");
            result[24] = new PropertyDescriptor("property", ELImageTag.class,
                                               null, "setPropertyExpr");
            result[25] = new PropertyDescriptor("src", ELImageTag.class,
                                               null, "setSrcExpr");
            result[26] = new PropertyDescriptor("srcKey", ELImageTag.class,
                                               null, "setSrcKeyExpr");
            result[27] = new PropertyDescriptor("style", ELImageTag.class,
                                               null, "setStyleExpr");
            result[28] = new PropertyDescriptor("styleClass",
                                               ELImageTag.class,
                                               null, "setStyleClassExpr");
            result[29] = new PropertyDescriptor("styleId", ELImageTag.class,
                                               null, "setStyleIdExpr");
            result[30] = new PropertyDescriptor("tabindex", ELImageTag.class,
                                               null, "setTabindexExpr");
            result[31] = new PropertyDescriptor("title", ELImageTag.class,
                                               null, "setTitleExpr");
            result[32] = new PropertyDescriptor("titleKey", ELImageTag.class,
                                               null, "setTitleKeyExpr");
            result[33] = new PropertyDescriptor("value", ELImageTag.class,
                                               null, "setValueExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
