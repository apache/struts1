package org.apache.struts.example.tiles.portal;

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
