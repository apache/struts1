package org.apache.struts.example.tiles.dynPortal;

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
