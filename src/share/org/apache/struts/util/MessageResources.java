/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/MessageResources.java,v 1.9 2001/02/12 00:32:13 craigmcc Exp $
 * $Revision: 1.9 $
 * $Date: 2001/02/12 00:32:13 $
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


import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;


/**
 * General purpose abstract class that describes an API for retrieving
 * Locale-sensitive messages from underlying resource locations of an
 * unspecified design, and optionally utilizing the <code>MessageFormat</code>
 * class to produce internationalized messages with parametric replacement.
 * <p>
 * Calls to <code>getMessage()</code> variants without a <code>Locale</code>
 * argument are presumed to be requesting a message string in the default
 * <code>Locale</code> for this JVM.
 * <p>
 * Calls to <code>getMessage()</code> with an unknown key, or an unknown
 * <code>Locale</code> will return <code>null</code> if the
 * <code>returnNull</code> property is set to <code>true</code>.  Otherwise,
 * a suitable error message will be returned instead.
 * <p>
 * <strong>IMPLEMENTATION NOTE</strong> - Classes that extend this class
 * must be Serializable so that instances may be used in distributable
 * application server environments.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.9 $ $Date: 2001/02/12 00:32:13 $
 */

public abstract class MessageResources implements Serializable {


    // ------------------------------------------------------------- Properties


    /**
     * The configuration parameter used to initialize this MessageResources.
     */
    protected String config = null;

    public String getConfig() {
        return (this.config);
    }


    /**
     * The default Locale for our environment.
     */
    protected Locale defaultLocale = Locale.getDefault();


    /**
     * The <code>MessageResourcesFactory</code> that created this instance.
     */
    protected MessageResourcesFactory factory = null;

    public MessageResourcesFactory getFactory() {
        return (this.factory);
    }


    /**
     * The set of previously created MessageFormat objects, keyed by the
     * key computed in <code>messageKey()</code>.
     */
    protected HashMap formats = new HashMap();


    /**
     * Should we return <code>null</code> instead of an error message string
     * if an unknown Locale or key is requested?
     */
    protected boolean returnNull = false;

    public boolean getReturnNull() {
        return (this.returnNull);
    }

    public void setReturnNull(boolean returnNull) {
        this.returnNull = returnNull;
    }


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new MessageResources according to the specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     */
    public MessageResources(MessageResourcesFactory factory, String config) {

        this(factory, config, false);

    }


    /**
     * Construct a new MessageResources according to the specified parameters.
     *
     * @param factory The MessageResourcesFactory that created us
     * @param config The configuration parameter for this MessageResources
     * @param returnNull The returnNull property we should initialize with
     */
    public MessageResources(MessageResourcesFactory factory, String config,
                         boolean returnNull) {

        super();
        this.factory = factory;
        this.config = config;
        this.returnNull = returnNull;

    }


    // --------------------------------------------------------- Public Methods



    /**
     * Returns a text message for the specified key, for the default Locale.
     *
     * @param key The message key to look up
     */
    public String getMessage(String key) {

	return (getMessage((Locale) null, key));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key The message key to look up
     * @param args An array of replacement parameters for placeholders
     */
    public String getMessage(String key, Object args[]) {

	return (getMessage((Locale) null, key, args));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     */
    public String getMessage(String key, Object arg0) {

	return (getMessage((Locale) null, key, arg0));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1) {

	return (getMessage((Locale) null, key, arg0, arg1));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1,
			     Object arg2) {

	return (getMessage((Locale) null, key, arg0, arg1, arg2));

    }


    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     * @param arg3 The replacement for placeholder {3} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1,
			     Object arg2, Object arg3) {

	return (getMessage((Locale) null, key, arg0, arg1, arg2, arg3));

    }


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
    public abstract String getMessage(Locale locale, String key);


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
        if (locale == null)
            locale = defaultLocale;
	MessageFormat format = null;
	String formatKey = messageKey(locale, key);
	synchronized (formats) {
	    format = (MessageFormat) formats.get(formatKey);
	    if (format == null) {
		String formatString = getMessage(locale, key);
		if (formatString == null) {
		    if (returnNull)
			return (null);
		    else
			return ("???" + formatKey + "???");
		}
		format = new MessageFormat(formatString);
		formats.put(formatKey, format);
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
    public String getMessage(Locale locale, String key, Object arg0) {

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
			     String key, Object arg0, Object arg1) {

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
			     String key, Object arg0, Object arg1,
			     Object arg2) {

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
			     String key, Object arg0, Object arg1,
			     Object arg2, Object arg3) {

	Object args[] = new Object[4];
	args[0] = arg0;
	args[1] = arg1;
	args[2] = arg2;
	args[3] = arg3;
	return (getMessage(locale, key, args));

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Compute and return a key to be used in caching information by a Locale.
     * <strong>NOTE</strong> - The locale key for the default Locale in our
     * environment is a zero length String.
     *
     * @param locale The locale for which a key is desired
     */
    protected String localeKey(Locale locale) {

        if (locale == null)
            return ("");
        //        else if (locale.equals(defaultLocale))
        //            return ("");
        else
            return (locale.toString());

    }


    /**
     * Compute and return a key to be used in caching information
     * by Locale and message key.
     *
     * @param locale The Locale for which this format key is calculated
     * @param key The message key for which this format key is calculated
     */
    protected String messageKey(Locale locale, String key) {

        return (localeKey(locale) + "." + key);

    }


    /**
     * Compute and return a key to be used in caching information
     * by locale key and message key.
     *
     * @param localeKey The locale key for which this cache key is calculated
     * @param key The message key for which this cache key is calculated
     */
    protected String messageKey(String localeKey, String key) {

        return (localeKey + "." + key);

    }


    // --------------------------------------------------------- Static Methods


    /**
     * The default MessageResourcesFactory used to create MessageResources
     * instances.
     */
    protected static MessageResourcesFactory defaultFactory = null;


    /**
     * Create and return an instance of <code>MessageResources</code> for the
     * created by the default <code>MessageResourcesFactory</code>.
     *
     * @param config Configuration parameter for this message bundle.
     */
    public synchronized static MessageResources
        getMessageResources(String config) {

        if (defaultFactory == null)
            defaultFactory = MessageResourcesFactory.createFactory();
        return defaultFactory.createResources(config);

    }


    /**
     * Log a message to the Writer that has been configured for our use.
     *
     * @param message The message to be logged
     */
    public void log(String message) {

        System.out.print("MessageResources: ");
        System.out.println(message);
        System.out.flush();

    }


    /**
     * Log a message and exception to the Writer that has been configured
     * for our use.
     *
     * @param message The message to be logged
     * @param throwable The exception to be logged
     */
    public void log(String message, Throwable throwable) {

        System.out.print("MessageResources: ");
        System.out.println(message);
        throwable.printStackTrace(System.out);

    }


}
