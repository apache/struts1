/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/share/org/apache/strutsel/taglib/html/ELResetTagBeanInfo.java,v 1.2 2003/02/19 03:53:49 dmkarr Exp $
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
 * <code>org.apache.strutsel.taglib.html.ELResetTag</code> class.  It is needed
 * to override the default mapping of custom tag attribute names to class
 * attribute names.
 *<p>
 * In particular, it provides for the mapping of the custom tag attribute
 * <code>disabled</code> to the instance variable <code>disabledExpr</code>.
 *<p>
 * This is necessary because the base class,
 * <code>org.apache.struts.taglib.html.ResetTag</code> already defines this
 * attribute, of type <code>boolean</code>, and the <code>ELResetTag</code>
 * class has to be able to see this values as a <code>String</code> type in
 * order to evaluate it with the JSTL EL engine.
 *<p>
 * Unfortunately, if a <code>BeanInfo</code> class needs to be provided to
 * change the mapping of one attribute, it has to specify the mappings of ALL
 * attributes, even if all the others use the expected mappings of "name" to
 * "method".
 */
public class ELResetTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[25];

        try {
            result[0] = new PropertyDescriptor("accesskey", ELResetTag.class,
                                               null, "setAccesskeyExpr");
            result[1] = new PropertyDescriptor("alt", ELResetTag.class,
                                               null, "setAltExpr");
            result[2] = new PropertyDescriptor("altKey", ELResetTag.class,
                                               null, "setAltKeyExpr");
            result[3] = new PropertyDescriptor("disabled", ELResetTag.class,
                                               null, "setDisabledExpr");
            result[4] = new PropertyDescriptor("onblur", ELResetTag.class,
                                               null, "setOnblurExpr");
            result[5] = new PropertyDescriptor("onchange", ELResetTag.class,
                                               null, "setOnchangeExpr");
            result[6] = new PropertyDescriptor("onclick", ELResetTag.class,
                                               null, "setOnclickExpr");
            result[7] = new PropertyDescriptor("ondblclick",
                                               ELResetTag.class,
                                               null, "setOndblclickExpr");
            result[8] = new PropertyDescriptor("onfocus", ELResetTag.class,
                                               null, "setOnfocusExpr");
            result[9] = new PropertyDescriptor("onkeydown",
                                                ELResetTag.class,
                                               null, "setOnkeydownExpr");
            result[10] = new PropertyDescriptor("onkeypress",
                                                ELResetTag.class,
                                               null, "setOnkeypressExpr");
            result[11] = new PropertyDescriptor("onkeyup", ELResetTag.class,
                                               null, "setOnkeyupExpr");
            result[12] = new PropertyDescriptor("onmousedown",
                                               ELResetTag.class,
                                               null, "setOnmousedownExpr");
            result[13] = new PropertyDescriptor("onmousemove",
                                               ELResetTag.class,
                                               null, "setOnmousemoveExpr");
            result[14] = new PropertyDescriptor("onmouseout",
                                               ELResetTag.class,
                                               null, "setOnmouseoutExpr");
            result[15] = new PropertyDescriptor("onmouseover",
                                               ELResetTag.class,
                                               null, "setOnmouseoverExpr");
            result[16] = new PropertyDescriptor("onmouseup",
                                                ELResetTag.class,
                                               null, "setOnmouseupExpr");
            result[17] = new PropertyDescriptor("property", ELResetTag.class,
                                               null, "setPropertyExpr");
            result[18] = new PropertyDescriptor("style", ELResetTag.class,
                                               null, "setStyleExpr");
            result[19] = new PropertyDescriptor("styleClass",
                                               ELResetTag.class,
                                               null, "setStyleClassExpr");
            result[20] = new PropertyDescriptor("styleId", ELResetTag.class,
                                               null, "setStyleIdExpr");
            result[21] = new PropertyDescriptor("tabindex", ELResetTag.class,
                                               null, "setTabindexExpr");
            result[22] = new PropertyDescriptor("title", ELResetTag.class,
                                               null, "setTitleExpr");
            result[23] = new PropertyDescriptor("titleKey", ELResetTag.class,
                                               null, "setTitleKeyExpr");
            result[24] = new PropertyDescriptor("value", ELResetTag.class,
                                               null, "setValueExpr");
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
