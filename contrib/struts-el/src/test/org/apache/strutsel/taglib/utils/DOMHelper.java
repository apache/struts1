package org.apache.strutsel.taglib.utils;

import java.util.*;

import javax.xml.transform.*;

import org.apache.xpath.*;

import org.w3c.dom.*;

import org.xml.sax.*;

import org.apache.commons.logging.*;


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
            } else
                System.out.println(
                        spaces.substring(0, level) + "<" + nodeName + ">");

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
