package org.apache.strutsel.taglib.utils;

import java.util.HashMap;
import org.apache.cactus.JspTestCase;

public class JspTagTestCase extends JspTestCase
{
    public JspTagTestCase(String theName) {
        super(theName);
    }

    protected void
        checkAttrValue(HashMap                              attrMap,
                       com.meterware.httpunit.WebResponse   testResponse,
                       String                               headerKey,
                       String                               tagName,
                       String                               attrName)
    {
        String attrValue         = (String)attrMap.get(attrName);
        String requiredAttrValue =
            (String)testResponse.getHeaderField(headerKey);

        if (!attrValue.equals(requiredAttrValue)) {
            fail(TestHelper.getAttrErrMess(tagName, attrName,
                                           requiredAttrValue, attrValue));
        }
    }
}
