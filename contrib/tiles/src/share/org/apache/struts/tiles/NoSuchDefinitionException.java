/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/NoSuchDefinitionException.java,v 1.2 2001/12/27 17:35:38 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:38 $
 * $Author: cedric $
 *
 */

package org.apache.struts.tiles;

  /**
   * Exception throw when an instance is not found.
   */
public class NoSuchDefinitionException extends DefinitionsFactoryException
{
    /**
     * Constructor.
     */
  public NoSuchDefinitionException()
    {
    super();
    }
    /**
     * Constructor.
     */
  public NoSuchDefinitionException( String msg )
    {
    super(msg);
    }
}
