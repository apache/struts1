package org.apache.struts.example.upload;

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