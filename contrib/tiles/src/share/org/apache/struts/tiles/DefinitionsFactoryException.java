/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/DefinitionsFactoryException.java,v 1.2 2001/12/27 17:35:38 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:38 $
 * $Author: cedric $
 *
 */

package org.apache.struts.tiles;

  /**
   * Exception throw when an error occur while factory try to
   * create a new instance mapper.
   */
public class DefinitionsFactoryException extends TilesException
{
    /**
     * Constructor.
    * Create a new MapperFactoryException.
    *
    * @param message The error or warning message.
    */
  public DefinitionsFactoryException()
    {
    super();
    this.exception = null;
  }

    /**
     * Constructor.
    * Create a new MapperFactoryException.
    *
    * @param message The error or warning message.
    */
  public DefinitionsFactoryException(String message)
    {
    super(message);
    this.exception = null;
  }


  /**
    * Create a new MapperFactoryException wrapping an existing exception.
    *
    * <p>The existing exception will be embedded in the new
    * one, and its message will become the default message for
    * the MapperFactoryException.</p>
    *
    * @param e The exception to be wrapped in a SAXException.
    */
  public DefinitionsFactoryException(Exception e)
  {
    super();
    this.exception = e;
  }


  /**
    * Create a new MapperFactoryException from an existing exception.
    *
    * <p>The existing exception will be embedded in the new
    * one, but the new exception will have its own message.</p>
    *
    * @param message The detail message.
    * @param e The exception to be wrapped in a MapperFactoryException.
    * @see org.xml.sax.Parser#setLocale
    */
  public DefinitionsFactoryException(String message, Exception e)
  {
    super(message);
    this.exception = e;
  }


  /**
    * Return a detail message for this exception.
    *
    * <p>If there is a embedded exception, and if the MapperFactoryException
    * has no detail message of its own, this method will return
    * the detail message from the embedded exception.</p>
    *
    * @return The error or warning message.
    */
  public String getMessage ()
  {
    String message = super.getMessage ();

    if (message == null && exception != null) {
      return exception.getMessage();
    } else {
      return message;
    }
  }


  /**
    * Return the embedded exception, if any.
    *
    * @return The embedded exception, or null if there is none.
    */
  public Exception getException ()
  {
    return exception;
  }

  //////////////////////////////////////////////////////////////////////
  // Internal state.
  //////////////////////////////////////////////////////////////////////


  /**
   * Any "wrapped" exception will be exposed when this is serialized.
   * @serial
   */
  private Exception exception;
}
