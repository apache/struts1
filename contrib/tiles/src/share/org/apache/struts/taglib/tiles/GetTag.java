/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/GetTag.java,v 1.4 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.4 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import org.apache.struts.tiles.ComponentContext;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


/**
 * This is the tag handler for &lt;template:get&gt;, which gets
 * content from the request scope and either includes the content or prints
 * it, depending upon the value of the content's direct attribute.
 *
 * This tag is intended to be compatible with the same tag from Templates (David Geary).
 * Implementation extends InsertTag, for facility (no so well).
 * The only difference is the default value of attribute 'ignore', which is true
 * for this tag (default behavior of David Geary's templates).
 */
public class GetTag extends InsertTag {


    /**
     * Constructor.
     * Set default value for 'isErrorIgnored'.
     */
    public GetTag() {
        isErrorIgnored = true;
    }

    /**
     * Release all allocated resources.
     */
    public void release() {

        super.release();
        isErrorIgnored = true;
    }


}
