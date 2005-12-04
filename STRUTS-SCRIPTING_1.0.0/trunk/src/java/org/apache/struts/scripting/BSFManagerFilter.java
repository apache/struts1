package org.apache.struts.scripting;

// util imports:
import java.util.Properties;

// misc imports:
import org.apache.bsf.BSFManager;


/**
 *  Defines a class that wants to manipulate the contents of the scripting
 *  context before the script is executed.  An example would be a class that
 *  puts business facade classes in the context.
 */
public interface BSFManagerFilter {

    /**
     *  Initializes the filter.  Properties can be retrieved as:
     *  <code>struts-scripting.filters.FILTER_NAME.PROPERTY_NAME=PROPERTY_VALUE</code>
     *  where FILTER_NAME is the "name" parameter.
     *
     * @param name The name of the filter
     * @param props The properties
     */
    public void init(String name, Properties props);

    /**
     *  Applies the filter.
     *
     * @param mgr The scripting manager
     */
    public BSFManager apply(BSFManager mgr);
}
