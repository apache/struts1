/*
 * Copyright 1999-2002,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shale.examples.mailreader;

import org.apache.struts.examples.mailreader.User;

/**
 * <p>In the absence of a <code>DialogController</code>, provide some
 * properties that can be used to save our state information.</p>
 *
 * $Id$
 */

public class State {
    
    
    // -------------------------------------------------------------- Properties


    /**
     * <p>Host name used to select which subscription to process.</p>
     */
    private String host = null;
    public String getHost() { return this.host; }
    public void setHost(String host) { this.host = host; }


    /**
     * <p>Transaction processing mode.</p>
     */
    private String mode = null;
    public String getMode() { return this.mode; }
    public void setMode(String mode) { this.mode = mode; }


    /**
     * <p>The currently logged on <code>User</code>.</p>
     */
    private User user = null;
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }


}
