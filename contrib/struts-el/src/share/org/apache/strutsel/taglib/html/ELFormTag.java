package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.FormTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELFormTag extends FormTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAction((String)ExpressionUtil.
                      evalNotNull("form", "action", getAction(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAction(null);
        }

        try {
            setEnctype((String)ExpressionUtil.
                       evalNotNull("form", "enctype", getEnctype(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setEnctype(null);
        }

        try {
            setFocus((String)ExpressionUtil.
                     evalNotNull("form", "focus", getFocus(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFocus(null);
        }

        try {
            setMethod((String)ExpressionUtil.
                      evalNotNull("form", "method", getMethod(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMethod(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("form", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnreset((String)ExpressionUtil.
                       evalNotNull("form", "onreset", getOnreset(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnreset(null);
        }

        try {
            setOnsubmit((String)ExpressionUtil.
                        evalNotNull("form", "onsubmit", getOnsubmit(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnsubmit(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("form", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("form", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("form", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("form", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTarget((String)ExpressionUtil.
                      evalNotNull("form", "target", getTarget(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTarget(null);
        }

        try {
            setType((String)ExpressionUtil.
                    evalNotNull("form", "type", getType(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setType(null);
        }
    }
}
