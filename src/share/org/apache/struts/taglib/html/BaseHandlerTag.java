/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/html/BaseHandlerTag.java,v 1.1 2001/01/06 21:50:39 mschachter Exp $
 * $Revision: 1.1 $
 * $Date: 2001/01/06 21:50:39 $
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

package org.apache.struts.taglib.html;

import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.MessageResources;

/**
 * Base class for tags that render form elements capable of including JavaScript
 * event handlers and/or CSS Style attributes. This class does not implement
 * the doStartTag() or doEndTag() methods. Subclasses should provide
 * appropriate implementations of these.
 *
 * @author Don Clasen
 * @version $Revision: 1.1 $ $Date: 2001/01/06 21:50:39 $
 */

public abstract class BaseHandlerTag extends BodyTagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
     MessageResources.getMessageResources(Constants.Package + ".LocalStrings");


//  Navigation Management

    /** Access key character. */
    protected String accesskey= null;

    /** Tab index value. */
    protected String tabindex = null;

//  Mouse Events

    /** Mouse click event. */
    private String onclick = null;

    /** Mouse double click event. */
    private String ondblclick = null;

    /** Mouse over component event. */
    private String onmouseover = null;

    /** Mouse exit component event. */
    private String onmouseout = null;

    /** Mouse moved over component event. */
    private String onmousemove = null;

    /** Mouse pressed on component event. */
    private String onmousedown = null;

    /** Mouse released on component event. */
    private String onmouseup = null;

//  Keyboard Events

    /** Key down in component event. */
    private String onkeydown = null;

    /** Key released in component event. */
    private String onkeyup = null;

    /** Key down and up together in component event. */
    private String onkeypress = null;

// Text Events

    /** Text selected in component event. */
    private String onselect = null;

    /** Content changed after component lost focus event. */
    private String onchange = null;

// Focus Events

    /** Component lost focus event. */
    private String onblur = null;

    /** Component has received focus event. */
    private String onfocus = null;

// CSS Style Support

    /** Style attribute associated with component. */
    private String style = null;

    /** Named Style class associated with component. */
    private String styleClass = null;

    // ------------------------------------------------------------- Properties

//  Navigation Management

    /** Sets the accessKey character. */
    public void setAccesskey(String accessKey) {
	this.accesskey = accessKey;
    }

    /** Returns the accessKey character. */
    public String getAccesskey() {
	return (this.accesskey);
    }


    /** Sets the tabIndex value. */
    public void setTabindex(String tabIndex) {
	this.tabindex = tabIndex;
    }

    /** Returns the tabIndex value. */
    public String getTabindex() {
	return (this.tabindex);
    }

// Mouse Events

    /** Sets the onClick event handler. */
    public void setOnclick(String onClick) {
        this.onclick = onClick;
    }

    /** Returns the onClick event handler. */
    public String getOnclick() {
        return onclick;
    }

    /** Sets the onDblClick event handler. */
    public void setOndblclick(String onDblClick) {
        this.ondblclick = onDblClick;
    }

    /** Returns the onDblClick event handler. */
    public String getOndblclick() {
        return ondblclick;
    }

    /** Sets the onMouseDown event handler. */
    public void setOnmousedown(String onMouseDown) {
        this.onmousedown = onMouseDown;
    }

    /** Returns the onMouseDown event handler. */
    public String getOnmousedown() {
        return onmousedown;
    }

    /** Sets the onMouseUp event handler. */
    public void setOnmouseup(String onMouseUp) {
        this.onmouseup = onMouseUp;
    }

    /** Returns the onMouseUp event handler. */
    public String getOnmouseup() {
        return onmouseup;
    }

    /** Sets the onMouseMove event handler. */
    public void setOnmousemove(String onMouseMove) {
        this.onmousemove = onMouseMove;
    }

    /** Returns the onMouseMove event handler. */
    public String getOnmousemove() {
        return onmousemove;
    }

    /** Sets the onMouseOver event handler. */
    public void setOnmouseover(String onMouseOver) {
        this.onmouseover = onMouseOver;
    }

    /** Returns the onMouseOver event handler. */
    public String getOnmouseover() {
        return onmouseover;
    }

    /** Sets the onMouseOut event handler. */
    public void setOnmouseout(String onMouseOut) {
        this.onmouseout = onMouseOut;
    }

    /** Returns the onMouseOut event handler. */
    public String getOnmouseout() {
        return onmouseout;
    }

// Keyboard Events

    /** Sets the onKeyDown event handler. */
    public void setOnkeydown(String onKeyDown) {
        this.onkeydown = onKeyDown;
    }

    /** Returns the onKeyDown event handler. */
    public String getOnkeydown() {
        return onkeydown;
    }

    /** Sets the onKeyUp event handler. */
    public void setOnkeyup(String onKeyUp) {
        this.onkeyup = onKeyUp;
    }

    /** Returns the onKeyUp event handler. */
    public String getOnkeyup() {
        return onkeyup;
    }

    /** Sets the onKeyPress event handler. */
    public void setOnkeypress(String onKeyPress) {
        this.onkeypress = onKeyPress;
    }

    /** Returns the onKeyPress event handler. */
    public String getOnkeypress() {
        return onkeypress;
    }

// Text Events

    /** Sets the onChange event handler. */
    public void setOnchange(String onChange) {
        this.onchange = onChange;
    }

    /** Returns the onChange event handler. */
    public String getOnchange() {
        return onchange;
    }

    /** Sets the onSelect event handler. */
    public void setOnselect(String onSelect) {
        this.onselect = onSelect;
    }

    /** Returns the onSelect event handler. */
    public String getOnselect() {
        return onselect;
    }

// Focus Events

    /** Sets the onBlur event handler. */
    public void setOnblur(String onBlur) {
        this.onblur = onBlur;
    }

    /** Returns the onBlur event handler. */
    public String getOnblur() {
        return onblur;
    }

    /** Sets the onFocus event handler. */
    public void setOnfocus(String onFocus) {
        this.onfocus = onFocus;
    }

    /** Returns the onFocus event handler. */
    public String getOnfocus() {
        return onfocus;
    }

// CSS Style Support

    /** Sets the style attribute. */
    public void setStyle(String style) {
        this.style = style;
    }

    /** Returns the style attribute. */
    public String getStyle() {
        return style;
    }

    /** Sets the style class attribute. */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /** Returns the style class attribute. */
    public String getStyleClass() {
        return styleClass;
    }

    // --------------------------------------------------------- Public Methods


    /**
     * Release any acquired resources.
     */
    public void release() {

	super.release();
	accesskey = null;
	tabindex = null;
	onclick = null;
	ondblclick = null;
	onmouseover = null;
	onmouseout = null;
	onmousemove = null;
	onmousedown = null;
	onmouseup = null;
	onkeydown = null;
	onkeyup = null;
	onkeypress = null;
	onselect = null;
	onchange = null;
	onblur = null;
	onfocus = null;
	style = null;
	styleClass = null;

    }


    // ------------------------------------------------------ Protected Methods


    /**
     * Prepares the style attributes for inclusion in the component's HTML tag.
     * @return The prepared String for inclusion in the HTML tag.
     */
    protected String prepareStyles() {
        StringBuffer styles = new StringBuffer();
        if (style != null) {
            styles.append(" style=\"");
            styles.append(style);
            styles.append("\"");
        }
        if (styleClass != null) {
            styles.append(" class=\"");
            styles.append(styleClass);
            styles.append("\"");
        }
        return styles.toString();
    }

    /**
     * Prepares the event handlers for inclusion in the component's HTML tag.
     * @return The prepared String for inclusion in the HTML tag.
     */
    protected String prepareEventHandlers() {
        StringBuffer handlers = new StringBuffer();
        prepareMouseEvents(handlers);
        prepareKeyEvents(handlers);
        prepareTextEvents(handlers);
        prepareFocusEvents(handlers);
        return handlers.toString();
    }


    // -------------------------------------------------------- Private Methods


    /**
     * Prepares the mouse event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareMouseEvents(StringBuffer handlers) {
        if (onclick != null) {
            handlers.append(" onclick=\"");
            handlers.append(onclick);
            handlers.append("\"");
        }

        if (ondblclick != null) {
            handlers.append(" ondblclick=\"");
            handlers.append(ondblclick);
            handlers.append("\"");
        }

        if (onmouseover != null) {
            handlers.append(" onmouseover=\"");
            handlers.append(onmouseover);
            handlers.append("\"");
        }

        if (onmouseout != null) {
            handlers.append(" onmouseout=\"");
            handlers.append(onmouseout);
            handlers.append("\"");
        }

        if (onmousemove != null) {
            handlers.append(" onmousemove=\"");
            handlers.append(onmousemove);
            handlers.append("\"");
        }

        if (onmousedown != null) {
            handlers.append(" onmousedown=\"");
            handlers.append(onmousedown);
            handlers.append("\"");
        }

        if (onmouseup != null) {
            handlers.append(" onmouseup=\"");
            handlers.append(onmouseup);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the keyboard event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareKeyEvents(StringBuffer handlers) {

        if (onkeydown != null) {
            handlers.append(" onkeydown=\"");
            handlers.append(onkeydown);
            handlers.append("\"");
        }

        if (onkeyup != null) {
            handlers.append(" onkeyup=\"");
            handlers.append(onkeyup);
            handlers.append("\"");
        }

        if (onkeypress != null) {
            handlers.append(" onkeypress=\"");
            handlers.append(onkeypress);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the text event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareTextEvents(StringBuffer handlers) {

        if (onselect != null) {
            handlers.append(" onselect=\"");
            handlers.append(onselect);
            handlers.append("\"");
        }

        if (onchange != null) {
            handlers.append(" onchange=\"");
            handlers.append(onchange);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the focus event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareFocusEvents(StringBuffer handlers) {

        if (onblur != null) {
            handlers.append(" onblur=\"");
            handlers.append(onblur);
            handlers.append("\"");
        }

        if (onfocus != null) {
            handlers.append(" onfocus=\"");
            handlers.append(onfocus);
            handlers.append("\"");
        }
    }




}
