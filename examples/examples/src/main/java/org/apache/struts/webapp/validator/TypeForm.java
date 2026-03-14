/*
 * $Id$ 
 *
 * Copyright 2000-2004 The Apache Software Foundation.
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


package org.apache.struts.webapp.validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean for the user type page.
 *
*/
public final class TypeForm extends ValidatorForm implements Serializable {
    private String action = null;
    private String name = null;  //Used to test Multiform per page validation when property name is 'name'
    private String sByte = null;
    private String sShort = null;
    private String sInteger = null;
    private String sLong = null;
    private String sFloat = null;
    private String sFloatRange = null;
    private String sDouble = null;
    private String sDate = null;
    private String sCreditCard = null;
    private String sSatisfaction = null;
    private String[] sOsList = null;
    private String sOverallSatisfaction = null;
    private String sWouldRecommend = null;
    private String[] sUsedLanguages = null;
    private String mask = null;
    private String email = null;
    private String url = null;

    private List lNames = initNames();

  public String getAction() {
 return action;
   }

   public void setAction(String action) {
       this.action = action;
   }

  public String getName() {
 return name;
   }

   public void setName(String name) {
       this.name = name;
   }

     public String getByte() {
       return sByte;
    }

    public void setByte(String sByte) {
       	this.sByte = sByte;
    }

    public String getShort() {
       return sShort;
    }

    public void setShort(String sShort) {
       	this.sShort = sShort;
    }

    public String getInteger() {
       return sInteger;
    }

    public void setInteger(String sInteger) {
       	this.sInteger = sInteger;
    }

    public String getLong() {
       return sLong;
    }

    public void setLong(String sLong) {
       	this.sLong = sLong;
    }

    public String getFloat() {
       return sFloat;
    }

    public void setFloat(String sFloat) {
       	this.sFloat = sFloat;
    }

   /**
    * Float field with range checking
    * @return
    */
    public String getFloatRange() {
       return sFloatRange;
    }

   /**
    * Float field with range checking
    * @param sFloatRange
    */
    public void setFloatRange(String sFloatRange) {
          this.sFloatRange = sFloatRange;
    }

    public String getDouble() {
       return sDouble;
    }

    public void setDouble(String sDouble) {
       	this.sDouble = sDouble;
    }

    public String getDate() {
       return sDate;
    }

    public void setDate(String sDate) {
       	this.sDate = sDate;
    }

    public String getCreditCard() {
       return sCreditCard;
    }

    public void setCreditCard(String sCreditCard) {
       	this.sCreditCard = sCreditCard;
    }

    public String getMask() {
       return mask;
    }

    public void setMask(String mask) {
       	this.mask = mask;
    }

    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       	this.email = email;
    }

    public String getUrl() {
       return url;
    }

    public void setUrl(String url) {
       	this.url = url;
    }

    public String getSatisfaction() {
       return sSatisfaction;
    }

    public void setSatisfaction(String sSatisfaction) {
       	this.sSatisfaction = sSatisfaction;
    }

    public String[] getOsList() {
       return sOsList;
    }

    public void setOsList(String[] anOsList) {
       	this.sOsList = anOsList;
    }

    public String getOverallSatisfaction() {
       return sOverallSatisfaction;
    }

    public void setOverallSatisfaction(String anOverallSatisfaction) {
       	this.sOverallSatisfaction = anOverallSatisfaction;
    }

    public String getWouldRecommend() {
       return sWouldRecommend;
    }

    public void setWouldRecommend(String anWouldRecommend) {
       	this.sWouldRecommend = anWouldRecommend;
    }

    public String[] getUsedLanguages() {
       return sUsedLanguages;
    }

    public void setUsedLanguages(String[] anUsedLanguages) {
       	this.sUsedLanguages = anUsedLanguages;
    }

    public List getNameList() {
       return lNames;
    }

    public void setNameList(List lNames) {
       this.lNames = lNames;
    }

    /**
     * Reset all properties to their default values.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
       String reset = (String)request.getAttribute("typeForm.reset");
       if ((null != reset)|| ("true".equals(reset))) {
           action = null;
           sByte = null;
           sShort = null;
           sInteger = null;
           sLong = null;
           sFloat = null;
           sFloatRange = null;
           sDouble = null;
           sDate = null;
           sCreditCard = null;
           sSatisfaction = null;
           sOsList = null;
           sOverallSatisfaction = null;
           sUsedLanguages = null;
       }
       //lNames = initNames();
    }

    /**
     * Initialize list.
     * @return empty list of LabelValueBeans
    */
    private static List initNames() {
       List lResults = new ArrayList();

       for (int i = 0; i < 3; i++) {
          lResults.add(new LabelValueBean(null, null));
       }

       return lResults;
    }
}
