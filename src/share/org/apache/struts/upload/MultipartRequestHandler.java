/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/upload/MultipartRequestHandler.java,v 1.5 2002/12/08 07:12:16 rleland Exp $
 * $Revision: 1.5 $
 * $Date: 2002/12/08 07:12:16 $
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

package org.apache.struts.upload;

import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionMapping;

/**
  * MultipartRequestHandler provides an standard interface for struts to
  * deal with file uploads from forms with enctypes of "multipart/form-data".
  * Providers must provide a no-argument constructor for initialization.
  *
  * @author Mike Schachter
  */
public interface MultipartRequestHandler
{
    /**
     * This is the ServletRequest attribute that should be set when a multipart request is being read
     * and the maximum length is exceeded. The value is a Boolean. If the maximum length isn't exceeded,
     * this attribute shouldn't be put in the ServletRequest. It's the job of the implementation to put this
     * attribute in the request if the maximum length is exceeded; in the handleRequest(HttpServletRequest) method.
     */
    public static final String ATTRIBUTE_MAX_LENGTH_EXCEEDED = "org.apache.struts.upload.MaxLengthExceeded";

    /**
     * Convienience method to set a reference to a working
     * ActionServlet instance.
     */
    public void setServlet(ActionServlet servlet);

    /**
     * Convienience method to set a reference to a working
     * ActionMapping instance.
     */
    public void setMapping(ActionMapping mapping);

    /**
     * Get the ActionServlet instance
     */
    public ActionServlet getServlet();

    /**
     * Get the ActionMapping instance for this request
     */
    public ActionMapping getMapping();

    /**
      * After constructed, this is the first method called on
      * by ActionServlet.  Use this method for all your
      * data-parsing of the ServletInputStream in the request
      *
      * @exception ServletException thrown if something goes wrong
      */
    public void handleRequest(HttpServletRequest request)
        throws ServletException;

    /**
     * This method is called on to retrieve all the text
     * input elements of the request.
     *
     * @return A Hashtable where the keys and values are the names and
     *  values of the request input parameters
     */
    public Hashtable getTextElements();
    
    /**
     * This method is called on to retrieve all the FormFile
     * input elements of the request.
     * @see org.apache.struts.upload.FormFile
     * @return A Hashtable where the keys are the input names of the
     *  files and the values are FormFile objects
     */
    public Hashtable getFileElements();

    /**
     * This method returns all elements of a multipart request.
     * @return A Hashtable where the keys are input names and values
     *   are either Strings or FormFiles
     */
    public Hashtable getAllElements();

    /**
     * This method is called on when there's some sort of problem
     * and the form post needs to be rolled back.  Providers
     * should remove any FormFiles used to hold information
     * by setting them to null and also physically delete
     * them if the implementation calls for writing directly
     * to disk.
     * NOTE: Currently implemented but not automatically
     * supported, ActionForm implementors must call rollback()
     * manually for rolling back file uploads.
     */
    public void rollback();

    /**
     * This method is called on when a successful form post
     * has been made.  Some implementations will use this
     * to destroy temporary files or write to a database
     * or something of that nature.
     */
    public void finish();

}

