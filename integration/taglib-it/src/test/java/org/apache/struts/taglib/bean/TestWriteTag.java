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
package org.apache.struts.taglib.bean;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.cactus.WebResponse;
import org.apache.struts.Globals;
import org.apache.struts.taglib.SimpleBeanForTesting;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.taglib.TaglibTestBase;

import javax.servlet.jsp.PageContext;
import java.util.Locale;

/**
 * Suite of unit tests for the <code>org.apache.struts.taglib.bean.WriteTag</code>
 * class.
 */
public class TestWriteTag extends TaglibTestBase {
    protected final static String TEST_STRING_VAL = "Test Value";
    protected final static Integer TEST_INTEGER_VAL = new Integer("1234");
    protected final static Double TEST_DOUBLE_VAL = new Double("1234.5961");
    protected final static String REQUEST_KEY = "REQUEST_KEY";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestWriteTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner
                .main(new String[] { TestWriteTag.class.getName() });
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestWriteTag.class);
    }

    private void formatAndTest(String compare, String output) {
        //fix for introduced carriage return / line feeds
        output = replace(output, "\r", "");
        output = replace(output, "\n", "");
        output = output.trim();
        //System.out.println("Testing [" + compare + "] == [" + output + "]");
        assertEquals(compare, output);
    }

    private void runMyTest(String whichTest, String locale) throws Exception {
        pageContext.setAttribute(Globals.LOCALE_KEY,
                new Locale(locale, locale),
                PageContext.SESSION_SCOPE);
        request.setAttribute("runTest", whichTest);
        pageContext
                .forward("/org/apache/struts/taglib/bean/TestWriteTag.jsp");
    }

    // Name
    public void testWriteTagName() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_STRING_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagName", "");
    }

    public void endWriteTagName(WebResponse response) {
        formatAndTest(TEST_STRING_VAL, response.getText());
    }

    // Property
    public void testWriteTagNameProperty() throws Exception {
        SimpleBeanForTesting sbft = new SimpleBeanForTesting(TEST_STRING_VAL);
        pageContext
                .setAttribute(REQUEST_KEY, sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameProperty", "");
    }

    public void endWriteTagNameProperty(WebResponse response) {
        formatAndTest(TEST_STRING_VAL, response.getText());
    }

    // Name and Format
    public void testWriteTagNameFormat() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_INTEGER_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormat", "");
    }

    public void endWriteTagNameFormat(WebResponse response) {
        formatAndTest("1,234", response.getText());
    }

    // Name, Format, and FormatKey (default bundle)
    public void testWriteTagNameFormatKeyDefaultBundle() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_INTEGER_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyDefaultBundle", "");
    }

    public void endWriteTagNameFormatKeyDefaultBundle(WebResponse response) {
        formatAndTest("$1,234", response.getText());
    }

    // Name, Format, and FormatKey (alternate bundle)
    public void testWriteTagNameFormatKeyAlternateBundle() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_INTEGER_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyAlternateBundle", "");
    }

    public void endWriteTagNameFormatKeyAlternateBundle(
            WebResponse response) {
        formatAndTest("$1,234", response.getText());
    }

    // Name, Format, and FormatKey (default bundle) (Double)
    public void testWriteTagNameFormatKeyDefaultBundleDouble()
            throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_DOUBLE_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyDefaultBundleDouble", "");
    }

    public void endWriteTagNameFormatKeyDefaultBundleDouble(
            WebResponse response) {
        formatAndTest("$1,235", response.getText());
    }

    // Name, Format, and FormatKey (alternate bundle) (Double)
    public void testWriteTagNameFormatKeyAlternateBundleDouble()
            throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_DOUBLE_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyAlternateBundleDouble", "");
    }

    public void endWriteTagNameFormatKeyAlternateBundleDouble(
            WebResponse response) {
        formatAndTest("$1,234.6", response.getText());
    }

    // Name, Format, and FormatKey (default bundle)
    public void testWriteTagNameFormatKeyDefaultBundle_fr() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_DOUBLE_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyDefaultBundle", "fr");
    }

    public void endWriteTagNameFormatKeyDefaultBundle_fr(
            WebResponse response) {
        formatAndTest("$1234,5961.", response.getText());
    }

    // Name, Format, and FormatKey (alternate bundle)
    public void testWriteTagNameFormatKeyAlternateBundle_fr()
            throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                TEST_DOUBLE_VAL,
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFormatKeyAlternateBundle", "fr");
    }

    public void endWriteTagNameFormatKeyAlternateBundle_fr(
            WebResponse response) {
        formatAndTest("$1234,5961.", response.getText());
    }

    // Name, Property, and Format
    public void testWriteTagNamePropertyFormat() throws Exception {
        SimpleBeanForTesting sbft =
                new SimpleBeanForTesting(TEST_INTEGER_VAL);
        pageContext
                .setAttribute(REQUEST_KEY, sbft, PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNamePropertyFormat", "");
    }

    public void endWriteTagNamePropertyFormat(WebResponse response) {
        formatAndTest("1,234", response.getText());
    }

    // Name and ignore
    public void testWriteTagNameIgnore() throws Exception {
        runMyTest("testWriteTagNameIgnore", "");
    }

    public void endWriteTagNameIgnore(WebResponse response) {
        formatAndTest("", response.getText());
    }

    // Name and filter
    public void testWriteTagNameFilter() throws Exception {
        pageContext.setAttribute(REQUEST_KEY,
                "<testing&'\">",
                PageContext.REQUEST_SCOPE);
        runMyTest("testWriteTagNameFilter", "");
    }

    public void endWriteTagNameFilter(WebResponse response) {
        formatAndTest(TagUtils.getInstance().filter("<testing&'\">"),
                response.getText());
    }


}
