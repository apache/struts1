<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Content Stylesheet for Struts Documentation -->
<!-- $Id: userGuide.xsl,v 1.1 2001/01/08 16:27:31 mschachter Exp $ -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <!-- Output method -->
  <xsl:output method="html" indent="no"/>
  
  <xsl:template match="document">
  
  <html>
  <head>
  <title><xsl:value-of select="./properties/title" /></title>
  </head>
  <body>
  
  		<xsl:apply-templates />
  
  </body>
  </html>
  
  </xsl:template>
  
  
  	<xsl:template match="chapter">
  			
  		<xsl:element name="a">
  			<xsl:attribute name="name"><xsl:value-of select="@href" /></xsl:attribute>
  		</xsl:element>
  		<h2><xsl:value-of select="@name" /></h2>
  		
  		<xsl:apply-templates select="section" />
	</xsl:template>
  			
	<xsl:template match="section">
		<xsl:element name="a">
			<xsl:attribute name="name"><xsl:value-of select="@href" /></xsl:attribute>
		</xsl:element>
		<h3><xsl:value-of select="@name" /></h3>
                <xsl:copy>
                    <xsl:apply-templates select="*|@" />
                </xsl:copy>
	</xsl:template>

        <xsl:template match="properties">
        </xsl:template>
        
          <!-- Process everything else by just passing it through -->
          <xsl:template match="*|@*">
            <xsl:copy>
              <xsl:apply-templates select="@*|*|text()"/>
            </xsl:copy>
          </xsl:template>
</xsl:stylesheet>