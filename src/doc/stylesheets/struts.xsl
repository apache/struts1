<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Content Stylesheet for Struts Documentation -->
<!-- $Id: struts.xsl,v 1.1 2000/08/28 02:14:38 craigmcc Exp $ -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <!-- Output Method -->
  <xsl:output method="html" indent="no"/>

  <!-- Defined Variables -->
  <xsl:variable name="color-bg"   select="'#ffffff'"/>
  <xsl:variable name="color-fg"   select="'#000000'"/>
  <xsl:variable name="color-link" select="'#023264'"/>
  <xsl:variable name="color-text" select="'#000000'"/>
  <xsl:variable name="section-bg" select="'#023264'"/>
  <xsl:variable name="section-fg" select="'#ffffff'"/>

  <!-- Match the entire document and process to a web page -->
  <xsl:template match="/">

    <xsl:variable name="project" select="document('../project.xml')/project"/>
    <html>

    <head>
    <meta name="author" content="{document/properties/author/.}"/>
    <!-- <link rel="stylesheet" type="text/css" href="default.css"/> -->
    <xsl:choose>
      <xsl:when test="/document/properties/title">
        <title><xsl:value-of select="/document/properties/title"/></title>
      </xsl:when>
      <xsl:when test="/document/body/title">
        <title><xsl:value-of select="/document/body/title"/></title>
      </xsl:when>
      <xsl:otherwise>
        <title><xsl:value-of select="$project/title"/></title>
      </xsl:otherwise>
    </xsl:choose>
    </head>

    <body bgcolor="{$color-bg}" link="{$color-link}" vlink="{$color-link}"
          alink="{$color-link}" text="{$color-text}">

    <table border="0" cellpadding="0" cellspacing="0" width="100%">

      <tr><td colspan="2">
          <a href="http://jakarta.apache.org">
          <img src="images/jakarta-logo.gif" align="left" border="0"/>
          </a>
          <img src="images/struts.gif" align="right" border="0"/>
      </td></tr>

      <tr><td colspan="2">
          <hr/>
      </td></tr>

      <tr><td width="120" valign="top" cellspacing="2" cellpadding="0">

        <table border="0" cellspacing="2">
          <xsl:for-each select="$project/menu">
            <tr><td align="left" colspan="2">
              <font color="${color-link}"><b>
                <xsl:value-of select="@name"/>
              </b></font>
            </td></tr>
            <xsl:for-each select="item">
              <tr>
                <td width="20">*</td>
                <td>
                  <font size="-1">
                    <xsl:variable name="href">
                      <xsl:value-of select="@href"/>
                    </xsl:variable>
                    <a href="{$href}"><xsl:value-of select="@name"/></a>
                  </font>
                </td>
              </tr>
            </xsl:for-each>
          </xsl:for-each>
        </table>

      </td>
      <td width="480" cellspacing="2" cellpadding="0" valign="top">

        <xsl:apply-templates select="document/body"/>

      </td></tr>

      <tr><td colspan="2">
        <hr/>
      </td></tr>

      <tr><td colspan="2">
        <div align="center"><font size="-1">
        Copyright &#169; 2000, Apache Software Foundation
        </font></div>
        <img src="images/struts-power.gif" align="right" border="0"/>
      </td></tr>

    </table>



    </body>

    </html>

  </xsl:template>


  <!-- Process the document body -->
  <xsl:template match="document/body">

    <table border="0" width="100%">
      <xsl:for-each select="section">
        <tr><td bgcolor="{$section-bg}" cellspacing="5" cellpadding="5">
          <font color="{$section-fg}" face="arial,helvetica,sanserif" size="+1"><b>
            <xsl:value-of select="@name"/>
          </b></font>
        </td></tr>
        <tr><td cellspacing="0" cellpadding="0">
          <blockquote>
            <xsl:apply-templates/>
          </blockquote>
        </td></tr>
      </xsl:for-each>
    </table>

  </xsl:template>

  <!-- Process an individual paragraph -->
  <xsl:template match="p">
    <p><xsl:apply-templates/><br/></p>
  </xsl:template>

  <!-- Process everything else by just passing it through -->
  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*|*|text()"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
