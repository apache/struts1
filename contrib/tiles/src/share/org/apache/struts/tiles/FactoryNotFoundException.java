/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/FactoryNotFoundException.java,v 1.1 2001/08/01 14:36:42 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:42 $
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
