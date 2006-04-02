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

package org.apache.struts.taglib;

import org.apache.struts.action.ActionForm;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Simple bean for unit tests.
 */
public class SimpleBeanForTesting extends ActionForm {
    public SimpleBeanForTesting() {
        super();
    }

    public SimpleBeanForTesting(List lst) {
        this.lst = lst;
    }

    public SimpleBeanForTesting(boolean checked) {
        this.checked = checked;
    }

    public SimpleBeanForTesting(Boolean checkedWrapper) {
        this.checkedWrapper = checkedWrapper;
    }

    public SimpleBeanForTesting(Map map) {
        this.map = map;
    }

    public SimpleBeanForTesting(String string) {
        this.string = string;
    }

    public SimpleBeanForTesting(String[] stringArray) {
        this.stringArray = stringArray;
    }

    public SimpleBeanForTesting(Integer integerValue) {
        this.integerValue = integerValue;
    }

    private String string;
    private String[] stringArray;
    private Integer integerValue;
    private Double doubleValue;
    private List lst;
    private Map map;
    private String x;
    private String y;
    private Object nestedObject;
    private Object[] array;
    private Enumeration enumeration;
    private Collection collection;
    private boolean checked;
    private Boolean checkedWrapper;

    //Copied right from the FAQ
    private String strAry[] =
            { "String 0", "String 1", "String 2", "String 3", "String 4" };

    public String getStringIndexed(int index) {
        return (strAry[index]);
    }

    public void setStringIndexed(int index, String value) {
        strAry[index] = value;
    }

    /**
     * Returns the lst.
     *
     * @return List
     */
    public List getList() {
        return lst;
    }

    /**
     * Returns the map.
     *
     * @return Map
     */
    public Map getMap() {
        return map;
    }

    /**
     * Sets the lst.
     *
     * @param lst The lst to set
     */
    public void setList(List lst) {
        this.lst = lst;
    }

    /**
     * Sets the map.
     *
     * @param map The map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Returns the string.
     *
     * @return String
     */
    public String getString() {
        return string;
    }

    /**
     * Sets the string.
     *
     * @param string The string to set
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * Returns an array of type String.
     *
     * @return String[]
     */
    public String[] getStringArray() {
        return stringArray;
    }

    /**
     * Sets the string array.
     *
     * @param string The string array to set
     */
    public void setStringArray(String[] stringArray) {
        this.stringArray = stringArray;
    }

    /**
     * Returns the lst.
     *
     * @return List
     */
    public List getLst() {
        return lst;
    }

    /**
     * Sets the lst.
     *
     * @param lst The lst to set
     */
    public void setLst(List lst) {
        this.lst = lst;
    }

    /**
     * Returns the Object Array.
     *
     * @return Object[]
     */
    public Object[] getArray() {
        return array;
    }

    /**
     * Sets the Object Array .
     *
     * @param array The Object array to set
     */
    public void setArray(Object[] array) {
        this.array = array;
    }

    /**
     * Returns the enumeration.
     *
     * @return Enumeration
     */
    public Enumeration getEnumeration() {
        return enumeration;
    }

    /**
     * Sets the enumeration.
     *
     * @param enumeration The enumeration to set
     */
    public void setEnumeration(Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    /**
     * Returns the nestedObject.
     *
     * @return Object
     */
    public Object getNestedObject() {
        return nestedObject;
    }

    /**
     * Sets the nestedObject.
     *
     * @param nestedObject The nestedObject to set
     */
    public void setNestedObject(Object nestedObject) {
        this.nestedObject = nestedObject;
    }

    /**
     * Returns the collection.
     *
     * @return Collection
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * Sets the collection.
     *
     * @param collection The collection to set
     */
    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    /**
     * Returns the doubleValue.
     *
     * @return Double
     */
    public Double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Returns the integerValue.
     *
     * @return Integer
     */
    public Integer getIntegerValue() {
        return integerValue;
    }

    /**
     * Sets the doubleValue.
     *
     * @param doubleValue The doubleValue to set
     */
    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    /**
     * Sets the integerValue.
     *
     * @param integerValue The integerValue to set
     */
    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    /**
     * Returns the checked.
     *
     * @return boolean
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * Sets the checked.
     *
     * @param checked The checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * Returns the checkedWrapper.
     *
     * @return Boolean
     */
    public Boolean getCheckedWrapper() {
        return checkedWrapper;
    }

    /**
     * Sets the checkedWrapper.
     *
     * @param checkedWrapper The checkedWrapper to set
     */
    public void setCheckedWrapper(Boolean checkedWrapper) {
        this.checkedWrapper = checkedWrapper;
    }

    /**
     * Returns the x.
     *
     * @return String
     */
    public String getX() {
        return x;
    }

    /**
     * Returns the y.
     *
     * @return String
     */
    public String getY() {
        return y;
    }

    /**
     * Sets the x.
     *
     * @param x The x to set
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * Sets the y.
     *
     * @param y The y to set
     */
    public void setY(String y) {
        this.y = y;
    }

    public String getJustThrowAnException() throws Exception {
        throw new Exception();
    }

}
