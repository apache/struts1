package org.apache.struts.scaffold;

import org.apache.struts.action.ActionForm;


/**
 * Generic properties for use with menu items.
 */
public class MenuForm extends ActionForm {


// ------------------------------------------------------- Properties


    /**
     * A generic name field,
     * use to store a selected option or the name of a property.
     * <p>
     * For example, to indicate a search by product, you might set
     * keyName=product and keyValue=Scaffold.
     * <p>
     * When both name and value are used, value should be the "scalar".
     * <p>
     * In forms, the abbreviations N and V may be used.
     * (See Public Methods section.)
     */
    private String keyName = null;


    /**
     * Return the keyName.
     */
    public String getKeyName() {
        return this.keyName;
    }


    /**
     * Set the keyName.
     *
     * @param key The new keyName
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }


    /**
     * A generic value field,
     * use to store a value associated with an option or the
     * value of a property.
     * <p>
     * For example, to indicate a search by product you might set
     * keyName=product and keyValue=Scaffold.
     * <p>
     * When both key and value are used, value should be the "scalar".
     */
    private String keyValue = null;


    /**
     * Return the value.
     */
    public String getKeyValue() {
        return this.keyValue;
    }


    /**
     * Set the value.
     *
     * @param value The new value
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }


    /**
     * The page, for use in workflows.
     */
    private int page = 0;


    /**
     * Return the page.
     */
    public int getPage() {
        return this.page;
    }


    /**
     * Set the page key
     *
     * @param key The new page key
     */
    public void setPage(int page) {
        this.page = page;
    }


    /**
     * The name of a logical forward
     * for use in mapping a forward to a menu option.
     */
    private String dispatch = null;


    /**
     * Return the dispatch.
     */
    public String getDispatch() {
        return this.dispatch;
    }


    /**
     * Set the dispatch
     *
     * @param key The new dispatch
     */
    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }



// ----------------------------------------------------- Public Methods


    /**
     * Return the keyName abbreviated as N.
     */
    public String getN() {
        return getKeyName();
    }


    /**
     * Set the keyName abbreviated as N.
     *
     * @param keyName The new keyName
     */
    public void setN(String keyName) {
        setKeyName(keyName);
    }


    /**
     * Return the Option (alias for name).
     * <p>
     * Mnemoric: O-N / Name
     */
    public String getO() {
        return getKeyName();
    }


    /**
     * Set the Option (alias for name)
     * <p>
     * Mnemoric: O-N / Name
     *
     * @param keyName The new keyName
     */
    public void setO(String keyName) {
        setKeyName(keyName);
    }


    /**
     * Return the value abbreviated as V.
     */
    public String getV() {
        return getKeyValue();
    }


    /**
     * Set the value abbreviated as V.
     *
     * @param keyValue The new keyValue
     */
    public void setV(String keyValue) {
        setKeyValue(keyValue);
    }


    /**
     * Return the Text (alias for value).
     * <p>
     * Mnemoric: T-V / value
     */
    public String getT() {
        return getKeyValue();
    }


    /**
     * Set the Text (alias for value)
     * <p>
     * Mnemoric: T-V / value
     *
     * @param value The new KeyValue
     */
    public void setT(String keyValue) {
        setKeyValue(keyValue);
    }


} // end MenuForm


/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/MenuForm.java,v 1.1 2002/08/14 18:30:08 husted Exp $
 * $Revision: 1.1 $
 * $Date: 2002/08/14 18:30:08 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
