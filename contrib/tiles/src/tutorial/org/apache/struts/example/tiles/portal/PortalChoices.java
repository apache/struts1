package org.apache.struts.example.tiles.portal;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;


/**
 * Instances of this classe hold available Tiles for a portal page.
 *
 * @author Cedric Dumoulin
 */
public class PortalChoices
{
       /** Choices Tiles */
     protected List choices = new ArrayList();
       /** Choices Tiles labels */
     protected List choiceLabels = new ArrayList();

       /**
        * Set list of choices.
        * Labels come from tiles names
        * @param list list of tiles
        */
     public void setChoices( List list)
       {
       setChoices(list, list);
       }

       /**
        * add list to list of choices Tiles
        * Labels come from tiles names
        * @param list list of tiles
        */
     public void addChoices( List list)
       {
       addChoices( list, list);
       }

       /**
        * Set list of choices Tiles.
        * Previous list is disguarded.
        * @param list list of tiles
        * @param labels corresponding labels. List size must be the same as list.
        * If labels is null, use list of tiles.
        * @throws ArrayIndexOutOfBoundsException if list and labels aren't the same size.
        */
     public void setChoices( List list, List labels)
         throws ArrayIndexOutOfBoundsException
       {
         // If no labels, use list keys
       if( labels == null )
         labels = list;
         // Check sizes
       if( list.size() != labels.size() )
         {// error
         System.out.println( "Error : list and labels size must be the same." );
         throw new java.lang.ArrayIndexOutOfBoundsException( "List of choices and list of labels must be of the same size" );
         }
       this.choices = list;
       choiceLabels = labels;
       }

       /**
        * add list and labels to list of choices Tiles.
        * If labels is null, use keys list as labels.
        * @list list of choice keys to add
        * @param labels corresponding labels. List size must be the same as list.
        * If labels is null, use list of tiles.
        * @throws ArrayIndexOutOfBoundsException if list and labels aren't the same size.
        */
     public void addChoices( List list, List labels)
         throws ArrayIndexOutOfBoundsException
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
         throw new java.lang.ArrayIndexOutOfBoundsException( "List of choices and list of labels must be of the same size" );
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
        * Get list of labels for Tiles
        */
     public List getChoiceLabels( )
       {
       return choiceLabels;
       }

       /**
        * Get label for specified Tile, identified by its key.
        * @param key Tile key
        */
     public String getChoiceLabel( Object key )
       {
       int index = choices.indexOf( key );
       if(index==-1)
         return null;
       return (String)choiceLabels.get(index);
       }

       /**
        * Get list of labels for Tile keys passes as parameters
        * @param choiceKeys List of keys to search for labels.
        */
     public List getChoiceLabelsForKeys( List choiceKeys )
       {
       List listLabels = new ArrayList();

       Iterator i = choiceKeys.iterator();
       while(i.hasNext())
         {
         Object key = i.next();
         listLabels.add( getChoiceLabel(key) );
         } // end loop
       return listLabels;
       }

       /**
        * Check keys passed in parameters, and add it in returned list if available
        * in choices.
        * If a key denote a special key, create appropriate 'definition'.
        * @keys array of keys to add to list.
        */
     public List checkKeys( String keys[] )
       {
       List list = new ArrayList();

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
       return list;
       }

       /**
        * Set labels for choices Tiles.
        */
     protected void setChoiceLabels( List list)
       {
       this.choiceLabels = list;
       }
       /**
        * add list to list of choices Tiles
        */
     protected void addChoiceLabels( List list)
       {
       if(choiceLabels== null)
         {
         setChoiceLabels(list);
         return;
         }
       choiceLabels.addAll(list);
       }

}