package org.apache.strutsel.taglib.logic;

import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class ELIterateTagBeanInfo extends SimpleBeanInfo
{
    public  PropertyDescriptor[] getPropertyDescriptors()
    {
        PropertyDescriptor[]  result   = new PropertyDescriptor[9];

        try {
            Method collectionGetter  =
                ELIterateTag.class.getMethod("getCollectionExpr", new Class[0]);
            Method collectionSetter  =
                ELIterateTag.class.getMethod("setCollectionExpr",
                                             new Class[]{String.class});

            result[0] =
                new PropertyDescriptor("collection",
                                       collectionGetter, collectionSetter);

            result[1] = new PropertyDescriptor("id", ELIterateTag.class);
            result[2] = new PropertyDescriptor("indexId", ELIterateTag.class);
            result[3] = new PropertyDescriptor("length", ELIterateTag.class);
            result[4] = new PropertyDescriptor("name", ELIterateTag.class);
            result[5] = new PropertyDescriptor("offset", ELIterateTag.class);
            result[6] = new PropertyDescriptor("property", ELIterateTag.class);
            result[7] = new PropertyDescriptor("scope", ELIterateTag.class);
            result[8] = new PropertyDescriptor("type", ELIterateTag.class);
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
