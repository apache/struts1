/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELImgTagBeanInfo.java,v 1.2 2003/02/27 14:21:27 dmkarr Exp $
 * $Revision: 1.2 $
 * $Date: 2003/02/27 14:21:27 $
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
 * <code>org.apache.strutsel.taglib.html.ELImgTag</code> class.  It is
 * needed to override the default mapping of custom tag attribute names to
 * class attribute names.
 */
public class ELImgTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[41];

        try {
            result[0] = new PropertyDescriptor("accesskey", ELImgTag.class,
                                               null, "setAccesskeyExpr");
            result[1] = new PropertyDescriptor("align", ELImgTag.class,
                                               null, "setAlignExpr");
            result[2] = new PropertyDescriptor("alt", ELImgTag.class,
                                               null, "setAltExpr");
            result[3] = new PropertyDescriptor("altKey", ELImgTag.class,
                                               null, "setAltKeyExpr");
            result[4] = new PropertyDescriptor("border", ELImgTag.class,
                                               null, "setBorderExpr");
            result[5] = new PropertyDescriptor("bundle", ELImgTag.class,
                                               null, "setBundleExpr");
            result[6] = new PropertyDescriptor("height", ELImgTag.class,
                                               null, "setHeightExpr");
            result[7] = new PropertyDescriptor("hspace", ELImgTag.class,
                                               null, "setHspaceExpr");
            result[8] = new PropertyDescriptor("imageName", ELImgTag.class,
                                               null, "setImageNameExpr");
            result[9] = new PropertyDescriptor("ismap", ELImgTag.class,
                                               null, "setIsmapExpr");
            result[10] = new PropertyDescriptor("locale", ELImgTag.class,
                                               null, "setLocaleExpr");
            result[11] = new PropertyDescriptor("lowsrc", ELImgTag.class,
                                               null, "setLowsrcExpr");
            result[12] = new PropertyDescriptor("name", ELImgTag.class,
                                               null, "setNameExpr");
            result[13] = new PropertyDescriptor("onclick", ELImgTag.class,
                                               null, "setOnclickExpr");
            result[14] = new PropertyDescriptor("ondblclick", ELImgTag.class,
                                               null, "setOndblclickExpr");
            result[15] = new PropertyDescriptor("onkeydown", ELImgTag.class,
                                               null, "setOnkeydownExpr");
            result[16] = new PropertyDescriptor("onkeypress", ELImgTag.class,
                                               null, "setOnkeypressExpr");
            result[17] = new PropertyDescriptor("onkeyup", ELImgTag.class,
                                               null, "setOnkeyupExpr");
            result[18] = new PropertyDescriptor("onmousedown", ELImgTag.class,
                                               null, "setOnmousedownExpr");
            result[19] = new PropertyDescriptor("onmousemove", ELImgTag.class,
                                               null, "setOnmousemoveExpr");
            result[20] = new PropertyDescriptor("onmouseout", ELImgTag.class,
                                               null, "setOnmouseoutExpr");
            result[21] = new PropertyDescriptor("onmouseover", ELImgTag.class,
                                               null, "setOnmouseoverExpr");
            result[22] = new PropertyDescriptor("onmouseup", ELImgTag.class,
                                               null, "setOnmouseupExpr");
            result[23] = new PropertyDescriptor("page", ELImgTag.class,
                                               null, "setPageExpr");
            result[24] = new PropertyDescriptor("pageKey", ELImgTag.class,
                                               null, "setPageKeyExpr");
            result[25] = new PropertyDescriptor("paramId", ELImgTag.class,
                                               null, "setParamIdExpr");
            result[26] = new PropertyDescriptor("paramName", ELImgTag.class,
                                               null, "setParamNameExpr");
            result[27] = new PropertyDescriptor("paramProperty", ELImgTag.class,
                                               null, "setParamPropertyExpr");
            result[28] = new PropertyDescriptor("paramScope", ELImgTag.class,
                                               null, "setParamScopeExpr");
            result[29] = new PropertyDescriptor("property", ELImgTag.class,
                                               null, "setPropertyExpr");
            result[30] = new PropertyDescriptor("scope", ELImgTag.class,
                                               null, "setScopeExpr");
            result[31] = new PropertyDescriptor("src", ELImgTag.class,
                                               null, "setSrcExpr");
            result[32] = new PropertyDescriptor("srcKey", ELImgTag.class,
                                               null, "setSrcKeyExpr");
            result[33] = new PropertyDescriptor("style", ELImgTag.class,
                                               null, "setStyleExpr");
            result[34] = new PropertyDescriptor("styleClass", ELImgTag.class,
                                               null, "setStyleClassExpr");
            result[35] = new PropertyDescriptor("styleId", ELImgTag.class,
                                               null, "setStyleIdExpr");
            result[36] = new PropertyDescriptor("title", ELImgTag.class,
                                               null, "setTitleExpr");
            result[37] = new PropertyDescriptor("titleKey", ELImgTag.class,
                                               null, "setTitleKeyExpr");
            result[38] = new PropertyDescriptor("usemap", ELImgTag.class,
                                               null, "setUsemapExpr");
            result[39] = new PropertyDescriptor("vspace", ELImgTag.class,
                                               null, "setVspaceExpr");
            result[40] = new PropertyDescriptor("width", ELImgTag.class,
                                               null, "setWidthExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
