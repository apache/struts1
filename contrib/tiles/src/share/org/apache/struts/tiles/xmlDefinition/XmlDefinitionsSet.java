//Source file: D:\\tmp\\generated\\s1\\struts\\component\\xmlDefinition\\XmlDefinitionsSet.java

package org.apache.struts.tiles.xmlDefinition;

import org.apache.struts.tiles.NoSuchDefinitionException;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A set of definitions red from XML definitions file
*/
public class XmlDefinitionsSet
{
    /** defined definitions */
  protected Map definitions;

     /**
      * Constructor.
      */
  public XmlDefinitionsSet()
   {
   definitions = new HashMap();
   }

  /**
   * Put definition in set
   * @param definition
   */
  public void putDefinition(XmlDefinition definition)
  {
  definitions.put( definition.getName(), definition );
  }

  /**
   * Get requested definition
   * @param name definitio name
   */
  public XmlDefinition getDefinition(String name)
  {
  return (XmlDefinition)definitions.get( name );
  }

  /**
   * Get definitions map
   * @param name definitio name
   */
  public Map getDefinitions()
  {
  return definitions;
  }

  /**
   * Resolve extended instances.
   */
  public void resolveInheritances() throws NoSuchDefinitionException
    {
      // Walks throw all definitions and resolve individual inheritance
    Iterator i = definitions.values().iterator();
    while( i.hasNext() )
      {
      XmlDefinition definition = (XmlDefinition)i.next();
      definition.resolveInheritance( this );
      }  // end loop
    }

  /**
   * add definitions from specified child definitions set.
   * For each definition in child, look if it exist in this set.
   * If not, add it, if yes, overload parent's definition with child definition.
   * @param child definition used to overload this object.
   * @return void
   */
  public void extend( XmlDefinitionsSet child )
    {
    if(child==null)
      return;
    Iterator i = child.getDefinitions().values().iterator();
    while( i.hasNext() )
      {
      XmlDefinition childInstance = (XmlDefinition)i.next();
      XmlDefinition parentInstance = getDefinition(childInstance.getName() );
      if( parentInstance != null )
        {
        parentInstance.overload( childInstance );
        }
       else
        putDefinition( childInstance );
      } // end loop
    }
    /**
     *
     */
  public String toString()
    {
    return "definitions=" + definitions.toString() ;
    }

}
