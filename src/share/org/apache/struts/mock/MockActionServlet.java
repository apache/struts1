/*
 * $Id$
 *
 * Copyright 1999-2004 The Apache Software Foundation.
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


package org.apache.struts.mock;


import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;


import org.apache.struts.action.ActionServlet;

/**
 * <p>Mock <strong>ActionServlet</strong> object for low-level unit tests
 * of Struts controller components.  Coarser grained tests should be
 * implemented in terms of the Cactus framework, instead of the mock
 * object classes.</p>
 *
 * <p><strong>WARNING</strong> - Only getter methods for servletContext and
 * servletConfig are provided, plus additional methods to configure this
 * object as necessary.  Methods for unsupported operations will throw
 * <code>UnsupportedOperationException</code>.</p>
 *
 * <p><strong>WARNING</strong> - Because unit tests operate in a single
 * threaded environment, no synchronization is performed.</p>
 *
 * @version $Rev$ $Date$
 */
public class MockActionServlet extends ActionServlet {
  protected ServletContext servletContext;
  protected ServletConfig servletConfig;

    /**
     * Constructor.
     */
  public MockActionServlet( ServletContext servletContext, ServletConfig servletConfig  )
  {
  this.servletContext = servletContext;
  this.servletConfig = servletConfig;
  }

    /**
     * Constructor.
     */
  public MockActionServlet( )
  {
      ; // do nothing
  }

    /**
     * Set property
     * @param servletContext
     */
  public void setServletContext( ServletContext servletContext )
  {
  this.servletContext = servletContext;
  }

    /**
     * Get property
     * @return
     */
  public ServletContext getServletContext(  )
  {
  return servletContext;
  }

    /**
     * Set property
     * @param servletConfig
     */
  public void setServletConfig( ServletConfig servletConfig )
  {
  this.servletConfig = servletConfig;
  }

    /**
     * Get property
     * @return
     */
  public ServletConfig getServletConfig(  )
  {
  return servletConfig;
  }

  /**
   * Expose as public so that test classes can exercise things which
   * retrieve messages.
   */
  public void initInternal() throws ServletException {
      super.initInternal();
  }

}
