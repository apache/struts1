/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/Attic/ArrayStack.java,v 1.1 2000/12/28 01:55:14 craigmcc Exp $
 * $Revision: 1.1 $
 * $Date: 2000/12/28 01:55:14 $
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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;


/**
 * Implementation of the <code>java.util.Stack</code> API that is based on
 * an <code>ArrayList</code> rather than a <code>Vector</code>.  This means
 * no synchronization locks are utilized internally, so you must synchronize
 * externally if an instance is referenced from multiple threads.
 *
 * @author Craig R. McClanahan
 * @version $Revision: 1.1 $ $Date: 2000/12/28 01:55:14 $
 */

public class ArrayStack implements Serializable {


    // ----------------------------------------------------- Instance Variables


    /**
     * The underlying collection class.
     */
    protected ArrayList list = new ArrayList();


    // --------------------------------------------------------- Public Methods


    /**
     * Remove all elements from this stack.  After this call, the stack will
     * be empty.
     */
    public void clear() {

        list.clear();

    }


    /**
     * Return <code>true</code> if this stack is currently empty.
     */
    public boolean empty() {

        return (list.size() == 0);

    }


    /**
     * Return the top item off of this stack without removing it.
     *
     * @exception EmptyStackExceptino if the stack is empty
     */
    public Object peek() throws EmptyStackException {

        return (peek(0));

    }


    /**
     * Return the n'th item down (zero-relative) from the top of this
     * stack without removing it.
     *
     * @param n Number of items down to go
     *
     * @exception EmptyStackException if there are not enough items on the
     *  stack to satisfy this request
     */
    public Object peek(int n) throws EmptyStackException {

        int m = (list.size() - n) - 1;
        if (m < 0)
            throw new EmptyStackException();
        else
            return (list.get(m));

    }


    /**
     * Pop the top item off of this stack and return it.
     *
     * @exception EmptyStackException if the stack is empty
     */
    public Object pop() throws EmptyStackException {

        if (list.size() == 0)
            throw new EmptyStackException();
        return (list.remove(list.size() - 1));

    }


    /**
     * Push a new item onto the top of this stack.  The pushed item is also
     * returned.
     *
     * @param item Item to be added
     */
    public Object push(Object item) {

        list.add(item);
        return (item);

    }


    /**
     * Return the number of items on this stack.
     */
    public int size() {

        return (list.size());

    }


}
