package org.apache.struts.scaffold;


import org.apache.struts.action.RequestActionMapping;

/**
 * Enhanced base ActionMapping.
 * @version $Revision: 1.2 $ $Date: 2004/01/18 13:43:09 $
 */
public class BaseActionMapping extends RequestActionMapping {

    /**
     * Forward compatabilty with Struts 1.1.
     * Comment or remove this field for Stuts 1.1
     */
    private static boolean configured = false;

    /**
     * Message if they try to set a frozen configuration property.
     */
    private static String FROZEN_MESSAGE = "Configuration is frozen";

    /**
     * Adds submitPath to RequestActionMapping.
     *
     * This is useful when using one mapping to acquire a
     * blank or prepopulated form, and then submitting that
     * form to another mapping to complete the insert or update.
     *
     * It is also useful when reusing the same base form
     * or result list in different workflows.
     * Each mapping can indicate where to return,
     * for its own workflow.
     *
     * The current mapping is stored in the request under
     * the Globals.MAPPING_KEY (or the equivalent Struts 1.0
     * Action.MAPPING_KEY).
     *
     * You can access this property, or any mapping property,
     * using <bean:define>, or the equivalent, and then using
     * a Runtime Expression to use the property with the
     * <code>action</code> property of the html:form tag.
     *
     * To modify runtime behavior depending on whether returnPath
     * has been set, use the <logic:present> tag to either
     * use the returnPath or some alternate property.
     *
     * To use more than one-time property in a response,
     * pass a helper bean from the action instead.
     */
    protected String submitPath = null;
    public String getSubmitPath() {
        return this.submitPath;
    }
    public void setSubmitPath(String submitPath) {
        if (configured) {
            throw new IllegalStateException(FROZEN_MESSAGE);
        }
        this.submitPath = submitPath;
    }


} // end BaseActionMapping


/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/BaseActionMapping.java,v 1.2 2004/01/18 13:43:09 husted Exp $
 * $Revision: 1.2 $
 * $Date: 2004/01/18 13:43:09 $
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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
**/




