package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.HtmlTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELHtmlTag extends HtmlTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setLocale(((Boolean)ExpressionUtil.
                       evalNotNull("html", "locale", getLocale() + "", 
                                   Boolean.class, this, pageContext)).
                      booleanValue());
        } catch (NullAttributeException ex) {
            setLocale(false);
        }

        try {
            setXhtml(((Boolean)ExpressionUtil.
                      evalNotNull("html", "xhtml", getXhtml() + "", 
                                  Boolean.class, this, pageContext)).
                     booleanValue());
        } catch (NullAttributeException ex) {
            setXhtml(false);
        }
    }
}
