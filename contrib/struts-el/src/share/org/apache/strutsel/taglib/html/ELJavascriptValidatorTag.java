package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.JavascriptValidatorTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELJavascriptValidatorTag extends JavascriptValidatorTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }
    
    private void evaluateExpressions() throws JspException {
        try {
            setDynamicJavascript((String)ExpressionUtil.
                                 evalNotNull("javascript", "dynamicJavascript",
                                             getDynamicJavascript(), 
                                             String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setDynamicJavascript(null);
        }

        try {
            setFormName((String)ExpressionUtil.
                        evalNotNull("javascript", "formName", getFormName(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFormName(null);
        }

        try {
            setMethod((String)ExpressionUtil.
                      evalNotNull("javascript", "method", getMethod(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMethod(null);
        }

        try {
            setPage(((Integer)ExpressionUtil.
                     evalNotNull("javascript", "page", getPage() + "", 
                                 Integer.class, this, pageContext)).
                    intValue());
        } catch (NullAttributeException ex) {
            setPage(0);
        }

        try {
            setSrc((String)ExpressionUtil.
                   evalNotNull("javascript", "src", getSrc(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setSrc(null);
        }

        try {
            setStaticJavascript((String)ExpressionUtil.
                                evalNotNull("javascript", "staticJavascript",
                                            getStaticJavascript(), 
                                            String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStaticJavascript(null);
        }
    }
}
