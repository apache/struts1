/*
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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

package org.apache.struts.action;

import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit tests for the <code>org.apache.struts.action.ActionMessages</code> class.
 *
 * @version $Revision: 1.6 $ $Date: 2004/01/13 12:48:53 $
 */

public class TestActionMessages extends TestCase {
	protected ActionMessages aMsgs = null;
	protected ActionMessages anMsgs = null;
	protected ActionMessage msg1 = null;
	protected ActionMessage msg2 = null;
	protected ActionMessage msg3 = null;
	protected ActionMessage msg4 = null;
	protected ActionMessage msg5 = null;

	/**
	 * Defines the testcase name for JUnit.
	 *
	 * @param theName the testcase's name.
	 */
	public TestActionMessages(String theName) {
		super(theName);
	}

	/**
	 * Start the tests.
	 *
	 * @param theArgs the arguments. Not used
	 */
	public static void main(String[] theArgs) {
		junit.awtui.TestRunner.main(new String[] { TestActionMessages.class.getName()});
	}

	/**
	 * @return a test suite (<code>TestSuite</code>) that includes all methods
	 *         starting with "test"
	 */
	public static Test suite() {
		// All methods starting with "test" will be executed in the test suite.
		return new TestSuite(TestActionMessages.class);
	}

	public void setUp() {
		aMsgs = new ActionMessages();
		anMsgs = new ActionMessages();
		Object[] objs1 = new Object[] { "a", "b", "c", "d", "e" };
		Object[] objs2 = new Object[] { "f", "g", "h", "i", "j" };
		msg1 = new ActionMessage("aMessage", objs1);
		msg2 = new ActionMessage("anMessage", objs2);
		msg3 = new ActionMessage("msg3", "value1");
		msg4 = new ActionMessage("msg4", "value2");
		msg5 = new ActionMessage("msg5", "value3", "value4");
	}

	public void tearDown() {
		aMsgs = null;
	}

	public void testEmpty() {
		assertTrue("aMsgs is not empty!", aMsgs.isEmpty());
	}

	public void testNotEmpty() {
		aMsgs.add("myProp", msg1);
		assertTrue("aMsgs is empty!", aMsgs.isEmpty() == false);
	}

	public void testSizeWithOneProperty() {
		aMsgs.add("myProp", msg1);
		aMsgs.add("myProp", msg2);
		assertTrue("number of mesages is not 2", aMsgs.size("myProp") == 2);
	}

	public void testSizeWithManyProperties() {
		aMsgs.add("myProp1", msg1);
		aMsgs.add("myProp2", msg2);
		aMsgs.add("myProp3", msg3);
		aMsgs.add("myProp3", msg4);
		aMsgs.add("myProp4", msg5);
		assertTrue("number of messages for myProp1 is not 1", aMsgs.size("myProp1") == 1);
		assertTrue("number of messages", aMsgs.size() == 5);
	}

	public void testSizeAndEmptyAfterClear() {
		testSizeWithOneProperty();
		aMsgs.clear();
		testEmpty();
		assertTrue("number of meesages is not 0", aMsgs.size("myProp") == 0);
	}

	public void testGetWithNoProperty() {
		Iterator it = aMsgs.get("myProp");
		assertTrue("iterator is not empty!", it.hasNext() == false);
	}

	public void testGetForAProperty() {
		testSizeWithOneProperty();
		Iterator it = aMsgs.get("myProp");
		assertTrue("iterator is empty!", it.hasNext() == true);
	}

	/**
	 * Tests adding an ActionMessages object to an ActionMessages object.
	 */
	public void testAddMessages() {
		ActionMessage msg1 = new ActionMessage("key");
		ActionMessage msg2 = new ActionMessage("key2");
		ActionMessage msg3 = new ActionMessage("key3");
		ActionMessages msgs = new ActionMessages();
		ActionMessages add = new ActionMessages();

		msgs.add("prop1", msg1);
		add.add("prop1", msg2);
		add.add("prop3", msg3);

		msgs.add(add);
		assertTrue(msgs.size() == 3);
		assertTrue(msgs.size("prop1") == 2);

		// test message order
		Iterator props = msgs.get();
		int count = 1;
		while (props.hasNext()) {
			ActionMessage msg = (ActionMessage) props.next();
			if (count == 1) {
				assertTrue(msg.getKey().equals("key"));
			} else if (count == 2) {
				assertTrue(msg.getKey().equals("key2"));
			} else {
				assertTrue(msg.getKey().equals("key3"));
			}

			count++;
		}
	}
}
