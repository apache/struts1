package org.apache.scaffold.http;

import java.net.*;
import java.io.*;

/**
 * A network client that reads from a HTTP URL.
 * <P>
 * Taken from Core Servlets and JavaServer Pages
 * from Prentice Hall and Sun Microsystems Press,
 * http://www.coreservlets.com/.
 * &copy; 2000 Marty Hall; may be freely used or adapted.
 * <P>
 * Adapted for general use by a servlet to read a page
 * into a StringBuffer by Ted Husted, August 2001.
 * <P>
 * @author Marty Hall
 * @author Ted Husted
 * @version $Revision: 1.1 $ $Date: 2001/12/23 19:32:50 $
 */
public class HttpClient {

    /**
     * Retrieve indicated page, returning headers and
     * page content.
     * <P>
     * @params host
     * @params port
     * @params requestLine
     * @params requestHeaders
     * @params content
     * @exception Catches IOException and UnknownHostException,
     * and returns messages in content.
     * @author Marty Hall
     * @author Ted Husted
     * @version $Revision: 1.1 $ $Date: 2001/12/23 19:32:50 $
     */
    public HttpClient(
            String host, int port,
            String requestLine, String[] requestHeaders,
            StringBuffer responseHeaders, StringBuffer responseContent)
                throws UnknownHostException,IOException {

        // -- check host and connect - Will succeed or throw UHE
        InetAddress.getByName(host);
        Socket uriSocket = new Socket(host,port);

        // -- handleConnection - May throw IOE
        PrintWriter out = SocketUtil.getWriter(uriSocket);
        BufferedReader in = SocketUtil.getReader(uriSocket);

        // -- say howdy
        out.println(requestLine);

        // -- send any headers, and then blank
        for(int i=0; i<requestHeaders.length; i++) {
            if (requestHeaders[i] == null)
                break;
            else
                out.println(requestHeaders[i]);
        }
        out.println();

        // -- capture response
        String line;
        // - headers
        while ((line = in.readLine()) != null) {
            responseHeaders.append(line + "\n");
            if ("".equals(line)) break;
        }
        // - content
        while ((line = in.readLine()) != null)
            responseContent.append(line + "\n");
   }
}
