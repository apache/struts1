/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/InsertTag.java,v 1.4 2001/11/02 16:50:04 cedric Exp $
 * $Revision: 1.4 $
 * $Date: 2001/11/02 16:50:04 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.ComponentDefinition;
import org.apache.struts.tiles.DefinitionsUtil;
import org.apache.struts.tiles.NoSuchDefinitionException;
import org.apache.struts.tiles.FactoryNotFoundException;
import org.apache.struts.tiles.DefinitionsFactoryException;

import org.apache.struts.tiles.AttributeDefinition;
import org.apache.struts.tiles.DirectStringAttribute;
import org.apache.struts.tiles.PathAttribute;
import org.apache.struts.tiles.DefinitionAttribute;
import org.apache.struts.tiles.DefinitionNameAttribute;

import org.apache.struts.taglib.tiles.util.TagUtils;

import org.apache.struts.action.Action;

import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.PageContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * This is the tag handler for &lt;template:insert&gt;, which includes
 * a template. The tag's body content consists of &lt;template:put&gt;
 * tags, which are accessed by &lt;template:get&gt; in the template.
 *
 * @author David Geary
 * @author Cedric Dumoulin
 * @version $Revision: 1.4 $ $Date: 2001/11/02 16:50:04 $
 */
public class InsertTag extends TagSupport implements PutTagParent, ComponentConstants, PutListTagParent
{
    /** debug flag */
  public final static boolean debug = true;

    /* JSP Tag attributes */
    /** Flush attribute value */
  protected boolean flush = true;
    /** Name of entity to include */
  protected String name = null;
  /** Name of page, component or template to include */
  private String page = null;
    /** Name of attribute from which to read page name to include */
  private String attribute = null;
    /** Name of bean used as entity to include */
  private String beanName = null;
    /** Name of bean property, if any */
  private String beanProperty = null;
    /** Scope of bean, if any */
  private String beanScope = null;
    /** Role attribute */
  protected String role = null;
    /**
     * Is errors ignored ? This is the property for attribute 'ignore'.
     * Default value is false, which throw an exception.
     * Only 'attribute not found' errors are ignored.
     */
  protected boolean isErrorIgnored = false;
    /** Name of component instance to include */
  private String definitionName = null;

    /* Internal properties */
    /**
     * Is the end tag need to be processed ?
     * Default value is true. Boolean set in case of ignored errors.
     */
  private boolean processEndTag = true;
    /** Current component context */
  protected ComponentContext cachedCurrentContext;
    /** Finale handler of tag methods */
  private TagHandler tagHandler = null;

    /** Trick to allows inner classes to access pageContext */
  protected PageContext pageContext = null;

		/**
		 * Reset member values for reuse. This method calls super.release(),
		 * which invokes TagSupport.release(), which typically does nothing.
		 */
    public void release() {

        super.release();
        attribute = null;
        beanName = null;
        beanProperty = null;
        beanScope = null;

        definitionName = null;
        flush = true;
        name = null;
        page = null;
        role = null;
        isErrorIgnored = false;
    }

		/**
		 * Reset internal member values for reuse.
		 */
    protected void releaseInternal()
      {
      cachedCurrentContext = null;
      processEndTag = true;
        // pageContext = null;  // orion doesn't set it between two tags
      tagHandler = null;
      }

    /**
     * Set the current page context.
     * Called by the page implementation prior to doStartTag().
     * <p>
     * Needed to allow inner classes to access pageContext.
     */
  public void setPageContext(PageContext pc)
      {
      this.pageContext = pc;
      super.setPageContext(pc);
      }
    /**
     * Set property
     */
  public void setComponent(String name){
    this.page = name;
  }

    /**
     * Set property
     */
  public void setTemplate(String name){
    this.page = name;
  }

    /**
     * Set name property
     */
  public void setName(String name){
    this.name = name;
  }

    /**
     * Set property
     * @deprecated Use setDefinition() instead.
     */
  public void setInstance(String name){
    this.definitionName = name;
  }

    /**
     * Set property
     */
  public void setDefinition(String name){
    this.definitionName = name;
  }

  /**
   * Set property
   */
  public void setPage(String page){
    this.page = page;
  }

    /**
     * Set property
     */
  public void setAttribute(String value){
    this.attribute = value;
  }

    /**
     * Set property.
     */
  public void setBeanName(String value){
    this.beanName = value;
  }

    /**
     * Set property.
     */
  public void setBeanProperty(String value){
    this.beanProperty = value;
  }

    /**
     * Set property.
     */
  public void setBeanScope(String value){
    this.beanScope = value;
  }

    /**
     * Set property
     */
  public void setFlush(boolean flush){
    this.flush = flush;
  }

    /**
     * Set property
     * Method added for compatibility with JSP1.1
     */
  public void setFlush(String flush){
       this.flush = (Boolean.valueOf(flush).booleanValue());
  }

    /**
     * Set role attribute
     * @param name The role the user must be in to store content.
     */
   public void setRole(String role) {
      this.role = role;
   }

    /**
     * Set ignore attribute
     */
  public void setIgnore(boolean ignore)
    {
    this.isErrorIgnored = ignore;
    }


     /////////////////////////////////////////////////////////////////////////

    /**
     * Add a body attribute.
     * Erase any attribute with same name previously set.
     */
  public void putAttribute(String name, Object value)
    {
    tagHandler.putAttribute(name, value);
    }

    /**
     * Process nested &lg;put&gt; tag.
     * Method calls by nested &lg;put&gt; tags.
     * Nested list is added to current list.
     * If role is defined, it is checked immediately.
     */
  public void processNestedTag(PutTag nestedTag) throws JspException
    {
      // Check role
    String role = nestedTag.getRole();
    if( role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
      { // not allowed : skip attribute
      return;
      } // end if

    putAttribute( nestedTag.getName(), nestedTag.getRealValue() );
    }


    /**
     * Process nested &lg;putList&gt; tag.
     * Method calls by nested &lg;putList&gt; tags.
     * Nested list is added to sub-component attributes
     * If role is defined, it is checked immediately.
     */
  public void processNestedTag(PutListTag nestedTag) throws JspException
   {
      // Check role
    String role = nestedTag.getRole();
    if( role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
      { // not allowed : skip attribute
      return;
      } // end if

      // Check if a name is defined
    if( nestedTag.getName() == null)
      throw new JspException( "Error - PutList : attribute name is not defined. It is mandatory as the list is added as attribute of 'insert'." );
      // now add attribute to enclosing parent (i.e. : this object).
    putAttribute( nestedTag.getName(), nestedTag.getList() );
    }

    /**
     * Method calls by nested &lg;putList&gt; tags.
     * A new list is added to current insert object.
     */
  public void putAttribute(PutListTag nestedTag) throws JspException
    {
      // Check role
    String role = nestedTag.getRole();
    if( role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
      { // not allowed : skip attribute
      return;
      } // end if

    putAttribute( nestedTag.getName(), nestedTag.getList() );
    }

    /**
     * Get current component context.
     */
  private ComponentContext getCurrentContext()
    {
    if( cachedCurrentContext == null )
      {
      cachedCurrentContext = (ComponentContext)pageContext.getAttribute( ComponentConstants.COMPONENT_CONTEXT, pageContext.REQUEST_SCOPE);
      }
    return cachedCurrentContext;
    }

		/**
		 * Process the start tag by checking tag's attributes and creating appropriate handler.
		 * Possible handlers :
		 * <ul>
		 * <li> URL
		 * <li> definition
		 * <li> direct String
		 * </ul>
		 * Handlers also contain sub-component context.
		 *
		 */
		public int doStartTag() throws JspException
		{
      // Check role immediatly to avoid useless stuff.
      // In case of insertion of a "definition", definition's role still checked later.
      // This lead to a double check of "role" ;-(
    if(role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
      {
        processEndTag = false;
      	return SKIP_BODY;
      } // end if

      // Now, real stuff
    try
      {
      tagHandler = createTagHandler();
      }
     catch( JspException ex )
      { // Do we ignore errors ?
      if( isErrorIgnored )
        {
        processEndTag = false;
        return SKIP_BODY;
        }
        // Errors aren't ignored, let it throw.
      throw ex;
      }
    return tagHandler.doStartTag();
		}

		/**
		 * Process the end tag by including the template.
		 * Simply call the handler doEndTag
		 */
		public int doEndTag() throws JspException
		{
    if( !processEndTag )
      {
      releaseInternal();
      return EVAL_PAGE;
      }

    int res =  tagHandler.doEndTag();
      // Reset properties used by object, in order to be able to reuse object.
    releaseInternal();
    return res;
		}

		/**
		 * Process tag attribute and create corresponding tag handler.
		 */
		public TagHandler createTagHandler()  throws JspException
		{
      // Check each tag attribute.
      // page Url attribute must be the last checked  because it can appears concurrently
      // with others attributes.
    if( definitionName != null )
      {
      return processDefinitionName( definitionName );
      }

     else if( attribute != null )
      {
      return processAttribute( attribute );
      }

     else if( beanName != null )
      {
      return processBean( beanName, beanProperty, beanScope );
      }
     else if( name != null )
      {
      return processName( name );
      }
     else if( page != null )
      {
      return processUrl( page );
      }
     else
      {
      throw new JspException ( "Error - Tag Insert : At least one of the following attribute must be defined : template|page|attribute|definition|name|beanName. Check tag syntax" );
      }
		}

		/**
		 * Process an object retrieved as a bean or attribute.
		 * Object can be a typed attribute, a String, or anything else.
		 * If typed attribute, use associated type.
		 * Otherwise, apply toString() on object, and use returned string as a name.
     * @throws JspException - Throws by underlying nested call to processDefinitionName()
     * @see processDefinitionName(String name) for exception explanation
		 * @roseuid 3AB404640029
		 */
		public TagHandler processObjectValue(Object value)
      throws JspException
		{
      // First, check if value is one of the Typed Attribute
    if( value instanceof AttributeDefinition )
      { // We have a type => return appropriate IncludeType
      return processTypedAttribute((AttributeDefinition)value);
      }
     else if( value instanceof ComponentDefinition )
      return processDefinition( (ComponentDefinition)value );

      // Value must denote a valid String
    return processAsDefinitionOrURL( value.toString() );
		}

		/**
		 * Process name.
		 * Search in following order :
		 * <ul>
		 * <li>Component context -  if found, process it as value.</li>
		 * <li>definitions factory</li>
		 * <li>URL</li>
		 * <li></li>
		 * </ul>
		 *
		 * @return appropriate tag handler.
     * @throws JspException - Throws by underlying nested call to processDefinitionName()
     * @see processDefinitionName(String name) for exception explanation
		 * @roseuid 3AB405960336
		 */
		public TagHandler processName(String name)
      throws JspException
		{
    Object attrValue = getCurrentContext().getAttribute(name);

    if( attrValue != null )
      return processObjectValue( attrValue);

    return processAsDefinitionOrURL(name);
		}

		/**
		 * Process
		 * @roseuid 3AB4CE540211
		 */
		public TagHandler processUrl(String url)
		{
    return new InsertHandler(url, role );
		}

		/**
		 * Process tag attribute "definition"
     * First, search definition in the factory, then create handler from this definition.
		 * @param name Name of the definition.
		 * @return Appropriate TagHandler.
		 * @throws JspException- NoSuchDefinitionException No Definition  found for name.
		 * @throws JspException- FactoryNotFoundException Can't find Definitions factory.
		 * @throws JspException- DefinedComponentFactoryException General error in factory.
		 * @roseuid 3AB4CEFB01CB
		 */
		protected TagHandler processDefinitionName(String name)
      throws JspException
		{

    try
      {
      ComponentDefinition definition =  DefinitionsUtil.getDefinition(name, pageContext);
      if( definition == null )
        {  // is it possible ?
        throw new NoSuchDefinitionException();
        }
      return processDefinition( definition );

      }
     catch( NoSuchDefinitionException ex )
        {
        throw new JspException ( "Error -  Tag Insert : Can't get definition '"
                               + definitionName
                               + "'. Check if this name exist in definitions factory." );
        }
     catch( FactoryNotFoundException ex )
        { // factory not found.
        throw new JspException ( ex.getMessage() );
        } // end catch
     catch( DefinitionsFactoryException ex )
        {
        if(debug)
          ex.printStackTrace( );
          // Save exception to be able to show it later
        pageContext.setAttribute(Action.EXCEPTION_KEY, ex, PageContext.REQUEST_SCOPE);
        throw new JspException (  ex.getMessage() );
        } // end catch
		}

		/**
		 * End of Process tag attribute "definition"
     * Overload definition with tag attributes "template" and "role".
     * Then, create appropriate tag handler.
		 * @param definition Definition to process.
		 * @return Appropriate TagHandler.
		 */
		protected TagHandler processDefinition(ComponentDefinition definition)
		{
      // Declare local variable in order to not change Tag attribute values.
    String role = this.role;
    String page = this.page;
     // Overload definition with tag's template and role.
     if(role == null )
      role = definition.getRole();
    if( page == null )
      page = definition.getTemplate();
      // Can check if page is set
    return new InsertHandler( definition.getAttributes(), page, role );
		}

		/**
		 * Process a bean.
		 * Get bean value, eventually using property and scope. Found value is process by processObjectValue().
		 * @param name Name of the bean
		 * @param property Property in the bean, or null.
		 * @scope bean scope, or null.
		 * @return Appropriate TagHandler.
		 * @throws JspException - NoSuchDefinitionException No value associated to bean.
     * @throws JspException an error occur while reading bean, or no definition found.
     * @throws JspException - Throws by underlying nested call to processDefinitionName()
     * @see processDefinitionName(String name) for exception explanation
		 * @roseuid 3AB4CFEE0260
		 */
		protected TagHandler processBean(String beanName, String beanProperty, String beanScope)
      throws JspException
		{
    Object beanValue = TagUtils.getRealValueFromBean( beanName, beanProperty, beanScope, pageContext );
    if( beanValue == null )
      {
      //throw new NoSuchDefinitionException();
      throw new JspException( "Error - Tag Insert : No value defined for bean '"
                              + beanName + "' with property '"
                              + beanProperty + "' in scope '"
                              + beanScope + "'."   );
      } // end if
    return processObjectValue(beanValue);
		}

		/**
		 * Process tag attribute "attribute=".
		 * Get value from component attribute.
		 * Found value is process by processObjectValue().
		 * @param name Name of the attribute.
		 * @return Appropriate TagHandler.
		 * @throws JspException - NoSuchDefinitionException No Definition  found for name.
     * @throws JspException - Throws by underlying nested call to processDefinitionName()
     * @see processDefinitionName(String name) for exception explanation
		 * @roseuid 3AB4D29F0026
		 */
		public TagHandler processAttribute(String name)
      throws JspException
    {
    Object attrValue = getCurrentContext().getAttribute(name);

    if( attrValue == null )
        throw new JspException ( "Error - Tag Insert : No value found for attribute '"
                               + name
                               + "'." );
    return processObjectValue( attrValue );
	  }

		/**
		 * Try to process name as a definition, or as an URL if not found.
		 * @param name Name to process.
		 * @return appropriate TagHandler
		 * @roseuid 3AB4D8DC002D
		 */
		public TagHandler processAsDefinitionOrURL( String name )
		{
    try
      {
      ComponentDefinition definition =  DefinitionsUtil.getDefinition(name, pageContext);
      if( definition != null )
        return processDefinition( definition );
      }
     catch( DefinitionsFactoryException ex )
      { // silently failed, because we can choose to not define a factory.
      }
      // no definition found, try as url
    return processUrl( name );
		}

		/**
		 * Process typed attribute according to its type.
		 * @param value Typed attribute to process.
		 * @return appropriate TagHandler.
     * @throws JspException - Throws by underlying nested call to processDefinitionName()
     * @see processDefinitionName(String name) for exception explanation
		 * @roseuid 3AB4D955008B
		 */
		public TagHandler processTypedAttribute(AttributeDefinition value)
      throws JspException
 		{
    if( value instanceof DirectStringAttribute )
      return new DirectStringHandler( (String)value.getValue() );

     else if( value instanceof DefinitionAttribute )
      return processDefinition( (ComponentDefinition)value.getValue() );

     else if( value instanceof DefinitionNameAttribute )
      {
      return processDefinitionName( (String)value.getValue() );
      }
     //else if( value instanceof PathAttribute )
    return new InsertHandler( (String)value.getValue(), role );
		}

  /////////////////////////////////////////////////////////////////////////////

		/**
     * Inner Interface
		 * Sub handler for tag.
		 */
		protected interface TagHandler
		{
      /**
       * Create ComponentContext for type depicted by implementation class.
       */
    public int doStartTag() throws JspException;
      /**
       * Do include for type depicted by implementation class.
       */
    public int doEndTag() throws JspException;
      /**
       * Add a component parameter (attribute) to subContext.
       */
    public void putAttribute( String name, Object value );
    } // end inner interface

  /////////////////////////////////////////////////////////////////////////////

		/**
		 * Real handler, after attribute resolution.
		 * Handle include sub-component.
		 */
		protected class InsertHandler implements TagHandler
		{
    protected String page;
    protected ComponentContext currentContext;
    protected ComponentContext subCompContext;
    protected String role;

      /**
       * Constructor.
       * Create insert handler using Component definition.
       */
    public InsertHandler( Map attributes, String page, String role  )
      {
      this.page = page;
      this.role = role;
      subCompContext = new ComponentContext( attributes );
      }

      /**
       * Constructor.
       * Create insert handler to insert page at specified location.
       */
    public InsertHandler( String page, String role )
      {
      this.page = page;
      this.role = role;
      subCompContext = new ComponentContext();
      }

      /**
       * Create a new empty context.
       */
    public int doStartTag() throws JspException
      {
        // Check role
      if(role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
        {
      	return SKIP_BODY;
        } // end if

        // save current context
      this.currentContext = getCurrentContext();
      return EVAL_BODY_INCLUDE;
      }

      /**
       * Add attribute to sub context.
       * Do nothing.
       */
    public void putAttribute( String name, Object value )
      {
      subCompContext.putAttribute( name, value );
      }

      /**
       * Include requested page.
       */
    public int doEndTag() throws JspException
      {
      // Check role
      if(role != null && !((HttpServletRequest)pageContext.getRequest()).isUserInRole(role) )
        {
      	return EVAL_PAGE;
        } // end if

      try {
        if( DefinitionsUtil.userDebugLevel > DefinitionsUtil.NO_DEBUG )
          System.out.println( "insert page='" + page + "'." );

         // set new context for included component.
        pageContext.setAttribute( ComponentConstants.COMPONENT_CONTEXT, subCompContext, pageContext.REQUEST_SCOPE);
        // include requested component.
        if( flush )
          pageContext.getOut().flush();
        pageContext.include(page);
        }
       catch (IOException ex)
        {
        processException( ex, "Can't insert page '"+ page+ "' : " + ex.getMessage() );
        }
       catch (IllegalArgumentException ex)
        {  // Can't resolve page uri
          // Do we ignore bad page uri errors ?
        if( ! (page == null && isErrorIgnored) )
           // Don't ignore bad page uri errors
          processException( ex, "Tag 'insert' can't insert page '" + page + "'. Check if it exists.\n" + ex.getMessage() );
        }
       catch (ServletException ex)
        {
        Throwable realEx = ex;
        if( ex.getRootCause() != null )
          {
          realEx = ex.getRootCause();
          }
        processException( realEx, "[ServletException in:"+ page+ "] " + realEx.getMessage()+ "'" );
        }
      catch (Exception ex) {
        processException( ex, "[Exception in:" + page + "] "+ ex.getMessage());
        }
      finally
        {
          // restore old context
          // done only if currentContext not null (bug with Silverstream ?; related by Arvindra Sehmi 20010712)
        if( currentContext != null )
          pageContext.setAttribute( ComponentConstants.COMPONENT_CONTEXT, currentContext, pageContext.REQUEST_SCOPE);
        }
      return EVAL_PAGE;
      }
       /**
        * Process an exception.
        * Depending of debug attribute, print full exception trace, or only
        * its message in output page.
        * @ex Exception
        * @msg An additional message to show in console, and to propagate if we can't output exception.
        */
    protected void processException( Throwable ex, String msg ) throws JspException
      {
      try {
        if( msg == null )
          msg = ex.getMessage();

        if(debug)
          {  // show full trace
          System.out.println( msg );
          ex.printStackTrace();
          pageContext.getOut().println(msg);
          ex.printStackTrace(new PrintWriter(pageContext.getOut(), true) );
          }
         else
          { // show only message
          pageContext.getOut().println(msg);
          } // end if
        }
       catch(IOException ioex )
        { // problems. Propagate original exception
        pageContext.setAttribute(ComponentConstants.EXCEPTION_KEY, ex, PageContext.REQUEST_SCOPE);
        throw new JspException(  msg );
        }
      }
		}

  /////////////////////////////////////////////////////////////////////////////

		/**
		 * Handle insert direct string.
		 */
		protected class DirectStringHandler implements TagHandler
		{
      /** Object to print as a direct string */
    private Object value;

      /**
       * Constructor.
       */
    public DirectStringHandler(Object value )
      {
      this.value = value;
      }

      /**
       * Do nothing are there is no context for a direct string
       */
    public int doStartTag() throws JspException
      {
      return SKIP_BODY;
      }

      /**
       * Add attribute to sub context.
       * Do nothing.
       */
    public void putAttribute( String name, Object value )
      {
      }

      /**
       * Print String in page output stream
       */
    public int doEndTag() throws JspException
      {
      try
        {
        if(flush)
          pageContext.getOut().flush();

        pageContext.getOut().print( value );
        }
       catch (IOException ex)
        {
        if(debug)
          System.out.println( "Can't write string '"+ value + "' : " + ex.getMessage() );
        pageContext.setAttribute(ComponentConstants.EXCEPTION_KEY, ex, PageContext.REQUEST_SCOPE);
        throw new JspException( "Can't write string '"+ value + "' : " + ex.getMessage() );
        }
      return EVAL_PAGE;
      }
 		}
}
