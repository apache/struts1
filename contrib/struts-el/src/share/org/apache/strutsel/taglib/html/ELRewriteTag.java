package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.RewriteTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELRewriteTag extends RewriteTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAnchor((String)ExpressionUtil.
                      evalNotNull("rewrite", "anchor", getAnchor(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAnchor(null);
        }

        try {
            setForward((String)ExpressionUtil.
                       evalNotNull("rewrite", "forward", getForward(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setHref((String)ExpressionUtil.
                    evalNotNull("rewrite", "href", getHref(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHref(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("rewrite", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setPage((String)ExpressionUtil.
                    evalNotNull("rewrite", "page", getPage(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setParamId((String)ExpressionUtil.
                       evalNotNull("rewrite", "paramId", getParamId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamId(null);
        }

        try {
            setParamName((String)ExpressionUtil.
                         evalNotNull("rewrite", "paramName", getParamName(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String)ExpressionUtil.
                             evalNotNull("rewrite", "paramProperty", getParamProperty(), 
                                         String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String)ExpressionUtil.
                          evalNotNull("rewrite", "paramScope", getParamScope(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("rewrite", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("rewrite", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setTransaction(((Boolean)ExpressionUtil.
                            evalNotNull("rewrite", "transaction",
                                        getTransaction() + "", 
                                        Boolean.class, this, pageContext)).
                           booleanValue());
        } catch (NullAttributeException ex) {
            setTransaction(false);
        }
    }
}
