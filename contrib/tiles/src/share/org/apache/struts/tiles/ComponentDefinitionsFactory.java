/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/ComponentDefinitionsFactory.java,v 1.1 2001/08/01 14:36:42 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:42 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import java.util.Map;
import java.io.Serializable;
import javax.servlet.jsp.PageContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;

/**
* Component repository interface.
 * This interface allows to retrieve an definition by its name, independently of the 
 * factory implementation.
 * Implementation must be Serializable, in order to be compliant with web Container
 * having this constraint (Weblogic 6.x).
*/
public interface ComponentDefinitionsFactory extends Serializable 
{
   
   /**
     * Get a definition by its name.
     * @throws DefinitionsFactoryException An error occur while getting 
definition.
     * @throws NoSuchDefinitionException No definition found for specified name
     * Implementation can throw more accurate exception as a subclass of this 
exception
   @roseuid 3AF6F7E203DB
   */
   public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext) throws NoSuchDefinitionException,DefinitionsFactoryException;
   
   /**
     * Init factory.
     * This method is called exactly once immediately after factory creation in
     * case of internal creation (by DefinitionUtil).
     * @param servletContext Servlet Context passed to newly created factory.
     * @param properties Map of name/property passed to newly created factory. 
Map can contains
     * more properties than requested.
     * @throws DefinitionsFactoryException An error occur during initialization.
   @roseuid 3AF6F7E30080
   */
   public void initFactory(ServletContext servletContext, Map properties) throws DefinitionsFactoryException;
}
