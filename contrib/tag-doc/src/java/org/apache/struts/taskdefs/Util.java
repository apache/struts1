/*
 * $Id$ 
 *
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

/**
 * General purpose utility class.
 *
 * @version $Rev$ $Date$
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