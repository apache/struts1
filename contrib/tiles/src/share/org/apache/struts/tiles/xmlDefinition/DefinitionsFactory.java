//Source file: D:\\tmp\\generated\\s1\\struts\\component\\xmlDefinition\\DefinitionsFactory.java

package org.apache.struts.tiles.xmlDefinition;

import org.apache.struts.tiles.ComponentDefinitionsFactory;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.DefinitionsFactoryException;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.NoSuchDefinitionException;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * A factory for definitions.
 * This factory allows to retrieve definitions by their keys.
 */
public class DefinitionsFactory
{
     /** */
   protected Map definitions;

   /**
     * Get a definition by its name.
     * @throws DefinitionsFactoryException An error occur while getting
     * definition.
     * @throws NoSuchDefinitionException No definition found for specified name
     * Implementation can throw more accurate exception as a subclass of this
     * exception
     */
   public ComponentDefinition getDefinition(String name, ServletRequest request, ServletContext servletContext)
             throws NoSuchDefinitionException, DefinitionsFactoryException
   {
   return (ComponentDefinition)definitions.get(name);
   }

  /**
   * Put definition in set
   * @param definition
   */
  public void putDefinition(ComponentDefinition definition)
  {
  definitions.put( definition.getName(), definition );
  }

   /**
    * Constructor.
    * Create a factory initialized with definitions from XmlDefinitionsSet.
    * @param xmlDefinitions resolved definition from XmlDefinitionSet.
    * @throws DefinitionsFactoryException If an error occurs while creating a definition.
    * @throws NoSuchDefinitionException If an error occurs while resolving inheritance
    */
   public DefinitionsFactory(XmlDefinitionsSet xmlDefinitions)
    throws NoSuchDefinitionException
    {
    definitions = new HashMap();

      // First, resolve inheritance
    xmlDefinitions.resolveInheritances();

      // Walk thru xml set and copy each definitions.
    Iterator i = xmlDefinitions.getDefinitions().values().iterator();
    while( i.hasNext() )
      {
      XmlDefinition xmlDefinition = (XmlDefinition)i.next();
        putDefinition( new ComponentDefinition( xmlDefinition) );
      }  // end loop
   }
    /**
     *
     */
  public String toString()
    {
    return definitions.toString();
    }

}

