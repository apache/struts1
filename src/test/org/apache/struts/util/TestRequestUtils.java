/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/util/TestRequestUtils.java,v 1.1 2002/07/02 01:55:27 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2002/07/02 01:55:27 $
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


package org.apache.struts.util;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import junit.framework.*;
import org.apache.struts.action.Action;
import org.apache.struts.taglib.html.Constants;
import org.apache.struts.mock.TestMockBase;


/**
 * <p>Unit tests for <code>org.apache.struts.util.RequestUtils</code>.</p>
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2002/07/02 01:55:27 $
 */

public class TestRequestUtils extends TestMockBase {


    // ----------------------------------------------------------------- Basics


    public TestRequestUtils(String name) {
        super(name);
    }


    public static void main(String args[]) {
        junit.awtui.TestRunner.main
            (new String[] { TestRequestUtils.class.getName() } );
    }


    public static Test suite() {
        return (new TestSuite(TestRequestUtils.class));
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


    // ---------------------------------------------------------- absoluteURL()


    public void testAbsoluteURL() {

        request.setPathElements("/myapp", "action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.absoluteURL(request, "/foo/bar.jsp").toString();
            assertEquals("absoluteURL is correct",
                         "http://localhost:8080/myapp/foo/bar.jsp",
                         url);
        } catch (MalformedURLException e) {
            fail("Threw MalformedURLException: " + e);
        }

    }


    // ---------------------------------------------------- computeParameters()


    // No parameters and no transaction token
    public void testComputeParameters0a() {
        
        Map map = null;
        try {
            map = RequestUtils.computeParameters(page,
                                                 null, null, null, null,
                                                 null, null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNull("Map is null", map);

    }


    // No parameters but add transaction token
    public void testComputeParameters0b() {

        session.setAttribute(Action.TRANSACTION_TOKEN_KEY, "token");
        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, null, null, null, null,
                 null, null, null, true);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("One parameter in the returned map",
                     1, map.size());
        assertTrue("Transaction token parameter present",
                   map.containsKey(Constants.TOKEN_KEY));
        assertEquals("Transaction token parameter value",
                     "token",
                     (String) map.get(Constants.TOKEN_KEY));

    }


    // Single parameter -- name
    public void testComputeParameters1a() {

        session.setAttribute("attr", "bar");
        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, "foo", "attr", null, null,
                 null, null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("One parameter in the returned map",
                     1, map.size());
        assertTrue("Parameter present",
                   map.containsKey("foo"));
        assertEquals("Parameter value",
                     "bar",
                     (String) map.get("foo"));

    }


    // Single parameter -- scope + name
    public void testComputeParameters1b() {

        request.setAttribute("attr", "bar");
        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, "foo", "attr", null, "request",
                 null, null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("One parameter in the returned map",
                     1, map.size());
        assertTrue("Parameter present",
                   map.containsKey("foo"));
        assertEquals("Parameter value",
                     "bar",
                     (String) map.get("foo"));

    }


    // Single parameter -- scope + name + property
    public void testComputeParameters1c() {

        request.setAttribute("attr", new FormBean("bar"));

        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, "foo", "attr", "stringProperty", "request",
                 null, null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("One parameter in the returned map",
                     1, map.size());
        assertTrue("Parameter present",
                   map.containsKey("foo"));
        assertEquals("Parameter value",
                     "bar",
                     (String) map.get("foo"));

    }


    // Provided map -- name
    public void testComputeParameters2a() {

        Map map = new HashMap();
        map.put("foo1", "bar1");
        map.put("foo2", "bar2");
        session.setAttribute("attr", map);

        try {
            map = RequestUtils.computeParameters
                (page, null, null, null, null,
                 "attr", null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("Two parameter in the returned map",
                     2, map.size());
        assertTrue("Parameter foo1 present",
                   map.containsKey("foo1"));
        assertEquals("Parameter foo1 value",
                     "bar1",
                     (String) map.get("foo1"));
        assertTrue("Parameter foo2 present",
                   map.containsKey("foo2"));
        assertEquals("Parameter foo2 value",
                     "bar2",
                     (String) map.get("foo2"));

    }


    // Provided map -- scope + name
    public void testComputeParameters2b() {

        Map map = new HashMap();
        map.put("foo1", "bar1");
        map.put("foo2", "bar2");
        request.setAttribute("attr", map);

        try {
            map = RequestUtils.computeParameters
                (page, null, null, null, null,
                 "attr", null, "request", false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("Two parameter in the returned map",
                     2, map.size());
        assertTrue("Parameter foo1 present",
                   map.containsKey("foo1"));
        assertEquals("Parameter foo1 value",
                     "bar1",
                     (String) map.get("foo1"));
        assertTrue("Parameter foo2 present",
                   map.containsKey("foo2"));
        assertEquals("Parameter foo2 value",
                     "bar2",
                     (String) map.get("foo2"));

    }


    // Provided map -- scope + name + property
    public void testComputeParameters2c() {

        request.setAttribute("attr", new FormBean());

        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, null, null, null, null,
                 "attr", "mapProperty", "request", false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("Two parameter in the returned map",
                     2, map.size());
        assertTrue("Parameter foo1 present",
                   map.containsKey("foo1"));
        assertEquals("Parameter foo1 value",
                     "bar1",
                     (String) map.get("foo1"));
        assertTrue("Parameter foo2 present",
                   map.containsKey("foo2"));
        assertEquals("Parameter foo2 value",
                     "bar2",
                     (String) map.get("foo2"));

    }


    // Provided map -- name with one key and two values
    public void testComputeParameters2d() {

        Map map = new HashMap();
        map.put("foo", new String[] { "bar1", "bar2" });
        session.setAttribute("attr", map);

        try {
            map = RequestUtils.computeParameters
                (page, null, null, null, null,
                 "attr", null, null, false);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("One parameter in the returned map",
                     1, map.size());
        assertTrue("Parameter foo present",
                   map.containsKey("foo"));
        assertTrue("Parameter foo value type",
                   map.get("foo") instanceof String[]);
        String values[] = (String[]) map.get("foo");
        assertEquals("Values count",
                     2, values.length);

    }


    // Kitchen sink combination of parameters with a merge
    public void testComputeParameters3a() {

        request.setAttribute("attr", new FormBean("bar3"));
        session.setAttribute(Action.TRANSACTION_TOKEN_KEY, "token");

        Map map = null;
        try {
            map = RequestUtils.computeParameters
                (page, "foo1", "attr", "stringProperty", "request",
                 "attr", "mapProperty", "request", true);
        } catch (JspException e) {
            fail("JspException: " + e);
        }
        assertNotNull("Map is not null", map);
        assertEquals("Three parameter in the returned map",
                     3, map.size());

        assertTrue("Parameter foo1 present",
                   map.containsKey("foo1"));
        assertTrue("Parameter foo1 value type",
                   map.get("foo1") instanceof String[]);
        String values[] = (String[]) map.get("foo1");
        assertEquals("Values count",
                     2, values.length);

        assertTrue("Parameter foo2 present",
                   map.containsKey("foo2"));
        assertEquals("Parameter foo2 value",
                     "bar2",
                     (String) map.get("foo2"));

        assertTrue("Transaction token parameter present",
                   map.containsKey(Constants.TOKEN_KEY));
        assertEquals("Transaction token parameter value",
                     "token",
                     (String) map.get(Constants.TOKEN_KEY));
    }


    // ----------------------------------------------------------- computeURL()


    // Default subapp -- Forward only
    public void testComputeURL1a() {

        request.setPathElements("/myapp", "action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, "foo",
                 null, null,
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "/myapp/bar.jsp",
                     url);

    }


    // Default subapp -- Href only
    public void testComputeURL1b() {

        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 "http://foo.com/bar", null,
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "http://foo.com/bar",
                     url);

    }


    // Default subapp -- Page only
    public void testComputeURL1c() {

        request.setPathElements("/myapp", "action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 null, "/bar",
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "/myapp/bar",
                     url);

    }


    // Second subapp -- Forward only
    public void testComputeURL2a() {

        request.setAttribute(Action.APPLICATION_KEY, appConfig2);
        request.setPathElements("/myapp", "/2/action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, "foo",
                 null, null,
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "/myapp/2/baz.jsp",
                     url);

    }


    // Second subapp -- Href only
    public void testComputeURL2b() {

        request.setAttribute(Action.APPLICATION_KEY, appConfig2);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 "http://foo.com/bar", null,
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "http://foo.com/bar",
                     url);

    }


    // Second subapp -- Page only
    public void testComputeURL2c() {

        request.setAttribute(Action.APPLICATION_KEY, appConfig2);
        request.setPathElements("/myapp", "action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 null, "/bar",
                 null, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "/myapp/2/bar",
                     url);

    }


    // Add parameters only
    public void testComputeURL3a() {

        request.setPathElements("/myapp", "action.do", null, null);
        Map map = new HashMap();
        map.put("foo1", "bar1");
        map.put("foo2", "bar2");
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 null, "/bar",
                 map, null, false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertTrue("url value",
                   url.equals("/myapp/bar?foo1=bar1&foo2=bar2") ||
                   url.equals("/myapp/bar?foo2=bar2&foo1=bar1"));

    }


    // Add anchor only
    public void testComputeURL3b() {

        request.setPathElements("/myapp", "action.do", null, null);
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 null, "/bar",
                 null, "anchor", false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertEquals("url value",
                     "/myapp/bar#anchor",
                     url);

    }


    // Add parameters + anchor
    public void testComputeURL3c() {

        request.setPathElements("/myapp", "action.do", null, null);
        Map map = new HashMap();
        map.put("foo1", "bar1");
        map.put("foo2", "bar2");
        String url = null;
        try {
            url = RequestUtils.computeURL
                (page, null,
                 null, "/bar",
                 map, "anchor", false);
        } catch (MalformedURLException e) {
            fail("MalformedURLException: " + e);
        }
        assertNotNull("url present", url);
        assertTrue("url value",
                   url.equals("/myapp/bar?foo1=bar1&foo2=bar2#anchor") ||
                   url.equals("/myapp/bar?foo2=bar2&foo1=bar1#anchor"));

    }





    // ------------------------------------------------------------ serverURL()


    public void testServerURL() {

        String url = null;
        try {
            url = RequestUtils.serverURL(request).toString();
        } catch (MalformedURLException e) {
            fail("Threw MalformedURLException: " + e);
        }
        assertNotNull("serverURL is present", url);
        assertEquals("serverURL value",
                     "http://localhost:8080",
                     url);

    }


}
