/*
 * $Id$ 
 *
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts.scaffold;


import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
// import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
// import org.apache.struts.validator.ValidatorForm; // Struts 1.1
import com.wintecinc.struts.action.ValidatorForm; // Struts 1.0.x

import org.apache.commons.scaffold.lang.ChainedException;
import org.apache.commons.scaffold.lang.Log;
import org.apache.commons.scaffold.lang.Tokens;
import org.apache.commons.scaffold.text.ConvertUtils;


/**
 * Enhanced base ActionForm.
 * :TODO: Extend from DynaValidatorForm in 1.1 version.
 * @version $Rev$ $Date$
 */
public class BaseForm extends ValidatorForm {


// ---------------------------------------------------------- Remote Host
 
    /**
     * The network address making this request.
     * <p>
     * This is the value returned by reqest.getremoteHost.
     * It is provided so that this can be logged by components on
     * the business tier if needed.
     * This property is maintained automatically through the
     * <code>reset</code> method.
     */
    private String remoteHost = null;

    /**
     * Return our remoteHost property.
     */
    public String getRemoteHost() {
        return (this.remoteHost);
    }

    /**
     * Set our remoteHost property.
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }


     /**
      * Sets RemoteHost attribute to request.getRemoteHost().
      */
     protected void resetRemoteHost(HttpServletRequest request) {
         setRemoteHost(request.getRemoteHost());
     }
 
 
// ------------------------------------------------------- Session Locale

    /**
     * The session attribute key for our session locale [Action.LOCALE_KEY].
     * (Suggestion only, may be overridden by presentation framework
     */
    public static String STRUTS_LOCALE_KEY = Action.LOCALE_KEY;


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
     * Return the session key attribute for our locale object.
     */
    public String getSessionLocaleName() {
        return STRUTS_LOCALE_KEY;
    }


    /**
     * Reset our locale property to the locale object found in
     * the session associated with this request.
     */
    protected void resetSessionLocale(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session!=null) {

            setSessionLocale((Locale)
                session.getAttribute(getSessionLocaleName()));

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


// -------------------------------------------------------------- Mutable

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


// ------------------------------------------------------------- Dispatch

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
     * A static, empty String used by isBlank.
     */
     private static String EMPTY = "";


    /**
     * Convenience method to check for a null or empty String.
     *
     * @param s The sting to check
     */
    protected boolean blank(String s) {
        return ConvertUtils.blank(s);
    }


    /**
     * Convenience method to check for a null, empty, or "0" String.
     *
     * @param s The sting to check
     */
    protected boolean blankValue(String s) {
        return ConvertUtils.blankValue(s);
    }


    /**
     * @deprecated Use blank instead.
     */
    protected boolean isBlank(String s) {
        return blank(s);
    }


    /**
     * Convenience method to test for a required field
     * and setup the error message.
     */
    protected void required(
        ActionErrors errors,
        String field,
        String name,
        String arg) {
        if ((null==field) || (0==field.length())) {
            errors.add(name,
                new ActionError(Tokens.ERRORS_REQUIRED,arg));
        }
    }


    /**
     * Convenience method to test for a required array
     * and setup the error message.
     */
    protected void required(
        ActionErrors errors,
        String[] field,
        String name,
        String arg) {
        if ((null==field) || (0==field.length)) {
            errors.add(name,
                new ActionError(Tokens.ERRORS_REQUIRED,arg));
        }
    }


    /**
     * Create an object of the specified class,
     * throwing a runtime exception if any error occurs.
     * If an exception is not thrown, then helper is guaranteed to exist.
     *
     * @param objectClass The name of the class
     * @throws IllegalArgumentException if object cannot be
     * instantiated
     */
    public Object createObject(
            String objectClass) {

            // Try the create
        Object object = null;
        try {
            object = Class.forName(objectClass).newInstance();
        }
        catch (Throwable t) {
           object = null;
               // assemble message: {class}: {exception}
           StringBuffer sb = new StringBuffer();
           sb.append(Log.CREATE_OBJECT_ERROR);
           sb.append(Log.CLASS);
           sb.append(objectClass);
           sb.append(Log.SPACE);
           sb.append(Log.ACTION_EXCEPTION);
           sb.append(t.toString());
                // throw runtime exception
           throw new IllegalArgumentException(sb.toString());
        }
        return object;

     } // createHelperObject()


    /**
     * Return map of properties for this bean.
     * Base method uses <code>PropertyUtils.describe</code>.
     * Override if some properties should not be transfered
     * this way, or a property name should be altered.
     * This will return the actual public properties.
     *
     * @exception Exception on any error.
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
     * @exception Exception on any error.
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
     * @exception Exception on any error.
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
     * can override on the fly -- If this bean does not supply a
     * property, then the profile property is used. But any 
     * property named on the userProfile is included (even if 
     * it has no match on this bean).
     * <p>
     * If profile is null, a map of this bean's properties is returned.
     * <p>
     * The profile can be any JavaBean.
     * <p>
     * This method is forwardly-compatible with BaseMapForm.
     * For an instance of BaseMapForm, getMap() is used; otherwise
     * describe() or PropertyUtils.describe() is used.
     *
     * :FIXME: Needs testing. Works OK without a profile bean =:o)
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
            // Starting with the formMap, for every element in the userMap, 
            // see if the formMap element is non-existant, null, or an empty String. 
            // If it is (our formMap doesn't override), add the userMap value 
            // to the formMap. 
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


 
    /**
     * If bean is set to mutable, calls <code>resetSessionLocale</code>
     * and <code>resetRemoteHost</code>.
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
            
            super.reset(mapping,request);

           // :TODO: Might be useful to have a collection of reset listeners 
            resetRemoteHost(request);
            resetSessionLocale(request);
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
    

} // end BaseForm