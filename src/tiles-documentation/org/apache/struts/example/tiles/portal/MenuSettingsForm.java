/*
 */


package org.apache.struts.example.tiles.portal;


import java.util.List;
import java.util.ArrayList;
import org.apache.struts.action.ActionForm;


/**
 * Action form used to read data from web page form.
 */
public final class MenuSettingsForm extends ActionForm  {

    /** Validate value */
  protected String validate;
    /** empty list used by reset */
  protected String[] empty = {};
    /** list of items selected by user */
  protected String[] selected;

    /**
     * Set selected items
     */
  public void setSelected( String array[] )
    {
    selected = array;
    }

    /**
     * Get selected items
     */
  public String[] getSelected()
    {
    return selected;
    }

    /**
     * Get selected items
     */
  public String[] getSelectedChoices()
    {
    return empty;
    }

   /**
    * Is this form submitted ?
    */
  public boolean isSubmitted()
    {
    return validate != null;
    }

   /**
    * Is this form submitted ?
    */
  public void setValidate( String value)
    {
    this.validate = value;
    }

    /**
     * Reset properties
     */
  public void reset()
    {
    selected =  empty;
    validate = null;
    }
}

