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
package org.apache.struts.taglib.logic;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.JspTestCase;
import org.apache.struts.taglib.SimpleBeanForTesting;
import org.apache.struts.util.LabelValueBean;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.logic.EmptyTag</code>
 * class.
 */
public class TestEmptyTag extends JspTestCase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestEmptyTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestEmptyTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestEmptyTag.class);
    }

    private void runNameTest(String testKey, EmptyTag et,
                             Object o, int scope, String whichScope,
                             boolean condition, boolean useProperty,
                             String property)
            throws ServletException, JspException {

        pageContext.setAttribute(testKey, o, scope);
        et.setPageContext(pageContext);
        et.setName(testKey);
        if (useProperty) {
            et.setProperty(property);
        }
        et.setScope(whichScope);

        assertEquals(
                "Testing " + testKey + " with EmtpyTag in " + whichScope
                        + " scope",
                condition,
                et.condition());
    }

    /**
     * Testing <code>EmptyTag</code> using name attribute in the some scope.
     */
    public void testEmptyTagUsingName()
            throws ServletException, JspException {

        String tst = "";
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                tst,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                false,
                null);
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                tst,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                false,
                null);
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                tst,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                false,
                null);

        tst = "hello";
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                tst,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                false,
                null);
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                tst,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                false,
                null);
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                tst,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                false,
                null);

        // Testing ArrayList
        ArrayList lst = new ArrayList();
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                lst,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                false,
                null);
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                lst,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                false,
                null);
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                lst,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                false,
                null);

        lst.add(0, "test");
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                lst,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                false,
                null);
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                lst,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                false,
                null);
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                lst,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                false,
                null);

        // Testing HashMap
        HashMap map = new HashMap();
        runNameTest("testMapEmpty",
                new EmptyTag(),
                map,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                false,
                null);
        runNameTest("testMapEmpty",
                new EmptyTag(),
                map,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                false,
                null);
        runNameTest("testMapEmpty",
                new EmptyTag(),
                map,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                false,
                null);

        map.put("testKey", "test");
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                map,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                false,
                null);
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                map,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                false,
                null);
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                map,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                false,
                null);

    }

    /**
     * Testing <code>EmptyTag</code> using name attribute in the some scope.
     */
    public void testEmptyTagUsingProperty()
            throws ServletException, JspException {

        LabelValueBean lvb = new LabelValueBean(null, null);
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                lvb,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                true,
                "value");
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                lvb,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                true,
                "value");
        runNameTest("testStringEmptyString",
                new EmptyTag(),
                lvb,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                true,
                "value");

        lvb.setValue("");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                true,
                "value");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                true,
                "value");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                true,
                "value");

        lvb.setValue("hello");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                true,
                "value");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                true,
                "value");
        runNameTest("testStringNotEmpty",
                new EmptyTag(),
                lvb,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                true,
                "value");

        // Testing ArrayList
        SimpleBeanForTesting sbft = new SimpleBeanForTesting();
        ArrayList lst = new ArrayList();
        sbft.setList(lst);
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                sbft,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                true,
                "list");
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                sbft,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                true,
                "list");
        runNameTest("testArrayListEmpty",
                new EmptyTag(),
                sbft,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                true,
                "list");

        lst.add(0, "test");
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                true,
                "list");
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                true,
                "list");
        runNameTest("testArrayListNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                true,
                "list");

        // Testing HashMap
        HashMap map = new HashMap();
        sbft.setMap(map);
        runNameTest("testMapEmpty",
                new EmptyTag(),
                sbft,
                PageContext.APPLICATION_SCOPE,
                "application",
                true,
                true,
                "map");
        runNameTest("testMapEmpty",
                new EmptyTag(),
                sbft,
                PageContext.SESSION_SCOPE,
                "session",
                true,
                true,
                "map");
        runNameTest("testMapEmpty",
                new EmptyTag(),
                sbft,
                PageContext.REQUEST_SCOPE,
                "request",
                true,
                true,
                "map");

        map.put("testKey", "test");
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.APPLICATION_SCOPE,
                "application",
                false,
                true,
                "map");
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.SESSION_SCOPE,
                "session",
                false,
                true,
                "map");
        runNameTest("testMapNotEmpty",
                new EmptyTag(),
                sbft,
                PageContext.REQUEST_SCOPE,
                "request",
                false,
                true,
                "map");

    }


}
