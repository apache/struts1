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
  <TITLE>Taglib Reports</TITLE>
  <LINK REL="stylesheet" TYPE="text/css" HREF="stylesheet.css" TITLE="Style" />
</HEAD>
<BODY>

<TABLE BORDER="0" WIDTH="100%">
<TR>
<TD NOWRAP="true">
  <DIV CLASS="FrameHeading">
    TagLibs
  </DIV>
  <BR/>

<xsl:for-each select="//shortname">
<xsl:sort select="."/>
  <xsl:element name="A">
    <xsl:attribute name="TARGET">taglibFrame</xsl:attribute>
    <xsl:attribute name="HREF"><xsl:value-of select="." />-report.html</xsl:attribute>
    <xsl:attribute name="CLASS">FrameItem</xsl:attribute>
      <xsl:value-of select="." />
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
