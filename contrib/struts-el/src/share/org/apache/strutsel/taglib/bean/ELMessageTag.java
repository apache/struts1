package org.apache.strutsel.taglib.bean;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.bean.MessageTag;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMessageTag extends MessageTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    /**
     * Processes all attribute values which use the JSTL expression evaluation
     * engine to determine their values.
     *
     * @exception JspException if a JSP exception has occurred
     */
    private void evaluateExpressions() throws JspException {

        try {
            setArg0((String)ExpressionUtil.
                    evalNotNull("message", "arg0", getArg0(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setArg0(null);
        }

        try {
            setArg1((String)ExpressionUtil.
                    evalNotNull("message", "arg1", getArg1(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setArg1(null);
        }

        try {
            setArg2((String)ExpressionUtil.
                    evalNotNull("message", "arg2", getArg2(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setArg2(null);
        }

        try {
            setArg3((String)ExpressionUtil.
                    evalNotNull("message", "arg3", getArg3(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setArg3(null);
        }

        try {
            setArg4((String)ExpressionUtil.
                    evalNotNull("message", "arg4", getArg4(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setArg4(null);
        }

        try {
            setBundle((String)ExpressionUtil.
                      evalNotNull("message", "bundle", getBundle(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBundle(null);
        }

        try {
            setKey((String)ExpressionUtil.
                   evalNotNull("message", "key", getKey(),
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setKey(null);
        }

        try {
            setLocale((String)ExpressionUtil.
                      evalNotNull("message", "locale", getLocale(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocale(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("message", "name", getName(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("message", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("message", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }
    }
}
