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
  <xsl:value-of select="taglib"/>:<xsl:value-of select="name"/>
</SPAN>
&#160;&#160;&#160;&#160;(
<A HREF="#_description">description</A>
)

<P/>
<!-- ========== ATTRIBUTE SUMMARY =========== -->
<A NAME="attribute_summary"><!-- --></A>

<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="0" WIDTH="100%">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TD COLSPAN="2">
  <SPAN CLASS="FrameHeading">Attribute Summary</SPAN>
</TD>
</TR>

<xsl:for-each select="attribute">
<xsl:sort select="required" order="descending"/>
<xsl:sort select="name" order="ascending"/>
<xsl:element name="TR">
  <xsl:attribute name="CLASS">TableRow<xsl:value-of select="substring(required,1,1)"/>
    <xsl:value-of select="substring(rtexprvalue,1,1)"/>
  </xsl:attribute>
<TD ALIGN="right" VALIGN="top" WIDTH="10%"><FONT SIZE="-1">
  &#160;<xsl:if test="rtexprvalue='true'"><CODE>RTExprValue</CODE></xsl:if>
</FONT></TD>
<TD>
  <xsl:element name="A">
    <xsl:attribute name="HREF">#<xsl:value-of select="name"/></xsl:attribute>
    <B><CODE><xsl:value-of select="name"/></CODE></B>
  </xsl:element>
<BR/>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
<xsl:value-of select="substring(info,1,80)"/>
</TD>
</xsl:element>
</xsl:for-each>

</TABLE>

<P/>

<!-- ============ ATTRIBUTE DETAIL ========== -->
<A NAME="attribute_detail"><!-- --></A>

<TABLE BORDER="1" CELLPADDING="3" CELLSPACING="0" WIDTH="100%">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TD COLSPAN="1">
  <SPAN CLASS="FrameHeading">Attribute Detail</SPAN>
</TD>
</TR>
</TABLE>

<xsl:for-each select="attribute">
<xsl:sort select="required" order="descending"/>
<xsl:sort select="name" order="ascending"/>
<xsl:element name="A">
  <xsl:attribute name="NAME"><xsl:value-of select="name"/></xsl:attribute>
</xsl:element>
<H3><xsl:value-of select="name"/></H3>
<xsl:copy-of select="info"/>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
<TR>
<TD><B>Required:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="required"/></TD>
</TR><TR>
<TD><B>RTExprValue:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="rtexprvalue"/></TD>
</TR>
</TABLE>
<HR/>
</xsl:for-each>

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

<xsl:value-of select="summary" />
<P/>
<xsl:copy-of select="info" />
<P/>
<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
<TR>
<TD><B>Tag Class:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="tagclass"/></TD>
</TR><TR>
<TD><B>Tei Class:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="teiclass"/></TD>
</TR><TR>
<TD><B>Body Content:</B>&#160;&#160;&#160;&#160;</TD>
<TD><xsl:value-of select="bodycontent"/></TD>
</TR>
</TABLE>

</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
