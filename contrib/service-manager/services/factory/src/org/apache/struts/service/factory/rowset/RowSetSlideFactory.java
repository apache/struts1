/*
 * $Header: /home/cvs/jakarta-struts/contrib/service-manager/services/factory/src/org/apache/struts/service/factory/rowset/Attic/RowSetSlideFactory.java,v 1.1 2001/07/25 20:42:23 oalexeev Exp $
 * $Revision: 1.1 $
 * $Date: 2001/07/25 20:42:23 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
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

package org.apache.struts.service.factory.rowset;

import java.lang.Class;
import java.lang.Throwable;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.RowSet;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.ServletException;
import org.apache.struts.service.factory.Factory;
import org.apache.struts.service.factory.Parameter;
import org.apache.struts.action.Action;
import org.apache.struts.util.Slide;
import org.apache.struts.service.ServletServiceManager;

/** 
 * @author Oleg V Alexeev
 * @version $Revision: 1.1 $ $Date: 2001/07/25 20:42:23 $
 */
public class RowSetSlideFactory extends Factory {

        public Object create( String type, Parameter[] parameters )
           throws Throwable {
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                Integer offset = (Integer)parameters[1].getValue();
                Integer length = (Integer)parameters[2].getValue();
                int total = 0;
                CachedRowSet crs = new CachedRowSet(); 
                Slide slide = new Slide( length.intValue(), 
                        offset.intValue(), new RowSetIterator( crs ) );
                RowSetFactoryMapping factoryMapping= ( RowSetFactoryMapping )getFactoryMapping();
                String dataSourceName = factoryMapping.getDataSource();
                DataSource dataSource = ((ServletServiceManager)getFactoryService().getServiceManager()).getStrutsDataSource( dataSourceName );

                if( dataSource==null )
                        throw new ServletException( "DataSource '" + 
                                   dataSourceName + "' not found!");

                try {
                        conn = dataSource.getConnection();

                        stmt = conn.prepareStatement( (String)parameters[0].getValue() );

                        stmt.setMaxRows( slide.getOffset() + slide.getSlideLength() );

                        for( int i = 3; i < parameters.length; i++ ) {
                                stmt.setObject( i + 1, parameters[i].getValue() );
                        }

                        rs = stmt.executeQuery();
                        
                        for( int i = 0; i < slide.getOffset() - 1; i++, total++ ) rs.next();

                        crs.populate( rs );

                        rs.close();

                        stmt.close();

                } finally {
                        try {
                                conn.close();
                        } catch ( Exception e ) {
                                ;
                        }       
                }     

                slide.setTotalLength( slide.getOffset() + slide.getSlideLength() + 1 );

                return slide;
        }
        
}
