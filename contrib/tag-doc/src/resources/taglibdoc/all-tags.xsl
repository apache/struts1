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
  <LINK REL ="stylesheet" TYPE="text/css" HREF="stylesheet.css" TITLE="Style" />
</HEAD>
<BODY>

<TABLE BORDER="0" WIDTH="100%">
<TR>
<TD NOWRAP="true">
  <DIV CLASS="FrameHeading">
    All Tags
  </DIV>
  <BR/>

<xsl:for-each select="//tag">
<xsl:sort select="name"/>
  <xsl:element name="A">
    <xsl:attribute name="TARGET">tagFrame</xsl:attribute>
    <xsl:attribute name="HREF"><xsl:value-of select="taglib"/>/<xsl:value-of select="name"/>.html</xsl:attribute>
    <xsl:attribute name="CLASS">FrameItem</xsl:attribute>
      <xsl:value-of select="name" />
  </xsl:element>
  <BR/>
</xsl:for-each>

</TD>
</TR>
</TABLE>


</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
