/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 */


package com.wintecinc.struts.example.validator.jdbc;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import com.wintecinc.struts.action.ActionMessage;
import com.wintecinc.struts.action.ActionMessages;
import com.wintecinc.struts.action.GenericAction;
import com.wintecinc.struts.action.GenericForm;


/**
 * Implementation of <strong>Action</strong> that validates a registration form.
 *
 * @author David Wintefeldt
*/
public class RegistrationAction extends GenericAction {

    /**
     * Inserts a row into the table associated with this Action.
     *
     * @param		request		<code>HttpServletRequest</code> associated with this action.
     * @param		form		<code>GenericForm</code> associated with this action.
    */  
    protected void insert(HttpServletRequest request, GenericForm form) throws Exception {
    	RegistrationForm info = (RegistrationForm)form;
        Connection con = null;
        PreparedStatement pstmt = null;     
        Statement stmt = null;        
        ResultSet rs = null;           
        
        try {
            // Need a logged in user.
            String sUserName = ""; //request.getUserPrincipal().getName();
            con = getConnection();
            con.setAutoCommit(false);

            stmt = con.createStatement();
           
            rs = stmt.executeQuery("SELECT ID FROM REGISTRATION where UPPER(LAST_NAME)='" + info.getLastName().toUpperCase() + "'");
            
            if (rs.next()) {
	       ActionErrors errors = new ActionErrors();
	       String name = info.getLastName() + " " + info.getLastName();
               errors.add("lastName", new ActionError("app.insert.duplicateonunique", name));	

               saveErrors(request, errors);
               throw new DuplicateNameException(name + " already exists.");
            }            
            
            String sQuery = "INSERT INTO REGISTRATION (ID, FIRST_NAME, LAST_NAME, ADDR, CITY, STATE_PROV, ZIP_POSTAL, PHONE, EMAIL, " +
                                                     " B_ACTIVE, DATE_CREATED, DATE_MODIFIED, USER_NAME) " +
                                                     " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            pstmt = con.prepareStatement(sQuery);

	    pstmt.setInt(1, getNextKey(con, "REGISTRATION", "ID"));
            pstmt.setString(2, info.getFirstName());
	    pstmt.setString(3, info.getLastName());
	    pstmt.setString(4, info.getAddr());
	    pstmt.setString(5, info.getCity());
	    pstmt.setString(6, info.getStateProv());
	    pstmt.setString(7, info.getZipPostal());
	    pstmt.setString(8, info.getPhone());
	    pstmt.setString(9, info.getEmail());
	    pstmt.setBoolean(10, true);
	    pstmt.setTimestamp(11, new java.sql.Timestamp(new java.util.Date().getTime())); 
	    pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime())); 
	    pstmt.setString(13, sUserName);
	    
	    pstmt.execute();
	    
	    con.commit();
        } catch (Exception e) {
           if (con != null)
              try { con.rollback(); } catch (Exception ex) {}
           log("RegistrationAction::insert() - " + e.getMessage(), e);
           throw e;
        } finally {
           if (rs != null)
              try { rs.close(); } catch (Exception e) {}
           if (stmt != null)
              try { stmt.close(); } catch (Exception e) {}
           if (pstmt != null)
              try { pstmt.close(); } catch (Exception e) {}
           if (con != null)
              try { con.close(); } catch (Exception e) {}
        }
    }

    /**
     * Updates a row into the table associated with this Action.
     *
     * @param		request		<code>HttpServletRequest</code> associated with this action.
     * @param		form		<code>GenericForm</code> associated with this action.
    */
    protected void update(HttpServletRequest request, GenericForm form) throws Exception {
    	RegistrationForm info = (RegistrationForm)form;
        Connection con = null;
	PreparedStatement pstmt = null;
	
        try {
            // Need a logged in user.
	    String sUserName = ""; //request.getUserPrincipal().getName();
	    
            con = getConnection();
            String sQuery = "UPDATE REGISTRATION SET FIRST_NAME = ?, " +
                                                   " LAST_NAME = ?,       " +
                                                   " ADDR = ?," +
                                                   " CITY = ?," +
                                                   " STATE_PROV = ?," +
                                                   " ZIP_POSTAL = ?," +
                                                   " PHONE = ?," +
                                                   " EMAIL = ?," +
                                                   //" B_ACTIVE = ?," +
                                                   " DATE_MODIFIED = ?," +
                                                   " USER_NAME = ? " + 
                                                   " WHERE ID = ?";

            pstmt = con.prepareStatement(sQuery);

            pstmt.setString(1, info.getFirstName());
            pstmt.setString(2, info.getLastName());
            pstmt.setString(3, info.getAddr());
            pstmt.setString(4, info.getCity());
            pstmt.setString(5, info.getStateProv());
            pstmt.setString(6, info.getZipPostal());
            pstmt.setString(7, info.getPhone());
            pstmt.setString(8, info.getEmail());
	    pstmt.setTimestamp(9, new java.sql.Timestamp(new java.util.Date().getTime())); 
	    pstmt.setString(10, sUserName);
	    pstmt.setInt(11, getKey(info.getId()));

	    pstmt.execute();
	    
	    con.commit();
        } catch (Exception e) {
          log("RegistrationAction::update() - " + e.getMessage(), e);
          throw e;
        } finally {
           if (pstmt != null)
              try { pstmt.close(); } catch (Exception e) {}
           if (con != null)
              try { con.close(); } catch (Exception e) {}
        }
    }

    /**
     * Gets a record from the table associated with this Action and returns 
     * it in a <code>GenericForm</code> object.
     *
     * @param		request		<code>HttpServletRequest</code> associated with this action.
    */    
    protected GenericForm getInfo(HttpServletRequest request, GenericForm form) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        RegistrationForm info = new RegistrationForm();

        try {
           con = getConnection();
           stmt = con.createStatement();
           
           rs = stmt.executeQuery("SELECT ID, FIRST_NAME, LAST_NAME, ADDR, CITY, STATE_PROV, ZIP_POSTAL, " +
                                  " PHONE, EMAIL FROM REGISTRATION where ID=" + getKey(request));

            if (rs.next()) {
                info.setId(rs.getString("ID"));
                info.setFirstName(rs.getString("FIRST_NAME"));
                info.setLastName(rs.getString("LAST_NAME"));
                info.setAddr(rs.getString("ADDR"));
                info.setCity(rs.getString("CITY"));
                info.setStateProv(rs.getString("STATE_PROV"));
                info.setZipPostal(rs.getString("ZIP_POSTAL"));
                info.setPhone(rs.getString("PHONE"));
                info.setEmail(rs.getString("EMAIL"));
            }
            
            // ODBC sticks extra spaces to fill up the field so I remove them 
            // Shouldn't happen if you have a direct driver to MySQL, Oracle, MS SQL*SERVER
            info.setId((info.getId() != null ? info.getId().trim() : ""));
            info.setFirstName((info.getFirstName() != null ? info.getFirstName().trim() : ""));
            info.setLastName((info.getLastName() != null ? info.getLastName().trim() : ""));
            info.setAddr((info.getAddr() != null ? info.getAddr().trim() : ""));
            info.setCity((info.getCity() != null ? info.getCity().trim() : ""));
            info.setStateProv((info.getStateProv() != null ? info.getStateProv().trim() : ""));
            info.setZipPostal((info.getZipPostal() != null ? info.getZipPostal().trim() : ""));
            info.setPhone((info.getPhone() != null ? info.getPhone().trim() : ""));
            info.setEmail((info.getEmail() != null ? info.getEmail().trim() : ""));

        } catch (Exception e) {
          log("RegistrationAction::getInfo() - " + e.getMessage(), e);
        } finally {
           if (rs != null)
              try { rs.close(); } catch (Exception e) {}
           if (stmt != null)
              try { stmt.close(); } catch (Exception e) {}
           if (con != null)
              try { con.close(); } catch (Exception e) {}
        }
        return info;
    }

    /**
     * Deletes a record.
     *
     * @param		request		<code>HttpServletRequest</code> associated with this action.
     * @param		form		<code>GenericForm</code> associated with this action.
    */
    protected void delete(HttpServletRequest request, GenericForm form) throws Exception {
    	RegistrationForm info = (RegistrationForm)form;
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
           con = getConnection();
           String sQuery = "DELETE REGISTRATION WHERE ID=?";
           
           pstmt = con.prepareStatement(sQuery);

	   pstmt.setInt(1, getKey(info.getId()));

	   pstmt.execute();
	   
	   String name = info.getFirstName() + " " + info.getLastName();
	   
	   ActionMessages messages = new ActionMessages();
	   messages.add(new ActionMessage("app.delete", name));	
	   saveMessages(request, messages);
	   
	   con.commit();
        } catch (Exception e) {
          log("RegistrationAction::delete() - " + e.getMessage(), e);
          throw e;
        } finally {
           if (pstmt != null)
              try { pstmt.close(); } catch (Exception e) {}
           if (con != null)
              try { con.close(); } catch (Exception e) {}
        }
    }

    /**
     * Searches the table associated with this Action.
     *
     * @param		request		<code>HttpServletRequest</code> associated with this action.
    */
    protected void search(HttpServletRequest request, GenericForm form) throws Exception {
        Connection con = null;
        Statement stmt = null;
	ResultSet rs = null;
	
	List lResults = new ArrayList();
	RegistrationForm paramInfo = (RegistrationForm)form;
	
        String sQuery = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, EMAIL FROM REGISTRATION ORDER BY LAST_NAME";
        
        if (paramInfo != null && paramInfo.getLastName() != null && paramInfo.getLastName().trim().length() > 0)
           sQuery = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, EMAIL FROM REGISTRATION WHERE UPPER(LAST_NAME) LIKE '" + paramInfo.getLastName().toUpperCase() + "%' ORDER BY LAST_NAME";
           
        try {
           con = getConnection();
	   stmt = con.createStatement();
           rs = stmt.executeQuery(sQuery);

	   while (rs.next()) {
              RegistrationForm info = new RegistrationForm();

              info.setId(rs.getString("ID"));
              info.setFirstName(rs.getString("FIRST_NAME"));
              info.setLastName(rs.getString("LAST_NAME"));
              info.setPhone(rs.getString("PHONE"));
              info.setEmail(rs.getString("EMAIL"));
              lResults.add(info);
	   }

	   saveSearchResults(request, lResults);
        } catch (Exception e) {
          log("RegistrationAction::search() - " + e.getMessage(), e);
          throw e;
        } finally {
           if (rs != null)
              try { rs.close(); } catch (Exception e) {}
           if (stmt != null)
              try { stmt.close(); } catch (Exception e) {}
           if (con != null)
              try { con.close(); } catch (Exception e) {}
        }
    }

    /**
     * Retrieves key from the <code>HttpServletRequest</code>.
    */    
    private int getKey(HttpServletRequest request) {
       return getKey(request.getParameter("id"));
    }

    /**
     * Retrieves key from a <code>String</code>.
    */    
    private int getKey(String sKey) {
       int iKey = 0;
       try { 
          iKey = Integer.parseInt(sKey);
       } catch (Exception e) { }    	
       
       return iKey;
    }

}
