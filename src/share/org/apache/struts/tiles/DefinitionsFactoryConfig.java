/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/DefinitionsFactoryConfig.java,v 1.7 2003/02/27 19:20:51 cedric Exp $
 * $Revision: 1.7 $
 * $Date: 2003/02/27 19:20:51 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */


package org.apache.struts.tiles;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

/**
 * A TilesFactoryConfig object hold configuration attributes for a tile
 * definition factory.
 *
 * @author Cedric Dumoulin
 * @since 1.1
 * @version $Revision: 1.7 $ $Date: 2003/02/27 19:20:51 $
 */
public class DefinitionsFactoryConfig implements Serializable
{

    /**
     * Fully qualified classname of the factory to create.
     * If no classname is set, a default factory is created
     * (of class "org.apache.struts.tiles.xmlDefinition.I18nFactorySet").
     */
    protected String factoryClassname = "org.apache.struts.tiles.xmlDefinition.I18nFactorySet";

    /**
     * Debug level value. 0=no debug info >0 = debug info.
     * @deprecated Use commons-logging mechanism.
     */
    protected int debugLevel = 0;

    /**
     * Debug level value used when parsing configuration file.
     * 0=no debug info; >0 = debug info.
     * @deprecated Use commons-logging mechanism.
     */
    protected int parserDebugLevel = 0;

    /**
     * Specifies whether the parser will validate configuration files.
     * Default value is true.
     */
    protected boolean parserValidate = true;
    /** Definition configuration file specified by user */
    protected String definitionConfigFiles;

    /**
     * Specifies whether the factory is "module-aware".
     */
    protected boolean moduleAware = true;

    /**
     * The name associated to this factory.
     * <br>
     * With Struts 1.1, this name is the module name to which this factory
     * belong. It is set by the system.
     * <br>
     * In prior versions, this property is not used.
     */
    protected String factoryName;

    /** Alternate name for parser debug details properties in configuration file */
    public static final String PARSER_DETAILS_PARAMETER_NAME = "definitions-parser-details";
    /** Alternate name for parser validate properties in configuration file */
    public static final String PARSER_VALIDATE_PARAMETER_NAME = "definitions-parser-validate";
    /** Alternate name for factory classname properties in configuration file */
    public static final String FACTORY_CLASSNAME_PARAMETER_NAME = "definitions-factory-class";
    /** Alternate name for definition files properties in configuration file */
    public static final String DEFINITIONS_CONFIG_PARAMETER_NAME = "definitions-config";
    /** Alternate name for definition debug details properties in configuration file */
    public static final String TILES_DETAILS_PARAMETER_NAME = "definitions-debug";

    /**
     * Map of extra attribute available.
     */
    private Map extraAttributes = new HashMap();

    /**
     * Default constructor.
     */
    public DefinitionsFactoryConfig()
    {

    }

    /**
     * Constructor.
     * Create configuration object, and initialize it with parameters from Map.
     * Parameters corresponding to an attribute are filtered and stored in appropriate
     * attribute.
     * @param initParameters Map.
     */
    public DefinitionsFactoryConfig(Map initParameters)
    {

    }

    /**
     * Get the module aware flag.
     * @return <code>true</code>: user wants a single factory instance,
     * <code>false</code>: user wants multiple factory instances (one per module with Struts)
     */
    public boolean isModuleAware()
    {
        return moduleAware;
    }
    /**
     * Set the module aware flag.
     * @param moduleAware <code>true</code>: user wants a single factory instance,
     * <code>false</code>: user wants multiple factory instances (one per module with Struts)
     */
    public void setModuleAware(boolean moduleAware)
    {
        this.moduleAware = moduleAware;
    }

    /**
     * Get the classname of the factory.
     * @return Classname.
     */
    public String getFactoryClassname()
    {
        return factoryClassname;
    }

    /**
     * Set the classname of the factory..
     * @param aFactoryClassname Classname of the factory.
     */
    public void setFactoryClassname(String aFactoryClassname)
    {
        factoryClassname = aFactoryClassname;
    }

    /**
     * Get debug level.
     * @return Debug level.
     * @deprecated Use commons-logging mechanism.
     */
    public int getDebugLevel()
    {
        return debugLevel;
    }

    /**
     * Set debug level.
     * @param aDebugLevel Debug level.
     * @deprecated Use commons-logging mechanism.
     */
    public void setDebugLevel(int aDebugLevel)
    {
        debugLevel = aDebugLevel;
    }

    /**
     * Get the debug level for the parser.
     * @return Debug level.
     * @deprecated Use commons-logging mechanism.
     */
    public int getParserDebugLevel()
    {
        return parserDebugLevel;
    }

    /**
     * Set the debug level for the parser.
     * @param aParserDebugLevel Debug level.
     * @deprecated Use commons-logging mechanism.
     */
    public void setParserDebugLevel(int aParserDebugLevel)
    {
        parserDebugLevel = aParserDebugLevel;
    }

    /**
     * Determines if the parser is validating.
     * @return <code>true<code> when in validating mode.
     */
    public boolean getParserValidate()
    {
        return parserValidate;
    }

    /**
     * Set the validating mode for the parser.
     * @param aParserValidate <code>true</code> for validation, <code>false</code> otherwise
     */
    public void setParserValidate(boolean aParserValidate)
    {
        parserValidate = aParserValidate;
    }

    /**
     * Get the definition config files.
     * @return Defition config files.
     */
    public String getDefinitionConfigFiles()
    {
        return definitionConfigFiles;
    }

    /**
     * Set the definition config files.
     * @param aDefinitionConfigFiles Definition config files.
     */
    public void setDefinitionConfigFiles(String aDefinitionConfigFiles)
    {
        definitionConfigFiles = aDefinitionConfigFiles;
    }

    /**
     * Set value of an additional attribute.
     * @param name Name of the attribute.
     * @param value Value of the attribute.
     */
    public void setAttribute(String name, Object value)
    {
        extraAttributes.put(name, value);
    }

    /**
     * Get value of an additional attribute.
     * @param name Name of the attribute.
     * @return Value of the attribute, or null if not found.
     */
    public Object getAttribute(String name)
    {
        return extraAttributes.get(name);
    }

    /**
     * Get additional attributes as a Map.
     * @return Map A Map containing attribute name - value pairs.
     */
    public Map getAttributes()
    {
        Map map = new HashMap(extraAttributes);
        // Add property attributes using old names
        /*
          map.put(DEFINITIONS_CONFIG_PARAMETER_NAME, getDefinitionConfigFiles());
          map.put(TILES_DETAILS_PARAMETER_NAME, Integer.toString(getDebugLevel()) );
          map.put(PARSER_DETAILS_PARAMETER_NAME, Integer.toString(getParserDebugLevel()) );
          map.put(PARSER_VALIDATE_PARAMETER_NAME, new Boolean(getParserValidate()).toString() );

          if( ! "org.apache.struts.tiles.xmlDefinition.I18nFactorySet".equals(getFactoryClassname()) )
          map.put(FACTORY_CLASSNAME_PARAMETER_NAME, getFactoryClassname());
        */
        return map;
    }

    /**
     * Populate this config object from properties map, based on
     * the specified name/value pairs.  This method uses the populate() method from
     * org.apache.commons.beanutils.BeanUtil.
     * <p>
     * Properties keys are scanned for old property names, and linked to the new name
     * if necessary. This modifies the properties map.
     * <p>
     * The particular setter method to be called for each property is
     * determined using the usual JavaBeans introspection mechanisms.  Thus,
     * you may identify custom setter methods using a BeanInfo class that is
     * associated with the class of the bean itself.  If no such BeanInfo
     * class is available, the standard method name conversion ("set" plus
     * the capitalized name of the property in question) is used.
     * <p>
     * <strong>NOTE</strong>:  It is contrary to the JavaBeans Specification
     * to have more than one setter method (with different argument
     * signatures) for the same property.
     *
     * @param properties Map keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set.
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method.
     * @exception java.lang.reflect.InvocationTargetException if the property accessor method
     *  throws an exception.
     * @see org.apache.commons.beanutils.BeanUtils
     */
    public void populate( Map properties)
        throws java.lang.IllegalAccessException,java.lang.reflect.InvocationTargetException
    {
        // link old parameter names for backward compatibility
        linkOldPropertyNames(properties);
        BeanUtils.populate( this, properties);
    }

    /**
     * Link old property names to new property names.
     * This modifies the map.
     * @param properties Map keyed by property name, with the
     *  corresponding (String or String[]) value(s) to be set.
     */
    static public void linkOldPropertyNames( Map properties)
    {
        Set entries = properties.entrySet();
        Map toAdd = new HashMap();
        Iterator i = entries.iterator();
        while( i.hasNext() )
            {
                Map.Entry entry = (Map.Entry)i.next();
                if(DEFINITIONS_CONFIG_PARAMETER_NAME.equals(entry.getKey()))
                    toAdd.put( "definitionConfigFiles", entry.getValue());
                else if(FACTORY_CLASSNAME_PARAMETER_NAME.equals(entry.getKey()))
                    toAdd.put( "factoryClassname", entry.getValue());
                else if(PARSER_DETAILS_PARAMETER_NAME.equals(entry.getKey()))
                    toAdd.put( "parserDebugLevel", entry.getValue());
                else if(PARSER_VALIDATE_PARAMETER_NAME.equals(entry.getKey()))
                    toAdd.put( "parserValidate", entry.getValue());
                else if(TILES_DETAILS_PARAMETER_NAME.equals(entry.getKey()))
                    toAdd.put( "debugLevel", entry.getValue());
            } // end loop
        if( toAdd.size() > 0 )
            properties.putAll( toAdd );
    }

    /**
     * Get the factory name.
     */
    public String getFactoryName()
    {
        return factoryName;
    }
    /**
     * Set the factory name.
     * @param factoryName Name of the factory.
     */
    public void setFactoryName(String factoryName)
    {
        this.factoryName = factoryName;
    }
}
