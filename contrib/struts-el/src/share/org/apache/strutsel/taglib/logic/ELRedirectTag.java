package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.RedirectTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELRedirectTag extends RedirectTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAnchor((String)ExpressionUtil.
                      evalNotNull("redirect", "anchor", getAnchor(),
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAnchor(null);
        }

        try {
            setForward((String)ExpressionUtil.
                       evalNotNull("redirect", "forward", getForward(),
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setHref((String)ExpressionUtil.
                    evalNotNull("redirect", "href", getHref(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHref(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("redirect", "name", getName(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setPage((String)ExpressionUtil.
                    evalNotNull("redirect", "page", getPage(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setParamName((String)ExpressionUtil.
                         evalNotNull("redirect", "paramName", getParamName(),
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String)ExpressionUtil.
                             evalNotNull("redirect", "paramProperty",
                                         getParamProperty(),
                                         String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String)ExpressionUtil.
                          evalNotNull("redirect", "paramScope", getParamScope(),
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("redirect", "property", getProperty(),
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("redirect", "scope", getScope(),
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setTransaction(((Boolean)ExpressionUtil.
                            evalNotNull("redirect", "transaction",
                                        getTransaction() + "",
                                        Boolean.class, this, pageContext)).
                           booleanValue());
        } catch (NullAttributeException ex) {
            setTransaction(false);
        }
    }
}
