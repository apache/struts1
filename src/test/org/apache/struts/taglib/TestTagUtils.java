/*
 * Copyright 2004 The Apache Software Foundation.
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

package org.apache.struts.taglib;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;


/**
 * Unit tests for the TagUtils.
 */
public class TestTagUtils extends TestCase {
    
    /** All logging goes through this logger */
    private static Log log = LogFactory.getLog(TestTagUtils.class);

    protected TagUtils tagutils = TagUtils.getInstance();

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestTagUtils(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(
            new String[] {TestTagUtils.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestTagUtils.class);
    }

    public void setUp() {
    }

    public void tearDown() {
    }
    
    /**
     * Test Operators.
     */
    public void testFilter() {

        // Test Null
        assertNull("Filter Test 1", tagutils.filter(null));

        // Test Empty String
        assertEquals("Filter Test 2", "", tagutils.filter(""));

        // Test Single Character
        assertEquals("Filter Test 3", "a", tagutils.filter("a"));

        // Test Multiple Characters
        assertEquals("Filter Test 4", "adhdfhdfhadhf", tagutils.filter("adhdfhdfhadhf"));

        // Test Each filtered Character
        assertEquals("Filter Test 5", "&lt;", tagutils.filter("<"));
        assertEquals("Filter Test 6", "&gt;", tagutils.filter(">"));
        assertEquals("Filter Test 7", "&amp;", tagutils.filter("&"));
        assertEquals("Filter Test 8", "&quot;", tagutils.filter("\""));
        assertEquals("Filter Test 9", "&#39;", tagutils.filter("'"));

        // Test filtering beginning, middle, end
        assertEquals("Filter Test 10", "a&lt;", tagutils.filter("a<"));
        assertEquals("Filter Test 11", "&lt;a", tagutils.filter("<a"));
        assertEquals("Filter Test 12", "a&lt;a", tagutils.filter("a<a"));

        // Test filtering beginning, middle, end
        assertEquals("Filter Test 13", "abc&lt;", tagutils.filter("abc<"));
        assertEquals("Filter Test 14", "&lt;abc", tagutils.filter("<abc"));
        assertEquals("Filter Test 15", "abc&lt;abc", tagutils.filter("abc<abc"));

        // Test Multiple Characters
        assertEquals("Filter Test 16", "&lt;input type=&quot;text&quot; value=&#39;Me &amp; You&#39;&gt;", 
                       tagutils.filter("<input type=\"text\" value='Me & You'>"));
    }

}
