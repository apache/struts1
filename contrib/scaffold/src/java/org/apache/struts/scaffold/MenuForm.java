/*
 * $Header: /home/cvs/jakarta-struts/contrib/scaffold/src/java/org/apache/struts/scaffold/MenuForm.java,v 1.2 2004/03/14 07:15:03 sraeburn Exp $
 * $Revision: 1.2 $
 * $Date: 2004/03/14 07:15:03 $
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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