package org.apache.struts.upload;

import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.upload.FormFile;
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
    public void handleRequest(HttpServletRequest request) throws ServletException;
    
    /**
      * This method is called on to retrieve all the text
      * input elements of the request.
      * @return A Hashtable where the keys and values are the names and values of the request input parameters
      */
    public Hashtable getTextElements();
    
    /**
     * This method is called on to retrieve all the FormFile
     * input elements of the request.
     * @see org.apache.struts.upload.FormFile
     * @return A Hashtable where the keys are the input names of the files and the values are FormFile objects
     */
    public Hashtable getFileElements();
    
    /**
     * This method returns all elements of a multipart request.
     * @return A Hashtable where the keys are input names and values are either Strings or FormFiles
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
     * or something of that nature
     */
    public void finish();
}

