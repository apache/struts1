/*
* $Header: /home/cvs/jakarta-struts/src/upload/org/apache/struts/webapp/upload/Attic/UploadForm.java,v 1.5 2002/07/27 21:53:13 martinc Exp $
* $Revision: 1.5 $
* $Date: 2002/07/27 21:53:13 $
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

package org.apache.struts.webapp.upload;


import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;


/**
 * This class is a placeholder for form values.  In a multipart request, files are represented by
 * set and get methods that use the class org.apache.struts.upload.FormFile, an interface with
 * basic methods to retrieve file information.  The actual structure of the FormFile is dependant
 * on the underlying impelementation of multipart request handling.  The default implementation
 * that struts uses is org.apache.struts.upload.CommonsMultipartRequestHandler.
 *
 * @author Mike Schachter
 * @version $Revision: 1.5 $ $Date: 2002/07/27 21:53:13 $
 */

public class UploadForm extends ActionForm
{
    public static final String ERROR_PROPERTY_MAX_LENGTH_EXCEEDED = "org.apache.struts.webapp.upload.MaxLengthExceeded";


    /**
     * The value of the text the user has sent as form data
     */
    protected String theText;

    /**
     * Whether or not to write to a file
     */
    protected boolean writeFile;

    /**
     * The file that the user has uploaded
     */
    protected FormFile theFile;

    /**
     * The file path to write to
     */
    protected String filePath;



    /**
     * Retrieve the value of the text the user has sent as form data
     */
    public String getTheText() {
        return theText;
    }

    /**
     * Set the value of the form data text
     */
    public void setTheText(String theText) {
        this.theText = theText;
    }

    /**
     * Retrieve a representation of the file the user has uploaded
     */
    public FormFile getTheFile() {
        return theFile;
    }

    /**
     * Set a representation of the file the user has uploaded
     */
    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

    /**
     * Set whether or not to write to a file
     */
    public void setWriteFile(boolean writeFile) {
        this.writeFile = writeFile;
    }

    /**
     * Get whether or not to write to a file
     */
    public boolean getWriteFile() {
        return writeFile;
    }

    /**
     * Set the path to write a file to
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get the path to write a file to
     */
    public String getFilePath() {
        return filePath;
    }

    public void reset() {
        writeFile = false;
    }

    /**
     * Check to make sure the client hasn't exceeded the maximum allowed upload size inside of this
     * validate method.
     */
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
    {
        ActionErrors errors = null;
        //has the maximum length been exceeded?
        Boolean maxLengthExceeded = (Boolean)
                request.getAttribute(MultipartRequestHandler.ATTRIBUTE_MAX_LENGTH_EXCEEDED);
        if ((maxLengthExceeded != null) && (maxLengthExceeded.booleanValue()))
        {
            errors = new ActionErrors();
            errors.add(ERROR_PROPERTY_MAX_LENGTH_EXCEEDED, new ActionError("maxLengthExceeded"));
        }
        return errors;

    }
}
