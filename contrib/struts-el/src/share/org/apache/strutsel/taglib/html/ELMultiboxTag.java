package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.MultiboxTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELMultiboxTag extends MultiboxTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("multibox", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("multibox", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("multibox", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setDisabled(((Boolean)ExpressionUtil.
                         evalNotNull("multibox", "disabled", getDisabled() + "", 
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setName((String)ExpressionUtil.
                      evalNotNull("multibox", "name", getName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnblur((String)ExpressionUtil.
                      evalNotNull("multibox", "onblur", getOnblur(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

        try {
            setOnchange((String)ExpressionUtil.
                        evalNotNull("multibox", "onchange", getOnchange(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnchange(null);
        }

        try {
            setOnclick((String)ExpressionUtil.
                       evalNotNull("multibox", "onclick", getOnclick(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String)ExpressionUtil.
                          evalNotNull("multibox", "ondblclick", getOndblclick(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String)ExpressionUtil.
                       evalNotNull("multibox", "onfocus", getOnfocus(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("multibox", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("multibox", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("multibox", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String)ExpressionUtil.
                           evalNotNull("multibox", "onmousedown", getOnmousedown(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

        try {
            setOnmousemove((String)ExpressionUtil.
                           evalNotNull("multibox", "onmousemove",
                                       getOnmousemove(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousemove(null);
        }

        try {
            setOnmouseout((String)ExpressionUtil.
                          evalNotNull("multibox", "onmouseout", getOnmouseout(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseout(null);
        }

        try {
            setOnmouseover((String)ExpressionUtil.
                           evalNotNull("multibox", "onmouseover",
                                       getOnmouseover(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseover(null);
        }

        try {
            setOnmouseup((String)ExpressionUtil.
                         evalNotNull("multibox", "onmouseup", getOnmouseup(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseup(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("multibox", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("multibox", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("multibox", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("multibox", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTabindex((String)ExpressionUtil.
                        evalNotNull("multibox", "tabindex", getTabindex(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTabindex(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("multibox", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("multibox", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("multibox", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
