/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/PropertyMessageResources.java,v 1.3 2001/02/12 00:32:14 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2001/02/12 00:32:14 $
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


import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;


/**
 * Concrete subclass of <code>MessageResources</code> that reads message keys
 * and corresponding strings from named property resources in the same manner
 * that <code>java.util.PropertyResourceBundle</code> does.  The
 * <code>base</code> property defines the base property resource name, and
 * must be specified.
 * <p>
 * <strong>IMPLEMENTATION NOTE</strong> - This class trades memory for
 * speed by caching all messages located via generalizing the Locale under
 * the original locale as well.
 * This results in specific messages being stored in the message cache
 * more than once, but improves response time on subsequent requests for
 * the same locale + key combination.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2001/02/12 00:32:14 $
 */

public class PropertyMessageResources extends MessageResources {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new PropertyMessageResources according to the
     * specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     */
    public PropertyMessageResources(MessageResourcesFactory factory,
                                    String config) {

        super(factory, config);

    }


    /**
     * Construct a new PropertyMessageResources according to the
     * specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     * @param returnNull The returnNull property we should initialize with
     */
    public PropertyMessageResources(MessageResourcesFactory factory,
                                    String config, boolean returnNull) {

        super(factory, config, returnNull);

    }


    // ------------------------------------------------------------- Properties


    /**
     * The set of locale keys for which we have already loaded messages, keyed
     * by the value calculated in <code>localeKey()</code>.
     */
    protected HashMap locales = new HashMap();


    /**
     * The cache of messages we have accumulated over time, keyed by the
     * value calculated in <code>messageKey()</code>.
     */
    protected HashMap messages = new HashMap();


    // --------------------------------------------------------- Public Methods


    /**
     * Returns a text message for the specified key, for the default Locale.
     * A null string result will be returned by this method if no relevant
     * message resource is found for this key or Locale, if the
     * <code>returnNull</code> property is set.  Otherwise, an appropriate
     * error message will be returned.
     * <p>
     * This method must be implemented by a concrete subclass.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     */
    public String getMessage(Locale locale, String key) {

        // Initialize variables we will require
        String localeKey = localeKey(locale);
        String originalKey = messageKey(localeKey, key);
        String messageKey = null;
        String message = null;
        int underscore = 0;
        boolean addIt = false;  // Add if not found under the original key

        // Loop from specific to general Locales looking for this message
        while (true) {

            // Load this Locale's messages if we have not done so yet
            loadLocale(localeKey);

            // Check if we have this key for the current locale key
            messageKey = messageKey(localeKey, key);
            synchronized (messages) {
                message = (String) messages.get(messageKey);
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
                message = (String) messages.get(messageKey);
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
            message = (String) messages.get(messageKey);
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


    // ------------------------------------------------------ Protected Methods


    /**
     * Load the messages associated with the specified Locale key.  For this
     * implementation, the <code>config</code> property should contain a fully
     * qualified package and resource name, separated by periods, of a series
     * of property resources to be loaded from the class loader that created
     * this PropertyMessageResources instance.  This is exactly the same name
     * format you would use when utilizing the
     * <code>java.util.PropertyResourceBundle</code> class.
     *
     * @param localeKey Locale key for the messages to be retrieved
     */
    protected void loadLocale(String localeKey) {

        // Have we already attempted to load messages for this locale?
        synchronized (locales) {
            if (locales.get(localeKey) != null)
                return;
            locales.put(localeKey, localeKey);
        }

        // Set up to load the property resource for this locale key, if we can
        String name = config.replace('.', '/');
        if (localeKey.length() > 0)
            name += "_" + localeKey;
        name += ".properties";
        InputStream is = null;
        Properties props = new Properties();

        // Load the specified property resource
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(name);
            if (is != null) {
                props.load(is);
                is.close();
            }
        } catch (Throwable t) {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable u) {
                    ;
                }
            }
        }

        // Copy the corresponding values into our cache
        if (props.size() < 1)
            return;
        synchronized (messages) {
            Enumeration names = props.keys();
            while (names.hasMoreElements()) {
                String key = (String) names.nextElement();
                messages.put(messageKey(localeKey, key),
                             props.getProperty(key));
            }
        }

    }


}
