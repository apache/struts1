package org.apache.struts.tiles.beans;

import java.io.Serializable;


  /**
   * A bean MenuItem interface.
   *
   */
public interface MenuItem extends Serializable
{
    /**
     * Set value property
     */
  public void setValue(String value);

    /**
     * Get value property
     */
  public String getValue();

    /**
     * Set link property
     */
  public void setLink(String link);

    /**
     * Get link property
     */
  public String getLink();

    /**
     * Set icon property
     */
  public void setIcon(String link);

    /**
     * Get icon property
     */
  public String getIcon();

    /**
     * Set tooltip property
     */
  public void setTooltip(String link);

    /**
     * Get tooltip property
     */
  public String getTooltip();
}