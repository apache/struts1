package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.NotMatchTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELNotMatchTag extends NotMatchTag {

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
                      evalNotNull("notMatch", "cookie", getCookie(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setCookie(null);
        }

        try {
            setExpr((String)ExpressionUtil.
                      evalNotNull("notMatch", "expr", getExpr(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setExpr(null);
        }

        try {
            setHeader((String)ExpressionUtil.
                      evalNotNull("notMatch", "header", getHeader(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHeader(null);
        }

        try {
            setLocation((String)ExpressionUtil.
                        evalNotNull("notMatch", "location", getLocation(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocation(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("notMatch", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setParameter((String)ExpressionUtil.
                         evalNotNull("notMatch", "parameter", getParameter(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParameter(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("notMatch", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("notMatch", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("notMatch", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
