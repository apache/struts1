<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Convert Tag Library Documentation into Tag Library Descriptor -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  version="1.0">

  <!-- Output method and formatting -->
  <xsl:output
             method="xml"
             indent="yes"
     doctype-public="-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN"
     doctype-system="http://java.sun.com/j2ee/dtd/web-jsptaglibrary_1_2.dtd"/>
   <xsl:strip-space elements="taglib tag attribute"/>

  <!-- Process the document itself -->
  <xsl:template match="document">
    <xsl:apply-templates select="taglib"/>
  </xsl:template>

  <!-- Process an entire tag library -->
  <xsl:template match="taglib">
    <taglib>
      <xsl:if test="tlib-version">
        <tlib-version><xsl:value-of select="tlib-version"/></tlib-version>
      </xsl:if>
      <xsl:if test="jsp-version">
        <jsp-version><xsl:value-of select="jsp-version"/></jsp-version>
      </xsl:if>
      <xsl:if test="short-name">
        <short-name><xsl:value-of select="short-name"/></short-name>
      </xsl:if>
      <xsl:if test="uri">
        <uri><xsl:value-of select="uri"/></uri>
      </xsl:if>
      <xsl:apply-templates select="validator"/>
      <xsl:apply-templates select="listener"/>
      <xsl:apply-templates select="tag"/>
    </taglib>
  </xsl:template>

  <!-- Process a validator element -->
  <xsl:template match="validator">
    <xsl:apply-templates/>
  </xsl:template>

  <!-- Process a listener element -->
  <xsl:template match="listener">
    <listener>
      <xsl:apply-templates/>
    </listener>
  </xsl:template>

  <!-- Process an individual tag -->
  <xsl:template match="tag">
    <tag>
      <xsl:if test="name">
        <name><xsl:value-of select="name"/></name>
      </xsl:if>
      <xsl:if test="tag-class">
        <tag-class><xsl:value-of select="tag-class"/></tag-class>
      </xsl:if>
      <xsl:if test="tei-class">
        <tei-class><xsl:value-of select="tei-class"/></tei-class>
      </xsl:if>
      <xsl:if test="body-content">
        <body-content><xsl:value-of select="body-content"/></body-content>
      </xsl:if>
      <xsl:apply-templates select="attribute"/>
    </tag>
  </xsl:template>

  <!-- Process an individual tag attribute -->
  <xsl:template match="attribute">
    <attribute>
      <xsl:if test="name">
        <name><xsl:value-of select="name"/></name>
      </xsl:if>
      <xsl:if test="required">
        <required><xsl:value-of select="required"/></required>
      </xsl:if>
      <xsl:if test="rtexprvalue">
        <rtexprvalue><xsl:value-of select="rtexprvalue"/></rtexprvalue>
      </xsl:if>
    </attribute>
  </xsl:template>

  <!-- Process everything else by just passing it through -->
  <xsl:template match="*|@*">
    <xsl:copy>
      <xsl:apply-templates select="@*|*|text()"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
