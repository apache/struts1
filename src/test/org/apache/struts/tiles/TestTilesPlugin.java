/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/tiles/TestTilesPlugin.java,v 1.3 2003/05/04 22:41:13 dgraham Exp $
 * $Revision: 1.3 $
 * $Date: 2003/05/04 22:41:13 $
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


import javax.servlet.ServletException;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.ModuleConfigFactory;
import org.apache.struts.config.PlugInConfig;
import org.apache.struts.mock.MockActionServlet;
import org.apache.struts.mock.TestMockBase;
import org.apache.struts.util.RequestUtils;

/**
 * <p>Unit tests for <code>org.apache.struts.tiles.*</code>.</p>
 *
 * @author Cedric Dumoulin
 * @version $Revision: 1.3 $ $Date: 2003/05/04 22:41:13 $
 */

public class TestTilesPlugin extends TestMockBase {


  protected ModuleConfig module1;
  protected ModuleConfig module2;
  protected MockActionServlet actionServlet;

    // ----------------------------------------------------------------- Basics


    public TestTilesPlugin(String name) {
        super(name);
    }


    public static void main(String args[]) {
        junit.awtui.TestRunner.main
            (new String[] { TestTilesPlugin.class.getName() } );
    }


    public static Test suite() {
        return (new TestSuite(TestTilesPlugin.class));
    }


    // ----------------------------------------------------- Instance Variables



    // ----------------------------------------------------- Setup and Teardown


    public void setUp()
    {

    super.setUp();
    TilesUtil.testReset();
    actionServlet = new MockActionServlet(context, config);
    }


    public void tearDown() {

        super.tearDown();

    }


    // ------------------------------------------------------- Individual Tests


    /**
     * Create a module configuration
     * @param moduleName
     */
    public ModuleConfig createModuleConfig(
    	String moduleName,
    	String configFileName,
    	boolean moduleAware) {
            
    	ModuleConfig moduleConfig =
    		ModuleConfigFactory.createFactory().createModuleConfig(moduleName);
            
    	context.setAttribute(Globals.MODULE_KEY + moduleName, moduleConfig);
    
    	// Set tiles plugin
    	PlugInConfig pluginConfig = new PlugInConfig();
    	pluginConfig.setClassName("org.apache.struts.tiles.TilesPlugin");
        
    	pluginConfig.addProperty(
    		"moduleAware",
    		(moduleAware == true ? "true" : "false"));
            
    	pluginConfig.addProperty(
    		"definitions-config",
    		"/org/apache/struts/tiles/config/" + configFileName);
            
    	moduleConfig.addPlugInConfig(pluginConfig);
    	return moduleConfig;
    }

    /**
     * Fake call to init module plugins
     * @param config
     */
  public void initModulePlugIns( ModuleConfig moduleConfig)
  {
  PlugInConfig plugInConfigs[] = moduleConfig.findPlugInConfigs();
  PlugIn plugIns[] = new PlugIn[plugInConfigs.length];

  context.setAttribute(Globals.PLUG_INS_KEY + moduleConfig.getPrefix(), plugIns);
  for (int i = 0; i < plugIns.length; i++) {
      try {
          plugIns[i] =
              (PlugIn)RequestUtils.applicationInstance(plugInConfigs[i].getClassName());
          BeanUtils.populate(plugIns[i], plugInConfigs[i].getProperties());
            // Pass the current plugIn config object to the PlugIn.
            // The property is set only if the plugin declares it.
            // This plugin config object is needed by Tiles
          BeanUtils.copyProperty( plugIns[i], "currentPlugInConfigObject", plugInConfigs[i]);
          plugIns[i].init(actionServlet, moduleConfig);
      } catch (ServletException e) {
          // Lets propagate
          e.printStackTrace();
          //throw e;
      } catch (Exception e) {
          e.printStackTrace();
          //throw e;
      }
  }
  }

    // ---------------------------------------------------------- absoluteURL()


    /**
     * Test multi factory creation when moduleAware=true.
     */
    public void testMultiFactory() {
    	// init TilesPlugin
    	module1 = createModuleConfig("/module1", "tiles-defs.xml", true);
    	module2 = createModuleConfig("/module2", "tiles-defs.xml", true);
    	initModulePlugIns(module1);
    	initModulePlugIns(module2);
    
    	// mock request context
    	request.setAttribute(Globals.MODULE_KEY, module1);
    	request.setPathElements("/myapp", "/module1/foo.do", null, null);
    	// Retrieve factory for module1
    	DefinitionsFactory factory1 =
    		TilesUtil.getDefinitionsFactory(request, context);
            
    	assertNotNull("factory found", factory1);
    	assertEquals(
    		"factory name",
    		"/module1",
    		factory1.getConfig().getFactoryName());
    
    	// mock request context
    	request.setAttribute(Globals.MODULE_KEY, module2);
    	request.setPathElements("/myapp", "/module2/foo.do", null, null);
    	// Retrieve factory for module2
    	DefinitionsFactory factory2 =
    		TilesUtil.getDefinitionsFactory(request, context);
    	assertNotNull("factory found", factory2);
    	assertEquals(
    		"factory name",
    		"/module2",
    		factory2.getConfig().getFactoryName());
    
    	// Check that factory are different
    	assertNotSame("Factory from different modules", factory1, factory2);
    }

    /**
     * Test single factory creation when moduleAware=false.
     */
  public void testSingleSharedFactory()
  {
    // init TilesPlugin
  module1 = createModuleConfig( "/module1", "tiles-defs.xml", false );
  module2 = createModuleConfig( "/module2", "tiles-defs.xml", false );
  initModulePlugIns(module1);
  initModulePlugIns(module2);

    // mock request context
  request.setAttribute(Globals.MODULE_KEY, module1);
  request.setPathElements("/myapp", "/module1/foo.do", null, null);
    // Retrieve factory for module1
  DefinitionsFactory factory1 = TilesUtil.getDefinitionsFactory( request, context);
  assertNotNull( "factory found", factory1);
  assertEquals( "factory name", "/module1", factory1.getConfig().getFactoryName() );

    // mock request context
  request.setAttribute(Globals.MODULE_KEY, module2);
  request.setPathElements("/myapp", "/module2/foo.do", null, null);
    // Retrieve factory for module2
  DefinitionsFactory factory2 = TilesUtil.getDefinitionsFactory( request, context);
  assertNotNull( "factory found", factory2);
  assertEquals( "factory name", "/module1", factory2.getConfig().getFactoryName() );

    // Check that factory are different
  assertEquals("Same factory", factory1, factory2);
  }


}

