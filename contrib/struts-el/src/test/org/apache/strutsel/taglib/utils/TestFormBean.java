package org.apache.strutsel.taglib.utils;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

public class TestFormBean extends ActionForm
{
    private String   stringProperty;
    private Object[] arrayProperty;

    public  String   getStringProperty() { return (stringProperty); }
    public  void     setStringProperty(String stringProperty)
    { this.stringProperty = stringProperty; }

    public  Object[] getArrayProperty() { return (arrayProperty); }
    public  void setArrayProperty(Object[] arrayProperty)
    { this.arrayProperty = arrayProperty; }
}
