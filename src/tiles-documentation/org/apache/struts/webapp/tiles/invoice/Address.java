/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/invoice/Address.java,v 1.1 2002/07/11 15:35:21 cedric Exp $
 * $Revision: 1.1 $
 * $Date: 2002/07/11 15:35:21 $
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

package org.apache.struts.webapp.tiles.invoice;

/**
 * An address.
 */
public class Address
{

  /**
   * Street part 1
   */
  private String street1;

  /**
   * Street part 2
   */
  private String street2;

  /**
   * City
   */
  private String city;

  /**
   * Country
   */
  private String country;

  /**
   * Zip Code
   */
  private String zipCode;

  public Address()
  {
  }

  /**
* Access method for the street1 property.
*
* @return   the current value of the street1 property
   */
  public String getStreet1() {
    return street1;}

  /**
   * @return void
* Sets the value of the street1 property.
*
* @param aStreet1 the new value of the street1 property
   */
  public void setStreet1(String aStreet1) {
    street1 = aStreet1;}

  /**
* Access method for the street2 property.
*
* @return   the current value of the street2 property
   */
  public String getStreet2() {
    return street2;}

  /**
   * @return void
* Sets the value of the street2 property.
*
* @param aStreet2 the new value of the street2 property
   */
  public void setStreet2(String aStreet2) {
    street2 = aStreet2;}

  /**
* Access method for the city property.
*
* @return   the current value of the city property
   */
  public String getCity() {
    return city;}

  /**
   * @return void
* Sets the value of the city property.
*
* @param aCity the new value of the city property
   */
  public void setCity(String aCity) {
    city = aCity;}

  /**
* Access method for the country property.
*
* @return   the current value of the country property
   */
  public String getCountry() {
    return country;}

  /**
   * @return void
* Sets the value of the country property.
*
* @param aCountry the new value of the country property
   */
  public void setCountry(String aCountry) {
    country = aCountry;}

  /**
* Access method for the zipCode property.
*
* @return   the current value of the zipCode property
   */
  public String getZipCode() {
    return zipCode;}

  /**
   * @return void
* Sets the value of the zipCode property.
*
* @param aZipCode the new value of the zipCode property
   */
  public void setZipCode(String aZipCode) {
    zipCode = aZipCode;}
}
