package org.apache.strutsel.taglib.bean;

import org.apache.struts.taglib.bean.SizeTag;
import javax.servlet.jsp.JspException;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

public class ELSizeTag extends SizeTag {

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
                          evalNotNull("size", "collection",
                                      getCollectionExpr(), 
                                      Object.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setCollection(null);
        }

        try {
            setId((String)ExpressionUtil.
                  evalNotNull("size", "id", getId(), 
                              String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setId(null);
        }

        try {
            setName((String)ExpressionUtil.
                    evalNotNull("size", "name", getName(), 
                                String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setName(null);
        }

        try {
            setProperty((String)ExpressionUtil.
                        evalNotNull("size", "property", getProperty(), 
                                    String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setProperty(null);
        }

        try {
            setScope((String)ExpressionUtil.
                     evalNotNull("size", "scope", getScope(), 
                                 String.class, this, pageContext));
        } catch (NullAttributeException ex) {
            setScope(null);
        }
    }
}
