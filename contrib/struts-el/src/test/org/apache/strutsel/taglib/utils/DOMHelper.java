/*
 * $Header: /home/cvs/jakarta-struts/contrib/struts-el/src/test/org/apache/strutsel/taglib/utils/DOMHelper.java,v 1.5 2003/07/26 05:48:03 dmkarr Exp $
 * $Revision: 1.5 $
 * $Date: 2003/07/26 05:48:03 $
 * ====================================================================
 *
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

package org.apache.strutsel.taglib.utils;

import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.CachedXPathAPI;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class DOMHelper {

    protected static final String spaces = "                                    ";

    private static Log log = LogFactory.getLog(DOMHelper.class);

    public static String getNodeText(org.w3c.dom.Document document, 
                                     String xpath)
                              throws TransformerException {

        String result = null;

        if (!xpath.endsWith("text()")) {

            if (!xpath.endsWith("/"))
                xpath += "/";

            xpath += "text()";
        }

        CachedXPathAPI xpathAPI = new CachedXPathAPI();
        Node foundNode = xpathAPI.selectSingleNode(document, xpath);

        if (foundNode == null)
            result = "";
        else if (foundNode.getNodeType() == Node.TEXT_NODE)
            result = ((Text)foundNode).getData();

        return (result);
    }

    public static void recordFoundAttributes(org.w3c.dom.Document document, 
                                             String xpath, Map map)
                                      throws TransformerException {

        CachedXPathAPI xpathAPI = new CachedXPathAPI();
        Node foundNode = xpathAPI.selectSingleNode(document.getDocumentElement(), 
                                                   xpath);

        if (foundNode != null) {

            NamedNodeMap attrMap = foundNode.getAttributes();

            for (int ctr = 0; ctr < attrMap.getLength(); ++ctr) {

                Attr attrNode = (Attr)attrMap.item(ctr);
                map.put(attrNode.getName(), attrNode.getValue());
            }
        }
    }

    public static boolean verifyAttributesPresent(Map attrMap, 
                                                  String[] attrNames, 
                                                  boolean allowOthers)
                                           throws Exception {

        boolean result = true;

        if (attrNames != null) {

            // First see if all of the expected attributes were actually found.
            for (int ctr = 0; ctr < attrNames.length; ++ctr) {

                if (attrMap.get(attrNames[ctr]) == null) {
                    result = false;
                    throw new Exception("Expected attribute \"" + 
                                        attrNames[ctr] + 
                                        "\" was not found in the generated tag.");
                }
            }

            // Now, if no "extra" attributes are allowed, verify that all the
            // attributes that were found were expected.
            if (!allowOthers) {

                for (Iterator iter = attrMap.keySet().iterator();
                     iter.hasNext();) {

                    String key = (String)iter.next();
                    boolean found = false;

                    for (int ctr = 0; ctr < attrNames.length; ++ctr) {

                        if (key.equals(attrNames[ctr])) {
                            found = true;

                            break;
                        }
                    }

                    if (!found) {
                        throw new Exception("Attribute \"" + key + 
                                            "\" was not " + 
                                            "an expected attribute in the " + 
                                            "generated tag.");
                    }
                }
            }
        }

        return (result);
    }

    public static boolean verifyAttributesNotPresent(Map attrMap, 
                                                     String[] attrNames)
                                              throws Exception {

        boolean result = true;

        if (attrNames != null) {

            for (int ctr = 0; ctr < attrNames.length; ++ctr) {

                if (attrMap.get(attrNames[ctr]) != null) {
                    result = false;

                    break;
                }
            }
        }

        return (result);
    }

    public static void printNode(Node node) {
        if (log.isDebugEnabled()) {
            System.out.println("Node tree:");
            printNode(node, 0);
        }
    }

    public static void printNode(Node node, int level) {

        if (node == null)
            return;

        String nodeName = node.getNodeName();
        NodeList children = node.getChildNodes();

        if (children != null) {

            short nodeType = node.getNodeType();

            if (nodeType == Node.TEXT_NODE) {

                String text = ((Text)node).getData();
                System.out.print(text);
            } else {
                System.out.print(spaces.substring(0, level) + "<" + nodeName);

                NamedNodeMap  nodeMap  = node.getAttributes();
                if (nodeMap.getLength() > 0) {
                    StringBuffer sb = new StringBuffer();

                    for (int ctr = 0; ctr < nodeMap.getLength(); ++ ctr) {
                        Attr  attrnode = (Attr) nodeMap.item(ctr);
                        String   name  = attrnode.getName();
                        String   value = attrnode.getValue();

                        sb.append(" " + name + "=\"" + value + "\"");
                    }

                    System.out.print(sb.toString());
                }
                
                System.out.println(">");
            }
            
            for (int ctr = 0; ctr < children.getLength(); ++ctr) {

                Node child = children.item(ctr);
                printNode(child, level + 1);
            }

            if (nodeType != Node.TEXT_NODE)
                System.out.println(
                        spaces.substring(0, level) + "</" + nodeName + ">");
        } else
            System.out.println(
                    spaces.substring(0, level) + "<" + nodeName + "/>");
    }
}
