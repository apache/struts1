package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.SelectTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELSelectTag extends SelectTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }
    
    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("select", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("select", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("select", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setDisabled(((Boolean)ExpressionUtil.
                         evalNotNull("select", "disabled", getDisabled() + "", 
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setIndexed(((Boolean)ExpressionUtil.
                        evalNotNull("select", "indexed", getIndexed() + "", 
                                    Boolean.class, this, pageContext)).
                       booleanValue());
        } catch (NullAttributeException ex) {
            setIndexed(false);
        }

        try {
            setMultiple((String)ExpressionUtil.
                        evalNotNull("select", "multiple", getMultiple(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setMultiple(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("select", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnblur((String)ExpressionUtil.
                      evalNotNull("select", "onblur", getOnblur(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

        try {
            setOnchange((String)ExpressionUtil.
                        evalNotNull("select", "onchange", getOnchange(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnchange(null);
        }

        try {
            setOnclick((String)ExpressionUtil.
                       evalNotNull("select", "onclick", getOnclick(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String)ExpressionUtil.
                          evalNotNull("select", "ondblclick", getOndblclick(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String)ExpressionUtil.
                       evalNotNull("select", "onfocus", getOnfocus(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("select", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("select", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("select", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String)ExpressionUtil.
                           evalNotNull("select", "onmousedown", getOnmousedown(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

        try {
            setOnmousemove((String)ExpressionUtil.
                           evalNotNull("select", "onmousemove",
                                       getOnmousemove(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousemove(null);
        }

        try {
            setOnmouseout((String)ExpressionUtil.
                          evalNotNull("select", "onmouseout", getOnmouseout(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseout(null);
        }

        try {
            setOnmouseover((String)ExpressionUtil.
                           evalNotNull("select", "onmouseover",
                                       getOnmouseover(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseover(null);
        }

        try {
            setOnmouseup((String)ExpressionUtil.
                         evalNotNull("select", "onmouseup", getOnmouseup(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseup(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("select", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setSize((String)ExpressionUtil.
                    evalNotNull("select", "size", getSize(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setSize(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("select", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("select", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("select", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTabindex((String)ExpressionUtil.
                        evalNotNull("select", "tabindex", getTabindex(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTabindex(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("select", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("select", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("select", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
