/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/taglib/Attic/BaseHandlerTag.java,v 1.2 2000/06/15 03:18:53 craigmcc Exp $
 * $Revision: 1.2 $
 * $Date: 2000/06/15 03:18:53 $
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

package org.apache.struts.taglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import org.apache.struts.util.MessageResources;

/**
 * Base class for tags that render form elements capable of including JavaScript
 * event handlers and/or CSS Style attributes. This class does not implement
 * the doStartTag() or doEndTag() methods. Subclasses should provide
 * appropriate implementations of these.
 *
 * @author Don Clasen
 * @version $Revision: 1.2 $ $Date: 2000/06/15 03:18:53 $
 */

public abstract class BaseHandlerTag extends BodyTagSupport {


    // ----------------------------------------------------- Instance Variables


    /**
     * The message resources for this package.
     */
    protected static MessageResources messages =
	MessageResources.getMessageResources
	("org.apache.struts.taglib.LocalStrings");


//  Mouse Events

    /** Mouse click event. */
    private String onClick = null;

    /** Mouse double click event. */
    private String onDblClick = null;

    /** Mouse over component event. */
    private String onMouseOver = null;

    /** Mouse exit component event. */
    private String onMouseOut = null;

    /** Mouse moved over component event. */
    private String onMouseMove = null;

    /** Mouse pressed on component event. */
    private String onMouseDown = null;

    /** Mouse released on component event. */
    private String onMouseUp = null;

//  Keyboard Events

    /** Key down in component event. */
    private String onKeyDown = null;

    /** Key released in component event. */
    private String onKeyUp = null;

    /** Key down and up together in component event. */
    private String onKeyPress = null;

// Text Events

    /** Text selected in component event. */
    private String onSelect = null;

    /** Content changed after component lost focus event. */
    private String onChange = null;

// Focus Events

    /** Component lost focus event. */
    private String onBlur = null;

    /** Component has received focus event. */
    private String onFocus = null;

// CSS Style Support

    /** Style attribute associated with component. */
    private String style = null;

    /** Named Style class associated with component. */
    private String styleClass = null;

    // ------------------------------------------------------------- Properties

// Mouse Events

    /** Sets the onClick event handler. */
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }

    /** Returns the onClick event handler. */
    public String getOnClick() {
        return onClick;
    }

    /** Sets the onDblClick event handler. */
    public void setOnDblClick(String onDblClick) {
        this.onDblClick = onDblClick;
    }

    /** Returns the onDblClick event handler. */
    public String getOnDblClick() {
        return onDblClick;
    }

    /** Sets the onMouseDown event handler. */
    public void setOnMouseDown(String onMouseDown) {
        this.onMouseDown = onMouseDown;
    }

    /** Returns the onMouseDown event handler. */
    public String getOnMouseDown() {
        return onMouseDown;
    }

    /** Sets the onMouseUp event handler. */
    public void setOnMouseUp(String onMouseUp) {
        this.onMouseUp = onMouseUp;
    }

    /** Returns the onMouseUp event handler. */
    public String getOnMouseUp() {
        return onMouseUp;
    }

    /** Sets the onMouseMove event handler. */
    public void setOnMouseMove(String onMouseMove) {
        this.onMouseMove = onMouseMove;
    }

    /** Returns the onMouseMove event handler. */
    public String getOnMouseMove() {
        return onMouseMove;
    }

    /** Sets the onMouseOver event handler. */
    public void setOnMouseOver(String onMouseOver) {
        this.onMouseOver = onMouseOver;
    }

    /** Returns the onMouseOver event handler. */
    public String getOnMouseOver() {
        return onMouseOver;
    }

    /** Sets the onMouseOut event handler. */
    public void setOnMouseOut(String onMouseOut) {
        this.onMouseOut = onMouseOut;
    }

    /** Returns the onMouseOut event handler. */
    public String getOnMouseOut() {
        return onMouseOut;
    }

// Keyboard Events

    /** Sets the onKeyDown event handler. */
    public void setOnKeyDown(String onKeyDown) {
        this.onKeyDown = onKeyDown;
    }

    /** Returns the onKeyDown event handler. */
    public String getOnKeyDown() {
        return onKeyDown;
    }

    /** Sets the onKeyUp event handler. */
    public void setOnKeyUp(String onKeyUp) {
        this.onKeyUp = onKeyUp;
    }

    /** Returns the onKeyUp event handler. */
    public String getOnKeyUp() {
        return onKeyUp;
    }

    /** Sets the onKeyPress event handler. */
    public void setOnKeyPress(String onKeyPress) {
        this.onKeyPress = onKeyPress;
    }

    /** Returns the onKeyPress event handler. */
    public String getOnKeyPress() {
        return onKeyPress;
    }

// Text Events

    /** Sets the onChange event handler. */
    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    /** Returns the onChange event handler. */
    public String getOnChange() {
        return onChange;
    }

    /** Sets the onSelect event handler. */
    public void setOnSelect(String onSelect) {
        this.onSelect = onSelect;
    }

    /** Returns the onSelect event handler. */
    public String getOnSelect() {
        return onSelect;
    }

// Focus Events

    /** Sets the onBlur event handler. */
    public void setOnBlur(String onBlur) {
        this.onBlur = onBlur;
    }

    /** Returns the onBlur event handler. */
    public String getOnBlur() {
        return onBlur;
    }

    /** Sets the onFocus event handler. */
    public void setOnFocus(String onFocus) {
        this.onFocus = onFocus;
    }

    /** Returns the onFocus event handler. */
    public String getOnFocus() {
        return onFocus;
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

    /**
     * Prepares the mouse event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareMouseEvents(StringBuffer handlers) {
        if (onClick != null) {
            handlers.append(" onClick=\"");
            handlers.append(onClick);
            handlers.append("\"");
        }

        if (onDblClick != null) {
            handlers.append(" onDblClick=\"");
            handlers.append(onDblClick);
            handlers.append("\"");
        }

        if (onMouseOver != null) {
            handlers.append(" onMouseOver=\"");
            handlers.append(onMouseOver);
            handlers.append("\"");
        }

        if (onMouseOut != null) {
            handlers.append(" onMouseOut=\"");
            handlers.append(onMouseOut);
            handlers.append("\"");
        }

        if (onMouseMove != null) {
            handlers.append(" onMouseMove=\"");
            handlers.append(onMouseMove);
            handlers.append("\"");
        }

        if (onMouseDown != null) {
            handlers.append(" onMouseDown=\"");
            handlers.append(onMouseDown);
            handlers.append("\"");
        }

        if (onMouseUp != null) {
            handlers.append(" onMouseUp=\"");
            handlers.append(onMouseUp);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the keyboard event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareKeyEvents(StringBuffer handlers) {

        if (onKeyDown != null) {
            handlers.append(" onKeyDown=\"");
            handlers.append(onKeyDown);
            handlers.append("\"");
        }

        if (onKeyUp != null) {
            handlers.append(" onKeyUp=\"");
            handlers.append(onKeyUp);
            handlers.append("\"");
        }

        if (onKeyPress != null) {
            handlers.append(" onKeyPress=\"");
            handlers.append(onKeyPress);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the text event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareTextEvents(StringBuffer handlers) {

        if (onSelect != null) {
            handlers.append(" onSelect=\"");
            handlers.append(onSelect);
            handlers.append("\"");
        }

        if (onChange != null) {
            handlers.append(" onChange=\"");
            handlers.append(onChange);
            handlers.append("\"");
        }
    }

    /**
     * Prepares the focus event handlers, appending them to the the given
     * StringBuffer.
     * @param handlers The StringBuffer that output will be appended to.
     */
    private void prepareFocusEvents(StringBuffer handlers) {

        if (onBlur != null) {
            handlers.append(" onBlur=\"");
            handlers.append(onBlur);
            handlers.append("\"");
        }

        if (onFocus != null) {
            handlers.append(" onFocus=\"");
            handlers.append(onFocus);
            handlers.append("\"");
        }
    }




}