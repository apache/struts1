package org.apache.strutsel.taglib.html;

import org.apache.struts.taglib.html.OptionsCollectionTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELOptionsCollectionTag extends OptionsCollectionTag {

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }
    
    private void evaluateExpressions() throws JspException {
        try {
            setFilter(((Boolean)ExpressionUtil.
                       evalNotNull("optionsCollection", "filter",
                                   getFilter() + "", 
                                   Boolean.class, this, pageContext)).
                      booleanValue());
        } catch (NullAttributeException ex) {
            setFilter(false);
        }

        try {
            setLabel((String)ExpressionUtil.
                     evalNotNull("optionsCollection", "label", getLabel(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLabel(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("optionsCollection", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("optionsCollection", "property",
                                    getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setStyle((String)ExpressionUtil.
                     evalNotNull("optionsCollection", "style", getStyle(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyle(null);
        }

        try {
            setStyleClass((String)ExpressionUtil.
                          evalNotNull("optionsCollection", "styleClass",
                                      getStyleClass(), 
                                      String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setStyleClass(null);
        }

//         try {
//             setStyleId((String)ExpressionUtil.
//                        evalNotNull("optionsCollection", "styleId", getStyleId(),
//                                    String.class, this, pageContext));
//         } catch (NullAttributeException ex) {
//             setStyleId(null);
//         }

        try {
            setValue((String)ExpressionUtil.
                     evalNotNull("optionsCollection", "value", getValue(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setValue(null);
        }
    }
}
