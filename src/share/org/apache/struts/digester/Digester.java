/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/digester/Attic/Digester.java,v 1.7 2000/08/01 20:03:29 craigmcc Exp $
 * $Revision: 1.7 $
 * $Date: 2000/08/01 20:03:29 $
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


package org.apache.struts.digester;


import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.EmptyStackException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * A <strong>Digester</strong> processes an XML input stream by matching a
 * series of element nesting patterns to execute Rules that have been added
 * prior to the start of parsing.  This package was inspired by the
 * <code>XmlMapper</code> class that was part of Tomcat 3.0 and 3.1,
 * but is organized somewhat differently.
 * <p>
 * A typical use of a Digester might look something like this:
 * <pre>
 *	InputStream input = ...;	// Open XML input stream
 *	Digester digester = new Digester();
 *	digester.push(this);
 *	digester.setValidating(false);
 *	digester.addObjectCreate("action-mappings/action", mappingClass,
 *                               "className");
 *	digester.addSetProperties("action-mappings/action");
 *	digester.addSetNext("action-mappings/action", "addMapping",
 *			    "org.apache.struts.action.ActionMapping");
 *	try {
 *	    digester.parse(input);
 *	    input.close();
 *	} catch (SAXException e) {
 *	    System.out.println("SAXException: " + e);
 *	    e.printStackTrace(System.out);
 *	}
 * </pre>
 * <p>
 * The initial <code>push()</code> call pushes the instance containing this
 * code (the ActionServlet in the example above) onto the digester's stack.
 * Each of the "add" methods adds a processing rule that is matched when
 * an <code>&lt;action&gt;</code> element is found nested within an
 * <code>&lt;action-mappings&gt;</code> element.  These rules perform the
 * following processing:
 * <ul>
 * <li>A new Java object will be instantiated and pushed onto the digester's
 *     object stack.  If the <code>&lt;action&gt;</code>  element includes
 *     an attribute named <code>className</code>, the value of that attribute
 *     must be the fully qualified Java class name of the object to be created.
 *     Otherwise, the class name is taken from the <code>mappingClass</code>
 *     instance variable.</li>
 * <li>The attributes of the <code>&lt;action&gt;</code> element are matched
 *     against the properties of the object on top of the stack.  Whenever
 *     there is a <code>setXxx()</code> method with a corresponding name, that
 *     property will be set to the corresponding attribute value.</li>
 * <li>A call to the <code>addMapping</code> method of the next-to-top object
 *     on the stack (i.e. the action servlet) is made, passing the top object
 *     on the stack as an argument.  The method expects an argument of type
 *    "org.apache.struts.action.ActionMapping" (or a subclass or interface
 *    implementation of this type) to be passed.
 * <li>Any object added to the digeter's stack is removed.
 * </ul>
 * <p>
 * This is a very powerful technique for constructing trees of Java objects
 * that are configured based on the element attributes, without having to
 * hard code the configuration logic.
 *
 * @author Craig McClanahan
 * @version $Revision: 1.7 $ $Date: 2000/08/01 20:03:29 $
 */

public final class Digester extends HandlerBase {


    // --------------------------------------------------------- Constructors


    /**
     * Construct a new Digester with default properties.
     */
    public Digester() {

	super();

    }


    // --------------------------------------------------- Instance Variables


    /**
     * The body text of the current element.
     */
    private StringBuffer bodyText = new StringBuffer();


    /**
     * The stack of body text string buffers for surrounding elements.
     */
    private Stack bodyTexts = new Stack();


    /**
     * The debugging detail level of this component.
     */
    private int debug = 0;


    /**
     * The URLs of DTDs that have been registered, keyed by the public
     * identifier that corresponds.
     */
    private Hashtable dtds = new Hashtable();


    /**
     * The Locator associated with our parser.
     */
    private Locator locator = null;


    /**
     * The current match pattern for nested element processing.
     */
    private String match = "";


    /**
     * The SAXParser we will use to parse the input stream.
     */
    private SAXParser parser = null;


    /**
     * The "root" element of the stack (in other words, the last object
     * that was popped.
     */
    private Object root = null;


    /**
     * The set of Rules that have been registered with this Digester.  The
     * key is the matching pattern against the current element stack, and
     * the value is a Vector containing the Rules for that pattern, in the
     * order that they were registered.
     */
    private Hashtable rules = new Hashtable();


    /**
     * The object stack being constructed.
     */
    private Stack stack = new Stack();


    /**
     * Do we want to use a validating parser?
     */
    private boolean validating = false;


    // ----------------------------------------------------------- Properties


    /**
     * Return the current depth of the element stack.
     */
    public int getCount() {

	return (stack.size());

    }


    /**
     * Return the debugging detail level of this Context.
     */
    public int getDebug() {

	return (this.debug);

    }


    /**
     * Set the debugging detail level of this Context.
     *
     * @param debug The new debugging detail level
     */
    public void setDebug(int debug) {

	this.debug = debug;

    }


    /**
     * Return the SAXParser we will use to parse the input stream.  If there
     * is a problem creating the parser, return <code>null</code>.
     */
    public SAXParser getParser() {

	// Return the parser we already created (if any)
	if (parser != null)
	    return (parser);

	// Create and return a new parser
	try {
	    SAXParserFactory factory = SAXParserFactory.newInstance();
	    factory.setNamespaceAware(false);
	    factory.setValidating(validating);
	    parser = factory.newSAXParser();
	    return (parser);
	} catch (Exception e) {
	    log("Digester.getParser: ", e);
	    return (null);
	}

    }


    /**
     * Return the validating parser flag.
     */
    public boolean getValidating() {

	return (this.validating);

    }


    /**
     * Set the validating parser flag.  This must be called before
     * <code>parse()</code> is called the first time.
     *
     * @param validating The new validating parser flag.
     */
    public void setValidating(boolean validating) {

	this.validating = validating;

    }


    // ---------------------------------------------- DocumentHandler Methods


    /**
     * Process notification of character data received from the body of
     * an XML element.
     *
     * @param buffer The characters from the XML document
     * @param start Starting offset into the buffer
     * @param length Number of characters from the buffer
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void characters(char buffer[], int start, int length)
      throws SAXException {

	//	if (debug >= 3)
	//	    log("characters(" + new String(buffer, start, length) + ")");

	bodyText.append(buffer, start, length);

    }


    /**
     * Process notification of the end of the document being reached.
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void endDocument() throws SAXException {

	//	if (debug >= 3)
	//	    log("endDocument()");

	if (getCount() > 1)
	    log("endDocument():  " + getCount() + " elements left");
	while (getCount() > 1)
	    pop();

	// Fire "finish" events for all defined rules
	Enumeration keys = this.rules.keys();
	while (keys.hasMoreElements()) {
	    String key = (String) keys.nextElement();
	    Vector rules = (Vector) this.rules.get(key);
	    for (int i = 0; i < rules.size(); i++) {
		try {
		    ((Rule) rules.elementAt(i)).finish();
		} catch (Exception e) {
		    log("Finish event threw exception", e);
		    throw new SAXException(e);
		}
	    }
	}

	// Perform final cleanup
	clear();

    }


    /**
     * Process notification of the end of an XML element being reached.
     *
     * @param name Name of the element that is ending
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void endElement(String name) throws SAXException {

	//	if (debug >= 3)
	//	    log("endElement(" + match + ")");
	Vector rules = (Vector) this.rules.get(match);

	// Fire "body" events for all relevant rules
	if (rules != null) {
	    //	    if (debug >= 3)
	    //		log("  Firing 'body' events for " + rules.size() + " rules");
	    String bodyText = this.bodyText.toString();
	    for (int i = 0; i < rules.size(); i++) {
		try {
		    ((Rule) rules.elementAt(i)).body(bodyText);
		} catch (Exception e) {
		    log("Body event threw exception", e);
		    throw new SAXException(e);
		}
	    }
	}

	// Recover the body text from the surrounding element
	bodyText = (StringBuffer) bodyTexts.pop();

	// Fire "end" events for all relevant rules in reverse order
	if (rules != null) {
	    //	    if (debug >= 3)
	    //		log("  Firing 'end' events for " + rules.size() + " rules");
	    for (int i = 0; i < rules.size(); i++) {
		int j = (rules.size() - i) - 1;
		try {
		    ((Rule) rules.elementAt(j)).end();
		} catch (Exception e) {
		    log("End event threw exception", e);
		    throw new SAXException(e);
		}
	    }
	}

	// Recover the previous match expression
	int slash = match.lastIndexOf('/');
	if (slash >= 0)
	    match = match.substring(0, slash);
	else
	    match = "";

    }


    /**
     * Process notification of ignorable whitespace received from the body of
     * an XML element.
     *
     * @param buffer The characters from the XML document
     * @param start Starting offset into the buffer
     * @param length Number of characters from the buffer
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void ignorableWhitespace(char buffer[], int start, int len)
      throws SAXException {

	//	if (debug >= 3)
	//	    log("ignorableWhitespace(" +
	//		new String(buffer, start, len) + ")");

	;	// No processing required

    }


    /**
     * Process notification of a processing instruction that was encountered.
     *
     * @param target The processing instruction target
     * @param data The processing instruction data (if any)
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void processingInstruction(String target, String data)
      throws SAXException {

	//	if (debug >= 3)
	//	    log("processingInstruction('" + target + "', '" + data + "')");

	;	// No processing is required

    }


    /**
     * Set the document locator associated with our parser.
     *
     * @param locator The new locator
     */
    public void setDocumentLocator(Locator locator) {

	//	if (debug >= 3)
	//	    log("setDocumentLocator()");

	this.locator = locator;

    }


    /**
     * Process notification of the beginning of the document being reached.
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void startDocument() throws SAXException {

	//	if (debug >= 3)
	//	    log("startDocument()");

    }


    /**
     * Process notification of the start of an XML element being reached.
     *
     * @param name Name of the element that is starting
     * @param list The attributes associated with this element
     *
     * @exception SAXException if a parsing error is to be reported
     */
    public void startElement(String name, AttributeList list)
      throws SAXException {

	// Save the body text accumulated for our surrounding element
	bodyTexts.push(bodyText);
	bodyText.setLength(0);

	// Compute the current matching rule
	if (match.length() > 0)
	    match += "/" + name;
	else
	    match = name;
	//	if (debug >= 3)
	//	    log("startElement(" + match + ")");


	// Fire "begin" events for all relevant rules
	Vector rules = (Vector) this.rules.get(match);
	if (rules != null) {
	    //	    if (debug >= 3)
	    //		log("  Firing 'begin' events for " + rules.size() + " rules");
	    String bodyText = this.bodyText.toString();
	    for (int i = 0; i < rules.size(); i++) {
		try {
		    ((Rule) rules.elementAt(i)).begin(list);
		} catch (Exception e) {
		    log("Begin event threw exception", e);
		    throw new SAXException(e);
		}
	    }
	}

    }


    // --------------------------------------------------- DTDHandler Methods


    /**
     * Receive notification of a notation declaration event.
     *
     * @param name The notation name
     * @param publicId The public identifier (if any)
     * @param systemId The system identifier (if any)
     */
    public void notationDecl(String name, String publicId, String systemId) {

	if (debug >= 1)
	    log("notationDecl('" + name + "', '" + publicId + "', '" +
		systemId + "')");

    }


    /**
     * Receive notification of an unparsed entity declaration event.
     *
     * @param name The unparsed entity name
     * @param publicId The public identifier (if any)
     * @param systemId The system identifier (if any)
     * @param notation The name of the associated notation
     */
    public void unparsedEntityDecl(String name, String publicId,
				   String systemId, String notation) {

	if (debug >= 1)
	    log("unparsedEntityDecl('" + name + "', '" + publicId + "', '" +
		systemId + "', '" + notation + "')");

    }


    // ----------------------------------------------- EntityResolver Methods


    /**
     * Resolve the requested external entity.
     *
     * @param publicId The public identifier of the entity being referenced
     * @param systemId The system identifier of the entity being referenced
     *
     * @exception SAXException if a parsing exception occurs
     */
    public InputSource resolveEntity(String publicId, String systemId)
	throws SAXException {

	if (debug >= 1)
	    log("resolveEntity('" + publicId + "', '" + systemId + "')");
	String dtdURL = (String) dtds.get(publicId);

	// Has this system identifier been registered?
	if (dtdURL == null) {
	    if (debug >= 1)
		log(" Not registered, use system identifier");
	    return (null);
	}

	// Return an input source to our alternative URL
	if (debug >= 1)
	    log(" Resolving to alternate DTD '" + dtdURL + "'");
	return (new InputSource(dtdURL));

    }


    // ------------------------------------------------- ErrorHandler Methods


    /**
     * Receive notification of a recoverable parsing error.
     *
     * @param exception The warning information
     *
     * @exception SAXException if a parsing exception occurs
     */
    public void error(SAXParseException exception) throws SAXException {

	log("Parse Error at line " + exception.getLineNumber() +
	    " column " + exception.getColumnNumber() + ": " +
	    exception.getMessage(), exception);

    }


    /**
     * Receive notification of a fatal parsing error.
     *
     * @param exception The warning information
     *
     * @exception SAXException if a parsing exception occurs
     */
    public void fatalError(SAXParseException exception) throws SAXException {

	log("Parse Fatal Error at line " + exception.getLineNumber() +
	    " column " + exception.getColumnNumber() + ": " +
	    exception.getMessage(), exception);

    }


    /**
     * Receive notification of a parsing warning.
     *
     * @param exception The warning information
     *
     * @exception SAXException if a parsing exception occurs
     */
    public void warning(SAXParseException exception) throws SAXException {

	log("Parse Warning at line " + exception.getLineNumber() +
	    " column " + exception.getColumnNumber() + ": " +
	    exception.getMessage(), exception);

    }


    // ------------------------------------------------------ Logging Methods


    /**
     * Log a message to the log writer associated with this context.
     *
     * @param message The message to be logged
     */
    public void log(String message) {

	System.out.println(message);

    }


    /**
     * Log a message and associated exception to the log writer
     * associated with this context.
     *
     * @param message The message to be logged
     * @param exception The associated exception to be logged
     */
    public void log(String message, Throwable exception) {

	System.out.println(message);
	exception.printStackTrace(System.out);

    }


    // ------------------------------------------------------- Public Methods


    /**
     * Parse the content of the specified file using this Digester.  Returns
     * the root element from the object stack (if any).
     *
     * @param file File containing the XML data to be parsed
     *
     * @exception IOException if an input/output error occurs
     * @exception SAXException if a parsing exception occurs
     */
    public Object parse(File file) throws IOException, SAXException {

	getParser().parse(file, this);
	return (root);

    }


    /**
     * Parse the content of the specified input source using this Digester.
     * Returns the root element from the object stack (if any).
     *
     * @param input Input source containing the XML data to be parsed
     *
     * @exception IOException if an input/output error occurs
     * @exception SAXException if a parsing exception occurs
     */
    public Object parse(InputSource input) throws IOException, SAXException {

	getParser().parse(input, this);
	return (root);

    }


    /**
     * Parse the content of the specified input stream using this Digester.
     * Returns the root element from the object stack (if any).
     *
     * @param input Input stream containing the XML data to be parsed
     *
     * @exception IOException if an input/output error occurs
     * @exception SAXException if a parsing exception occurs
     */
    public Object parse(InputStream input) throws IOException, SAXException {

	getParser().parse(input, this);
	return (root);

    }


    /**
     * Parse the content of the specified URI using this Digester.
     * Returns the root element from the object stack (if any).
     *
     * @param uri URI containing the XML data to be parsed
     *
     * @exception IOException if an input/output error occurs
     * @exception SAXException if a parsing exception occurs
     */
    public Object parse(String uri) throws IOException, SAXException {

	getParser().parse(uri, this);
	return (root);

    }


    /**
     * Register the specified DTD URL for the specified public identifier.
     * This must be called before the first call to <code>parse()</code>.
     *
     * @param publicId Public identifier of the DTD to be resolved
     * @param dtdURL The URL to use for reading this DTD
     */
    public void register(String publicId, String dtdURL) {

	dtds.put(publicId, dtdURL);

    }


    // --------------------------------------------------------- Rule Methods


    /**
     * Register a new Rule matching the specified pattern.
     *
     * @param pattern Element matching pattern
     * @param rule Rule to be registered
     */
    public void addRule(String pattern, Rule rule) {

	Vector list = (Vector) rules.get(pattern);
	if (list == null) {
	    list = new Vector();
	    rules.put(pattern, list);
	}
	list.addElement(rule);

    }



    /**
     * Add an "call method" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to be called
     * @param paramCount Number of expected parameters (or zero
     *  for a single parameter from the body of this element)
     */
    public void addCallMethod(String pattern, String methodName,
    			      int paramCount) {

	addRule(pattern,
	        new CallMethodRule(this, methodName, paramCount));

    }


    /**
     * Add an "call method" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to be called
     * @param paramCount Number of expected parameters (or zero
     *  for a single parameter from the body of this element)
     * @param paramTypes Set of Java class names for the types
     *  of the expected parameters
     */
    public void addCallMethod(String pattern, String methodName,
    			      int paramCount, String paramTypes[]) {

	addRule(pattern,
	        new CallMethodRule(this, methodName,
	        		   paramCount, paramTypes));

    }


    /**
     * Add a "call parameter" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param paramIndex Zero-relative parameter index to set
     *  (from the body of this element)
     */
    public void addCallParam(String pattern, int paramIndex) {

	addRule(pattern,
	        new CallParamRule(this, paramIndex));

    }


    /**
     * Add a "call parameter" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param paramIndex Zero-relative parameter index to set
     *  (from the specified attribute)
     * @param attributeName Attribute whose value is used as the
     *  parameter value
     */
    public void addCallParam(String pattern, int paramIndex,
    			      String attributeName) {

	addRule(pattern,
	        new CallParamRule(this, paramIndex, attributeName));

    }


    /**
     * Add an "object create" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param className Java class name to be created
     */
    public void addObjectCreate(String pattern, String className) {

	addRule(pattern,
	        new ObjectCreateRule(this, className));

    }


    /**
     * Add an "object create" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param className Default Java class name to be created
     * @param attributeName Attribute name that optionally overrides
     *  the default Java class name to be created
     */
    public void addObjectCreate(String pattern, String className,
    				String attributeName) {

	addRule(pattern,
	        new ObjectCreateRule(this, className, attributeName));

    }


    /**
     * Add a "set next" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to call on the parent element
     */
    public void addSetNext(String pattern, String methodName) {

	addRule(pattern,
	        new SetNextRule(this, methodName));

    }


    /**
     * Add a "set next" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to call on the parent element
     * @param paramType Java class name of the expected parameter type
     */
    public void addSetNext(String pattern, String methodName,
    			   String paramType) {

	addRule(pattern,
	        new SetNextRule(this, methodName, paramType));

    }


    /**
     * Add a "set properties" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     */
    public void addSetProperties(String pattern) {

	addRule(pattern,
	        new SetPropertiesRule(this));

    }


    /**
     * Add a "set property" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param name Attribute name containing the property name to be set
     * @param value Attribute name containing the property value to set
     */
    public void addSetProperty(String pattern, String name, String value) {

	addRule(pattern,
		new SetPropertyRule(this, name, value));

    }


    /**
     * Add a "set top" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to call on the parent element
     */
    public void addSetTop(String pattern, String methodName) {

	addRule(pattern,
	        new SetTopRule(this, methodName));

    }


    /**
     * Add a "set top" rule for the specified parameters.
     *
     * @param pattern Element matching pattern
     * @param methodName Method name to call on the parent element
     * @param paramType Java class name of the expected parameter type
     */
    public void addSetTop(String pattern, String methodName,
    			  String paramType) {

	addRule(pattern,
	        new SetTopRule(this, methodName, paramType));

    }


    // -------------------------------------------------------- Stack Methods


    /**
     * Clear the current contents of the object stack.
     */
    public void clear() {

	match = "";
	while (!bodyTexts.empty())
	    bodyTexts.pop();
	while (!stack.empty())
	    stack.pop();
	root = null;

    }


    /**
     * Return the top object on the stack without removing it.  If there are
     * no objects on the stack, return <code>null</code>.
     */
    public Object peek() {

	try {
	    return (stack.peek());
	} catch (EmptyStackException e) {
	    return (null);
	}

    }


    /**
     * Return the n'th object down the stack, where 0 is the top element
     * and [getCount()-1] is the bottom element.  If the specified index
     * is out of range, return <code>null</code>.
     *
     * @param n Index of the desired element, where 0 is the top of the stack,
     *  1 is the next element down, and so on.
     */
    public Object peek(int n) {

	int index = (stack.size() - n) - 1;
	if ((index < 0) || (index >= stack.size()))
	    return (null);
	else
	    return (((Vector) stack).elementAt(index));

    }


    /**
     * Pop the top object off of the stack, and return it.  If there are
     * no objects on the stack, return <code>null</code>.
     */
    public Object pop() {

	try {
	    if (stack.size() == 1)
		root = stack.peek();
	    return (stack.pop());
	} catch (EmptyStackException e) {
	    return (null);
	}

    }


    /**
     * Push a new object onto the top of the object stack.
     *
     * @param object The new object
     */
    public void push(Object object) {

	stack.push(object);

    }


}
