package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.ImgTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELImgTag extends ImgTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }
    
    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("img", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAlign((String)ExpressionUtil.
                   evalNotNull("img", "align", getAlign(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlign(null);
        }

        try {
            setAlt((String)ExpressionUtil.
                   evalNotNull("img", "alt", getAlt(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAlt(null);
        }

        try {
            setAltKey((String)ExpressionUtil.
                      evalNotNull("img", "altKey", getAltKey(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAltKey(null);
        }

        try {
            setBorder((String)ExpressionUtil.
                      evalNotNull("img", "border", getBorder(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBorder(null);
        }

        try {
            setBundle((String)ExpressionUtil.
                      evalNotNull("img", "bundle", getBundle(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setBundle(null);
        }

        try {
            setHeight((String)ExpressionUtil.
                      evalNotNull("img", "height", getHeight(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHeight(null);
        }

        try {
            setHspace((String)ExpressionUtil.
                      evalNotNull("img", "hspace", getHspace(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHspace(null);
        }

        try {
            setImageName((String)ExpressionUtil.
                      evalNotNull("img", "imageName", getImageName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setImageName(null);
        }

        try {
            setIsmap((String)ExpressionUtil.
                      evalNotNull("img", "ismap", getIsmap(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setIsmap(null);
        }

        try {
            setLocale((String)ExpressionUtil.
                      evalNotNull("img", "locale", getLocale(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLocale(null);
        }

        try {
            setLowsrc((String)ExpressionUtil.
                      evalNotNull("img", "lowsrc", getLowsrc(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLowsrc(null);
        }

        try {
            setName((String)ExpressionUtil.
                      evalNotNull("img", "name", getName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

//         try {
//             setOnblur((String)ExpressionUtil.
//                       evalNotNull("img", "onblur", getOnblur(), 
//                                   String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnblur(null);
//         }

//         try {
//             setOnchange((String)ExpressionUtil.
//                         evalNotNull("img", "onchange", getOnchange(), 
//                                     String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnchange(null);
//         }

//         try {
//             setOnclick((String)ExpressionUtil.
//                        evalNotNull("img", "onclick", getOnclick(), 
//                                    String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnclick(null);
//         }

//         try {
//             setOndblclick((String)ExpressionUtil.
//                           evalNotNull("img", "ondblclick", getOndblclick(), 
//                                       String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOndblclick(null);
//         }

//         try {
//             setOnfocus((String)ExpressionUtil.
//                        evalNotNull("img", "onfocus", getOnfocus(), 
//                                    String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnfocus(null);
//         }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("img", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("img", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("img", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

//         try {
//             setOnmousedown((String)ExpressionUtil.
//                            evalNotNull("img", "onmousedown", getOnmousedown(), 
//                                        String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmousedown(null);
//         }

//         try {
//             setOnmousemove((String)ExpressionUtil.
//                            evalNotNull("img", "onmousemove",
//                                        getOnmousemove(), 
//                                        String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmousemove(null);
//         }

//         try {
//             setOnmouseout((String)ExpressionUtil.
//                           evalNotNull("img", "onmouseout", getOnmouseout(), 
//                                       String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseout(null);
//         }

//         try {
//             setOnmouseover((String)ExpressionUtil.
//                            evalNotNull("img", "onmouseover",
//                                        getOnmouseover(), 
//                                        String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseover(null);
//         }

//         try {
//             setOnmouseup((String)ExpressionUtil.
//                          evalNotNull("img", "onmouseup", getOnmouseup(), 
//                                      String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnmouseup(null);
//         }

        try {
            setPage((String)ExpressionUtil.
                        evalNotNull("img", "page", getPage(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setPageKey((String)ExpressionUtil.
                        evalNotNull("img", "pageKey", getPageKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPageKey(null);
        }

        try {
            setParamId((String)ExpressionUtil.
                        evalNotNull("img", "paramId", getParamId(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamId(null);
        }

        try {
            setParamName((String)ExpressionUtil.
                        evalNotNull("img", "paramName", getParamName(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String)ExpressionUtil.
                        evalNotNull("img", "paramProperty", getParamProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String)ExpressionUtil.
                        evalNotNull("img", "paramScope", getParamScope(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("img", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                        evalNotNull("img", "scope", getScope(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setSrc((String)ExpressionUtil.
                        evalNotNull("img", "src", getSrc(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setSrc(null);
        }

        try {
            setSrcKey((String)ExpressionUtil.
                        evalNotNull("img", "srcKey", getSrcKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setSrcKey(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("img", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("img", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("img", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("img", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("img", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setUsemap((String)ExpressionUtil.
                     evalNotNull("img", "usemap", getUsemap(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setUsemap(null);
        }

        try {
            setVspace((String)ExpressionUtil.
                     evalNotNull("img", "vspace", getVspace(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setVspace(null);
        }

        try {
            setWidth((String)ExpressionUtil.
                     evalNotNull("img", "width", getWidth(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setWidth(null);
        }
    }
}
