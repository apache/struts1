/*
 * Copyright 2004 The Apache Software Foundation.
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

package org.apache.shale.view;

import org.apache.shale.DialogController;
import org.apache.shale.ViewController;

/**
 * <p>{@link AbstractViewController} is a convenience base implementation of {@link ViewController}.</p>
 *
 * $Id$
 */

public class AbstractViewController implements ViewController {
    

    // -------------------------------------------------------------- Properties


    private DialogController dialog = null;
    
    
    // Specified by ViewController
    public DialogController getDialog() {

        return this.dialog;

    }


    // Specified by ViewController
    public void setDialog(DialogController dialog) {

        this.dialog = dialog;

    }


    private boolean postBack = false;
    
    
    // Specified by ViewController
    public boolean isPostBack() {
        
        return this.postBack;

    }
    
    
    // Specified by ViewController
    public void setPostBack(boolean postBack) {

        this.postBack = postBack;
        
    }


    // ---------------------------------------------------------- Public Methods


    /**
     * <p>The default implementation releases references to any specified
     * objects that this view is related to.</p>
     */
    public void destroy() {

        this.dialog = null;

    }


    /**
     * <p>The default implementation does nothing.</p>
     */
    public void init() {
    }


    /**
     * <p>The default implementation does nothing.</p>
     */
    public void prepare() {
    }


}
