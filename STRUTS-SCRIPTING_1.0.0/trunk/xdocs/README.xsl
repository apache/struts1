<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:java_clang="http://xml.apache.org/xalan/java/org.apache.commons.lang"
    xmlns:java_lang="http://xml.apache.org/xalan/java/java.lang">
    
    <xsl:output method="text" />
    <xsl:strip-space elements="*" />
    
    <xsl:variable name="index" select="document('index.xml')" />
    <xsl:variable name="changes" select="document('changes.xml')" />
    <xsl:variable name="source" select="document('source-guide.xml')" />
    
    <xsl:template match="/">
      <xsl:value-of select="concat('The ',project/name,' (Version ',project/currentVersion,')')" />
<xsl:text> README File

============
INTRODUCTION:
============
</xsl:text>
        <xsl:apply-templates select="$index/document/body/section" mode="nosubs" />
        
        <xsl:apply-templates select="$changes/document/body/release[1]" />
        
        <xsl:apply-templates select="$source/document/body/section" />
        
        <xsl:variable name="end">
<xsl:text>This and a lot more information can be found in the Struts Scripting documentation on the website, and if you are reading this after extracting a fresh release, you'll find the same documentation in the /docs directory.
---------------- http://struts.apache.org ----------------
</xsl:text>
        </xsl:variable>
        <xsl:text>&#10;----------------------------------------------------------&#10;</xsl:text>
        <xsl:value-of select="java_clang:WordUtils.wrap($end, 80)" />
        
    </xsl:template>
    
    <xsl:template match="section" mode="nosubs">
        <xsl:apply-templates select="*[name(.) != 'subsection']" />
    </xsl:template>
    
    <xsl:template match="section">
<xsl:text> 

========================
</xsl:text>
      <xsl:variable name="str" select="java_lang:String.new(string(@name))" />
      <xsl:variable name="str1" select="java_lang:toUpperCase($str)" />
      <xsl:value-of select="$str1" />
<xsl:text>
========================
</xsl:text>

        <xsl:apply-templates select="*[name(.) != 'subsection']" />

        <xsl:apply-templates select="subsection" />
    </xsl:template>

    <xsl:template match="subsection">
      <xsl:text>&#10;</xsl:text>
      <xsl:variable name="str" select="java_lang:String.new(string(@name))" />
      <xsl:variable name="str1" select="java_lang:toUpperCase($str)" />
      <xsl:value-of select="$str1" />
      <xsl:text>&#10;</xsl:text>
      
      <xsl:apply-templates />
      
    </xsl:template>

    
    <xsl:template match="release">
<xsl:text> 

========================
NEW AND REVISED FEATURES:
========================
</xsl:text>
      <xsl:text>&#10;New Features:&#10;</xsl:text>
      <xsl:apply-templates select="action[@type='add']" />
      <xsl:text>&#10;Fixes:&#10;</xsl:text>
      <xsl:apply-templates select="action[@type='fix']" />
      <xsl:text>&#10;Miscellaneous:&#10;</xsl:text>
      <xsl:apply-templates select="action[@type != 'fix' and type != 'add']" />      
    </xsl:template>
    
    <xsl:template match="action">
      <xsl:variable name="txt">
          <xsl:apply-templates />
         <xsl:value-of select="concat(' (',@dev,')&#10;')" />
        </xsl:variable>
        <xsl:text> * </xsl:text>
        <xsl:value-of select="java_clang:WordUtils.wrap($txt, 77)" />
    </xsl:template>
    
    <xsl:template match="a[@href and not(starts-with(@href, '#'))]">
      <xsl:text> </xsl:text>
      <xsl:apply-templates  /><xsl:value-of select="concat(' (',@href,')')" />
      <xsl:text> </xsl:text>
    </xsl:template>
    
    <xsl:template match="li[starts-with(a/@href, '#')]" />
    
    <xsl:template match="li[not(starts-with(a/@href, '#'))]">
      <xsl:text> * </xsl:text> 
      <xsl:variable name="txt1">  
        <xsl:apply-templates />
      </xsl:variable>
      <xsl:value-of select="java_clang:WordUtils.wrap($txt1, 77)" />
      <xsl:text>&#10;</xsl:text>
    </xsl:template>
    
    <xsl:template match="ul[not(starts-with(li/a/@href, '#'))]">
      <xsl:text>&#10;</xsl:text><xsl:apply-templates /><xsl:text>&#10;</xsl:text>
    </xsl:template>
    
    <xsl:template match="p">
      <xsl:text>&#10;</xsl:text>
      <xsl:variable name="txt1">  
        <xsl:apply-templates />
      </xsl:variable>
      <xsl:value-of select="java_clang:WordUtils.wrap($txt1, 80)" />
      <xsl:text>&#10;</xsl:text>
    </xsl:template>
    
    <xsl:template match="node()|@*" priority="-1">
      <xsl:copy>
        <xsl:apply-templates select="@*|node()"/>
      </xsl:copy>
    </xsl:template>
    
    <xsl:template match="li/text()|p/text()|action/text()">
      <xsl:variable name="str" select="java_lang:String.new(string(.))" />
      <xsl:variable name="str1" select="java_lang:replaceAll($str, '\s+',' ')" />
      <xsl:choose>
        <xsl:when test="number(.) > 1 and name(..) = 'p'">
          <xsl:value-of select="$str1" />
        </xsl:when>
        <xsl:otherwise>
          <xsl:variable name="str2" select="java_clang:StringUtils.trim($str1)" />
          <xsl:value-of select="$str2" />
        </xsl:otherwise>
      </xsl:choose>  
      
    </xsl:template>
    
    <xsl:template match="text()" priority="-1">
      <xsl:value-of select="." />
    </xsl:template>
    
        
</xsl:stylesheet>
