/*
 * Created on Jan 31, 2005
 * Author:jmitchell
 *
 */
package org.apache.struts.apps.mailreader.dao.impl.memory;

import org.apache.struts.apps.mailreader.dao.User;
import org.apache.struts.apps.mailreader.dao.impl.AbstractSubscription;


/**
 * @author jmitchell
 *
 */
public class MemorySubscription extends AbstractSubscription{

    public MemorySubscription(User user, String host) {
        super(user, host);
    }

}
