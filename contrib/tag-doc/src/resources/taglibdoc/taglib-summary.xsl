<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output
	method="html"
	version="4.0"
	omit-xml-declaration="yes"
	indent="yes"
/>

<xsl:template match="*">
<HTML>
<HEAD>
  <LINK REL ="stylesheet" TYPE="text/css" HREF="../stylesheet.css" TITLE="Style" />
</HEAD>
<BODY>

<!-- ======== START OF TAG DATA ======== -->
<A NAME="_top"><!-- --></A>

<SPAN CLASS="FrameTitle">
  <xsl:value-of select="//taglib/display-name" />
</SPAN>
&#160;&#160;&#160;&#160;(
<A HREF="#_description">description</A>
)

<P/>
<!-- ========== TAG SUMMARY =========== -->
<A NAME="tag_summary"><!-- --></A>

<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="0" WIDTH="100%">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TD COLSPAN="2">
  <SPAN CLASS="FrameHeading">Tag Summary</SPAN>
</TD>
</TR>

<xsl:for-each select="//taglib/tag">
<xsl:sort select="name" order="ascending"/>
<xsl:element name="TR">
  <xsl:attribute name="CLASS">TableRow</xsl:attribute>
<TD VALIGN="top" WIDTH="10%">
  <xsl:element name="A">
    <xsl:attribute name="HREF"><xsl:value-of select="name"/>.html</xsl:attribute>
    <B><CODE><xsl:value-of select="name"/></CODE></B>
  </xsl:element>
</TD>
<TD>
  <xsl:value-of select="summary"/>
</TD>
</xsl:element>
</xsl:for-each>

</TABLE>

<P/>
<P/>

<!-- ============ DESCRIPTION ========== -->
<A NAME="_description"><!-- --></A>

<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="0" WIDTH="100%">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TD COLSPAN="1">
  <SPAN CLASS="FrameHeading">Description</SPAN>
</TD>
</TR>
</TABLE>
<P/>

<xsl:copy-of select="//taglib/info" />
<P/>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
<TR>
<TD><B>TagLib Version:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="//taglib/tlibversion"/></TD>
</TR><TR>
<TD><B>JSP Version:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="//taglib/jspversion"/></TD>
</TR><TR>
<TD><B>Short Name:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="//taglib/shortname"/></TD>
</TR><TR>
<TD><B>URI:</B>&#160;&#160;&#160;&#160;</TD>
<TD>
  <xsl:element name="A">
    <xsl:attribute name="HREF">
      <xsl:value-of select="//taglib/uri"/>
    </xsl:attribute>
    <xsl:value-of select="//taglib/uri"/>
  </xsl:element>
</TD>
</TR>
</TABLE>

</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
