package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.ForwardTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELForwardTag extends ForwardTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("forward", "name", getName(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }
    }
}
