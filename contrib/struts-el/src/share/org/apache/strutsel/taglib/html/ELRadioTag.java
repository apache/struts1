package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.RadioTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELRadioTag extends RadioTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }
    
    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("radio", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("radio", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("radio", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setDisabled(((Boolean)ExpressionUtil.
                         evalNotNull("radio", "disabled", getDisabled() + "", 
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setDisabled(false);
        }

        try {
            setIdName((String)ExpressionUtil.
                     evalNotNull("radio", "idName", getIdName(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setIdName(null);
        }

        try {
            setIndexed(((Boolean)ExpressionUtil.
                        evalNotNull("radio", "indexed", getIndexed() + "", 
                                    Boolean.class, this, pageContext)).
                       booleanValue());
        } catch (NullAttributeException ex) {
            setIndexed(false);
        }

        try {
            setName((String)ExpressionUtil.
                      evalNotNull("radio", "name", getName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnblur((String)ExpressionUtil.
                      evalNotNull("radio", "onblur", getOnblur(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

        try {
            setOnchange((String)ExpressionUtil.
                        evalNotNull("radio", "onchange", getOnchange(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnchange(null);
        }

        try {
            setOnclick((String)ExpressionUtil.
                       evalNotNull("radio", "onclick", getOnclick(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String)ExpressionUtil.
                          evalNotNull("radio", "ondblclick", getOndblclick(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String)ExpressionUtil.
                       evalNotNull("radio", "onfocus", getOnfocus(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("radio", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("radio", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("radio", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String)ExpressionUtil.
                           evalNotNull("radio", "onmousedown", getOnmousedown(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

//         try {
//             setOnmousemove((String)ExpressionUtil.
//                            evalNotNull("radio", "onmousemove",
//                                        getOnmousemove(), 
//                                        String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmousemove(null);
//         }

//         try {
//             setOnmouseout((String)ExpressionUtil.
//                           evalNotNull("radio", "onmouseout", getOnmouseout(), 
//                                       String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseout(null);
//         }

//         try {
//             setOnmouseover((String)ExpressionUtil.
//                            evalNotNull("radio", "onmouseover",
//                                        getOnmouseover(), 
//                                        String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseover(null);
//         }

//         try {
//             setOnmouseup((String)ExpressionUtil.
//                          evalNotNull("radio", "onmouseup", getOnmouseup(), 
//                                      String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseup(null);
//         }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("radio", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("radio", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("radio", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("radio", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTabindex((String)ExpressionUtil.
                        evalNotNull("radio", "tabindex", getTabindex(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTabindex(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("radio", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("radio", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("radio", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
