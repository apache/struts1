/*
 * $Header: /home/cvs/jakarta-struts/contrib/tiles/src/share/org/apache/struts/taglib/tiles/Attic/UseAttributeTei.java,v 1.1 2001/08/01 14:36:41 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2001/08/01 14:36:41 $
 * $Author: cedric $
 *
 */

package org.apache.struts.taglib.tiles;


import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


/**
 * Implementation of <code>TagExtraInfo</code> for the <b>UseAtribute</b>
 * tag, identifying the scripting object(s) to be made visible.
 *
 */

public final class UseAttributeTei extends TagExtraInfo {


    /**
     * Return information about the scripting variables to be created.
     */
    public VariableInfo[] getVariableInfo(TagData data) {

      String classname = data.getAttributeString("classname");
      if( classname == null )
        classname = "java.lang.Object";
      String id = data.getAttributeString("id");
      if( id == null )
        id = data.getAttributeString("name");;

	return new VariableInfo[] {
	  new VariableInfo(id,
	                   classname,
	                   true,
	                   VariableInfo.AT_END)
	};

    }


}
