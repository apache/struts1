package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.MessagesPresentTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMessagesPresentTag extends MessagesPresentTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("messagesPresent", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("messagesPresent", "property",
                                    getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setMessage((String)ExpressionUtil.
                       evalNotNull("messagesPresent", "message", getMessage(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMessage(null);
        }
    }
}
