package org.apache.struts.scaffold;


import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// import org.apache.struts.util.BeanUtils; // Struts 1.0.x
// import org.apache.struts.util.PropertyUtils; // Struts 1.0.x
import org.apache.commons.beanutils.BeanUtils; // Struts 1.1
import org.apache.commons.beanutils.PropertyUtils; // Struts 1.1

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm; // Struts 1.1
// import com.wintecinc.struts.action.ValidatorForm; // Struts 1.0.x

import org.apache.commons.scaffold.lang.ChainedException;


/**
 * Enhanced base ActionForm.
 * @author Ted Husted
 * @version $Revision: 1.6 $ $Date: 2002/09/12 20:39:00 $
 * @todo Change from BeanUtil.populate to copyProperties
 * in 1.1 version.
 */
public class BaseForm extends ActionForm {


// ----------------------------------------------------------- Properties

    /**
     * The network address making this request.
     * <p>
     * This is the value returned by reqest.getRemoteAddr.
     * It is provided so that this can be logged by components on
     * the business tier if needed.
     * This property is maintained automatically through the
     * <code>reset</code> method.
     */
    private String remoteAddr = null;

    /**
     * Return our remoteAddr property.
     */
    public String getRemoteAddr() {
        return (this.remoteAddr);
    }


    /**
     * Set our remoteAddr property.
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }


    /**
     * Our locale property.
     * <p>
     * If the formBean instance is mutable, this is set to the Struts
     * session locale whenever <code>reset()</code> is called. to update
     * the session locale, use <code>putSessionLocale()</code>.
     * <p>
     * The properties refer to this as the "SessionLocale" so as to avoid
     * naming/signature conflicts with business beans which may also
     * maintain a Locale property.
     */
    private Locale locale = null;


    /**
     * Set our locale property.
     *
     */
    public void setSessionLocale(Locale locale) {
        this.locale = locale;
    }


    /**
     * Retrieve our locale property.
     */
    public Locale getSessionLocale() {
        return this.locale;
    }


    /**
     * The mutable state.
     * <p>
     * To avoid autopopulation when forwarding beans between actions,
     * set mutable to be true and be sure all setters observe the
     * mutable state.
     * <p>
     * (<code>if (isMutable()) this.field = field;</code>).
     * subject to autopopulation.
     */
    private boolean mutable = true;


    /**
     * Set the mutable state.
     */
    public void setMutable(boolean mutable) {
        this.mutable = mutable;
    }


    /**
     * Retrieve the mutable state.
     */
    public boolean isMutable() {
        return this.mutable;
    }


    /**
     * The dispatch property.
     * <p>
     * This can be set by a JavaScript buttonto indicate which task should
     * be performed (or dispatched) by an action.
     * Observes the bean's mutable state.
     */
    public String dispatch = null;


    /**
     * Set dispatch.
     */
    public void setDispatch(String dispatch) {
        if (isMutable()) this.dispatch = dispatch;
    }


    /**
     * Get the dispatch.
     */
    public String getDispatch() {
        return this.dispatch;
    }


// --------------------------------------------------------- Public Methods

    /**
     * If bean is set to mutable, calls <code>resetSessionLocale</code>
     * and <code>setRemoteAddr</code>.
     *
     * Subclasses resetting their own fields should observe the mutable
     * state (<code>if (isMutable()) ...</code>).
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(
            ActionMapping mapping,
            HttpServletRequest request) {

        if (isMutable()) {

            resetSessionLocale(request);
            setRemoteAddr(request.getRemoteAddr());
        }

    } // end reset


    /**
     * Return an empty ActionErrors or the result of calling
     * the superclass validate. Will not return null.
     */
    public ActionErrors validate(ActionMapping mapping,
        HttpServletRequest request) {

        ActionErrors errors = super.validate(mapping,request);
        if (null==errors) errors = new ActionErrors();
        return errors;
    }


    /**
     * Reset our locale property to the locale object found in
     * the session associated with this request.
     */
    protected void resetSessionLocale(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session!=null) {

            setSessionLocale((Locale)
                session.getAttribute(Action.LOCALE_KEY));

        }
        else {

            setSessionLocale(Locale.getDefault());
        }

    } // end resetSessionLocale


    /**
     * Change the locale property in the session to our locale object,
     * or the default Locale if ours is null.
     */
    protected void putSessionLocale(HttpServletRequest request) {

        Locale locale = getSessionLocale();
        if (null==locale) locale = Locale.getDefault();

        request.getSession(true).setAttribute(Action.LOCALE_KEY,locale);

    } // end putSessionLocale


    /**
     * Display the user's locale setting or the default locale.
     */
     public String getLocaleDisplay() {

         Locale locale = getSessionLocale();
         if (null==locale) locale = Locale.getDefault();
         return locale.getDisplayName();

     } // end getLocaleDisplay


    /**
     * Set our locale to given ISO Language Code.
     * An empty String is used for the country.
     * <p>
     * Mainly provided for completeness.
     */
     public void setLocaleDisplay(String language) {
         setSessionLocale(new Locale(language,EMPTY));
     }


    /**
     * A static, empty String used by isBlank.
     */
     private static String EMPTY = "";


    /**
     * Convenience method to check for a null or empty String.
     *
     * @param s The sting to check
     */
    protected boolean isBlank(String s) {
        return ((s==null) || (EMPTY.equals(s)));
    }


    /**
     * Return map of properties for this bean.
     * Base method uses <code>PropertyUtils.describe</code>.
     * Override if some properties should not be transfered
     * this way, or a property name should be altered.
     * This will return the actual public properties.
     *
     * @exception Throws Exception on any error.
     */
    public Map describe() throws Exception {

        try {
            return PropertyUtils.describe(this);
        } catch (Throwable t) {
            throw new ChainedException(t);
      }

    } // end describe


    /**
     * Set properties from given object.
     * Base method uses <code>BeanUtils.copyProperties</code> and
     * <code>PropertyUtils.describe</code>.
     *
     * @param o The object to use to populate this object.
     * @exception Throws Exception on any error.
     */
    public void set(Object o) throws Exception {

        try {
            BeanUtils.copyProperties(this,o);
        } catch (Throwable t) {
            throw new ChainedException(t);
      }

    } // end set


    /**
     * Populate matching properties on given object.
     * Base method uses <code>BeanUtils.copyProperties</code> and
     * <code>describe()</code>.
     *
     * @param o The object to populate from this object.
     * @exception Throws Exception on any error.
     */
    public void populate(Object o) throws Exception {

        try {
            BeanUtils.copyProperties(o,describe());
        } catch (Throwable t) {
            throw new ChainedException(t);
      }

    } // end populate


    /**
     * // :FIXME: Needs testing. Works OK without a profile bean =:o)
     * Merge a profile bean with this bean to provide a unified map
     * of the combined parameters. Will on add properties to the map
     * from the profile bean if matching property on this bean is
     * blank or null (which includes absent).
     * The result is a union of the properties, with the this
     * bean's non-blank properties having precedence over the
     * profile's properties. The profile is a base that this bean
     * can override on the fly. If this bean does not supply a
     * property, then the profile property is used.
     * <p>
     * If profile is null, a map of this bean's properties is returned.
     * <p>
     * The profile can be any JavaBean.
     * <p>
     * This method is forwardly-compatible with BaseMapForm.
     * For an instance of BaseMapForm, getMap() is used; otherwise
     * describe() or PropertyUtils.describe() is used.
     *
     * @fixme Needs testing. Works OK without a profile bean =:o)
     * @param profile The profile bean, if any
     * @throws Exception if error transfering data to map
     */
    protected Map merge(Object profile) throws Exception {

        Map formMap = null;
        if (this instanceof BaseMapForm) {
            BaseMapForm form = (BaseMapForm) this;
            formMap = form.getMap();
        }
        else {
            formMap = describe();
        }

        if (profile!=null) {

            Map userMap = null;
            if (profile instanceof BaseMapForm) {
                BaseMapForm form = (BaseMapForm) profile;
                userMap = form.getMap();
            }
            else if (profile instanceof BaseForm) {
                BaseForm form = (BaseForm) profile;
                userMap = form.describe();
            }
            else {
                userMap = PropertyUtils.describe(this);
            }

                // Add user element to formMap if form element is null or blank
            Iterator i = userMap.keySet().iterator();
            while (i.hasNext()) {
                String key = (String) i.next();
                String formValue = (String) formMap.get(key);
                if (isBlank(formValue)) {
                    formMap.put(key,userMap.get(key));
                }
            }
        }

        return formMap;

    } // end merge

// end BaseForm

}


/*
 *
 *    Copyright (c) 2002 Synthis Corporation.
 *    430 10th Street NW, Suite S-108, Atlanta GA 30318, U.S.A.
 *    All rights reserved.
 *
 *    This software is licensed to you free of charge under
 *    the Apache Software License, so long as this copyright
 *    statement, list of conditions, and comments,  remains
 *    in the source code.  See bottom of file for more
 *    license information.
 *
 *    This software was written to support code generation
 *    for the Apache Struts J2EE architecture by Synthis'
 *    visual application modeling tool Adalon.
 *
 *    For more information on Adalon and Struts code
 *    generation please visit http://www.synthis.com
 *
 */


 /*
  * ====================================================================
  *
  * The Apache Software License, Version 1.1
  *
  * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
  * 4. The names "The Jakarta Project", "Scaffold", and "Apache Software
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

