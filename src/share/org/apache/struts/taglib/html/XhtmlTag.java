package org.apache.struts.taglib.html;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.Globals;

/**
 * This tag tells all other html taglib tags to render themselves in xhtml.  It has no 
 * attributes; it's presence in a page turns on xhtml.
 * <p>
 * Example:<br/>
 * &lt;html:xhtml/&gt;
 * </p>
 * 
 * @author David Graham
 */
public class XhtmlTag extends TagSupport {

    /**
     * Constructor for XhtmlTag.
     */
    public XhtmlTag() {
        super();
    }

    /**
     * @see javax.servlet.jsp.tagext.Tag#doEndTag()
     */
    public int doEndTag() throws JspException {
        this.pageContext.setAttribute(Globals.XHTML_KEY, "true", this.pageContext.PAGE_SCOPE);
        return EVAL_PAGE;
    }

}
