package org.apache.strutsel.taglib.utils;

import java.io.*;
import org.apache.cactus.*;
import org.apache.commons.logging.*;
import org.apache.strutsel.taglib.utils.*;


public class TestHelper {
    private static Log log = LogFactory.getLog(TestHelper.class);

    public static void printResponse(com.meterware.httpunit.WebResponse testResponse)
                              throws IOException {
        log.debug("response text[" + testResponse.getText() + "]");
    }

    public static String getAttrErrMess(String  tagName,
                                        String  attrName,
                                        String  requiredValue,
                                        String  actualValue)
    {
        return ("The <" + tagName + "> \"" + attrName + "\" attribute " +
                "should have had value \"" + requiredValue + "\", but it " +
                "instead had value \"" + actualValue + "\".");
    }
}
