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
package org.apache.struts.taglib;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.perl.Perl5Util;

public class TaglibTestBase extends JspTestCase {

    private static Log log = LogFactory.getLog(TaglibTestBase.class);

    private Perl5Util perl = new Perl5Util();

    public TaglibTestBase(String arg0) {
        super(arg0);
    }

    // Return the tests included in this test suite
    public static Test suite() {
        return (new TestSuite(TaglibTestBase.class));
    }

    protected void runTest(String whichTest, String jsp) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.forward(jsp);
    }

    protected String replace(String source, String find, String replace) {

        return perl.substitute("s/" + find + "/" + replace + "/", source);

    }

    /**
     * Utility method for reuse from the test JSPs.
     *
     * @param expected  The expected results for the test.
     * @param compareTo The results to compare to the expected results.
     */
    public static void assertResults(String expected, String compareTo) {
        Perl5Util perl = new Perl5Util();
        expected = perl.substitute("s/[\\r\\n]//gmx", expected);
        compareTo = perl.substitute("s/[\\r\\n]//gmx", compareTo);
        Assert.assertEquals(expected, compareTo);
    }

    public static void fail(String msg) {
        Assert.fail(msg);
    }

}
