package org.apache.struts.example.upload;

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
	