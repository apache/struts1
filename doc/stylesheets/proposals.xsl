<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Content Stylesheet for Struts Documentation -->
<!-- $Id: proposals.xsl,v 1.5 2003/09/05 23:02:40 sraeburn Exp $ -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <!-- Output method -->
  <xsl:output method="xml" 
  	      version="1.0" 
  	      encoding="iso-8859-1" 
  	      omit-xml-declaration="yes" 
  	      doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
  	      doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
  	      indent="yes" 
  	      media-type="text/html"/>

  <!-- Process an entire document into an HTML page -->
  <xsl:template match="document">
    <xsl:variable name="project"
                select="document('../proposals/project.xml')/project"/>

    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
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
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />   
    <meta name="author" content="{properties/author/.}"/>
    <link rel="stylesheet" type="text/css" href="../struts.css"/>
    </head>

    <body>

    <table border="0" width="100%" cellspacing="5">

      <tr><td colspan="2">
        <a href="http://jakarta.apache.org">
        <img src="../images/jakarta-logo.gif" align="left" border="0" alt="The Apache Jakarta Project"/>
        </a>
        <a href="http://jakarta.apache.org/struts">
        <img src="../images/struts.gif" align="right" border="0" alt="Struts"/>
        </a>
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
        <div class="footer">
        Copyright (c) 2000-2003, Apache Software Foundation - <a href="http://nagoya.apache.org/wiki/apachewiki.cgi?StrutsDocComments">Comments?</a>
        </div>
        <img src="../images/struts-power.gif" align="right" border="0" alt="Powered by Struts"/>
      </td></tr>

    </table>
    </body>
    </html>

  </xsl:template>

  <!-- Process the project element for the navigation bar -->
  <xsl:template match="project">
    <xsl:apply-templates/>
  </xsl:template>
  
  <!-- Silently skip title element in project.xml -->
  <xsl:template match="title"/> 

  <!-- Process a menu for the navigation bar -->
  <xsl:template match="menu">
    <table border="0" cellspacing="5">
      <tr>
        <th colspan="2" align="left">
          <span class="menu-title">
            <xsl:value-of select="@name"/>
          </span>
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
        <span class="menu-item">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a href="{$href}"><xsl:value-of select="@name"/></a>
        </span>
      </td>
    </tr>
  </xsl:template>


  <!-- Process a document body -->
  <xsl:template match="body">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- Process an entire chapter (assumes one chapter per page) -->
  <xsl:template match="chapter">
    <xsl:choose>
      <xsl:when test="@href">
        <xsl:variable name="href">
          <xsl:value-of select="@href"/>
        </xsl:variable>
        <a name="{$href}"></a>
      </xsl:when>
    </xsl:choose>
    <table border="0" cellspacing="5" cellpadding="5" width="100%">
      <tr><td class="chapter-title">
          <xsl:value-of select="@name"/>
      </td></tr>
      <tr><td><p>Contributors: </p><ul>
    <xsl:for-each select="/document/properties/author">
    <li><xsl:value-of select="."/></li>
    </xsl:for-each>
    </ul>
      </td></tr>
    </table>
    <xsl:apply-templates select="section" />
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
      <tr><td class="section-title">
          <xsl:value-of select="@name"/>
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
      <tr><td class="subsection-title">
          <xsl:value-of select="@name"/>
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
      <tr><td class="taglib-title">
          <strong><xsl:value-of select="display-name"/></strong>
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
      <tr><td class="tag-title">
          <strong><xsl:value-of select="name"/></strong> -
          <xsl:value-of select="summary"/>
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
      <tr><td class="tasklist">
          <xsl:value-of select="@name"/>
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
