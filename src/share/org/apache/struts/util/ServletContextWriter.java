/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/ServletContextWriter.java,v 1.2 2001/02/12 00:32:14 craigmcc Exp $
 * $Revision: 1.2 $
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


import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletContext;


/**
 * A PrintWriter implementation that uses the logging facilities of a
 * <code>javax.servlet.ServletContext</code> to output its results.  Output
 * will be buffered until a newline character is output, <code>flush()</code>
 * is called, or until one of the <code>println()</code> methods is called.
 * Along the way, carriage return characters are skipped.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.2 $ $Date: 2001/02/12 00:32:14 $
 */

public class ServletContextWriter extends PrintWriter {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a ServletContextWriter associated with the specified
     * ServletContext instance.
     *
     * @param context The associated servlet context
     */
    public ServletContextWriter(ServletContext context) {

        super(new StringWriter());
        this.context = context;

    }


    // ------------------------------------------------------------- Properties


    /**
     * The buffer into which we accumulate lines to be logged.
     */
    protected StringBuffer buffer = new StringBuffer();


    /**
     * The servlet context with which we are associated.
     */
    protected ServletContext context = null;


    /**
     * The error state for this stream.
     */
    protected boolean error = false;


    // --------------------------------------------------------- Public Methods


    /**
     * Flush the stream and check for its error state.  <strong>IMPLEMENTATION
     * NOTE</strong> - our associated servlet context gives no indication of
     * problems with logging, so the only way this method will return
     * <code>true</code> is if <code>setError()</code> is called.
     */
    public boolean checkError() {

        flush();
        return (error);

    }


    /**
     * Close the stream.
     */
    public void close() {

        flush();

    }


    /**
     * Flush the stream.
     */
    public void flush() {

        if (buffer.length() > 0) {
            context.log(buffer.toString());
            buffer.setLength(0);
        }

    }


    /**
     * Print a boolean value.
     *
     * @param b The value to be printed
     */
    public void print(boolean b) {

        write(String.valueOf(b));

    }


    /**
     * Print a character value.
     *
     * @param c The value to be printed
     */
    public void print(char c) {

        write(c);

    }


    /**
     * Print a character array.
     *
     * @param c The character array to be printed
     */
    public void print(char c[]) {

        for (int i = 0; i < c.length; i++)
            write(c[i]);

    }


    /**
     * Print a double value.
     *
     * @param d The value to be printed
     */
    public void print(double d) {

        write(String.valueOf(d));

    }


    /**
     * Print a float value.
     *
     * @param f The value to be printed
     */
    public void print(float f) {

        write(String.valueOf(f));

    }


    /**
     * Print an integer value.
     *
     * @param i The value to be printed
     */
    public void print(int i) {

        write(String.valueOf(i));

    }


    /**
     * Print a long value.
     *
     * @param l The value to be printed
     */
    public void print(long l) {

        write(String.valueOf(l));

    }


    /**
     * Print an object.
     *
     * @param o The value to be printed
     */
    public void print(Object o) {

        write(o.toString());

    }


    /**
     * Print a String value.
     *
     * @param s The value to be printed
     */
    public void print(String s) {

        int len = s.length();
        for (int i = 0; i < len; i++)
            write(s.charAt(i));

    }


    /**
     * Terminate the current line and flush the buffer.
     */
    public void println() {

        flush();

    }


    /**
     * Print a boolean value and terminate the line.
     *
     * @param b The value to be printed
     */
    public void println(boolean b) {

        println(String.valueOf(b));

    }


    /**
     * Print a character value and terminate the line.
     *
     * @param c The value to be printed
     */
    public void println(char c) {

        write(c);
        println();

    }


    /**
     * Print a character array and terminate the line.
     *
     * @param c The character array to be printed
     */
    public void println(char c[]) {

        for (int i = 0; i < c.length; i++)
            print(c[i]);
        println();

    }


    /**
     * Print a double value and terminate the line.
     *
     * @param d The value to be printed
     */
    public void println(double d) {

        println(String.valueOf(d));

    }


    /**
     * Print a float value and terminate the line.
     *
     * @param f The value to be printed
     */
    public void println(float f) {

        println(String.valueOf(f));

    }


    /**
     * Print an integer value and terminate the line.
     *
     * @param i The value to be printed
     */
    public void println(int i) {

        println(String.valueOf(i));

    }


    /**
     * Print a long value and terminate the line.
     *
     * @param l The value to be printed
     */
    public void println(long l) {

        println(String.valueOf(l));

    }


    /**
     * Print an object and terminate the line.
     *
     * @param o The value to be printed
     */
    public void println(Object o) {

        println(o.toString());

    }


    /**
     * Print a String value and terminate the line.
     *
     * @param s The value to be printed
     */
    public void println(String s) {

        int len = s.length();
        for (int i = 0; i < len; i++)
            print(s.charAt(i));
        println();

    }


    /**
     * Set the error state for this stream.
     */
    public void setError() {

        this.error = true;

    }


    /**
     * Write a single character to this stream.
     *
     * @param c The character to be written
     */
    public void write(char c) {

        if (c == '\n')
            flush();
        else if (c != '\r')
            buffer.append(c);

    }


    /**
     * Write a single character to this stream.
     *
     * @param c The character to be written
     */
    public void write(int c) {

        write((char) c);

    }


    /**
     * Write an array of charaters to this stream.
     *
     * @param buf The character array to be written
     */
    public void write(char buf[]) {

        for (int i = 0; i < buf.length; i++)
            write(buf[i]);

    }


    /**
     * Write the specified subset of an array of characters to this stream.
     *
     * @param buf The character array from which to write
     * @param off The zero-relative starting offset to write
     * @param len The number of characters to write
     */
    public void write(char buf[], int off, int len) {

        for (int i = off; i < len; i++)
            write(buf[i]);

    }


    /**
     * Write a String to this stream.
     *
     * @param s The string to be written
     */
    public void write(String s) {

        int len = s.length();
        for (int i = 0; i < len; i++)
            write(s.charAt(i));

    }


    /**
     * Write the specified portion of a String to this stream.
     *
     * @param s The String from which to write
     * @param off The zero-relative starting offset to write
     * @param len The number of characters to write
     */
    public void write(String s, int off, int len) {

        for (int i = off; i < len; i++)
            write(s.charAt(i));

    }


}
