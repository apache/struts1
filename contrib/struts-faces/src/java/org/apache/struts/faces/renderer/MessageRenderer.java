/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-faces/src/java/org/apache/struts/faces/renderer/MessageRenderer.java,v 1.3 2003/07/27 06:43:16 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2003/07/27 06:43:16 $
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

package org.apache.struts.faces.renderer;


import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;


/**
 * <p><code>Renderer</code> implementation for the <code>message</code> tag
 * from the <em>Struts-Faces Integration Library</em>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2003/07/27 06:43:16 $
 */

public class MessageRenderer extends WriteRenderer {


    // ------------------------------------------------------- Static Variables


    /**
     * <p>The <code>Log</code> instance for this class.</p>
     */
    private static Log log = LogFactory.getLog(MessageRenderer.class);


    // --------------------------------------------------------- Public Methods


    // ------------------------------------------------------ Protected Methods


    /**
     * <p>Return the message format String to be processed for this message.
     * </p>
     *
     * @param context FacesContext for the response we are creating
     * @param component Component to be rendered
     *
     * @exception IllegalArgumentException if no MessageResources bundle
     *  can be found
     * @exception IllegalArgumentException if no message key can be found
     */
    protected String getText(FacesContext context, UIComponent component) {

        // Look up the MessageResources bundle to be used
        String bundle = (String) component.getAttribute("bundle");
        if (bundle == null) {
            bundle = Globals.MESSAGES_KEY;
        }
        MessageResources resources = (MessageResources)
            context.getExternalContext().getApplicationMap().get(bundle);
        if (resources == null) { // FIXME - i18n
            throw new IllegalArgumentException("MessageResources bundle " +
                                               bundle + " not found");
        }

        // Look up the message key to be used
        Object value = component.getAttribute("key");
        if (value == null) {
            value = ((UIOutput) component).currentValue(context);
        }
        if (value == null) { // FIXME - i18n
            throw new NullPointerException("Component " +
                                           component.getComponentId() +
                                           " has no current value");
        }
        String key = value.toString();

        // Build the substitution arguments list
        ArrayList list = new ArrayList();
        Iterator kids = component.getChildren();
        while (kids.hasNext()) {
            UIComponent kid = (UIComponent) kids.next();
            if (!(kid instanceof UIParameter)) {
                continue;
            }
            list.add(((UIParameter) kid).currentValue(context));
        }
        Object args[] = (Object[]) list.toArray(new Object[list.size()]);

        // Look up the requested message
        return (resources.getMessage(context.getLocale(), key, args));

    }


}
