/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/utils/HashMapMessageResources.java,v 1.3 2002/11/16 05:12:06 jmitchell Exp $
 * $Revision: 1.3 $
 * $Date: 2002/11/16 05:12:06 $
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
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

package org.apache.strutsel.taglib.utils;

import java.util.HashMap;
import java.util.Locale;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;


/**
 * This is a simple derived class of <code>MessageResources</code> which is
 * just used for testing custom tags which use the
 * <code>MessageResources</code> class.
 */
public class HashMapMessageResources
    extends MessageResources {

    private HashMap messages = new HashMap();

    public HashMapMessageResources(MessageResourcesFactory factory, 
                                   String config) {
        super(factory, config);
    }

    public HashMapMessageResources(MessageResourcesFactory factory, 
                                   String config, boolean returnNull) {
        super(factory, config, returnNull);
    }

    public void addMessage(String key, String value) {
        addMessage("", key, value);
    }

    public void addMessage(String locale, String key, String value) {
        messages.put(locale + "." + key, value);
    }

    /**
    * Returns a text message for the specified key, for the specified locale.
    * If no result is found for the given locale, the locale is "trimmed" off
    * the end of more specific locale modifiers to check for a match.  If no
    * match is found with the trimmed locale, the current "default" locale is
    * checked, if it is different from the given locale.  If still no match is
    * found, an empty locale specifier is used.
    *
    * This method is copied directly from the
    * <code>PropertyMessageResources</code> class.
    */
    public String getMessage(Locale locale, String key) {

        // Initialize variables we will require
        String localeKey = localeKey(locale);
        String originalKey = messageKey(localeKey, key);
        String messageKey = null;
        String message = null;
        int underscore = 0;
        boolean addIt = false; // Add if not found under the original key

        // Loop from specific to general Locales looking for this message
        while (true) {

            // Load this Locale's messages if we have not done so yet
            loadLocale(localeKey);

            // Check if we have this key for the current locale key
            messageKey = messageKey(localeKey, key);

            synchronized (messages) {
                message = (String)messages.get(messageKey);
                if (message != null) {
                    if (addIt)
                        messages.put(originalKey, message);
                    return (message);
                }
            }

            // Strip trailing modifiers to try a more general locale key
            addIt = true;
            underscore = localeKey.lastIndexOf("_");

            if (underscore < 0)

                break;

            localeKey = localeKey.substring(0, underscore);
        }

        // Try the default locale if the current locale is different
        if (!defaultLocale.equals(locale)) {
            localeKey = localeKey(defaultLocale);
            messageKey = messageKey(localeKey, key);
            loadLocale(localeKey);

            synchronized (messages) {
                message = (String)messages.get(messageKey);
                if (message != null) {
                    if (addIt)
                        messages.put(originalKey, message);
                    return (message);
                }
            }
        }

        // As a last resort, try the default Locale
        localeKey = "";
        messageKey = messageKey(localeKey, key);
        loadLocale(localeKey);

        synchronized (messages) {
            message = (String)messages.get(messageKey);

            if (message != null) {
                if (addIt)
                    messages.put(originalKey, message);
                return (message);
            }
        }

        // Return an appropriate error indication
        if (returnNull)
            return (null);
        else
            return ("???" + messageKey(locale, key) + "???");
    }

    protected void loadLocale(String localeKey) {
    }
}
