package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.HiddenTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELHiddenTag extends HiddenTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("hidden", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("hidden", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setIndexed(((Boolean)ExpressionUtil.
                        evalNotNull("hidden", "indexed", getIndexed() + "", 
                                    Boolean.class, this, pageContext)).
                       booleanValue());
        } catch (NullAttributeException ex) {
            setIndexed(false);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("hidden", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("hidden", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("hidden", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("hidden", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("hidden", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }

        try {
            setWrite(((Boolean)ExpressionUtil.
                      evalNotNull("hidden", "write", getWrite() + "", 
                                  Boolean.class, this, pageContext)).
                     booleanValue());
        } catch (NullAttributeException ex) {
            setWrite(false);
        }
    }
}
