/*
 * $Header: /home/cvs/jakarta-struts/src/tiles-documentation/org/apache/struts/webapp/tiles/dynPortal/PortalPrefsForm.java,v 1.3 2004/01/13 12:48:57 husted Exp $
 * $Revision: 1.3 $
 * $Date: 2004/01/13 12:48:57 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
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
 *    any, must include the following acknowledgement:
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
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;


/**
 */

public final class PortalPrefsForm extends ActionForm  {

    /** Validate value */
  protected String validate;
    /** empty list used by reset */
  protected String[] empty = {};
    /** list of "remaining" choices */
  protected String[] remaining = empty;
    /** list of user columns */
  protected List columns = new ArrayList();
    /** list of user columns labels */
  protected List columnLabels = new ArrayList();
    /** list of columns selected by user */
  protected List newCols = new ArrayList();

    /** Choice list */
  protected List choice;
    /** Choice list labels */
  protected List choiceLabels;
    /** Is initialized ? */
  protected boolean initialized = false;


    /**
     * Set col
     */
  public void setCol( int index, List col )
    {
    columns.set( index, col);
    }

    /**
     * Add col
     */
  public void addCol( List col )
    {
    columns.add( col);
    }

    /**
     * Get col Labels
     */
  public List getColLabels(int index)
    {
    return (List)columnLabels.get(index);
    }

    /**
     * Set col Labels
     */
  public void setColLabels( int index, List col )
    {
    columnLabels.set( index, col);
    }

    /**
     * Add col Labels
     */
  public void addColLabels( List col )
    {
    columnLabels.add( col);
    }

    /**
     * Get col
     */
  public List getCol(int index)
    {
    return (List)columns.get(index);
    }

    /**
     * Set col Labels
     */
  public void setNewCol( int index, String list[] )
    {
      // ensure capacity
    while( index>=newCols.size())newCols.add(null);
    newCols.set( index, list);
    }

    /**
     * Get col
     */
  public String[] getNewCol(int index)
    {
    if(newCols==null || index>=newCols.size())
      return null;
    return (String[])newCols.get(index);
    }

    /**
     * Get number of columns
     */
  public int getNumCol()
    {
    return newCols.size();
    }

    /**
     * Set list1
     */
  public void setL1( String array[] )
    {
    setNewCol(1, array);
    }
    /**
     * Set list1
     */
  public String[] getL1()
    {
    return getNewCol(1);
    }

    /**
     * Set list1
     */
  public void setL0( String array[] )
    {
    setNewCol(0, array);
    }
    /**
     * Set list1
     */
  public String[] getL0()
    {
    return getNewCol(0);
    }
    /**
     * Set list1
     */
  public void setRemaining( String array[] )
    {
    remaining = array;
    }
    /**
     * Set list1
     */
  public String[] getRemaining()
    {
    return remaining;
    }


    /**
     * Set list1
     */
  public void setChoices( List list )
    {
    choice = list;
    }
    /**
     * Set list1
     */
  public void setChoiceLabels( List list )
    {
    choiceLabels = list;
    }
    /**
     * Set list1
     */
  public List getChoices()
    {
    return choice;
    }
    /**
     * Set list1
     */
  public List getChoiceLabels()
    {
    return choiceLabels;
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
    remaining =  empty;
    validate = null;
    columns.clear();
    columnLabels.clear();
    newCols.clear();
    }
    /**
     * Initialized flag
     */
  public boolean isInitialized()
    {
    return initialized;
    }
    /**
     * Initialized flag
     */
  public void setInitialized( boolean isInitialized)
    {
    initialized = isInitialized;
    }
}

