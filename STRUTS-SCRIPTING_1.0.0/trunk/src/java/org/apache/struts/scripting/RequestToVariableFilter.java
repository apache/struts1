package org.apache.struts.scripting;

// util imports:
import java.util.Enumeration;
import java.util.Properties;

// logging imports:
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// misc imports:
import javax.servlet.http.HttpServletRequest;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;


/**
 *  Takes request parameters and declares variables with them.  If a variable
 *  is already exists with that name, a "_" is prepended to the name.  Both
 *  Strings and arrays are recognized.
 */
public class RequestToVariableFilter implements BSFManagerFilter {

    private static final Log log = LogFactory.getLog(TestFilter.class);

    /**
     *  Initializes the filter.
     *
     * @param name The name of the filter
     * @param props The properties
     */
    public void init(String name, Properties props) {}

    /**
     *  Applies the filter.
     *
     * @param mgr The bsf manager
     * @return The bsf manager
     */
    public BSFManager apply(BSFManager mgr) {
        HttpServletRequest request = (HttpServletRequest)mgr.lookupBean("request");
        String[] values = null;
        String name = null, newName = null;
        Object o = null;
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements(); ) {
            name = (String)e.nextElement();
            o = mgr.lookupBean(name);
            if (o == null) {
                newName = name;
            }
            else {
                newName = "_" + name;
            }

            values = request.getParameterValues(name);
            try {
                if (values.length>1) {
                    mgr.declareBean(newName, values, values.getClass());
                    if (log.isDebugEnabled()) {
                        log.debug("creating array var "+newName);
                    }
                }
                else {
                    mgr.declareBean(newName, values[0], String.class);
                    if (log.isDebugEnabled()) {
                        log.debug("creating string var "+newName);
                    }
                }
            }
            catch (BSFException ex) {
                log.warn("Unable to set variable "+newName, ex);
            }

        }
        if (log.isDebugEnabled()) {
            log.debug("Done filtering");
        }
        return mgr;
    }
}
