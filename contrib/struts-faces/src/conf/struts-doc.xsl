<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Content Stylesheet for Struts Tag Library Documentation -->
<!-- $Id: struts-doc.xsl,v 1.1 2003/03/07 03:22:42 craigmcc Exp $ -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <!-- Output method -->
  <xsl:output method="html" indent="no"/>

  <!-- Defined variables -->
  <xsl:variable name="body-bg"   select="'#ffffff'"/>
  <xsl:variable name="body-fg"   select="'#000000'"/>
  <xsl:variable name="body-link" select="'#023264'"/>
  <xsl:variable name="banner-bg" select="'#023264'"/>
  <xsl:variable name="banner-fg" select="'#ffffff'"/>


  <!-- Process an entire document into an HTML page -->
  <xsl:template match="document">

    <html>
    <head>
    <meta name="author" content="{properties/author/.}"/>
    <title><xsl:value-of select="properties/title"/></title>
    </head>

    <body bgcolor="{$body-bg}" text="{$body-fg}" link="{$body-link}"
          alink="{$body-link}" vlink="{$body-link}">
    <xsl:apply-templates select="taglib"/>
    <hr/>
    <div align="center"><font color="{$body-link}" size="-1"><em>
    Copyright (c) 2002, Apache Software Foundation
    </em></font></div>
    </body>
    </html>

  </xsl:template>


  <!-- Process a tag library section -->
  <xsl:template match="taglib">
    <table border="0" cellspacing="5" cellpadding="5" width="100%">
      <tr><td bgcolor="{$banner-bg}">
        <font color="{$banner-fg}" face="arial,helvetica,sanserif" size="+1">
          <strong><xsl:value-of select="display-name"/></strong>
        </font>
      </td></tr>
      <tr><td>
        <blockquote>
          <xsl:apply-templates select="description"/>
        </blockquote>
      </td></tr>
      <tr><td>
        <blockquote>
          <table border="1" cellspacing="2" cellpadding="2">
            <tr>
              <th width="15%">Tag Name</th>
              <th>Description</th>
            </tr>
            <xsl:for-each select="tag">
              <tr>
                <td align="center">
                  <xsl:variable name="name">
                    <xsl:value-of select="name"/>
                  </xsl:variable>
                  <a href="#{$name}"><xsl:value-of select="name"/></a>
                </td>
                <td>
                  <xsl:value-of select="summary"/>
                </td>
              </tr>
            </xsl:for-each>
          </table>
        </blockquote>
      </td></tr>
    </table>
    <xsl:apply-templates select="tag"/>
  </xsl:template>


  <!-- Process an individual tag -->
  <xsl:template match="tag">
    <xsl:variable name="name">
      <xsl:value-of select="name"/>
    </xsl:variable>
    <a name="{$name}"></a>
    <table border="0" cellspacing="2" cellpadding="2">
      <tr><td bgcolor="{$banner-bg}">
        <font color="{$banner-fg}" face="arial,helvetica,sanserif">
          <strong><xsl:value-of select="name"/></strong> -
          <xsl:value-of select="summary"/>
        </font>
      </td></tr>
      <tr><td>
        <blockquote>
          <xsl:apply-templates select="description"/>
        </blockquote>
      </td></tr>
      <xsl:if test="not(@document-attributes)">
        <xsl:call-template name="document-tag-attributes" />
      </xsl:if>
      <xsl:if test="@document-attributes='true'">
        <xsl:call-template name="document-tag-attributes" />
      </xsl:if>
    </table>
  </xsl:template>


  <!-- Create the table of documentation for a tag -->
  <xsl:template name="document-tag-attributes">
    <tr><td>
      <blockquote>
        <table border="1" cellspacing="2" cellpadding="2">
          <tr>
            <th width="15%">Attribute Name</th>
            <th>Description</th>
          </tr>
          <xsl:for-each select="attribute">
            <tr>
              <td align="center">
                <xsl:value-of select="name"/>
              </td>
              <td>
                <xsl:apply-templates select="description"/>
                <xsl:variable name="required">
                  <xsl:value-of select="required"/>
                </xsl:variable>
                <xsl:if test="required='true'">
                  [Required]
                </xsl:if>
                <xsl:if test="rtexprvalue='true'">
                  [RT Expr]
                </xsl:if>
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </blockquote>
    </td></tr>
  </xsl:template>


  <!-- Process an individual paragraph -->
<!--
  <xsl:template match="p">
    <p><xsl:apply-templates/><br/></p>
  </xsl:template>
-->

  <!-- Process everything else by just passing it through -->
  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*|*|text()"/>
    </xsl:copy>
  </xsl:template>


</xsl:stylesheet>
