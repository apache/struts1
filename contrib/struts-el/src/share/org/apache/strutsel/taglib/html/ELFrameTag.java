package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.FrameTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELFrameTag extends FrameTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setAnchor((String)ExpressionUtil.
                      evalNotNull("frame", "anchor", getAnchor(),
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setAnchor(null);
        }

        try {
            setForward((String)ExpressionUtil.
                       evalNotNull("frame", "forward", getForward(),
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setForward(null);
        }

        try {
            setFrameborder((String)ExpressionUtil.
                           evalNotNull("frame", "frameborder", getFrameborder(),
                                       String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFrameborder(null);
        }

        try {
            setFrameName((String)ExpressionUtil.
                         evalNotNull("frame", "frameName", getFrameName(),
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setFrameName(null);
        }

        try {
            setHref((String)ExpressionUtil.
                    evalNotNull("frame", "href", getHref(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setHref(null);
        }

        try {
            setLongdesc((String)ExpressionUtil.
                        evalNotNull("frame", "longdesc", getLongdesc(),
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLongdesc(null);
        }

        try {
            setMarginheight(((Integer)ExpressionUtil.
                             evalNotNull("frame", "marginheight",
                                         getMarginheight() + "",
                                         Integer.class, this, pageContext)).
                            intValue());
        } catch (NullAttributeException ex) {
            setMarginheight(0);
        }

        try {
            setMarginwidth(((Integer)ExpressionUtil.
                            evalNotNull("frame", "marginwidth",
                                        getMarginwidth() + "",
                                        Integer.class, this, pageContext)).
                           intValue());
        } catch (NullAttributeException ex) {
            setMarginwidth(0);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("frame", "name", getName(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setNoresize(((Boolean)ExpressionUtil.
                         evalNotNull("frame", "noresize", getNoresize() + "",
                                     Boolean.class, this, pageContext)).
                        booleanValue());
        } catch (NullAttributeException ex) {
            setNoresize(false);
        }

        try {
            setPage((String)ExpressionUtil.
                    evalNotNull("frame", "page", getPage(),
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setPage(null);
        }

        try {
            setParamId((String)ExpressionUtil.
                       evalNotNull("frame", "paramId", getParamId(),
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamId(null);
        }

        try {
            setParamName((String)ExpressionUtil.
                         evalNotNull("frame", "paramName", getParamName(),
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamName(null);
        }

        try {
            setParamProperty((String)ExpressionUtil.
                             evalNotNull("frame", "paramProperty",
                                         getParamProperty(),
                                         String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamProperty(null);
        }

        try {
            setParamScope((String)ExpressionUtil.
                          evalNotNull("frame", "paramScope", getParamScope(),
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setParamScope(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("frame", "property", getProperty(),
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("frame", "scope", getScope(),
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setScrolling((String)ExpressionUtil.
                         evalNotNull("frame", "scrolling",
                                     getScrolling(),
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScrolling(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("frame", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("frame", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

        try {
            setStyleId((String)ExpressionUtil.
                       evalNotNull("frame", "styleId", getStyleId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleId(null);
        }

        try {
            setTitle((String)ExpressionUtil.
                     evalNotNull("frame", "title", getTitle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitle(null);
        }

        try {
            setTitleKey((String)ExpressionUtil.
                        evalNotNull("frame", "titleKey", getTitleKey(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setTitleKey(null);
        }

        try {
            setTransaction(((Boolean)ExpressionUtil.
                            evalNotNull("frame", "transaction",
                                        getTransaction() + "",
                                        Boolean.class, this, pageContext)).
                           booleanValue());
        } catch (NullAttributeException ex) {
            setTransaction(false);
        }
    }
}
