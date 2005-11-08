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
