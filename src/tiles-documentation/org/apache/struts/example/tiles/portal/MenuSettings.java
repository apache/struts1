package org.apache.struts.example.tiles.portal;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

  /**
   * Objects of this class hold menu settings for one user.
   */
   public class MenuSettings
   {
       /** List of items */
     protected List items = new ArrayList();

       /**
        * Get list of items
        */
     public List getItems( )
       {
       return items;
       }

       /**
        * Add an item to the list
        */
     public void addItem( Object item )
       {
       items.add( item );
       }

       /**
        * Add all items to the list.
        */
     public void addItems( List list )
       {
       items.addAll( list );
       }

       /**
        * Reset settings
        */
     public void reset()
       {
       items.clear();
       }

     public String toString()
       {
       return "items=" + items;
       }
   }
