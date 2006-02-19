package org.apache.struts.scripting;

// util imports:
import java.util.Properties;

// logging imports:
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// misc imports:
import org.apache.bsf.BSFManager;


/**
 *  Tests to make sure the filtering system is working
 */
public class TestFilter implements BSFManagerFilter {

    private static final Log log = LogFactory.getLog(TestFilter.class);

    /**
     *  Initializes the filter
     *
     * @param name The name of the filter
     * @param props The properties
     */
    public void init(String name, Properties props) {
        log.info("Initializing TestFilter");
    }

    /**
     *  Applies the filter
     *
     * @param mgr The bsf manager
     * @return The bsf manager
     */
    public BSFManager apply(BSFManager mgr) {
        log.info("Filtering in TestFilter");
        return mgr;
    }
}
