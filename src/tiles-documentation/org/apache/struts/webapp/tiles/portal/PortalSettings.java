/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/portal/PortalSettings.java,v 1.3 2004/01/13 12:48:58 husted Exp $
 * $Revision: 1.3 $
 * $Date: 2004/01/13 12:48:58 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.struts.webapp.tiles.portal;

import java.util.ArrayList;
import java.util.List;

  /**
   * Objects of this class hold portal settings for one user.
   */
   public class PortalSettings
   {
       /** Number of columms*/
     protected int numCols;
       /** List of lists (one per column) */
     protected List lists = new ArrayList();

       /**
        * Get number of columns.
        */
     public int getNumCols()
       {
       return numCols;
       }
       /**
        * Set number of columns
        */
     public void setNumCols( String numCols )
       {
       setNumCols( Integer.parseInt(numCols) );
       }
       /**
        * Set number of columns.
        * Ensure capacity for internal list.
        */
     public void setNumCols( int numCols )
       {
       this.numCols = numCols;
       }
       /**
        * Get list at specified index
        */
     public List getListAt( int index )
       {
       return (List)lists.get(index);
       }

       /**
        * Add a list without checking
        */
     public void addList( List list )
       {
       lists.add( list);
       }

       /**
        * Set list at specified index. Previous list is disguarded.
        * Add empty list if necessary.
        * Indexes go from 0 to numCols-1
        * @param index index of the list to add.
        * @param list list to set.
        */
     public void setListAt( int index, List list )
       {
         // First, ensure capacity
       while( index>lists.size() ) lists.add(new ArrayList());
       lists.add( index, list);
       }

       /**
        * Reset settings
        */
     public void reset()
       {
       numCols = 0;
       lists.clear();
       }

     public String toString()
       {
       return "colCount=" + numCols
              + " , lists=" + lists;
       }
   }
