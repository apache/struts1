/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/AddTagParent.java,v 1.2 2001/12/27 17:35:37 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2001/12/27 17:35:37 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import javax.servlet.jsp.JspException;

/**
 * Tag Class implementing this interface can contains nested PutTag.
 * This interface define a method calls by nested tag.
 */
public interface AddTagParent {
    /**
     * Process the nested tag.
     * @param nestedTag Nested to process.
     */
    void processNestedTag(AddTag nestedTag) throws JspException;
}
