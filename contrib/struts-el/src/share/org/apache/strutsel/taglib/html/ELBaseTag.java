package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.BaseTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELBaseTag extends BaseTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setTarget((String)ExpressionUtil.
                      evalNotNull("base", "target", getTarget(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTarget(null);
        }
    }
}
