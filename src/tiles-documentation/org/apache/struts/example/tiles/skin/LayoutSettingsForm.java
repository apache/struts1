//Source file: H:\\TEMP\\generated\\org\\apache\\struts\\example\\tiles\\skin\\LayoutSettingForm.java

package org.apache.struts.example.tiles.skin;

import org.apache.struts.action.ActionForm;

/**
 * Struts form
 */
public class LayoutSettingsForm extends ActionForm
{

    /** Validate value */
  protected String validate;

   /**
    * User selected key value
    */
   private String selected;

   /**
    * Access method for the selectedKey property.
    *
    * @return   the current value of the selectedKey property
    */
   public String getSelected()
   {
      return selected;
   }

   /**
    * Sets the value of the selectedKey property.
    *
    * @param aSelectedKey the new value of the selectedKey property
    */
   public void setSelected(String aSelectedKey)
   {
      selected = aSelectedKey;
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
    selected =  null;
    validate = null;
    }

}
