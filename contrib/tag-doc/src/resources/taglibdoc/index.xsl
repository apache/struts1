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
  <TITLE>Taglib Documentation</TITLE>
</HEAD>
<FRAMESET cols="20%,80%">
  <FRAMESET rows="30%,70%">
    <FRAME src="overview-frame.html" name="taglibListFrame"/>
    <FRAME src="all-tags.html" name="taglibFrame"/>
  </FRAMESET>
  <FRAME src="" name="tagFrame"/>
</FRAMESET>


<NOFRAMES>
  <H2>Frame Alert</H2>
  <P/>
  This document is designed to be viewed using the frames feature. 
  If you see this message, you are using a non-frame-capable web client.
  <BR/>
  Link to <A HREF="overview-frame.html">Non-frame version.</A>
</NOFRAMES>


</HTML>
</xsl:template>
</xsl:stylesheet>
