/*
 * Copyright 2002,2004 The Apache Software Foundation.
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

package org.apache.struts.faces.sysclient;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlBase;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHead;
import com.gargoylesoftware.htmlunit.html.HtmlLink;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpState;


/**
 * <p>Test case for a simple Struts-Faces page that is statically examined
 * to ensure that all of the expected elements have been rendered correctly.</p>
 *
 * @version $Revision: 1.1 $ $Date: 2004/08/03 07:01:09 $
 */

public class SimpleTestCase extends AbstractTestCase {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Construct a new instance of this test case.</p>
     *
     * @param name Name of the new test case
     */
    public SimpleTestCase(String name) {

        super(name);

    }


    // ------------------------------------------------------ Instance Variables


    // ------------------------------------------------------ Test Setup Methods


    /**
     * <p>Set up the instance variables required for this test case.</p>
     */
    public void setUp() throws Exception {

        super.setUp();
        page("/simple.faces");

    }


    /**
     * <p>Return the set of tests included in this test suite.</p>
     */
    public static Test suite() {

        return (new TestSuite(SimpleTestCase.class));

    }


    /**
     * <p>Tear down instance variables required by this test case.</p>
     */
    public void tearDown() {

        super.tearDown();

    }



    // ------------------------------------------------- Individual Test Methods


    /**
     * <p>Verify the presence and contents of a base element.</p>
     */
    public void testBase() throws Exception {

        HtmlBase base = null;
        Iterator kids = head().getChildElements().iterator();
        while (kids.hasNext()) {
            HtmlElement kid = (HtmlElement) kids.next();
            if (kid instanceof HtmlBase) {
                assertNull("Only one base element present", base);
                base = (HtmlBase) kid;
            }
        }
        assertNotNull("Exactly one base element present", base);
        assertEquals("base", base.getTagName());
        assertEquals(url("/simple.jsp").toString(), base.getHrefAttribute());
        assertEquals("", base.getTargetAttribute());

    }


    /**
     * <p>Verify the presence and contents of an html element.</p>
     */
    public void testHtml() throws Exception {

        HtmlElement html = (HtmlElement) page;
        assertEquals("html", html.getTagName());
        assertEquals("http://www.w3.org/1999/xhtml", html.getAttributeValue("xmlns"));
        // TODO: verify the "lang" attribute
        // TODO: verify the "xml:lang" attribute

    }


    /**
     * <p>Verify the presence and contents of a stylesheet element.</p>
     */
    public void testStylesheet() throws Exception {

        HtmlLink link = null;
        Iterator kids = head().getChildElements().iterator();
        while (kids.hasNext()) {
            HtmlElement kid = (HtmlElement) kids.next();
            if (kid instanceof HtmlLink) {
                assertNull("Only one stylesheet element present", link);
                link = (HtmlLink) kid;
            }
        }
        assertNotNull("Exactly one stylesheet element present", link);
        assertEquals("link", link.getTagName());
        assertEquals("", link.getCharsetAttribute());
        String url = this.url.toString();
        url = url.substring(0, url.length() - 1);
        url = url.substring(url.lastIndexOf('/'));
        assertEquals(url + "/stylesheet.css", link.getHrefAttribute());
        assertEquals("", link.getHrefLangAttribute());
        assertEquals("", link.getMediaAttribute());
        assertEquals("stylesheet", link.getRelAttribute());
        assertEquals("", link.getRevAttribute());
        assertEquals("", link.getTargetAttribute());
        assertEquals("text/css", link.getTypeAttribute());
        
    }


    /**
     * <p>Verify the title of the returned page.</p>
     */
    public void testTitle() throws Exception {

        assertEquals("simple", title());

    }


}
