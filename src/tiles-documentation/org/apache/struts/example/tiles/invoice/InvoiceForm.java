/*
 */


package org.apache.struts.example.tiles.invoice;


import java.util.Vector;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;


/**
 */

public final class InvoiceForm extends ActionForm {

  /**
   * Shipping address
   */
  private Address shippingAddress;

  /**
   * Bill address
   */
  private Address billAddress;

  /**
   * Invoice total amount
   */
  private double amount;

  /**
   * Customer firstname
   */
  private String firstname;

  /**
   * Customer last name
   */
  private String lastname;

  public InvoiceForm()
    {
    shippingAddress = new Address();
    billAddress = new Address();
    }

  /**
   * Access method for the shippingAddress property.
   *
   * @return   the current value of the shippingAddress property
   */
  public Address getShippingAddress()
   {
    return shippingAddress;}

  /**
   * @return void
   * Sets the value of the shippingAddress property.
   *
   * @param aShippingAddress the new value of the shippingAddress property
   */
  public void setShippingAddress(Address aShippingAddress)
    {
    shippingAddress = aShippingAddress;
    }

  /**
   * Access method for the billAddress property.
   *
   * @return   the current value of the billAddress property
   */
  public Address getBillAddress()
    {
    return billAddress;
    }

  /**
   * @return void
   * Sets the value of the billAddress property.
   *
   * @param aBillAddress the new value of the billAddress property
   */
  public void setBillAddress(Address aBillAddress)
    {
    billAddress = aBillAddress;
    }

  /**
   * Access method for the amount property.
   *
   * @return   the current value of the amount property
   */
  public double getAmount()
    {
    return amount;
    }

  /**
   * @return void
   * Sets the value of the amount property.
   *
   * @param aAmount the new value of the amount property
   */
  public void setAmount(double aAmount)
    {
    amount = aAmount;
    }

  /**
   * Access method for the firstname property.
   *
   * @return   the current value of the firstname property
   */
  public String getFirstname()
    {
    return firstname;
    }

  /**
   * @return void
   * Sets the value of the firstname property.
   *
   * @param aFirstname the new value of the firstname property
   */
  public void setFirstname(String aFirstname)
    {
    firstname = aFirstname;
    }

  /**
   * Access method for the lastname property.
   *
   * @return   the current value of the lastname property
   */
  public String getLastname()
    {
    return lastname;
    }

  /**
   * @return void
   * Sets the value of the lastname property.
   *
   * @param aLastname the new value of the lastname property
   */
  public void setLastname(String aLastname)
    {
    lastname = aLastname;
    }

}

