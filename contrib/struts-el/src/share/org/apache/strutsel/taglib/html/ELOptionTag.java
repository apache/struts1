package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.OptionTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELOptionTag extends OptionTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setBundle((String)ExpressionUtil.
                      evalNotNull("option", "bundle", getBundle(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBundle(null);
        }

        try {
            setDisabled(((Boolean)ExpressionUtil.
                         evalNotNull("option", "disabled", getDisabled() + "", 
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setKey((String)ExpressionUtil.
                   evalNotNull("option", "key", getKey(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setKey(null);
        }

        try {
            setLocale((String)ExpressionUtil.
                      evalNotNull("option", "locale", getLocale(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocale(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("option", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("option", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

//         try {
//             setStyleId((String)ExpressionUtil.
//                        evalNotNull("option", "styleId", getStyleId(), 
//                                    String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setStyleId(null);
//         }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("option", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
