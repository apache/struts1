package org.apache.strutsel.taglib.bean;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class ELSizeTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[5];

        try {
            Method collectionGetter  =
                ELSizeTag.class.getMethod("getCollectionExpr", new Class[0]);
            Method collectionSetter  =
                ELSizeTag.class.getMethod("setCollectionExpr",
                                          new Class[]{String.class});

            result[0] =
                new PropertyDescriptor("collection",
                                       collectionGetter, collectionSetter);

            result[1] = new PropertyDescriptor("id", ELSizeTag.class);
            result[2] = new PropertyDescriptor("name", ELSizeTag.class);
            result[3] = new PropertyDescriptor("property", ELSizeTag.class);
            result[4] = new PropertyDescriptor("scope", ELSizeTag.class);
        }
        catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
        
        return (result);
    }
}
