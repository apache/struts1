<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Content Stylesheet for Struts Documentation -->
<!-- $Id: struts.xsl,v 1.2 2003/01/07 05:50:11 martinc Exp $ -->

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
    <xsl:variable name="project"
                select="document('../project.xml')/project"/>

    <html>
    <head>
    <meta name="author" content="{properties/author/.}"/>
    <!-- <link rel="stylesheet" type="text/css" href="default.css"/> -->
    <xsl:choose>
      <xsl:when test="properties/title">
        <title><xsl:value-of select="properties/title"/></title>
      </xsl:when>
      <xsl:when test="body/title">
        <title><xsl:value-of select="body/title"/></title>
      </xsl:when>
      <xsl:otherwise>
        <title><xsl:value-of select="$project/title"/></title>
      </xsl:otherwise>
    </xsl:choose>
    </head>

    <body bgcolor="{$body-bg}" text="{$body-fg}" link="{$body-link}"
          alink="{$body-link}" vlink="{$body-link}">

    <table border="0" width="100%" cellspacing="5">

      <tr><td colspan="2">
        <a href="http://jakarta.apache.org">
        <img src="images/jakarta-logo.gif" align="left" border="0"/>
        </a>
        <img src="images/struts.gif" align="right" border="0"/>
      </td></tr>

      <tr><td colspan="2">
        <hr/>
      </td></tr>

      <tr>
        <td width="120" valign="top">
          <xsl:apply-templates select="$project"/>
        </td>

        <td valign="top">
          <xsl:apply-templates select="body"/>
        </td>
      </tr>

      <tr><td colspan="2">
        <hr/>
      </td></tr>

      <tr><td colspan="2">
        <div align="center"><font color="{$body-link}" size="-1"><em>
        Copyright (c) 2000-2002, Apache Software Foundation
        </em></font></div>
        <img src="images/struts-power.gif" align="right" border="0"/>
      </td></tr>

    </table>
    </body>
    </html>

  </xsl:template>

  <!-- Process the project element for the navigation bar -->
  <xsl:template match="project">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- Process a menu for the navigation bar -->
  <xsl:template match="menu">
    <table border="0" cellspacing="5">
      <tr>
        <th colspan="2" align="left">
          <font color="{$body-link}"><strong>
            <xsl:value-of select="@name"/>
          </strong></font>
        </th>
      </tr>
      <xsl:apply-templates/>
    </table>
  </xsl:template>


  <!-- Process a menu item for the navigation bar -->
  <xsl:template match="item">
    <tr>
      <td align="center" width="15"></td>
      <td>
        <font size="-1">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a href="{$href}"><xsl:value-of select="@name"/></a>
        </font>
      </td>
    </tr>
  </xsl:template>


  <!-- Process a document body -->
  <xsl:template match="body">
    <xsl:apply-templates/>
  </xsl:template>


  <!-- Process a documentation section -->
  <xsl:template match="section">
    <xsl:choose>
      <xsl:when test="@href">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a name="{$href}"></a>
      </xsl:when>
    </xsl:choose>
    <table border="0" cellspacing="5" cellpadding="5" width="100%">
      <tr><td bgcolor="{$banner-bg}">
        <font color="{$banner-fg}" face="arial,helvetica,sanserif" size="+1">
          <strong><xsl:value-of select="@name"/></strong>
        </font>
      </td></tr>
      <tr><td>
        <blockquote>
          <xsl:apply-templates/>
        </blockquote>
      </td></tr>
    </table>
  </xsl:template>

  <!-- Process a documentation subsection -->
  <xsl:template match="subsection">
    <xsl:choose>
      <xsl:when test="@href">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a name="{$href}"></a>
      </xsl:when>
    </xsl:choose>
    <table border="0" cellspacing="5" cellpadding="5" width="100%">
      <tr><td bgcolor="{$banner-bg}">
        <font color="{$banner-fg}" face="arial,helvetica,sanserif">
          <strong><xsl:value-of select="@name"/></strong>
        </font>
      </td></tr>
      <tr><td>
        <blockquote>
          <xsl:apply-templates/>
        </blockquote>
      </td></tr>
    </table>
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
          <xsl:apply-templates select="info"/>
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
          <xsl:apply-templates select="info"/>
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
                <xsl:apply-templates select="info"/>
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
  <xsl:template match="p">
    <p><xsl:apply-templates/><br/></p>
  </xsl:template>


  <!-- Process a task list section -->
  <xsl:template match="task-list">
    <xsl:choose>
      <xsl:when test="@href">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a name="{$href}"></a>
      </xsl:when>
    </xsl:choose>
    <table border="0" cellspacing="5" cellpadding="5" width="100%">
      <tr><td bgcolor="{$banner-bg}">
        <font color="{$banner-fg}" face="arial,helvetica,sanserif" size="+1">
          <xsl:value-of select="@name"/>
        </font>
      </td></tr>
      <tr><td>
        <xsl:apply-templates select="info"/>
      </td></tr>
      <tr><td>
        <blockquote>
          <table border="1" cellspacing="5" cellpadding="5" width="100%">
            <tr>
              <th width="75%">Description</th>
              <th width="25%">Volunteer</th>
            </tr>
            <xsl:apply-templates select="task"/>
          </table>
        </blockquote>
      </td></tr>
    </table>
  </xsl:template>

  <!-- Process an individual task (in a TODO list) -->
  <xsl:template match="task">
    <tr>
      <td>
        <xsl:choose>
          <xsl:when test="@name">
            <em><xsl:value-of select="@name"/></em>.
          </xsl:when>
        </xsl:choose>
        <xsl:value-of select="info"/>
      </td>
      <td><xsl:value-of select="assigned"/></td>
    </tr>
  </xsl:template>

  <!-- Process everything else by just passing it through -->
  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*|*|text()"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
