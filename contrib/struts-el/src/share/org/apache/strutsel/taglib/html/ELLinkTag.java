package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.LinkTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELLinkTag extends LinkTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAccesskey((String)ExpressionUtil.
                         evalNotNull("link", "accessKey", getAccesskey(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAccesskey(null);
        }

        try {
            setAnchor((String)ExpressionUtil.
                   evalNotNull("link", "anchor", getAnchor(), 
                               String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAnchor(null);
        }

        try {
            setForward((String)ExpressionUtil.
                      evalNotNull("link", "forward", getForward(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setHref((String)ExpressionUtil.
                      evalNotNull("link", "href", getHref(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHref(null);
        }

        try {
            setIndexed(((Boolean)ExpressionUtil.
                        evalNotNull("link", "indexed", getIndexed() + "", 
                                    Boolean.class, this, pageContext)).
                       booleanValue());
        } catch (NullAttributeException ex) {
            setIndexed(false);
        }

        try {
            setIndexId((String)ExpressionUtil.
                      evalNotNull("link", "indexId", getIndexId(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setIndexId(null);
        }

        try {
            setLinkName((String)ExpressionUtil.
                      evalNotNull("link", "linkName", getLinkName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLinkName(null);
        }

        try {
            setName((String)ExpressionUtil.
                      evalNotNull("link", "name", getName(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOnblur((String)ExpressionUtil.
                      evalNotNull("link", "onblur", getOnblur(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnblur(null);
        }

//         try {
//             setOnchange((String)ExpressionUtil.
//                         evalNotNull("link", "onchange", getOnchange(), 
//                                     String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setOnchange(null);
//         }

        try {
            setOnclick((String)ExpressionUtil.
                       evalNotNull("link", "onclick", getOnclick(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnclick(null);
        }

        try {
            setOndblclick((String)ExpressionUtil.
                          evalNotNull("link", "ondblclick", getOndblclick(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOndblclick(null);
        }

        try {
            setOnfocus((String)ExpressionUtil.
                       evalNotNull("link", "onfocus", getOnfocus(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnfocus(null);
        }

        try {
            setOnkeydown((String)ExpressionUtil.
                         evalNotNull("link", "onkeydown", getOnkeydown(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeydown(null);
        }

        try {
            setOnkeypress((String)ExpressionUtil.
                          evalNotNull("link", "onkeypress", getOnkeypress(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeypress(null);
        }

        try {
            setOnkeyup((String)ExpressionUtil.
                       evalNotNull("link", "onkeyup", getOnkeyup(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnkeyup(null);
        }

        try {
            setOnmousedown((String)ExpressionUtil.
                           evalNotNull("link", "onmousedown", getOnmousedown(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousedown(null);
        }

        try {
            setOnmousemove((String)ExpressionUtil.
                           evalNotNull("link", "onmousemove",
                                       getOnmousemove(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmousemove(null);
        }

        try {
            setOnmouseout((String)ExpressionUtil.
                          evalNotNull("link", "onmouseout", getOnmouseout(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseout(null);
        }

        try {
            setOnmouseover((String)ExpressionUtil.
                           evalNotNull("link", "onmouseover",
                                       getOnmouseover(), 
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseover(null);
        }

        try {
            setOnmouseup((String)ExpressionUtil.
                         evalNotNull("link", "onmouseup", getOnmouseup(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOnmouseup(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("link", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setPage((String)ExpressionUtil.
                        evalNotNull("link", "page", getPage(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setParamId((String)ExpressionUtil.
                        evalNotNull("link", "paramId", getParamId(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamId(null);
        }

        try {
            setParamName((String)ExpressionUtil.
                        evalNotNull("link", "paramName", getParamName(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String)ExpressionUtil.
                        evalNotNull("link", "paramProperty", getParamProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String)ExpressionUtil.
                        evalNotNull("link", "paramScope", getParamScope(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                     evalNotNull("link", "property", getProperty(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("link", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("link", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("link", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("link", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTabindex((String)ExpressionUtil.
                        evalNotNull("link", "tabindex", getTabindex(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTabindex(null);
        }

        try {
            setTarget((String)ExpressionUtil.
                        evalNotNull("link", "target", getTarget(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTarget(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("link", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("link", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setTransaction(((Boolean)ExpressionUtil.
                            evalNotNull("link", "transaction",
                                        getTransaction() + "", 
                                        Boolean.class, this, pageContext)).
                           booleanValue());
        } catch (NullAttributeException ex) {
            setTransaction(false);
        }
    }
}
