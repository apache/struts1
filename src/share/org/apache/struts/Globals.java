/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/Globals.java,v 1.5 2003/02/16 02:51:32 craigmcc Exp $
 * $Revision: 1.5 $
 * $Date: 2003/02/16 02:51:32 $
 *
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


package org.apache.struts;


import java.io.Serializable;


/**
 * <p>Global manifest constants for the entire Struts Framework.</p>
 *
 * <p>Many of these constants were initially defined in <code>Action</code>,
 * but were moved here so that they could be referenced without referencing
 * the <code>Action</code> class itself.  For backwards compatibility,
 * constant references there point at this class, and the constant values
 * themselves have not changed.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.5 $ $Date: 2003/02/16 02:51:32 $
 */

public class Globals implements Serializable {


    // ----------------------------------------------------- Manifest Constants


    /**
     * The context attributes key under which our <code>ActionServlet</code>
     * instance will be stored.
     *
     * @since Struts 1.1
     */
    public static final String ACTION_SERVLET_KEY =
        "org.apache.struts.action.ACTION_SERVLET";


    /**
     * <p>The base of the context attributes key under which our
     * <code>ModuleConfig</code> data structure will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     *
     * <p>For each request processed by the controller servlet, the
     * <code>ModuleConfig</code> object for the module selected by
     * the request URI currently being processed will also be exposed under
     * this key as a request attribute.</p>
     *
     * @since Struts 1.1
     * @deprecated Use MODULE_KEY
     */
    public static final String APPLICATION_KEY =
        "org.apache.struts.action.MODULE";


    /**
     * <p>The request attributes key under which a boolean <code>true</code>
     * value should be stored if this request was cancelled.</p>
     *
     * @since Struts 1.1
     */
    public static final String CANCEL_KEY =
        "org.apache.struts.action.CANCEL";


    /**
     * <p>The base of the context attributes key under which our
     * <code>ModuleConfig</code> data structure will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     *
     * <p>For each request processed by the controller servlet, the
     * <code>ModuleConfig</code> object for the module selected by
     * the request URI currently being processed will also be exposed under
     * this key as a request attribute.</p>
     *
     * @since Struts 1.1
     */
    public static final String MODULE_KEY =
        "org.apache.struts.action.MODULE";


    /**
     * The context attributes key under which our <strong>default</strong>
     * configured data source (which must implement
     * <code>javax.sql.DataSource</code>) is stored,
     * if one is configured for this module.
     */
    public static final String DATA_SOURCE_KEY =
      "org.apache.struts.action.DATA_SOURCE";


    /**
     * The request attributes key under which your action should store an
     * <code>org.apache.struts.action.ActionErrors</code> object, if you
     * are using the corresponding custom tag library elements.
     */
    public static final String ERROR_KEY =
      "org.apache.struts.action.ERROR";


    /**
     * The request attributes key under which Struts custom tags might store a
     * <code>Throwable</code> that caused them to report a JspException at
     * runtime.  This value can be used on an error page to provide more
     * detailed information about what really went wrong.
     */
    public static final String EXCEPTION_KEY =
        "org.apache.struts.action.EXCEPTION";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionFormBeans</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig
     */
    public static final String FORM_BEANS_KEY =
        "org.apache.struts.action.FORM_BEANS";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionForwards</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig.
     */
    public static final String FORWARDS_KEY =
      "org.apache.struts.action.FORWARDS";


    /**
     * The session attributes key under which the user's selected
     * <code>java.util.Locale</code> is stored, if any.  If no such
     * attribute is found, the system default locale
     * will be used when retrieving internationalized messages.  If used, this
     * attribute is typically set during user login processing.
     */
    public static final String LOCALE_KEY =
      "org.apache.struts.action.LOCALE";


    /**
     * The request attributes key under which our
     * <code>org.apache.struts.ActionMapping</code> instance
     * is passed.
     */
    public static final String MAPPING_KEY =
        "org.apache.struts.action.mapping.instance";


    /**
     * The context attributes key under which our
     * <code>org.apache.struts.action.ActionMappings</code> collection
     * is normally stored, unless overridden when initializing our
     * ActionServlet.
     *
     * @deprecated Replaced by collection in ModuleConfig
     */
    public static final String MAPPINGS_KEY =
      "org.apache.struts.action.MAPPINGS";


    /**
     * The request attributes key under which your action should store an
     * <code>org.apache.struts.action.ActionMessages</code> object, if you
     * are using the corresponding custom tag library elements.
     *
     * @since Struts 1.1
     */
    public static final String MESSAGE_KEY =
      "org.apache.struts.action.ACTION_MESSAGE";


    /**
     * <p>The base of the context attributes key under which our
     * module <code>MessageResources</code> will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual resources key.</p>
     *
     * <p>For each request processed by the controller servlet, the
     * <code>MessageResources</code> object for the module selected by
     * the request URI currently being processed will also be exposed under
     * this key as a request attribute.</p>
     */
    public static final String MESSAGES_KEY =
      "org.apache.struts.action.MESSAGE";


    /**
     * The request attributes key under which our multipart class is stored.
     */
    public static final String MULTIPART_KEY =
        "org.apache.struts.action.mapping.multipartclass";


    /**
     * <p>The base of the context attributes key under which an array of
     * <code>PlugIn</code> instances will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     * @since Struts 1.1
     */
    public static final String PLUG_INS_KEY =
        "org.apache.struts.action.PLUG_INS";


    /**
     * <p>The base of the context attributes key under which our
     * <code>RequestProcessor</code> instance will be stored.  This
     * will be suffixed with the actual module prefix (including the
     * leading "/" character) to form the actual attributes key.</p>
     * @since Struts 1.1
     */
    public static final String REQUEST_PROCESSOR_KEY =
        "org.apache.struts.action.REQUEST_PROCESSOR";


    /**
     * The context attributes key under which we store the mapping defined
     * for our controller serlet, which will be either a path-mapped pattern
     * (<code>/action/*</code>) or an extension mapped pattern
     * (<code>*.do</code>).
     */
    public static final String SERVLET_KEY =
        "org.apache.struts.action.SERVLET_MAPPING";


    /**
     * The session attributes key under which our transaction token is
     * stored, if it is used.
     */
    public static final String TRANSACTION_TOKEN_KEY =
        "org.apache.struts.action.TOKEN";
        
    /**
     * The page attributes key under which xhtml status is stored.  This may be "true"
     * or "false".  When set to true, the html tags output xhtml.
     * @since Struts 1.1
     */
    public static final String XHTML_KEY =
        "org.apache.struts.globals.XHTML";


}
