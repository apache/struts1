package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.OptionsTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELOptionsTag extends OptionsTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return(super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {
        try {
            setCollection((String)ExpressionUtil.
                          evalNotNull("checkbox", "collection",
                                      getCollection(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setCollection(null);
        }

        try {
            setFilter(((Boolean)ExpressionUtil.
                       evalNotNull("options", "filter", getFilter() + "", 
                                   Boolean.class, this, pageContext)).
                      booleanValue());
        } catch (NullAttributeException ex) {
            setFilter(false);
        }

        try {
            setLabelName((String)ExpressionUtil.
                         evalNotNull("options", "labelName", getLabelName(), 
                                     String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLabelName(null);
        }

        try {
            setLabelProperty((String)ExpressionUtil.
                             evalNotNull("options", "labelProperty",
                                         getLabelProperty(), 
                                         String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLabelProperty(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("options", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("options", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("options", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("options", "styleClass", getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

//         try {
//             setStyleId((String)ExpressionUtil.
//                        evalNotNull("options", "styleId", getStyleId(),
//                                    String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setStyleId(null);
//         }
    }
}
