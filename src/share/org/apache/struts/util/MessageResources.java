/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/MessageResources.java,v 1.3 2000/06/29 22:24:48 craigmcc Exp $
 * $Revision: 1.3 $
 * $Date: 2000/06/29 22:24:48 $
 *
 * ====================================================================
 * 
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
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
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
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


import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * General purpose utility module that wraps the named resource bundle
 * passed to our constructor and produces messages from it, with parametric
 * replacement of MessageFormat parameters.  Convenience methods allow
 * retrieval of messages for either the default Locale or a specified Locale.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.3 $ $Date: 2000/06/29 22:24:48 $
 */

public final class MessageResources {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new resources object that wraps the named ResourceBundles.
     *
     * @param base Base name of the ResourceBundles to be wrapped
     *
     * @exception MissingResourceException if the specified bundle cannot
     *  be loaded
     */
    public MessageResources(String base) throws MissingResourceException {

	super();
	this.base = base;
	this.bundle = ResourceBundle.getBundle(base);

    }


    // ----------------------------------------------------- Instance Variables


    /**
     * The base name of resource bundles used for resolving requests for
     * messages.
     */
    private String base = null;


    /**
     * The resource bundle to be used for resolving requests for messages
     * when no Locale is specified (i.e. the bundle that will be used for
     * the default Locale).
     */
    private ResourceBundle bundle = null;


    /**
     * The set of resource bundles from which we access resources, keyed by
     * the key computed in <code>getLocaleKey()</code>.
     */
    private Hashtable bundles = new Hashtable();


    /**
     * The default Locale for our installation.
     */
    private static final Locale defaultLocale = Locale.getDefault();


    /**
     * The set of previously created message format objects, keyed by
     * the key computed in <code>getResourceKey()</code>.
     */
    private Hashtable formats = new Hashtable();


    /**
     * Should we return <code>null</code> for message keys that are not
     * found (instead of an error code that includes the offending key)?
     */
    private boolean returnNull = true;


    // ------------------------------------------------------------- Properties


    /**
     * Return the "return null" property.
     */
    public boolean getReturnNull() {

	return (this.returnNull);

    }


    /**
     * Set the "return null" property.
     *
     * @param returnNull The new "return null" property
     */
    public void setReturnNull(boolean returnNull) {

	this.returnNull = returnNull;

    }


    // --------------------------------------------------------- Public Methods



    /**
     * Return the resource bundle that will be used to resolve message
     * requests for the default Locale.
     *
     * @exception MissingResourceException if a suitable bundle cannot
     *  be located
     */
    public ResourceBundle getBundle() throws MissingResourceException {

	return (getBundle(null));

    }


    /**
     * Return the resource bundle that will be used to resolve message
     * requests for the specified Locale, if there is one.  Otherwise,
     * return <code>null</code>
     *
     * @param locale The locale used to select a resource bundle
     *
     * @exception MissingResourceException if a suitable bundle cannot
     *  be located
     */
    public ResourceBundle getBundle(Locale locale)
	throws MissingResourceException {

	if (locale == null)
	    return (bundle);
	String localeKey = getLocaleKey(locale);

	ResourceBundle bundle = null;
	synchronized (bundles) {
	    bundle = (ResourceBundle) bundles.get(localeKey);
	    if (bundle == null) {
		bundle = ResourceBundle.getBundle(base, locale);
		bundles.put(localeKey, bundle);
	    }
	}
	return (bundle);

    }


    /**
     * Returns a text message for the specified key, for the default Locale.
     * A null string result will be returned by this method if no relevant
     * message resource is found.
     *
     * @param key The message key to look up
     */
    public String getMessage(String key) {

	return (getMessage((Locale) null, key));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will be returned by
     * this method if no resource bundle has been configured.
     *
     * @param key The message key to look up
     * @param args An array of replacement parameters for placeholders
     */
    public String getMessage(String key, Object args[]) {

	return (getMessage((Locale) null, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     */
    public String getMessage(String key, String arg0) {

	return (getMessage((Locale) null, key, arg0));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     */
    public String getMessage(String key, String arg0, String arg1) {

	return (getMessage((Locale) null, key, arg0, arg1));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     */
    public String getMessage(String key, String arg0, String arg1,
			     String arg2) {

	return (getMessage((Locale) null, key, arg0, arg1, arg2));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     * @param arg3 The replacement for placeholder {3} in the message
     */
    public String getMessage(String key, String arg0, String arg1,
			     String arg2, String arg3) {

	return (getMessage((Locale) null, key, arg0, arg1, arg2, arg3));

    }


    /**
     * Returns a text message for the specified key, for the default Locale.
     * A null string result will be returned by this method if no relevant
     * message resource is found.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     */
    public String getMessage(Locale locale, String key) {

	Object args[] = new Object[0];
	return (getMessage(locale, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will be returned by
     * this method if no resource bundle has been configured.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     * @param args An array of replacement parameters for placeholders
     */
    public String getMessage(Locale locale, String key, Object args[]) {

	// Cache MessageFormat instances as they are accessed
	MessageFormat format = null;
	String messageKey = getResourceKey(locale, key);
	synchronized (formats) {
	    format = (MessageFormat) formats.get(messageKey);
	    if (format == null) {
		String formatString = (String) getResource(locale, key);
		if (formatString == null) {
		    if (returnNull)
			return (null);
		    else
			return ("???" + key + "???");
		}
		format = new MessageFormat(formatString);
		formats.put(messageKey, format);
	    }

	}
	return (format.format(args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     */
    public String getMessage(Locale locale, String key, String arg0) {

	Object args[] = new Object[1];
	args[0] = arg0;
	return (getMessage(locale, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     */
    public String getMessage(Locale locale,
			     String key, String arg0, String arg1) {

	Object args[] = new Object[2];
	args[0] = arg0;
	args[1] = arg1;
	return (getMessage(locale, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     */
    public String getMessage(Locale locale,
			     String key, String arg0, String arg1,
			     String arg2) {

	Object args[] = new Object[3];
	args[0] = arg0;
	args[1] = arg1;
	args[2] = arg2;
	return (getMessage(locale, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned
     * by this method.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     * @param arg3 The replacement for placeholder {3} in the message
     */
    public String getMessage(Locale locale,
			     String key, String arg0, String arg1,
			     String arg2, String arg3) {

	Object args[] = new Object[4];
	args[0] = arg0;
	args[1] = arg1;
	args[2] = arg2;
	args[3] = arg3;
	return (getMessage(locale, key, args));

    }


    /**
     * Returns a resource for the specified message key, or <code>null</code>
     * if no corresponding resource can be found, for the default Locale.
     *
     * @param key The resource key to look up
     */
    public Object getResource(String key) {

	return (getResource((Locale) null, key));

    }


    /**
     * Returns a resource for the specified message key, or <code>null</code>
     * if no corresponding resource can be found, for the specified Locale.
     *
     * @param locale The requested message Locale, or <code>null</code>
     *  for the system default Locale
     * @param key The resource key to look up
     */
    public Object getResource(Locale locale, String key) {

	// Look up the resource bundle we will use
	ResourceBundle bundle = null;
	try {
	    bundle = getBundle(locale);
	} catch (Exception e) {
	    return (null);
	}

	// Look up the resource itself in this resource bundle
	Object result = null;
	try {
	    result = bundle.getString(key);
	} catch (MissingResourceException e) {
	    return (null);
	}
	return (result);

    }


    // -------------------------------------------------------- Private Methods


    /**
     * Returns the key under which resources for a particular Locale may be
     * uniquely accessed.
     *
     * @param locale The locale for which a key is to be computed, or
     *  <code>null</code> for the system default Locale
     */
    private String getLocaleKey(Locale locale) {

	if (locale == null)
	    locale = defaultLocale;
	StringBuffer sb = new StringBuffer();
	sb.append(locale.getCountry());
	sb.append(".");
	sb.append(locale.getLanguage());
	sb.append(".");
	String variant = locale.getVariant();
	if (variant != null) {
	    sb.append(variant);
	    sb.append(".");
	}
	return (sb.toString());

    }


    /**
     * Returns the key under which a message format for a particular message,
     * from the resource bundle for a particular locale, may be uniquely
     * accessed.
     *
     * @param locale Locale for which this message key is computed
     * @param id Message identifier for which this message key is computed
     */
    private String getResourceKey(Locale locale, String id) {

	return (getLocaleKey(locale) + "." + id);

    }


    // --------------------------------------------------------- Static Methods


    /**
     * The MessageResources instances that have been created so far,
     * keyed by base name.
     */
    private static Hashtable cache = new Hashtable();


    /**
     * Return an instance of MessageResources for the specified base name.
     *
     * @param base Base name of the ResourceBundles to be wrapped
     *
     * @exception MissingResourcesException if no default resources
     *  can be loaded
     */
    public synchronized static MessageResources
	getMessageResources(String base) throws MissingResourceException {

	MessageResources messageResources =
	    (MessageResources) cache.get(base);
	if (messageResources != null)
	    return (messageResources);
	messageResources = new MessageResources(base);
	cache.put(base, messageResources);
	return (messageResources);

    }


}
