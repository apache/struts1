package org.apache.strutsel.taglib.bean;

import org.apache.struts.taglib.bean.ResourceTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELResourceTag extends ResourceTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setId((String)ExpressionUtil.
                  evalNotNull("resource", "id", getId(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setId(null);
        }

        try {
            setInput((String)ExpressionUtil.
                    evalNotNull("resource", "input", getInput(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setInput(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("resource", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }
    }
}
