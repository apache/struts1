/*
 * Copyright 2026 The Struts1-Forever Project.
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
package org.apache.struts.actions;

import java.util.Locale;

import junit.framework.TestCase;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.mock.MockHttpServletRequest;
import org.apache.struts.mock.MockHttpServletResponse;
import org.apache.struts.mock.MockHttpSession;

/**
 * Unit tests for {@link LocaleAction}.
 *
 * <p>LocaleAction reads {@code language}, {@code country}, and {@code page}
 * properties from an ActionForm (any bean with those properties), sets the
 * resulting {@link Locale} as {@code Globals.LOCALE_KEY} in the session, and
 * either forwards to the {@code page} property or to the "success" forward.</p>
 */
public class TestLocaleAction extends TestCase {

    private LocaleAction action;
    private ActionMapping mapping;
    private MockHttpSession session;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    /** Builds a DynaActionForm with language / country / page properties. */
    private DynaActionForm buildForm(String language, String country, String page)
            throws Exception {
        FormBeanConfig config = new FormBeanConfig();
        config.setName("localeForm");
        config.setType("org.apache.struts.action.DynaActionForm");

        org.apache.struts.config.FormPropertyConfig langProp =
                new org.apache.struts.config.FormPropertyConfig();
        langProp.setName("language");
        langProp.setType("java.lang.String");
        config.addFormPropertyConfig(langProp);

        org.apache.struts.config.FormPropertyConfig countryProp =
                new org.apache.struts.config.FormPropertyConfig();
        countryProp.setName("country");
        countryProp.setType("java.lang.String");
        config.addFormPropertyConfig(countryProp);

        org.apache.struts.config.FormPropertyConfig pageProp =
                new org.apache.struts.config.FormPropertyConfig();
        pageProp.setName("page");
        pageProp.setType("java.lang.String");
        config.addFormPropertyConfig(pageProp);

        DynaActionFormClass clazz = DynaActionFormClass.createDynaActionFormClass(config);
        DynaActionForm form = (DynaActionForm) clazz.newInstance();
        form.set("language", language);
        form.set("country", country);
        form.set("page", page);
        return form;
    }

    protected void setUp() throws Exception {
        action = new LocaleAction();

        // "success" forward used when page property is null
        mapping = new ActionMapping();
        mapping.addForwardConfig(
                new org.apache.struts.action.ActionForward("success", "/home.jsp", false));

        session = new MockHttpSession();
        request = new MockHttpServletRequest(session);
        response = new MockHttpServletResponse();
    }

    // ------------------------------------------------------------------
    // Locale is set in session
    // ------------------------------------------------------------------

    public void testLocale_LanguageOnly_SetsLocaleInSession() throws Exception {
        DynaActionForm form = buildForm("fr", "", null);

        action.execute(mapping, form, request, response);

        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        assertNotNull("Locale must be stored in session", locale);
        assertEquals("fr", locale.getLanguage());
        assertEquals("", locale.getCountry());
    }

    public void testLocale_LanguageAndCountry_SetsLocaleInSession() throws Exception {
        DynaActionForm form = buildForm("ja", "JP", null);

        action.execute(mapping, form, request, response);

        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        assertNotNull(locale);
        assertEquals("ja", locale.getLanguage());
        assertEquals("JP", locale.getCountry());
    }

    public void testLocale_EmptyLanguage_DoesNotChangeLocale() throws Exception {
        // Pre-seed session with a known locale
        session.setAttribute(Globals.LOCALE_KEY, Locale.GERMAN);

        DynaActionForm form = buildForm("", "", null);

        action.execute(mapping, form, request, response);

        // language is empty, so the existing locale should be kept unchanged
        Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
        assertNotNull(locale);
        assertEquals("de", locale.getLanguage());
    }

    // ------------------------------------------------------------------
    // Forward destination
    // ------------------------------------------------------------------

    public void testLocale_NoPageProperty_ForwardsToSuccess() throws Exception {
        DynaActionForm form = buildForm("en", "US", null);

        ActionForward forward = action.execute(mapping, form, request, response);

        assertNotNull(forward);
        assertEquals("/home.jsp", forward.getPath());
    }

    public void testLocale_WithPageProperty_ForwardsToPage() throws Exception {
        DynaActionForm form = buildForm("de", "DE", "/custom-target.jsp");

        ActionForward forward = action.execute(mapping, form, request, response);

        assertNotNull(forward);
        assertEquals("/custom-target.jsp", forward.getPath());
    }
}
