/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/tiles/Attic/ComponentContext.java,v 1.2 2001/09/10 12:52:11 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/09/10 12:52:11 $
 * $Author: cedric $
 *
 */


package org.apache.struts.tiles;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import org.apache.struts.taglib.tiles.ComponentConstants;

/**
 * Component context.
 */
public class ComponentContext
{

  /**
   * Component attributes.
   */
  private Map attributes;

  /**
   * Component attributes.
   */
  private static Iterator EMPTY_ITERATOR = new EmptyIterator();

    /**
     * Constructor.
     */
  public ComponentContext()
    {
    }

    /**
     * Constructor.
     * @deprecated Use ComponentContext( Map attributes ) instead.
     */
  public ComponentContext( ComponentDefinition instance )
    {
//    try
//      {
        // instance's attributes map is never null.
      attributes = new HashMap(instance.getAttributes());
//      }
//     catch( NullPointerException ex )
//      { // no attributes in instance : silently fail.
//      }
    }

    /**
     * Constructor.
     * Create a context and set specified attributes.
     * @param attributes Attributes to initialize context
     */
  public ComponentContext( Map attributes )
    {
    if( attributes != null )
      this.attributes = new HashMap(attributes);
    }

  /**
   * Add all attributes to this context.
   * Copies all of the mappings from the specified attribute map to this context.
   * New attribute mappings will replace any mappings that this context had for any of the keys
   * currently in the specified attribute map.
   * @param attributes to add.
   */
  public void addAll(Map newAttributes)
  {
  if( attributes == null )
    {
    attributes = new HashMap(newAttributes);
    return;
    }
  attributes.putAll( newAttributes );
  }

  /**
   * Add all missing attributes to this context.
   * Copies all of the mappings from the specified attributes map to this context.
   * New attribute mappings will be added only if they don't already exist in
   * this context.
   * @param attributes to add.
   */
  public void addMissing(Map defaultAttributes)
  {
  if( defaultAttributes == null )
    return;
  if( attributes == null )
    {
    attributes = new HashMap(defaultAttributes);
    return;
    }

  Set entries = defaultAttributes.entrySet();
  Iterator iterator = entries.iterator();
  while( iterator.hasNext() )
    {
    Map.Entry entry = (Map.Entry)iterator.next();
    if( !attributes.containsKey( entry.getKey()) )
      {
      attributes.put(entry.getKey(), entry.getValue());
      } // end if
    } // end loop
  }

  /**
   * Get an attribute from context
   * @param name
   * @return <{Object}>
   */
  public Object getAttribute(String name)
  {
  if( attributes == null )
    return null;
  return attributes.get( name );
  }

  /**
   * Get names of all attributes
   * @param name
   * @return <{Object}>
   */
  public Iterator getAttributeNames()
  {
  if( attributes == null )
    return EMPTY_ITERATOR;
  return attributes.keySet().iterator();
  }

  /**
   * Put a new attribute to context.
   * @param name
   * @param value
   * @return void
   * @roseuid 39AEBEDB022E
   */
  public void putAttribute(String name, Object value)
  {
  if( attributes == null )
    attributes = new HashMap();

  attributes.put( name, value );
  }

    /**
     * Find object in one of the contexts.

     * Order : component then pageContext.findAttribute()

     * @return requested bean or null if not found.

     *

     */

    public Object findAttribute( String beanName, PageContext pageContext)

      {

      Object attribute = getAttribute(beanName);

      if( attribute == null )

        {

        attribute =  pageContext.findAttribute( beanName );

        } // end if

      return attribute;

      }

    /**

     * Get object from requested context.

     * Context can be 'component'.

     * @return requested bean or null if not found.

     *

     */

    public Object getAttribute( String beanName, int scope, PageContext pageContext)

      {

      if(scope == ComponentConstants.COMPONENT_SCOPE )

        return getAttribute(beanName);


      return  pageContext.getAttribute( beanName, scope );

      }

    /**
     * Get component context from request.
     */
  static public ComponentContext getContext( ServletRequest request )
    {
    return (ComponentContext)request.getAttribute(ComponentConstants.COMPONENT_CONTEXT);
    }

    /**
     * Store component context into request.
     */
  static public void setContext( ComponentContext context, ServletRequest request )
    {
    request.setAttribute(ComponentConstants.COMPONENT_CONTEXT, context);
    }
 }

class EmptyIterator  implements Iterator {

  public boolean hasNext()
  {
   return false;
  }

  public Object next()
  {
  throw new java.util.NoSuchElementException();
  }

  public void remove()
  {
  throw new UnsupportedOperationException();
  }
 } // end inner class


