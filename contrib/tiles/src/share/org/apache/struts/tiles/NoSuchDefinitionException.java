/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/NoSuchDefinitionException.java,v 1.1 2001/08/01 14:36:42 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:42 $
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
