/*
 * $Header: /home/cvs/jakarta-struts/src/upload/org/apache/struts/webapp/upload/Attic/UploadForm.java,v 1.1 2001/03/22 13:17:10 rleland Exp $
 * $Revision: 1.1 $
 * $Date: 2001/03/22 13:17:10 $
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
 */

package org.apache.struts.webapp.upload;


import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;


/**
 * This class is a placeholder for form values.  In a multipart request, files are represented by
 * set and get methods that use the class org.apache.struts.upload.FormFile, an interface with
 * basic methods to retrieve file information.  The actual structure of the FormFile is dependant
 * on the underlying impelementation of multipart request handling.  The default implementation
 * that struts uses is org.apache.struts.upload.DiskMultipartRequestHandler.
 *
 * @author Mike Schachter
 * @version $Revision: 1.1 $ $Date: 2001/03/22 13:17:10 $
 */

public class UploadForm extends ActionForm {

        /**
         * The value of the text the user has sent as form data
         */
        protected String theText;

        /**
         * The file that the user has uploaded
         */
        protected FormFile theFile;



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
}
