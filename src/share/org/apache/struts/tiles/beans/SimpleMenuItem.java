/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/beans/SimpleMenuItem.java,v 1.4 2004/01/13 12:48:51 husted Exp $
 * $Revision: 1.4 $
 * $Date: 2004/01/13 12:48:51 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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

package org.apache.struts.tiles.beans;

import java.io.Serializable;

/**
 * A MenuItem implementation.
 * Used to read menu items in definitions.
 */
public class SimpleMenuItem implements MenuItem, Serializable {

    private String value = null;

    private String link = null;

    private String icon = null;

    private String tooltip = null;

    /**
     * Constructor.
     */
    public SimpleMenuItem() {
        super();
    }

    /**
     * Set value property.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get value property.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set link property.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get link property.
     */
    public String getLink() {
        return link;
    }

    /**
     * Set icon property.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Get icon property.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set tooltip property.
     */
    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * Get tooltip property.
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Return String representation.
     */
    public String toString() {
        StringBuffer buff = new StringBuffer("SimpleMenuItem[");

        if (getValue() != null) {
            buff.append("value=").append(getValue()).append(", ");
        }

        if (getLink() != null) {
            buff.append("link=").append(getLink()).append(", ");
        }

        if (getTooltip() != null) {
            buff.append("tooltip=").append(getTooltip()).append(", ");
        }

        if (getIcon() != null) {
            buff.append("icon=").append(getIcon()).append(", ");
        }

        buff.append("]");
        return buff.toString();
    }

}
