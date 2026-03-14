/*
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


package org.apache.struts.mock;


import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspWriter;


/**
 * <p>Mock <strong>JspWriter</strong> object for low-level unit tests
 * of Struts controller components.  Captures output written by JSP tags
 * into a StringWriter for assertion verification.</p>
 *
 * <p><strong>WARNING</strong> - Only the minimal set of methods needed to
 * create unit tests is provided, plus additional methods to configure this
 * object as necessary.  Methods for unsupported operations will throw
 * <code>UnsupportedOperationException</code>.</p>
 *
 * <p><strong>WARNING</strong> - Because unit tests operate in a single
 * threaded environment, no synchronization is performed.</p>
 */

public class MockJspWriter extends JspWriter {


    // ----------------------------------------------------------- Constructors


    public MockJspWriter() {
        super(0, true);
        this.stringWriter = new StringWriter();
    }


    // ----------------------------------------------------- Instance Variables


    protected StringWriter stringWriter;


    // --------------------------------------------------------- Public Methods


    /**
     * Return the content written to this writer.
     */
    public String getContent() {
        return stringWriter.toString();
    }


    // -------------------------------------------------------- JspWriter Methods


    public void clear() throws IOException {
    }


    public void clearBuffer() throws IOException {
    }


    public void close() throws IOException {
        stringWriter.close();
    }


    public void flush() throws IOException {
        stringWriter.flush();
    }


    public int getRemaining() {
        return 0;
    }


    public void newLine() throws IOException {
        stringWriter.write(System.getProperty("line.separator"));
    }


    public void print(boolean b) throws IOException {
        stringWriter.write(String.valueOf(b));
    }


    public void print(char c) throws IOException {
        stringWriter.write(String.valueOf(c));
    }


    public void print(int i) throws IOException {
        stringWriter.write(String.valueOf(i));
    }


    public void print(long l) throws IOException {
        stringWriter.write(String.valueOf(l));
    }


    public void print(float f) throws IOException {
        stringWriter.write(String.valueOf(f));
    }


    public void print(double d) throws IOException {
        stringWriter.write(String.valueOf(d));
    }


    public void print(char[] s) throws IOException {
        stringWriter.write(s);
    }


    public void print(String s) throws IOException {
        stringWriter.write(s == null ? "null" : s);
    }


    public void print(Object o) throws IOException {
        stringWriter.write(String.valueOf(o));
    }


    public void println() throws IOException {
        newLine();
    }


    public void println(boolean b) throws IOException {
        print(b);
        newLine();
    }


    public void println(char c) throws IOException {
        print(c);
        newLine();
    }


    public void println(int i) throws IOException {
        print(i);
        newLine();
    }


    public void println(long l) throws IOException {
        print(l);
        newLine();
    }


    public void println(float f) throws IOException {
        print(f);
        newLine();
    }


    public void println(double d) throws IOException {
        print(d);
        newLine();
    }


    public void println(char[] s) throws IOException {
        print(s);
        newLine();
    }


    public void println(String s) throws IOException {
        print(s);
        newLine();
    }


    public void println(Object o) throws IOException {
        print(o);
        newLine();
    }


    public void write(char[] cbuf, int off, int len) throws IOException {
        stringWriter.write(cbuf, off, len);
    }


}
