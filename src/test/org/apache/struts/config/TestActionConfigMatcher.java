/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/config/TestActionConfigMatcher.java,v 1.6 2004/01/13 12:48:53 husted Exp $
 * $Revision: 1.6 $
 * $Date: 2004/01/13 12:48:53 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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


package org.apache.struts.config;


import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.mock.TestMockBase;


/**
 * <p>Unit tests for <code>org.apache.struts.util.ActionConfigMatcher</code>.</p>
 *
 * @version $Revision: 1.6 $ $Date: 2004/01/13 12:48:53 $
 */

public class TestActionConfigMatcher extends TestMockBase {


    // ----------------------------------------------------------------- Basics


    public TestActionConfigMatcher(String name) {
        super(name);
    }


    public static void main(String args[]) {
        junit.awtui.TestRunner.main
            (new String[] { TestActionConfigMatcher.class.getName() } );
    }


    public static Test suite() {
        return (new TestSuite(TestActionConfigMatcher.class));
    }


    // ----------------------------------------------------- Instance Variables



    // ----------------------------------------------------- Setup and Teardown


    public void setUp() {

        super.setUp();

    }


    public void tearDown() {

        super.tearDown();

    }


    // ------------------------------------------------------- Individual Tests


    // ---------------------------------------------------------- match()


    public void testNoMatch() {
        ActionConfig[] configs = new ActionConfig[1];
        ActionConfig mapping = buildActionConfig("/foo");
        configs[0] = mapping;
        ActionConfigMatcher matcher = new ActionConfigMatcher(configs);
        
        assertNull("ActionConfig shouldn't be matched", matcher.match("/test"));
    }
    
    public void testNoWildcardMatch() {
        ActionConfig[] configs = new ActionConfig[1];
        ActionConfig mapping = buildActionConfig("/fooBar");
        configs[0] = mapping;
        ActionConfigMatcher matcher = new ActionConfigMatcher(configs);
        
        assertNull("ActionConfig shouldn't be matched", matcher.match("/fooBar"));
    }
    
    public void testShouldMatch() {
        ActionConfig[] configs = new ActionConfig[1];
        ActionConfig mapping = buildActionConfig("/foo*");
        configs[0] = mapping;
        ActionConfigMatcher matcher = new ActionConfigMatcher(configs);
        
        ActionConfig matched = matcher.match("/fooBar");
        assertNotNull("ActionConfig should be matched", matched);
        assertTrue("ActionConfig should have two action forward", matched.findForwardConfigs().length == 2);
        assertTrue("ActionConfig should have two exception forward", matched.findExceptionConfigs().length == 2);
    }
    
    public void testCheckSubstitutionsMatch() {
        ActionConfig[] configs = new ActionConfig[1];
        ActionConfig mapping = buildActionConfig("/foo*");
        configs[0] = mapping;
        ActionConfigMatcher matcher = new ActionConfigMatcher(configs);
        ActionConfig m = matcher.match("/fooBar");
        
        assertTrue("Name hasn't been replaced", "name,Bar".equals(m.getName()));
        assertTrue("Path hasn't been replaced", "/fooBar".equals(m.getPath()));
        assertTrue("Prefix isn't correct", "foo".equals(m.getPrefix()));
        assertTrue("Scope isn't correct", "request".equals(m.getScope()));
        assertTrue("Suffix isn't correct", "bar".equals(m.getSuffix()));
        assertTrue("Unknown isn't correct", !m.getUnknown());
        assertTrue("Validate isn't correct", m.getValidate());

        assertTrue("Type hasn't been replaced", "foo.bar.BarAction".equals(m.getType()));
        assertTrue("Roles hasn't been replaced", "public,Bar".equals(m.getRoles()));
        assertTrue("Parameter hasn't been replaced", "param,Bar".equals(m.getParameter()));
        assertTrue("Attribute hasn't been replaced", "attrib,Bar".equals(m.getAttribute()));
        assertTrue("Forward hasn't been replaced", "fwd,Bar".equals(m.getForward()));
        assertTrue("Include hasn't been replaced", "include,Bar".equals(m.getInclude()));
        assertTrue("Input hasn't been replaced", "input,Bar".equals(m.getInput()));

        ForwardConfig[] fConfigs = m.findForwardConfigs();
        boolean found = false;
        for (int x=0; x<fConfigs.length; x++) {
            ForwardConfig cfg = fConfigs[x];
            if ("name".equals(cfg.getName())) {
                found = true;
                assertTrue("ContextRelative isn't correct", cfg.getContextRelative());
                assertTrue("Path hasn't been replaced", "path,Bar".equals(cfg.getPath()));
            }
        }
        assertTrue("The forward config 'name' cannot be found", found);
    }
    
    private ActionConfig buildActionConfig(String path) {
        ActionMapping mapping = new ActionMapping();

        mapping.setName("name,{1}");
        mapping.setPath(path);
        mapping.setPrefix("foo");
        mapping.setScope("request");
        mapping.setSuffix("bar");
        mapping.setUnknown(false);
        mapping.setValidate(true);

        mapping.setType("foo.bar.{1}Action");
        mapping.setRoles("public,{1}");
        mapping.setParameter("param,{1}");
        mapping.setAttribute("attrib,{1}");
        mapping.setForward("fwd,{1}");
        mapping.setInclude("include,{1}");
        mapping.setInput("input,{1}");

        ForwardConfig cfg = new ActionForward();
        cfg.setContextRelative(true);
        cfg.setName("name");
        cfg.setPath("path,{1}");
        mapping.addForwardConfig(cfg);
        
        cfg = new ActionForward();
        cfg.setContextRelative(true);
        cfg.setName("name2");
        cfg.setPath("path2");
        mapping.addForwardConfig(cfg);
 
        ExceptionConfig excfg = new ExceptionConfig();
        excfg.setKey("foo");
        excfg.setType("foo.Bar");
        excfg.setPath("path");
        mapping.addExceptionConfig(excfg);

        excfg = new ExceptionConfig();
        excfg.setKey("foo2");
        excfg.setType("foo.Bar2");
        excfg.setPath("path2");
        mapping.addExceptionConfig(excfg);


        mapping.freeze();

        return mapping;
    }


}
