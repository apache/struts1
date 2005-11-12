/*
 * $Id$
 *
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.chain.commands.generic;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.chain.Catalog;
import org.apache.commons.chain.CatalogFactory;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.apache.struts.chain.commands.util.ClassUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import java.lang.reflect.InvocationTargetException;

/**
 * Variant on chain LookupCommand which can optionally
 * wrap the context it passes to the looked up command
 * in an alternative class.
 *
 */
public class WrappingLookupCommand implements Filter {

    public WrappingLookupCommand() {
        catalogName = null;
        name = null;
        nameKey = null;
        optional = false;
    }

    // ------------------------------------------------------ Instance Variables
    private String catalogName = null;
    private String name = null;
    private String nameKey = null;
    private String wrapperClassName = null;
    private boolean optional = false;

    private static final Log log = 
            LogFactory.getLog(WrappingLookupCommand.class);


    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public String getWrapperClassName() {
        return wrapperClassName;
    }

    public void setWrapperClassName(String wrapperClassName) {
        this.wrapperClassName = wrapperClassName;
    }

    public boolean execute(Context context)
            throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("execute ["+this+"]");
        }
        
        Command command = getCommand(context);
        if(command != null) {
            return command.execute(getContext(context));
        } else {
            return false;
        }
    }

    public boolean postprocess(Context context, Exception exception) {
        Command command = getCommand(context);
        if(command != null && (command instanceof Filter)) {
            try {
                return ((Filter)command).postprocess(
                        getContext(context), exception);
            }
            catch (NoSuchMethodException ex) {
                log.error("Error wrapping context in postprocess", ex);
            }catch (IllegalAccessException ex) {
                log.error("Error wrapping context in postprocess", ex);
            }catch (InvocationTargetException ex) {
                log.error("Error wrapping context in postprocess", ex);
            }catch (InstantiationException ex) {
                log.error("Error wrapping context in postprocess", ex);
            }catch (ClassNotFoundException ex) {
                log.error("Error wrapping context in postprocess", ex);
            }
        }
        return false;
    }

    protected Command getCommand(Context context) {
        CatalogFactory catalogFactory = CatalogFactory.getInstance();
        String catalogName = getCatalogName();
        Catalog catalog = null;
        if(catalogName == null) {
            catalog = catalogFactory.getCatalog();
            catalogName = "{default}"; // for debugging purposes
        } else {
            catalog = catalogFactory.getCatalog(catalogName);
        }
        if(catalog == null) {
            throw new IllegalArgumentException(
                    "Cannot find catalog '" + catalogName + "'");
        }

        Command command = null;
        String name = getName();

        if(name == null) {
            name = (String)context.get(getNameKey());
        }

        if(name != null) {
            if (log.isDebugEnabled()) {
                log.debug("Lookup command " + name 
                        + " in catalog " + catalogName);
            }
            command = catalog.getCommand(name);
            if (log.isDebugEnabled()) {
                log.debug("Found command " + command + ";"
                        + " optional: " + isOptional());
            }
            if(command == null && !isOptional()) {
                throw new IllegalArgumentException("Cannot find command " 
                    + "'" + name + "' in catalog '" + catalogName + "'");
            } else {
                return command;
            }
        } else {
            throw new IllegalArgumentException("No command name");
        }
    }

    /**
     * <p>If the <code>wrapperClassName</code> property is not null, return a 
     * <code>Context</code> of the type specified by 
     * <code>wrapperClassName</code>, instantiated using a single-arg
     * constructor which takes the <code>context</code> passed as an 
     * argument to this method.</p>
     * 
     * <p>This method throws an exception if the wrapperClass cannot be found, 
     * or if there are any errors instantiating the wrapping context.</p>
     * 
     * @param context
     * @return
     */
    protected Context getContext(Context context)
            throws ClassNotFoundException,
                   InstantiationException,
                   InvocationTargetException,
                   IllegalAccessException,
                   NoSuchMethodException
    {
        if (wrapperClassName == null) {
            if (log.isDebugEnabled()) {
                log.debug("No defined wrapper class; " +
                        "returning original context.");
            }
            return context;
        }

        if (log.isDebugEnabled()) {
            log.debug("Looking for wrapper class: " + wrapperClassName);
        }
        
        Class wrapperClass = ClassUtils.getApplicationClass(wrapperClassName);
        
        if (log.isDebugEnabled()) {
            log.debug("Instantiating wrapper class");
        }
        
        return (Context) ConstructorUtils
                .invokeConstructor(wrapperClass, new Object[] { context });
    }

}