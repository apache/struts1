package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.CancelTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELCancelTag extends CancelTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("cancel", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("cancel", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("cancel", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setDisabled(((Boolean)ExpressionUtil.
                         evalNotNull("cancel", "disabled", getDisabled() + "", 
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setOnblur((String)ExpressionUtil.
                      evalNotNull("cancel", "onblur", getOnblur(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

        try {
            setOnchange((String)ExpressionUtil.
                        evalNotNull("cancel", "onchange", getOnchange(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnchange(null);
        }

        try {
            setOnclick((String)ExpressionUtil.
                       evalNotNull("cancel", "onclick", getOnclick(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String)ExpressionUtil.
                          evalNotNull("cancel", "ondblclick", getOndblclick(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String)ExpressionUtil.
                       evalNotNull("cancel", "onfocus", getOnfocus(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("cancel", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("cancel", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("cancel", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String)ExpressionUtil.
                           evalNotNull("cancel", "onmousedown", getOnmousedown(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

        try {
            setOnmousemove((String)ExpressionUtil.
                           evalNotNull("cancel", "onmousemove",
                                       getOnmousemove(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousemove(null);
        }

        try {
            setOnmouseout((String)ExpressionUtil.
                          evalNotNull("cancel", "onmouseout", getOnmouseout(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseout(null);
        }

        try {
            setOnmouseover((String)ExpressionUtil.
                           evalNotNull("cancel", "onmouseover",
                                       getOnmouseover(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseover(null);
        }

        try {
            setOnmouseup((String)ExpressionUtil.
                         evalNotNull("cancel", "onmouseup", getOnmouseup(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseup(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("cancel", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("cancel", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("cancel", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("cancel", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTabindex((String)ExpressionUtil.
                        evalNotNull("cancel", "tabindex", getTabindex(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTabindex(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("cancel", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("cancel", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("cancel", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
