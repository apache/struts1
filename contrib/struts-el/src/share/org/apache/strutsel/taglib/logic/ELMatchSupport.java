package org.apache.strutsel.taglib.logic;

import org.apache.struts.util.MessageResources;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.util.RequestUtils;

class ELMatchSupport {
    public static boolean condition(boolean           desired,
                                    String            expr,
                                    String            value,
                                    String            location,
                                    MessageResources  messages,
                                    PageContext       pageContext)
        throws JspException
    {
        boolean   result   = false;

        if (expr != null) {
            // Perform the comparison requested by the location attribute
            boolean matched = false;
            if (location == null) {
                matched = (expr.indexOf(value) >= 0);
            } else if (location.equals("start")) {
                matched = expr.startsWith(value);
            } else if (location.equals("end")) {
                matched = expr.endsWith(value);
            } else {
                JspException e = new JspException
                    (messages.getMessage("logic.location", location));
                RequestUtils.saveException(pageContext, e);
                throw e;
            }

            result   = (matched == desired);
        }

        return (result);
    }
}
