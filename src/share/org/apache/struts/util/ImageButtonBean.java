/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/util/ImageButtonBean.java,v 1.2 2002/09/14 21:22:08 martinc Exp $
 * $Revision: 1.2 $
 * $Date: 2002/09/14 21:22:08 $
 *
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


import java.io.Serializable;


/**
 * A simple JavaBean to encapsulate the request parameters sent for an HTML
 * input element of type image. Such an element causes two parameters to be
 * sent, one each for the X and Y coordinates of the button press. An instance
 * of this bean within an <code>ActionForm</code> can be used to capture these
 * and provide a simple means of detecting whether or not the corresponding
 * image was selected.
 *
 * @author  Ted Husted
 * @author  Martin F N Cooper
 * @version $Revision: 1.2 $ $Date: 2002/09/14 21:22:08 $
 */
public class ImageButtonBean implements Serializable {


    // ----------------------------------------------------------- Constructors


    /**
     * Construct an instance with empty property values.
     */
    public ImageButtonBean() {
    }

    /**
     * Construct an instance with the supplied property values.
     *
     * @param x The X coordinate of the button press.
     * @param y The Y coordinate of the button press.
     */
    public ImageButtonBean(String x, String y) {
        this.x = x;
        this.y = y;
    }


    // ------------------------------------------------------------- Properties


    /**
     * The X coordinate of the button press.
     */
    private String x;

    public String getX() {
        return (this.x);
    }

    public void setX(String x) {
        this.x = x;
    }


    /**
     * The Y coordinate of the button press.
     */
    private String y;

    public String getY() {
         return (this.y);
    }

    public void setY(String y) {
        this.y = y;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * A convenience method to determine whether or not the corresponding image
     * element was selected.
     */
    public boolean isSelected() {
        return ((x != null) || (y != null));
    }


    /**
     * Return a string representation of this object.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("ImageButtonBean[");
        sb.append(this.x);
        sb.append(", ");
        sb.append(this.y);
        sb.append("]");
        return (sb.toString());
    }

}
