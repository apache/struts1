/*
 * $Header: /home/cvs/jakarta-struts/contrib/tag-doc/src/java/org/apache/struts/taskdefs/Util.java,v 1.3 2004/02/29 22:18:42 martinc Exp $
 * $Revision: 1.3 $
 * $Date: 2004/02/29 22:18:42 $
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

package org.apache.struts.taskdefs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** * General purpose utility class. * * @version $Revision: 1.3 $ $Date: 2004/02/29 22:18:42 $
 */
public class Util {
	/**
	 * Returns the list of named descendents. Allows the user
	 * to specify '/' separated path to the child element. e.g.
	 * 'a/b/c'. Note, that if 'a' has multiple child elements
	 * named 'b', the method will go to the first 'b' element,
	 * and return all child elements named 'c'. Returns an
	 * empty List if the path does not return any elements.
	 *
	 * @param elem The parent/context element
	 * @param path '/' separated path
	 * @return List of elements as per the path.
	 */
	public static List getElements(Element elem, String path) {
		List list = new ArrayList();
		if (elem == null)
			return list;

		int ndx = path.indexOf("/");
		if (ndx != -1) {
			Element child = getElement(elem, path.substring(0, ndx));
			return getElements(child, path.substring(ndx + 1));
		}
		NodeList nl = elem.getChildNodes();
		for (int i = 0, len = nl.getLength(); i < len; i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE
				&& node.getNodeName().equals(path)) {
				list.add(node);
			}
		}
		return list;
	}
	/**
	 * Returns the named descendent. Allows the user
	 * to specify '/' separated path to the child element. e.g.
	 * 'a/b/c'. Note, that in case of multiple children with the
	 * same name, the first child is returned. Returns a null, if
	 * the path does not map to any element.
	 *
	 * @param elem The parent/context element
	 * @param path '/' separated path
	 * @return Named element as per the path.
	 */
	public static Element getElement(Element elem, String path) {
		List elems = getElements(elem, path);
		if (elems.size() == 0)
			return null;
		else
			return (Element) elems.get(0);
	}
	/**
	 * Returns the text value of the named descendent. Allows the user
	 * to specify '/' separated path to the child element. e.g.
	 * 'a/b/c'. Note, that in case of multiple children with the
	 * same name, the first child is considered. The text value
	 * is determined by concatenating all the text children. Returns
	 * an empty String if node is not found.
	 *
	 * @param elem The parent/context element
	 * @param path '/' separated path
	 * @return Text value of the name descendent.
	 */
	public static String getElementValue(Element elem, String path) {
		Element child = getElement(elem, path);
		if (child == null)
			return "";

		String ret = "";
		NodeList nl = child.getChildNodes();
		for (int i = 0, len = nl.getLength(); i < len; i++) {
			Node node = nl.item(i);
			if (node.getNodeType() == Node.TEXT_NODE)
				ret += node.getNodeValue();
		}
		return ret;
	}
	/**
	 * Adds a Child element to the parent node. If the 'value' is
	 * not null, a Text node with the same value is appended to the
	 * Child.
	 * @param parent The parent node
	 * @param tag The child element name
	 * @param value Value of text node appended to the Child
	 * @return the newly added Child node.
	 */
	public static Element addChildElement(
		Node parent,
		String tag,
		String value) {
		Document doc =
			(parent.getNodeType() == Node.DOCUMENT_NODE)
				? (Document) parent
				: parent.getOwnerDocument();
		Element child = doc.createElement(tag);
		if (value != null)
			child.appendChild(doc.createTextNode(value));
		parent.appendChild(child);
		return child;
	}

	/**
	 * Copies a given resource from classpath to the destination file.
	 * Uses Util.class.getResourceAsStream(resPath) internally.
	 * @param resPath Path to the resource
	 * @param dstFile Destination file
	 * @return true, if the file is copied successfully.
	 */
	public static boolean copyFile(String resPath, File dstFile) {
		try {
			BufferedInputStream bis =
				new BufferedInputStream(
					Util.class.getResourceAsStream(resPath));
			FileOutputStream fos = new FileOutputStream(dstFile);
			int offset = 0, len = 0;
			byte[] buffer = new byte[2 * 1024];
			while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, len);
				offset += len;
			}
			bis.close();
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}