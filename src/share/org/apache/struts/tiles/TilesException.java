/*
 * $Header: /home/cvs/jakarta-struts/src/share/org/apache/struts/tiles/TilesException.java,v 1.2 2003/02/27 19:20:51 cedric Exp $
 * $Revision: 1.2 $
 * $Date: 2003/02/27 19:20:51 $
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


package org.apache.struts.tiles;


/**
 * Root class for all Tiles-exceptions.
 * @author Cedric Dumoulin
 */
public class TilesException extends Exception
{


  /**
   * Any "wrapped" exception will be exposed when this is serialized.
   * @serial
   */
  private Exception exception;
  /**
    * Constructor.
    */
  public TilesException()
    {
    super();
    this.exception = null;
  }

  /**
    * Constructor.
    * @param message The error or warning message.
    */
  public TilesException(String message)
    {
    super(message);
    this.exception = null;
  }


  /**
    * Create a new <code>TilesException</code> wrapping an existing exception.
    *
    * <p>The existing exception will be embedded in the new
    * one, and its message will become the default message for
    * the TilesException.</p>
    *
    * @param e The exception to be wrapped.
    */
  public TilesException(Exception e)
  {
    super();
    this.exception = e;
  }


  /**
    * Create a new <code>TilesException</code> from an existing exception.
    *
    * <p>The existing exception will be embedded in the new
    * one, but the new exception will have its own message.</p>
    *
    * @param message The detail message.
    * @param e The exception to be wrapped.
    */
  public TilesException(String message, Exception e)
  {
    super(message);
    this.exception = e;
  }


  /**
    * Return a detail message for this exception.
    *
    * <p>If there is a embedded exception, and if the TilesException
    * has no detail message of its own, this method will return
    * the detail message from the embedded exception.</p>
    *
    * @return The error or warning message.
    */
  public String getMessage ()
  {
    String message = super.getMessage ();

    if (message == null && exception != null) {
      return exception.getMessage();
    } else {
      return message;
    }
  }


  /**
    * Return the embedded exception, if any.
    *
    * @return The embedded exception, or <code>null</code> if there is none.
    */
  public Exception getException ()
  {
    return exception;
  }

}
