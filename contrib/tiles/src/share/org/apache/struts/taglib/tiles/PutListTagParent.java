/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/PutListTagParent.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;

import javax.servlet.jsp.JspException;

/**
 * Tag Class implementing this interface can contains nested PutTag.
 * This interface define a method calls by nested tag.
 */
public interface PutListTagParent {
  /**
   * Add an attribute to container.
   * @param nestedTag Nested PutTag defining the attribute.
   */
  void processNestedTag(PutListTag nestedTag) throws JspException;

}
