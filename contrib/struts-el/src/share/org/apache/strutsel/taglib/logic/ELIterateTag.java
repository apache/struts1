package org.apache.strutsel.taglib.logic;

import org.apache.struts.taglib.logic.IterateTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELIterateTag extends IterateTag {

    private String   collectionExpr;

    public  String   getCollectionExpr() { return (collectionExpr); }
    public  void     setCollectionExpr(String collectionExpr)
    { this.collectionExpr   = collectionExpr; }

    public void release()
    {
        super.release();
        setCollectionExpr(null);
    }

    public int doStartTag() throws JspException {
        evaluateExpressions();
        return (super.doStartTag());
    }

    private void evaluateExpressions() throws JspException {

        try {
            setCollection(ExpressionUtil.
                          evalNotNull("iterate", "collection",
                                      getCollectionExpr(), 
                                      Object.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setCollection(null);
        }

        try {
            setId((String)ExpressionUtil.
                  evalNotNull("iterate", "id", getId(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setId(null);
        }

        try {
            setIndexId((String)ExpressionUtil.
                       evalNotNull("iterate", "indexId", getIndexId(), 
                                   String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setIndexId(null);
        }

        try {
            setLength((String)ExpressionUtil.
                      evalNotNull("iterate", "length", getLength(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setLength(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("iterate", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setOffset((String)ExpressionUtil.
                      evalNotNull("iterate", "offset", getOffset(), 
                                  String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setOffset(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("iterate", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("iterate", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }

        try {
            setType((String)ExpressionUtil.
                    evalNotNull("iterate", "type", getType(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setType(null);
        }
    }
}
