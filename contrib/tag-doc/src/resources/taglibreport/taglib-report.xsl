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
  <STYLE TYPE="text/css">
    #ATTR_HEADER {
      position: absolute;
      top: 0px;
      left: 0px;
      z-index: 1;
    }
    #TAG_HEADER {
      position: absolute;
      top: 0px;
      left: 0px;
      z-index: 2;
    }
    #TOP_LEFT {
      position: absolute;
      top: 0px;
      left: 0px;
      z-index: 3;
    }
    #DATA_TABLE {
      position: absolute;
      top: 0px;
      left: 0px;
    }
  </STYLE>
  <SCRIPT LANGUAGE="JavaScript" SRC="xbPositionableElement.js"/>
  <SCRIPT LANGUAGE="JavaScript">&lt;!--
    var s0, s1, s2;
    if (!document.layers) {
      s0 = new xbPositionableElement('TOP_LEFT', 'left', 'top', 0, 0);
      s1 = new xbPositionableElement('ATTR_HEADER', 'none', 'top', 0, 0);
      s2 = new xbPositionableElement('TAG_HEADER', 'left', 'none', 0, 0);
    }
    function init() {
      if (!document.layers) {
        s0.start();
        s1.start();
        s2.start();
      }
    }
  //--&gt;</SCRIPT>
</HEAD>
<BODY onLoad="init()">

<!-- Data Table -->
<DIV ID="DATA_TABLE">
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
<TR CLASS="TableHeadingColor">
  <TD WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px;">&#160;</DIV>
  </TD>
  <TD WIDTH="75" NOWRAP="true">
    <DIV>&#160;<B>bodyContent</B>&#160;</DIV>
  </TD>
<xsl:for-each select="//attributes/attribute">
<xsl:sort select="."/>
  <TD WIDTH="75" NOWRAP="true">
    <DIV>&#160;<B><xsl:value-of select="."/></B>&#160;</DIV>
  </TD>
</xsl:for-each>
</TR>
<xsl:for-each select="//taglib/tag">
<xsl:sort select="name"/>
<TR>
  <xsl:variable name="curr_tag" select="."/>
  <TD CLASS="TableHeadingColor" WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px; overflow: hidden;">&#160;<B><xsl:value-of select="$curr_tag/name"/></B>&#160;</DIV>
  </TD>
  <TD WIDTH="75" NOWRAP="true">
    &#160;<xsl:value-of select="$curr_tag/bodycontent"/>&#160;
  </TD>
  <xsl:for-each select="//attributes/attribute">
  <xsl:sort select="."/>
    <xsl:variable name="curr_attr" select="."/>
    <xsl:element name="TD">
      <xsl:attribute name="WIDTH">75</xsl:attribute>
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
</DIV>

<!-- Top Left Blank Cell -->
<LAYER VISIBILITY="hide"><DIV ID="TOP_LEFT">
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
<TR CLASS="TableHeadingColor">
  <TD WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px;">&#160;</DIV>
  </TD>
</TR>
</TABLE>
</DIV></LAYER>

<!-- Attribute Header Table -->
<LAYER VISIBILITY="hide"><DIV ID="ATTR_HEADER">
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
<TR CLASS="TableHeadingColor">
  <TD WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px;">&#160;</DIV>
  </TD>
  <TD WIDTH="75" NOWRAP="true">
    <DIV>&#160;<B>bodyContent</B>&#160;</DIV>
  </TD>
<xsl:for-each select="//attributes/attribute">
<xsl:sort select="."/>
  <TD WIDTH="75" NOWRAP="true">
    <DIV>&#160;<B><xsl:value-of select="."/></B>&#160;</DIV>
  </TD>
</xsl:for-each>
</TR>
</TABLE>
</DIV></LAYER>

<!-- Tag Header Table -->
<LAYER VISIBILITY="hide"><DIV ID="TAG_HEADER">
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
<TR CLASS="TableHeadingColor">
  <TD WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px;">&#160;</DIV>
  </TD>
</TR>
<xsl:for-each select="//taglib/tag">
<xsl:sort select="name"/>
<TR CLASS="TableHeadingColor">
  <TD WIDTH="150" NOWRAP="true">
    <DIV STYLE="width: 150px; overflow: hidden;">&#160;<B><xsl:value-of select="./name"/></B>&#160;</DIV>
  </TD>
</TR>
</xsl:for-each>
</TABLE>
</DIV></LAYER>


</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
