package org.apache.strutsel.taglib.bean;

import org.apache.struts.taglib.bean.StrutsTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELStrutsTag extends StrutsTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setId((String)ExpressionUtil.
                  evalNotNull("struts", "id", getId(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setId(null);
        }

        try {
            setFormBean((String)ExpressionUtil.
                  evalNotNull("struts", "formBean", getFormBean(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFormBean(null);
        }

        try {
            setForward((String)ExpressionUtil.
                       evalNotNull("struts", "forward", getForward(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setMapping((String)ExpressionUtil.
                       evalNotNull("struts", "mapping", getMapping(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMapping(null);
        }
    }
}
