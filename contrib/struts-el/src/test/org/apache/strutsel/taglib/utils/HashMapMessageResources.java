package org.apache.strutsel.taglib.utils;

import java.util.*;

import org.apache.struts.util.*;

import org.apache.strutsel.taglib.utils.*;


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
