/*
 * $Header: /home/cvs/jakarta-struts/src/upload/org/apache/struts/webapp/upload/Attic/UploadAction.java,v 1.1 2001/03/22 13:17:10 rleland Exp $
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


import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;



/**
 * This class takes the UploadForm and retrieves the text value
 * and file attributes and puts them in the request for the display.jsp
 * page to display them
 *
 * @author Mike Schachter
 * @version $Revision: 1.1 $ $Date: 2001/03/22 13:17:10 $
 */


public class UploadAction extends Action {

        public ActionForward perform(ActionMapping mapping,
                                     ActionForm    form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

                if (form instanceof UploadForm) {

                        UploadForm theForm = (UploadForm) form;

                        //retrieve the text data
                        String text = theForm.getTheText();

                        //retrieve the file representation
                        FormFile file = theForm.getTheFile();

                        //retrieve the file name
                        String fileName= file.getFileName();

                        //retrieve the content type
                        String contentType = file.getContentType();

                        //retrieve the file size
                        String size = (file.getFileSize() + " bytes");

                        String data = null;

                        try {
                                //retrieve the file data
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                                InputStream stream = file.getInputStream();
                                byte[] buffer = new byte[file.getFileSize()];
                                stream.read(buffer);
                                baos.write(buffer);
                                data = new String(baos.toByteArray());
                        }
                        catch (FileNotFoundException fnfe) {
                                return null;
                        }
                        catch (IOException ioe) {
                                return null;
                        }

                        //place the data into the request for retrieval from display.jsp
                        request.setAttribute("text", text);
                        request.setAttribute("fileName", fileName);
                        request.setAttribute("contentType", contentType);
                        request.setAttribute("size", size);
                        request.setAttribute("data", data);

                        //destroy the temporary file created
                        file.destroy();

                        //return a forward to display.jsp
                        return mapping.findForward("display");
                }

                //this shouldn't happen in this example
                return null;
        }


}