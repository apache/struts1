package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.MatchTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMatchTag extends MatchTag {

    private String   expr;

    public  String   getExpr() {
        return (expr);
    }
    
    public  void  setExpr(String expr) {
        this.expr = expr;
    }
    
    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    protected boolean condition(boolean desired) throws JspException {
        boolean   result   = false;
        if (getExpr() != null) {
            result   =
                ELMatchSupport.condition(desired, getExpr(), value, location,
                                         messages, pageContext);
        }
        else {
            result   = super.condition(desired);
        }
        return (result);
    }
    
    private void evaluateExpressions() throws JspException {

        try {
            setCookie((String)ExpressionUtil.
                      evalNotNull("match", "cookie", getCookie(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setCookie(null);
        }

        try {
            setExpr((String)ExpressionUtil.
                      evalNotNull("match", "expr", getExpr(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setExpr(null);
        }

        try {
            setHeader((String)ExpressionUtil.
                      evalNotNull("match", "header", getHeader(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHeader(null);
        }

        try {
            setLocation((String)ExpressionUtil.
                        evalNotNull("match", "location", getLocation(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocation(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("match", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setParameter((String)ExpressionUtil.
                         evalNotNull("match", "parameter", getParameter(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParameter(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("match", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("match", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("match", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
