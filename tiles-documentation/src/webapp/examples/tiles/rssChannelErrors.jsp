<%--
/**
 * Summarize channels errors as unadorned HTML.
 *
 * @parameters errors
 * @version $Revision: 1.2 $ $Date$
 */
--%>
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<TABLE border="0" cellspacing="2" cellpadding="4" width="300" align="center" >
<TR>
<TD class="alert">
Error while reading channels.
<br></br>Are you connected ?
</TD>
</TR>
<TR>
<TD class="error" width="100%"><html:errors/></TD>
</TR>

</TABLE>

