package org.apache.strutsel.taglib.utils;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public final class EvalHelper
{
    private EvalHelper() {}

    public static Object eval(String      tagName,
                              String      attrName,
                              String      attrValue,
                              Class       attrType,
                              Tag         tagObject,
                              PageContext pageContext)
        throws JspException, NullAttributeException
    {
        Object result   =
            ExpressionUtil.evalNotNull(tagName, attrName, attrValue,
                                       attrType, tagObject, pageContext);
        if (result == null)
            throw new NullAttributeException(attrName, tagName);
        return (result);
    }
}
