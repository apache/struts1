package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.ErrorsTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELErrorsTag extends ErrorsTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setBundle((String)ExpressionUtil.
                      evalNotNull("errors", "bundle", getBundle(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBundle(null);
        }

        try {
            setLocale((String)ExpressionUtil.
                      evalNotNull("errors", "locale", getLocale(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocale(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("errors", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("errors", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }
    }
}
