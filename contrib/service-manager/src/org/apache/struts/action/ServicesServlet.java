/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/src/org/apache/struts/action/Attic/ServicesServlet.java,v 1.2 2001/07/18 04:22:19 oalexeev Exp $
 * $Revision: 1.2 $
 * $Date: 2001/07/18 04:22:19 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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

package org.apache.struts.action;

import java.lang.Class;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastHashMap;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.service.ServiceManager;
import org.apache.struts.service.ServletServiceManager;
import org.apache.struts.upload.MultipartRequestWrapper;
import org.xml.sax.AttributeList;
import org.xml.sax.SAXException;

/** 
 * @author Oleg V Alexeev
 * @version $Revision: 1.2 $ $Date: 2001/07/18 04:22:19 $
 */
public class ServicesServlet
    extends ActionServlet {

    protected ServletServiceManager services = new ServletServiceManager( this );

    // ---------------------------------------------------- HttpServlet Methods

    public void destroy() {

        services.destroy();
        super.destroy();

    }

    protected void initServiceManager() 
        throws ServletException {
        try {
                services.init();
        } catch ( Exception e ) {
                throw new ServletException( "Exception at init of ServletServiceManager", e );
        }
    }    

    public void init() throws ServletException {

        initServiceManager();
        super.init();

    }

    /**
     * Initialize the debugging detail level for this application.
     *
     * @exception ServletException if we cannot initialize these resources
     */
    protected void initDebug() throws ServletException {

        super.initDebug();
        services.setDebugLevel( debug );

    }


    public void reload() throws IOException, ServletException {

        services.destroy();
        super.destroy();
        initServiceManager();
        super.init();

    }

    public String processPath(HttpServletRequest request) {
        return super.processPath( request );
    }

    public ActionMapping processMapping(String path,
                                           HttpServletRequest request) {
        return super.processMapping( path, request );
    }        


    protected Digester initDigester(int detail) {

        Digester digester = super.initDigester( detail );

        services.initDigester( digester, "struts-config", detail );

        return digester;

    }

    protected boolean processPreprocess( HttpServletRequest request,
                                         HttpServletResponse response)
        throws IOException, ServletException {

        try {
                return services.performBooleanCall( 
                        "preprocess", new Object[]{ request, response }, true );
        } catch ( Exception e ) {
                throw new ServletException( "Exception at processPreprocess call to the services", e );
        }

    }

    protected void processPopulate(ActionForm formInstance,
                                   ActionMapping mapping,
                                   HttpServletRequest request)
        throws ServletException {

        if (formInstance == null)
            return;
        //set the servlet of the ActionForm
        formInstance.setServlet(this);

        // Populate the bean properties of this ActionForm instance
        if (debug >= 1)
            log(" Populating bean properties from this request");
        formInstance.reset(mapping, request);
        //place the mapping's multipart request handler class
        //into the request to be read by the RequestUtils.populate
        //method in the event of a multipart request
        if (mapping.getMultipartClass() != null)
            request.setAttribute(Action.MULTIPART_KEY,
                                mapping.getMultipartClass());
        //also pass the mapping through the request
        request.setAttribute(Action.MAPPING_KEY,
                             mapping);

        try {

                if( services.performBooleanCall( "populateBefore", 
                        new Object[]{ formInstance, mapping, request }, true ) ) {
                        RequestUtils.populate(formInstance, mapping.getPrefix(),
                                mapping.getSuffix(), request);
                        services.performCall( "populateAfter", 
                                new Object[]{ formInstance, mapping, request } );
                }
        } catch ( Exception e ) {
                throw new ServletException( "Exception at processPopulate call to the services", e );
        }

    }

    protected boolean processValidate(ActionMapping mapping,
        ActionForm formInstance, HttpServletRequest request,
        HttpServletResponse response)
        throws IOException, ServletException {

        Boolean result = null;

        if (formInstance == null)
            return (true);

        if (debug >= 1)
            log(" Validating input form properties");

        // Was this submit cancelled?
        if ((request.getParameter(Constants.CANCEL_PROPERTY) != null) ||
            (request.getParameter(Constants.CANCEL_PROPERTY_X) != null)) {
            if (debug >= 1)
                log("  Cancelled transaction, no validation");
            return (true);
        }

        // Has validation been turned off on this mapping?
        if (!mapping.getValidate())
            return (true);

        ActionErrors errors = new ActionErrors();

        try {

                if( services.performBooleanCall( "validateBefore", 
                        new Object[]{ mapping, formInstance, request, response, errors }, true ) ) {

                        if (debug >= 1)
                                log("  No errors detected by 'before' services");

                        // Call the validate() method of our ActionForm bean
                        errors = formInstance.validate(mapping, request);
                        if ((errors == null) || errors.empty()) {
                                if (debug >= 1)
                                        log("  No errors detected by form validate");
                                if( services.performBooleanCall( "validateAfter", 
                                        new Object[]{ mapping, formInstance, request, response, errors }, true ) ) {
                                        if (debug >= 1)
                                                log("  No errors detected by 'after' services");
                                        return (true);
                                }
                        }
                }
        } catch ( Exception e ) {
                throw new ServletException( "Exception at processValidate call to the services", e );
        }

        //does our form have a multipart request?
        if (formInstance.getMultipartRequestHandler() != null) {
            //rollback the request
            if (debug > 1) {
                log("  Rolling back the multipart request");
            }
            
            formInstance.getMultipartRequestHandler().rollback();
        }
        // Has an input form been specified for this mapping?
        String uri = mapping.getInput();
        if (uri == null) {
            if (debug >= 1)
                log("  No input form, but validation returned errors");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("noInput",
                                                   mapping.getPath()));
            return (false);
        }

        // Save our error messages and return to the input form if possible
        if (debug >= 1)
            log("  Validation error(s), redirecting to: " + uri);
        request.setAttribute(Action.ERROR_KEY, errors);
        //unwrap the multipart request if there is one
        if (request instanceof MultipartRequestWrapper) {
            request = ((MultipartRequestWrapper) request).getRequest();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(uri);
        if (rd == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                               internal.getMessage("requestDispatcher",
                                                   uri));
            return (false);
        }
        rd.forward(request, response);

        return false;

    }

}


