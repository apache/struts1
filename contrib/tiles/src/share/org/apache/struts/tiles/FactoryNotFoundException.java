/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/FactoryNotFoundException.java,v 1.2 2001/12/27 17:35:38 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:38 $
 * $Author: cedric $
 *
 */

package org.apache.struts.tiles;

  /**
   * Exception throw when instances factory is not found.
   */
public class FactoryNotFoundException extends DefinitionsFactoryException
{
    /**
     * Constructor.
     */
  public FactoryNotFoundException()
    {
    super();
    }
    /**
     * Constructor.
     */
  public FactoryNotFoundException( String msg )
    {
    super(msg);
    }
}
