/*
 *  ====================================================================
 *
 *  The Apache Software License, Version 1.1
 *
 *  Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 *  reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  3. The end-user documentation included with the redistribution, if
 *  any, must include the following acknowlegement:
 *  "This product includes software developed by the
 *  Apache Software Foundation (http://www.apache.org/)."
 *  Alternately, this acknowlegement may appear in the software itself,
 *  if and wherever such third-party acknowlegements normally appear.
 *
 *  4. The names "The Jakarta Project", "Struts", and "Apache Software
 *  Foundation" must not be used to endorse or promote products derived
 *  from this software without prior written permission. For written
 *  permission, please contact apache@apache.org.
 *
 *  5. Products derived from this software may not be called "Apache"
 *  nor may "Apache" appear in their names without prior written
 *  permission of the Apache Group.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 *  ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 *  USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *  SUCH DAMAGE.
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many
 *  individuals on behalf of the Apache Software Foundation.  For more
 *  information on the Apache Software Foundation, please see
 *  <http://www.apache.org/>.
 *
 */
package org.apache.struts.action;

// Struts imports:
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.WildcardHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


/**
 *  Matches paths against pre-compiled wildcard expressions pulled from 
 *  action mappings. It uses the wildcard matcher from the Apache
 *  Cocoon project.
 *
 * @author    Don Brown
 */
class ActionMappingMatcher {

    /**  
     * The logging instance 
     */
    private static final Log log =
        LogFactory.getLog(ActionMappingMatcher.class);
    
    /**  
     * The compiled paths and their associated ActionMapping's 
     */
    private List compiledPaths;

    /**
     *  Finds and precompiles the wildcard patterns from the ActionMapping
     *  "path" attributes.
     *  ActionMapping's will be evaluated in the order they exist in the
     *  Struts config file. Only paths that actually contain a wildcard
     *  will be compiled.
     *
     * @param  configs  An array of ActionConfig's to process
     */
    protected ActionMappingMatcher(ActionConfig[] configs) {
        compiledPaths = new ArrayList();
        int[] pattern;
        String path;
        for (int x = 0; x < configs.length; x++) {
            path = configs[x].getPath();
            if (path.indexOf('*') > -1) {
                if (path.length() > 0 && path.charAt(0) == '/') {
                    path = path.substring(1);
                }
                if (log.isDebugEnabled()) {
                    log.debug("Compiling action mapping path '" + path + "'");
                }    
                pattern = WildcardHelper.compilePattern(path);
                compiledPaths.add(new Mapping(pattern, configs[x]));
            }    
        }
    }

    /**
     *  Matches the path against the compiled wildcard patterns.
     *
     * @param  path             The portion of the request URI for selecting a
     *      mapping
     * @return                  The action mapping if matched, else null
     */
    protected ActionMapping match(String path) {

        ActionMapping mapping = null;
        if (compiledPaths.size() > 0) {
            if (log.isDebugEnabled()) {
                log.debug("Attempting to match '" + path
                    + "' to a wildcard pattern");
            }    
            if (path.length() > 0 && path.charAt(0) == '/') {
                path = path.substring(1);
            }    
            Mapping m;
            HashMap vars = new HashMap();
            for (Iterator i = compiledPaths.iterator(); i.hasNext();) {
                m = (Mapping) i.next();
                if (WildcardHelper.match(vars, path, m.getPattern())) {
                    mapping = convertActionMapping(
                            path,
                            (ActionMapping) m.getActionConfig(),
                            vars);
                }
            }
        }    

        return mapping;
    }
    
    /**
     *  Clones the ActionMapping and its children, replacing various properties
     *  with the values of the wildcard-matched strings.
     *
     * @param  path  The requested path
     * @param  orig  The original ActionMapping
     * @param  vars  A Map of wildcard-matched strings
     * @return       A cloned ActionMapping with appropriate properties replaced
     *      with wildcard-matched values
     */
    protected ActionMapping convertActionMapping(String path, 
            ActionMapping orig, Map vars) {
        ActionMapping mapping = new ActionMapping();
        mapping.setModuleConfig(orig.getModuleConfig());

        mapping.setMultipartClass(orig.getMultipartClass());
        mapping.setName(convertParam(orig.getName(), vars));
        if (path.charAt(0) != '/') {
            path = "/" + path;
        }    
        mapping.setPath(path);
        mapping.setPrefix(orig.getPrefix());
        mapping.setScope(orig.getScope());
        mapping.setSuffix(orig.getSuffix());
        mapping.setUnknown(orig.getUnknown());
        mapping.setValidate(orig.getValidate());

        mapping.setType(convertParam(orig.getType(), vars));
        mapping.setRoles(convertParam(orig.getRoles(), vars));
        mapping.setParameter(convertParam(orig.getParameter(), vars));
        mapping.setAttribute(convertParam(orig.getAttribute(), vars));
        mapping.setForward(convertParam(orig.getForward(), vars));
        mapping.setInclude(convertParam(orig.getInclude(), vars));
        mapping.setInput(convertParam(orig.getInput(), vars));

        ExceptionConfig[] exConfigs = orig.findExceptionConfigs();
        for (int x = 0; x < exConfigs.length; x++) {
            mapping.addExceptionConfig(exConfigs[x]);
        }

        ForwardConfig[] fConfigs = orig.findForwardConfigs();
        ForwardConfig cfg;
        for (int x = 0; x < fConfigs.length; x++) {
            cfg = new ActionForward();
            cfg.setContextRelative(fConfigs[x].getContextRelative());
            cfg.setName(fConfigs[x].getName());
            cfg.setPath(convertParam(fConfigs[x].getPath(), vars));
            mapping.addForwardConfig(cfg);
        }
        return mapping;
    }

    /**
     *  Inserts into a value wildcard-matched strings where specified.
     *
     * @param  val   The value to convert
     * @param  vars  A Map of wildcard-matched strings
     * @return       The new value
     */
    protected String convertParam(String val, Map vars) {
        if (val == null) {
            return null;
        } else if (val.indexOf("{") == -1) {
            return val;
        }

        Map.Entry entry;
        StringBuffer key = new StringBuffer("{0}");
        StringBuffer ret = new StringBuffer(val);
        int x;
        for (Iterator i = vars.entrySet().iterator(); i.hasNext();) {
            entry = (Map.Entry) i.next();
            key.setCharAt(1, ((String) entry.getKey()).charAt(0));
            x = ret.toString().indexOf(key.toString());
            if (x > -1) {
                ret.replace(x, x + 3, (String) entry.getValue());
            }
        }
        return ret.toString();
    }

    /**
     *  Stores a compiled wildcard pattern and the ActionConfig it came from
     */
    private class Mapping {

        /**  The compiled pattern */
        private int[] pattern;

        /**  The original ActionConfig */
        private ActionConfig config;

        /**
         *  Contructs a read-only Mapping instance
         *
         *  @param pattern  The compiled pattern
         *  @param config   The original ActionConfig
         */
        public Mapping(int[] pattern, ActionConfig config) {
            this.pattern = pattern;
            this.config = config;
        }    

        /**
         *  Gets the compiled wildcard pattern
         *
         *  @return The compiled pattern
         */
        public int[] getPattern() {
            return this.pattern;
        }

        /**
         *  Gets the ActionConfig that contains the pattern
         *
         *  @return The associated ActionConfig
         */
        public ActionConfig getActionConfig() {
            return this.config;
        }    
    }    
}

