/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/dynPortal/PortalSettings.java,v 1.1 2002/07/11 15:35:20 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/07/11 15:35:20 $
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
 *    any, must include the following acknowlegement:
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
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
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

package org.apache.struts.webapp.tiles.dynPortal;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

  /**
   * Objects of this class hold portal settings for one user.
   */
   public class PortalSettings
   {
       /** Number of columms*/
     protected int numCols;
       /** List of lists (one per column) */
     protected List lists = new ArrayList();
       /** Choices Tiles */
     protected List choices = new ArrayList();
       /** Choices Tiles labels */
     protected List choiceLabels = new ArrayList();

       /**
        * Get label for specified Tile, identified by its key.
        */
     public String getLabel( Object key )
       {
       int index = choices.indexOf( key );
       return (String)choiceLabels.get(index);
       }

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
       this.numCols = Integer.parseInt(numCols);
       }
       /**
        * Set number of columns
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
        * Get labels for list at specified index.
        */
     public List getListLabelAt( int index )
       {
       List listLabels = new ArrayList();
       List list = getListAt(index);

       Iterator i = list.iterator();
       while(i.hasNext())
         {
         Object key = i.next();
         listLabels.add( getLabel(key) );
         } // end loop
       return listLabels;
       }

       /**
        * Add a list without checking
        */
     public void addList( List list )
       {
       lists.add( list);
       }

       /**
        * Set list of choices Tiles
        */
     public void setChoices( List list)
       {
       setChoices(list, list);
       }

       /**
        * add list to list of choices Tiles
        */
     public void addChoices( List list)
       {
       addChoices( list, list);
       }

       /**
        * Set list of choices Tiles
        */
     public void setChoices( List list, List labels)
       {
         // If no labels, use list keys
       if( labels == null )
         labels = list;
         // Check sizes
       if( list.size() != labels.size() )
         {// error
         System.out.println( "Error : list and labels size must be the same." );
         }
       this.choices = list;
       choiceLabels = labels;
       }

       /**
        * add list and labels to list of choices Tiles.
        * If labels is null, use keys list as labels.
        * @list list of choice keys to add
        * @labels corresponding labels (list size must be the same as list).
        */
     public void addChoices( List list, List labels)
       {
         // If no labels, use list keys
       if( labels == null )
         labels = list;
         // Check sizes
        if(choices== null)
         {
         setChoices(list, labels);
         return;
         }

       if( list.size() != labels.size() )
         {// error
         System.out.println( "Error : list and labels size must be the same." );
         }
       choices.addAll(list);
       choiceLabels.addAll(labels);
       }

       /**
        * Get list of choices Tiles
        */
     public List getChoices( )
       {
       return choices;
       }

       /**
        * Set labels for choices Tiles.
        */
     public void setChoiceLabels( List list)
       {
       this.choiceLabels = list;
       }
       /**
        * add list to list of choices Tiles
        */
     public void addChoiceLabels( List list)
       {
       if(choiceLabels== null)
         {
         setChoiceLabels(list);
         return;
         }
       choiceLabels.addAll(list);
       }
       /**
        * Get list of choices Tiles
        */
     public List getChoiceLabels( )
       {
       return choiceLabels;
       }

       /**
        * Reset list at specified index, and fill it with keys from array.
        * Keys are added only if they are in the choices list.
        * Special keys are transformed in appropriate 'definition'.
        * @index index of the list to add.
        * @keys array of keys to initialize list.
        */
     public void resetListAt( int index, String keys[] )
       {
       List list = getListAt(index);
       list.clear();
       addListAt(index, keys);
       }
       /**
        * Add keys from array to list at specified index.
        * Keys are added only if they are in the choices list.
        * Special keys are transformed in appropriate 'definition'.
        * @index index of the list to add.
        * @keys array of keys to add to list.
        */
     public void addListAt( int index, String keys[] )
       {
         // First, ensure capacity
       while( index>lists.size()-1 ) lists.add(new ArrayList());

       List list = getListAt(index);;
         // add keys to list
       for(int i=0;i<keys.length;i++)
         {
         String key = keys[i];
         if( key.indexOf( '@' )>0 )
           { // special key
           }
         if( choices.contains( key ) )
           { // ok, add it
           list.add( key );
           }
         } // end loop
       lists.add( list);
       }

   }
