/*
 * Copyright 2025-2026 the original author or authors.
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

package org.apache.struts.tiles;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.struts.tiles.xmlDefinition.I18nFactorySet;

/**
 * Tests for CVE-2023-49735: path traversal via crafted Locale values
 * in I18nFactorySet.calculateSuffixes().
 *
 * <p>The vulnerability exists because calculateSuffixes() uses
 * locale.getLanguage(), locale.getCountry(), and locale.getVariant()
 * directly to build file path suffixes without validating that
 * they contain only safe characters. A crafted Locale with path
 * traversal sequences (e.g. "../../etc") in any field will produce
 * suffixes like "_../../etc" that are then used to construct file
 * paths for loading XML definition files.</p>
 *
 * <p>These tests currently fail because no validation is performed.
 * They should pass once language, country, and variant values are
 * validated against a safe character pattern (e.g. [a-zA-Z0-9]{0,8}).</p>
 */
public class TestI18nFactorySetPathTraversal extends TestCase {

    // ------------------------------------------------------------- Basics

    public TestI18nFactorySetPathTraversal(String name) {
        super(name);
    }

    public static void main(String args[]) {
        junit.awtui.TestRunner.main(
            new String[] { TestI18nFactorySetPathTraversal.class.getName() });
    }

    public static Test suite() {
        return (new TestSuite(TestI18nFactorySetPathTraversal.class));
    }

    // ------------------------------------------------------------- Helpers

    /**
     * Invoke the private calculateSuffixes(Locale) method via reflection.
     */
    private List invokeCalculateSuffixes(Locale locale) throws Exception {
        Method method = I18nFactorySet.class.getDeclaredMethod(
            "calculateSuffixes", new Class[] { Locale.class });
        method.setAccessible(true);
        return (List) method.invoke(new I18nFactorySet(), new Object[] { locale });
    }

    /**
     * Assert that none of the suffixes in the list contain path traversal
     * sequences ("../" or "..\").
     */
    private void assertNoPathTraversal(String message, List suffixes) {
        Iterator it = suffixes.iterator();
        while (it.hasNext()) {
            String suffix = (String) it.next();
            if (suffix.indexOf("../") >= 0 || suffix.indexOf("..\\") >= 0) {
                fail(message + ": suffix '" + suffix
                    + "' contains path traversal sequence");
            }
        }
    }

    /**
     * Assert that all locale component values within suffixes contain
     * only safe characters (letters, digits, hyphens, and underscores).
     */
    private void assertSafeCharactersOnly(String message, List suffixes) {
        Iterator it = suffixes.iterator();
        while (it.hasNext()) {
            String suffix = (String) it.next();
            // Suffix format is like "_lang", "_lang_country", "_lang_country_variant"
            // Each segment after '_' should contain only [a-zA-Z0-9_-]
            // Remove leading underscore and check each segment
            String withoutLeadingUnderscore = suffix.substring(1);
            char[] chars = withoutLeadingUnderscore.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c != '_' && c != '-' && !Character.isLetterOrDigit(c)) {
                    fail(message + ": suffix '" + suffix
                        + "' contains unsafe character '" + c + "'");
                }
            }
        }
    }

    // ------------------------------------------------- Individual Tests

    /**
     * Path traversal in the language field should be rejected.
     * Currently fails because calculateSuffixes does not validate
     * locale components.
     */
    public void testCalculateSuffixes_Language_PathTraversal_IsRejected() throws Exception {
        Locale malicious = new Locale("../../etc", "", "");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertNoPathTraversal("language with path traversal", suffixes);
    }

    /**
     * Path traversal in the country field should be rejected.
     * Currently fails because calculateSuffixes does not validate
     * locale components.
     */
    public void testCalculateSuffixes_Country_PathTraversal_IsRejected() throws Exception {
        Locale malicious = new Locale("en", "../../etc", "");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertNoPathTraversal("country with path traversal", suffixes);
    }

    /**
     * Path traversal in the variant field should be rejected.
     * Currently fails because calculateSuffixes does not validate
     * locale components.
     */
    public void testCalculateSuffixes_Variant_PathTraversal_IsRejected() throws Exception {
        Locale malicious = new Locale("en", "US", "../../etc");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertNoPathTraversal("variant with path traversal", suffixes);
    }

    /**
     * Backslash-based path traversal in the language field should be rejected.
     */
    public void testCalculateSuffixes_Language_BackslashTraversal_IsRejected() throws Exception {
        Locale malicious = new Locale("..\\..\\etc", "", "");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertSafeCharactersOnly("language with backslash traversal", suffixes);
    }

    /**
     * Locale language containing slash characters should be rejected.
     */
    public void testCalculateSuffixes_Language_SlashInValue_IsRejected() throws Exception {
        Locale malicious = new Locale("en/../../WEB-INF", "", "");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertSafeCharactersOnly("language with slash", suffixes);
    }

    /**
     * Locale country containing slash characters should be rejected.
     */
    public void testCalculateSuffixes_Country_SlashInValue_IsRejected() throws Exception {
        Locale malicious = new Locale("en", "US/../../WEB-INF", "");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertSafeCharactersOnly("country with slash", suffixes);
    }

    /**
     * Locale variant containing slash characters should be rejected.
     */
    public void testCalculateSuffixes_Variant_SlashInValue_IsRejected() throws Exception {
        Locale malicious = new Locale("en", "US", "XX/../../WEB-INF");
        List suffixes = invokeCalculateSuffixes(malicious);
        assertSafeCharactersOnly("variant with slash", suffixes);
    }

    /**
     * Valid locale values should still produce correct suffixes.
     * This is a regression guard to ensure the fix does not break
     * normal locale handling.
     */
    public void testCalculateSuffixes_ValidLocale_ProducesSuffixes() throws Exception {
        Locale locale = new Locale("en", "US", "WIN");
        List suffixes = invokeCalculateSuffixes(locale);
        assertEquals("three suffixes expected", 3, suffixes.size());
        assertEquals("_en", suffixes.get(0));
        assertEquals("_en_US", suffixes.get(1));
        assertEquals("_en_US_WIN", suffixes.get(2));
    }

    /**
     * Empty locale should produce no suffixes (existing behavior).
     */
    public void testCalculateSuffixes_EmptyLocale_ProducesNoSuffixes() throws Exception {
        Locale locale = new Locale("", "", "");
        List suffixes = invokeCalculateSuffixes(locale);
        assertEquals("no suffixes for empty locale", 0, suffixes.size());
    }

    /**
     * Variant with hyphens (BCP 47 style) should be accepted.
     * For example, "sr_Latn_RS" or vendor-specific variants like
     * "polyton" are legitimate locale identifiers.
     */
    public void testCalculateSuffixes_VariantWithHyphen_IsAccepted() throws Exception {
        Locale locale = new Locale("ca", "ES", "valencia-x-lv");
        List suffixes = invokeCalculateSuffixes(locale);
        assertEquals("three suffixes expected", 3, suffixes.size());
        assertEquals("_ca", suffixes.get(0));
        assertEquals("_ca_ES", suffixes.get(1));
        assertEquals("_ca_ES_valencia-x-lv", suffixes.get(2));
    }
}
