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
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * <p>Test case for a simple Struts-Faces page that is statically examined
 * to ensure that all of the expected elements have been rendered correctly.</p>
 *
 * @version $Rev$ $Date$
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
     * <p>Verify that the loadMessages tag properly exposes a Struts
     * MessageResources instance as a Map.</p>
     */
    public void testLoadMessages() throws Exception {

        HtmlSpan span = null;

        span = (HtmlSpan) element("lookup-simple");
        assertNotNull(span);
        assertEquals("Resource Simple Text", span.asText());

        span = (HtmlSpan) element("lookup-filtered");
        assertNotNull(span);
        assertEquals("Resource <b>Filtered</b> Text", span.asText());

        span = (HtmlSpan) element("lookup-unfiltered");
        assertNotNull(span);
        assertEquals("Resource Unfiltered Text", span.asText());

    }


    /**
     * <p>Verify the presence and contents of several message components.</p>
     */
    public void testMessage() throws Exception {

        HtmlSpan span = null;

        span = (HtmlSpan) element("message-direct");
        assertNotNull(span);
        assertEquals("Resource Simple Text", span.asText());

        span = (HtmlSpan) element("message-indirect");
        assertNotNull(span);
        assertEquals("Resource Simple Text", span.asText());

        span = (HtmlSpan) element("message-filtered");
        assertNotNull(span);
        assertEquals("Resource <b>Filtered</b> Text", span.asText());

        span = (HtmlSpan) element("message-unfiltered");
        assertNotNull(span);
        assertEquals("Resource Unfiltered Text", span.asText());

        span = (HtmlSpan) element("message-substitute");
        assertNotNull(span);
        assertEquals("From Here to Eternity", span.asText());

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
     * <p>Verify the proper operation of clientId in a subview.</p.
     */
    public void testSubview() throws Exception {

        HtmlSpan span = null;

        span = (HtmlSpan) element("subview:write1");
        assertNotNull(span);
        assertEquals("subview:write1", span.asText());

    }


    /**
     * <p>Verify the title of the returned page.</p>
     */
    public void testTitle() throws Exception {

        assertEquals("simple", title());

    }


    /**
     * <p>Verify the presence and contents of several write components.</p>
     */
    public void testWrite() throws Exception {

        HtmlSpan span = null;

        span = (HtmlSpan) element("write-literal");
        assertNotNull(span);
        assertEquals("Literal Write Content", span.asText());

        span = (HtmlSpan) element("write-filtered");
        assertNotNull(span);
        assertEquals("Literal <b>Filtered</b> Content", span.asText());

        span = (HtmlSpan) element("write-unfiltered");
        assertNotNull(span);
        assertEquals("Literal Unfiltered Content", span.asText());

        span = (HtmlSpan) element("retrieved-literal");
        assertNotNull(span);
        assertEquals("Retrieved Simple Content", span.asText());

        span = (HtmlSpan) element("retrieved-filtered");
        assertNotNull(span);
        assertEquals("Retrieved <b>Filtered</b> Content", span.asText());

        span = (HtmlSpan) element("retrieved-unfiltered");
        assertNotNull(span);
        assertEquals("Retrieved Unfiltered Content", span.asText());

    }


}
