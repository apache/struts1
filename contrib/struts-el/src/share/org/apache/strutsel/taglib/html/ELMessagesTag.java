package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.MessagesTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMessagesTag extends MessagesTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setId((String)ExpressionUtil.
                  evalNotNull("messages", "id", getId(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setId(null);
        }

        try {
            setBundle((String)ExpressionUtil.
                      evalNotNull("messages", "bundle", getBundle(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBundle(null);
        }

        try {
            setLocale((String)ExpressionUtil.
                      evalNotNull("messages", "locale", getLocale(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocale(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("messages", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("messages", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setHeader((String)ExpressionUtil.
                      evalNotNull("messages", "header", getHeader(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHeader(null);
        }

        try {
            setFooter((String)ExpressionUtil.
                      evalNotNull("messages", "footer", getFooter(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFooter(null);
        }

        try {
            setMessage((String)ExpressionUtil.
                       evalNotNull("messages", "message", getMessage(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMessage(null);
        }
    }
}
