/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
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
 
  
package org.apache.struts.scaffold;


/**
 * Generic bean to use with image buttons.
 */
public final class ImageButtonBean extends Object {

    private String x = null;
    private String y = null;

    public String getX() {
        return (this.x);
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
         return (this.y);
    }

    public void setY(String y) {
        this.y = y;
    }

    public boolean isSelected() {
          return ((x!=null) || (y!=null));
    }

} // end ImageButtonBean