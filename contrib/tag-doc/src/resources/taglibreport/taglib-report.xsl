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

<TABLE BORDER="1" WIDTH="100%" CELLSPACING="0" CELLPADDING="0">

<TR CLASS="TableHeadingColor">
<TD>&#160;</TD>
<TD NOWRAP="true">&#160;<B>body-content</B>&#160;</TD>
<xsl:for-each select="//attributes/attribute">
<xsl:sort select="."/>
  <TD NOWRAP="true" STYLE="width: 75px;">
    &#160;<B><xsl:value-of select="."/></B>&#160;
  </TD>
</xsl:for-each>
</TR>

<!-- Add 'bodycontent' as the second column?? -->

<xsl:for-each select="//taglib/tag">
<xsl:sort select="name"/>
<TR>
  <xsl:variable name="curr_tag" select="."/>
  <TD CLASS="TableHeadingColor">&#160;<B><xsl:value-of select="$curr_tag/name"/></B>&#160;</TD>
  <TD>&#160;<xsl:value-of select="$curr_tag/bodycontent"/>&#160;</TD>
  <xsl:for-each select="//attributes/attribute">
  <xsl:sort select="."/>
    <xsl:variable name="curr_attr" select="."/>
    <xsl:element name="TD">
      <xsl:attribute name="NOWRAP">true</xsl:attribute>
      <xsl:for-each select="$curr_tag/attribute">
      <xsl:sort select="name"/>
        <xsl:if test="./name = $curr_attr">
          <xsl:choose>
            <xsl:when test="./required = 'true'">
              <xsl:attribute name="BGCOLOR">#FF9933</xsl:attribute>
              &#160;
            </xsl:when>
            <xsl:otherwise>
              <xsl:attribute name="BGCOLOR">#FFFF66</xsl:attribute>
              &#160;
            </xsl:otherwise>
          </xsl:choose>
          <xsl:choose>
            <xsl:when test="./rtexprvalue = 'true'">
              RT
            </xsl:when>
          </xsl:choose>
        </xsl:if>
      </xsl:for-each>
      &#160;
    </xsl:element>
  </xsl:for-each>
</TR>
</xsl:for-each>

</TABLE>


</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
